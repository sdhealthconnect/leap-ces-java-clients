package gov.hhs.onc.leap.ces.common.clients.model.card;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"system",
"value"
})
public class Actor {

    /**
    *
    * (Required)
    *
    */
    @JsonProperty("system")
    private String system;
    /**
    *
    * (Required)
    *
    */
    @JsonProperty("value")
    private String value;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
    *
    * (Required)
    *
    */
    @JsonProperty("system")
    public String getSystem() {
        return system;
    }

    /**
    *
    * (Required)
    *
    */
    @JsonProperty("system")
    public void setSystem(String system) {
        this.system = system;
    }

    /**
    *
    * (Required)
    *
    */
    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    /**
    *
    * (Required)
    *
    */
    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
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