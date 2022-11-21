package com.adobe.aem.guides.wknd.core.services;

import com.google.gson.JsonObject;

public interface CustomSearchService {
    public JsonObject searchresult(String searchtext);
}
