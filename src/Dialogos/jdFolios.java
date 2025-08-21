package Dialogos;

import Conexion.ExeSql;
import Formularios.fmMain;
import PanelForm.pfOCCliente;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class jdFolios extends javax.swing.JDialog {
   
    String TipDocto="";

    public jdFolios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txDesde = new javax.swing.JTextField();
        btAceptar = new javax.swing.JButton();
        txHasta = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btCancelar = new javax.swing.JButton();
        cbDoc = new javax.swing.JComboBox();
        txCod = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Código");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Documento");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Desde");

        txDesde.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txDesde.setText("0");
        txDesde.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txDesdeFocusLost(evt);
            }
        });
        txDesde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txDesdeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txDesdeKeyTyped(evt);
            }
        });

        btAceptar.setText("Aceptar");
        btAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAceptarActionPerformed(evt);
            }
        });

        txHasta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txHasta.setText("0");
        txHasta.setToolTipText("");
        txHasta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txHastaFocusLost(evt);
            }
        });
        txHasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txHastaActionPerformed(evt);
            }
        });
        txHasta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txHastaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txHastaKeyTyped(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Hasta");

        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        cbDoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione Tipo Documento", "Boleta Electrónica", "Factura Electrónica", "Guia de Despacho", "Nota Crédito", "Nota Débito" }));
        cbDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDocActionPerformed(evt);
            }
        });

        txCod.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txCod.setEnabled(false);
        txCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txCodActionPerformed(evt);
            }
        });
        txCod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txCodKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txCodKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btCancelar)
                        .addGap(26, 26, 26)
                        .addComponent(btAceptar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txCod, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txDesde, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                .addComponent(txHasta))
                            .addGap(123, 123, 123))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDoc))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addComponent(jLabel4))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    

//-----------------------------------------------------------------------------
//  BOTON ACEPTAR
//-----------------------------------------------------------------------------
    private void btAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAceptarActionPerformed
        
        ExeSql Sql = new ExeSql();
        String Query = "";
        
        int desde = 0;
        int hasta = 0;
        
        if (txDesde.getText().isEmpty() || txDesde.getText().equals("0")){
                    
            fmMain.Mensaje("Debe ingresar un Número");
            txDesde.requestFocus();
            return;
        
        }
        
        
        if (txHasta.getText().isEmpty() || txHasta.getText().equals("0") ){
                    
            fmMain.Mensaje("Debe ingresar un Número");
            txHasta.requestFocus();
            return;
        
        }
        
        
        desde = Integer.parseInt(txDesde.getText().trim());
        hasta = Integer.parseInt(txHasta.getText().trim());
        
        
        if (desde > hasta){
        
            fmMain.Mensaje("Final no puede se mayor que el inicial !");
            txDesde.requestFocus();
            return;
        
        
        }
        
        
        if (cbDoc.getSelectedIndex() > 0){
        
            try {
                Query = "update par_correlativo SET \n" +
                        "numero= "+txDesde.getText().trim() +","+
                        "desde= "+txDesde.getText().trim() +","+
                        "hasta= "+txHasta.getText().trim() +" \n"+
                        "where tipo='"+txCod.getText().trim()+"'";
                
                
                Sql.ExeSql(Query);
                Sql.Commit();
                fmMain.Mensaje("Folios Actualizados !");
                
            } catch (SQLException ex) {
                Sql.Rollback();
                Logger.getLogger(jdFolios.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
        }else if (cbDoc.getSelectedIndex() == 0) {
        
                fmMain.Mensaje("Debe elejir un Tipo de Documento");
                return;
        
        }
        
  
    }//GEN-LAST:event_btAceptarActionPerformed

    private void txDesdeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txDesdeKeyTyped
      
        char validar = evt.getKeyChar();    //Código agregado por R. Lopez
        if (Character.isLetter(validar) || !Character.isDigit(validar) ){         //se valida que solo se ingresen 
           
            evt.consume();                  //números al textfield 
        }
        
        
      
    }//GEN-LAST:event_txDesdeKeyTyped

    private void txDesdeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txDesdeKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            txHasta.requestFocus();
        }
    }//GEN-LAST:event_txDesdeKeyPressed

    private void txHastaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txHastaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
         
        }
            
    }//GEN-LAST:event_txHastaKeyPressed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
      
        dispose();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
      
    }//GEN-LAST:event_formWindowOpened

    private void txHastaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txHastaKeyTyped
       
        char validar = evt.getKeyChar();    //Código agregado por R. Lopez
        if (Character.isLetter(validar) || !Character.isDigit(validar) ){         //se valida que solo se ingresen 
        
            evt.consume();                  //números al textfield 
        }
    }//GEN-LAST:event_txHastaKeyTyped

    private void txHastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txHastaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txHastaActionPerformed

    private void txCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txCodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txCodActionPerformed

    private void txCodKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCodKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txCodKeyPressed

    private void txCodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCodKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txCodKeyTyped

    private void cbDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDocActionPerformed
        
        if (cbDoc.getSelectedItem().equals("Boleta Electrónica")){
        
                TipDocto = "BOC";
        
        }else if (cbDoc.getSelectedItem().equals("Factura Electrónica")){
        
                TipDocto = "FAC";
            
        
        }else if (cbDoc.getSelectedItem().equals("Guia de Despacho")){
        
                TipDocto = "GDC";
        
        }else if (cbDoc.getSelectedItem().equals("Nota Crédito")){
        
               TipDocto = "NCC";
        
        }else if (cbDoc.getSelectedItem().equals("Nota Débito")){
        
              TipDocto = "NDC";
        
        }
        
        
        txCod.setText(TipDocto);
        
        
    }//GEN-LAST:event_cbDocActionPerformed

    private void txDesdeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txDesdeFocusLost
       
        if (txDesde.getText().isEmpty()){
            
            txDesde.setText("0");
            
        }
        
    }//GEN-LAST:event_txDesdeFocusLost

    private void txHastaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txHastaFocusLost
       
        if (txHasta.getText().isEmpty()){
            
            txHasta.setText("0");
            
        }
        
    }//GEN-LAST:event_txHastaFocusLost

   
    

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(jdFolios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jdFolios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jdFolios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jdFolios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdFolios dialog = new jdFolios(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAceptar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JComboBox cbDoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txCod;
    private javax.swing.JTextField txDesde;
    private javax.swing.JTextField txHasta;
    // End of variables declaration//GEN-END:variables
}
