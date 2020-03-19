package gov.hhs.onc.leap.ces.common.clients.model.xacml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
* Patient Consent Consult XACML Response
* <p>
* Patient Consent Consult XACML Response
*
*/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"Response"
})
public class XacmlResponse {

    /**
    *
    * (Required)
    *
    */
    @JsonProperty("Response")
    private List<Object> response = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
    *
    * (Required)
    *
    */
    @JsonProperty("Response")
    public List<Object> getResponse() {
        return response;
    }

    /**
    *
    * (Required)
    *
    */
    @JsonProperty("Response")
    public void setResponse(List<Object> response) {
        this.response = response;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}