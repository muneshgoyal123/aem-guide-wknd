package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.adobe.aem.guides.wknd.core.models.AuthorBooks;
import com.adobe.aem.guides.wknd.core.models.helper.BookDetails;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


@Model(adaptables = {SlingHttpServletRequest.class,Resource.class},adapters = AuthorBooks.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,resourceType = AuthorBooksImpl.RESOURCE_TYPE)
public class AuthorBooksImpl implements AuthorBooks
{
    protected static final String RESOURCE_TYPE = "wknd/components/author-books";

    @SlingObject
    Resource componentResource;

    @Inject
    @Required
    @Default(values="AUTHORNAME")
    String authorname;

    @ValueMapValue
    List<String> books;

    @ValueMapValue
    List<BookDetails> bookdetailswithmap;

    @PostConstruct
    public void init()
    {
        books=new ArrayList<String>(books)!=null?books:Collections.emptyList();

        bookdetailswithmap =new ArrayList<>();

        Resource res = componentResource.getChild("bookdetailswithmap");

        if(null != res && res.hasChildren()) {

            Iterator<Resource> cards = res.listChildren();

            while(cards.hasNext()) {

                Resource card = cards.next();

                BookDetails book1 = new BookDetails();

                book1.setBook(card.getValueMap().get("book", String.class));
                book1.setSubject(card.getValueMap().get("subject", String.class));
                book1.setPublished(card.getValueMap().get("published", String.class));
                bookdetailswithmap.add(book1);
    }
}
    }

    @Override
    public String getAuthorName() {
        
        return authorname;
    }

    @Override
    public List<String> getAuthorBooks() {
       
        return books;
    }

    @Override
    public List<BookDetails> getBookDetailsWithMap() {
        
        return bookdetailswithmap;
    }


    
}
