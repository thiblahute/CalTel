/** 
 * filename : CalTelContactHandling.java
 * @Copyright (C) Thibault Saunier 2009 <saunierthibault@gmail.com>
 *
 * @Author: Thibault Saunier <saunierthibault@gmail.com>
 */

package upla.caltel;

import com.google.gdata.client.*;
import com.google.gdata.client.calendar.*;
import com.google.gdata.data.*;
import com.google.gdata.data.acl.*;
import com.google.gdata.data.calendar.*;
import com.google.gdata.util.*;
import com.google.gdata.data.DateTime;
import java.net.URL;
import java.util.ArrayList;

public class CalTelCalendarHandling 
{
    /** 
     *
     */
    public CalendarFeed GetCalendarFeed (CalTelApp app)
    {
        URL feedUrl = null;
        CalendarFeed resultFeed = null;

        try 
          {
            feedUrl = new URL("http://www.google.com/calendar/feeds/default/allcalendars/full");
          }
        catch (java.net.MalformedURLException exception) 
          {
            System.out.println("java.io.IOException");
          }

        try 
          {
            /* Get the feed*/
            resultFeed = (app.getService()).getFeed(feedUrl, CalendarFeed.class);
          }
        catch (java.io.IOException exception)
          {
            System.out.println("java.io.IOException");
          }
        catch (com.google.gdata.util.ServiceException exception)
          {
            System.out.println("java.io.IOException");
          }

        return resultFeed;
    }
}
