package com.adobe.aem.guides.wknd.core.models.impl;

import javax.inject.Inject;

import com.adobe.aem.guides.wknd.core.models.Author;
import com.day.cq.wcm.api.Page;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


@Model(adaptables = {SlingHttpServletRequest.class,Resource.class},adapters = Author.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,resourceType = AuthorImpl.RESOURCE_TYPE)
public class AuthorImpl implements Author 
{
    
    protected static final String RESOURCE_TYPE = "wknd/components/author";

    @ScriptVariable
    Page currentPage;

    @Inject               // inject this property @via annotation by resource object
    @Via("resource")
    @Default(values = "FIRSTNAME")
    String fname;

    @ValueMapValue
    @Required
    @Default(values = "LASTNAME")
    String lname;

    @Inject
    @Via("resource")
    boolean professor;

    @Override
    public String getFirstName() {
        
        return fname;
    }

    @Override
    public String getLastName() {
        
        return lname;
    }

    @Override
    public boolean getIsProfessor() {
    
        return professor;
    }

    @Override
    public String getPageTitle()
    {
        return currentPage.getTitle();
    }
    
}
