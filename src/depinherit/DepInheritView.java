 /*
 * DepCalcViewView.java
 */

package depinherit;

import business.*;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;


/**
 * The application's main frame.
 */
public class DepInheritView extends FrameView {
    AssetSL asl;
    AssetDDL addl;
    Asset15DL a15dl;

    public DepInheritView(SingleFrameApplication app) {
        super(app);

        initComponents();
        jtblSched.setName(null); // ensures the table state errod does not appear
        
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = DepInheritApp.getApplication().getMainFrame();
            aboutBox = new DepInheritAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        DepInheritApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtxtAssetNm = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtxtCost = new javax.swing.JTextField();
        jtxtSalvage = new javax.swing.JTextField();
        jtxtLife = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jbtnClear = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jradSL = new javax.swing.JRadioButton();
        jradDDL = new javax.swing.JRadioButton();
        jbtnCalc = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblSched = new javax.swing.JTable();
        jrad15DL = new javax.swing.JRadioButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        jmnuSave = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        buttonGroup1 = new javax.swing.ButtonGroup();

        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(depinherit.DepInheritApp.class).getContext().getResourceMap(DepInheritView.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jtxtAssetNm.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtAssetNm.setText(resourceMap.getString("jtxtAssetNm.text")); // NOI18N
        jtxtAssetNm.setName("jtxtAssetNm"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jtxtCost.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtCost.setText(resourceMap.getString("jtxtCost.text")); // NOI18N
        jtxtCost.setName("jtxtCost"); // NOI18N

        jtxtSalvage.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtSalvage.setText(resourceMap.getString("jtxtSalvage.text")); // NOI18N
        jtxtSalvage.setName("jtxtSalvage"); // NOI18N

        jtxtLife.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtLife.setText(resourceMap.getString("jtxtLife.text")); // NOI18N
        jtxtLife.setName("jtxtLife"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jbtnClear.setText(resourceMap.getString("jbtnClear.text")); // NOI18N
        jbtnClear.setName("jbtnClear"); // NOI18N
        jbtnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnClearActionPerformed(evt);
            }
        });

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        buttonGroup1.add(jradSL);
        jradSL.setText(resourceMap.getString("jradSL.text")); // NOI18N
        jradSL.setName("jradSL"); // NOI18N

        buttonGroup1.add(jradDDL);
        jradDDL.setText(resourceMap.getString("jradDDL.text")); // NOI18N
        jradDDL.setName("jradDDL"); // NOI18N

        jbtnCalc.setText(resourceMap.getString("jbtnCalc.text")); // NOI18N
        jbtnCalc.setName("jbtnCalc"); // NOI18N
        jbtnCalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCalcActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jtblSched.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtblSched.setName("jtblSched"); // NOI18N
        jScrollPane1.setViewportView(jtblSched);

        buttonGroup1.add(jrad15DL);
        jrad15DL.setText(resourceMap.getString("jrad15DL.text")); // NOI18N
        jrad15DL.setName("jrad15DL"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtxtAssetNm, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtCost, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel2)))
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jtxtSalvage, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtxtLife, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbtnClear))))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jradSL)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jradDDL)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addComponent(jrad15DL)
                        .addGap(96, 96, 96))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnCalc)
                .addGap(225, 225, 225))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtAssetNm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtSalvage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtLife, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnClear))
                .addGap(37, 37, 37)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jradSL)
                    .addComponent(jradDDL)
                    .addComponent(jrad15DL))
                .addGap(6, 6, 6)
                .addComponent(jbtnCalc)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        jmnuSave.setText(resourceMap.getString("jmnuSave.text")); // NOI18N
        jmnuSave.setName("jmnuSave"); // NOI18N
        fileMenu.add(jmnuSave);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(depinherit.DepInheritApp.class).getContext().getActionMap(DepInheritView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 362, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCalcActionPerformed
        statusMessageLabel.setText("");
        double cost, salvage;
        int life;
        String dtype = "";
        
        
        if (jtxtAssetNm.getText().isEmpty()) {
            statusMessageLabel.setText("Missing Asset Name.");
            jtxtAssetNm.requestFocusInWindow();
            return;
        }
       
        try {
             cost = Double.parseDouble(jtxtCost.getText());
        } catch(NumberFormatException e) {
            statusMessageLabel.setText("Cost error: " + e.getMessage());
            jtxtCost.requestFocusInWindow();
            return; 
            
        }
       
        try {
            salvage = Double.parseDouble(jtxtSalvage.getText());
        } catch(NumberFormatException e) {
            statusMessageLabel.setText("Salvage error " + e.getMessage());
            jtxtSalvage.requestFocusInWindow();
            return;
        }
        
        try {
            life = Integer.parseInt(jtxtLife.getText());
        } catch(NumberFormatException e) {
            statusMessageLabel.setText("Life error: " + e.getMessage());
            jtxtLife.requestFocusInWindow();
            return;
        }
        
        if(jradSL.isSelected()) {
            asl = new AssetSL(jtxtAssetNm.getText(), cost, salvage,life);
            if (!asl.getErrorMsg().isEmpty()) {
            statusMessageLabel.setText(asl.getErrorMsg());
            return;
       
           
       }
        } else if (jradDDL.isSelected()) {
            addl = new AssetDDL(jtxtAssetNm.getText(), cost, salvage, life);
            if (!addl.getErrorMsg().isEmpty()) {
            statusMessageLabel.setText(addl.getErrorMsg());
            return;
       
        }
        } else if (jrad15DL.isSelected()) {
            a15dl = new Asset15DL(jtxtAssetNm.getText(), cost, salvage, life);
            if (!a15dl.getErrorMsg().isEmpty()) {
            statusMessageLabel.setText(a15dl.getErrorMsg());
            return;
              
            }
          
            
        } else {
            statusMessageLabel.setText("Unknown Depreciation Method");
            return ;
        }
        
     
     
       String[] cols = {"Year", "Beg.Bal.", "Ann.Dep.", "End.Bal." };
       String[][] t= new String[life] [4]; // All cells
       DefaultTableModel mod = new DefaultTableModel(t,cols);
       jtblSched.setModel(mod);
       // Additional extra credit: right-justify the table columns...
       NumberFormat curr = NumberFormat.getCurrencyInstance();
       for (int i=1; i <= life; i++) {
           jtblSched.setValueAt(i, i-1, 0);
           if (jradDDL.isSelected()) {
           jtblSched.setValueAt(curr.format(addl.getBegBal(i)), i-1, 1);
           jtblSched.setValueAt(curr.format(addl.getAnnDep(i)), i-1, 2);
           jtblSched.setValueAt(curr.format(addl.getEndBal(i)), i-1, 3);
           } else if (jradSL.isSelected()) {
           jtblSched.setValueAt(curr.format(asl.getBegBal(i)), i-1, 1);
           jtblSched.setValueAt(curr.format(asl.getAnnDepr(i)), i-1, 2);
           jtblSched.setValueAt(curr.format(asl.getEndBal(i)), i-1, 3);
         
           } else if (jrad15DL.isSelected())  {
           jtblSched.setValueAt(curr.format(a15dl.getBegBal(i)), i-1, 1);
           jtblSched.setValueAt(curr.format(a15dl.getAnnDep(i)), i-1, 2);
           jtblSched.setValueAt(curr.format(a15dl.getEndBal(i)), i-1, 3);
           
           } else {
               statusMessageLabel.setText(" Unknown Type. ");
               return;
           }
       }// end for for
       
      
       
                
         
    }//GEN-LAST:event_jbtnCalcActionPerformed

    private void jbtnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnClearActionPerformed
        statusMessageLabel.setText("");
        jtxtAssetNm.setText("");
        jtxtCost.setText("");
        jtxtSalvage.setText("");
        jtxtLife.setText("");
        buttonGroup1.clearSelection();
        jtblSched.setModel(new DefaultTableModel());
        jtxtAssetNm.requestFocusInWindow();
        
    }//GEN-LAST:event_jbtnClearActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnCalc;
    private javax.swing.JButton jbtnClear;
    private javax.swing.JMenuItem jmnuSave;
    private javax.swing.JRadioButton jrad15DL;
    private javax.swing.JRadioButton jradDDL;
    private javax.swing.JRadioButton jradSL;
    private javax.swing.JTable jtblSched;
    private javax.swing.JTextField jtxtAssetNm;
    private javax.swing.JTextField jtxtCost;
    private javax.swing.JTextField jtxtLife;
    private javax.swing.JTextField jtxtSalvage;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}