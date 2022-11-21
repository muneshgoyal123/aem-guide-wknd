package com.adobe.aem.guides.wknd.core.models.impl;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.via.ResourceSuperType;

import com.adobe.aem.guides.wknd.core.models.CardComponent;
import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.wcm.core.components.models.Image;


@Model(
    adaptables = SlingHttpServletRequest.class, 
    adapters = { CardComponent.class,ComponentExporter.class}, 
    resourceType = CardComponentImpl.RESOURCE_TYPE, 
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
    )
public class CardComponentImpl implements CardComponent 
{
    static final String RESOURCE_TYPE = "wknd/components/cardComponent";

    @Self
    @Via(type = ResourceSuperType.class)
    private Image imagess;

    @ValueMapValue
    private String firstName;

    @ValueMapValue
    private String lastName;

    @Override
    public String getFirstName() {
        
        return firstName;
    }

    @Override
    public String getLastName() {
       
        return lastName;
    }

    @Override
    public String getSrc() {
        return null != imagess ? imagess.getSrc() : null;
    }

    @Override
    public String getAlt() {
        return null != imagess ? imagess.getAlt() : null;
    }

    @Override
    public String getTitle() {
        return null != imagess ? imagess.getTitle() : null;
    }

    @Override
    public String getExportedType() {
        return CardComponentImpl.RESOURCE_TYPE;
    }

    @Override
    public boolean isEmpty() {
        
        if (StringUtils.isBlank(firstName)) {
            // Name is missing, but required
            return true;
        }
          else if(StringUtils.isBlank(lastName))
          {
              return true;
          }
        else if (imagess == null || StringUtils.isBlank(imagess.getSrc())) {
            // A valid image is required
            return true;
        } else {
            // Everything is populated, so this component is not considered empty
            return false;
        }
    }
    }

    


