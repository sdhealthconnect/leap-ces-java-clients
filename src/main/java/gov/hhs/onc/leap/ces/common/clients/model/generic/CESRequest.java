package gov.hhs.onc.leap.ces.common.clients.model.generic;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import gov.hhs.onc.leap.ces.common.clients.model.xacml.AccessSubject;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.Action;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.StringAttribute;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.ConceptAttribute;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.Request;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.Resource;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.Response;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.SystemValue;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.XacmlRequest;

public class CESRequest {
  public static class SystemValuePair {
    String system;
    String value;

    public SystemValuePair(String system, String value) {
      this.system = system;
      this.value = value;
    }
  }

  List<SystemValuePair> patientId;
  List<SystemValuePair> actor;
  String scope;
  String purposeOfUse;

  public XacmlRequest toXacmlRequest() {
    XacmlRequest xacmlRequest = new XacmlRequest();
    Request request = new Request();

    // set requestor info
    AccessSubject subject = new AccessSubject();
    ConceptAttribute subjAttr = new ConceptAttribute().setAttributeId("actor");

    List<SystemValue> xacmlActorSystemValue = new ArrayList<SystemValue>();
    for (SystemValuePair actorId : actor) {
      xacmlActorSystemValue.add(
          new SystemValue().setSystem(actorId.system).setValue(actorId.value));
    }
    subjAttr.setValue(xacmlActorSystemValue);

    subject.setAttribute(Arrays.asList(subjAttr));
    request.setAccessSubject(Arrays.asList(subject));

    // set resource
    Resource resource = new Resource();
    List<SystemValue> xacmlPatientSystemValue = new ArrayList<SystemValue>();
    for (SystemValuePair aPatientId : patientId) {
      xacmlPatientSystemValue.add(
          new SystemValue().setSystem(aPatientId.system).setValue(aPatientId.value));
    }

    ConceptAttribute resourceAttr =
        new ConceptAttribute().setAttributeId("patientId").setValue(xacmlPatientSystemValue);
    resource.setAttribute(Arrays.asList(resourceAttr));
    request.setResource(Arrays.asList(resource));

    // set action
    Action action = new Action();
    StringAttribute actionAttrScope = new StringAttribute().setAttributeId("scope").setValue(scope);
    StringAttribute actionAttrPOU =
        new StringAttribute().setAttributeId("purposeOfUse").setValue(purposeOfUse);

    action.setAttribute(Arrays.asList(new StringAttribute[] {actionAttrScope, actionAttrPOU}));
    request.setAction(Arrays.asList(action));
    xacmlRequest.setRequest(request);

    return xacmlRequest;
  }

  public CESRequest() {}

  public CESRequest(
      List<SystemValuePair> patientId,
      List<SystemValuePair> actor,
      String scope,
      String purposeOfUse) {
    this.patientId = patientId;
    this.actor = actor;
    this.scope = scope;
    this.purposeOfUse = purposeOfUse;
  }

  public List<SystemValuePair> getPatientId() {
    return this.patientId;
  }

  public List<SystemValuePair> getActor() {
    return this.actor;
  }

  public String getScope() {
    return this.scope;
  }

  public String getPurposeOfUse() {
    return this.purposeOfUse;
  }

  public CESRequest setPatientId(List<SystemValuePair> patientId) {
    this.patientId = patientId;
    return this;
  }

  public CESRequest setActor(List<SystemValuePair> actor) {
    this.actor = actor;
    return this;
  }

  public CESRequest setScope(String scope) {
    this.scope = scope;
    return this;
  }

  public CESRequest setPurposeOfUse(String purposeOfUse) {
    this.purposeOfUse = purposeOfUse;
    return this;
  }
}
