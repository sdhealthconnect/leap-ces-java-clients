package gov.hhs.onc.leap.ces.common.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * A JWT Auth Helper to manipulate tokens.
 * The Helper was created to:
 * A) verify and decode a Token that exists in the Http Request
 * B) get the token from the request
 * C) Create JWT tokens (not yet implemented)
 *
 * To create valid keys on *nix we can use commands presented below:
 * openssl req -x509 -days 1000 -new -key private.pem -out public.pem
 * openssl genrsa 2048 > private.pem
 *
 * @author: sgroh@saperi.io
 */
public class JWTAuthHelper {
    private final static Logger LOG = LoggerFactory.getLogger(JWTAuthHelper.class);
    private static final String PKCS_1_PEM_HEADER = "-----BEGIN RSA PRIVATE KEY-----";
    private static final String PKCS_1_PEM_FOOTER = "-----END RSA PRIVATE KEY-----";
    private static final String PKCS_8_PEM_HEADER = "-----BEGIN PRIVATE KEY-----";
    private static final String PKCS_8_PEM_FOOTER = "-----END PRIVATE KEY-----";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public JWTAuthHelper(final String spublicKey, final String sprivateKey) {
        CertificateFactory fact = null;
        X509Certificate cer = null;
        try {
            fact = CertificateFactory.getInstance("X.509");
            cer = (X509Certificate) fact.generateCertificate(IOUtils.toInputStream(spublicKey, "UTF-8"));
        } catch (CertificateException | IOException e) {
            LOG.error("Unable to create JWTAuhtHelper", e);
        }
        this.publicKey = cer.getPublicKey();
        if (sprivateKey == null) {
            LOG.warn("Private Key was not specified");
        } else {
            try {
                this.privateKey = readPrivateKey(sprivateKey);
            } catch (Exception e) {
                LOG.error("Unable to create JWTAuhtHelper", e);
            }
        }
    }

    /**
     * Process a private key from the configuration file and return a {@link PrivateKey} object.
     *
     * @param privateKey the string content of the private key
     * @return a {@link PrivateKey} object.
     * @throws Exception if the key can not be loaded.
     */
    private PrivateKey readPrivateKey(String privateKey) throws Exception {

        if (privateKey.contains(PKCS_1_PEM_HEADER)) {
            // OpenSSL / PKCS#1 Base64 PEM encoded file
            privateKey = privateKey.replace(PKCS_1_PEM_HEADER, "");
            privateKey = privateKey.replace(PKCS_1_PEM_FOOTER, "");
            privateKey = privateKey.replace(" ", "");
            return readPkcs1PrivateKey(Base64.getDecoder().decode(privateKey));
        }

        if (privateKey.contains(PKCS_8_PEM_HEADER)) {
            // PKCS#8 Base64 PEM encoded file
            privateKey = privateKey.replace(PKCS_8_PEM_HEADER, "");
            privateKey = privateKey.replace(PKCS_8_PEM_FOOTER, "");
            return readPkcs8PrivateKey(Base64.getDecoder().decode(privateKey));
        }
        else return null;
    }

    private PrivateKey readPkcs1PrivateKey(byte[] pkcs1Bytes) throws GeneralSecurityException {
        // We can't use Java internal APIs to parse ASN.1 structures, so we build a PKCS#8 key Java can understand
        int pkcs1Length = pkcs1Bytes.length;
        int totalLength = pkcs1Length + 22;
        byte[] pkcs8Header = new byte[] {
                0x30, (byte) 0x82, (byte) ((totalLength >> 8) & 0xff), (byte) (totalLength & 0xff), // Sequence + total length
                0x2, 0x1, 0x0, // Integer (0)
                0x30, 0xD, 0x6, 0x9, 0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0xD, 0x1, 0x1, 0x1, 0x5, 0x0, // Sequence: 1.2.840.113549.1.1.1, NULL
                0x4, (byte) 0x82, (byte) ((pkcs1Length >> 8) & 0xff), (byte) (pkcs1Length & 0xff) // Octet string + length
        };
        byte[] pkcs8bytes = join(pkcs8Header, pkcs1Bytes);
        return readPkcs8PrivateKey(pkcs8bytes);
    }

    private static PrivateKey readPkcs8PrivateKey(byte[] pkcs8Bytes) throws GeneralSecurityException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA", "SunRsaSign");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pkcs8Bytes);
        try {
            return keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            throw new IllegalArgumentException("Unexpected key format!", e);
        }
    }

    private static byte[] join(byte[] byteArray1, byte[] byteArray2){
        byte[] bytes = new byte[byteArray1.length + byteArray2.length];
        System.arraycopy(byteArray1, 0, bytes, 0, byteArray1.length);
        System.arraycopy(byteArray2, 0, bytes, byteArray1.length, byteArray2.length);
        return bytes;
    }

    public String getToken(final HttpServletRequest request) {
        String token = request.getHeader(HEADER_AUTHORIZATION);
        try {
            return token.strip().split(" ")[1];
        } catch (Exception e) {
            LOG.error("The Bearer token is not present on headers", e);
        }
        return null;
    }

    public Claims verifyAndDecodeToken(HttpServletRequest request) {
        if (request==null){
            LOG.error("The Request can not be null");
            throw new IllegalArgumentException("The request can not be null to verify a JWT token");
        }
        if (publicKey==null){
            LOG.error("The JWTAuthHelper does not contains a publicKey set");
            throw new IllegalArgumentException("JWTAuthHelper should be created with some publicKey");
        }
        String token = getToken(request);
        try {
            // obtain an attribute from claims using  claims.get("attr-name")
            return Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(token).getBody();

        } catch (Exception e) {
            throw new JwtException("The token could not be verified nor decoded", e);
        }
    }
}
