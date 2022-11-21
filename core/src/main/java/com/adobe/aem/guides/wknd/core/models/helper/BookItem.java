package com.adobe.aem.guides.wknd.core.models.helper;

import java.util.Date;

public class BookItem 
{
    String title2;
    Date date;
    String description;
    int copies;
   public String getTitle2()
   {
       return title2;
   }

   public void setTitle2(String title2)
   {
         this.title2=title2;
   }

   public Date getDate()
   {
       return date;
   }

   public void setDate(Date date)
   {
         this.date=date;
   }

   public String getDescription()
   {
       return description;
   }

   public void setDescription(String description)
   {
         this.description=description;
   }

   public int getCopies()
   {
       return copies;
   }

   public void setCopies(int copies)
   {
         this.copies=copies;
   }

}
