package gov.hhs.onc.leap.ces.common.clients.model.ces;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import gov.hhs.onc.leap.ces.common.clients.model.card.PatientConsentConsultHookRequest;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"hookRequest", "payload"})
public class PatientConsentConsultHookRequestWithData {

    @JsonProperty("hookRequest")
    private PatientConsentConsultHookRequest hookRequest;
    @JsonProperty("payload")
    private String payload;

    @JsonProperty("hookRequest")
    public PatientConsentConsultHookRequest getHookRequest() {
        return hookRequest;
    }

    @JsonProperty("hookRequest")
    public void setHookRequest(PatientConsentConsultHookRequest hookRequest) {
        this.hookRequest = hookRequest;
    }

    @JsonProperty("payload")
    public String getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public void setPayload(String payload) {
        this.payload = payload;
    }
}
