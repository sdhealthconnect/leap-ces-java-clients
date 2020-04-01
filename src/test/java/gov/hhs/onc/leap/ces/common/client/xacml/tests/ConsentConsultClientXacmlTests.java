/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.onc.leap.ces.common.client.xacml.tests;

import gov.hhs.onc.leap.ces.common.clients.model.xacml.AccessSubject;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.Action;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.ActionAttribute;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.Attribute;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.Request;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.Resource;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.Response;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.SystemValue;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.XacmlRequest;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.XacmlResponse;
import gov.hhs.onc.leap.ces.common.clients.xacml.ConsentConsultXacmlClient;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author duanedecouteau
 */
public class ConsentConsultClientXacmlTests {
  private ConsentConsultXacmlClient client;
  private String host = "https://sdhc-leap.appspot.com";

  public ConsentConsultClientXacmlTests() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
    client = new ConsentConsultXacmlClient(host);
  }

  @After
  public void tearDown() {
  }

  @Test
  public void INTEGRATION_CES_TEST1() throws Exception {
    XacmlRequest xacmlRequest = new XacmlRequest();
    Request request = new Request();

    // set requestor info
    List<AccessSubject> subjectList = new ArrayList<AccessSubject>();
    AccessSubject subject = new AccessSubject();
    List<Attribute> subjAttrList = new ArrayList<Attribute>();
    Attribute subjAttr = new Attribute();
    subjAttr.setAttributeId("actor");
    SystemValue subjValue = new SystemValue();
    subjValue.setSystem("urn:ietf:rfc:3986");
    subjValue.setValue("2.16.840.1.113883.20.5");
    List<SystemValue> subjSysValList = new ArrayList<SystemValue>();
    subjSysValList.add(subjValue);
    subjAttr.setValue(subjSysValList);
    subjAttrList.add(subjAttr);
    subject.setAttribute(subjAttrList);
    subjectList.add(subject);
    request.setAccessSubject(subjectList);

    // set resource
    List<Resource> resourceList = new ArrayList<Resource>();
    Resource resource = new Resource();
    Attribute resourceAttr = new Attribute();
    resourceAttr.setAttributeId("patientId");
    SystemValue resourceValue = new SystemValue();
    resourceValue.setSystem("http://hl7.org/fhir/sid/us-ssn");
    resourceValue.setValue("111111111");
    List<SystemValue> sysValList = new ArrayList<SystemValue>();
    sysValList.add(resourceValue);
    resourceAttr.setValue(sysValList);
    List<Attribute> resourceAttrList = new ArrayList<Attribute>();
    resourceAttrList.add(resourceAttr);
    resource.setAttribute(resourceAttrList);
    resourceList.add(resource);
    request.setResource(resourceList);

    // set action
    List<Action> actionList = new ArrayList<Action>();
    Action action = new Action();
    List<ActionAttribute> actionAttrList = new ArrayList<ActionAttribute>();
    ActionAttribute actionAttrScope = new ActionAttribute();
    actionAttrScope.setAttributeId("scope");
    actionAttrScope.setValue("patient-privacy");
    ActionAttribute actionAttrPOU = new ActionAttribute();
    actionAttrPOU.setAttributeId("purposeOfUse");
    actionAttrPOU.setValue("TREAT");
    actionAttrList.add(actionAttrScope);
    actionAttrList.add(actionAttrPOU);
    action.setAttribute(actionAttrList);
    actionList.add(action);
    request.setAction(actionList);
    xacmlRequest.setRequest(request);

    XacmlResponse xacmlResponse = client.getConsentDecision(xacmlRequest);
    Response res = xacmlResponse.getResponse().get(0);
    String decision = res.getDecision();
    String obligationAction = res.getObligations().get(0).getObligationId().getCode();
    String obligationActionSystem = res.getObligations().get(0).getObligationId().getSystem();
    String securityLabel = res.getObligations().get(0).getAttributeAssignments().get(0).getSystemCodes().get(0)
        .getCode();

    assert ("Permit".equals(decision));
    assert ("REDACT".equals(obligationAction));
    assert ("http://terminology.hl7.org/CodeSystem/v3-ActCode".equals(obligationActionSystem));
    assert ("R".equals(securityLabel));
  }

  @Test
  public void INTEGRATION_CES_TEST2() throws Exception {
    String xacmlRequestString = "{" +
    "  \"Request\":{" +
    "    \"AccessSubject\":[" +
    "      {" +
    "        \"Attribute\":[" +
    "          {" +
    "            \"AttributeId\":\"actor\"," +
    "            \"Value\":[" +
    "              {" +
    "                \"system\":\"urn:ietf:rfc:3986\"," +
    "                \"value\":\"2.16.840.1.113883.20.5\"" +
    "              }" +
    "            ]" +
    "          }" +
    "        ]" +
    "      }" +
    "    ]," +
    "    \"Action\":[" +
    "      {" +
    "        \"Attribute\":[" +
    "          {" +
    "            \"AttributeId\":\"scope\"," +
    "            \"Value\":\"patient-privacy\"" +
    "          }," +
    "          {" +
    "            \"AttributeId\":\"purposeOfUse\"," +
    "            \"Value\":\"TREAT\"" +
    "          }" +
    "        ]" +
    "      }" +
    "    ]," +
    "    \"Resource\":[" +
    "      {" +
    "        \"Attribute\":[" +
    "          {" +
    "            \"AttributeId\":\"patientId\"," +
    "            \"Value\":[" +
    "              {" +
    "                \"system\":\"http://hl7.org/fhir/sid/us-ssn\"," +
    "                \"value\":\"111111111\"" +
    "              }" +
    "            ]" +
    "          }" +
    "        ]" +
    "      }" +
    "    ]" +
    "  }" +
    "}";
  
    XacmlResponse xacmlResponse = client.getConsentDecision(xacmlRequestString);
    Response res = xacmlResponse.getResponse().get(0);
    String decision = res.getDecision();
    String obligationAction = res.getObligations().get(0).getObligationId().getCode();
    String obligationActionSystem = res.getObligations().get(0).getObligationId().getSystem();
    String securityLabel = res.getObligations().get(0).getAttributeAssignments().get(0).getSystemCodes().get(0)
        .getCode();

    assert ("Permit".equals(decision));
    assert ("REDACT".equals(obligationAction));
    assert ("http://terminology.hl7.org/CodeSystem/v3-ActCode".equals(obligationActionSystem));
    assert ("R".equals(securityLabel));
  }
}
