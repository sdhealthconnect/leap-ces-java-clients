package gov.hhs.onc.leap.ces.common.clients.model.xacml;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Patient Consent Consult XACML Request
 *
 * <p>Patient Consent Consult XACML Request
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"Request"})
public class XacmlRequest {

  /** (Required) */
  @JsonProperty("Request")
  private Request request;

  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  /** (Required) */
  @JsonProperty("Request")
  public Request getRequest() {
    return request;
  }

  /** (Required) */
  @JsonProperty("Request")
  public void setRequest(Request request) {
    this.request = request;
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
