/*
 * Copyright 2020 PDB Corp.
 *
 * Proprietary Software built off of open-source software?
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pdbcorp.eap.uni.config;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author jaradat-pdb
 */
@Component
@Slf4j
public final class HttpsClientCxfConduit {

	private HttpsUtils httpsUtils;

	private String enableHttps;

	@Autowired
	public HttpsClientCxfConduit(HttpsUtils httpsUtils, @Value("${https.enabled}") String enableHttps) {
		this.httpsUtils = httpsUtils;
		this.enableHttps = enableHttps;
	}

	public final Object setHttpsClientConduit(Object clientProxy) {
		Client soapClient = ClientProxy.getClient(clientProxy);
		HTTPConduit httpConduit = (HTTPConduit) soapClient.getConduit();
		HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
		httpClientPolicy.setConnectionTimeout(60000);
		httpClientPolicy.setReceiveTimeout(3600000);
		httpClientPolicy.setAllowChunking(false);
		httpConduit.setClient(httpClientPolicy);
		
		if(enableHttps.equalsIgnoreCase("true")) {
			if(log.isTraceEnabled()) {
				log.trace("Setting conduit client TLS parameters, enableHttps: {}", enableHttps);
			}
			httpsUtils.initializeTlsParametersForConduit();
			httpConduit.setTlsClientParameters(httpsUtils.getTlsParams());
			if(log.isTraceEnabled()) {
				log.trace("Successfully set conduit client TLS parameters", enableHttps);
			}
		}
		
		return clientProxy;
	}

}
