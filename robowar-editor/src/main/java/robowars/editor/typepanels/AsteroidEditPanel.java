/*
 * WeaponEditPanel.java
 *
 * Created on July 4, 2004, 10:32 PM
 */

package robowars.editor.typepanels;

/**
 *
 * @author  bdickie
 */
public class AsteroidEditPanel extends CardDetailsEditor {
    
    /** Creates new form WeaponEditPanel */
    public AsteroidEditPanel() {
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
        txtAttackSpace = new javax.swing.JTextField();
        txtDefendSpace = new javax.swing.JTextField();
        txtOreProduction = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Attack Space:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel2.setText("Defender Space:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel3.setText("Ore Production:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        add(txtAttackSpace, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 100, -1));

        add(txtDefendSpace, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 100, -1));

        add(txtOreProduction, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 100, -1));

    }//GEN-END:initComponents

    public void loadDetails(java.sql.ResultSet rs) throws java.sql.SQLException {
        txtAttackSpace.setText(rs.getString("card_attack_space"));
        txtDefendSpace.setText(rs.getString("card_defence_space"));
        txtOreProduction.setText(rs.getString("card_ore_production"));
    }    
    
    public boolean saveDetails(java.util.Map values) {
        values.put("card_attack_space", txtAttackSpace.getText());
        values.put("card_defence_space", txtDefendSpace.getText());
        values.put("card_ore_production", txtOreProduction.getText());
        return true;
    }    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtAttackSpace;
    private javax.swing.JTextField txtDefendSpace;
    private javax.swing.JTextField txtOreProduction;
    // End of variables declaration//GEN-END:variables
    
}
