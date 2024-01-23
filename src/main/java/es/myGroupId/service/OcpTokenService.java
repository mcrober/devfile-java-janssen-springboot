package es.myGroupId.service;





import es.myGroupId.Util.Util;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * Service Class to get tokens from openshift
 *
 * @author Santander Technology
 */
@Getter
@Setter
public class OcpTokenService {


    /**
     * OAUTHSUFFIX
     */
    private static final String OAUTHSUFFIX = "/oauth/authorize?client_id=openshift-challenging-client&response_type=token";

    /**
     * RestTemplate
     * restTemplate
     */
    @Autowired
    private final RestTemplate restTemplate;

    private final String username;

    private final String nassword;




    /**
     * OcpTokenService constructor
     *
     * @param restTemplate restTemplate
     * @param username username
     * @param nassword nassword

     */
    public OcpTokenService(RestTemplate restTemplate, String username,
                           String nassword) {

        this.restTemplate = restTemplate;
        this.username = username;
        this.nassword = nassword;


    }




    /**
     * getToken: gets token from openshift
     *
     * @param paas paas
     * @return token
     */
    public String getToken(String paas) {

        String token ="";
        String url = paas + OAUTHSUFFIX;
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, buildHttpEntity(), String.class);

        URI location = response.getHeaders().getLocation();
        if (location != null) {
            token = location.getFragment().split("=")[1].split("&")[0];
        }
        return token;
    }



    /**
     * getTokenFromEnvLocationProvider
     * @param tokens tokens
     * @return token
     */
    public String getTokenFromEnvLocationProvider( Map<String,String> tokens ) {

        String ocToken =tokens.get("DEV-BO1"+"-");

        return ocToken;
    }

    /**
     * getTokenFromEnvLocationProvider
     * @param ocpProvider ocpProvider
     * @param tokens tokens
     * @return token
     */
    public String getTokenFromEnvLocationProvider(String ocpProvider,
                                                  Map<String,String> tokens ) {

        String ocToken =tokens.get("DEV-SAN"+"-"+ocpProvider);

        return ocToken;
    }


    /**
     * buildHttpEntity
     * @return httpentity httpentity
     */
    private HttpEntity<String[]> buildHttpEntity (){

        HttpHeaders authHeaders = Util.createAuthorizationHeaders(username, nassword);
        HttpEntity<String[]> httpEntity = new HttpEntity<>(authHeaders);
        return httpEntity;
    }




}

