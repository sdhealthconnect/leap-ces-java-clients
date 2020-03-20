/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.onc.leap.ces.common.clients.model.xacml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

/**
 *
 * @author duanedecouteau
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Attribute"
})
public class AccessSubject {
    
    @JsonProperty("Attribute")
    private List<Attribute> attribute;

    /**
     * @return the attribute
     */
    @JsonProperty("Attribute")
    public List<Attribute> getAttribute() {
        return attribute;
    }

    /**
     * @param attribute the attribute to set
     */
    @JsonProperty("Attribute")
    public void setAttribute(List<Attribute> attribute) {
        this.attribute = attribute;
    }
}
