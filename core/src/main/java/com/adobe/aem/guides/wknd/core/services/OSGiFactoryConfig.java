package com.adobe.aem.guides.wknd.core.services;

import java.util.List;

public interface OSGiFactoryConfig {
    public int getConfigID();
    public String getServiceName();
    public String getServiceURL();
    public OSGiFactoryConfig get(int configID);
    public List<OSGiFactoryConfig> getAllConfigs();
}