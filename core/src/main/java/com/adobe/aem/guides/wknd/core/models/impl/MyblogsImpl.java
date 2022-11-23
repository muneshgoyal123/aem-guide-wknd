package com.adobe.aem.guides.wknd.core.models.impl;
import com.adobe.aem.guides.wknd.core.models.dto.BlogsList;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;

// these three packages are responsible for model 
import org.apache.sling.models.annotations.Model;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;

//mapping dialog value 
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

//resolver
import org.apache.sling.models.annotations.Source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// for post construct for init
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

// resource and resource resolver
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

// for logger 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adobe.aem.guides.wknd.core.models.Myblogs;

@Model(adaptables = { SlingHttpServletRequest.class }, adapters = { Myblogs.class }, resourceType = {
    MyblogsImpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class MyblogsImpl implements Myblogs
{
    protected static final String RESOURCE_TYPE = "wknd/components/myblogs";

    private static final Logger LOGGER = LoggerFactory.getLogger(MyblogsImpl.class);

    // @Self
    // Resource resource;

    @Inject
    @Source("sling-object")
    private ResourceResolver resourceResolver;

    @ValueMapValue
    String mainpath;

    List<BlogsList> myPageList = new ArrayList<>();


    @PostConstruct
    protected void init() {

        // ResourceResolver resolver = resource.getResourceResolver();

        Session session = resourceResolver.adaptTo(Session.class);
        QueryBuilder queryBuilder = resourceResolver.adaptTo(QueryBuilder.class);

        Map<String, String> predicate = new HashMap<>();
        predicate.put("path", mainpath);
        predicate.put("type", "cq:Page");

        Query query = null;

        try {
            query = queryBuilder.createQuery(PredicateGroup.create(predicate), session);

        } catch (Exception e) {
            LOGGER.error("Error in Query Check ");
        }

        SearchResult searchResult = query.getResult();

        for (Hit hit : searchResult.getHits()) {
            String path = null;

            try {
                BlogsList temp = new BlogsList();
                path = hit.getPath();
                Resource articlResource = resourceResolver.getResource(path);
                Page articlPage = articlResource.adaptTo(Page.class);
                String title = articlPage.getTitle();

                String description = articlPage.getDescription();

                temp.setPagePath(path);
                temp.setPathTitle(title);
                temp.setPathDescription(description);
                myPageList.add(temp);

                // LOGGER.debug("" + description + " " + title);
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }

        }

    }

    @Override
    public List<BlogsList> getMyBlogList() {

        return myPageList;
    }
}
