package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
//import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.adobe.aem.guides.wknd.core.models.Publication1;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
//import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;





@Model(adaptables = { SlingHttpServletRequest.class }, adapters = { Publication1.class }, resourceType = {
Publication1Impl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Publication1Impl implements Publication1
 {
   
    protected static final String RESOURCE_TYPE = "wknd/components/publication1";
    public List<Map<String,String>> bookDetailsMap;

    @Self
    private SlingHttpServletRequest request;


    @Inject
    Resource componentResource;

    
    @ValueMapValue
    private String title1;

    
    @ValueMapValue
    private String title2;

    @ValueMapValue
    private String date;

    @ValueMapValue
    private String description;
    
    
    @Override
    public String getTitle1()
    {
        return title1;
    }

    @Override
    public List<Map<String, String>> getBookDetailsMap() {
         
        bookDetailsMap=new ArrayList<>();
        try {
            //Resource bookDetail = this.resourceResolver.getResource("bookDetailsMap");
            Resource bookDetail=componentResource.getChild("bookDetailsMap");
            if(bookDetail!=null){
                for (Resource book : bookDetail.getChildren()) {
                    Map<String,String> bookMap=new HashMap<>();
                    bookMap.put("title2",book.getValueMap().get("title2",String.class));
                    bookMap.put("date",book.getValueMap().get("date",String.class));
                    bookMap.put("description",book.getValueMap().get("description",String.class));
                    bookDetailsMap.add(bookMap);
                }
            }
        }catch (Exception e){
            
        }
        
           return bookDetailsMap;
    }
   

    @Override
    public String getTitle2()
    {
        return title2;
    }

    @Override
    public String getDate()
    {
        return date;


    }

    @Override
    public String getDescription()
    {
        return description;
    }

 }

