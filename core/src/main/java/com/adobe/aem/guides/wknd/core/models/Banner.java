package com.adobe.aem.guides.wknd.core.models;

import org.osgi.annotation.versioning.ProviderType;

import com.adobe.cq.wcm.core.components.models.Image;

    @ProviderType
    public interface Banner extends Image 
    {
        public String getBannerText();
    
        boolean isEmpty();
    }
     

