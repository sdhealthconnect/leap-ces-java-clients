/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.onc.leap.ces.common.clients.model.xacml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author duanedecouteau
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Decision", "Obligations" })
public class Response {

    @JsonProperty("Decision")
    private String decision;
    @JsonProperty("Obligations")
    private List<Obligations> obligations;

    /**
     * @return the decision
     */
    @JsonProperty("Decision")
    public String getDecision() {
        return decision;
    }

    /**
     * @param decision
     *            the decision to set
     */
    @JsonProperty("Decision")
    public void setDecision(String decision) {
        this.decision = decision;
    }

    /**
     * @return the obligations
     */
    @JsonProperty("Obligations")
    public List<Obligations> getObligations() {
        return obligations;
    }

    /**
     * @param obligations
     *            the obligations to set
     */
    @JsonProperty("Obligations")
    public void setObligations(List<Obligations> obligations) {
        this.obligations = obligations;
    }
}
