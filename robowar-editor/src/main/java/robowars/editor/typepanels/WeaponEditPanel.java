/*
 * WeaponEditPanel.java
 *
 * Created on July 4, 2004, 11:03 PM
 */

package robowars.editor.typepanels;

/**
 *
 * @author  bdickie
 */
public class WeaponEditPanel extends CardDetailsEditor {
    
    /** Creates new form WeaponEditPanel */
    public WeaponEditPanel() {
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
        txtOre = new javax.swing.JTextField();
        txtGold = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtShotCost = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDamage = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtHardpoints = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Gold Cost:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel2.setText("Ore Cost:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        add(txtOre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 70, -1));

        add(txtGold, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 70, -1));

        jLabel3.setText("Shot Cost:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, -1));

        add(txtShotCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 70, -1));

        jLabel4.setText("Damage:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, -1, -1));

        add(txtDamage, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 70, -1));

        jLabel5.setText("Hardpoints:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));

        add(txtHardpoints, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 70, -1));

    }//GEN-END:initComponents

    public void loadDetails(java.sql.ResultSet rs) throws java.sql.SQLException {
        txtGold.setText(rs.getString("card_gold_cost"));
        txtOre.setText(rs.getString("card_ore_cost"));
        txtShotCost.setText(rs.getString("card_shot_cost"));
        txtHardpoints.setText(rs.getString("card_hardpoints"));
        txtDamage.setText(rs.getString("card_damage"));
    }    
    
    public boolean saveDetails(java.util.Map values) {
        values.put("card_gold_cost", txtGold.getText());
        values.put("card_ore_cost", txtOre.getText());
        values.put("card_shot_cost", txtShotCost.getText());
        values.put("card_hardpoints", txtHardpoints.getText());
        values.put("card_damage", txtDamage.getText());
        
        return true;
    }    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtDamage;
    private javax.swing.JTextField txtGold;
    private javax.swing.JTextField txtHardpoints;
    private javax.swing.JTextField txtOre;
    private javax.swing.JTextField txtShotCost;
    // End of variables declaration//GEN-END:variables
    
}