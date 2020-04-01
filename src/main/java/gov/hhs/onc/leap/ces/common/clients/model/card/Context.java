package gov.hhs.onc.leap.ces.common.clients.model.card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

/** Context where the consent decision is needed */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"actor", "purposeOfUse", "scope", "patientId"})
public class Context {

  /** identifiers of the actor */
  @JsonProperty("actor")
  @JsonPropertyDescription("identifiers of the actor")
  private List<Actor> actor = null;
  /** purpose of use */
  @JsonProperty("purposeOfUse")
  @JsonPropertyDescription("purpose of use")
  private Context.PurposeOfUse purposeOfUse;
  /** consent scope (Required) */
  @JsonProperty("scope")
  @JsonPropertyDescription("consent scope")
  private Context.Scope scope;
  /** identity of the patient whose consent is being considered (Required) */
  @JsonProperty("patientId")
  @JsonPropertyDescription("identity of the patient whose consent is being considered")
  private List<PatientId> patientId = null;

  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  /** identifiers of the actor */
  @JsonProperty("actor")
  public List<Actor> getActor() {
    return actor;
  }

  /** identifiers of the actor */
  @JsonProperty("actor")
  public void setActor(List<Actor> actor) {
    this.actor = actor;
  }

  /** purpose of use */
  @JsonProperty("purposeOfUse")
  public Context.PurposeOfUse getPurposeOfUse() {
    return purposeOfUse;
  }

  /** purpose of use */
  @JsonProperty("purposeOfUse")
  public void setPurposeOfUse(Context.PurposeOfUse purposeOfUse) {
    this.purposeOfUse = purposeOfUse;
  }

  /** consent scope (Required) */
  @JsonProperty("scope")
  public Context.Scope getScope() {
    return scope;
  }

  /** consent scope (Required) */
  @JsonProperty("scope")
  public void setScope(Context.Scope scope) {
    this.scope = scope;
  }

  /** identity of the patient whose consent is being considered (Required) */
  @JsonProperty("patientId")
  public List<PatientId> getPatientId() {
    return patientId;
  }

  /** identity of the patient whose consent is being considered (Required) */
  @JsonProperty("patientId")
  public void setPatientId(List<PatientId> patientId) {
    this.patientId = patientId;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  public enum PurposeOfUse {
    HMARKT("HMARKT"),
    HOPERAT("HOPERAT"),
    HPAYMT("HPAYMT"),
    HRESCH("HRESCH"),
    PATRQT("PATRQT"),
    TREAT("TREAT"),
    PUBHLTH("PUBHLTH");

    private final String value;
    private static final Map<String, Context.PurposeOfUse> CONSTANTS =
        new HashMap<String, Context.PurposeOfUse>();

    static {
      for (Context.PurposeOfUse c : values()) {
        CONSTANTS.put(c.value, c);
      }
    }

    private PurposeOfUse(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }

    @JsonValue
    public String value() {
      return this.value;
    }

    @JsonCreator
    public static Context.PurposeOfUse fromValue(String value) {
      Context.PurposeOfUse constant = CONSTANTS.get(value);
      if (constant == null) {
        throw new IllegalArgumentException(value);
      } else {
        return constant;
      }
    }
  }

  public enum Scope {
    ADR("adr"),
    RESEARCH("research"),
    PATIENT_PRIVACY("patient-privacy"),
    TREATMENT("treatment");

    private final String value;
    private static final Map<String, Context.Scope> CONSTANTS =
        new HashMap<String, Context.Scope>();

    static {
      for (Context.Scope c : values()) {
        CONSTANTS.put(c.value, c);
      }
    }

    private Scope(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }

    @JsonValue
    public String value() {
      return this.value;
    }

    @JsonCreator
    public static Context.Scope fromValue(String value) {
      Context.Scope constant = CONSTANTS.get(value);
      if (constant == null) {
        throw new IllegalArgumentException(value);
      } else {
        return constant;
      }
    }
  }
}
