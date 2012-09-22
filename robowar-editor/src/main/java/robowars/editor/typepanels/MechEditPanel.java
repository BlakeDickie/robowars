/*
 * MechEditPanel.java
 *
 * Created on July 4, 2004, 10:42 PM
 */

package robowars.editor.typepanels;

/**
 *
 * @author  bdickie
 */
public class MechEditPanel extends CardDetailsEditor {
    
    /** Creates new form MechEditPanel */
    public MechEditPanel() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtGold = new javax.swing.JTextField();
        txtOre = new javax.swing.JTextField();
        txtArmour = new javax.swing.JTextField();
        cboSize = new javax.swing.JComboBox();
        txtHardpoints = new javax.swing.JTextField();
        txtTargets = new javax.swing.JTextField();
        cboJets = new javax.swing.JComboBox();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Gold Cost:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel2.setText("Ore Cost:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel3.setText("Armour:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel4.setText("Size:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel5.setText("Hardpoints:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

        jLabel6.setText("# Targets:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, -1));

        jLabel7.setText("Jump Jets");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));

        add(txtGold, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 70, -1));

        add(txtOre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 70, -1));

        add(txtArmour, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 70, -1));

        cboSize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Small", "Medium", "Large" }));
        add(cboSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        add(txtHardpoints, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 70, -1));

        add(txtTargets, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 70, -1));

        cboJets.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No", "Yes" }));
        add(cboJets, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, -1));

    }//GEN-END:initComponents

    public void loadDetails(java.sql.ResultSet rs) throws java.sql.SQLException {
        txtGold.setText(rs.getString("card_gold_cost"));
        txtOre.setText(rs.getString("card_ore_cost"));
        txtArmour.setText(rs.getString("card_armour"));
        txtHardpoints.setText(rs.getString("card_hardpoints"));
        txtTargets.setText(rs.getString("card_target_number"));
        
        cboSize.setSelectedIndex(rs.getInt("card_size"));
        cboJets.setSelectedIndex(rs.getInt("card_jump_jets"));
    }    
    
    public boolean saveDetails(java.util.Map values) {
        values.put("card_gold_cost", txtGold.getText());
        values.put("card_ore_cost", txtOre.getText());
        values.put("card_armour", txtArmour.getText());
        values.put("card_hardpoints", txtHardpoints.getText());
        values.put("card_target_number", txtTargets.getText());
        
        values.put("card_size", "" + cboSize.getSelectedIndex());
        values.put("card_jump_jets", "" + cboJets.getSelectedIndex());
        
        
        return true;
    }    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboJets;
    private javax.swing.JComboBox cboSize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtArmour;
    private javax.swing.JTextField txtGold;
    private javax.swing.JTextField txtHardpoints;
    private javax.swing.JTextField txtOre;
    private javax.swing.JTextField txtTargets;
    // End of variables declaration//GEN-END:variables
    
}