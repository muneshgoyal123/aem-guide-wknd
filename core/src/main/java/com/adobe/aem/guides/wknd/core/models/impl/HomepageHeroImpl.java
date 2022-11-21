package com.adobe.aem.guides.wknd.core.models.impl;

import com.adobe.aem.guides.wknd.core.models.HomepageHero;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { SlingHttpServletRequest.class }, adapters = { HomepageHero.class }, resourceType = {
HomepageHeroImpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HomepageHeroImpl implements HomepageHero
{
    protected static final String RESOURCE_TYPE = "wknd/components/homepagehero";

    @Self
    private SlingHttpServletRequest request;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String image; 


    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getImage() {
        return image;
    }

}
