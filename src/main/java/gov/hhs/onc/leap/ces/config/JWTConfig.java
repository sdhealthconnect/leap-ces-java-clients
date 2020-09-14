package gov.hhs.onc.leap.ces.config;

import gov.hhs.onc.leap.ces.common.helper.JWTAuthHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * A JWT Configuration class.
 * This was created to inject easily private and public keys on the {@link JWTAuthHelper} class.
 *
 * This config requires a yml file with this content:
 * ces:
 *     public-key: "-----BEGIN CERTIFICATE----- YOUR PUBLIC KEY HERE -----END CERTIFICATE-----"
 *     private-key: "-----BEGIN RSA PRIVATE KEY----- YOUR PRIVATE KEY HERE -----END RSA PRIVATE KEY-----"
 *
 * @author: sgroh@saperi.io
 */
public class JWTConfig {
    private final static Logger LOG = LoggerFactory.getLogger(JWTConfig.class);

    @Value("${ces.public-key:}")
    private String publicKey;

    @Value("${ces.private-key:}")
    private String privateKey;

    @Bean
    /**
     * Create a JWTAuthHelper to manipulate JWT tokens.
     */
    JWTAuthHelper jwtAuthHelper() {
        try {
            JWTAuthHelper jwtAuthHelper = new JWTAuthHelper(publicKey, privateKey);
            return jwtAuthHelper;
        } catch (Exception e) {
            LOG.error("Error creating bean JWTAuthHelper", e);
        }
        return null;
    }
}
