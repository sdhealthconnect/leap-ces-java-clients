package gov.hhs.onc.leap.ces.common.clients.model.card;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Patient Consent Consult Hook Request
 * <p>
 * Patient Consent Consult Hook Request
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "hook", "hookInstance", "context" })
public class PatientConsentConsultHookRequest {

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("hook")
    private String hook;
    /**
     * UUID for this hook call (Required)
     *
     */
    @JsonProperty("hookInstance")
    @JsonPropertyDescription("UUID for this hook call")
    private String hookInstance;
    /**
     * Context where the consent decision is needed (Required)
     *
     */
    @JsonProperty("context")
    @JsonPropertyDescription("Context where the consent decision is needed")
    private Context context;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("hook")
    public String getHook() {
        return hook;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("hook")
    public void setHook(String hook) {
        this.hook = hook;
    }

    /**
     * UUID for this hook call (Required)
     *
     */
    @JsonProperty("hookInstance")
    public String getHookInstance() {
        return hookInstance;
    }

    /**
     * UUID for this hook call (Required)
     *
     */
    @JsonProperty("hookInstance")
    public void setHookInstance(String hookInstance) {
        this.hookInstance = hookInstance;
    }

    /**
     * Context where the consent decision is needed (Required)
     *
     */
    @JsonProperty("context")
    public Context getContext() {
        return context;
    }

    /**
     * Context where the consent decision is needed (Required)
     *
     */
    @JsonProperty("context")
    public void setContext(Context context) {
        this.context = context;
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
