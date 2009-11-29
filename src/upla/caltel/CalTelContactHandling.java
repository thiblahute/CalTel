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
import com.google.gdata.data.ParseSource;
import com.google.gdata.util.common.xml.XmlWriter;
import com.google.gdata.data.ExtensionProfile;

import java.net.URL;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.StringWriter;
import java.io.FileWriter;

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
            XmlWriter w = null;

            contactFeed =  service.getFeed(feedUrl, ContactFeed.class);

            /* file writing necessary */
            ExtensionProfile feedExtension = new ExtensionProfile();
            ExtensionProfile entryExtension = new ExtensionProfile();

            File userHome = (new File (System.getProperty("user.home")));
            BufferedWriter writer = new java.io.BufferedWriter(new FileWriter (new File (userHome, "contacts.xml")));
            w = new XmlWriter (writer);
            contactFeed.generateFeedStart(feedExtension, w, null);

            for (i=0; i <contactFeed.getEntries().size (); i++)
              {
                ContactEntry entry = contactFeed.getEntries().get(i);
                entry.generate(w, entryExtension);
                String email = null;
                if (entry.getEmailAddresses().size()>0)
                    email = " "+entry.getEmailAddresses().get(0).getAddress();
                else 
                  email = "";
                contactList.addElement ((entry.getTitle()).getPlainText()+email);
              }
           
            /* We close the feed hand write it to the local file*/
            contactFeed.generateFeedEnd (w);
            writer.write(writer.toString());
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
      }
    
    void loadFile (CalTelView view,  FileInputStream contactStream)
      {
        int i;
        ParseSource xmlParsing = new ParseSource (contactStream);
        ContactFeed contactFeed = new ContactFeed();
        javax.swing.DefaultListModel contactList = view.getContactList ();

        try
          {
            ExtensionProfile feedExtension = new ExtensionProfile();
            contactFeed.parseAtom (feedExtension, contactStream);
          }   
        catch (java.io.IOException exception)
          {
            System.out.println ("java.io.IOException");
          }
        catch (com.google.gdata.util.ParseException exception)
          {
            System.out.println ("com.google.gdata.util.ParseException");
          }
        catch (com.google.gdata.util.ServiceException exception)
          {
            System.out.println ("com.google.gdata.util.ServiceException");
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
