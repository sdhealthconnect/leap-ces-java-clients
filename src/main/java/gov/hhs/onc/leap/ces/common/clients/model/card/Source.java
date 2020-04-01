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
@JsonPropertyOrder({"label", "url"})
public class Source {

  @JsonProperty("label")
  private String label;

  @JsonProperty("url")
  private String url;

  /** @return the label */
  @JsonProperty("label")
  public String getLabel() {
    return label;
  }

  /** @param label the label to set */
  @JsonProperty("label")
  public void setLabel(String label) {
    this.label = label;
  }

  /** @return the url */
  @JsonProperty("url")
  public String getUrl() {
    return url;
  }

  /** @param url the url to set */
  @JsonProperty("url")
  public void setUrl(String url) {
    this.url = url;
  }
}
