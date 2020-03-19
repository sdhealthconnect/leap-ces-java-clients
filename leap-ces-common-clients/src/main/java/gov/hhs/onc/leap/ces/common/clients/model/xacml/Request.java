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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"AccessSubject",
"Action",
"Resource"
})
public class Request {

    /**
    *
    * (Required)
    *
    */
    @JsonProperty("AccessSubject")
    private List<Object> accessSubject = null;
    /**
    *
    * (Required)
    *
    */
    @JsonProperty("Action")
    private List<Object> action = null;
    /**
    *
    * (Required)
    *
    */
    @JsonProperty("Resource")
    private List<Object> resource = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
    *
    * (Required)
    *
    */
    @JsonProperty("AccessSubject")
    public List<Object> getAccessSubject() {
        return accessSubject;
    }

    /**
    *
    * (Required)
    *
    */
    @JsonProperty("AccessSubject")
    public void setAccessSubject(List<Object> accessSubject) {
        this.accessSubject = accessSubject;
    }

    /**
    *
    * (Required)
    *
    */
    @JsonProperty("Action")
    public List<Object> getAction() {
        return action;
    }

    /**
    *
    * (Required)
    *
    */
    @JsonProperty("Action")
    public void setAction(List<Object> action) {
        this.action = action;
    }

    /**
    *
    * (Required)
    *
    */
    @JsonProperty("Resource")
    public List<Object> getResource() {
        return resource;
    }

    /**
    *
    * (Required)
    *
    */
    @JsonProperty("Resource")
    public void setResource(List<Object> resource) {
        this.resource = resource;
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