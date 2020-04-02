/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.onc.leap.ces.common.client.xacml.tests;

import gov.hhs.onc.leap.ces.common.clients.model.generic.CESRequest;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.AccessSubject;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.Action;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.StringAttribute;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.ConceptAttribute;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.Request;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.Resource;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.Response;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.SystemValue;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.XacmlRequest;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.XacmlResponse;
import gov.hhs.onc.leap.ces.common.clients.xacml.ConsentConsultXacmlClient;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/** @author duanedecouteau */
public class ConsentConsultClientXacmlTests {
  private ConsentConsultXacmlClient client;
  private String host = "https://sdhc-leap.appspot.com";

  public ConsentConsultClientXacmlTests() {}

  @BeforeClass
  public static void setUpClass() {}

  @AfterClass
  public static void tearDownClass() {}

  @Before
  public void setUp() {
    client = new ConsentConsultXacmlClient(host);
  }

  @After
  public void tearDown() {}

  @Test
  public void INTEGRATION_CES_TEST1() throws Exception {
    XacmlRequest xacmlRequest = new XacmlRequest();
    Request request = new Request();

    // set requestor info
    AccessSubject subject = new AccessSubject();
    ConceptAttribute subjAttr = new ConceptAttribute().setAttributeId("actor");
    SystemValue subjValue =
        new SystemValue().setSystem("urn:ietf:rfc:3986").setValue("2.16.840.1.113883.20.5");

    subjAttr.setValue(Arrays.asList(subjValue));
    subject.setAttribute(Arrays.asList(subjAttr));
    request.setAccessSubject(Arrays.asList(subject));

    // set resource
    Resource resource = new Resource();
    SystemValue resourceValue =
        new SystemValue().setSystem("http://hl7.org/fhir/sid/us-ssn").setValue("111111111");
    ConceptAttribute resourceAttr =
        new ConceptAttribute().setAttributeId("patientId").setValue(Arrays.asList(resourceValue));
    resource.setAttribute(Arrays.asList(resourceAttr));
    request.setResource(Arrays.asList(resource));

    // set action
    Action action = new Action();
    StringAttribute actionAttrScope =
        new StringAttribute().setAttributeId("scope").setValue("patient-privacy");
    StringAttribute actionAttrPOU =
        new StringAttribute().setAttributeId("purposeOfUse").setValue("TREAT");

    action.setAttribute(Arrays.asList(new StringAttribute[] {actionAttrScope, actionAttrPOU}));
    request.setAction(Arrays.asList(action));
    xacmlRequest.setRequest(request);

    XacmlResponse xacmlResponse = client.getConsentDecision(xacmlRequest);
    Response res = xacmlResponse.getResponse().get(0);
    String decision = res.getDecision();
    String obligationAction = res.getObligations().get(0).getObligationId().getCode();
    String obligationActionSystem = res.getObligations().get(0).getObligationId().getSystem();
    String securityLabel =
        res.getObligations()
            .get(0)
            .getAttributeAssignments()
            .get(0)
            .getSystemCodes()
            .get(0)
            .getCode();

    assert ("Permit".equals(decision));
    assert ("REDACT".equals(obligationAction));
    assert ("http://terminology.hl7.org/CodeSystem/v3-ActCode".equals(obligationActionSystem));
    assert ("R".equals(securityLabel));
  }

  @Test
  public void INTEGRATION_CES_TEST2() throws Exception {
    String xacmlRequestString =
        "{"
            + "  \"Request\":{"
            + "    \"AccessSubject\":["
            + "      {"
            + "        \"Attribute\":["
            + "          {"
            + "            \"AttributeId\":\"actor\","
            + "            \"Value\":["
            + "              {"
            + "                \"system\":\"urn:ietf:rfc:3986\","
            + "                \"value\":\"2.16.840.1.113883.20.5\""
            + "              }"
            + "            ]"
            + "          }"
            + "        ]"
            + "      }"
            + "    ],"
            + "    \"Action\":["
            + "      {"
            + "        \"Attribute\":["
            + "          {"
            + "            \"AttributeId\":\"scope\","
            + "            \"Value\":\"patient-privacy\""
            + "          },"
            + "          {"
            + "            \"AttributeId\":\"purposeOfUse\","
            + "            \"Value\":\"TREAT\""
            + "          }"
            + "        ]"
            + "      }"
            + "    ],"
            + "    \"Resource\":["
            + "      {"
            + "        \"Attribute\":["
            + "          {"
            + "            \"AttributeId\":\"patientId\","
            + "            \"Value\":["
            + "              {"
            + "                \"system\":\"http://hl7.org/fhir/sid/us-ssn\","
            + "                \"value\":\"111111111\""
            + "              }"
            + "            ]"
            + "          }"
            + "        ]"
            + "      }"
            + "    ]"
            + "  }"
            + "}";

    XacmlResponse xacmlResponse = client.getConsentDecision(xacmlRequestString);
    Response res = xacmlResponse.getResponse().get(0);
    String decision = res.getDecision();
    String obligationAction = res.getObligations().get(0).getObligationId().getCode();
    String obligationActionSystem = res.getObligations().get(0).getObligationId().getSystem();
    String securityLabel =
        res.getObligations()
            .get(0)
            .getAttributeAssignments()
            .get(0)
            .getSystemCodes()
            .get(0)
            .getCode();

    assert ("Permit".equals(decision));
    assert ("REDACT".equals(obligationAction));
    assert ("http://terminology.hl7.org/CodeSystem/v3-ActCode".equals(obligationActionSystem));
    assert ("R".equals(securityLabel));
  }

  @Test
  public void INTEGRATION_CES_TEST3() throws Exception {
    CESRequest request =
        new CESRequest()
            .setScope("patient-privacy")
            .setPurposeOfUse("TREAT")
            .setPatientId(
                Arrays.asList(
                    new CESRequest.SystemValuePair("http://hl7.org/fhir/sid/us-ssn", "111111111")))
            .setActor(
                Arrays.asList(
                    new CESRequest.SystemValuePair("urn:ietf:rfc:3986", "2.16.840.1.113883.20.5")));

    XacmlResponse xacmlResponse = client.getConsentDecision(request.toXacmlRequest());
    Response res = xacmlResponse.getResponse().get(0);
    String decision = res.getDecision();
    String obligationAction = res.getObligations().get(0).getObligationId().getCode();
    String obligationActionSystem = res.getObligations().get(0).getObligationId().getSystem();
    String securityLabel =
        res.getObligations()
            .get(0)
            .getAttributeAssignments()
            .get(0)
            .getSystemCodes()
            .get(0)
            .getCode();

    assert ("Permit".equals(decision));
    assert ("REDACT".equals(obligationAction));
    assert ("http://terminology.hl7.org/CodeSystem/v3-ActCode".equals(obligationActionSystem));
    assert ("R".equals(securityLabel));
  }

  @Test
  public void INTEGRATION_CES_TEST4() throws Exception {

    CESRequest.SystemValuePair[] patientIds =
        new CESRequest.SystemValuePair[] {
          new CESRequest.SystemValuePair("http://hl7.org/fhir/sid/us-ssn", "111111111"),
          new CESRequest.SystemValuePair("http://hl7.org/fhir/sid/us-medicare", "0000-000-0000"),
        };

    CESRequest request =
        new CESRequest()
            .setScope("patient-privacy")
            .setPurposeOfUse("TREAT")
            .setPatientId(Arrays.asList(patientIds))
            .setActor(
                Arrays.asList(
                    new CESRequest.SystemValuePair("urn:ietf:rfc:3986", "2.16.840.1.113883.20.5")));

    XacmlResponse xacmlResponse = client.getConsentDecision(request.toXacmlRequest());
    Response res = xacmlResponse.getResponse().get(0);
    String decision = res.getDecision();
    String obligationAction = res.getObligations().get(0).getObligationId().getCode();
    String obligationActionSystem = res.getObligations().get(0).getObligationId().getSystem();
    String securityLabel =
        res.getObligations()
            .get(0)
            .getAttributeAssignments()
            .get(0)
            .getSystemCodes()
            .get(0)
            .getCode();

    assert ("Permit".equals(decision));
    assert ("REDACT".equals(obligationAction));
    assert ("http://terminology.hl7.org/CodeSystem/v3-ActCode".equals(obligationActionSystem));
    assert ("R".equals(securityLabel));
  }
}
