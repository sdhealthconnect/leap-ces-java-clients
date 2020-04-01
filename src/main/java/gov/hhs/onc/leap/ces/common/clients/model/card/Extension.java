/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.onc.leap.ces.common.clients.model.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;

/** @author duanedecouteau */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"Decision", "Obligations"})
public class Extension {

  @JsonProperty("decision")
  private String decision;

  @JsonProperty("obligations")
  private List<Obligations> obligations = new ArrayList<Obligations>();

  /** @return the obligations */
  @JsonProperty("obligations")
  public List<Obligations> getObligations() {
    return obligations;
  }

  /** @param obligations the obligations to set */
  @JsonProperty("obligations")
  public void setObligations(List<Obligations> obligations) {
    this.obligations = obligations;
  }

  /** @return the decision */
  @JsonProperty("decision")
  public String getDecision() {
    return decision;
  }

  /** @param decision the decision to set */
  @JsonProperty("decision")
  public void setDecision(String decision) {
    this.decision = decision;
  }
}
