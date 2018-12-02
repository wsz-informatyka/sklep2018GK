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
import pl.wroc.edukacja.portale.rest.NewJerseyClient;
import pl.wroc.edukacja.portale.rest.TestMagentoRest;

/**
 *
 * @author p.grobelny
 */
public class ProductManager {

    private String id;
    private String name;
    private String price;
    private String qty;
 
    
    private Collection productList;

    /**
     * Get the value of productList
     *
     * @return the value of productList
     */
    public Collection getProductList() {
        return productList;
    }

    /**
     * Set the value of productList
     *
     * @param productList new value of productList
     */
    public void setProductList(Collection productList) {
        this.productList = productList;
    }


    /**
     * Get the value of lastname
     *
     * @return the value of lastname
     */
    public String getPrice() {
        return price;
    }

    /**
     * Set the value of lastname
     *
     * @param price new value of lastname
     */
    public void setPrice(String price) {
        this.price = price;
    }
    
  public String getQty() {
        return qty;
    }

    /**
     * Set the value of lastname
     *
     * @param qty new value of lastname
     */
    public void setQty(String qty) {
        this.qty = qty;
    }
    


        
    /**
     * Get the value of firstname
     *
     * @return the value of firstname
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of firstname
     *
     * @param name new value of firstname
     */
    public void setName(String name) {
        this.name = name;
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

    public String showProduct() {

        FacesContext context = FacesContext.getCurrentInstance();
        NewJerseyClient rest;

        try {
            rest = new NewJerseyClient(new Integer(this.id).intValue());
        } catch (Exception e) {

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error reading product!",
                    "The product id should be a number.");
            context.addMessage(null, message);
            Logger.getAnonymousLogger().log(Level.SEVERE,
                    "The product id should be a number.",
                    e);
            return null;
        }
        
        
        
        Response resp = rest.gET_JSON_OAUTH2(Response.class);
        System.out.println(resp.getStatusInfo());
        //System.out.println(resp.getMediaType());
        if (!resp.getStatusInfo().toString().contains("OK")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error reading product!",
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

            String idC = jsonNode.get("sku").asText();
            this.setId(idC);
            String nameC = jsonNode.get("name").asText();
           this.setName(nameC);
           String priceC = jsonNode.get("price").asText();
            this.setPrice(priceC);
   
          
        } catch (Exception ex) {

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error reading product!",
                    "Unexpected error when reading user.  Please contact the system Administrator");
            context.addMessage(null, message);
            Logger.getAnonymousLogger().log(Level.SEVERE,
                    "Unable to read the product",
                    ex);
            return null;
        }
        
        productList = new ArrayList();
        productList.add(this);
        return "product";
    }

}
