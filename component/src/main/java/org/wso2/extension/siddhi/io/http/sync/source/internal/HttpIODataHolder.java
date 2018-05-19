/*
 *  Copyright (c) 2017 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.wso2.extension.siddhi.io.http.sync.source.internal;

import org.osgi.framework.BundleContext;
import org.wso2.carbon.analytics.idp.client.core.api.IdPClient;

/**
 * Data holder class for http IO class.
 */
public class HttpIODataHolder {
    private IdPClient client;
    private BundleContext bundleContext;
    private static HttpIODataHolder instance = new HttpIODataHolder();

    private HttpIODataHolder() {
    }

    public static HttpIODataHolder getInstance() {
        return instance;
    }

    public IdPClient getClient() {
        return client;
    }

    public void setClient(IdPClient client) {
        this.client = client;
    }

    public BundleContext getBundleContext() {
        return bundleContext;
    }

    public void setBundleContext(BundleContext bundleContext) {
        this.bundleContext = bundleContext;
    }
}
