/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.onc.leap.ces.common.clients.model.xacml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;

/** @author duanedecouteau */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"AttributeId", "Value"})
public class AttributeAssignment {

  @JsonProperty("AttributeId")
  private String attributeId;

  @JsonProperty("Value")
  private List<SystemCode> systemCodes = new ArrayList<SystemCode>();

  /** @return the attributeId */
  @JsonProperty("AttributeId")
  public String getAttributeId() {
    return attributeId;
  }

  /** @param attributeId the attributeId to set */
  @JsonProperty("AttributeId")
  public void setAttributeId(String attributeId) {
    this.attributeId = attributeId;
  }

  /** @return the systemCodes */
  @JsonProperty("Value")
  public List<SystemCode> getSystemCodes() {
    return systemCodes;
  }

  /** @param systemCodes the systemCodes to set */
  @JsonProperty("Value")
  public void setSystemCodes(List<SystemCode> systemCodes) {
    this.systemCodes = systemCodes;
  }
}
