package com.adobe.aem.guides.wknd.core.models;

import java.util.List;

import com.adobe.aem.guides.wknd.core.models.helper.EducationData;

public interface EducationCard 
{
    String getTitle1();
    List<EducationData> getInstitutes();

}
