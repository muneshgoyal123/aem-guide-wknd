package com.adobe.aem.guides.wknd.core.models;

import com.adobe.cq.wcm.core.components.models.Image;



public interface CardComponent extends Image

{
    public String getFirstName();
    public String getLastName();
    
        boolean isEmpty();
}
