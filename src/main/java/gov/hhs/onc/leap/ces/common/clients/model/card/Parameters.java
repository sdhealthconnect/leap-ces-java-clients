/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.onc.leap.ces.common.clients.model.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

/** @author duanedecouteau */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"labels"})
public class Parameters {

  @JsonProperty("labels")
  private List<Labels> labels;

  /** @return the labels */
  @JsonProperty("labels")
  public List<Labels> getLabels() {
    return labels;
  }

  /** @param labels the labels to set */
  @JsonProperty("labels")
  public void setLabels(List<Labels> labels) {
    this.labels = labels;
  }
}
