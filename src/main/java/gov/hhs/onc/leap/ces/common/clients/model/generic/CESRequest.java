package gov.hhs.onc.leap.ces.common.clients.model.generic;

import java.util.Arrays;
import java.util.List;

import gov.hhs.onc.leap.ces.common.clients.model.xacml.XacmlRequest;

public class CESRequest {
  public class SystemValuePair {
    String system;
    String value;
  }

  List<SystemValuePair> patientId;
  List<SystemValuePair> actor;
  String scope;
  String purposeOfUse;

  public XacmlRequest toXacmlRequest() {

    return null;
  }

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

  public void setPatientId(List<SystemValuePair> patientId) {
    this.patientId = patientId;
  }

  public List<SystemValuePair> getActor() {
    return this.actor;
  }

  public void setActor(List<SystemValuePair> actor) {
    this.actor = actor;
  }

  public String getScope() {
    return this.scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public String getPurposeOfUse() {
    return this.purposeOfUse;
  }

  public void setPurposeOfUse(String purposeOfUse) {
    this.purposeOfUse = purposeOfUse;
  }

  public CESRequest patientId(List<SystemValuePair> patientId) {
    this.patientId = patientId;
    return this;
  }

  public CESRequest actor(List<SystemValuePair> actor) {
    this.actor = actor;
    return this;
  }

  public CESRequest scope(String scope) {
    this.scope = scope;
    return this;
  }

  public CESRequest purposeOfUse(String purposeOfUse) {
    this.purposeOfUse = purposeOfUse;
    return this;
  }
}
