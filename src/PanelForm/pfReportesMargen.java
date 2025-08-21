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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author infornatica
 */
public class pfReportesMargen extends javax.swing.JPanel {

    /**
     * Creates new form pfReportesMargen
     */
    public pfReportesMargen() {
        initComponents();
        lbcargando.setVisible(false);
    }

    public void Limpiar() {
        DefaultTableModel model = (DefaultTableModel) Documentos.getModel();
        while(model.getRowCount()>0){
            model.removeRow(0);
        }
        DefaultTableModel model_det = (DefaultTableModel) DetalleDocumento.getModel();
        while(model_det.getRowCount()>0){
            model_det.removeRow(0);
        }
        DefaultTableModel model_or = (DefaultTableModel) Ordenes.getModel();
        while(model_or.getRowCount()>0){
            model_or.removeRow(0);
        }
        DefaultTableModel model_or_det = (DefaultTableModel) DetalleOrdenes.getModel();
        while(model_or_det.getRowCount()>0){
            model_or_det.removeRow(0);
        }
    }
    public void CargaDocumentos() {
        DefaultTableModel model = (DefaultTableModel) Documentos.getModel();
        while(model.getRowCount()>0){
            model.removeRow(0);
        }
        DefaultTableModel model_det = (DefaultTableModel) DetalleDocumento.getModel();
        while(model_det.getRowCount()>0){
            model_det.removeRow(0);
        }
        int mes = cbMes.getSelectedIndex()+1;
        String ano = cbAno.getSelectedItem().toString();
        ExeSql sql = new ExeSql();
        ResultSet rs = null;
        ResultSet rs_conteo = null;
        

        int registros = 0;
        try {
            
            rs_conteo = sql.Select("select codigo, convenio from par_convenio order by codigo asc");
            for(int i = 0; rs_conteo.next(); i++){
                float resultado = 0;
                float valor = 0;
                float costo = 0;
                String query =  "select pcp.costofinal costo,\n" +
                "det.cantidad, det.valorunitario, det.tipdocto, con.codigo\n" +
                "from ctactecli c\n" +
                "left join ctacteclidet det on det.nrodocto = c.nrodocto and c.tipdocto = det.tipdocto and c.rut = det.rut and det.nrodocto = c.nrodocto\n" +
                "left join producto p on p.sku = det.sku\n" +
                "left join par_convenio con on con.codigo = p.convenio\n" +
                "left join producto_cpromedio pcp on pcp.sku = det.sku \n" +   //Linea nueva
                "where c.tipdocto in ('FEC','FAC','NCC')\n" +
                "and EXTRACT(YEAR FROM c.femision) = '"+ano+"'\n" +
                "and EXTRACT(MONTH FROM c.femision) = '"+mes+"' \n" +
                "and con.codigo is not null and con.codigo = "+rs_conteo.getString("codigo")+"\n";
                if(cbEcona.isSelected()){
                    query = query + "and c.rut = 76440015\n";
                }
                else {
                    query = query + "and c.rut <> 76440015\n";
                }
                query = query + "and c.rut <> 96726970";
                
                rs = sql.Select(query);
                
                if(rs.next()){
                    rs.beforeFirst();
                    for(int o = 0; rs.next(); o++){

                        if(rs.getString("tipdocto").equals("NCC")){
    //                        System.out.println(rs.getString("tipdocto")+": "+rs.getInt("cantidad")+" - " +rs.getInt("valorunitario")+" - "+rs.getInt("costo"));
                            valor = valor + (rs.getFloat("cantidad")*rs.getFloat("valorunitario"))*-1;
                            costo = costo + (rs.getFloat("cantidad")*rs.getFloat("costo"))*-1;
                        }
                        else {
    //                        System.out.println(rs.getString("tipdocto")+": "+rs.getInt("cantidad")+" - " +rs.getInt("valorunitario")+" - "+rs.getInt("costo"));
                            valor = valor + (rs.getFloat("cantidad")*rs.getFloat("valorunitario"));
                            costo = costo + (rs.getFloat("cantidad")*rs.getFloat("costo"));
                        }



                    }
                    resultado = ((valor - costo)/(valor))*100;
    //                if(resultado > 0){
                        model.addRow(new Object[]{
                            rs_conteo.getString("codigo"),
                            rs_conteo.getString("convenio"),
                            String.valueOf(Math.round(resultado)+" %")
                        });
                        registros = registros + 1;
                }
//                }}
//                System.out.println("asdasdsasd: "+Math.round(resultado)+" %");
            }
            documentoregistro.setText(String.valueOf(registros));

           
            
        } catch (SQLException ex) {
            Logger.getLogger(pfReportesMargen.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            sql.Close();
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

        jPanel4 = new javax.swing.JPanel();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Documentos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        documentoregistro = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMargenFacturas = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DetalleDocumento = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        documentodetalleregistro = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        cbAno = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbMes = new javax.swing.JComboBox<>();
        CargaButton = new javax.swing.JButton();
        cbEcona = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Ordenes = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        ordenesregistro = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMargenOrdenes = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        DetalleOrdenes = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        ordenesdetallesregistro = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lbcargando = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jMenuItem1.setText("Ficha Producto");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem3.setText("Copiar N° Documento");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem3);

        jMenuItem2.setText("Ficha Producto");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItem2);

        jMenuItem4.setText("Copiar N° Documento");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItem4);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Reporte Margen"));
        jPanel1.setToolTipText("");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Facturas"));

        Documentos.setAutoCreateRowSorter(true);
        Documentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Convenio", "Margen"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Documentos.getTableHeader().setReorderingAllowed(false);
        Documentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DocumentosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(Documentos);
        if (Documentos.getColumnModel().getColumnCount() > 0) {
            Documentos.getColumnModel().getColumn(0).setMinWidth(50);
            Documentos.getColumnModel().getColumn(0).setPreferredWidth(50);
            Documentos.getColumnModel().getColumn(0).setMaxWidth(50);
            Documentos.getColumnModel().getColumn(2).setMinWidth(60);
            Documentos.getColumnModel().getColumn(2).setPreferredWidth(60);
            Documentos.getColumnModel().getColumn(2).setMaxWidth(60);
        }

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Registros:");

        documentoregistro.setText("0");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Facturas Margen Total:");

        txtMargenFacturas.setText("0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(documentoregistro, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtMargenFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(documentoregistro)
                    .addComponent(jLabel7)
                    .addComponent(txtMargenFacturas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        DetalleDocumento.setAutoCreateRowSorter(true);
        DetalleDocumento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SKU", "Nombre", "$ Costo", "$ Unitario", "Convenio", "Cantidad", "N° Documento", "Tipo", "Margen", "Total Linea"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DetalleDocumento.setComponentPopupMenu(jPopupMenu1);
        DetalleDocumento.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(DetalleDocumento);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Registros:");

        documentodetalleregistro.setText("0");

        jButton1.setText("Exportar Excel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(documentodetalleregistro, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(documentodetalleregistro, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        cbAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022" }));

        jLabel1.setText("Año:");

        jLabel2.setText("Mes:");

        cbMes.setMaximumRowCount(12);
        cbMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE" }));
        cbMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMesActionPerformed(evt);
            }
        });

        CargaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/Actualiza.png"))); // NOI18N
        CargaButton.setText("Cargar");
        CargaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargaButtonActionPerformed(evt);
            }
        });

        cbEcona.setText("Econa");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(cbAno, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cbMes, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbEcona)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CargaButton, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(cbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbEcona))
                    .addComponent(CargaButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Ordenes"));

        Ordenes.setAutoCreateRowSorter(true);
        Ordenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Convenio", "Margen"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Ordenes.getTableHeader().setReorderingAllowed(false);
        Ordenes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                OrdenesMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(Ordenes);
        if (Ordenes.getColumnModel().getColumnCount() > 0) {
            Ordenes.getColumnModel().getColumn(0).setMinWidth(50);
            Ordenes.getColumnModel().getColumn(0).setPreferredWidth(50);
            Ordenes.getColumnModel().getColumn(0).setMaxWidth(50);
            Ordenes.getColumnModel().getColumn(2).setMinWidth(60);
            Ordenes.getColumnModel().getColumn(2).setPreferredWidth(60);
            Ordenes.getColumnModel().getColumn(2).setMaxWidth(60);
        }

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Registros:");

        ordenesregistro.setText("0");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Ordenes Margen Total:");

        txtMargenOrdenes.setText("0");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(ordenesregistro, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtMargenOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ordenesregistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addComponent(txtMargenOrdenes)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        DetalleOrdenes.setAutoCreateRowSorter(true);
        DetalleOrdenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SKU", "Nombre", "$ Costo", "$ Unitario", "Convenio", "Cantidad", "Codigo OC", "Margen", "Total Linea"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DetalleOrdenes.setComponentPopupMenu(jPopupMenu2);
        DetalleOrdenes.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(DetalleOrdenes);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Registros:");

        ordenesdetallesregistro.setText("0");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addContainerGap())
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(ordenesdetallesregistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(263, 263, 263))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(ordenesdetallesregistro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(52, 52, 52))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbcargando.setText("Calculando Márgenes");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbcargando, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbcargando)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMesActionPerformed
    // TODO add your handling code here:
    }//GEN-LAST:event_cbMesActionPerformed

    public void CargaMargenTotalDocumentos() {
        int mes = cbMes.getSelectedIndex()+1;
        String ano = cbAno.getSelectedItem().toString();
        ExeSql sql = new ExeSql();
        ResultSet rs = null;
        String query =  "select pcp.costofinal costo,\n" +
                        "det.cantidad, det.valorunitario, det.tipdocto\n" +
                        "from ctactecli c\n" +
                        "left join ctacteclidet det on det.nrodocto = c.nrodocto and c.tipdocto = det.tipdocto and c.rut = det.rut and det.nrodocto = c.nrodocto\n" +
                        "left join producto p on p.sku = det.sku\n" +
                        "left join par_convenio con on con.codigo = p.convenio\n" +
                        "left join producto_cpromedio pcp on pcp.sku = det.sku \n" +  //Linea nueva
                        "where c.tipdocto in ('FEC','FAC','NCC')\n" +
                        "and EXTRACT(YEAR FROM c.femision) = '"+ano+"'\n" +
                        "and EXTRACT(MONTH FROM c.femision) = '"+mes+"' \n" +
                        "and con.codigo is not null\n";
                        if(cbEcona.isSelected()){
                            query = query + "and c.rut = 76440015\n";
                        }
                        else {
                            query = query + "and c.rut <> 76440015\n";
                        }
                        query = query + "and c.rut <> 96726970";
        try {
            rs = sql.Select(query);
            double valor = 0;
            double costo = 0;
            for(int i = 0; rs.next(); i++) {
                if(rs.getString("tipdocto").equals("NCC")){
//                    System.out.println(rs.getString("tipdocto")+": "+rs.getInt("cantidad")+" - " +rs.getInt("valorunitario")+" - "+rs.getInt("costo"));
                    valor = valor + (rs.getDouble("cantidad")*rs.getDouble("valorunitario"))*-1;
                    costo = costo + (rs.getDouble("cantidad")*rs.getDouble("costo"))*-1;
                    
                }
                else {
//                    System.out.println(rs.getString("tipdocto")+": "+rs.getInt("cantidad")+" - " +rs.getInt("valorunitario")+" - "+rs.getInt("costo"));
                    valor = valor + (rs.getDouble("cantidad")*rs.getDouble("valorunitario"));
                    costo = costo + (rs.getDouble("cantidad")*rs.getDouble("costo"));
                    
                }
            }
            DecimalFormat df2 = new DecimalFormat("#.##");
            double resultado = ((valor - costo)/(valor))*100;
            txtMargenFacturas.setText(String.valueOf(df2.format(resultado))+" %");

        } catch (SQLException ex) {
            Logger.getLogger(pfReportesMargen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void CargaMargenTotalOrdenes() {
        int mes = cbMes.getSelectedIndex()+1;
        String ano = cbAno.getSelectedItem().toString();
        ExeSql sql = new ExeSql();
        ResultSet rs = null;
        String query = "select pcp.costofinal costo,\n" +
                        "det.cantidad, det.valorunitario\n" +
                        "from occh c\n" +
                        "left join occhdet det on c.orden = det.orden and c.codigo_oc = det.codigo_oc and c.rut = det.rut left join producto p on p.sku = det.sku\n" +
                        "left join par_convenio con on con.codigo = p.convenio\n" +
                        "left join producto_cpromedio pcp on pcp.sku = det.sku \n" +  //Linea nueva
                        "where EXTRACT(YEAR FROM c.femision) = '"+ano+"'\n" +
                        "and EXTRACT(MONTH FROM c.femision) = '"+mes+"' \n" +
                        "and con.codigo is not null\n";
                        if(cbEcona.isSelected()){
                            query = query + "and c.rut = 76440015\n";
                        }
                        else {
                            query = query + "and c.rut <> 76440015\n";
                        }
        try {
            rs = sql.Select(query);
            double valor = 0;
            double costo = 0;
            for(int i = 0; rs.next(); i++) {
//                    System.out.println(rs.getString("tipdocto")+": "+rs.getInt("cantidad")+" - " +rs.getInt("valorunitario")+" - "+rs.getInt("costo"));
                valor = valor + (rs.getDouble("cantidad")*rs.getDouble("valorunitario"));
                costo = costo + (rs.getDouble("cantidad")*rs.getDouble("costo"));                   
            }
            DecimalFormat df2 = new DecimalFormat("#.##");
            double resultado = ((valor - costo)/(valor))*100;
            txtMargenOrdenes.setText(String.valueOf(df2.format(resultado))+" %");
        } catch (SQLException ex) {
            Logger.getLogger(pfReportesMargen.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            sql.Close();
        }
    }
    
    private void CargaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargaButtonActionPerformed
        Runnable miRunnable = new Runnable() {
            public void run() {
                try{
                    Limpiar();
                    lbcargando.setText("Calculando Márgenes.....");
                    txtMargenFacturas.setText("Calculando.....");
                    txtMargenOrdenes.setText("Calculando.....");
                    documentoregistro.setText("Calculando.....");
                    documentodetalleregistro.setText("Calculando.....");
                    ordenesdetallesregistro.setText("Calculando.....");
                    ordenesregistro.setText("Calculando.....");
                    URL urlInfo =  this.getClass().getResource("/Iconos16/wait.gif");
                    ImageIcon IconoInfo =  new ImageIcon(urlInfo); 
                    lbcargando.setIcon(IconoInfo);
                    lbcargando.setForeground(Color.red);
                    Documentos.setFocusable(false);
                    Ordenes.setFocusable(false);
                    CargaButton.setEnabled(false);
                    cbMes.setEnabled(false);
                    Documentos.setRowSelectionAllowed(false);
                    Ordenes.setRowSelectionAllowed(false);
                    lbcargando.setVisible(true);
                    CargaDocumentos();
                    CargaOrdenes();
                    CargaMargenTotalDocumentos();
                    CargaMargenTotalOrdenes();          
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    CargaButton.setEnabled(true);
                    Documentos.setFocusable(true);
                    Ordenes.setFocusable(true);
                    cbMes.setEnabled(true);
                    Documentos.setRowSelectionAllowed(true);
                    Ordenes.setRowSelectionAllowed(true);
                    lbcargando.setVisible(false);
                    documentodetalleregistro.setText("Seleccione convenio");
                    ordenesdetallesregistro.setText("Seleccione convenio");
                }
            }
        };
        
        Thread hilo = new Thread(miRunnable);
        hilo.start();
//        if(!hilo.getState()==Thread.State.TERMINATED){
//            CargaButton.setEnabled(true);
//            Documentos.setFocusable(true);
//            Ordenes.setFocusable(true);
//            cbMes.setEnabled(true);
//            Documentos.setRowSelectionAllowed(true);
//            Ordenes.setRowSelectionAllowed(true);
//        }

    }//GEN-LAST:event_CargaButtonActionPerformed

    private void DocumentosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DocumentosMousePressed
        CargaDetalleDocumentos();
    }//GEN-LAST:event_DocumentosMousePressed

    private void OrdenesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrdenesMousePressed
        CargaDetalleOrdenes();        // TODO add your handling code here:
    }//GEN-LAST:event_OrdenesMousePressed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        String SKU = DetalleDocumento.getValueAt(DetalleDocumento.getSelectedRow(), 0).toString().trim();      
        pfProductos Pro = new pfProductos();
        pnPestanas.addTab("Ficha Producto", Pro);
        PanelTab btc=new PanelTab(pnPestanas,0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(Pro), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount()-1);
        Pro.txSku.requestFocus();
        Pro.txSku.setText(SKU);
        Pro.btIr.doClick();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        String SKU = DetalleOrdenes.getValueAt(DetalleOrdenes.getSelectedRow(), 0).toString().trim();      
        pfProductos Pro = new pfProductos();
        pnPestanas.addTab("Ficha Producto", Pro);
        PanelTab btc=new PanelTab(pnPestanas,0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(Pro), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount()-1);
        Pro.txSku.requestFocus();
        Pro.txSku.setText(SKU);
        Pro.btIr.doClick();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        StringSelection Voucher = new StringSelection(DetalleDocumento.getValueAt(DetalleDocumento.getSelectedRow(), 6).toString().trim());
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        cb.setContents(Voucher, null);
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        StringSelection Voucher = new StringSelection(DetalleOrdenes.getValueAt(DetalleOrdenes.getSelectedRow(), 6).toString().trim());
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        cb.setContents(Voucher, null);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (DetalleDocumento.getRowCount() > 0) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
                chooser.setFileFilter(filter);
                chooser.setDialogTitle("Guardar archivo");
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    List tb = new ArrayList();
                    List nom = new ArrayList();
                    tb.add(DetalleDocumento);
                    nom.add("Reporte Margen");
                    String file = chooser.getSelectedFile().toString().concat(".xls");
                    try {
                       Exporter e = new Exporter(new File(file), tb, nom);
                        if (e.export()) {
                            JOptionPane.showMessageDialog(null, "Los datos fueron exportados en el directorio seleccionado", "Mensaje de Informacion", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Hubo un error " + e.getMessage(), " Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
                JOptionPane.showMessageDialog(this, "No hay datos para exportar","Mensaje de error",JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void CargaOrdenes() {
        DefaultTableModel model = (DefaultTableModel) Ordenes.getModel();
        while(model.getRowCount()>0){
            model.removeRow(0);
        }
        DefaultTableModel model_det = (DefaultTableModel) DetalleOrdenes.getModel();
        while(model_det.getRowCount()>0){
            model_det.removeRow(0);
        }
        int mes = cbMes.getSelectedIndex()+1;
        String ano = cbAno.getSelectedItem().toString();
        ExeSql sql = new ExeSql();
        
        ResultSet rs = null;
        ResultSet rs_conteo = null;
        ResultSet producto;
        int registros = 0;
        try {
            rs_conteo = sql.Select("select codigo, convenio from par_convenio order by codigo asc");
            for(int i = 0; rs_conteo.next(); i++){
                float resultado = 0;
                float valor = 0;
                float costo = 0;
                String query =  "select pcp.costofinal costo,\n" +
                "det.cantidad, det.valorunitario, det.totlinea\n" +
                "from occh c\n" +
                "left join occhdet det on c.orden = det.orden and c.codigo_oc = det.codigo_oc and c.rut = det.rut \n" +
                "left join producto p on p.sku = det.sku\n" +
                "left join par_convenio con on con.codigo = p.convenio\n" +
                "left join producto_cpromedio pcp on pcp.sku = det.sku \n" +  //Linea nueva
                "where EXTRACT(YEAR FROM c.femision) = '"+ano+"'\n" +
                "and EXTRACT(MONTH FROM c.femision) = '"+mes+"' \n" +
                "and con.codigo is not null and con.codigo = "+rs_conteo.getString("codigo")+"\n";
                if(cbEcona.isSelected()){
                    query = query + "and c.rut = 76440015\n";
                }
                else {
                    query = query + "and c.rut <> 76440015\n";
                }
                query = query + "and c.rut <> 96726970";
                rs = sql.Select(query);
                if(rs.next()){
                    rs.beforeFirst();
                    for(int o = 0; rs.next(); o++){


                            valor = valor + (rs.getFloat("cantidad")*rs.getFloat("valorunitario"));
                            costo = costo + (rs.getFloat("cantidad")*rs.getFloat("costo"));




                    }
                    resultado = ((valor - costo)/(valor))*100;
    //                if(resultado > 0){
                    model.addRow(new Object[]{
                        rs_conteo.getString("codigo"),
                        rs_conteo.getString("convenio"),
                        String.valueOf(Math.round(resultado)+" %")
                    });
                    registros = registros + 1;
                }
//                System.out.println("asdasdsasd: "+Math.round(resultado)+" %");
            }
            ordenesregistro.setText(String.valueOf(registros));
        } catch (SQLException ex) {
            Logger.getLogger(pfReportesMargen.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            sql.Close();
        }
    }
    public void CargaDetalleOrdenes() {
        int mes = cbMes.getSelectedIndex()+1;
        String ano = cbAno.getSelectedItem().toString();
        String convenio = Ordenes.getValueAt(Ordenes.getSelectedRow(),0).toString();
        ExeSql sql = new ExeSql();
        ExeSqlLuvaly luv = new ExeSqlLuvaly();
        ResultSet rs = null;
        ResultSet producto;
        String query =  "select det.sku, p.nombre, \n" +
                        "pcp.costofinal costofinal \n" +
                        "\n" +
                        ", p.pventa ,con.convenio,det.cantidad, c.totaldocto, c.codigo_oc, c.orden,\n" +
                        "round((((det.cantidad* det.valorunitario) - (det.cantidad *  (pcp.costofinal))) / (det.cantidad * det.valorunitario))*100) margen,\n" +
                        "det.totlinea, det.valorunitario\n" +
                        "from occh c\n" +
                        "left join occhdet det on det.orden = c.orden and det.codigo_oc = c.codigo_oc\n" +
                        "left join producto p on p.sku = det.sku\n" +
                        "left join par_convenio con on con.codigo = p.convenio\n" +
                        "left join producto_cpromedio pcp on pcp.sku = det.sku \n" +  //Linea nueva
                        "where EXTRACT(YEAR FROM c.femision) = '"+ano+"'\n" +
                        "and EXTRACT(MONTH FROM c.femision) = '"+mes+"' \n" +
                        "and con.codigo is not null\n" +
                        "and con.codigo = "+convenio+"\n";
                        if(cbEcona.isSelected()){
                            query = query + "and c.rut = 76440015\n";
                        }
                        else {
                            query = query + "and c.rut <> 76440015\n";
                        }

        try {
            int registro = 0;
            rs = sql.Select(query);
            DefaultTableModel model = (DefaultTableModel) DetalleOrdenes.getModel();
            while(model.getRowCount()>0){
                model.removeRow(0);
            }
            for(int i = 0; rs.next(); i++){
                producto =  luv.Select("select p.nombre,trim(u.unidad) as unidad, u.um from producto p \n"
                                     + "LEFT JOIN par_unidad u ON u.codigo=p.unidad\n "
                                     + "where p.sku = '"+rs.getString("sku").trim()+"'");
                producto.next();
                model.addRow(new Object[]{
                  rs.getString("sku"),
                  producto.getString("nombre"),
                  Math.round(rs.getFloat("costofinal")),
                  Math.round(rs.getFloat("valorunitario")),
                  rs.getString("convenio"),
                  rs.getInt("cantidad"),
                  rs.getInt("codigo_oc")+"-"+rs.getString("orden"),
                  rs.getString("margen")+" %",
                  Math.round(rs.getFloat("totlinea"))
                });    
                registro = registro + 1;
            }
            ordenesdetallesregistro.setText(String.valueOf(registro));
        } catch (SQLException ex) {
            Logger.getLogger(pfReportesMargen.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            sql.Close();
        }
    }
    public void CargaDetalleDocumentos() {
        int mes = cbMes.getSelectedIndex()+1;
        String ano = cbAno.getSelectedItem().toString();
        String convenio = Documentos.getValueAt(Documentos.getSelectedRow(),0).toString();
        ExeSql sql = new ExeSql();
        ExeSqlLuvaly luv = new ExeSqlLuvaly();
        ResultSet rs = null;
        ResultSet producto;
        String query =  "select det.sku,con.convenio, p.nombre, \n" +
                        "pcp.costofinal costo,\n" +
                        "det.valorunitario, det.cantidad, det.nrodocto, det.tipdocto, det.totallinea\n" +
                        "from ctactecli c\n" +
                        "left join ctacteclidet det on det.nrodocto = c.nrodocto and c.tipdocto = det.tipdocto and c.rut = det.rut and det.nrodocto = c.nrodocto\n" +
                        "left join producto p on p.sku = det.sku\n" +
                        "left join par_convenio con on con.codigo = p.convenio\n" +
                        "left join producto_cpromedio pcp on pcp.sku = det.sku \n" +  //Linea nueva
                        "where c.tipdocto in ('FEC','FAC','NCC')\n" +
                        "and EXTRACT(YEAR FROM c.femision) = '"+ano+"'\n" +
                        "and EXTRACT(MONTH FROM c.femision) = '"+mes+"' \n" +
                        "and con.codigo is not null and con.codigo = "+convenio+"\n";
                        if(cbEcona.isSelected()){
                            query = query + "and c.rut = 76440015\n";
                        }
                        else {
                            query = query + "and c.rut <> 76440015\n";
                        }
        try {
            int registros = 0;
            rs = sql.Select(query);
            DefaultTableModel model = (DefaultTableModel) DetalleDocumento.getModel();
            while(model.getRowCount()>0){
                model.removeRow(0);
            }
            float valor = 0;
            float costo = 0;
            for(int i = 0; rs.next(); i++) {
                producto =  luv.Select("select p.nombre,trim(u.unidad) as unidad, u.um from producto p \n"
                              + "LEFT JOIN par_unidad u ON u.codigo=p.unidad\n "
                              + "where p.sku = '"+rs.getString("sku").trim()+"'");
                producto.next();
//                    System.out.println(rs.getString("tipdocto")+": "+rs.getInt("cantidad")+" - " +rs.getInt("valorunitario")+" - "+rs.getInt("costo"));
                valor =  (rs.getFloat("cantidad")*rs.getFloat("valorunitario"));
                costo =  (rs.getFloat("cantidad")*rs.getFloat("costo"));

                
                float resultado = ((valor - costo)/(valor))*100;
                if(rs.getString("tipdocto").equals("NCC")){
                    resultado = resultado*-1;
                }
                model.addRow(new Object[]{
                    rs.getString("sku"),
                    producto.getString("nombre"),
                    Math.round(rs.getFloat("costo")),
                    Math.round(rs.getFloat("valorunitario")),
                    rs.getString("convenio"),
                    rs.getString("cantidad"),
                    rs.getString("nrodocto"),
                    rs.getString("tipdocto"),
                    String.valueOf(Math.round(resultado))+" %",
                    Math.round(rs.getFloat("totallinea"))
                
                });
                registros = registros + 1;
            }
            
            documentodetalleregistro.setText(String.valueOf(registros));
        } catch (SQLException ex) {
            Logger.getLogger(pfReportesMargen.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            sql.Close();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton CargaButton;
    private javax.swing.JTable DetalleDocumento;
    private javax.swing.JTable DetalleOrdenes;
    private javax.swing.JTable Documentos;
    private javax.swing.JTable Ordenes;
    private javax.swing.JComboBox<String> cbAno;
    private javax.swing.JCheckBox cbEcona;
    private javax.swing.JComboBox<String> cbMes;
    private javax.swing.JLabel documentodetalleregistro;
    private javax.swing.JLabel documentoregistro;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbcargando;
    private javax.swing.JLabel ordenesdetallesregistro;
    private javax.swing.JLabel ordenesregistro;
    private javax.swing.JLabel txtMargenFacturas;
    private javax.swing.JLabel txtMargenOrdenes;
    // End of variables declaration//GEN-END:variables
}
