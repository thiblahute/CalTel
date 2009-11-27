/** 
 * filename : CalTelContactHandling.java
 * @Copyright (C) Thibault Saunier 2009 <saunierthibault@gmail.com>
 *
 * @Author: Thibault Saunier, David Vargas, Carlos Lagos D.
 */

package upla.caltel;

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactGroupFeed;
import com.google.gdata.data.contacts.ContactGroupEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.contacts.ContactEntry;

import java.net.URL;

public class CalTelContactHandling 
{
    /** 
     *  Handle evrything about Contacts
     */
    public void setContactGui (ContactsService service, CalTelView view, String username)
      {
        int i;
        URL feedUrl = null;
        ContactGroupEntry groupEntry; 
        ContactGroupFeed groupFeed;
        ContactFeed contactFeed;

        javax.swing.DefaultListModel groupList = view.getGroupsList ();
        javax.swing.DefaultListModel contactList = view.getContactList ();

        /*Get groups*/
        try
          {
            feedUrl = new URL("http://www.google.com/m8/feeds/groups/"+username.replace("@","%40")+"/full");
          }
        catch (java.net.MalformedURLException exception)
          {
          }
        groupFeed = new ContactGroupFeed();
        groupFeed.declareExtensions (service.getExtensionProfile ());
        try
          {
            groupFeed =  service.getFeed(feedUrl, ContactGroupFeed.class);
          }
        catch (java.net.MalformedURLException exception)
          {
          }
        catch (java.io.IOException exception)
          {
          }
        catch (com.google.gdata.util.ServiceException exception)
          {
          }
        for (i=0; i <groupFeed.getEntries().size (); i++)
          {
            ContactGroupEntry entry = groupFeed.getEntries().get(i);
            groupList.addElement ((entry.getTitle()).getPlainText());
          }

        /*Get contacts from first group*/
        try
          {
            feedUrl = new URL("http://www.google.com/m8/feeds/contacts/"+username.replace("@","%40")+"/full");
          }
        catch (java.net.MalformedURLException exception)
          {
          }
        contactFeed = new ContactFeed();
        contactFeed.declareExtensions (service.getExtensionProfile ());
        try
          {
            contactFeed =  service.getFeed(feedUrl, ContactFeed.class);
          }
        catch (java.net.MalformedURLException exception)
          {
          }
        catch (java.io.IOException exception)
          {
          }
        catch (com.google.gdata.util.ServiceException exception)
          {
          }
        for (i=0; i <contactFeed.getEntries().size (); i++)
          {
            ContactEntry entry = contactFeed.getEntries().get(i);
            String email = null;
            if (entry.getEmailAddresses().size()>0)
                email = " "+entry.getEmailAddresses().get(0).getAddress();
            else 
                email = "";
            contactList.addElement ((entry.getTitle()).getPlainText()+email);
          }
      }
}
