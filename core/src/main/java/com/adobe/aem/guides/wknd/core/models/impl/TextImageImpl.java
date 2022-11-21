package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
//import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import com.adobe.aem.guides.wknd.core.models.TextImage;
//import org.apache.sling.models.factory.ModelFactory;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.commons.lang3.StringUtils;
//import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
//import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { SlingHttpServletRequest.class }, adapters = { TextImage.class }, resourceType = {
        TextImageImpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class TextImageImpl implements TextImage {
    protected static final String RESOURCE_TYPE = "wknd/components/textimage";

    @Self
    private SlingHttpServletRequest request;

    @ValueMapValue
    private String name;

    @ValueMapValue
    private String occupations;


    @ValueMapValue
    private List<String> headlines;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOccupations() {
        return occupations;
    }

    @Override
    public List<String> getHeadlines() {
         if (headlines != null) {
             Collections.sort(headlines);
             return new ArrayList<String>(headlines);
         } else {
             return Collections.emptyList();
         }
    }


    @PostConstruct
    private void init()
    {
        name="given name is "+name+"<br>"+"heell";

    }

    @Override
    public boolean isEmpty() {
        if (StringUtils.isBlank(name)) {
            return true;

        } else if (occupations == null || occupations.isEmpty()) {

            return true;
        }

        else {

            return false;
        }
    }
}