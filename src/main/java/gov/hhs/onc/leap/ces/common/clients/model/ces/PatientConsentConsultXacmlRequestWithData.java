package gov.hhs.onc.leap.ces.common.clients.model.ces;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.XacmlRequest;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"xacmlRequest", "payload"})
public class PatientConsentConsultXacmlRequestWithData {

    @JsonProperty("xacmlRequest")
    private XacmlRequest xacmlRequest;

    @JsonProperty("payload")
    private String payload;

    @JsonProperty("xacmlRequest")
    public XacmlRequest getXacmlRequest() {
        return xacmlRequest;
    }

    @JsonProperty("xacmlRequest")
    public void setXacmlRequest(XacmlRequest xacmlRequest) {
        this.xacmlRequest = xacmlRequest;
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
