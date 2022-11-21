package com.adobe.aem.guides.wknd.core.models.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


import com.adobe.aem.guides.wknd.core.models.CustomSearch;


@Model(adaptables = SlingHttpServletRequest.class,
       adapters = CustomSearch.class,
       resourceType = CustomSearchImpl.RESOURCE_TYPE,
       defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)


public class CustomSearchImpl implements CustomSearch{


    
    protected static final String RESOURCE_TYPE = "wknd/components/customsearch";

   
    @ValueMapValue
    String  rootPath;


    @Override
    public String getRootPath() {
        
        return rootPath;
    }
}