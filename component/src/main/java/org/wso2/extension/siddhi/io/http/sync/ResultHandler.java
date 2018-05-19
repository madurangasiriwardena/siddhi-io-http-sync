package org.wso2.extension.siddhi.io.http.sync;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.DefaultLastHttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import org.wso2.extension.siddhi.io.http.sync.source.ResultContainer;
import org.wso2.extension.siddhi.io.http.sync.source.exception.HttpSourceAdaptorRuntimeException;
import org.wso2.transport.http.netty.message.HTTPCarbonMessage;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Result handler
 */
public class ResultHandler {

    private static ResultHandler instance = new ResultHandler();
    private static Map<String, ResultContainer> resultContainerMap = new ConcurrentHashMap<>();

    public static ResultHandler getInstance() {

        return instance;
    }

    public void addResultContainer(String id, ResultContainer resultContainer) {
        resultContainerMap.put(id, resultContainer);
    }

    public ResultContainer getResultContainer(String id) {
        return resultContainerMap.get(id);
    }

    public static void registerCallback(HTTPCarbonMessage carbonMessage, String id) {
        ResultContainer resultContainer = new ResultContainer();
        resultContainer.setMessage(carbonMessage);

        resultContainerMap.put(id, resultContainer);
    }

    public static void handleCallback(String id, String payload) {

        ResultContainer resultContainer = resultContainerMap.get(id);

        handleResponse(resultContainer.getMessage(), 200, payload);

    }

    private static void handleResponse(HTTPCarbonMessage requestMsg, HTTPCarbonMessage responseMsg) {
        try {
            requestMsg.respond(responseMsg);
        } catch (org.wso2.transport.http.netty.contract.ServerConnectorException e) {
            throw new HttpSourceAdaptorRuntimeException("Error occurred during response", e);
        }
    }

    /**
     * Handle failure.
     *
     * @param requestMessage
     * @param code
     * @param payload
     */
    public static void handleResponse(HTTPCarbonMessage requestMessage, Integer code, String payload) {
        int statusCode = (code == null) ? 500 : code;
        String responsePayload = (payload != null) ? payload : "";
        handleResponse(requestMessage, createResponseMessage(responsePayload, statusCode));
    }

    /**
     * Create new HTTP carbon message.
     *
     * @param payload
     * @param statusCode
     * @return
     */
    private static HTTPCarbonMessage createResponseMessage(String payload, int statusCode) {

        HTTPCarbonMessage response = createHttpCarbonMessage(false);
//        StringDataSource stringDataSource = new StringDataSource(payload
//                , new HttpMessageDataStreamer(response).getOutputStream());
//        response.setMessageDataSource(stringDataSource);
//        byte[] errorMessageBytes = payload.getBytes(Charset.defaultCharset());

        response.addHttpContent(new DefaultLastHttpContent(Unpooled.wrappedBuffer(payload
                .getBytes(Charset.defaultCharset()))));

        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.set(org.wso2.transport.http.netty.common.Constants.HTTP_CONNECTION,
                org.wso2.transport.http.netty.common.Constants.CONNECTION_KEEP_ALIVE);
        httpHeaders.set(org.wso2.transport.http.netty.common.Constants.HTTP_CONTENT_TYPE,
                "application/json");
//        httpHeaders.set(org.wso2.transport.http.netty.common.Constants.HTTP_CONTENT_LENGTH,
//                (String.valueOf(errorMessageBytes.length)));

        response.setProperty(org.wso2.transport.http.netty.common.Constants.HTTP_STATUS_CODE, statusCode);
        response.setProperty(org.wso2.carbon.messaging.Constants.DIRECTION,
                org.wso2.carbon.messaging.Constants.DIRECTION_RESPONSE);
        return response;
    }

    /**
     * Create new HTTP carbon messge.
     *
     * @param isRequest
     * @return
     */
    private static HTTPCarbonMessage createHttpCarbonMessage(boolean isRequest) {
        HTTPCarbonMessage httpCarbonMessage;
        if (isRequest) {
            httpCarbonMessage = new HTTPCarbonMessage(
                    new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, ""));
            httpCarbonMessage.setEndOfMsgAdded(true);
        } else {
            httpCarbonMessage = new HTTPCarbonMessage(
                    new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK));
            httpCarbonMessage.setEndOfMsgAdded(true);
        }
        return httpCarbonMessage;
    }
}
