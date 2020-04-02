package gov.hhs.onc.leap.ces.common.clients.model.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"system", "value"})
public class Actor {

  /** (Required) */
  @JsonProperty("system")
  private String system;
  /** (Required) */
  @JsonProperty("value")
  private String value;

  /** (Required) */
  @JsonProperty("system")
  public String getSystem() {
    return system;
  }

  /** (Required) */
  @JsonProperty("system")
  public Actor setSystem(String system) {
    this.system = system;
    return this;
  }

  /** (Required) */
  @JsonProperty("value")
  public String getValue() {
    return value;
  }

  /** (Required) */
  @JsonProperty("value")
  public Actor setValue(String value) {
    this.value = value;
    return this;
  }
}
