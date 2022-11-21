package com.adobe.aem.guides.wknd.core.models;

import java.util.List;
import java.util.Map;

public interface Publication1 
{
      String getTitle1();
      List<Map<String,String>> getBookDetailsMap();  //Return a List Containing Map String,String Type


       String getTitle2();
      String getDate();
      String getDescription();
    
}
