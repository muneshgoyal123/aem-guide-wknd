package com.adobe.aem.guides.wknd.core.models.dto;

public class UserInformation 
{
    String userClass;
    String designation;
    String age;

    public String getUserClass()
    {
        return userClass;
    }
 
    public void setUserClass(String userClass)
    {
          this.userClass=userClass;
    }

    public String getDesignation()
    {
        return designation;
    }
 
    public void setDesignation(String designation)
    {
          this.designation=designation;
    }

    public String getAge()
    {
        return age;
    }
 
    public void setAge(String age)
    {
          this.age=age;
    }


}
