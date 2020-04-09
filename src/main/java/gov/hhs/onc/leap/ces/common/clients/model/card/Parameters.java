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
@JsonPropertyOrder({"codes", "exceptAnyOfCodes"})
public class Parameters {

  @JsonProperty("codes")
  private List<Codes> codes;

  @JsonProperty("exceptAnyOfCodes")
  private List<Codes> exceptAnyOfCodes;

  /** @return the codes */
  @JsonProperty("codes")
  public List<Codes> getCodes() {
    return codes;
  }

  /** @param codes the codes to set */
  @JsonProperty("codes")
  public void setCodes(List<Codes> codes) {
    this.codes = codes;
  }

  /** @return the codes */
  @JsonProperty("exceptAnyOfCodes")
  public List<Codes> getExceptAnyOfCodes() {
    return exceptAnyOfCodes;
  }

  /** @param codes the codes to set */
  @JsonProperty("exceptAnyOfCodes")
  public void setExceptAnyOfCodes(List<Codes> exceptAnyOfCodes) {
    this.exceptAnyOfCodes = exceptAnyOfCodes;
  }
}
