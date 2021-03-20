# LEAP Consent Enforcement Service-Java Client
This library is a Java client for the [LEAP Consent Decision Service](https://github.com/sdhealthconnect/leap-cds).

## Use
### CDS Hooks Client
The CDS Hook Client can be instantiated by giving the host name where the Consent Decision Service resides:
```java
import gov.hhs.onc.leap.ces.common.clients.card.ConsentConsultCardClient;

ConsentConsultCardClient client = new ConsentConsultCardClient(host);
```

The `getConsentDecision()` method can be called to get a consent decision from the Consent Decision Service. This method can take either be a JSON string containing the request or a `PatientConsentConsultHookRequest` which encodes the request as a POJO and returns a `PatientConsentConsultHookResponse` object.

To see an example of how to construct a `PatientConsentConsultHookRequest` see the following example ([from this test](https://github.com/sdhealthconnect/ces-common-clients/blob/master/src/test/java/gov/hhs/onc/leap/ces/common/client/card/tests/ConsentConsultClientCardTests.java)).
```java
PatientId patient =
    new PatientId()
        .setSystem("http://hl7.org/fhir/sid/us-ssn")
        .setValue("111111111");

Actor actor = 
    new Actor()
        .setSystem("urn:ietf:rfc:3986")
        .setValue("2.16.840.1.113883.20.5");

Context context =
    new Context()
        .setPatientId(Arrays.asList(patient))
        .setPurposeOfUse(PurposeOfUse.TREAT)
        .setScope(Context.Scope.PATIENT_PRIVACY)
        .setActor(Arrays.asList(actor));

PatientConsentConsultHookRequest request =
    new PatientConsentConsultHookRequest()
        .setContext(context)
        .setHook("patient-consent-consult")
        .setHookInstance("123456");

PatientConsentConsultHookResponse res = client.getConsentDecision(request);
``` 

Note that for convenience, purpose of use is defined as an enum with the following values: `HMARKT`, `HOPERAT`, `HPAYMT`, `HRESCH`, `PATRQT`, `TREAT`, `ETREAT`, and `PUBHLTH`.


To see an example of the JSON string for the CDS Hooks requst check out the [LEAP CDS documentation](https://github.com/sdhealthconnect/leap-cds/blob/master/README.md).

### XACML Client
The XACML Client can be instantiated by giving the host name where the Consent Decision Service resides:
```java
import gov.hhs.onc.leap.ces.common.clients.xacml.ConsentConsultXacmlClient;

ConsentConsultXacmlClient client = new ConsentConsultXacmlClient(host);
```

The `getConsentDecision()` method can be called to get a consent decision from the Consent Decision Service. This method can take either be a JSON string containing the request or a `XacmlRequest` which encodes the request as a POJO and returns a `XacmlResponse` object.

To see an example of how to construct an `XacmlRequest` see [this test](https://github.com/sdhealthconnect/ces-common-clients/blob/master/src/test/java/gov/hhs/onc/leap/ces/common/client/xacml/tests/ConsentConsultClientXacmlTests.java). A simpler way to construct an XACML request is to use the `toXacmlRequest()` method of the generic request class `CESRequest` which provides a simpler interface to construct a request:

```java
CESRequest request =
    new CESRequest()
        .setScope("patient-privacy")
        .setPurposeOfUse("TREAT")
        .setPatientId(
            Arrays.asList(
                new CESRequest.SystemValuePair("http://hl7.org/fhir/sid/us-ssn", "111111111")))
        .setActor(
            Arrays.asList(
                new CESRequest.SystemValuePair("urn:ietf:rfc:3986", "2.16.840.1.113883.20.5")));

XacmlResponse xacmlResponse = client.getConsentDecision(request.toXacmlRequest());
```

To see an example of the JSON string for the XACML requst check out the [LEAP CDS documentation](https://github.com/sdhealthconnect/leap-cds/blob/master/README.md).

## Setup
### Build
You can build the project using maven:
```
mvn clean install
```
### Test
You can run the tests using maven:
```
mvn test
```
