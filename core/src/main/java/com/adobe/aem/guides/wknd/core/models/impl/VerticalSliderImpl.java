package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import com.adobe.aem.guides.wknd.core.models.VerticalSlider;
import com.adobe.aem.guides.wknd.core.models.helper.CarousalItem;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { SlingHttpServletRequest.class }, adapters = { VerticalSlider.class }, resourceType = {
    VerticalSliderImpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class VerticalSliderImpl implements VerticalSlider
{
    protected static final String RESOURCE_TYPE = "wknd/components/vertical-slider2";


    @Self
    private SlingHttpServletRequest request;

    @SlingObject
    Resource componentResource;

    @ValueMapValue
    private List<CarousalItem> carousal;

    @PostConstruct
    public void init()
    {
         carousal =new ArrayList<>();

        Resource res = componentResource.getChild("carousal");

        if(null != res && res.hasChildren()) {

            Iterator<Resource> cards = res.listChildren();

            while(cards.hasNext()) {

                Resource card = cards.next();

                CarousalItem ci = new CarousalItem();

                ci.setTitle(card.getValueMap().get("title", String.class));
                
                ci.setDescription(card.getValueMap().get("description", String.class));
                ci.setImages(card.getValueMap().get("images", String.class));
                carousal.add(ci);
    }
}
    }


    @Override
     public List<CarousalItem> getCarousal()
     {
         return carousal;
     }
}
