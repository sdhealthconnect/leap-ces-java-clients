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
@JsonPropertyOrder({"summary", "detail", "indicator", "source", "extension"})
public class Card {

  @JsonProperty("summary")
  private String summary;

  @JsonProperty("detail")
  private String detail;

  @JsonProperty("indicator")
  private String indicator;

  @JsonProperty("source")
  private Source source;

  @JsonProperty("extension")
  private Extension extension;

  /** @return the summary */
  @JsonProperty("summary")
  public String getSummary() {
    return summary;
  }

  /** @param summary the summary to set */
  @JsonProperty("summary")
  public void setSummary(String summary) {
    this.summary = summary;
  }

  /** @return the detail */
  @JsonProperty("detail")
  public String getDetail() {
    return detail;
  }

  /** @param detail the detail to set */
  @JsonProperty("detail")
  public void setDetail(String detail) {
    this.detail = detail;
  }

  /** @return the indicator */
  @JsonProperty("indicator")
  public String getIndicator() {
    return indicator;
  }

  /** @param indicator the indicator to set */
  @JsonProperty("indicator")
  public void setIndicator(String indicator) {
    this.indicator = indicator;
  }

  /** @return the source */
  @JsonProperty("source")
  public Source getSource() {
    return source;
  }

  /** @param source the source to set */
  @JsonProperty("source")
  public void setSource(Source source) {
    this.source = source;
  }

  /** @return the extension */
  @JsonProperty("extension")
  public Extension getExtension() {
    return extension;
  }

  /** @param extension the extension to set */
  @JsonProperty("extension")
  public void setExtension(Extension extension) {
    this.extension = extension;
  }
}
