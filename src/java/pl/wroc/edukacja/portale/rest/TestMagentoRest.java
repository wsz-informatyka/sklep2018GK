package pl.wroc.edukacja.portale.rest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import pl.wroc.edukacja.portale.rest.NewJerseyClient;
import pl.wroc.edukacja.portale.rest.NewJerseyClientInv;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p.grobelny
 */
public class TestMagentoRest {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        NewJerseyClient rest = new NewJerseyClient();
        Response resp = rest.gET_JSON_OAUTH2(Response.class);
        System.out.println(resp.getStatusInfo());
        System.out.println(resp.getMediaType());
        String respString = resp.readEntity(String.class);
        System.out.println(respString);

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(respString);
        } catch (IOException ex) {
            Logger.getLogger(TestMagentoRest.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        String id = jsonNode.get("id").asText();
        System.out.println("id: "+id);
        
        String name = jsonNode.get("name").asText();
        System.out.println("name: "+name);
        
        String price = jsonNode.get("price").asText();
      System.out.println("price: "+price); 
      

    
    }
}
    
