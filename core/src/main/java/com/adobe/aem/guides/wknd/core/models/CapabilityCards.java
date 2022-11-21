package com.adobe.aem.guides.wknd.core.models;

import java.util.List;

import com.adobe.aem.guides.wknd.core.models.dto.CapabilityData;

public interface CapabilityCards 
{
    List<CapabilityData> getCapabilityCard();
    String getHeading();
    public boolean isEmpty();
   
}
