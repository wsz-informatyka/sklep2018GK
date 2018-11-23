<%-- 
    Document   : customer
    Created on : 12-Nov-2018, 21:39:52
    Author     : p.grobelny
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE html>

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Customer Detail</title>
            <link rel="stylesheet" type="text/css" href="jsfcrud.css" />
        </head>
        <body>
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            <h1>Customer Detail</h1>

            <h:dataTable value="#{customermanager.customerList}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Customer Id"/>
                    </f:facet>
                    <h:outputText value="#{item.id}"/>

                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Firstname"/>
                    </f:facet>
                    <h:outputText value="#{item.firstname}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Lastname"/>
                    </f:facet>
                    <h:outputText value="#{item.lastname}"/>
                </h:column>
                
                 <h:column>
                    <f:facet name="header">
                        <h:outputText value="Email"/>
                    </f:facet>
                    <h:outputText value="#{item.email}"/>
                </h:column>

            </h:dataTable>
            <h:form id="return">
                <h:commandLink id="return"
                               value="Return"
                               action="return"/>
            </h:form>
        </body>
    </html>
</f:view>