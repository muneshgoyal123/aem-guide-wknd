package com.adobe.aem.guides.wknd.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
//import org.jetbrains.annotations.NotNull;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Query Builder servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/demo" })
public class QueryBuilderServlet extends SlingAllMethodsServlet 
{
    private static final Logger log = LoggerFactory.getLogger(QueryBuilderServlet.class);

    @Reference
	private QueryBuilder builder;

    private Session session;

    
    @Override
    protected void doGet( SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException 
            
            {
             try{
                log.info("----------< Executing Query Builder Servlet >----------");

                ResourceResolver resourceResolver = request.getResourceResolver();
                session = resourceResolver.adaptTo(Session.class);
                Map<String, String> predicate = new HashMap<>();

                predicate.put("path", "/content/dam");
			predicate.put("type", "dam:Asset");
			predicate.put("group.p.or", "true");
			//predicate.put("group.1_fulltext", param);
			predicate.put("group.1_fulltext.relPath", "jcr:content");

            Query query = builder.createQuery(PredicateGroup.create(predicate), session);
			
			query.setStart(0);
			query.setHitsPerPage(20);
			
			/**
			 * Getting the search results
			 */
			SearchResult searchResult = query.getResult();
			
			for(Hit hit : searchResult.getHits()) {
				
				String path = hit.getPath();
				
				response.getWriter().println(path);
			}
        
		} catch (Exception e) {

			log.error(e.getMessage(), e);
		} finally {
			
			if(session != null) {
				
				session.logout();
			}
		}
	}
       
    }

