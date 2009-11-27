/*
 * CalTelAboutBox.java
 */

package upla.caltel;

import org.jdesktop.application.Action;

/**
 * Manage the about windows
 **/
public class CalTelAboutBox extends javax.swing.JDialog {

    public CalTelAboutBox(java.awt.Frame parent) {
        super(parent);
        initComponents();
        getRootPane().setDefaultButton(closeButton);
    }

    @Action public void closeAboutBox() {
        dispose();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                closeButton = new javax.swing.JButton();
                javax.swing.JLabel appTitleLabel = new javax.swing.JLabel();
                javax.swing.JLabel versionLabel = new javax.swing.JLabel();
                javax.swing.JLabel appVersionLabel = new javax.swing.JLabel();
                javax.swing.JLabel vendorLabel = new javax.swing.JLabel();
                javax.swing.JLabel appVendorLabel = new javax.swing.JLabel();
                javax.swing.JLabel appDescLabel = new javax.swing.JLabel();
                javax.swing.JLabel imageLabel = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setModal(true);
                setName("aboutBox"); // NOI18N
                setResizable(false);

                org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(CalTelAboutBox.class);
                closeButton.setText(resourceMap.getString("closeButton.text")); // NOI18N
                closeButton.setName("closeButton"); // NOI18N
                closeButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                closeButtonActionPerformed(evt);
                        }
                });

                appTitleLabel.setFont(appTitleLabel.getFont().deriveFont(appTitleLabel.getFont().getStyle() | java.awt.Font.BOLD, appTitleLabel.getFont().getSize()+4));
                appTitleLabel.setName("appTitleLabel"); // NOI18N

                versionLabel.setFont(versionLabel.getFont().deriveFont(versionLabel.getFont().getStyle() | java.awt.Font.BOLD));
                versionLabel.setName("versionLabel"); // NOI18N

                appVersionLabel.setName("appVersionLabel"); // NOI18N

                vendorLabel.setFont(vendorLabel.getFont().deriveFont(vendorLabel.getFont().getStyle() | java.awt.Font.BOLD));
                vendorLabel.setName("vendorLabel"); // NOI18N

                appVendorLabel.setName("appVendorLabel"); // NOI18N

                appDescLabel.setName("appDescLabel"); // NOI18N

                imageLabel.setName("imageLabel"); // NOI18N

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(imageLabel)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(versionLabel)
                                                                        .addComponent(vendorLabel))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(appVendorLabel)
                                                                .addGap(76, 76, 76))
                                                        .addComponent(appTitleLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(appDescLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                                                        .addComponent(closeButton)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(24, 24, 24)
                                                .addComponent(appVersionLabel)))
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(appTitleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(appDescLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(versionLabel)
                                        .addComponent(appVersionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(vendorLabel)
                                        .addComponent(appVendorLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addComponent(closeButton)
                                .addContainerGap())
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

	private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
		
	}//GEN-LAST:event_closeButtonActionPerformed
    
        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton closeButton;
        // End of variables declaration//GEN-END:variables
    
}
