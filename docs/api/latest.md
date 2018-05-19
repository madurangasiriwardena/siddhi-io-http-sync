# API Docs - v1.0.18

## Sink

### http-sync *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#sink">(Sink)</a>*

<p style="word-wrap: break-word">This extension publish the HTTP events in any HTTP method  POST, GET, PUT, DELETE  via HTTP or https protocols. As the additional features this component can provide basic authentication as well as user can publish events using custom client truststore files when publishing events via https protocol. And also user can add any number of headers including HTTP_METHOD header for each event dynamically.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
@sink(type="http-sync", @map(...)))
```

<span id="system-parameters" class="md-typeset" style="display: block; font-weight: bold;">System Parameters</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Parameters</th>
    </tr>
    <tr>
        <td style="vertical-align: top">clientBootstrapBossGroupSize</td>
        <td style="vertical-align: top; word-wrap: break-word">property to configure number of boss threads, which accepts incoming connections until the ports are unbound. Once connection accepts successfully, boss thread passes the accepted channel to one of the worker threads.</td>
        <td style="vertical-align: top">4</td>
        <td style="vertical-align: top">Any integer</td>
    </tr>
    <tr>
        <td style="vertical-align: top">clientBootstrapWorkerGroupSize</td>
        <td style="vertical-align: top; word-wrap: break-word">property to configure number of worker threads, which performs non blocking read and write for one or more channels in non-blocking mode.</td>
        <td style="vertical-align: top">8</td>
        <td style="vertical-align: top">Any integer</td>
    </tr>
    <tr>
        <td style="vertical-align: top">trustStoreLocation</td>
        <td style="vertical-align: top; word-wrap: break-word">The default truststore file path.</td>
        <td style="vertical-align: top">${carbon.home}/resources/security/client-truststore.jks</td>
        <td style="vertical-align: top">Path to client-truststore.jks</td>
    </tr>
    <tr>
        <td style="vertical-align: top">trustStorePassword</td>
        <td style="vertical-align: top; word-wrap: break-word">The default truststore password.</td>
        <td style="vertical-align: top">wso2carbon</td>
        <td style="vertical-align: top">Truststore password</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
@sink(type='http',publisher.url='http://localhost:8009/foo', method='{{method}}',headers="'content-type:xml','content-length:94'",client.bootstrap.configuration="'client.bootstrap.socket.timeout:20','client.bootstrap.worker.group.size:10'",client.pool.configuration="'client.connection.pool.count:10','client.max.active.connections.per.pool:1'" @map(type='xml' , @payload('{{payloadBody}}')))define stream FooStream (payloadBody String, method string, headers string);

```
<p style="word-wrap: break-word">If it is xml mapping expected input should be in following format for FooStream:{&lt;events&gt;    &lt;event&gt;        &lt;symbol&gt;WSO2&lt;/symbol&gt;        &lt;price&gt;55.6&lt;/price&gt;        &lt;volume&gt;100&lt;/volume&gt;    &lt;/event&gt;&lt;/events&gt;,POST,Content-Length:24#Content-Location:USA#Retry-After:120}Above event will generate output as below.~Output http event payload&lt;events&gt;<br>&nbsp;&nbsp;&nbsp;&nbsp;&lt;event&gt;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;symbol&gt;WSO2&lt;/symbol&gt;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;price&gt;55.6&lt;/price&gt;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;volume&gt;100&lt;/volume&gt;<br>&nbsp;&nbsp;&nbsp;&nbsp;&lt;/event&gt;<br>&lt;/events&gt;<br>~Output http event headersContent-Length:24,Content-Location:'USA',Retry-After:120,Content-Type:'application/xml',HTTP_METHOD:'POST',~Output http event propertiesHTTP_METHOD:'POST',HOST:'localhost',PORT:8009PROTOCOL:'http'TO:'/foo'</p>

## Source

### http-sync *<a target="_blank" href="https://wso2.github.io/siddhi/documentation/siddhi-4.0/#source">(Source)</a>*

<p style="word-wrap: break-word">The HTTP source receives POST requests via HTTP or HTTPS in format such as <code>text</code>, <code>XML</code> and <code>JSON</code>. If required, you can enable basic authentication to ensure that events are received only from users who are authorized to access the service.</p>

<span id="syntax" class="md-typeset" style="display: block; font-weight: bold;">Syntax</span>
```
@source(type="http-sync", receiver.url="<STRING>", basic.auth.enabled="<STRING>", worker.count="<STRING>", socket.idle.timeout="<INT>", ssl.verify.client="<STRING>", ssl.protocol="<STRING>", tls.store.type="<STRING>", parameters="<STRING>", ciphers="<STRING>", ssl.enabled.protocols="<STRING>", server.enable.session.creation="<STRING>", server.supported.snimatchers="<STRING>", server.suported.server.names="<STRING>", request.size.validation.configuration="<STRING>", request.size.validation="<STRING>", request.size.validation.maximum.value="<STRING>", request.size.validation.reject.status.code="<STRING>", request.size.validation.reject.message="<STRING>", request.size.validation.reject.message.content.type="<STRING>", header.size.validation="<STRING>", header.validation.maximum.request.line="<STRING>", header.validation.maximum.size="<STRING>", header.validation.maximum.chunk.size="<STRING>", header.validation.reject.status.code="<STRING>", header.validation.reject.message="<STRING>", header.validation.reject.message.content.type="<STRING>", server.bootstrap.configuration="<OBJECT>", server.bootstrap.nodelay="<BOOL>", server.bootstrap.keepalive="<BOOL>", server.bootstrap.sendbuffersize="<INT>", server.bootstrap.recievebuffersize="<INT>", server.bootstrap.connect.timeout="<INT>", server.bootstrap.socket.reuse="<BOOL>", server.bootstrap.socket.timeout="<BOOL>", server.bootstrap.socket.backlog="<BOOL>", trace.log.enabled="<BOOL>", @map(...)))
```

<span id="query-parameters" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">QUERY PARAMETERS</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Data Types</th>
        <th>Optional</th>
        <th>Dynamic</th>
    </tr>
    <tr>
        <td style="vertical-align: top">receiver.url</td>
        <td style="vertical-align: top; word-wrap: break-word">The URL to which the events should be received. User can provide any valid url and if the url is not provided the system will use the following format <code>http://0.0.0.0:9763/&lt;appNAme&gt;/&lt;streamName&gt;</code>If the user want to use SSL the url should be given in following format <code>https://localhost:8080/&lt;streamName&gt;</code></td>
        <td style="vertical-align: top">http://0.0.0.0:9763/<appNAme>/<streamName></td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">basic.auth.enabled</td>
        <td style="vertical-align: top; word-wrap: break-word">If this is set to <code>true</code>, basic authentication is enabled for incoming events, and the credentials with which each event is sent are verified to ensure that the user is authorized to access the service. If basic authentication fails, the event is not authenticated and an authentication error is logged in the CLI. By default this values 'false' </td>
        <td style="vertical-align: top">false</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">worker.count</td>
        <td style="vertical-align: top; word-wrap: break-word">The number of active worker threads to serve the incoming events. The value is 1 by default. This will ensure that the events are directed to the event stream in the same order in which they arrive. By increasing this value the performance might increase at the cost of loosing event ordering.</td>
        <td style="vertical-align: top">1</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">socket.idle.timeout</td>
        <td style="vertical-align: top; word-wrap: break-word">Idle timeout for HTTP connection.</td>
        <td style="vertical-align: top">120000</td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">ssl.verify.client</td>
        <td style="vertical-align: top; word-wrap: break-word">The type of client certificate verification.</td>
        <td style="vertical-align: top">null</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">ssl.protocol</td>
        <td style="vertical-align: top; word-wrap: break-word">ssl/tls related options</td>
        <td style="vertical-align: top">TLS</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">tls.store.type</td>
        <td style="vertical-align: top; word-wrap: break-word">TLS store type.</td>
        <td style="vertical-align: top">JKS</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">parameters</td>
        <td style="vertical-align: top; word-wrap: break-word">Parameters other than basics such as ciphers,sslEnabledProtocols,client.enable.session.creation. Expected format of these parameters is as follows: "'ciphers:xxx','sslEnabledProtocols,client.enable:xxx'"</td>
        <td style="vertical-align: top">null</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">ciphers</td>
        <td style="vertical-align: top; word-wrap: break-word">List of ciphers to be used. This parameter should include under parameters Ex: 'ciphers:TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256'</td>
        <td style="vertical-align: top">null</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">ssl.enabled.protocols</td>
        <td style="vertical-align: top; word-wrap: break-word">SSL/TLS protocols to be enabled. This parameter should be in camel case format(sslEnabledProtocols) under parameters. Ex 'sslEnabledProtocols:true'</td>
        <td style="vertical-align: top">null</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">server.enable.session.creation</td>
        <td style="vertical-align: top; word-wrap: break-word">Enable HTTP session creation.This parameter should include under parameters Ex: 'client.enable.session.creation:true'</td>
        <td style="vertical-align: top">null</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">server.supported.snimatchers</td>
        <td style="vertical-align: top; word-wrap: break-word">Http SNIMatcher to be added. This parameter should include under parameters Ex: 'server.supported.snimatchers:SNIMatcher'</td>
        <td style="vertical-align: top">null</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">server.suported.server.names</td>
        <td style="vertical-align: top; word-wrap: break-word">Http supported servers. This parameter should include under parameters Ex: 'server.suported.server.names:server'</td>
        <td style="vertical-align: top">null</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">request.size.validation.configuration</td>
        <td style="vertical-align: top; word-wrap: break-word">Parameters that responsible for validating the http request and request headers. Expected format of these parameters is as follows: "'request.size.validation:xxx','request.size.validation.maximum.value:xxx'"</td>
        <td style="vertical-align: top">null</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">request.size.validation</td>
        <td style="vertical-align: top; word-wrap: break-word">To enable the request size validation.</td>
        <td style="vertical-align: top">false</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">request.size.validation.maximum.value</td>
        <td style="vertical-align: top; word-wrap: break-word">If request size is validated then maximum size.</td>
        <td style="vertical-align: top">Integer.MAX_VALUE</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">request.size.validation.reject.status.code</td>
        <td style="vertical-align: top; word-wrap: break-word">If request is exceed maximum size and request.size.validation is enabled then status code to be send as response.</td>
        <td style="vertical-align: top">401</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">request.size.validation.reject.message</td>
        <td style="vertical-align: top; word-wrap: break-word">If request is exceed maximum size and request.size.validation is enabled then status message to be send as response.</td>
        <td style="vertical-align: top">Message is bigger than the valid size</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">request.size.validation.reject.message.content.type</td>
        <td style="vertical-align: top; word-wrap: break-word">If request is exceed maximum size and request.size.validation is enabled then content type to be send as response.</td>
        <td style="vertical-align: top">plain/text</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">header.size.validation</td>
        <td style="vertical-align: top; word-wrap: break-word">To enable the header size validation.</td>
        <td style="vertical-align: top">false</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">header.validation.maximum.request.line</td>
        <td style="vertical-align: top; word-wrap: break-word">If header header validation is enabled then the maximum request line.</td>
        <td style="vertical-align: top">4096</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">header.validation.maximum.size</td>
        <td style="vertical-align: top; word-wrap: break-word">If header header validation is enabled then the maximum expected header size.</td>
        <td style="vertical-align: top">8192</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">header.validation.maximum.chunk.size</td>
        <td style="vertical-align: top; word-wrap: break-word">If header header validation is enabled then the maximum expected chunk size.</td>
        <td style="vertical-align: top">8192</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">header.validation.reject.status.code</td>
        <td style="vertical-align: top; word-wrap: break-word">401</td>
        <td style="vertical-align: top">If header is exceed maximum size and header.size.validation is enabled then status code to be send as response.</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">header.validation.reject.message</td>
        <td style="vertical-align: top; word-wrap: break-word">If header is exceed maximum size and header.size.validation is enabled then message to be send as response.</td>
        <td style="vertical-align: top">Message header is bigger than the valid size</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">header.validation.reject.message.content.type</td>
        <td style="vertical-align: top; word-wrap: break-word">If header is exceed maximum size and header.size.validation is enabled then content type to be send as response.</td>
        <td style="vertical-align: top">plain/text</td>
        <td style="vertical-align: top">STRING</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">server.bootstrap.configuration</td>
        <td style="vertical-align: top; word-wrap: break-word">Parameters that for bootstrap configurations of the server. Expected format of these parameters is as follows: "'ciphers:xxx','sslEnabledProtocols,client.enable:xxx'"</td>
        <td style="vertical-align: top">null</td>
        <td style="vertical-align: top">OBJECT</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">server.bootstrap.nodelay</td>
        <td style="vertical-align: top; word-wrap: break-word">Http server no delay.</td>
        <td style="vertical-align: top">true</td>
        <td style="vertical-align: top">BOOL</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">server.bootstrap.keepalive</td>
        <td style="vertical-align: top; word-wrap: break-word">Http server keep alive.</td>
        <td style="vertical-align: top">true</td>
        <td style="vertical-align: top">BOOL</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">server.bootstrap.sendbuffersize</td>
        <td style="vertical-align: top; word-wrap: break-word">Http server send buffer size.</td>
        <td style="vertical-align: top">1048576</td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">server.bootstrap.recievebuffersize</td>
        <td style="vertical-align: top; word-wrap: break-word">Http server receive buffer size.</td>
        <td style="vertical-align: top">1048576</td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">server.bootstrap.connect.timeout</td>
        <td style="vertical-align: top; word-wrap: break-word">Http server connection timeout.</td>
        <td style="vertical-align: top">15000</td>
        <td style="vertical-align: top">INT</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">server.bootstrap.socket.reuse</td>
        <td style="vertical-align: top; word-wrap: break-word">To enable http socket reuse.</td>
        <td style="vertical-align: top">false</td>
        <td style="vertical-align: top">BOOL</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">server.bootstrap.socket.timeout</td>
        <td style="vertical-align: top; word-wrap: break-word">Http server socket timeout.</td>
        <td style="vertical-align: top">15</td>
        <td style="vertical-align: top">BOOL</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">server.bootstrap.socket.backlog</td>
        <td style="vertical-align: top; word-wrap: break-word">THttp server socket backlog.</td>
        <td style="vertical-align: top">100</td>
        <td style="vertical-align: top">BOOL</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
    <tr>
        <td style="vertical-align: top">trace.log.enabled</td>
        <td style="vertical-align: top; word-wrap: break-word">Http traffic monitoring.</td>
        <td style="vertical-align: top">false</td>
        <td style="vertical-align: top">BOOL</td>
        <td style="vertical-align: top">Yes</td>
        <td style="vertical-align: top">No</td>
    </tr>
</table>

<span id="system-parameters" class="md-typeset" style="display: block; font-weight: bold;">System Parameters</span>
<table>
    <tr>
        <th>Name</th>
        <th style="min-width: 20em">Description</th>
        <th>Default Value</th>
        <th>Possible Parameters</th>
    </tr>
    <tr>
        <td style="vertical-align: top">serverBootstrapBossGroupSize</td>
        <td style="vertical-align: top; word-wrap: break-word">property to configure number of boss threads, which accepts incoming connections until the ports are unbound. Once connection accepts successfully, boss thread passes the accepted channel to one of the worker threads.</td>
        <td style="vertical-align: top">4</td>
        <td style="vertical-align: top">Any integer</td>
    </tr>
    <tr>
        <td style="vertical-align: top">serverBootstrapWorkerGroupSize</td>
        <td style="vertical-align: top; word-wrap: break-word">property to configure number of worker threads, which performs non blocking read and write for one or more channels in non-blocking mode.</td>
        <td style="vertical-align: top">8</td>
        <td style="vertical-align: top">Any integer</td>
    </tr>
    <tr>
        <td style="vertical-align: top">defaultHost</td>
        <td style="vertical-align: top; word-wrap: break-word">The default host of the transport.</td>
        <td style="vertical-align: top">0.0.0.0</td>
        <td style="vertical-align: top">Any valid host</td>
    </tr>
    <tr>
        <td style="vertical-align: top">defaultHttpPort</td>
        <td style="vertical-align: top; word-wrap: break-word">The default port if the default scheme is 'http'.</td>
        <td style="vertical-align: top">8280</td>
        <td style="vertical-align: top">Any valid port</td>
    </tr>
    <tr>
        <td style="vertical-align: top">defaultHttpsPort</td>
        <td style="vertical-align: top; word-wrap: break-word">The default port if the default scheme is 'https'.</td>
        <td style="vertical-align: top">8243</td>
        <td style="vertical-align: top">Any valid port</td>
    </tr>
    <tr>
        <td style="vertical-align: top">defaultScheme</td>
        <td style="vertical-align: top; word-wrap: break-word">The default protocol.</td>
        <td style="vertical-align: top">http</td>
        <td style="vertical-align: top">http<br>https</td>
    </tr>
    <tr>
        <td style="vertical-align: top">keyStoreLocation</td>
        <td style="vertical-align: top; word-wrap: break-word">The default keystore file path.</td>
        <td style="vertical-align: top">${carbon.home}/resources/security/wso2carbon.jks</td>
        <td style="vertical-align: top">Path to wso2carbon.jks file</td>
    </tr>
    <tr>
        <td style="vertical-align: top">keyStorePassword</td>
        <td style="vertical-align: top; word-wrap: break-word">The default keystore password.</td>
        <td style="vertical-align: top">wso2carbon</td>
        <td style="vertical-align: top">String of keystore password</td>
    </tr>
    <tr>
        <td style="vertical-align: top">certPassword</td>
        <td style="vertical-align: top; word-wrap: break-word">The default cert password.</td>
        <td style="vertical-align: top">wso2carbon</td>
        <td style="vertical-align: top">String of cert password</td>
    </tr>
</table>

<span id="examples" class="md-typeset" style="display: block; font-weight: bold;">Examples</span>
<span id="example-1" class="md-typeset" style="display: block; color: rgba(0, 0, 0, 0.54); font-size: 12.8px; font-weight: bold;">EXAMPLE 1</span>
```
@source(type='http', receiver.url='http://localhost:9055/endpoints/RecPro', socketIdleTimeout='150000', parameters="'ciphers : TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256', 'sslEnabledProtocols:TLSv1.1,TLSv1.2'",request.size.validation.configuration="request.size.validation:true",server.bootstrap.configuration="server.bootstrap.socket.timeout:25" @map(type='xml'))
define stream FooStream (symbol string, price float, volume long);

```
<p style="word-wrap: break-word">Above source listenerConfiguration performs a default XML input mapping. The expected input is as follows:&lt;events&gt;<br>&nbsp;&nbsp;&nbsp;&nbsp;&lt;event&gt;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;symbol&gt;WSO2&lt;/symbol&gt;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;price&gt;55.6&lt;/price&gt;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;volume&gt;100&lt;/volume&gt;<br>&nbsp;&nbsp;&nbsp;&nbsp;&lt;/event&gt;<br>&lt;/events&gt;<br>If basic authentication is enabled via the <code>basic.auth.enabled='true</code> setting, each input event is also expected to contain the <code>Authorization:'Basic encodeBase64(username:Password)'</code> header.</p>

