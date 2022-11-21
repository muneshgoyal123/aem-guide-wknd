package com.adobe.aem.guides.wknd.core.models;

import java.util.List;

import com.adobe.aem.guides.wknd.core.models.dto.UserInformation;

public interface SampleField 
{
   String getFname(); 
   String getLname();
   List<String> getLanguages();
   String getUserImage();
   List<UserInformation> getUserInformation();
   boolean isEmpty();   
}
