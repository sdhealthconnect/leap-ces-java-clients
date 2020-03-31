/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.onc.leap.ces.common.clients.xacml;

import org.apache.http.client.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.XacmlRequest;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.XacmlResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duanedecouteau
 */
public class ConsentConsultXacmlClient {
    private final static Logger LOGGER = Logger.getLogger(ConsentConsultXacmlClient.class.getName());
    private final String host;
    private final String endpoint = "/xacml";
    private final XacmlRequest consentRequest;

    private static final Header CDS_CLIENT_HEADER_CONTENT = new BasicHeader(HttpHeaders.CONTENT_TYPE,
            "application/json");
    private static final Header CDS_CLIENT_HEADER_ACCEPTS = new BasicHeader(HttpHeaders.ACCEPT, "application/json");

    public ConsentConsultXacmlClient(String host, XacmlRequest consentRequest) {
        this.host = host;
        this.consentRequest = consentRequest;
    }

    public XacmlResponse requestDecisionSecured() {
        XacmlResponse result = new XacmlResponse();
        CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier())
                .setDefaultHeaders(getDefaultHeaders()).build();
        try {
            HttpPost postRequest = new HttpPost(host + endpoint);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(consentRequest);
            StringEntity input = new StringEntity(jsonString);

            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest);

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            String output;
            StringBuffer sb = new StringBuffer();

            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            LOGGER.log(Level.INFO, String.format("Patient Consent Consult Response: ", sb.toString()));

            result = mapper.readValue(sb.toString(), XacmlResponse.class);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, String.format("Patient Consent Consult Failure: ", ex.getMessage()));
            ex.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception exClose) {
                LOGGER.log(Level.WARNING,
                        String.format("Consent Consult Client Failed to Close: ", exClose.getMessage()));
            }
        }
        return result;
    }

    private List<Header> getDefaultHeaders() {
        List<Header> defaultHeaders = new ArrayList<Header>();
        defaultHeaders.add(CDS_CLIENT_HEADER_CONTENT);
        defaultHeaders.add(CDS_CLIENT_HEADER_ACCEPTS);
        return defaultHeaders;
    }

}
