/*
 * ProgramEditPanel.java
 *
 * Created on July 4, 2004, 11:14 PM
 */

package robowars.editor.typepanels;

/**
 *
 * @author  bdickie
 */
public class ProgramEditPanel extends CardDetailsEditor {
    
    /** Creates new form ProgramEditPanel */
    public ProgramEditPanel() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jLabel3 = new javax.swing.JLabel();
        txtCompexity = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cboType = new javax.swing.JComboBox();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Complexity:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        add(txtCompexity, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 70, -1));

        jLabel1.setText("Type:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        cboType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Instant", "Permenant" }));
        add(cboType, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

    }//GEN-END:initComponents

    public void loadDetails(java.sql.ResultSet rs) throws java.sql.SQLException {
        txtCompexity.setText(rs.getString("card_complexity"));
        cboType.setSelectedIndex(rs.getInt("card_program_type"));
        
    }    
    
    public boolean saveDetails(java.util.Map values) {
        values.put("card_complexity", txtCompexity.getText());
        values.put("card_program_type", "" + cboType.getSelectedIndex());
        
        return true;
    }    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtCompexity;
    // End of variables declaration//GEN-END:variables
    
}
