package com.adobe.aem.guides.wknd.core.models;

import java.util.List;

import com.adobe.aem.guides.wknd.core.models.helper.BookDetails;

public interface AuthorBooks 
{
     String getAuthorName();
     List<String> getAuthorBooks();
     List<BookDetails> getBookDetailsWithMap();
}
