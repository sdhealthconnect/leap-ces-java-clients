/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.onc.leap.ces.common.clients.card;

import gov.hhs.onc.leap.ces.common.clients.model.card.PatientConsentConsultHookRequest;
import gov.hhs.onc.leap.ces.common.clients.model.card.PatientConsentConsultHookResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.IOException;

/** @author duanedecouteau */
public class ConsentConsultCardClient {
  private static final Logger LOGGER = Logger.getLogger(ConsentConsultCardClient.class.getName());
  private final String host;
  private final String endpoint = "/cds-services/patient-consent-consult";

  public ConsentConsultCardClient(String host) {
    this.host = host;
  }

  public PatientConsentConsultHookResponse getConsentDecision(String request) throws IOException {

    URL url = new URL(host + endpoint);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setDoOutput(true);
    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/json");
    conn.setRequestProperty("Accept", "application/json");

    OutputStream os = conn.getOutputStream();
    os.write(request.getBytes());
    os.flush();

    if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
      LOGGER.log(
          Level.WARNING,
          "Consent Consult Hook Failed: HTTP error code : " + conn.getResponseCode());
      throw new RuntimeException(
          "Consent Consult Hook Failed: HTTP error code : " + conn.getResponseCode());
    }

    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
    StringBuffer response = new StringBuffer();
    while (br.ready()) {
      response.append("\n" + br.readLine());
    }
    conn.disconnect();

    return new ObjectMapper()
        .readValue(response.toString(), PatientConsentConsultHookResponse.class);
  }

  public PatientConsentConsultHookResponse getConsentDecision(
      PatientConsentConsultHookRequest consentRequest) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    String consentRequestString = mapper.writeValueAsString(consentRequest);
    return getConsentDecision(consentRequestString);
  }
}
