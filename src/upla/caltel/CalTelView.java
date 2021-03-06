/**
 * CalTelView.java
 * @Author: Thibault Saunier, David Vargas, Carlos Lagos D.
 */

package upla.caltel;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import com.toedter.calendar.JCalendar;


/**
 * The application's main frame.
 */
public class CalTelView extends FrameView 
{

    /**
     * Instanciate the main app
     */
    public CalTelView(SingleFrameApplication app) 
      {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() 
                    {
                      public void actionPerformed(ActionEvent e) {
                    }
                    });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) 
          {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
          }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() 
                    {
                    public void actionPerformed(ActionEvent e) 
                    {
                    busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                    }
                    });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() 
                    {
                    public void propertyChange(java.beans.PropertyChangeEvent evt) 
                    {
                    String propertyName = evt.getPropertyName();
                    if ("started".equals(propertyName)) 
                    {
                    if (!busyIconTimer.isRunning()) 
                    {
                    busyIconIndex = 0;
                    busyIconTimer.start();
                    }
                    } else if ("done".equals(propertyName)) 
                    {
                    busyIconTimer.stop();
                    } else if ("message".equals(propertyName)) 
                    {
                    String text = (String)(evt.getNewValue());
                    messageTimer.restart();
                    }
                    else if ("progress".equals(propertyName)) 
                      {
                        int value = (Integer)(evt.getNewValue());
                      }
                    }
                    });
      }

    @Action
      public void showAboutBox() {
          if (aboutBox == null) {
              JFrame mainFrame = CalTelApp.getApplication().getMainFrame();
              aboutBox = new CalTelAboutBox(mainFrame);
              aboutBox.setLocationRelativeTo(mainFrame);
          }
          CalTelApp.getApplication().show(aboutBox);
      }

    @SuppressWarnings("unchecked")

      private void initComponents() {
          mainPanel = new javax.swing.JPanel();
          mainTabs = new javax.swing.JTabbedPane();
          phoneGuidePanel = new javax.swing.JPanel();
          groupsPanel = new javax.swing.JPanel();
          groupsToolBar = new javax.swing.JToolBar();
          groupsScrollPane = new javax.swing.JScrollPane();
          groupsList = new javax.swing.DefaultListModel();
          jGroupList = new javax.swing.JList();
          contactsPanel = new javax.swing.JPanel();
          contactsScrollPane = new javax.swing.JScrollPane();
          contactList = new javax.swing.DefaultListModel();
          jContactList = new javax.swing.JList();
          phoneGuideContactsToolBar = new javax.swing.JToolBar();
          calendarPanel = new javax.swing.JPanel();
          calendarToolBar = new javax.swing.JToolBar();
          contactsScrollPane1 = new javax.swing.JScrollPane();
          dayEventsList = new javax.swing.DefaultListModel();
          jDayEventList = new javax.swing.JList();
          jCalendar1 = new com.toedter.calendar.JCalendar();
          jCalendar2 = new com.toedter.calendar.JCalendar();
          menuBar = new javax.swing.JMenuBar();
          javax.swing.JMenu fileMenu = new javax.swing.JMenu();
          javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
          javax.swing.JMenu helpMenu = new javax.swing.JMenu();
          javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();

          mainPanel.setMinimumSize(new java.awt.Dimension(1029, 684));
          mainPanel.setName("mainPanel"); // NOI18N

          mainTabs.setName("mainTabs"); // NOI18N

          phoneGuidePanel.setName("phoneGuidePanel"); // NOI18N

          groupsPanel.setName("groupsPanel"); // NOI18N

          groupsToolBar.setRollover(true);
          groupsToolBar.setName("groupsToolBar"); // NOI18N

          groupsScrollPane.setName("groupsScrollPane"); // NOI18N

          jGroupList.setModel(groupsList);
          jGroupList.setName("jGourpsList"); // NOI18N
          groupsScrollPane.setViewportView(jGroupList);

          javax.swing.GroupLayout groupsPanelLayout = new javax.swing.GroupLayout(groupsPanel);
          groupsPanel.setLayout(groupsPanelLayout);
          groupsPanelLayout.setHorizontalGroup
          (
              groupsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(groupsToolBar, 
                                                                  javax.swing.GroupLayout.Alignment.TRAILING, 
                                                                  javax.swing.GroupLayout.DEFAULT_SIZE, 
                                                                  139, 
                                                                  Short.MAX_VALUE)
                                                    .addComponent(groupsScrollPane, 
                                                                  javax.swing.GroupLayout.DEFAULT_SIZE, 
                                                                  139, 
                                                                  Short.MAX_VALUE)
          );
          groupsPanelLayout.setVerticalGroup
          (
                      groupsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                      .addGroup(groupsPanelLayout.createSequentialGroup()
                                      .addComponent(groupsToolBar, 
                                                    javax.swing.GroupLayout.PREFERRED_SIZE, 
                                                    25, 
                                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                      .addComponent(groupsScrollPane, 
                                                    javax.swing.GroupLayout.DEFAULT_SIZE, 
                                                    594, 
                                                    Short.MAX_VALUE))
          );

          contactsPanel.setName("contactsPanel"); // NOI18N

          contactsScrollPane.setName("contactsScrollPane"); // NOI18N

          jContactList.setModel(contactList);
          jContactList.setName("jContactList"); // NOI18N
          contactsScrollPane.setViewportView(jContactList);

          phoneGuideContactsToolBar.setRollover(true);
          phoneGuideContactsToolBar.setName("phoneGuideContactsToolBar"); // NOI18N

          javax.swing.GroupLayout contactsPanelLayout = new javax.swing.GroupLayout(contactsPanel);
          contactsPanel.setLayout(contactsPanelLayout);
          contactsPanelLayout.setHorizontalGroup
          (
            contactsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(phoneGuideContactsToolBar, 
                                    javax.swing.GroupLayout.DEFAULT_SIZE, 
                                    852, 
                                    Short.MAX_VALUE)
                      .addComponent(contactsScrollPane, 
                                    javax.swing.GroupLayout.DEFAULT_SIZE, 
                                    852, 
                                    Short.MAX_VALUE)
          );
          contactsPanelLayout.setVerticalGroup
          (
            contactsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addGroup(contactsPanelLayout.createSequentialGroup()
                      .addComponent(phoneGuideContactsToolBar, 
                                    javax.swing.GroupLayout.PREFERRED_SIZE, 
                                    25, 
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addComponent(contactsScrollPane, 
                                    javax.swing.GroupLayout.DEFAULT_SIZE, 
                                    594, 
                                    Short.MAX_VALUE)
                      .addContainerGap())
          );

          javax.swing.GroupLayout phoneGuidePanelLayout = new javax.swing.GroupLayout(phoneGuidePanel);
          phoneGuidePanel.setLayout(phoneGuidePanelLayout);
          phoneGuidePanelLayout.setHorizontalGroup
          (
            phoneGuidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addGroup(phoneGuidePanelLayout.createSequentialGroup()
                      .addComponent(groupsPanel, 
                                    javax.swing.GroupLayout.PREFERRED_SIZE, 
                                    javax.swing.GroupLayout.DEFAULT_SIZE, 
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addComponent(contactsPanel, 
                                    javax.swing.GroupLayout.DEFAULT_SIZE, 
                                    javax.swing.GroupLayout.DEFAULT_SIZE, 
                                    Short.MAX_VALUE))
          );
          phoneGuidePanelLayout.setVerticalGroup
          (
            phoneGuidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addGroup(phoneGuidePanelLayout.createSequentialGroup()
                      .addComponent(groupsPanel,
                                    javax.swing.GroupLayout.DEFAULT_SIZE, 
                                    javax.swing.GroupLayout.DEFAULT_SIZE, 
                                    Short.MAX_VALUE)
                      .addContainerGap())
                      .addComponent(contactsPanel, 
                                    javax.swing.GroupLayout.DEFAULT_SIZE, 
                                    javax.swing.GroupLayout.DEFAULT_SIZE, 
                                    Short.MAX_VALUE)
                      );

          org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance()
                                                                .getContext().getResourceMap(CalTelView.class);
          mainTabs.addTab(resourceMap.getString("phoneGuidePanel.TabConstraints.tabTitle"), 
                          phoneGuidePanel);

          calendarPanel.setName("calendarPanel");

          calendarToolBar.setRollover(true);
          calendarToolBar.setName("calendarToolBar"); 

          contactsScrollPane1.setName("contactsScrollPane1"); 

          jDayEventList.setModel(dayEventsList);
          jDayEventList.setMinimumSize(new java.awt.Dimension(232, 800));
          jDayEventList.setName("dayEventsList");
          contactsScrollPane1.setViewportView(jDayEventList);

          jCalendar1.setName("jCalendar1");
          jCalendar1.addMouseListener(
                      new java.awt.event.MouseAdapter() 
                      {
                      public void mouseClicked(java.awt.event.MouseEvent evt) 
                      {
                      jCalendar1MouseClicked(evt);
                      }
                      });

          jCalendar2.setName("jCalendar2");

          javax.swing.GroupLayout calendarPanelLayout = new javax.swing.GroupLayout(calendarPanel);
          calendarPanel.setLayout(calendarPanelLayout);
          calendarPanelLayout.setHorizontalGroup
          (
            calendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addGroup(calendarPanelLayout.createSequentialGroup()
                      .addGroup(calendarPanelLayout.createParallelGroup
                                        (javax.swing.GroupLayout.Alignment.TRAILING)
                      .addComponent(jCalendar2, 
                                    javax.swing.GroupLayout.DEFAULT_SIZE, 
                                    310, 
                                    Short.MAX_VALUE)
                      .addGroup(calendarPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(jCalendar1, 
                                    javax.swing.GroupLayout.DEFAULT_SIZE, 
                                    298, 
                                    Short.MAX_VALUE)))
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(contactsScrollPane1, 
                                     javax.swing.GroupLayout.DEFAULT_SIZE, 
                                     800, 
                                     Short.MAX_VALUE)
                       .addContainerGap())
                      .addComponent(calendarToolBar, 
                                    javax.swing.GroupLayout.Alignment.TRAILING, 
                                    javax.swing.GroupLayout.DEFAULT_SIZE, 
                                    997, 
                                    Short.MAX_VALUE)
           );
           calendarPanelLayout.setVerticalGroup(
                      calendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addGroup(calendarPanelLayout.createSequentialGroup()
                          .addComponent(calendarToolBar, 
                                        javax.swing.GroupLayout.PREFERRED_SIZE, 
                                        25, 
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                          .addGap(127, 127, 127)
                          .addGroup(calendarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addGroup(calendarPanelLayout.createSequentialGroup()
                          .addComponent(jCalendar1, 
                                        javax.swing.GroupLayout.PREFERRED_SIZE, 
                                        182, 
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                          .addGap(2, 2, 2)
                          .addComponent(jCalendar2,
                                        javax.swing.GroupLayout.PREFERRED_SIZE, 
                                        203, 
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                          .addContainerGap())
                           .addComponent(contactsScrollPane1, 
                                         javax.swing.GroupLayout.DEFAULT_SIZE, 
                                         485, 
                                         Short.MAX_VALUE)))
          );

          mainTabs.addTab(resourceMap.getString("calendarPanel.TabConstraints.tabTitle"), calendarPanel);

          javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
          mainPanel.setLayout(mainPanelLayout);
          mainPanelLayout.setHorizontalGroup
          (
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                 mainPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(mainTabs, 
                                    javax.swing.GroupLayout.DEFAULT_SIZE, 
                                    1005, 
                                    Short.MAX_VALUE)
                      .addContainerGap())
          );
          mainPanelLayout.setVerticalGroup
          (
                      mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(mainTabs, 
                                    javax.swing.GroupLayout.DEFAULT_SIZE,
                                    672, 
                                    Short.MAX_VALUE))
          );

          menuBar.setName("menuBar");

          fileMenu.setText(resourceMap.getString("fileMenu.text"));
          fileMenu.setName("fileMenu");

          exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, 0));
          exitMenuItem.setText(resourceMap.getString("exitMenuItem.text"));
          exitMenuItem.setName("exitMenuItem");
          exitMenuItem.addMouseListener(new java.awt.event.MouseAdapter() 
            {
                public void mouseClicked(java.awt.event.MouseEvent evt) 
                {
                    exitMenuItemMouseClicked(evt);
                }
            });
          fileMenu.add(exitMenuItem);

          menuBar.add(fileMenu);

          helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
          helpMenu.setName("helpMenu"); // NOI18N

          aboutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, 0));
          aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
          aboutMenuItem.setName("aboutMenuItem"); // NOI18N
          aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
                      public void actionPerformed(java.awt.event.ActionEvent evt) {
                      aboutMenuItemActionPerformed(evt);
                      }
                      });
          helpMenu.add(aboutMenuItem);

          menuBar.add(helpMenu);

          setComponent(mainPanel);
          setMenuBar(menuBar);
          setStatusBar(calendarToolBar);
          setToolBar(calendarToolBar);
      }// </editor-fold>//GEN-END:initComponents

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void exitMenuItemMouseClicked(java.awt.event.MouseEvent evt) {
        System.out.println ("QUITING");
        System.exit(0);
    }

    private void jCalendar1MouseClicked(java.awt.event.MouseEvent evt)
      {
        System.out.println ("CalendarClicked");
      }          

    public javax.swing.DefaultListModel getGroupsList()
      {
        return groupsList;
      }
    public javax.swing.DefaultListModel getContactList()
      {
        return contactList;
      }
    public javax.swing.DefaultListModel getDayEventsList ()
      {
        return this.dayEventsList;
      }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel calendarPanel;
    private javax.swing.JToolBar calendarToolBar;
    private javax.swing.JList jContactList;
    private javax.swing.JPanel contactsPanel;
    private javax.swing.JScrollPane contactsScrollPane;
    private javax.swing.JScrollPane contactsScrollPane1;
    private javax.swing.JList jDayEventList;
    private javax.swing.JList jGroupList;
    private javax.swing.JPanel groupsPanel;
    private javax.swing.JScrollPane groupsScrollPane;
    private javax.swing.JToolBar groupsToolBar;
    private com.toedter.calendar.JCalendar jCalendar1;
    private com.toedter.calendar.JCalendar jCalendar2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTabbedPane mainTabs;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JToolBar phoneGuideContactsToolBar;
    private javax.swing.JPanel phoneGuidePanel;
    private javax.swing.DefaultListModel groupsList;
    private javax.swing.DefaultListModel contactList;
    private javax.swing.DefaultListModel dayEventsList;
    // End of variables declaration//GEN-END:variables

    private JCalendar Calendar;

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
