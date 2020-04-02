package gov.hhs.onc.leap.ces.common.clients.model.xacml;

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
}
