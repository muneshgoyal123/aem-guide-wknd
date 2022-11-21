package com.adobe.aem.guides.wknd.core.models;




public interface OSGiConfigDemo {
    /*--------Start Tutorial #31--------*/
    public String getServiceName();
    public int getServiceCount();
    public boolean isLiveData();
    public String[] getCountries() ;
    public String getRunModes();
    public String getApiName();
}