package gov.hhs.onc.leap.ces.common.client.model;

import org.junit.Test;

import gov.hhs.onc.leap.ces.common.clients.model.card.PatientConsentConsultHookResponse;
import gov.hhs.onc.leap.ces.common.clients.model.xacml.XacmlResponse;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ModelTests {

    @Test
    public void cardResponseParse() throws Exception {

        String responseJSON = new String(Files.readAllBytes(Paths.get(
                "src/test/java/gov/hhs/onc/leap/ces/common/client/model/fixtures/sample-card-response-with-codes.json")),
                "UTF-8");
        PatientConsentConsultHookResponse response = new ObjectMapper().readValue(responseJSON,
                PatientConsentConsultHookResponse.class);

        assert (response.getCards().get(0) != null);
        assert (response.getCards().get(0).getExtension() != null);
        assert ("R".equals(response.getCards().get(0).getExtension().getObligations().get(0).getParameters().getCodes()
                .get(0).getCode()));

        responseJSON = new String(Files.readAllBytes(Paths.get(
                "src/test/java/gov/hhs/onc/leap/ces/common/client/model/fixtures/sample-card-response-with-except-any-of-codes.json")),
                "UTF-8");

        response = new ObjectMapper().readValue(responseJSON, PatientConsentConsultHookResponse.class);

        assert (response.getCards().size() > 0);
        assert (response.getCards().get(0).getExtension() != null);
        assert ("N".equals(response.getCards().get(0).getExtension().getObligations().get(0).getParameters()
                .getExceptAnyOfCodes().get(0).getCode()));
    }

    @Test
    public void xacmlResponseParse() throws Exception {
        String responseJSON = new String(Files.readAllBytes(Paths.get(
                "src/test/java/gov/hhs/onc/leap/ces/common/client/model/fixtures/sample-xacml-response-with-codes.json")),
                "UTF-8");
        XacmlResponse response = new ObjectMapper().readValue(responseJSON, XacmlResponse.class);

        assert (response.getResponse().size() > 0);
        assert ("R".equals(response.getResponse().get(0).getObligations().get(0).getAttributeAssignments().get(0)
                .getSystemCodes().get(0).getCode()));

        responseJSON = new String(Files.readAllBytes(Paths.get(
                "src/test/java/gov/hhs/onc/leap/ces/common/client/model/fixtures/sample-xacml-response-with-except-any-of-codes.json")),
                "UTF-8");
        response = new ObjectMapper().readValue(responseJSON, XacmlResponse.class);

        assert (response.getResponse().size() > 0);
        assert ("N".equals(response.getResponse().get(0).getObligations().get(0).getAttributeAssignments().get(0)
                .getSystemCodes().get(0).getCode()));

    }
}
