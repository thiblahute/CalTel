/** 
 * filename : CalTelCalendarHandling.java
 * @Copyright (C) Thibault Saunier 2009 <saunierthibault@gmail.com>
 *
 * @Author: Thibault Saunier, David Vargas, Carlos Lagos D.
 */

package upla.caltel;

import org.jdesktop.application.FrameView;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarQuery.ExtendedPropertyMatch;

import com.google.gdata.data.calendar.CalendarFeed;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.ParseSource;
import com.google.gdata.data.ExtensionProfile;

import com.google.gdata.util.common.xml.XmlWriter;

import java.net.URL;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.StringWriter;
import java.io.FileWriter;
/** 
 *  Handle evrything about calendar
 */
public class CalTelCalendarHandling 
{
    /**
     *  Gets the calendar internet and add it to calendarList.
     *  @param service the ContactService which permit to do server's requests
     *  @param view the main gui of the calTel in order to the calendar to it.
     *  @param username the  username of the person for whose calendar we are looking for
     * */
    public void setCalendarGui (CalTelView view, CalendarService service, String username)
      {
        int i;
        URL feedUrl = null;
        CalendarFeed calendarFeed;
        CalendarQuery query;

        javax.swing.DefaultListModel calendarList = view.getDayEventsList ();

        /*Get calendar*/
        try
          {
            feedUrl = new URL("http://www.google.com/calendar/feeds/"+username.replace("@","%40")+"/public/full");
            /*feedUrl = new URL("http://www.google.com/calendar/feeds/e55743g0e9co2a2261819g6898%40group.calendar.google.com/private/full");*/
          }
        catch (java.net.MalformedURLException exception)
          {
          }
        calendarFeed = new CalendarFeed();
        query = new CalendarQuery (feedUrl);
        //query.setMinimumStartTime ((new DateTime()).now());

        try
          {
            calendarFeed =  service.query(query, CalendarFeed.class);
              /*getFeed(feedUrl, CalendarFeed.class);*/
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

        XmlWriter w = null;
        ExtensionProfile feedExtension = new ExtensionProfile();
        ExtensionProfile entryExtension = new ExtensionProfile();
        try 
          {
            File userHome = (new File (System.getProperty("user.home")));
            BufferedWriter writer = new java.io.BufferedWriter(new FileWriter (new File (userHome, "calendar.xml")));
            w = new XmlWriter (writer);
            calendarFeed.generateFeedStart(feedExtension, w, null);
            for (i = 0; i < calendarFeed.getEntries().size (); i++)
              {
                CalendarEntry entry = calendarFeed.getEntries().get(i);
                entry.generate(w, entryExtension);
                calendarList.addElement ((entry.getTitle()).getPlainText());
              }

            /* We close the feed hand write it to the local file*/
            calendarFeed.generateFeedEnd (w);
            writer.write(writer.toString());
          }
        catch (java.io.IOException exception)
          {
            System.out.println ("java.io.IOException");
          }

        /*System.out.println (writer.toString());*/

      ;}

    /**
     * load the file passed as parameter and show the contained calendar in the main GUI
     * @param view the main Gui where to add the calendar
     * @param calStream the FileInputStream containing the xml data
     * */
    void loadFile (CalTelView view,  FileInputStream calStream)
      {
        int i;
        CalendarFeed calendarFeed = new CalendarFeed();
        javax.swing.DefaultListModel calendarList = view.getDayEventsList ();

        try
          {
            /*calendarFeed.readFeed (xmlParsing);*/
            ExtensionProfile feedExtension = new ExtensionProfile();
            calendarFeed.parseAtom (feedExtension,calStream);
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
        System.out.println ("Entris NB:"+calendarFeed.getEtag() );
        for (i = 0; i < calendarFeed.getEntries().size (); i++)
          {
            CalendarEntry entry = calendarFeed.getEntries().get(i);
            System.out.println (entry.getTitle().getPlainText());
            calendarList.addElement ((entry.getTitle()).getPlainText());
          }

      }
}
