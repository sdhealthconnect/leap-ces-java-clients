package gov.hhs.onc.leap.ces.common.clients.model.card;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Patient Consent Consult Hook Request
 *
 * <p>Patient Consent Consult Hook Request
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"hook", "hookInstance", "context"})
public class PatientConsentConsultHookRequest {

  /** (Required) */
  @JsonProperty("hook")
  private String hook;
  /** UUID for this hook call (Required) */
  @JsonProperty("hookInstance")
  @JsonPropertyDescription("UUID for this hook call")
  private String hookInstance;
  /** Context where the consent decision is needed (Required) */
  @JsonProperty("context")
  @JsonPropertyDescription("Context where the consent decision is needed")
  private Context context;

  /** (Required) */
  @JsonProperty("hook")
  public String getHook() {
    return hook;
  }

  /** (Required) */
  @JsonProperty("hook")
  public PatientConsentConsultHookRequest setHook(String hook) {
    this.hook = hook;
    return this;
  }

  /** UUID for this hook call (Required) */
  @JsonProperty("hookInstance")
  public String getHookInstance() {
    return hookInstance;
  }

  /** UUID for this hook call (Required) */
  @JsonProperty("hookInstance")
  public PatientConsentConsultHookRequest setHookInstance(String hookInstance) {
    this.hookInstance = hookInstance;
    return this;
  }

  /** Context where the consent decision is needed (Required) */
  @JsonProperty("context")
  public Context getContext() {
    return context;
  }

  /** Context where the consent decision is needed (Required) */
  @JsonProperty("context")
  public PatientConsentConsultHookRequest setContext(Context context) {
    this.context = context;
    return this;
  }
}
