package gov.hhs.onc.leap.ces.common.helper;

import gov.hhs.onc.leap.ces.config.JWTConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

import static gov.hhs.onc.leap.ces.common.helper.JWTAuthHelper.HEADER_AUTHORIZATION;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JWTConfig.class)
@PropertySource("classpath:application.yml")
public class JWTAuthHelperTest {

    @Autowired
    private JWTAuthHelper jwtAuthHelper;
    private String token = "eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCJ9.eyJhY3RvciI6eyJzeXN0ZW0iOiJ1cm46aWV0ZjpyZmM6Mzk4NiIsInZhbHVlIjoidXJuOm9pZDoxLjEifSwicG91IjoiVFJFQVQiLCJpYXQiOjE1OTg1NjIzMjF9.NXaOuOhyX0YHqqX97kgCztYGYHJ3gsNF1gMk9AMSSoi9cMnurZCbNwGswr5OG5AdYKmeQmN5LvSyqY-giZN2Eyj2E5PQqerIVrm7W1VAl7JtUghvpukepPQ6nyO4orzZXDdsCfy8XFEbhLzlEQk0w4CRiRcYOtGK4-xDQHn0ALxYYAdxZicJCCMsQlJkDgRDOMi4bC4OAcnR7D-zbSDt1hoVk4NRMqSyIlFC39XucrsZZ5iOsdU5GbQpmOKbjeUAr42Y20jT-leUFw7CY17TY3CjSwyiTbvLeqVmoiihvEu7rxu3Sw52nfd80qN2YDZbi-7_aVDV8OlUutGZIKmvMg";

    @Test
    public void getToken() {
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        when(httpServletRequest.getHeader(HEADER_AUTHORIZATION)).thenReturn("Bearer " + token);
        assertEquals(jwtAuthHelper.getToken(httpServletRequest), token);
    }

    @Test
    public void verifyAndDecodeToken() {
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        when(httpServletRequest.getHeader(HEADER_AUTHORIZATION)).thenReturn("Bearer " + token);
        JWTAuthHelper jwtHelper = new JWTAuthHelper("-----BEGIN CERTIFICATE-----\nMIIDtTCCAp2gAwIBAgIJAMKR/NsyfcazMA0GCSqGSIb3DQEBBQUAMEUxCzAJBgNV\nBAYTAkFVMRMwEQYDVQQIEwpTb21lLVN0YXRlMSEwHwYDVQQKExhJbnRlcm5ldCBX\naWRnaXRzIFB0eSBMdGQwHhcNMTIxMTEyMjM0MzQxWhcNMTYxMjIxMjM0MzQxWjBF\nMQswCQYDVQQGEwJBVTETMBEGA1UECBMKU29tZS1TdGF0ZTEhMB8GA1UEChMYSW50\nZXJuZXQgV2lkZ2l0cyBQdHkgTHRkMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIB\nCgKCAQEAvtH4wKLYlIXZlfYQFJtXZVC3fD8XMarzwvb/fHUyJ6NvNStN+H7GHp3/\nQhZbSaRyqK5hu5xXtFLgnI0QG8oE1NlXbczjH45LeHWhPIdc2uHSpzXic78kOugM\nY1vng4J10PF6+T2FNaiv0iXeIQq9xbwwPYpflViQyJnzGCIZ7VGan6GbRKzyTKcB\n58yx24pJq+CviLXEY52TIW1l5imcjGvLtlCp1za9qBZa4XGoVqHi1kRXkdDSHty6\nlZWj3KxoRvTbiaBCH+75U7rifS6fR9lqjWE57bCGoz7+BBu9YmPKtI1KkyHFqWpx\naJc/AKf9xgg+UumeqVcirUmAsHJrMwIDAQABo4GnMIGkMB0GA1UdDgQWBBTs83nk\nLtoXFlmBUts3EIxcVvkvcjB1BgNVHSMEbjBsgBTs83nkLtoXFlmBUts3EIxcVvkv\ncqFJpEcwRTELMAkGA1UEBhMCQVUxEzARBgNVBAgTClNvbWUtU3RhdGUxITAfBgNV\nBAoTGEludGVybmV0IFdpZGdpdHMgUHR5IEx0ZIIJAMKR/NsyfcazMAwGA1UdEwQF\nMAMBAf8wDQYJKoZIhvcNAQEFBQADggEBABw7w/5k4d5dVDgd/OOOmXdaaCIKvt7d\n3ntlv1SSvAoKT8d8lt97Dm5RrmefBI13I2yivZg5bfTge4+vAV6VdLFdWeFp1b/F\nOZkYUv6A8o5HW0OWQYVX26zIqBcG2Qrm3reiSl5BLvpj1WSpCsYvs5kaO4vFpMak\n/ICgdZD+rxwxf8Vb/6fntKywWSLgwKH3mJ+Z0kRlpq1g1oieiOm1/gpZ35s0Yuor\nXZba9ptfLCYSggg/qc3d3d0tbHplKYkwFm7f5ORGHDSD5SJm+gI7RPE+4bO8q79R\nPAfbG1UGuJ0b/oigagciHhJp851SQRYf3JuNSc17BnK2L5IEtzjqr+Q=\n-----END CERTIFICATE-----", null);
        Claims claims = jwtHelper.verifyAndDecodeToken(httpServletRequest);
        assertEquals(3, claims.size());
        assertThat(claims.get("actor"), is(instanceOf(LinkedHashMap.class)));
        assertThat(claims.get("pou"), is(instanceOf(String.class)));
        assertThat(claims.get("iat"), is(instanceOf(Integer.class)));
        assertEquals("TREAT", claims.get("pou"));
    }

    @Test(expected = JwtException.class)
    public void verifyAndDecodeInvalidToken() {
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        when(httpServletRequest.getHeader(HEADER_AUTHORIZATION)).thenReturn("Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
        Claims claims = jwtAuthHelper.verifyAndDecodeToken(httpServletRequest);
    }
}