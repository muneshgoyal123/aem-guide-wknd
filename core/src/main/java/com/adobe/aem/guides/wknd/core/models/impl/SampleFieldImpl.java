package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.aem.guides.wknd.core.models.SampleField;
import com.adobe.aem.guides.wknd.core.models.dto.UserInformation;



@Model(
        adaptables = {SlingHttpServletRequest.class},
        adapters = {SampleField.class},
        resourceType = {SampleFieldImpl.RESOURCE_TYPE},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class SampleFieldImpl implements SampleField
{
    protected static final String RESOURCE_TYPE = "wknd/components/sample-field";
    
    @SlingObject
    Resource componentResource;

    @ValueMapValue
    private String fname;

    @ValueMapValue
    private String lname;

    @ValueMapValue
    private List<String> languages;

    @ValueMapValue
    private String userImage;

    @ValueMapValue
    private List<UserInformation> userInformation;

    @PostConstruct
    public void init()
    {
       

        userInformation =new ArrayList<>();

        Resource resource = componentResource.getChild("userInformation");

        if(null != resource && resource.hasChildren()) {

            Iterator<Resource> cards = resource.listChildren();

            while(cards.hasNext()) {

                Resource card = cards.next();

                UserInformation userInformationData = new UserInformation();

                userInformationData.setUserClass(card.getValueMap().get("userClass", String.class));
                userInformationData.setDesignation(card.getValueMap().get("designation", String.class));
                userInformationData.setAge(card.getValueMap().get("age", String.class));
                userInformation.add(userInformationData);
    }
}
    }

    @Override
    public String getFname()
    {
        
        return fname;
    }
   
    @Override
    public String getLname()
    {
        
        return lname;
    }

    @Override
    public List<String> getLanguages() {
        if (languages != null) {
            Collections.sort(languages);
            return new ArrayList<String>(languages);
        } else {
            return Collections.emptyList();
        }
   }

   @Override
   public String getUserImage() 
   {
        return userImage;
   }

   @Override
   public List<UserInformation> getUserInformation() 
   {
        return userInformation;
   }


    @Override
    public boolean isEmpty() 
    {
                                        
        if (StringUtils.isBlank(fname)) {

            return true;   // name is missing but required
        }
          else if(StringUtils.isBlank(lname))
            {
                 return true;
            }

            else if(languages==null || languages.isEmpty())
            {
                return true;   // At least one language is required
            }

            else if(userImage==null || StringUtils.isBlank(userImage))
            {
                return true;
            }

            else if(userInformation==null || userInformation.isEmpty())
            {
                return true;
            }
            else {
                  return false; // Everything is populated, so this component is not considered empty
            }
        }


}
