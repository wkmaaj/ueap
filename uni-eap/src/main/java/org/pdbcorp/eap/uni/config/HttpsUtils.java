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

import java.security.KeyStore;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.configuration.security.FiltersType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author jaradat-pdb
 */
@Component
@Slf4j
final class HttpsUtils {

	/* (non-Javadoc)
	 * Use of the volatile keyword results in the value of this variable being stored in main
	 * memory and thus ensures a constant value for this variable among threads in a
	 * multi-threaded environment.
	 */
	/**
	 * Used to track if HTTPS settings have been configured, default value is false.
	 */
	private volatile boolean tlsEnabled = false;

	private final TLSClientParameters tlsParams = new TLSClientParameters();

	public final String truststoreLocation;

	public final String truststorePassword;

	HttpsUtils(
			@Value("${truststore.location}") String truststoreLocation, @Value("${truststore.password}") String truststorePassword) {
		
		this.truststoreLocation = truststoreLocation;
		this.truststorePassword = truststorePassword;
	}

	/**
	 * 
	 * @return tlsEnabled
	 */
	public boolean isTlsEnabled() {
		if(log.isTraceEnabled()) {
			log.trace("Returning TLS enabled");
		}
		return tlsEnabled;
	}

	/**
	 * 
	 * @return tlsParams
	 */
	public final TLSClientParameters getTlsParams() {
		if(log.isTraceEnabled()) {
			log.trace("Returning TLS parameters");
		}
		return tlsParams;
	}

	public final void initializeTlsParametersForConduit() {
		if(log.isTraceEnabled()) {
			log.trace("Checking if HTTPS settings have been configured, isTlsEnabled: {}", tlsEnabled);
		}
		if(!tlsEnabled) {
			try {
				tlsEnabled = true;
				if(log.isTraceEnabled()) {
					log.trace("Configuring HTTPS setting, isTlsEnabled: {}", tlsEnabled);
				}
				
				/* Loading client and server keystores to use in SSL handshake */
				Resource clientstoreResource = new ClassPathResource("*.jks");
				KeyStore clientstore = KeyStore.getInstance("JKS");
				clientstore.load(clientstoreResource.getInputStream(), "password".toCharArray());
				
				Resource truststoreResource = new ClassPathResource(truststoreLocation);
				KeyStore truststore = KeyStore.getInstance("JKS");
				truststore.load(truststoreResource.getInputStream(), truststorePassword.toCharArray());
				if(log.isTraceEnabled()) {
					log.trace("Successfully loaded client and server keystores");
				}
				
				/* Disables checking that the server hostname matches the CN name defined in the subject of the server certificate */
				tlsParams.setDisableCNCheck(true);
				tlsParams.setSecureSocketProtocol("TLSv1.2");
				
				/* Loading key material(s) for client to use when authenticating to a remote server (i.e. to use when initiating SSL handshake) */
				KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
				kmf.init(clientstore, "password".toCharArray());
				KeyManager[] km = kmf.getKeyManagers();
				tlsParams.setKeyManagers(km);
				if(log.isTraceEnabled()) {
					log.trace("Successfully set key managers in TLS parameters");
				}
				
				/* Loading trust material(s) (X509 Certificates) for client to use when deciding to trust a remote server */
				TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
				tmf.init(truststore);
				TrustManager[] tm = tmf.getTrustManagers();
				tlsParams.setTrustManagers(tm);
				if(log.isTraceEnabled()) {
					log.trace("Successfully set trust managers in TLS parameters");
				}
				
				/* Defines the cipher suites allowed for use when encrypting communication during the SSL handshake */
				FiltersType allowableCipherSuites = new FiltersType();
				allowableCipherSuites.getInclude().add(".*_WITH_AES_.*");
				allowableCipherSuites.getInclude().add(".*_WITH_3DES_.*");
				tlsParams.setCipherSuitesFilter(allowableCipherSuites);
				if(log.isTraceEnabled()) {
					log.trace("Successfully set allowable cipher suites filter in TLS parameters\nSuccessfully initialized TLS parameters");
				}
			}
			catch(Exception e) {
				log.error("Error encountered initializing the TLS parameters, refer to stacktrace below:\n", e);
			}
		}
	}

}
