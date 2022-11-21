package com.adobe.aem.guides.wknd.core.models;

import java.util.List;

public interface TextImage 
{

    String getName();
    
    String getOccupations();

    List<String> getHeadlines();
   
    boolean isEmpty();
}
