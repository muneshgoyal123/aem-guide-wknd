package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import com.adobe.aem.guides.wknd.core.models.MultiComponent;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables = {SlingHttpServletRequest.class},
        adapters = {MultiComponent.class},
        resourceType = {MultiComponentImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class MultiComponentImpl implements MultiComponent {
    
    protected static final String RESOURCE_TYPE = "wknd/components/multicomponent";

    @ValueMapValue
    private String alignChildren;

    @ValueMapValue
    private String fruitType;

    @ValueMapValue
    private boolean columnLast;

    @ValueMapValue
    private boolean hideMismatches;

    @ValueMapValue
    private String backgroundColor;

    @ValueMapValue
    private List<String> headlines;

    @ValueMapValue
    private int columnWidth;

    @PostConstruct
    private void init() {
        alignChildren = StringUtils.isEmpty(alignChildren) ? "No Align Selected" : alignChildren;
        fruitType = StringUtils.isEmpty(fruitType) ? "No Fruit Selected" : fruitType;   
             
    }
    
    @Override
    public String getAlignChildren(){

        return alignChildren;
    }

    @Override
    public String getFruitType(){

        return fruitType;
    }

    @Override
    public boolean getColumnLast(){

        return columnLast;  
    }

    @Override
    public boolean getHideMismatches(){

        return hideMismatches;  
    }

    @Override
    public String getBackgroundColor(){

        return backgroundColor;  
    }   

    @Override
    public List<String> getHeadlines() {
         if (headlines != null) {
             Collections.sort(headlines);
             return new ArrayList<String>(headlines);
         } else {
             return Collections.emptyList();
         }
    }

    @Override
    public int getColumnWidth(){

        return columnWidth;  
    }
}
