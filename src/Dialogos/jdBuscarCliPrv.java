
package Dialogos;

import static Formularios.fmMain.pnPestanas;
import PanelForm.pfClientes;
import PanelForm.pfProveedores;
import Utilidades.CargaGrillas;
import Utilidades.PanelTab;
import java.awt.event.KeyEvent;

import javax.swing.table.DefaultTableModel;

public class jdBuscarCliPrv extends javax.swing.JDialog {

int Tipo;
String RutSel;
    public jdBuscarCliPrv(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txNombre = new javax.swing.JTextField();
        btBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Grilla = new javax.swing.JTable();
        btCerrar = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        jLabel1.setText("Nombre");

        txNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txNombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txNombreKeyTyped(evt);
            }
        });

        btBuscar.setText("Buscar");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

        Grilla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Rut", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Grilla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GrillaMouseClicked(evt);
            }
        });
        Grilla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GrillaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(Grilla);

        btCerrar.setText("Cerrar");
        btCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCerrarActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btBuscar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(btCerrar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btBuscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btCerrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void SetTipo(int Valor){
        Tipo=Valor;
    }
    public String GetRut(){
        return RutSel;
    }
    private void txNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txNombreKeyReleased
        if(evt.getKeyCode()== KeyEvent.VK_ENTER)
        btBuscar.doClick();
        if(evt.getKeyCode()== KeyEvent.VK_DOWN)
        Grilla.requestFocus();
    }//GEN-LAST:event_txNombreKeyReleased

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        String sql;
        DefaultTableModel tbMd = new DefaultTableModel();

        if(this.getTitle() == "Buscar Cliente"){
            sql =   "select rut || '-' || dv AS RUT, nombre\n" +
            "from cliente p\n" +
            "where upper(p.nombre) like '%" + txNombre.getText() +"%'";
        }
        else if(this.getTitle() == "Buscar Proveedor"){
            sql =   "select rut || '-' || dv AS RUT, nombre\n" +
            "from proveedor P\n" +
            "where upper(p.nombre) like '%" + txNombre.getText() +"%'";
        }
        else{
            sql =   "select rut , nombre\n" +
            "from personal P\n" +
            "where upper(p.nombre) like '%" + txNombre.getText() +"%'";
        }

        try {
            CargaGrillas c = new CargaGrillas();
            tbMd = c.returndata(sql);
            Grilla.setModel(tbMd);
            Grilla.getColumnModel().getColumn(0).setPreferredWidth(50);//Rut
            Grilla.getColumnModel().getColumn(1).setPreferredWidth(300);//Nombre

            jScrollPane1.getViewport().add(Grilla);
        } catch (Exception e) {
        }
            
    }//GEN-LAST:event_btBuscarActionPerformed

    private void GrillaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GrillaMouseClicked
        if(evt.getClickCount()==2){
            String Rut    = Grilla.getValueAt(Grilla.getSelectedRow(), 0).toString();
            String Nombre = Grilla.getValueAt(Grilla.getSelectedRow(), 1).toString();
            String RutPersonal = Rut;
            Rut = Rut.substring(0, Rut.length()-2);
            

            if(Nombre.length()>20) Nombre=Nombre.substring(0, 20);

            if(this.getTitle() == "Buscar Cliente"){
                 if(Tipo==0){
                     pfClientes Cli = new pfClientes();
                     Cli.setOpaque(false);
                     pnPestanas.addTab(Nombre, Cli);
                     PanelTab btc=new PanelTab(pnPestanas,0);
                     pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(Cli), btc);
                     pnPestanas.setSelectedIndex(pnPestanas.getTabCount()-1);
                     Cli.CargaCliente(Rut);
                 }
                 else
                     RutSel=Rut;
            }
            else if(this.getTitle() == "Buscar Proveedor"){
                if(Tipo==0){
                    pfProveedores Prv = new pfProveedores();
                    Prv.setOpaque(false);
                    pnPestanas.addTab(Nombre, Prv);
                    PanelTab btc=new PanelTab(pnPestanas,0);
                    pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(Prv), btc);
                    pnPestanas.setSelectedIndex(pnPestanas.getTabCount()-1);
                    Prv.CargaProveedor(Rut);
                }
                else   
                    RutSel = Rut;
            }
            else {
                if(Tipo==0){
//                    pfGastosSueldos Rem = new pfGastosSueldos();
//                    Rem.setOpaque(false);
//                    pnPestanas.addTab(Nombre, Rem);
//                    PanelTab btc=new PanelTab(pnPestanas,0);
//                    pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(Rem), btc);
//                    pnPestanas.setSelectedIndex(pnPestanas.getTabCount()-1);
//                    Rem.CargaPersonal(Rut);
                }
                else   
                    RutSel = RutPersonal;
            }

            this.dispose();
        }
    }//GEN-LAST:event_GrillaMouseClicked

    private void GrillaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GrillaKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            String Rut    = Grilla.getValueAt(Grilla.getSelectedRow(), 0).toString();
            String Nombre = Grilla.getValueAt(Grilla.getSelectedRow(), 1).toString();
            Rut = Rut.substring(0, Rut.length()-2);

            if(Nombre.length()>20) Nombre=Nombre.substring(0, 20);
            if(this.getTitle() == "Buscar Cliente" ){
                if(Tipo==0){
                    pfClientes Cli = new pfClientes();
                    Cli.setOpaque(false);
                    pnPestanas.addTab(Nombre, Cli);
                    PanelTab btc=new PanelTab(pnPestanas,0);
                    pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(Cli), btc);
                    pnPestanas.setSelectedIndex(pnPestanas.getTabCount()-1);
                    Cli.CargaCliente(Rut);
                }
                else{
                    RutSel=Rut;
                }

            }
            else{
                if(Tipo==0){
                    pfProveedores Prv = new pfProveedores();
                    Prv.setOpaque(false);
                    pnPestanas.addTab(Nombre, Prv);
                    PanelTab btc=new PanelTab(pnPestanas,0);
                    pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(Prv), btc);
                    pnPestanas.setSelectedIndex(pnPestanas.getTabCount()-1);
                    Prv.CargaProveedor(Rut);
                }
                else
                    RutSel=Rut;
                
            }

            this.dispose();
        }
        else if(evt.getKeyCode()== KeyEvent.VK_ESCAPE){
            txNombre.requestFocus();
        }
        else if(evt.getKeyCode()== KeyEvent.VK_UP && Grilla.getSelectedRow()==0){
            txNombre.requestFocus();
        }
    }//GEN-LAST:event_GrillaKeyPressed

    private void btCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btCerrarActionPerformed

    private void txNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txNombreKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ESCAPE){
            dispose();
        }
    }//GEN-LAST:event_txNombreKeyPressed

    private void txNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txNombreKeyTyped
        if (Character.isLowerCase(evt.getKeyChar()))
            evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));  
    }//GEN-LAST:event_txNombreKeyTyped

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(jdBuscarCliPrv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jdBuscarCliPrv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jdBuscarCliPrv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jdBuscarCliPrv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdBuscarCliPrv dialog = new jdBuscarCliPrv(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable Grilla;
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btCerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txNombre;
    // End of variables declaration//GEN-END:variables
}
