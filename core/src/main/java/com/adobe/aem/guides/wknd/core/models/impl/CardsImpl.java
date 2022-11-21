package com.adobe.aem.guides.wknd.core.models.impl;

import com.adobe.aem.guides.wknd.core.models.Cards;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { SlingHttpServletRequest.class }, adapters = { Cards.class }, resourceType = {
    CardsImpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CardsImpl implements Cards

{
    protected static final String RESOURCE_TYPE = "wknd/components/cards";

    @Self
    private SlingHttpServletRequest request;

    @SlingObject
    Resource componentResource;

    @ValueMapValue
    private String image1;

    @ValueMapValue
    private String image2;

    @ValueMapValue
    private String image3;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String text;

    @ValueMapValue
    private String title1;

    @ValueMapValue
    private String history;

    @ValueMapValue
    private String image4;

    @Override
    public String getImage1() {
        
        return image1;
    }

    @Override
    public String getImage2() {
        
        return image2;
    }

    @Override
    public String getImage3() {
        
        return image3;
    }

    @Override
    public String getTitle() {
        
        return title;
    }

    @Override
    public String getText() {
        
        return text;
    }

    @Override
    public String getTitle1() {
        
        return title1;
    }

    @Override
    public String getHistory() {
       
        return history;
    }

    @Override
    public String getImage4() {
        
        return image4;
    }

    


}
