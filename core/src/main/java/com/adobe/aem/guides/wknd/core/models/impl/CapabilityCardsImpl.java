package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.aem.guides.wknd.core.models.CapabilityCards;
import com.adobe.aem.guides.wknd.core.models.dto.CapabilityData;

@Model(adaptables = {SlingHttpServletRequest.class,Resource.class},adapters = CapabilityCards.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,resourceType = CapabilityCardsImpl.RESOURCE_TYPE)
public class CapabilityCardsImpl implements CapabilityCards 
{
    protected static final String RESOURCE_TYPE = "wknd/components/capabilitycards";

    @SlingObject
    Resource componentResource;

    @ValueMapValue
    String heading;

    @ValueMapValue
    List<CapabilityData> capabilityCard;

    @PostConstruct
     public void init()
     {
        capabilityCard =new ArrayList<>();

        Resource res = componentResource.getChild("capabilityCard");

        if(null != res && res.hasChildren()) {

            Iterator<Resource> cards = res.listChildren();

            while(cards.hasNext()) {

                Resource card = cards.next();

                CapabilityData capabilityData = new CapabilityData();

                capabilityData.setSubheading(card.getValueMap().get("subheading", String.class));
                capabilityCard.add(capabilityData);
    }
}

     }

    @Override
    public List<CapabilityData> getCapabilityCard() {
        
        return capabilityCard;
    }

    @Override
    public String getHeading() {
        
        return heading;
    }

    @Override
    public boolean isEmpty() {
        
        return StringUtils.isBlank(heading)&&StringUtils.isBlank((CharSequence) capabilityCard);
    }


    
}
