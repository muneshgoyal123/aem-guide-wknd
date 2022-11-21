package com.adobe.aem.guides.wknd.core.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.json.JsonException;
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

import com.adobe.aem.guides.wknd.core.services.OSGiConfig;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Query Builder servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/servlet" })
public class NormalServlet extends SlingAllMethodsServlet 
{
   // private static final Logger log = LoggerFactory.getLogger(NormalServlet.class);

   @Reference
   OSGiConfig osGiConfig;

    @Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException
    
    {

        response.setContentType("application/json");

          response.getWriter().println("HELLO");
          final ResourceResolver resourceResolver = request.getResourceResolver();
          Page page = resourceResolver.adaptTo(PageManager.class).getPage("/content/wknd/us/en");
          JsonArray pagesArray = new JsonArray();
     try {

            String str= osGiConfig.getServiceName();
            response.getWriter().println("HELLO " +str);

            // Iterator<Page> childPages = page.listChildren();
            // while (childPages.hasNext()) {
            //     Page childPage = childPages.next();
            //     JsonObject pageObject = new JsonObject();
            //     pageObject.addProperty(childPage.getTitle(), childPage.getPath().toString());
            //     pagesArray.add(pageObject);
            // }
        } catch (JsonException e) {
           
        }

        response.setContentType("application/json");
        response.getWriter().write(pagesArray.toString());
    }


}

