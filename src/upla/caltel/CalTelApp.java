/*
 * CalTelApp.java
 */

package upla.caltel;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import com.google.gdata.client.GoogleService;

/**
 * The main class of the application.
 */
public class CalTelApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    private GoogleService service;
    @Override protected void startup() {
        /*TODO  check it*/
        show (new ConnectionWindow(this));
        show(new CalTelView(this));
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

    public GoogleService getService()
      {
        return this.service;
      }
}
