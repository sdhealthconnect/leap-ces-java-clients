/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.onc.leap.ces.common.clients.model.xacml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duanedecouteau
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "AttributeId", "Value" })
public class Attribute {

    @JsonProperty("AttributeId")
    private String attributeId;
    @JsonProperty("Value")
    private List<SystemValue> value = new ArrayList<SystemValue>();

    /**
     * @return the attributeId
     */
    @JsonProperty("AttributeId")
    public String getAttributeId() {
        return attributeId;
    }

    /**
     * @param attributeId
     *            the attributeId to set
     */
    @JsonProperty("AttributeId")
    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
    }

    /**
     * @return the value
     */
    @JsonProperty("Value")
    public List<SystemValue> getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    @JsonProperty("Value")
    public void setValue(List<SystemValue> value) {
        this.value = value;
    }

}
