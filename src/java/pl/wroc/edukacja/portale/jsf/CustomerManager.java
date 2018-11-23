/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.wroc.edukacja.portale.jsf;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.Response;
import pl.wroc.edukacja.portale.rest.JerseyCustomer;
import pl.wroc.edukacja.portale.rest.TestMagentoRest;

/**
 *
 * @author p.grobelny
 */
public class CustomerManager {

    private String id;
    private String firstname;
    private String lastname;
    private String email;
    
    
    private Collection customerList;

    /**
     * Get the value of customerList
     *
     * @return the value of customerList
     */
    public Collection getCustomerList() {
        return customerList;
    }

    /**
     * Set the value of customerList
     *
     * @param customerList new value of customerList
     */
    public void setCustomerList(Collection customerList) {
        this.customerList = customerList;
    }


    /**
     * Get the value of lastname
     *
     * @return the value of lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Set the value of lastname
     *
     * @param lastname new value of lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Get the value of firstname
     *
     * @return the value of firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Set the value of firstname
     *
     * @param firstname new value of firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
     public String getEmail() {
        return email;
    }

    /**
     * Set the value of lastname
     *
     * @param email new value of lastname
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(String id) {
        this.id = id;
    }

    public String showCustomer() {

        FacesContext context = FacesContext.getCurrentInstance();
        JerseyCustomer rest;

        try {
            rest = new JerseyCustomer(new Integer(this.id).intValue());
        } catch (Exception e) {

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error reading user!",
                    "The user's id should be a number.");
            context.addMessage(null, message);
            Logger.getAnonymousLogger().log(Level.SEVERE,
                    "The user's id should be a number.",
                    e);
            return null;
        }
        
        
        
        Response resp = rest.gET_JSON_OAUTH2(Response.class);
        System.out.println(resp.getStatusInfo());
        //System.out.println(resp.getMediaType());
        if (!resp.getStatusInfo().toString().contains("OK")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error reading user!",
                    "Connection to API failed.");
            context.addMessage(null, message);
            Logger.getAnonymousLogger().log(Level.SEVERE,
                    "Connection to API failed.",
                    new Exception("Connection to API failed."));
            return null;
        }
        
        
        String respString = resp.readEntity(String.class);
        //System.out.println(respString);

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(respString);

            String idC = jsonNode.get("id").asText();
            this.setId(idC);
            String firstnameC = jsonNode.get("firstname").asText();
            this.setFirstname(firstnameC);
            
            String lastnameC = jsonNode.get("lastname").asText();
            this.setLastname(lastnameC);
            
            String emailC = jsonNode.get("email").asText();
            this.setEmail(emailC);
            
            
        } catch (Exception ex) {

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error reading user!",
                    "Unexpected error when reading user.  Please contact the system Administrator");
            context.addMessage(null, message);
            Logger.getAnonymousLogger().log(Level.SEVERE,
                    "Unable to read the user",
                    ex);
            return null;
        }
        
        customerList = new ArrayList();
        customerList.add(this);
        return "customer";
    }

}
