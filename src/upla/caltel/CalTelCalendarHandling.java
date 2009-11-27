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
import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarQuery.ExtendedPropertyMatch;
import com.google.gdata.data.DateTime;



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
        for (i=0; i <calendarFeed.getEntries().size (); i++)
          {
            CalendarEntry entry = calendarFeed.getEntries().get(i);
            calendarList.addElement ((entry.getTitle()).getPlainText());
          }
      }
}
