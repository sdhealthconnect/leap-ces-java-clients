/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.onc.leap.ces.common.clients.model.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/** @author duanedecouteau */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "parameters"})
public class Obligations {

  @JsonProperty("id")
  private ObligationId obligationId;

  @JsonProperty("parameters")
  private Parameters parameters;

  /** @return the obligationId */
  @JsonProperty("id")
  public ObligationId getObligationId() {
    return obligationId;
  }

  /** @param obligationId the obligationId to set */
  @JsonProperty("id")
  public void setObligationId(ObligationId obligationId) {
    this.obligationId = obligationId;
  }

  /** @return the attributeAssignments */
  @JsonProperty("parameters")
  public Parameters getParameters() {
    return parameters;
  }

  /** @param attributeAssignments the attributeAssignments to set */
  @JsonProperty("parameters")
  public void setParameters(Parameters parameters) {
    this.parameters = parameters;
  }
}
