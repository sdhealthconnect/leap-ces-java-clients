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

/**
 *
 * @author duanedecouteau
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Id",
    "AttributeAssignment"
})
public class Obligations {
    
    @JsonProperty("Id")
    private ObligationId obligationId;
    
    @JsonProperty("AttributeAssignent")
    private List<AttributeAssignment> attributeAssignments = new ArrayList<AttributeAssignment>();

    /**
     * @return the obligationId
     */
    @JsonProperty("Id")
    public ObligationId getObligationId() {
        return obligationId;
    }

    /**
     * @param obligationId the obligationId to set
     */
    @JsonProperty("Id")
    public void setObligationId(ObligationId obligationId) {
        this.obligationId = obligationId;
    }

    /**
     * @return the attributeAssignments
     */
    @JsonProperty("AttributeAssignment")
    public List<AttributeAssignment> getAttributeAssignments() {
        return attributeAssignments;
    }

    /**
     * @param attributeAssignments the attributeAssignments to set
     */
    @JsonProperty("AttributeAssignment")
    public void setAttributeAssignments(List<AttributeAssignment> attributeAssignments) {
        this.attributeAssignments = attributeAssignments;
    }
}
