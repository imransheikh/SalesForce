package com.salesforcetest.shared;

public class Compliance
{
   private String message;
   
   private String attachment;
   
   private String subject;
   
   public Compliance(String message, String subject, String attachment)
   {
      this.message = message;
      this.attachment = attachment;
      this.subject = subject;
   }

   public String getMessage()
   {
      return message;
   }

   public void setMessage(String message)
   {
      this.message = message;
   }

   public String getAttachment()
   {
      return attachment;
   }

   public void setAttachment(String attachment)
   {
      this.attachment = attachment;
   }

   public String getSubject()
   {
      return subject;
   }

   public void setSubject(String subject)
   {
      this.subject = subject;
   }
   
}
