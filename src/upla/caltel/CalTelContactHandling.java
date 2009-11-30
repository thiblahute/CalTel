/** 
 * filename : CalTelContactHandling.java
 * @Copyright (C) Thibault Saunier 2009 <saunierthibault@gmail.com>
 *
 * @Author: Thibault Saunier, David Vargas, Carlos Lagos D.
 */

package upla.caltel;

import com.google.gdata.client.contacts.ContactsService;

import com.google.gdata.data.ParseSource;
import com.google.gdata.data.ExtensionProfile;

import com.google.gdata.data.contacts.ContactGroupFeed;
import com.google.gdata.data.contacts.ContactGroupEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.contacts.ContactEntry;

import com.google.gdata.util.common.xml.XmlWriter;

import java.net.URL;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.StringWriter;
import java.io.FileWriter;

/** 
 *  Handle evrything about Contacts
 */
public class CalTelContactHandling 
{
    /**
     *  Gets the contacts andd groups on internet and add it to groupList and contactList.
     *  @param service the ContactService which permit to do server's requests
     *  @param view the main gui of the calTel in order to add contacts to it.
     *  @param username the  username of the person for whose contact we are looking for
     * */
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
            groupFeed = new ContactGroupFeed();
            groupFeed.declareExtensions (service.getExtensionProfile ());
            groupFeed =  service.getFeed(feedUrl, ContactGroupFeed.class);
            for (i=0; i <groupFeed.getEntries().size (); i++)
              {
                ContactGroupEntry entry = groupFeed.getEntries().get(i);
                groupList.addElement ((entry.getTitle()).getPlainText());
              }
            feedUrl = new URL("http://www.google.com/m8/feeds/contacts/"+username.replace("@","%40")+"/full");

            contactFeed = new ContactFeed();
            contactFeed.declareExtensions (service.getExtensionProfile ());

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
            System.out.println ("Malformed URL");
          }
        catch (java.io.IOException exception)
          {
            System.out.println ("IOException");
          }
        catch (com.google.gdata.util.ServiceException exception)
          {
            System.out.println ("ServiceException");
          }
      }
    
    /**
     * load the file passed as parameter and show the contained contacst in the main GUI
     * @param view the main Gui where to add contact
     * @param contactStream the FileInputStream containing the xml datad
     * */
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
