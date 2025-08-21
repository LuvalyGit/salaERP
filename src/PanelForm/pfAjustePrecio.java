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
import static PanelForm.pfIndicadoresInventario.DARK_GREEN;
import Utilidades.Exportercsv;
import Utilidades.PanelTab;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import jxl.Cell;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.BorderLineStyle;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;



/**
 *
 * @author informatica
 */
public class pfAjustePrecio extends javax.swing.JPanel {

    DefaultTableModel Tabla = null; 
    Calendar cal = Calendar.getInstance();
    int mesant = cal.get(Calendar.MONTH)+1;         // El Mes actual
    int anant = cal.get(Calendar.YEAR);             // El Año actual
        
    private String mes = String.valueOf(mesant);       
    private String agno = String.valueOf(anant);     
    
    public pfAjustePrecio() {
      
      initComponents();
      
      lbDesde.setVisible(false);
      lbHasta.setVisible(false);
      
      Tabla = (DefaultTableModel) Grilla.getModel();
     
       //Setea fecha inicial Desde y Hasta
      Date date = new Date();
      dtDesde.setDate(date);
      dtHasta.setDate(date);
      dtDesde.setEnabled(true);
      dtHasta.setEnabled(true);  
      
      dtDesde.setVisible(false);
      dtHasta.setVisible(false);
      
      btExportar.setVisible(false);
      
      txFiltro.setEnabled(false);
      
    }

    
    public String getDesde() {
         
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return( sdf.format( (dtDesde.getDate()).getTime() ) );
    }
     
     
      public String getHasta() {
         
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return( sdf.format( (dtHasta.getDate()).getTime() ) );
    }
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        dtDesde = new org.jdesktop.swingx.JXDatePicker();
        dtHasta = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane1 = new javax.swing.JScrollPane();
        Grilla = new javax.swing.JTable();
        btExportar = new javax.swing.JButton();
        btCargar = new javax.swing.JButton();
        lbDesde = new javax.swing.JLabel();
        lbHasta = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txFiltro = new javax.swing.JTextField();

        jMenuItem1.setText("Ficha Producto");
        jMenuItem1.setToolTipText("");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Ficha OCP");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        dtDesde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dtDesdeActionPerformed(evt);
            }
        });

        dtHasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dtHastaActionPerformed(evt);
            }
        });

        Grilla.setAutoCreateRowSorter(true);
        Grilla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Grilla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rut", "Proveedor", "N° FAP", "Fecha FAP", "Sku", "Producto", "Cantidad", "Precio OCP", "Precio FAP", "Diferencia", "Nro .OCP", "Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true, false, true, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Grilla.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(Grilla);
        if (Grilla.getColumnModel().getColumnCount() > 0) {
            Grilla.getColumnModel().getColumn(0).setMinWidth(80);
            Grilla.getColumnModel().getColumn(0).setPreferredWidth(80);
            Grilla.getColumnModel().getColumn(0).setMaxWidth(80);
            Grilla.getColumnModel().getColumn(2).setMinWidth(80);
            Grilla.getColumnModel().getColumn(2).setPreferredWidth(80);
            Grilla.getColumnModel().getColumn(2).setMaxWidth(80);
            Grilla.getColumnModel().getColumn(3).setMinWidth(90);
            Grilla.getColumnModel().getColumn(3).setPreferredWidth(90);
            Grilla.getColumnModel().getColumn(3).setMaxWidth(90);
            Grilla.getColumnModel().getColumn(4).setMinWidth(80);
            Grilla.getColumnModel().getColumn(4).setPreferredWidth(80);
            Grilla.getColumnModel().getColumn(4).setMaxWidth(80);
            Grilla.getColumnModel().getColumn(6).setMinWidth(60);
            Grilla.getColumnModel().getColumn(6).setPreferredWidth(60);
            Grilla.getColumnModel().getColumn(6).setMaxWidth(60);
            Grilla.getColumnModel().getColumn(7).setMinWidth(75);
            Grilla.getColumnModel().getColumn(7).setPreferredWidth(75);
            Grilla.getColumnModel().getColumn(7).setMaxWidth(75);
            Grilla.getColumnModel().getColumn(8).setMinWidth(75);
            Grilla.getColumnModel().getColumn(8).setPreferredWidth(75);
            Grilla.getColumnModel().getColumn(8).setMaxWidth(75);
            Grilla.getColumnModel().getColumn(9).setMinWidth(70);
            Grilla.getColumnModel().getColumn(9).setPreferredWidth(70);
            Grilla.getColumnModel().getColumn(9).setMaxWidth(70);
            Grilla.getColumnModel().getColumn(10).setMinWidth(80);
            Grilla.getColumnModel().getColumn(10).setPreferredWidth(80);
            Grilla.getColumnModel().getColumn(10).setMaxWidth(80);
            Grilla.getColumnModel().getColumn(11).setMinWidth(70);
            Grilla.getColumnModel().getColumn(11).setPreferredWidth(70);
            Grilla.getColumnModel().getColumn(11).setMaxWidth(70);
        }

        btExportar.setText("Exportar");
        btExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExportarActionPerformed(evt);
            }
        });

        btCargar.setText("CARGAR");
        btCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCargarActionPerformed(evt);
            }
        });

        lbDesde.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbDesde.setText("Desde");

        lbHasta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbHasta.setText("Hasta");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/search16.png"))); // NOI18N
        jLabel14.setText("FILTRO:");

        txFiltro.setEnabled(false);
        txFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txFiltroActionPerformed(evt);
            }
        });
        txFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txFiltroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txFiltroKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDesde, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(lbHasta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72)
                .addComponent(btCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(292, 292, 292)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(txFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbDesde)
                            .addComponent(dtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbHasta)
                            .addComponent(dtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dtHastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dtHastaActionPerformed
        CargaAjustes(getDesde(), getHasta());
    }//GEN-LAST:event_dtHastaActionPerformed

    private void dtDesdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dtDesdeActionPerformed
        CargaAjustes(getDesde(), getHasta());
    }//GEN-LAST:event_dtDesdeActionPerformed

    private void btExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExportarActionPerformed
       
        String norden = "0";
        String nusuario = "0";
            
        File ruta = null;
        
        String sSistemaOperativo = System.getProperty("os.name");               //Determina el Sistema Operativo del Equipo
            
        if (sSistemaOperativo.contains("Win")){
        
            ruta = new File("C:\\temp\\Ajuste"+mes+agno+".xls");               
        
        }else{
            
            ruta = new File("/home/informatica/Escritorio/Ajuste"+mes+agno+".xls");  
        }
        
        WorkbookSettings conf = new WorkbookSettings();
        conf.setEncoding("ISO-8859-1");                                     //Se establece la norma ISO de codificación de caracteres
           
        try {
            
            WritableWorkbook libro = Workbook.createWorkbook(ruta,conf);     //Se crea libro de excel
            //WritableFont Fuente = new WritableFont(WritableFont.ARIAL, 8, WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
            WritableFont Fuente = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE, Colour.WHITE);
 
            
            WritableCellFormat format = new WritableCellFormat();               //Alineación para los encabezados
            format.setAlignment(Alignment.CENTRE);
            format.setBackground(Colour.GREY_40_PERCENT);
            format.setFont(Fuente);
            format.setBorder(Border.ALL, BorderLineStyle.THIN,Colour.BLACK); //table border style
            format.setWrap(true);
            
 
            WritableCellFormat format2 = new WritableCellFormat();              //Alineación para los datos numericos a la derecha
            format2.setAlignment(Alignment.RIGHT);
            format2.setBorder(Border.ALL, BorderLineStyle.THIN,Colour.BLACK); //table border style
           
            WritableCellFormat format3 = new WritableCellFormat();              //Alineación para los datos texto a la izquierda
            format3.setAlignment(Alignment.LEFT);
            format3.setBorder(Border.ALL, BorderLineStyle.THIN,Colour.BLACK); //table border style
            
            WritableCellFormat format4 = new WritableCellFormat();              //Alineación para los datos texto al centro
            format4.setAlignment(Alignment.CENTRE);
            format4.setBorder(Border.ALL, BorderLineStyle.THIN,Colour.BLACK); //table border style
            
            
            WritableSheet hoja = libro.createSheet("Ajuste de Precios", 0);     //Se le asigna nombre a la primera hoja(0) del libro excel
            hoja.setColumnView(0, 15);     //rut                                    //Ancho de la columna (en número de caracteres)
            hoja.setColumnView(1, 80);     //nombre proveedor 
            hoja.setColumnView(2, 12);     //Nro. FAP 
            hoja.setColumnView(3, 12);     //Fecha FAP 
            hoja.setColumnView(4, 15);     //Sku 
            hoja.setColumnView(5, 80);     //nombre Producto 
            hoja.setColumnView(6, 10);     //Cantidad 
            hoja.setColumnView(7, 15);     //Precio OCP 
            hoja.setColumnView(8, 12);     //Precio FAP 
            hoja.setColumnView(9, 12);     //Diferencia 
            hoja.setColumnView(10, 15);     //Nro.OCP 
            hoja.setColumnView(11,15);    //Usuario 
         
            hoja.setRowView(0,26*20);    //la altura de la fila del encabezado
            
    //********************************  Se agregan contenido a las celdas de la hoja  *******************************************        
            hoja.addCell(new jxl.write.Label(0,0,"Rut Proveedor",format));                
            hoja.addCell(new jxl.write.Label(1,0,"Proveedor",format));
            hoja.addCell(new jxl.write.Label(2,0,"Nro. FAP",format));
            hoja.addCell(new jxl.write.Label(3,0,"Fecha FAP",format));
            hoja.addCell(new jxl.write.Label(4,0,"Sku",format));
            hoja.addCell(new jxl.write.Label(5,0,"Producto",format));
            hoja.addCell(new jxl.write.Label(6,0,"Cantidad",format));
            hoja.addCell(new jxl.write.Label(7,0,"Precio OCP",format));
            hoja.addCell(new jxl.write.Label(8,0,"Precio FAP",format));
            hoja.addCell(new jxl.write.Label(9,0,"Diferencia",format));
            hoja.addCell(new jxl.write.Label(10,0,"Nro. OCP",format));
            hoja.addCell(new jxl.write.Label(11,0,"Usuario",format));
            
            for(int i=0; i < Grilla.getRowCount(); i++){ //For
            
                int fila = i+1;
                
                hoja.addCell(new jxl.write.Label(0,fila,Grilla.getValueAt(i,0).toString(),format3));
                hoja.addCell(new jxl.write.Label(1,fila,Grilla.getValueAt(i,1).toString(),format3));
                hoja.addCell(new jxl.write.Label(2,fila,Grilla.getValueAt(i,2).toString(),format4));
                hoja.addCell(new jxl.write.Label(3,fila,Grilla.getValueAt(i,3).toString(),format4));
                hoja.addCell(new jxl.write.Label(4,fila,Grilla.getValueAt(i,4).toString(),format3));
                hoja.addCell(new jxl.write.Label(5,fila,Grilla.getValueAt(i,5).toString(),format3));
                hoja.addCell(new jxl.write.Label(6,fila,Grilla.getValueAt(i,6).toString(),format4));
                hoja.addCell(new jxl.write.Label(7,fila,Grilla.getValueAt(i,7).toString(),format2));
                hoja.addCell(new jxl.write.Label(8,fila,Grilla.getValueAt(i,8).toString(),format2));
                hoja.addCell(new jxl.write.Label(9,fila,Grilla.getValueAt(i,9).toString(),format2));
                hoja.addCell(new jxl.write.Label(10,fila,Grilla.getValueAt(i,10).toString(),format4));
                hoja.addCell(new jxl.write.Label(11,fila,Grilla.getValueAt(i,11).toString(),format3));
            
            }
            
            
            
    //***************************************************************************************************************************    
            libro.write();          //Se escriben el contenido en el libro
            libro.close();          //se cierra el libro
            
            fmMain.Mensaje("Archivo : Ajuste"+mes+agno+".xls \n creado con éxito");
            
        } catch (WriteException | IOException ex) {
            Logger.getLogger(pfAjustePrecio.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
        

    }//GEN-LAST:event_btExportarActionPerformed

    private void btCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCargarActionPerformed
        try{
            CargaAjustes(getDesde(), getHasta());
            btExportar.setVisible(true);
        } catch (Exception e) {
            fmMain.Mensaje(e.getMessage());
        }
    }//GEN-LAST:event_btCargarActionPerformed

    public void CargaAjustes(String desde, String hasta){
        
        ExeSql Sql = new ExeSql();
        ExeSql Sql2 = new ExeSql();
        ExeSqlLuvaly luv = new ExeSqlLuvaly();
        ResultSet producto = null;
        ResultSet Rs,Rs2;
        String norden = "";
        String nusuario = "";
        
        while(Tabla.getRowCount()>0){
         
            Tabla.removeRow(0);
        }     
        
        try{
            
            String Query ="SELECT ctp.rut,po.nombre AS proveedor,ctp.nrodocto,ct.femision,ctp.sku,p.nombre AS producto, \n"+
                          "ctp.cantidad,ctp.nuevo_valor,ctp.valorunitario,ctp.difajuste,ct.nrodocorigen, \n" +
                          "(SELECT usuarioemite FROM ctacteprv WHERE nrodocto = ct.nrodocorigen AND tipdocto IN ('OCP')) \n"+
                          "FROM ctacteprvdet ctp\n" +
                          "LEFT JOIN producto p ON ctp.sku = p.sku\n" +
                          "LEFT JOIN proveedor po ON ctp.rut = po.rut\n" +
                          "LEFT JOIN ctacteprv ct ON ctp.nrodocto = ct.nrodocto AND ctp.rut = ct.rut \n"+
                          "WHERE ctp.tipdocto IN ('FAP','GDP')\n" +
                          "AND ctp.unitanterior > 0\n" +
                          "AND ctp.difajuste <> 0";
            
            
            
            
            Rs = Sql.Select(Query);
            
            while (Rs.next()){
                
                if ( Rs.getString("nrodocorigen") == null){
                
                     norden = "0";
                
                }else{
            
                  norden = Rs.getString("nrodocorigen").trim();
            
                }
                
                
                if ( Rs.getString("usuarioemite") == null){
                
                     nusuario = "0";
                
                }else{
            
                  nusuario = Rs.getString("usuarioemite").trim();
            
                }
                
                
                
                
                
                
                double dif = Rs.getDouble("nuevo_valor") - Rs.getDouble("valorunitario");
                
                if (dif != 0){
                    producto =  luv.Select("select p.nombre as producto,trim(u.unidad) as unidad, u.um from producto p \n"
                                  + "LEFT JOIN par_unidad u ON u.codigo=p.unidad\n "
                                  + "where p.sku = '"+Rs.getString("sku").trim()+"'");
                    producto.next();
                    Tabla.addRow(new Object[]{
                                      Rs.getString("rut").trim(),
                                      Rs.getString("proveedor").trim(),
                                      Rs.getString("nrodocto").trim(),
                                      Rs.getString("femision").trim(),
                                      Rs.getString("sku").trim(),
                                      producto.getString("producto").trim(),
                                      Rs.getInt("cantidad"),
                                      Rs.getDouble("nuevo_valor"),
                                      Rs.getDouble("valorunitario"),
                                      dif,
                                      norden,
                                     nusuario.toUpperCase()
                    });
                }
            }
            
            
            Grilla.setDefaultRenderer(Object.class, new Elrender());
            
        } catch (Exception e) {
          
        } finally{
            Sql.Close();
            luv.Close();
            txFiltro.setEnabled(true);
        }
        
    }   
    
    
    private void txFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txFiltroKeyReleased

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(Grilla.getModel());
        Grilla.setRowSorter(sorter);
        String filtro = txFiltro.getText().trim();

        if (filtro.length()==0){
            
            sorter.setRowFilter(null);
        
        }else{
           
            sorter.setRowFilter(RowFilter.regexFilter(filtro));
        }
    }//GEN-LAST:event_txFiltroKeyReleased

    private void txFiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txFiltroKeyTyped

        if (Character.isLowerCase(evt.getKeyChar()))                    //Si está en minúscula
            evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));        //coloca el texto en mayúscula

    }//GEN-LAST:event_txFiltroKeyTyped

    private void txFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txFiltroActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       System.out.println( Grilla.getValueAt(Grilla.getSelectedRow(), 4).toString().trim());
        pfProductos Pro = new pfProductos();
        Pro.setOpaque(false);
        pnPestanas.addTab("Producto", Pro);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(Pro), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
        Pro.txSku.requestFocus();
        Pro.CargaProducto(Grilla.getValueAt(Grilla.getSelectedRow(), 4).toString().trim());        //
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        
        pfNPProveedor PrvOC = new pfNPProveedor();
            PrvOC.setOpaque(false);
            pnPestanas.addTab("OC Proveedor", PrvOC);
            PanelTab btc=new PanelTab(pnPestanas,0);
            pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(PrvOC), btc);
            pnPestanas.setSelectedIndex(pnPestanas.getTabCount()-1);
            PrvOC.AbrirOCP(Grilla.getValueAt(Grilla.getSelectedRow(), 10).toString().trim());
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    
    private void Imprimir(){
        try{
           
            Map parametro = new HashMap();
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/rpManifesto.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametro, new JRTableModelDataSource(Grilla.getModel()));
            JasperViewer view = new JasperViewer(jasperPrint);
            view.setVisible(true);
         
            
        }catch (JRException e) { 
                
                e.printStackTrace (); 
                System.out.println ("informes r" + e.toString ());
    
        }
    }
    
    class Elrender extends DefaultTableCellRenderer {
         
        @Override
        public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean isSelected, boolean hasFocus, int fila, int columna) {
        super.getTableCellRendererComponent(tabla,valor,isSelected, hasFocus, fila, columna);
            
            double margen = Double.parseDouble(tabla.getValueAt(fila,9).toString());
            
            
            if(margen > 0){
               
                
               if(fila==tabla.getSelectedRow()){    //Si es la Fila Seleccionada
                  
                  Color myWhite = new Color(51, 152, 47); 
                  this.setForeground(Color.white);
                  this.setBackground(myWhite);
                  this.setFont(new Font("Arial", Font.BOLD, 12));
               
               }else{                    //Si NO es la Fila Seleccionada
              
                    this.setForeground(DARK_GREEN);
                    this.setBackground(Color.white);
                } 
                
            
            }else if(margen < 0){    
             
                if(fila==tabla.getSelectedRow()){
                
                  this.setForeground(Color.white);
                  this.setBackground(Color.red);
                  this.setFont(new Font("Arial", Font.BOLD, 12));
                
                }else{
                          
                    this.setForeground(Color.red);  
                    this.setBackground(Color.white);  
                          
                }
            
            }else{
                
                 this.setForeground(Color.black);  
                this.setBackground(Color.white);
            } 
            
            return this;
        }      
            
            
        
    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Grilla;
    private javax.swing.JButton btCargar;
    private javax.swing.JButton btExportar;
    private org.jdesktop.swingx.JXDatePicker dtDesde;
    private org.jdesktop.swingx.JXDatePicker dtHasta;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDesde;
    private javax.swing.JLabel lbHasta;
    private javax.swing.JTextField txFiltro;
    // End of variables declaration//GEN-END:variables
}
