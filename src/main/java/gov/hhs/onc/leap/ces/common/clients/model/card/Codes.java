/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.onc.leap.ces.common.clients.model.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonProperty;

/** @author duanedecouteau */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"system", "code"})
public class Codes {

  @JsonProperty("system")
  private String system;

  @JsonProperty("code")
  private String code;

  /** @return the system */
  @JsonProperty("system")
  public String getSystem() {
    return system;
  }

  /** @param system the system to set */
  @JsonProperty("system")
  public void setSystem(String system) {
    this.system = system;
  }

  /** @return the code */
  @JsonProperty("code")
  public String getCode() {
    return code;
  }

  /** @param code the code to set */
  @JsonProperty("code")
  public void setCode(String code) {
    this.code = code;
  }
}
