package com.adobe.aem.guides.wknd.core.models;

import java.util.Iterator;


import com.day.cq.wcm.api.Page;

public interface ServiceDemo 
{
    public Iterator<Page> getPageList();
    
}
