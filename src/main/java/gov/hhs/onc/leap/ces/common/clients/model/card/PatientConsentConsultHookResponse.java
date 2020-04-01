package gov.hhs.onc.leap.ces.common.clients.model.card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Patient Consent Consult Hook Response
 *
 * <p>Patient Consent Consult Hook Response
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"cards"})
public class PatientConsentConsultHookResponse {

  /** (Required) */
  @JsonProperty("cards")
  private List<Card> cards = null;

  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  /** (Required) */
  @JsonProperty("cards")
  public List<Card> getCards() {
    return cards;
  }

  /** (Required) */
  @JsonProperty("cards")
  public void setCards(List<Card> cards) {
    this.cards = cards;
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
