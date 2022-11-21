package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import com.adobe.aem.guides.wknd.core.models.Publication;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;



@Model(adaptables = { SlingHttpServletRequest.class }, adapters = { Publication.class }, resourceType = {
    PublicationImpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class PublicationImpl implements Publication 
{
    protected static final String RESOURCE_TYPE = "wknd/components/publication";


    @Self
    private SlingHttpServletRequest request;

    @ValueMapValue
    private String title1;

    @ValueMapValue
    private List<String> title2;

    @ValueMapValue
    private List<String> date;

    @ValueMapValue
    private List<String> description;

    @PostConstruct
    public void init()
    {
       title2=new ArrayList<String>(title2)!=null?title2:Collections.emptyList();
       date=new ArrayList<String>(date)!=null?date:Collections.emptyList();
       description=new ArrayList<String>(description)!=null?description:Collections.emptyList();

    }

    @Override
    public String getTitle1()
    {
        return title1;
    }

    @Override
    public List<String> getTitle2()
    {
        return title2;
    }

    @Override
    public List<String> getDate()
    {
        return date;
    }

    @Override
    public List<String> getDescription()
    {
        return description;
    }


}
