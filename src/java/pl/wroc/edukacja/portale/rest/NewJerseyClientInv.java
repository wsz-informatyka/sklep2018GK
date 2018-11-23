package pl.wroc.edukacja.portale.rest;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Client;

import org.glassfish.jersey.client.oauth2.OAuth2ClientSupport;
import javax.ws.rs.core.Feature;


/**
 * Jersey REST client generated for REST resource:_1
 * [sklep/rest/V1/customers/1]<br>
 * USAGE:
 * <pre>
 *        NewJerseyClient client = new NewJerseyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author p.grobelny
 */
public class NewJerseyClientInv {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://i-system.vot.pl";
    String storedToken = "0lomthcuogyrc597m63zx6datne2jd8p";

    public NewJerseyClientInv() {
        
        client = javax.ws.rs.client.ClientBuilder.newClient();
        
        Feature feat = OAuth2ClientSupport.feature(storedToken);
        client.register(feat);
                  
        webTarget = client.target(BASE_URI).path("portale2018GK/rest/V1/stockItems/1");
          
    }
    
    public NewJerseyClientInv(int customerId) {
        
        client = javax.ws.rs.client.ClientBuilder.newClient();
        
        Feature feat = OAuth2ClientSupport.feature(storedToken);
        client.register(feat);
                  
        webTarget = client.target(BASE_URI).path("portale2018GK/rest/V1/stockItems/"+customerId);
          
    }

    
    /**
     * @param responseType Class representing the response
     * @return response object (instance of responseType class)
     */
    public <T> T gET_JSON_OAUTH2(Class<T> responseType) throws ClientErrorException {
      return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
              .property(OAuth2ClientSupport.OAUTH2_PROPERTY_ACCESS_TOKEN,storedToken)
              .get(responseType);
 
    }
    
        
    /**
     * @param responseType Class representing the response
     * @return response object (instance of responseType class)
     */
    public <T> T gET_JSON(Class<T> responseType) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    /**
     * @param responseType Class representing the response
     * @return response object (instance of responseType class)
     */
    public <T> T gET_FORM(Class<T> responseType) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).get(responseType);
    }

    /**
     * @param responseType Class representing the response
     * @return response object (instance of responseType class)
     */
    public <T> T gET_XML(Class<T> responseType) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    /**
     * @param responseType Class representing the response
     * @return response object (instance of responseType class)
     */
    public <T> T gET_TEXT_XML(Class<T> responseType) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.TEXT_XML).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
