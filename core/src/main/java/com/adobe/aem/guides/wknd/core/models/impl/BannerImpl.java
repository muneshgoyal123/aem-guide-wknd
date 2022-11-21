package com.adobe.aem.guides.wknd.core.models.impl;

import com.adobe.aem.guides.wknd.core.models.Banner;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.via.ResourceSuperType;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.wcm.core.components.models.Image;

@Model(
    adaptables = SlingHttpServletRequest.class, 
    adapters = { Banner.class,ComponentExporter.class}, 
    resourceType = BannerImpl.RESOURCE_TYPE, 
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
    )
public class BannerImpl implements Banner {
    
    // points to the the component resource path in ui.apps
    static final String RESOURCE_TYPE = "wknd/components/banner";

    @Self
    private SlingHttpServletRequest request;

    // With sling inheritance (sling:resourceSuperType) we can adapt the current resource to the Image class
    // this allows us to re-use all of the functionality of the Image class, without having to implement it ourself
    // see https://github.com/adobe/aem-core-wcm-components/wiki/Delegation-Pattern-for-Sling-Models
    @Self
    @Via(type = ResourceSuperType.class)
    private Image image;

    // map the property saved by the dialog to a variable named `bannerText`
    @ValueMapValue
    private String bannerText;

    // public getter to expose the value of `bannerText` via the Sling Model and JSON output
    @Override
    public String getBannerText() {
        return bannerText;
    }

    // Re-use the Image class for all other methods:

    @Override
    public String getSrc() {
        return null != image ? image.getSrc() : null;
    }

    @Override
    public String getAlt() {
        return null != image ? image.getAlt() : null;
    }

    @Override
    public String getTitle() {
        return null != image ? image.getTitle() : null;
    }


    // method required by `ComponentExporter` interface
    // exposes a JSON property named `:type` with a value of `wknd/components/banner`
    // required to map the JSON export to the SPA component props via the `MapTo`
    @Override
    public String getExportedType() {
        return BannerImpl.RESOURCE_TYPE;
    }

    @Override
    public boolean isEmpty() {
        
        if (StringUtils.isBlank(bannerText)) {
            // Name is missing, but required
            return true;
        } else if (image == null || StringUtils.isBlank(image.getSrc())) {
            // A valid image is required
            return true;
        } else {
            // Everything is populated, so this component is not considered empty
            return false;
        }
    }
}
