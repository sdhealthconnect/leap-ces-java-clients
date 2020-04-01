/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.onc.leap.ces.common.client.card.tests;

import gov.hhs.onc.leap.ces.common.clients.model.card.Actor;
import gov.hhs.onc.leap.ces.common.clients.model.card.Context;
import gov.hhs.onc.leap.ces.common.clients.model.card.Context.PurposeOfUse;
import gov.hhs.onc.leap.ces.common.clients.model.card.PatientConsentConsultHookRequest;
import gov.hhs.onc.leap.ces.common.clients.model.card.PatientId;
import gov.hhs.onc.leap.ces.common.clients.card.ConsentConsultCardClient;
import gov.hhs.onc.leap.ces.common.clients.model.card.Card;
import gov.hhs.onc.leap.ces.common.clients.model.card.Extension;
import gov.hhs.onc.leap.ces.common.clients.model.card.PatientConsentConsultHookResponse;
import gov.hhs.onc.leap.ces.common.clients.model.card.Source;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/** @author duanedecouteau */
public class ConsentConsultClientCardTests {
  private ConsentConsultCardClient client;
  private String host = "https://sdhc-leap.appspot.com";

  public ConsentConsultClientCardTests() {}

  @BeforeClass
  public static void setUpClass() {}

  @AfterClass
  public static void tearDownClass() {}

  @Before
  public void setUp() {
    client = new ConsentConsultCardClient(host);
  }

  @After
  public void tearDown() {}

  @Test
  public void INTEGRATION_CES_TEST1() throws Exception{
    PatientConsentConsultHookRequest request = new PatientConsentConsultHookRequest();
    Context ctx = new Context();
    PatientId patient = new PatientId();
    patient.setSystem("http://hl7.org/fhir/sid/us-ssn");
    patient.setValue("111111111");
    List<PatientId> lPatient = new ArrayList<PatientId>();
    lPatient.add(patient);
    ctx.setPatientId(lPatient);
    PurposeOfUse pou = PurposeOfUse.TREAT;
    ctx.setPurposeOfUse(pou);
    ctx.setScope(Context.Scope.PATIENT_PRIVACY);
    Actor actor = new Actor();
    actor.setSystem("urn:ietf:rfc:3986");
    actor.setValue("2.16.840.1.113883.20.5");
    List<Actor> lActor = new ArrayList<Actor>();
    lActor.add(actor);
    ctx.setActor(lActor);
    request.setContext(ctx);

    request.setHook("patient-consent-consult");
    request.setHookInstance("123456");

    PatientConsentConsultHookResponse res = client.getConsentDecision(request);
    Card card = res.getCards().get(0);
    String summary = card.getSummary();
    String detail = card.getDetail();
    String indicator = card.getIndicator();
    Source source = card.getSource();
    Extension extension = card.getExtension();

    String decision = extension.getDecision();
    String action = extension.getObligations().get(0).getObligationId().getCode();
    String label = extension.getObligations().get(0).getParameters().getLabels().get(0).getCode();
    System.out.println(decision);

    assert (summary != null);
    assert (detail != null);
    assert (indicator != null);
    assert (source != null);
    assert ("CONSENT_PERMIT".equals(decision));
    assert ("REDACT".equals(action));
    assert ("R".equals(label));
  }

  @Test
  public void INTEGRATION_CES_TEST2() throws Exception {
    String request =
        "{"
            + "   \"hook\": \"patient-consent-consult\","
            + "    \"hookInstance\": \"hook-instance-123\","
            + "    \"context\": {"
            + "      \"patientId\": ["
            + "        {"
            + "          \"system\": \"http://hl7.org/fhir/sid/us-ssn\","
            + "          \"value\": \"111111111\""
            + "        }"
            + "      ],"
            + "      \"scope\" : \"patient-privacy\","
            + "      \"purposeOfUse\": \"TREAT\","
            + "      \"actor\": ["
            + "        {"
            + "          \"system\": \"urn:ietf:rfc:3986\","
            + "          \"value\": \"2.16.840.1.113883.20.5\""
            + "        }"
            + "      ]"
            + "    }"
            + "}";
    PatientConsentConsultHookResponse res = client.getConsentDecision(request);

    Card card = res.getCards().get(0);
    String summary = card.getSummary();
    String detail = card.getDetail();
    String indicator = card.getIndicator();
    Source source = card.getSource();
    Extension extension = card.getExtension();

    String decision = extension.getDecision();
    System.out.println(decision);

    String action = extension.getObligations().get(0).getObligationId().getCode();
    String label = extension.getObligations().get(0).getParameters().getLabels().get(0).getCode();

    assert (summary != null);
    assert (detail != null);
    assert (indicator != null);
    assert (source != null);
    assert ("CONSENT_PERMIT".equals(decision));
    assert ("REDACT".equals(action));
    assert ("R".equals(label));
  }
}
