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

To see an example of how to construct a `PatientConsentConsultHookRequest` see [this test](https://github.com/sdhealthconnect/ces-common-clients/blob/master/src/test/java/gov/hhs/onc/leap/ces/common/client/card/tests/ConsentConsultClientCardTests.java). 

To see an example of the JSON string for the CDS Hooks requst check out the [LEAP CDS documentation](https://github.com/sdhealthconnect/leap-cds/blob/master/README.md).

### XACML Client
The XACML Client can be instantiated by giving the host name where the Consent Decision Service resides:
```java
import gov.hhs.onc.leap.ces.common.clients.xacml.ConsentConsultXacmlClient;

ConsentConsultXacmlClient client = new ConsentConsultXacmlClient(host);
```

The `getConsentDecision()` method can be called to get a consent decision from the Consent Decision Service. This method can take either be a JSON string containing the request or a `XacmlRequest` which encodes the request as a POJO and returns a `XacmlResponse` object.

To see an example of how to construct an `XacmlRequest` see [this test](https://github.com/sdhealthconnect/ces-common-clients/blob/master/src/test/java/gov/hhs/onc/leap/ces/common/client/xacml/tests/ConsentConsultClientXacmlTests.java). 

To see an example of the JSON string for the XACML requst check out the [LEAP CDS documentation](https://github.com/sdhealthconnect/leap-cds/blob/master/README.md).

## Development
### Code Linter
You can run the linter using maven:
```
mvn googleformatter:format
```

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
