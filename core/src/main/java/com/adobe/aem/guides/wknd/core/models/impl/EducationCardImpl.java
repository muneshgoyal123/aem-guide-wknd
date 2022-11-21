package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import com.adobe.aem.guides.wknd.core.models.EducationCard;
import com.adobe.aem.guides.wknd.core.models.helper.EducationData;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { SlingHttpServletRequest.class }, adapters = { EducationCard.class }, resourceType = {
    EducationCardImpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class EducationCardImpl implements EducationCard
{
    protected static final String RESOURCE_TYPE = "wknd/components/educationcard";
    
    @SlingObject
    Resource componentResource;

    @ValueMapValue
    String title1;

    @ValueMapValue
    List<EducationData> institutes;

    @PostConstruct
    public void init()
    {
        institutes =new ArrayList<>();

        Resource res = componentResource.getChild("institutes");

        if(null != res && res.hasChildren()) {

            Iterator<Resource> cards = res.listChildren();

            while(cards.hasNext()) {

                Resource card = cards.next();

                EducationData data = new EducationData();

                data.setTitle2(card.getValueMap().get("title2", String.class));
                
                data.setHistory(card.getValueMap().get("history", String.class));
                
                institutes.add(data);
    }
}
    }

    @Override
    public String getTitle1() {
         
        return title1;
    }

    @Override
    public List<EducationData> getInstitutes() {
        
        return institutes;
    }

    

}
