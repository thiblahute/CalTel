/** 
 * filename : CalTelCalendarHandling.java
 * @Copyright (C) Thibault Saunier 2009 <saunierthibault@gmail.com>
 *
 * @Author: Thibault Saunier, David Vargas, Carlos Lagos D.
 */

package upla.caltel;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.calendar.CalendarFeed;
import com.google.gdata.data.calendar.CalendarEntry;

import java.net.URL;

public class CalTelCalendarHandling 
{
    /** 
     *
     */
    public void setCalendarGui (CalTelView view, CalendarService service, String username)
      {
        int i;
        URL feedUrl = null;
        CalendarFeed calendarFeed;

        javax.swing.DefaultListModel calendarList = view.getDayEventsList ();

        /*Get calendar*/
        try
          {
            feedUrl = new URL("http://www.google.com/calendar/feeds/"+username.replace("@","%40")+"/private/full");
          }
        catch (java.net.MalformedURLException exception)
          {
          }
        calendarFeed = new CalendarFeed();
        calendarFeed.declareExtensions (service.getExtensionProfile ());
        try
          {
            calendarFeed =  service.getFeed(feedUrl, CalendarFeed.class);
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
        for (i=0; i <calendarFeed.getEntries().size (); i++)
          {
            CalendarEntry entry = calendarFeed.getEntries().get(i);
            calendarList.addElement ((entry.getTitle()).getPlainText());
          }
      }
}
