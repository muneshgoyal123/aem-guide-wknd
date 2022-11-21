package com.adobe.aem.guides.wknd.core.servlets;

import java.io.IOException;

import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.wknd.core.services.CustomSearchService;
import com.google.gson.JsonObject;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Query Builder servlet",

        "sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/search1" })
public class CustomSearchServlet extends SlingAllMethodsServlet{

    private static final Logger LOG = LoggerFactory.getLogger(CustomSearchServlet.class);

    @Reference
    CustomSearchService customSearchService;
    
    private Session session;

    @Override
    protected void doGet( SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/html");
                response.getWriter().println("HELLO");
        JsonObject searchResult = null; 
        try {
           
            ResourceResolver resourceResolver = request.getResourceResolver();
            session = resourceResolver.adaptTo(Session.class);
            String searchtext = request.getRequestParameter("searchText").getString();

            searchResult = customSearchService.searchresult(searchtext);

        } catch (Exception e) {
            LOG.info("\n ERROR {} ", e.getMessage());
        }
       
        response.getWriter().write(searchResult.toString());
    }    
}
