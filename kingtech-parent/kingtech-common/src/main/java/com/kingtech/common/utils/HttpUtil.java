package com.kingtech.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  @author sunjian
 */
public class HttpUtil {
    public static final String ENCODING = "UTF-8";
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private final static int TIME_OUT = 20 * 1000;


    private static HttpClient getSimpleHttpClient(String url, HttpParams httpParameters) {
        HttpClient httpclient;
        boolean isHTTPS = url.startsWith("https");
        if (isHTTPS) {
            httpclient = newHttpsClient();
        } else {
            httpclient = new DefaultHttpClient(httpParameters);
        }
        return httpclient;
    }

    public static String getGetResponseByUrl(String url) throws
            											 IOException {
        HttpClient httpClient = null;
        HttpGet httpGet = null;
        try {
        	logger.info("request url: " + url);
            HttpParams httpParameters = getHttpParamsWithTimeOut();
            httpClient = getSimpleHttpClient(url, httpParameters);
            httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            String result = convertInputStream(response.getEntity().getContent(), ENCODING);
            logger.info("response content: " + result);
            return result;
        } finally {
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
            if (httpClient != null) {
                httpClient.getConnectionManager().shutdown();
            }
        }
    }
    
    public static String postFormResponse(String url, final Map<String, String> formParams) throws 
	   																						IOException {
    	return postResponseWithParameterMap(url, formParams, new HashMap<String, String>());
    }
    
	public static String postResponseWithParameterMap(String url, final Map<String, String> formParams, Map<String, String> headerMap) throws 
																																	   IOException {
		HttpClient httpClient = null;
    	HttpPost httpPost = null;
		try {
			logger.info("requestContent: " + formParams);
			HttpParams httpParameters = getHttpParamsWithTimeOut();
			httpClient = getSimpleHttpClient(url, httpParameters);
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(convert(formParams), ENCODING);
			httpPost = new HttpPost(url);
			httpPost.setEntity(entity);
			for (Map.Entry<String, String> headerEntry : headerMap.entrySet()) {
                httpPost.addHeader(headerEntry.getKey(), headerEntry.getValue());
            }
			HttpResponse response = httpClient.execute(httpPost);

			String responseString = convertInputStream(response.getEntity().getContent(), ENCODING);
			logger.info("responseString: " + responseString);
            return responseString;
		} finally {
            if (httpPost != null) {
            	httpPost.releaseConnection();
            }
            if (httpClient != null) {
            	httpClient.getConnectionManager().shutdown();
            }
        }
	}
	
	public static String postJsonResponse(String url, String requestBody) throws 
																	  IOException {
		return postResponseWithHeader(url, requestBody, new HashMap<String, String>());
	}

    public static String postResponseWithHeader(String url, String requestBody, Map<String, String> headerMap) throws
    																										   IOException {
    	HttpClient httpClient = null;
    	HttpPost httpPost = null;
        try {
            logger.info("requestBody: " + requestBody);
            HttpParams httpParameters = getHttpParamsWithTimeOut();
            httpClient = getSimpleHttpClient(url, httpParameters);
            httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(requestBody, ENCODING));
            for (Map.Entry<String, String> headerEntry : headerMap.entrySet()) {
                httpPost.addHeader(headerEntry.getKey(), headerEntry.getValue());
            }
            HttpResponse response = httpClient.execute(httpPost);
            String responseString = convertInputStream(response.getEntity().getContent(), ENCODING);
            logger.info("responseString: " + responseString);
            return responseString;
        } finally {
            if (httpPost != null) {
            	httpPost.releaseConnection();
            }
            if (httpClient != null) {
            	httpClient.getConnectionManager().shutdown();
            }
        }
    }


    private static List<NameValuePair> convert(Map<String, String> map) {
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        if (map != null) {
            Set<String> keys = map.keySet();
            for (String key : keys) {
                formParams.add(new BasicNameValuePair(key, map.get(key)));
            }
        }
        return formParams;
    }

    private static HttpParams getHttpParamsWithTimeOut() {
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setSoTimeout(httpParameters, TIME_OUT);
        HttpConnectionParams.setConnectionTimeout(httpParameters, TIME_OUT);
        return httpParameters;
    }

    private static String convertInputStream(InputStream in, String encoding) {
        if (in == null)
            return null;
        try (Reader r = new BufferedReader(new InputStreamReader(new BufferedInputStream(in), encoding), 1024)) {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = r.read()) != -1)
                sb.append((char) c);
            return sb.toString();
        } catch (IOException e) {
            return null;
        }
    }

    private static HttpClient newHttpsClient() {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);

            SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
            HttpConnectionParams.setConnectionTimeout(params, TIME_OUT);
            HttpConnectionParams.setSoTimeout(params, TIME_OUT);

            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", sf, 443));

            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

            return new DefaultHttpClient(ccm, params);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }

    private static class MySSLSocketFactory extends SSLSocketFactory {
        SSLContext sslContext = SSLContext.getInstance("TLS");

        public MySSLSocketFactory(KeyStore truststore)
                throws
                NoSuchAlgorithmException,
                KeyManagementException,
                KeyStoreException,
                UnrecoverableKeyException {
            super(truststore);

            TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType)
                        throws
                        CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType)
                        throws
                        CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            sslContext.init(null, new TrustManager[]{tm}, null);
            this.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        }

        @Override
        public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws
                IOException,
                UnknownHostException {
            return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
        }

        @Override
        public Socket createSocket() throws
                IOException {
            return sslContext.getSocketFactory().createSocket();
        }
    }
}
