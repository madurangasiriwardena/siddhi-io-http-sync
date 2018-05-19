/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.extension.siddhi.io.http.sync.source;

import org.apache.log4j.Logger;
import org.wso2.transport.http.netty.message.HTTPCarbonMessage;

import java.util.concurrent.CountDownLatch;

/**
 * Container object blocks the request thread, collects result from IS-Analytics and return the request thread.
 */
public class ResultContainer {
    private static final Logger log = Logger.getLogger(ResultContainer.class);
    private CountDownLatch latch;
    private HTTPCarbonMessage message;

    public ResultContainer() {
        this.latch = new CountDownLatch(1);
//        try {
//            this.numberOfRules = ServiceValueHolder.getInstance().getTemplateManagerService()
//                    .getConfigurations(Constants.TEMPLATE_MANAGER_DOMAIN_NAME).size();
//        } catch (TemplateManagerException e) {
//            latch.countDown();
//            log.error("Failed to get number of rules deployed. " + e.getMessage(), e);
//        }
    }

//    /**
//     * Upon receiving a result from risk score stream this method will update the result list and handle locking
//     * mechanisms.
//     *
//     * @param score risk score for the request
//     */
//    public void addResult(int score) {
//        riskScoreList.add(score);
//        if (riskScoreList.size() == numberOfRules) {
//            latch.countDown();
//            if (log.isDebugEnabled()) {
//                log.debug("Result is added to the container. Releasing the thread.");
//            }
//        }
//    }
//
//    /**
//     * Wait for other threads to post results.
//     *
//     * @return risk score
//     */
//    public int getRiskScoreDTO() throws InterruptedException {
//        latch.await(1, TimeUnit.SECONDS);
//        int riskScore = Constants.DEFAULT_RISK_SCORE;
//        if (numberOfRules > 0 && riskScoreList.size() == numberOfRules) {
//            riskScore = Collections.max(riskScoreList);
//        }
//        return riskScore;
//    }

    public HTTPCarbonMessage getMessage() {

        return message;
    }

    public void setMessage(HTTPCarbonMessage message) {

        this.message = message;
    }
}
