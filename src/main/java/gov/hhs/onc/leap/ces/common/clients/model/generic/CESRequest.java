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

import gov.hhs.onc.leap.ces.common.clients.model.xacml.SystemValue;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.XacmlRequest;
import gov.hhs.onc.leap.ces.common.clients.model.card.Actor;
import gov.hhs.onc.leap.ces.common.clients.model.card.Context;

import gov.hhs.onc.leap.ces.common.clients.model.generic.CESRequest;
import gov.hhs.onc.leap.ces.common.clients.model.card.PatientConsentConsultHookRequest;
import gov.hhs.onc.leap.ces.common.clients.model.card.PatientId;
import gov.hhs.onc.leap.ces.common.clients.model.card.ContentClass;

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
  List<SystemValuePair> contentClass;
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

    List<ConceptAttribute> resourceAttributes = new ArrayList<ConceptAttribute>();

    ConceptAttribute patientIdAttr =
      new ConceptAttribute().setAttributeId("patientId").setValue(xacmlPatientSystemValue);
    
    resourceAttributes.add(patientIdAttr);

    maybeAddContentClassToXacmlRequest(resourceAttributes);

    resource.setAttribute(resourceAttributes);
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

  private void maybeAddContentClassToXacmlRequest(List<ConceptAttribute> resourceAttributes) {
    if (contentClass == null) {
      return;
    }
    
    List<SystemValue> xacmlContentClassSystemValue = new ArrayList<SystemValue>();
    for (SystemValuePair aContentClass : contentClass) {
      xacmlContentClassSystemValue.add(
          new SystemValue().setSystem(aContentClass.system).setValue(aContentClass.value));
    }

    ConceptAttribute contentClassAttr = 
      new ConceptAttribute().setAttributeId("class").setValue(xacmlContentClassSystemValue);
    resourceAttributes.add(contentClassAttr);
  }

  public PatientConsentConsultHookRequest toHookRequest(String hookInstance) {
    List<PatientId> patienIds = new ArrayList<PatientId>();
    for (SystemValuePair aPatientId : patientId) {
      patienIds.add(new PatientId().setSystem(aPatientId.system).setValue(aPatientId.value));
    }

    List<Actor> actorIds = new ArrayList<Actor>();
    for (SystemValuePair actorId : actor) {
      actorIds.add(new Actor().setSystem(actorId.system).setValue(actorId.value));
    }

    List<ContentClass> contentClasses = new ArrayList<ContentClass>();
    for (SystemValuePair aContentClass : contentClass) {
      contentClasses.add(new ContentClass().setSystem(aContentClass.system).setValue(aContentClass.value));
    }

    Context ctx =
        new Context()
            .setPatientId(patienIds)
            .setPurposeOfUse(Context.PurposeOfUse.fromValue(purposeOfUse))
            .setScope(Context.Scope.fromValue(scope))
            .setActor(actorIds)
            .setContentClass(contentClasses);

    PatientConsentConsultHookRequest request =
        new PatientConsentConsultHookRequest()
            .setContext(ctx)
            .setHook("patient-consent-consult")
            .setHookInstance(hookInstance);
    return request;
  }

  public CESRequest() {}

  public CESRequest(
      List<SystemValuePair> patientId,
      List<SystemValuePair> actor,
      List<SystemValuePair> contentClass,
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

  public List<SystemValuePair> getContentClass() {
    return this.contentClass;
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

  public CESRequest setContentClass(List<SystemValuePair> contentClass) {
    this.contentClass = contentClass;
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
