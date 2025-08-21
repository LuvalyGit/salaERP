/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PanelForm;

import Conexion.ExeSql;
import Conexion.ExeSqlLuvaly;
import Formularios.fmMain;
import static Formularios.fmMain.pnPestanas; 
import Utilidades.Exporter;
import Utilidades.PanelTab;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author infornatica
 */
public class pfReportePrecio extends javax.swing.JPanel {

    Date hoy = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    public pfReportePrecio() {
        initComponents();
        
        
        jPanel10.setVisible(false);
        CargaReportes();
        
        
      
    }

    
    public void CargaDocumentos() {
        DefaultTableModel model = (DefaultTableModel) Grilla.getModel();
        
        
        while(model.getRowCount()>0){
            model.removeRow(0);
        }
        
        ExeSqlLuvaly SqlLuv = new ExeSqlLuvaly();
        ExeSql Sql = new ExeSql();
        
        ResultSet RsLuv = null;
        ResultSet Rs = null;
        
        try {
            
            Rs = Sql.Select("SELECT sku, pventa, pventa_ant, compra FROM producto_valores WHERE pventa <> pventa_ant and compra = 1");
              
                if (Sql.GetRowCount() > 0){
              
                    while (Rs.next()) {
              
                        RsLuv = SqlLuv.Select("SELECT sku, nombre FROM producto where sku = '"+Rs.getString("sku")+"'");
                        
                        if (SqlLuv.GetRowCount() > 0){                        
                        
                            RsLuv.next();
                            
                            
                            
                //****************************************************************************************************************************            
                        //    int redon = cost_vent_iva%10;   //Extrae el el utlimo digito de la cifra
                            
                            int cost_vent_iva = Rs.getInt("pventa_ant");
                            int nuevo_cost_vent_iva = Rs.getInt("pventa");
                            int compra = Rs.getInt("compra");
                            
                            
                            int redon = Rs.getInt("pventa_ant")%10;   //Extrae el el utlimo digito de la cifra
                            int redon2 = Rs.getInt("pventa")%10;   //Extrae el el utlimo digito de la cifra

                            int dif = 10-redon;
                            int dif2 = 10-redon2;
            
                            if (redon >= 5  ){
            
                                cost_vent_iva = cost_vent_iva + dif;
                
            
                            }else if (redon < 5 && redon > 0   ) {
            
                                cost_vent_iva = cost_vent_iva - redon;
                        
                            }
                            
                            
                            if (redon2 >= 5  ){
            
                                nuevo_cost_vent_iva = nuevo_cost_vent_iva + dif2;
                
            
                            }else if (redon2 < 5 && redon2 > 0   ) {
            
                                nuevo_cost_vent_iva = nuevo_cost_vent_iva - redon2;
                        
                            }
                            
                            
                            
                           
                            
                //****************************************************************************************************************************            
                            
                            

                            if (cost_vent_iva != nuevo_cost_vent_iva && compra == 1){
                                
                                
                                String pventa_ant = String.valueOf(cost_vent_iva);
                                String pventa = String.valueOf(nuevo_cost_vent_iva);
                            
                                model.addRow(new Object[]{
                                                       Rs.getString("sku"),  
                                                       RsLuv.getString("nombre"), 
                                                       pventa_ant,
                                                       pventa
                                                  
                                });
                            
                            }
                        
                        }   
                        
                        
                        
                            
              
                    }
                }
            
              
            //    model.getRowCount()
             lbTotalOrd.setText(""+model.getRowCount());
           
            
        } catch (SQLException ex) {
            Logger.getLogger(pfReportePrecio.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            Sql.Close();
        }
            
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btGenerar = new javax.swing.JButton();
        CargaButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Grilla = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        lbTotalOrd = new javax.swing.JLabel();
        cbReporte = new javax.swing.JComboBox();
        btImprimir = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        GrillaImp = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Reporte Precios"));
        jPanel1.setToolTipText("");

        btGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Order24.png"))); // NOI18N
        btGenerar.setText("Generar Reporte");
        btGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGenerarActionPerformed(evt);
            }
        });

        CargaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/Actualiza.png"))); // NOI18N
        CargaButton.setText("Cargar");
        CargaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CargaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btGenerar)
                .addGap(767, 767, 767))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CargaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Productos"));

        Grilla.setAutoCreateRowSorter(true);
        Grilla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Descripción", "P. Actual (con IVA)", "P. Nuevo (con IVA)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Grilla.getTableHeader().setReorderingAllowed(false);
        Grilla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                GrillaMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(Grilla);
        if (Grilla.getColumnModel().getColumnCount() > 0) {
            Grilla.getColumnModel().getColumn(0).setMinWidth(70);
            Grilla.getColumnModel().getColumn(0).setPreferredWidth(70);
            Grilla.getColumnModel().getColumn(0).setMaxWidth(70);
            Grilla.getColumnModel().getColumn(2).setMinWidth(110);
            Grilla.getColumnModel().getColumn(2).setPreferredWidth(110);
            Grilla.getColumnModel().getColumn(2).setMaxWidth(110);
            Grilla.getColumnModel().getColumn(3).setMinWidth(110);
            Grilla.getColumnModel().getColumn(3).setPreferredWidth(110);
            Grilla.getColumnModel().getColumn(3).setMaxWidth(110);
        }

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Registros:");

        lbTotalOrd.setText("0");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(lbTotalOrd, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTotalOrd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        cbReporte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btImprimir.setText("Imprimir");
        btImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImprimirActionPerformed(evt);
            }
        });

        GrillaImp.setAutoCreateRowSorter(true);
        GrillaImp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Descripción", "P. Actual", "P. Nuevo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        GrillaImp.getTableHeader().setReorderingAllowed(false);
        GrillaImp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                GrillaImpMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(GrillaImp);
        if (GrillaImp.getColumnModel().getColumnCount() > 0) {
            GrillaImp.getColumnModel().getColumn(0).setMinWidth(70);
            GrillaImp.getColumnModel().getColumn(0).setPreferredWidth(70);
            GrillaImp.getColumnModel().getColumn(0).setMaxWidth(70);
        }

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(cbReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void CargaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargaButtonActionPerformed
       
        CargaDocumentos();
   
    }//GEN-LAST:event_CargaButtonActionPerformed

    private void GrillaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GrillaMousePressed
        //CargaDetalleOrdenes();        // TODO add your handling code here:
    }//GEN-LAST:event_GrillaMousePressed

    private void btGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGenerarActionPerformed
        ExeSql Sql = new ExeSql();
        ExeSql Sql2 = new ExeSql();
        ExeSql Sql3 = new ExeSql();
        ExeSql Sql4 = new ExeSql();
        ExeSql Sql5 = new ExeSql();
        
        ResultSet Rs = null;
        ResultSet Rs2 = null;
        ResultSet Rs3 = null;
        ResultSet Rs4 = null;
        
        String Query="";
        String Nombre = "";
        

        try {

            if(fmMain.OkCancel("¿Desea Generar Reporte?")== JOptionPane.OK_OPTION){

                int tpago = 0;

                Rs = Sql.Select("SELECT * FROM reporte_precios \n" +
                                "WHERE fecrep BETWEEN '" + getFecha() + "' and '" + getFecha() + "'");
                
                if (Sql.GetRowCount() > 0){
                
                      fmMain.Mensaje("Ya se Generó un Reporte para la Fecha : "+dateFormat.format(hoy));
                      return;
                
                }
                
                

                Rs2 = Sql2.Select("SELECT p.nombre, p.apellidopaterno, p.apellidomaterno from personal p\n" +
                                  "WHERE p.usuario =  '"+fmMain.GetUsuario()+"'");
            
                if (Sql2.GetRowCount() > 0){
            
                    Rs2.next();
                
                    Nombre = Rs2.getString("nombre") + " " + Rs2.getString("apellidopaterno") + " " + Rs2.getString("apellidomaterno");
            
                }
                
                
                
                Rs4 = Sql4.Select("select sp_getcorrelativo \n" +
                                 "from sp_getcorrelativo('RVTA')");
                Rs4.next();
                
                String Numero  = Rs4.getString("sp_getcorrelativo");
                Rs4.close();
                
                
                
                for (int i = 0; i < Grilla.getRowCount(); i++) {
                    
                    String sku = Grilla.getValueAt(i,0).toString().trim();
                    int pventa_ant = Integer.valueOf(Grilla.getValueAt(i,2).toString());
                    int pventa = Integer.valueOf(Grilla.getValueAt(i,3).toString());
                    
                    
                    Sql3.ExeSql("INSERT INTO reporte_precios (\n" +
                               "sku, pventa, pventa_ant, numero)\n" +
                               "VALUES ('" +
                               sku + "'," +
                               pventa + "," +
                               pventa_ant + "," +
                               Numero + ")");
                    
                    
                    Sql5.ExeSql("UPDATE producto_valores SET\n" +
                                 "pventa_ant = " + pventa + ", \n" +
                                 "compra = 0 \n" +
                                 "WHERE sku='" + sku + "'" );
                    
                    
                   

                }
                
                Sql3.Commit();
                Sql5.Commit();
                
                CargaReportes();
             

            }

        } catch (SQLException ex) {
            Sql3.Rollback();
            Logger.getLogger(pfReportePrecio.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
        
            Sql3.Close();
        }

        

    }//GEN-LAST:event_btGenerarActionPerformed

    private void btImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImprimirActionPerformed

        DefaultTableModel model = (DefaultTableModel) GrillaImp.getModel();
        
        
        while(model.getRowCount()>0){
            model.removeRow(0);
        }
        
        ExeSqlLuvaly SqlLuv = new ExeSqlLuvaly();
        ExeSql Sql = new ExeSql();
        ExeSql Sql2 = new ExeSql();
        
        ResultSet RsLuv = null;
        ResultSet Rs = null;
        ResultSet Rs2 = null;
      
        
        String Query="";
        String Nombre = "";
        

        try {


                Rs = Sql.Select("SELECT p.nombre, p.apellidopaterno, p.apellidomaterno from personal p\n" +
                                "WHERE p.usuario =  '"+fmMain.GetUsuario()+"'");
            
                if (Sql.GetRowCount() > 0){
            
                    Rs.next();
                
                    Nombre = Rs.getString("nombre") + " " + Rs.getString("apellidopaterno") + " " + Rs.getString("apellidomaterno");
            
                }
                
                
                 Rs2 = Sql2.Select("SELECT * FROM reporte_precios WHERE numero = "+cbReporte.getSelectedItem().toString().trim());
              
                if (Sql2.GetRowCount() > 0){
              
                    while (Rs2.next()) {
              
                        RsLuv = SqlLuv.Select("SELECT sku, nombre FROM producto where sku = '"+Rs2.getString("sku")+"'");
                        
                        if (SqlLuv.GetRowCount() > 0){                        
                        
                            RsLuv.next();
                            model.addRow(new Object[]{
                                                   Rs2.getString("sku"),  
                                                   RsLuv.getString("nombre"), 
                                                   Rs2.getString("pventa_ant"),
                                                   Rs2.getString("pventa")
                                                  
                            });
                        
                        }   
                            
              
                    }
                }
            
               

                Map parametro = new HashMap();

                parametro.put("FECHA",dateFormat.format(hoy));
                parametro.put("FOLIO",cbReporte.getSelectedItem().toString().trim());
                parametro.put("NOMREPO",Nombre );

                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/rpReportePrecio.jasper"));
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametro, new JRTableModelDataSource(GrillaImp.getModel()));
                JasperViewer view = new JasperViewer(jasperPrint,false);
                view.setVisible(true);

             

           

        }catch (JRException e) {
            
            e.printStackTrace ();
            System.out.println ("informes r" + e.toString ());

        } catch (SQLException ex) {
            
            Logger.getLogger(pfReportePrecio.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
        
            Sql.Close();
            Sql2.Close();
        }

        
        
        
    }//GEN-LAST:event_btImprimirActionPerformed

    private void GrillaImpMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GrillaImpMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_GrillaImpMousePressed

    
    private void CargaReportes(){
        
        ExeSql Sql= new ExeSql();
        ResultSet Rs;
         
        cbReporte.removeAllItems();
        DefaultComboBoxModel dfCm = new DefaultComboBoxModel();
        cbReporte.setModel(dfCm);
        
        
        try{
            
            //cbReporte.addItem("<Todos>");
            Rs = Sql.Select("SELECT numero \n" +
                            "FROM reporte_precios \n" +
                            "WHERE numero > 0 \n" +
                            "GROUP BY numero ORDER BY numero DESC");
               
            if (Sql.GetRowCount() > 0){
            
                while( Rs.next()){
               
                    cbReporte.addItem(Rs.getString("numero"));
                   
                }
            
            }
            
        }catch (Exception e){
                
            System.out.println(e.getMessage());
            fmMain.Mensaje("Error: "  + e);
        
        }finally{
        
            Sql.Close();
        }
        
    }
    
    
    
    public String getFecha() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return( sdf.format( hoy ) );
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton CargaButton;
    private javax.swing.JTable Grilla;
    private javax.swing.JTable GrillaImp;
    private javax.swing.JButton btGenerar;
    private javax.swing.JButton btImprimir;
    private javax.swing.JComboBox cbReporte;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbTotalOrd;
    // End of variables declaration//GEN-END:variables
}
