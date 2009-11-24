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
import java.net.URL;

public class CalTelContactHandling 
{
    /** 
     *
     *
    public static void main(String[]  
    {
        CalendarService myService = new CalendarService("exampleCo-exampleApp-1.0");
        URL feedUrl = null;
        CalendarFeed resultFeed = null;

        try {
            myService.setUserCredentials("gvfs.gdata@gmail.com", "gvfsgvfs");
        } catch ( com.google.gdata.util.AuthenticationException exception) {
            System.out.println("com.google.gdata.util.AuthenticationException");
        }va

        try {
            feedUrl = new URL("http://www.google.com/calendar/feeds/default/allcalendars/full");
        } catch (java.net.MalformedURLException exception) {
            System.out.println("java.io.IOException");
        }

        try {
            resultFeed = myService.getFeed(feedUrl, CalendarFeed.class);
        } catch (java.io.IOException exception){
            System.out.println("java.io.IOException");
        } catch (com.google.gdata.util.ServiceException exception){
            System.out.println("java.io.IOException");
        }

        System.out.println("Your calendars:");
        System.out.println();

        for (int i = 0; i < resultFeed.getEntries().size(); i++) 
          {
            CalendarEntry entry = resultFeed.getEntries().get(i);
            System.out.println("\t" + entry.getTitle().getPlainText());
          }
    }*/

}
// vim: ft=java

