package com.adobe.aem.guides.wknd.core.services.impl;

 import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Session;

import com.day.cq.search.result.Hit;


import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;



import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.wknd.core.services.CustomSearchService;
import com.adobe.aem.guides.wknd.core.util.ResolverUtil;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;





@Component(service = CustomSearchService.class, immediate = true)

public class CustomSearchServiceImpl implements CustomSearchService{


    private static final Logger LOG = LoggerFactory.getLogger(CustomSearchServiceImpl.class);
    
    @Reference
    QueryBuilder queryBuilder;

    @Reference
    ResourceResolverFactory resourceResolverFactory;


    public Map<String,String> createTextSearchQuery(String searchtext)
    {
        Map<String,String> queryMap = new HashMap<>();
        queryMap.put("path", "content/wknd");
        queryMap.put("type", "cq:Page");
        queryMap.put("group.p.or", "true");
        queryMap.put("group.1_fulltext.relPath", "jcr:content");
        queryMap.put("fulltext", searchtext);

        return queryMap;
    }

    @Override
    public JsonObject searchresult(String searchtext) {
       
        LOG.info("\n ------SEARCH RESULT ----- ");
        JsonObject searchResult = new JsonObject();

        try {
            ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
            final Session session = resourceResolver.adaptTo(Session.class);
            Query query= queryBuilder.createQuery(PredicateGroup.create(createTextSearchQuery(searchtext)), session);

            SearchResult result = query.getResult();

            int perPageResult = result.getHits().size();
            long totalResults = result.getTotalMatches();
            long startingResult = result.getStartIndex();
            
            searchResult.addProperty("perPageResult", perPageResult);
            searchResult.addProperty("totalaresults", totalResults);
            searchResult.addProperty("startingResult", startingResult);


            List<Hit> hits = result.getHits();
            JsonArray resultArray = new JsonArray();
            for (Hit hit : hits) {
                
                Page page = hit.getResource().adaptTo(Page.class);
                JsonObject resulObject = new JsonObject();
                resulObject.addProperty("title", page.getTitle());
                resulObject.addProperty("path", page.getPath());
                resultArray.add(resulObject);

                LOG.info("\n Page {} ", page.getPath());

            }
            searchResult.add("results", resultArray);
            


        } catch (Exception e) {
            LOG.info("\n ----ERROR-----{}", e.getMessage());
        }
        



        return searchResult;
    }

   

   
}