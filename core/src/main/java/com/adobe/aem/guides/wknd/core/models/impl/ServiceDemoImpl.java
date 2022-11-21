package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.Iterator;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.wknd.core.models.ServiceDemo;
import com.adobe.aem.guides.wknd.core.services.DemoService;
import com.day.cq.wcm.api.Page;

@Model(adaptables = SlingHttpServletRequest.class,
adapters = ServiceDemo.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ServiceDemoImpl implements ServiceDemo 
{
  private static final Logger LOG = LoggerFactory.getLogger(ServiceDemoImpl.class);
  
  @OSGiService
  DemoService demoService;

@Override
public Iterator<Page> getPageList() {
    
    return demoService.getPages();
}




  
  
}
