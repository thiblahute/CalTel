/*
 * CalTelApp.java
 * @Author: Thibault Saunier, David Vargas, Carlos Lagos D.
 */

package upla.caltel;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.client.contacts.ContactsService;

/**
 * The main class of the application.
 */
public class CalTelApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    private CalendarService calendarService;
    private ContactsService contactService;
    private CalTelView calTelView;
    private CalTelContactHandling calTelContactHandling;
    private CalTelCalendarHandling calTelCalendarHandling;
    private String username;
    private String contactFileName;

    /**
     * Shows the connection window and the CalTelView and sets a few attributes
     * */
    @Override protected void startup() {
        System.out.println (System.getProperty("user.home"));
        this.contactService = new ContactsService("CalTel");
        this.calendarService = new CalendarService("CalTel");
        this.calTelView = new CalTelView(this);
        show (this.calTelView);
        show (new conexionwindow (this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of CalTelApp
     */
    public static CalTelApp getApplication() {
        return Application.getInstance(CalTelApp.class);
    }


    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(CalTelApp.class, args);
    }

    /**
     * Service accesor
     * @return the ContactService attribute
     * */
    public ContactsService getContactService()
      {
        return this.contactService;
      }

    /**
     * Service accesor
     * @return the calendarService attribute
     * */
    public CalendarService getCalendarService()
      {
        return this.calendarService;
      }

    /**
     * calTelView accesor
     * @return the calTelView attribute
     * */
    public CalTelView getCalTelView ()
      {
        return this.calTelView;
      }

    /**
     * calTelContactHandling accesor
     * @return the CalTelContactHandling attribute
     * */
    public CalTelContactHandling getCalTelContactHandling ()
      {
        return this.calTelContactHandling;
      }
    public CalTelCalendarHandling getCalTelCalendarHandling()
      {
        return this.calTelCalendarHandling;
      }

    /**
     * calTelContactHandling seter
     * @param _contactHandling the CalTelContactHandling to set 
     * */
    public void setCalTelContactHandling (CalTelContactHandling _contactHandling)
      {
        this.calTelContactHandling = _contactHandling;
      }

    /**
     * calTelCalendarHandling seter
     * @param _calendarHandling the CalTelCalendarHandling to set 
     * */
    public void setCalTelCalendarHandling (CalTelCalendarHandling _calendarHandling)
      {
        this.calTelCalendarHandling = _calendarHandling;
      }

    /**
     * Username accesor
     * @return the username
     * */
    public String getUsername ()
      {
        return this.username;
      }
    public void setUsername (String _username)
      {
        this.username = _username;
      }
}
