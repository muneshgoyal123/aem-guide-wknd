package com.adobe.aem.guides.wknd.core.models;

import java.util.List;

import com.adobe.aem.guides.wknd.core.models.helper.BookItem;

public interface Multitab 
{
    String getData();
    String getImage();
    String getTitle1();
    List<BookItem> getBooks();
}
