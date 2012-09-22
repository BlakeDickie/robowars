/*
 * ComputerEditPanel.java
 *
 * Created on July 4, 2004, 11:10 PM
 */

package robowars.editor.typepanels;

/**
 *
 * @author  bdickie
 */
public class ComputerEditPanel extends CardDetailsEditor {
    
    /** Creates new form ComputerEditPanel */
    public ComputerEditPanel() {
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
        txtCompexity = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Gold Cost:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel2.setText("Ore Cost:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        add(txtOre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 70, -1));

        add(txtGold, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 70, -1));

        jLabel3.setText("Complexity:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, -1));

        add(txtCompexity, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 70, -1));

    }//GEN-END:initComponents

    public void loadDetails(java.sql.ResultSet rs) throws java.sql.SQLException {
        txtGold.setText(rs.getString("card_gold_cost"));
        txtOre.setText(rs.getString("card_ore_cost"));
        txtCompexity.setText(rs.getString("card_complexity"));
    }    
    
    public boolean saveDetails(java.util.Map values) {
        values.put("card_gold_cost", txtGold.getText());
        values.put("card_ore_cost", txtOre.getText());
        values.put("card_complexity", txtCompexity.getText());
        
        return true;
    }    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtCompexity;
    private javax.swing.JTextField txtGold;
    private javax.swing.JTextField txtOre;
    // End of variables declaration//GEN-END:variables
    
}
