package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
//import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.adobe.aem.guides.wknd.core.models.StudentCard;

import com.adobe.cq.dam.cfm.ContentElement;
//import org.apache.sling.models.factory.ModelFactory;
import com.adobe.cq.dam.cfm.ContentFragment;
import com.adobe.cq.wcm.core.components.models.Image;

import java.util.Optional;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
//import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.commons.lang3.StringUtils;
//import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
//import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { SlingHttpServletRequest.class }, adapters = { StudentCard.class }, resourceType = {
        StudentCardImpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class StudentCardImpl implements StudentCard {
    protected static final String RESOURCE_TYPE = "wknd/components/studentcard";
   



    @Self
    private SlingHttpServletRequest request;
    //private Resource resource;

    @Inject
    ResourceResolver resourceResolver;
    //private Optional<ContentFragment> contentFragment;

    @ValueMapValue
    private String student;

    // @ValueMapValue
    private String FirstName;

    //@ValueMapValue
    private String LastName;


    //@ValueMapValue
    private String Address;

    // @ValueMapValue
    private String Email;

    // @ValueMapValue
    private String Image;

   





    @PostConstruct
    private void init()
    {
        //Resource fragmentResource = resourceResolver.getResource(student);
        //contentFragment = Optional.ofNullable(fragmentResource.adaptTo(ContentFragment.class));
        
        //  FirstName=contentFragment.map(cf -> cf.getElement("firstname")).map(ContentElement::getContent)
        // .orElse(StringUtils.EMPTY);

        // LastName=contentFragment.map(cf -> cf.getElement("lastname")).map(ContentElement::getContent)
        // .orElse(StringUtils.EMPTY);

        // Address=contentFragment.map(cf -> cf.getElement("address")).map(ContentElement::getContent)
        // .orElse(StringUtils.EMPTY);

        // Email=contentFragment.map(cf -> cf.getElement("email")).map(ContentElement::getContent)
        // .orElse(StringUtils.EMPTY);

        // Image=contentFragment.map(cf -> cf.getElement("image")).map(ContentElement::getContent)
        // .orElse(StringUtils.EMPTY);


        Resource cfResource = this.resourceResolver.getResource(student);
        ContentFragment contentFragment = cfResource.adaptTo(ContentFragment.class);


        if (contentFragment != null) {
             FirstName = contentFragment.getElement("firstname") != null
            ? contentFragment.getElement("firstname").getContent()
            : null;
           
            LastName = contentFragment.getElement("lastname") != null
            ? contentFragment.getElement("lastname").getContent()
            : null;

            Address = contentFragment.getElement("address") != null
            ? contentFragment.getElement("address").getContent()
            : null;

            Email = contentFragment.getElement("email") != null
            ? contentFragment.getElement("email").getContent()
            : null;

            Image = contentFragment.getElement("image") != null
            ? contentFragment.getElement("image").getContent()
            : null;


    }
}

    @Override
    public String getFirstName() 
    {
        return FirstName;
    }

    @Override
    public String getLastName() {
        return LastName;
    }



    @Override
    public String getAddress() {
        return Address;
    }

    @Override
    public String getEmail() {
        return Email;
    }

    @Override
    public String getImage() {
        return Image;
    }




  
    

    
}