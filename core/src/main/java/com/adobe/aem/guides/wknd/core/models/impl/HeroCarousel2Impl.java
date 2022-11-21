package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import com.adobe.aem.guides.wknd.core.models.HeroCarousel2;
import com.adobe.aem.guides.wknd.core.models.helper.HeroCarouselItem;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;



@Model(adaptables = {SlingHttpServletRequest.class,Resource.class},adapters = HeroCarousel2.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,resourceType = HeroCarousel2Impl.RESOURCE_TYPE)
public class HeroCarousel2Impl implements HeroCarousel2
{
    protected static final String RESOURCE_TYPE = "wknd/components/hero-carousel2";

    @SlingObject
    Resource componentResource;

    @ValueMapValue
    List<HeroCarouselItem> carousel;


    @PostConstruct
    public void init()
    {
        carousel =new ArrayList<>();

        Resource res = componentResource.getChild("carousel");

        if(null != res && res.hasChildren()) {

            Iterator<Resource> cards = res.listChildren();

            while(cards.hasNext()) {

                Resource card = cards.next();

                HeroCarouselItem item1 = new HeroCarouselItem();

                item1.setDescription(card.getValueMap().get("description", String.class));
                item1.setImage(card.getValueMap().get("image", String.class));
                
                carousel.add(item1);
    }
}
    }


    @Override
    public List<HeroCarouselItem> getCarousel() {
       
        return carousel;
    }


    
}
