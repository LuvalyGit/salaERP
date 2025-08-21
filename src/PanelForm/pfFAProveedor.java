package PanelForm;

import Conexion.ExeSql;
import Conexion.ExeSqlLuvaly;
import Dialogos.*;
import Formularios.fmMain;
import static Formularios.fmMain.ccosto_usr;
import static Formularios.fmMain.pnPestanas;
import Utilidades.ComboCodigos;
import Utilidades.PanelTab;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class pfFAProveedor extends javax.swing.JPanel {
    
    
    //***************Variables Costo Promedio *****************//  
   
    ArrayList<ArrayList<String>> Lista  = new ArrayList<ArrayList<String>>();
            
 //*************************************************************//   
    
    public static int intNivelUsuario = 0;   //Variable nueva
    private ExeSql  Sql = new ExeSql();
    String RutMaster;
    String RutBusca;
    int Tipo; // 0::Nuevo    1:Abrir
    int PesoCorreccion=0;
    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    private enum Columnas{Sku,Nombre,UM,Cantidad,Unitario,Total,CantReal,UniReal};
    int PosTipoPago;
    boolean EsElectronica;
    DefaultListModel dfListModel = new DefaultListModel();
    double SumadorNeto,SumadorIva;
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
 
    public pfFAProveedor() {
        Tipo=-99;
        initComponents();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        Grilla.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        Grilla.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        Grilla.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        CargaTipoPago();
        cbTipoPagoId.setVisible(false);
        SetTipo(-1);

//        cbCodigoOc.setSelectedIndex(-1);
//        cbNroOrden.setSelectedIndex(-1);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnMenu = new javax.swing.JPanel();
        btAbrir = new javax.swing.JButton();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        btAutorizar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btGuardar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btPagos = new javax.swing.JButton();
        btNuevo = new javax.swing.JButton();
        btReversar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lbFolio = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dtEmision = new org.jdesktop.swingx.JXDatePicker();
        jLabel16 = new javax.swing.JLabel();
        dtRecepcion = new org.jdesktop.swingx.JXDatePicker();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txDias = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txEstado = new javax.swing.JTextField();
        txOrden = new javax.swing.JTextField();
        txNroFactura = new javax.swing.JTextField();
        btIrFactura = new javax.swing.JButton();
        txCuotas = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txEstadoPago = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbTipoPago = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstGuias = new javax.swing.JList();
        btAgregaGuia = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        chkElectronica = new javax.swing.JCheckBox();
        btProd = new javax.swing.JButton();
        btEliminaGuia = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Grilla = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txExento = new javax.swing.JTextField();
        txNeto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txIva = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txTotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txImpEspecifico = new javax.swing.JTextField();
        txComision = new javax.swing.JTextField();
        lbComision = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txDv = new javax.swing.JTextField();
        btIr = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txRut = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txNombre = new javax.swing.JTextField();
        cbTipoPagoId = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        GrillaDoc = new javax.swing.JTable();
        btCorrigePeso = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        pnMenu.setBackground(new java.awt.Color(220, 215, 226));

        btAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/open16.png"))); // NOI18N
        btAbrir.setText("Abrir");
        btAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAbrirActionPerformed(evt);
            }
        });

        jXLabel1.setForeground(new java.awt.Color(0, 51, 0));
        jXLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/OCProveedor24.png"))); // NOI18N
        jXLabel1.setText("FACTURA PROVEEDOR");
        jXLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        btAutorizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/ok_16.png"))); // NOI18N
        btAutorizar.setText("Autorizar");
        btAutorizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAutorizarActionPerformed(evt);
            }
        });

        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/Pencil16.png"))); // NOI18N
        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });

        btGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/save16.png"))); // NOI18N
        btGuardar.setText("Guardar");
        btGuardar.setEnabled(false);
        btGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarActionPerformed(evt);
            }
        });

        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/Cancel16.png"))); // NOI18N
        btCancelar.setText("Cancelar");
        btCancelar.setEnabled(false);
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        btPagos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/Peso16.png"))); // NOI18N
        btPagos.setText("Pagos");
        btPagos.setEnabled(false);
        btPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPagosActionPerformed(evt);
            }
        });

        btNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/New.png"))); // NOI18N
        btNuevo.setText("Nuevo");
        btNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevoActionPerformed(evt);
            }
        });

        btReversar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/erase16.png"))); // NOI18N
        btReversar.setText("Reversar");
        btReversar.setEnabled(false);
        btReversar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReversarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107)
                .addComponent(btGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123)
                .addComponent(btPagos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btAutorizar)
                .addGap(70, 70, 70)
                .addComponent(btReversar)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAbrir)
                    .addComponent(btAutorizar)
                    .addComponent(btEditar)
                    .addComponent(btGuardar)
                    .addComponent(btCancelar)
                    .addComponent(btPagos)
                    .addComponent(btNuevo)
                    .addComponent(btReversar))
                .addContainerGap())
        );

        add(pnMenu);

        jPanel1.setBackground(new java.awt.Color(220, 215, 226));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel4.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPanel4PropertyChange(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("NRO FACTURA");

        lbFolio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbFolio.setForeground(new java.awt.Color(255, 51, 51));

        jLabel5.setText("F. Emisión");

        dtEmision.setEnabled(false);

        jLabel16.setText("F.Recepción");

        dtRecepcion.setEnabled(false);

        jLabel17.setText("Cuotas");

        jLabel18.setText("Días");

        txDias.setEditable(false);
        txDias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txDiasMouseClicked(evt);
            }
        });
        txDias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txDiasActionPerformed(evt);
            }
        });

        jLabel19.setText("Orden de Compra");

        jLabel6.setText("Estado");

        txEstado.setEditable(false);

        txOrden.setEditable(false);
        txOrden.setEnabled(false);

        txNroFactura.setEnabled(false);
        txNroFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txNroFacturaKeyPressed(evt);
            }
        });

        btIrFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos22/Go.png"))); // NOI18N
        btIrFactura.setBorderPainted(false);
        btIrFactura.setEnabled(false);
        btIrFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIrFacturaActionPerformed(evt);
            }
        });

        txCuotas.setEditable(false);
        txCuotas.setEnabled(false);
        txCuotas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txCuotasKeyTyped(evt);
            }
        });

        jLabel7.setText("Estado Pago");

        txEstadoPago.setEditable(false);

        jLabel3.setText("Forma de Pago");

        cbTipoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoPagoActionPerformed(evt);
            }
        });

        jLabel4.setText("Guias");

        lstGuias.setBackground(new java.awt.Color(233, 232, 232));
        lstGuias.setEnabled(false);
        lstGuias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstGuiasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstGuias);

        btAgregaGuia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/Agregar.png"))); // NOI18N
        btAgregaGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregaGuiaActionPerformed(evt);
            }
        });

        jLabel8.setText("Electrónica");

        chkElectronica.setSelected(true);
        chkElectronica.setEnabled(false);
        chkElectronica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkElectronicaActionPerformed(evt);
            }
        });

        btProd.setText("Despliega Productos");
        btProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProdActionPerformed(evt);
            }
        });

        btEliminaGuia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/Eliminar.png"))); // NOI18N
        btEliminaGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEliminaGuiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txNroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btIrFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel16)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txEstadoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dtEmision, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(dtRecepcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel17)
                                .addComponent(jLabel19))
                            .addComponent(lbFolio)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(chkElectronica)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18))
                            .addComponent(txOrden))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txDias, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbTipoPago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btProd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btAgregaGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEliminaGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(lbFolio))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btEliminaGuia, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btAgregaGuia)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btProd)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel5)
                                        .addComponent(dtEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txNroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btIrFactura))
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel19)
                                        .addComponent(txOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(jLabel6)
                                            .addComponent(txEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel16)
                                            .addComponent(dtRecepcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(jLabel7)
                                            .addComponent(txEstadoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3)
                                            .addComponent(cbTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8)
                                            .addComponent(chkElectronica)))
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel17)
                                        .addComponent(jLabel18)
                                        .addComponent(txDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );

        Grilla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "UM", "Cantidad", "V. Unitario", "Total Linea", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        jScrollPane1.setViewportView(Grilla);
        if (Grilla.getColumnModel().getColumnCount() > 0) {
            Grilla.getColumnModel().getColumn(0).setResizable(false);
            Grilla.getColumnModel().getColumn(0).setPreferredWidth(60);
            Grilla.getColumnModel().getColumn(1).setResizable(false);
            Grilla.getColumnModel().getColumn(1).setPreferredWidth(350);
            Grilla.getColumnModel().getColumn(2).setResizable(false);
            Grilla.getColumnModel().getColumn(2).setPreferredWidth(30);
            Grilla.getColumnModel().getColumn(3).setResizable(false);
            Grilla.getColumnModel().getColumn(3).setPreferredWidth(60);
            Grilla.getColumnModel().getColumn(4).setResizable(false);
            Grilla.getColumnModel().getColumn(4).setPreferredWidth(60);
            Grilla.getColumnModel().getColumn(5).setResizable(false);
            Grilla.getColumnModel().getColumn(5).setPreferredWidth(80);
            Grilla.getColumnModel().getColumn(6).setMinWidth(0);
            Grilla.getColumnModel().getColumn(6).setPreferredWidth(0);
            Grilla.getColumnModel().getColumn(6).setMaxWidth(0);
            Grilla.getColumnModel().getColumn(7).setMinWidth(0);
            Grilla.getColumnModel().getColumn(7).setPreferredWidth(0);
            Grilla.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel11.setText("Exento");

        txExento.setEditable(false);
        txExento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txExento.setText("0");

        txNeto.setEditable(false);
        txNeto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txNeto.setText("0");
        txNeto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txNetoMouseClicked(evt);
            }
        });

        jLabel10.setText("Neto");

        txIva.setEditable(false);
        txIva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txIva.setText("0");
        txIva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txIvaMouseClicked(evt);
            }
        });

        jLabel12.setText("I.V.A.");

        txTotal.setEditable(false);
        txTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txTotal.setText("0");

        jLabel13.setText("TOTAL");

        jLabel14.setText("Imp. específico");

        txImpEspecifico.setEditable(false);
        txImpEspecifico.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txImpEspecifico.setText("0");

        txComision.setEditable(false);
        txComision.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txComision.setText("0");
        txComision.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbComision.setText("Comision");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(lbComision))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txComision)
                    .addComponent(txNeto)
                    .addComponent(txIva)
                    .addComponent(txExento, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(txTotal)
                    .addComponent(txImpEspecifico))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txComision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbComision))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txExento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txNeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txImpEspecifico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txDv.setEditable(false);

        btIr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos22/Go.png"))); // NOI18N
        btIr.setBorderPainted(false);
        btIr.setEnabled(false);
        btIr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIrActionPerformed(evt);
            }
        });

        jLabel1.setText("Rut");

        txRut.setEnabled(false);
        txRut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txRutKeyPressed(evt);
            }
        });

        jLabel2.setText("Nombre");

        txNombre.setEditable(false);
        txNombre.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txRut, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txDv, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btIr, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(239, 239, 239))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btIr)
                    .addComponent(txDv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GrillaDoc.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        GrillaDoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Docto.", "Nro", "Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Object.class
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
        GrillaDoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GrillaDocMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(GrillaDoc);

        btCorrigePeso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/Peso16.png"))); // NOI18N
        btCorrigePeso.setText("Corrección");
        btCorrigePeso.setEnabled(false);
        btCorrigePeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCorrigePesoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(cbTipoPagoId, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btCorrigePeso, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(cbTipoPagoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btCorrigePeso)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents
static private int GetCol(String Col){
        return Columnas.valueOf(Col).ordinal();
    }

private void CargaAdministrador(){
    
   int intNivelUsuario =0;
   
   intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if (((ccosto_usr!=4) ) && (intNivelUsuario<80)){
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado") ;
            btReversar.setEnabled(false);
            return;}
        else
            {
             btReversar.setEnabled(true);
                    }
    
//    if(fmMain.GetUsuarioAdministrador()){
//        btReversar.setEnabled(true);
//    }
//    else
//        btReversar.setEnabled(false);
}
//--------------------------------------------------------------------------------
// Funcion SET DE TIPOS
//--------------------------------------------------------------------------------
    private void SetTipo(int ElTipo){
        // NADA
        if(ElTipo==-1){
          /*Botones*/  
          btGuardar.setEnabled(false);
          btCancelar.setEnabled(false);
          btAbrir.setEnabled(true);
          btNuevo.setEnabled(true);
          btAutorizar.setEnabled(false);
          btEditar.setEnabled(false);
          
          fmMain.SetEstado(fmMain.pnPestanas.getSelectedIndex(),0);
          Habilita(false);
          Edicion(false);
          Limpia();
          Tipo=-1;
          
        }
        // NUEVA FACTURA
        else if(ElTipo==1){
           btCancelar.setEnabled(true);
           btGuardar.setEnabled(true);
           btAbrir.setEnabled(false);
           btEditar.setEnabled(false);
           btNuevo.setEnabled(false);
           Limpia();
           Habilita(false);
           Edicion(false);
           txRut.setEnabled(true);
           txRut.setEditable(true);
           btIr .setEnabled(true);
           txEstado.setText("PENDIENTE");
           txEstadoPago.setText("VIGENTE");
           txRut.requestFocus();
           Tipo=1;
        }
        //Abrir Factura
        else if(ElTipo==2){
           Limpia();
           Habilita(false);
           Edicion(false);
           txRut.setEnabled(true);
           txRut.setEditable(true);
           btIr .setEnabled(true);
           txRut.requestFocus();
           Tipo=2;
        }
        else if(ElTipo==3){
            Habilita(true);
            Edicion(true);
        }
            
    }
//--------------------------------------------------------------------------------
// EDICION
//--------------------------------------------------------------------------------
private void Edicion(boolean Estado){

    dtEmision.setEditable(Estado);
    dtRecepcion.setEditable(Estado);
    

}
//--------------------------------------------------------------------------------
// LIMPIA
//--------------------------------------------------------------------------------
private void Limpia(){
    
    DefaultComboBoxModel dfCm = new DefaultComboBoxModel();
    DefaultTableModel   dfTm = (DefaultTableModel) Grilla.getModel();
    DefaultTableModel TableModel = (DefaultTableModel) GrillaDoc.getModel();
    
    txRut.setText("");
    txNombre.setText("");
    txDv.setText("");
    txNeto.setText("");
    txComision.setText("");
    txExento.setText("");
    txIva.setText("");
    txTotal.setText("");
    txCuotas.setText("");
    txNroFactura.setText("");
    
    txEstado.setText("");
    txEstadoPago.setText("");
    dtEmision.setDate(null);
    dtRecepcion.setDate(null);
    txOrden.setText("");
    txCuotas.setText("");
    txDias.setText("");
    cbTipoPago.setSelectedIndex(0);
    lstGuias.removeAll();
    
    //Bloquea Campo Nro Factura
    
    txNroFactura.setEnabled(false);
           
    while(dfTm.getRowCount()>0)
        dfTm.removeRow(0);
    dfListModel.removeAllElements();
    
   //Limpia Grilla Documentos Relacionados
    while(TableModel.getRowCount()>0)
         TableModel.removeRow(0);
    
    txRut.requestFocus();
    
    
    //chbPrioridad.
    
}    
//--------------------------------------------------------------------------------
// HABILITA
//--------------------------------------------------------------------------------
private void Habilita(boolean Estado){
    txRut.setEnabled(Estado);
    txNombre.setEnabled(Estado);
    txDv.setEnabled(Estado);
  
    dtEmision.setEnabled(Estado);
    dtRecepcion.setEnabled(Estado);
    txNeto.setEnabled(Estado);
    txComision.setEnabled(Estado);
    txExento.setEnabled(Estado);
    txIva.setEnabled(Estado);
    txImpEspecifico.setEnabled(Estado);
    txTotal.setEnabled(Estado);
    btCorrigePeso.setEnabled((Estado));
    
    txEstado.setEnabled(Estado);
    txCuotas.setEnabled(Estado);
    dtEmision.setEnabled(Estado);
    txOrden.setEnabled(Estado);
    txDias.setEnabled(Estado);
    txEstadoPago.setEnabled(Estado);
    cbTipoPago.setEnabled(Estado);
    lstGuias.setEnabled(Estado);
    
    btIr.setEnabled(Estado);
    btAgregaGuia.setEnabled(Estado);
    chkElectronica.setEnabled(Estado);
    
    if(!Estado)
        lstGuias.setBackground(Color.LIGHT_GRAY);
    else
        lstGuias.setBackground(Color.WHITE);
}
//--------------------------------------------------------------------------------
// Funcion CODIGO desde COMBOBOX
//--------------------------------------------------------------------------------
    private int cbId_Accion(JComboBox Combo ){
        int Codigo=-1;
    if (Combo.getSelectedIndex()>=0){
        ComboCodigos id = (ComboCodigos) Combo.getSelectedItem();
        Codigo = id.getId();
        String nombre = Combo.getSelectedItem().toString();
        Combo.hidePopup();
    } 
    return Codigo;
}
private void CargaTipoPago(){
    ExeSql Sql = new ExeSql();
    ResultSet Rs;
    
    cbTipoPago.removeAllItems();
    cbTipoPagoId.removeAllItems();
    
    try {
        Rs = Sql.Select("select codigo,nombre\n" +
                        "from par_general \n" +
                        "where tipo='TIPOPAGOPRV'\n" +
                        "and vigente=1\n" +
                        "order by codigo");
        while(Rs.next()){
            cbTipoPago.addItem(Rs.getString("nombre"));
            cbTipoPagoId.addItem(Rs.getString("codigo"));
        }
    } catch (Exception e) {
        fmMain.Mensaje(e.getMessage());
    } finally{
        Sql.Close();
    }
}
    
    private void txRutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txRutKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            btIr.doClick();
        }
    }//GEN-LAST:event_txRutKeyPressed
    private void btAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAbrirActionPerformed
        txRut.setEnabled(true);
        txRut.setEditable(true);
        btIr.setEnabled(true);
        txRut.requestFocus();
        SumadorIva=0;  //Utilizado para ajustar totales
        SumadorNeto=0;//Utilizado para ajustar totales
        SetTipo(2);
    }//GEN-LAST:event_btAbrirActionPerformed
    private void AbrirFactura(String Rut,String Numero){
        ExeSql Sql = new ExeSql();
        ExeSqlLuvaly luv = new ExeSqlLuvaly();
        ResultSet producto = null;
        ResultSet Rs;
        DefaultTableModel TableModel = (DefaultTableModel) Grilla.getModel();
        
        try {
            
            Rs = Sql.Select("select case p.autoriza when 1 then 'AUTORIZADO' else 'PENDIENTE' end as estado,  case p.comision when null then 0 else p.comision end as comision, p.femision,p.frecepcion,p.nrodocorigen,p.autoriza,\n" +
                            "(select cuotas from ctacteprv where tipdocto='OCP' and nrodocto=p.nrodocorigen) as cuotas,\n" +
                            "(select dias from ctacteprv where tipdocto='OCP' and nrodocto=p.nrodocorigen) as dias, p.totalafecto,p.totaliva,p.totaldocto, p.cuotaspagadas,p.tipopago,p.electronica \n" +
                            "from ctacteprv p\n" +
                            "where rut=" + Rut +"\n" +
                            "and tipdocto='FAP'\n" +
                            "and nrodocto=" + Numero);
            if(Sql.GetRowCount()==0){
                JOptionPane.showMessageDialog(null,"Factura No encontrada");
                return;
            }
            
            while(TableModel.getRowCount()>0)
                TableModel.removeRow(0);
            
            dfListModel.removeAllElements();
            
            Rs.next();
            chkElectronica.setSelected(Rs.getBoolean("electronica"));
            EsElectronica = Rs.getBoolean("electronica");
            txEstado.setText(Rs.getString("Estado").trim());
            dtEmision.setDate(Rs.getDate("femision"));
            dtRecepcion.setDate(Rs.getDate("frecepcion"));
            txOrden.setText(Rs.getString("nrodocorigen"));
            txCuotas.setText(Rs.getString("cuotas").trim());
            txDias.setText(Rs.getString("dias").trim());
            txExento.setText("0");
            txNeto.setText(fmMain.FormatoTotal(Rs.getDouble("totalafecto")));
            txComision.setText(fmMain.FormatoTotal(Rs.getDouble("comision")));
            txIva.setText(fmMain.FormatoTotal(Rs.getDouble("totaliva")));
            txTotal.setText(fmMain.FormatoTotal(Rs.getDouble("totaldocto")));
            PosTipoPago = Rs.getInt("tipopago");
            cbTipoPago.setSelectedIndex(Rs.getInt("tipopago"));
           
            
            
            if(Rs.getInt("autoriza")==1){
                btPagos.setEnabled(true);
                btAutorizar.setEnabled(false);
                btEditar.setEnabled(false);
                CargaAdministrador();
            }
            else{
                btPagos.setEnabled(false);
                btAutorizar.setEnabled(true);
                btReversar.setEnabled(false);
                btEditar.setEnabled(true);
            }

            if(Rs.getInt("Cuotas") == Rs.getInt("Cuotaspagadas"))
                txEstadoPago.setText("Pagado");
            else if (Rs.getInt("Cuotaspagadas") < Rs.getInt("Cuotas") && Rs.getInt("Cuotaspagadas") > 0) 
                txEstadoPago.setText("Pagado " + Rs.getString("Cuotaspagadas") + " de " + Rs.getInt("Cuotas"));
            else 
                txEstadoPago.setText("No Pagado");
            
            
            
            // Carga detalles.
            Rs = Sql.Select("SELECT pd.sku, pd.cantidad, pd.valorunitario,pd.totallinea\n" +
                            "FROM ctacteprvdet pd\n" +
                            "WHERE pd.nrodocto=" + Numero + " and pd.tipdocto='FAP' and pd.rut=" + Rut);
            
            while(Rs.next()){
                producto =  luv.Select("SELECT p.nombre,trim(u.unidad) as unidad, u.um FROM producto p \n"+
                                       "LEFT JOIN par_unidad u ON u.codigo=p.unidad\n "+
                                       "WHERE p.sku = '"+Rs.getString("sku").trim()+"'");
                producto.next();
                TableModel.addRow(new Object[]{Rs.getString("sku"),
                                               producto.getString("nombre"),
                                               producto.getString("um"),
                                               Rs.getString("cantidad"),
                                               fmMain.FormatoNumero(Rs.getDouble("valorunitario")),
                                               fmMain.FormatoNumero(Rs.getDouble("totallinea")),
                                               Rs.getString("cantidad"),
                                               fmMain.FormatoNumero(Rs.getDouble("valorunitario"))
                });
            }

            
            Rs = Sql.Select("select nrodocto\n" +
                            "from ctacteprv\n" +
                            "where tipdocto='GDP'\n" +
                            "and tipdocorigen='FAP'\n" +
                            "and nrodocorigen=" + Numero + 
                            " and rut=" + Rut);
            while(Rs.next()){
                dfListModel.addElement(Rs.getString("nrodocto").trim());
                lstGuias.setModel(dfListModel);
            }
            Habilita(true);
            btAutorizar.setEnabled(true);
            Tipo=2;
            
            
            
        } catch (SQLException | HeadlessException e) {
            fmMain.Mensaje(e.getMessage());
        } finally{
            Sql.Close();
        }
        
    }
    // muestra los documentos relacionados en la grilla
    private void carga_doc_rel(String StOrden){
        
        ExeSql Sql = new ExeSql();
        ResultSet Rs,Rs1;
        String Query, QTot="";
        DefaultTableModel TableModel = (DefaultTableModel) GrillaDoc.getModel();
        try{     
        
            Query ="select f.tipdocto,f.nrodocto,f.femision from ctacteprv f \n" +
                   "where f.nrodocorigen = " + StOrden + "\n" +
                   "and f.tipdocorigen = 'FAP'\n" +
                   "and f.tipdocto in( 'NCP')\n" +
                   "UNION \n" +
                   "select f.tipdocto tip_nota, f.nrodocto nro_nota, f.femision\n" +
                   "from ctacteprv f \n" +
                   "where f.nrodocorigen=" + StOrden + "\n" +
                   "and f.tipdocorigen = 'FAP'\n" +
                   "and f.tipdocto in( 'GDP')";
        
//     Limpia Ordenes Encabezado   
            while(TableModel.getRowCount()>0)
                 TableModel.removeRow(0);    
            
            Rs = Sql.Select(Query);
            
            while(Rs.next()){
               
               TableModel.addRow(new Object[]{Rs.getString("tipdocto").trim(), 
                                              Rs.getInt("nrodocto"),Rs.getString("femision")});
            }
            
            Rs.close();
       
        }catch (Exception e) {
            fmMain.Mensaje("Existe una inconsistencia en la información.");
        }finally{
            Sql.Close();
        }    
        
    }
    
    private boolean CargaProveedor(String Rut) {
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
    
        try {
            Rs = Sql.Select("select rut,dv,nombre from proveedor where rut="+Rut);
            if(Sql.GetRowCount()==0){
                JOptionPane.showMessageDialog(null,"Proveedor No encontrado");
                return false;
            }
            
            Rs.next();
            txRut.setText(Rs.getString("Rut").trim());
            txDv.setText(Rs.getString("dv").trim());
            txNombre.setText(Rs.getString("nombre").trim().trim());
            
            txNroFactura.setEnabled(true);
            txNroFactura.setEditable(true);
            btIrFactura.setEnabled(true);
            txNroFactura.requestFocus();
            txNombre.setEnabled(true);
            txDv.setEnabled(true);
            return true;
        } catch (Exception e) {
            fmMain.Mensaje(e.getMessage());
            return false;
        } finally{
            Sql.Close();
        }
    }
    
    
    private void btIrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIrActionPerformed
        boolean Carga;
        if(!txRut.getText().isEmpty()){
            Carga=CargaProveedor(txRut.getText());
        }
        else{
            jdBuscarCliPrv BPC = new jdBuscarCliPrv(null, true);
            BPC.setLocationRelativeTo(null);
            BPC.setTitle("Buscar Proveedor");
            BPC.SetTipo(1);
            BPC.setVisible(true);
            Carga=CargaProveedor(BPC.GetRut());
        }
        if(Carga){
            if(Tipo==2){
                txNroFactura.requestFocus();
                }
            else if(Tipo==1){
                Habilita(true);
                Edicion(true);
                txNroFactura.requestFocus();
                dtEmision.setEditable(true);
                dtRecepcion.setEditable(true);
                txCuotas.setEditable(true);
                btAgregaGuia.setEnabled(true);
            }
        }
        
    }//GEN-LAST:event_btIrActionPerformed


public String getFechaEmisionAsString() {
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
return( sdf.format( (dtEmision.getDate()).getTime() ) );
    } catch (Exception e) {
        return "";
    }

}
public String getFechaRecepcionAsString() {
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
return( sdf.format( (dtRecepcion.getDate()).getTime() ) );
    } catch (Exception e) {
        return "";
    }

}//--------------------------------------------------------------------------------
// SUMADOR
//--------------------------------------------------------------------------------
private void Sumador(){    
    double Neto=0;
    double Exento=0;
    double Iva=0;
    double Total=0;
    double Comision=0;
    String Unitario,Cantidad;
    
    for(int i=0; i< Grilla.getRowCount(); i++){
        Unitario = Grilla.getModel().getValueAt(i,GetCol("UniReal")).toString();
        Cantidad = Grilla.getModel().getValueAt(i,GetCol("CantReal")).toString();
        Unitario = Unitario.replace(fmMain.GetMiles(), "");
        Cantidad = Cantidad.replace(fmMain.GetMiles(), "");
        Neto =  (Double.parseDouble(Unitario) * Double.parseDouble(Cantidad)) + Neto;
    }
    Neto = Math.round(Neto);
    if (txRut.getText().trim().equals("96726970"))
        {
        Comision = Math.round(Neto*0.005);
        Neto = Neto+Comision;
        }
    Iva = Math.round((Neto * Double.parseDouble("0.19")));
    
    if(PesoCorreccion != 0){
          if(PesoCorreccion==1)
              Iva = Iva +1;
          else
              Neto = Neto -1;
      }
    
    Total = Neto + Iva;
    
    txComision.setText(fmMain.FormatoTotal(Comision));
    txNeto.setText(fmMain.FormatoTotal(Neto));
    txExento.setText(fmMain.FormatoTotal(Exento));
    txIva.setText(fmMain.FormatoTotal(Iva));
    txTotal.setText(fmMain.FormatoTotal(Total));
}
//------------------------------------------------------------------------------
//  
//------------------------------------------------------------------------------
    private void GrillaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GrillaMouseClicked
        if(evt.getClickCount()==2 && !Grilla.getValueAt(Grilla.getSelectedRow(), 0).toString().isEmpty()){
            jdIngresaNumero jdNumero = new jdIngresaNumero(null, true);
            jdNumero.setLocationRelativeTo(null);
            jdNumero.setVisible(true);        
//            if(jdNumero.GetNumero() != -99 ){
//                Grilla.setValueAt(jdNumero.GetNumero(), Grilla.getSelectedRow(),3);
//                Grilla.setValueAt(Float.parseFloat(Grilla.getModel().getValueAt(Grilla.getSelectedRow(),3).toString()) * Float.parseFloat(Grilla.getModel().getValueAt(Grilla.getSelectedRow(),4).toString()), Grilla.getSelectedRow(),5);
//                Sumador();
//            }
            
            if(jdNumero.GetNumero() != -99 ){
                try {
                Grilla.setValueAt( fmMain.FormatoNumero(jdNumero.GetNumero()) , Grilla.getSelectedRow(),GetCol("Cantidad"));
                Grilla.setValueAt(fmMain.FormatoNumero(jdNumero.GetPrecio()), Grilla.getSelectedRow(),GetCol("Unitario"));
                double TotLinea = jdNumero.GetNumero() * jdNumero.GetPrecio();
                Grilla.setValueAt(fmMain.FormatoNumero(TotLinea),Grilla.getSelectedRow(),GetCol("Total"));
                
                
                Grilla.setValueAt(jdNumero.GetNumero(), Grilla.getSelectedRow(),GetCol("CantReal"));
                Grilla.setValueAt(jdNumero.GetPrecio(), Grilla.getSelectedRow(),GetCol("UniReal"));
                 
                
                
                Sumador();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            
            
            
            
        }

        
    }//GEN-LAST:event_GrillaMouseClicked

    private void btIrFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIrFacturaActionPerformed
        if (!txNroFactura.getText().isEmpty() && Tipo == 2) {
           
            AbrirFactura(txRut.getText().trim(), txNroFactura.getText().trim());
        
        } else if (txNroFactura.getText().isEmpty() && Tipo == 2) {
        
            jdAbrirDocumento ADoc = new jdAbrirDocumento(null, true);
            
            if (ADoc.ShowModal("FAP", txRut.getText().trim()) == JOptionPane.YES_OPTION) {
            
                txNroFactura.setText(ADoc.GetNumero());
                AbrirFactura(txRut.getText().trim(), txNroFactura.getText().trim());
                
            }
        }
        
        // Muestra los documentos relacionados
        carga_doc_rel(txNroFactura.getText().trim());
    }//GEN-LAST:event_btIrFacturaActionPerformed

    private void btAutorizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAutorizarActionPerformed
       
        if (txEstado.getText().trim().equals("AUTORIZADO")){
        
            fmMain.Mensaje("La factura se encuentra Autorizada");
            return;
        }    
        
        if(fmMain.OkCancel("¿Autorizar?")== JOptionPane.OK_OPTION){
                
                ExeSql Sql = new ExeSql();
                ExeSql Sql2 = new ExeSql();
                ExeSql Sql3 = new ExeSql();
                ExeSql Sql4 = new ExeSql();
                
                ResultSet Rs3 = null;  
                
                
                //******************************* Calculo Costo Promedio *******************************************
        
                for (int i = 0; i < Grilla.getRowCount(); i++) {
                
                    
                    String Sku = Grilla.getModel().getValueAt(i, 0).toString().trim();
                    String ValorUnitario = Grilla.getModel().getValueAt(i, 4).toString().trim();
                    String Cantidad = Grilla.getModel().getValueAt(i, 3).toString();
                    
                    Calcular_CostoPromedio(Sku,Cantidad,ValorUnitario,i);
                
                
                }
        
      //********************************************************************************************************
                
                
                
                try {
                    Sql.ExeSql("update ctacteprv set\n"
                            + "cuotas=" + txCuotas.getText().trim() + ",\n"
                            + "tipopago=" + cbTipoPagoId.getSelectedItem().toString() + ",\n"
                            + "dias='" + txDias.getText().trim() + "'\n"
                            + "where rut=" + txRut.getText().trim() + "\n"
                            + "and tipdocto='FAP'\n"
                            + "and nrodocto=" + txNroFactura.getText().trim());
                    
                    Sql.ExeSql("update ctacteprv set\n"
                            + "autoriza=1\n"
                            + "where rut=" + txRut.getText().trim() + "\n"
                            + "and tipdocto='FAP'\n"
                            + "and nrodocto=" + txNroFactura.getText().trim());
                    Sql.Commit();
                    
                    
                    //Modifica detalle producto
                
                    for (int i = 0; i < Grilla.getRowCount(); i++) {
                        
                        Rs3 = Sql3.Select("SELECT DISTINCT ctp.rut, ctp.tipdocto, ctp.nrodocto, ctp.sku, ct.femision, ctp.valorunitario FROM ctacteprvdet ctp\n" +
                                          "LEFT JOIN ctacteprv ct ON ctp.nrodocto = ct.nrodocto\n" +
                                          "WHERE ctp.sku = '"+ Grilla.getModel().getValueAt(i, GetCol("Sku")).toString().trim()+"' AND ctp.tipdocto = 'FAP'\n" +
                                          "AND ctp.nrodocto <> '"+txNroFactura.getText().trim() +"'\n" +
                                          "ORDER BY ct.femision DESC LIMIT 1 ");
                        
                        
                       if (Sql3.GetRowCount() > 0){             
                       
                           Rs3.next();
                           
                            String sfemision = sdf.format(Rs3.getDate("femision"));
                            String sfactura = sdf.format(dtEmision.getDate());
                            
                            Date femision = sdf.parse(sfemision);
                            Date ffactura = sdf.parse(sfactura);
                           
                            if (ffactura.compareTo(femision) < 0){     //Verifica si el precio mas reciente antes de actualizarlo
                                
                                Sql2.ExeSql("UPDATE producto_valores SET\n"
                                          + "valultcompra= " + fmMain.SetGuardar(Rs3.getString("valorunitario")) + "\n"
                                          + "WHERE sku = '" + Grilla.getModel().getValueAt(i, GetCol("Sku")).toString().trim() + "'\n");
                                
                                Sql2.Commit();
                            
                            }else{
                                        
                                        
                                Sql2.ExeSql("UPDATE producto_valores SET\n"
                                          + "valultcompra= " + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("UniReal")).toString()) + "\n"
                                          + "WHERE sku = '" + Grilla.getModel().getValueAt(i, GetCol("Sku")).toString().trim() + "'\n");
                                
                                Sql2.Commit();
                                
                                
                            }
                            
                       }else {
                       
                                Sql2.ExeSql("UPDATE producto_valores SET\n"
                                          + "valultcompra= " + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("UniReal")).toString()) + "\n"
                                          + "WHERE sku = '" + Grilla.getModel().getValueAt(i, GetCol("Sku")).toString().trim() + "'\n");
                                
                                Sql2.Commit();
                       
                       
                       
                       }
                        Sql4.ExeSql("update ctacteprvdet set\n"
                                  + "valorunitario= " + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("UniReal")).toString()) + ",\n"
                                  + "totallinea= " + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("Total")).toString()) + "\n"
                                  + "where sku ='" + Grilla.getModel().getValueAt(i, GetCol("Sku")).toString() + "'\n"
                                  + "and tipdocto= 'OCP' \n"
                                  + "and nrodocto=" + txOrden.getText().trim());
                        Sql4.Commit();
                    
                    }
                    
                    
                    //****************************** Actualiza Costo Promedio ***************************//
        
                    ExeSql Sql6 = new ExeSql();
                    ExeSql Sql7 = new ExeSql();
                    ExeSql Sql8 = new ExeSql();
                   
                    try {
        
                            for (int i = 0; i < Grilla.getRowCount(); i++) {
                
                                    Sql6.ExeSql("UPDATE producto_valores SET\n" +
                                                "costofinal = " + Math.round(Double.parseDouble(Lista.get(i).get(1))) + ",\n" +
                                                "compra = 1 \n" +
                                                "WHERE sku = '" + Lista.get(i).get(0).toString().trim() + "'\n");
                                    
                                    Sql7.ExeSql("UPDATE inventario SET\n" +
                                                "stock2 = stock \n" +
                                                "WHERE sku = '" + Lista.get(i).get(0).toString().trim() + "'\n");
                                    
                                    
                                  
                                    
                     
                            }
                
                            Sql6.Commit();
                            Sql7.Commit();
                            
                
                    }catch (SQLException ex) {
            
                            Sql4.Rollback();
                            Sql6.Rollback();
                            Sql7.Rollback();
                          
                            Logger.getLogger(pfFAProveedor.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
        
        
        
             //******************************************************************************************//
                    
                    JOptionPane.showMessageDialog(null,"Factura Autorizada");
                    Tipo=2;
                    btIrFactura.doClick();
                } catch (SQLException | HeadlessException e) {
                    fmMain.Mensaje(e.getMessage());
                    Sql.Rollback();
                    
                }   catch (ParseException ex) {
                        Logger.getLogger(pfFAProveedor.class.getName()).log(Level.SEVERE, null, ex);
                    }finally{
                    Sql.Close();
                }
                
            }
    }//GEN-LAST:event_btAutorizarActionPerformed

    private void Calcular_CostoPromedio(String Sku, String Cant, String Vun, int fila){
    
        
         Vun = Vun.replaceAll("\\,","");
        
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        
        double costopromedio = 0;
        double nuevocostopromedio = 0;
        int stock = 0;
        int nuevostock = 0;
        double totalcompra = 0;
        double totalanterior = 0;
        double nuevototal = 0;
                
        
        try {
            Rs = Sql.Select("SELECT p.sku, p.costofinal, i.stock2 FROM producto_valores p \n" +
                            "LEFT JOIN inventario i ON p.sku = i.sku \n" +
                            "WHERE p.sku = '"+Sku+"'");
            if (Sql.GetRowCount() > 0){
                
                Rs.next();
                
                stock = Rs.getInt("stock2");
                costopromedio = Rs.getDouble("costofinal");
              
                System.out.println("Actual Stock :"+stock);
                System.out.println("Actual CostoPromedio :"+costopromedio);
                
                nuevostock = stock + Integer.parseInt(Cant.replace(".0", ""));   //Se resta la cantidad de la NCP al stock del Producto
                
                System.out.println("Cant Compra :"+Integer.parseInt(Cant.replace(".0", "")));
                System.out.println("Valor Unitario :"+Double.parseDouble(Vun));
                
                totalcompra = (Integer.parseInt(Cant.replace(".0", ""))*Double.parseDouble(Vun));
                System.out.println("Total Compra :"+totalcompra);
                totalanterior = (stock * costopromedio);
                System.out.println("Actual Total :"+totalanterior+"\n\n");
                
                
                nuevototal = (totalcompra + totalanterior);   //Se Resta el monto de la NCP al Monto del Producto
                
                nuevocostopromedio = (nuevototal / nuevostock);
                
                
            
            }
            
            Lista.add(new ArrayList<String>());
  
        
            Lista.get(fila).add(0,Sku);
            Lista.get(fila).add(1,String.valueOf(nuevocostopromedio));
  
            
//            System.out.println("LA FILA1 0 = "+Lista.get(fila).get(0));
//            System.out.println("LA FILA1 0 = "+Lista.get(fila).get(1));
            
        
        } catch (SQLException ex) {
        
            Logger.getLogger(pfFAProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    } 
    
    
    
    
    
    private void txNroFacturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txNroFacturaKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER)
            btIrFactura.doClick();
    }//GEN-LAST:event_txNroFacturaKeyPressed

    private void txDiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txDiasMouseClicked
        if(evt.getClickCount()==2 && (Tipo==3 || Tipo==1)){
            jdDiasOCP Dias = new jdDiasOCP(null, true);
            Dias.setTitle("Dias");
            Dias.setLocationRelativeTo(txDias);
            Dias.SetFilas(Integer.valueOf(txCuotas.getText()));
            Dias.setVisible(true);
    //        Dias.SetFoco();
            txDias.setText(Dias.GetFilas());
        }
    }//GEN-LAST:event_txDiasMouseClicked

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        /*Botones*/
        BotonesEdita(true);
        
        /*Editar*/
        dtEmision.setEditable(true);
        dtRecepcion.setEditable(true);
        txCuotas.setEditable(true);
        btAutorizar.setEnabled(true);
        Tipo=3;
    }//GEN-LAST:event_btEditarActionPerformed

    private void txCuotasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCuotasKeyTyped
        if(!Character.isDigit(evt.getKeyChar()))
            evt.consume();
    }//GEN-LAST:event_txCuotasKeyTyped
    void BotonesEdita(boolean Estado)    {
        /*Botones*/
        btEditar.setEnabled(Estado);
        btAbrir.setEnabled(!Estado);
        btAutorizar.setEnabled(!Estado);
        btCancelar.setEnabled(Estado);
        btGuardar.setEnabled(Estado);
        btNuevo.setEnabled(!Estado);
//        btAutorizar.setEnabled(Estado);
        
        /*bloquea*/
        txRut.setEditable(!Estado);
        txNroFactura.setEditable(!Estado);
        txOrden.setEditable(!Estado);
        
    }
    public String getFechaEmision() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return( sdf.format( (dtEmision.getDate()).getTime() ) );
    }
    public String getFechaRecepcion() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return( sdf.format( (dtRecepcion.getDate()).getTime() ) );
    }
    private Integer BoolToInt(boolean Dato){
       if(Dato) return 1;
       else     return 0;

    }
    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed
        // Valida Entrada de Datos
        if (txCuotas.getText().isEmpty()
                || txDias.getText().isEmpty()
                || getFechaEmision().isEmpty()
                || getFechaRecepcion().isEmpty()
                || txOrden.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Faltan Datos");
            return;
        }
        
        
        //***************************************************************************************************************************//
        
        ExeSql Sql2 = new ExeSql();
        ResultSet Rs2;
        
        String Rut = txRut.getText().trim();
        String Numero = txNroFactura.getText().trim();
        
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
            
        try {
            Rs2 = Sql2.Select("SELECT rut,tipdocto,nrodocto FROM doc_mail \n" +
                              "WHERE rut = "+Rut+ "\n"+
                              "AND nrodocto=" + Numero);
            
            
            if(Sql2.GetRowCount()==0 && intNivelUsuario < 90 ){
                JOptionPane.showMessageDialog(null,"Error Ingreso de Documento");
                txNroFactura.requestFocus();
                return;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(pfFAProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        
        //*****************************************************************************************************************************//
        
        
        
        
        
        

        ExeSql Sql = new ExeSql();

        /*
         NUEVA FACTURA PROVEEDOR
         */
        if (Tipo == 1) {
            try {
                Sql.ExeSql("INSERT INTO public.ctacteprv\n"
                        + "(rut,tipdocto,nrodocto,femision,frecepcion,totalafecto,totalexento,totaliva,totalotroimp,totaldocto,electronica,recepcion,cuotas,dias,tipdocorigen,nrodocorigen, comision) \n"
                        + "VALUES ("
                        + txRut.getText().trim() + ","
                        + "'FAP',"
                        + txNroFactura.getText() + ",'"
                        + getFechaEmision() + "','"
                        + getFechaRecepcion() + "',"
                        + fmMain.SetGuardar(txNeto.getText()) + ","
                        + fmMain.SetGuardar(txExento.getText()) + ","
                        + fmMain.SetGuardar(txIva.getText()) + ","
                        + fmMain.SetGuardar(txImpEspecifico.getText()) + ","
                        + fmMain.SetGuardar(txTotal.getText()) + ","
                        + BoolToInt(chkElectronica.isSelected()) + ",0," 
                        + txCuotas.getText().trim() + ",'" + txDias.getText().trim() + "','OCP'," + txOrden.getText().trim() +","+fmMain.SetGuardar(txComision.getText())+ ")");
                for (int i = 0; i < Grilla.getRowCount(); i++) {
                    Sql.ExeSql("INSERT INTO ctacteprvdet\n"
                            + "(rut,tipdocto,nrodocto,sku,cantidad, valorunitario,totallinea) \n"
                            + "VALUES (\n"
                            + txRut.getText() + ",\n"
                            + "'FAP',\n"
                            + txNroFactura.getText().trim() + ",\n'"
                            + Grilla.getModel().getValueAt(i, GetCol("Sku")).toString().trim() + "',"
                            + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("CantReal")).toString()) + ","
                            + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("UniReal")).toString()) + ","
                            + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("Total")).toString()) + ")");
                }
                for (int i = 0; i < lstGuias.getModel().getSize(); i++) {
                    String NroGuia = lstGuias.getModel().getElementAt(i).toString();
                    Sql.ExeSql("update ctacteprv set tipdocorigen='FAP', nrodocorigen=" + txNroFactura.getText().trim() + "\n"
                            + "where rut=" + RutBusca.trim() + "\n"
                            + "and tipdocto='GDP' \n"
                            + "and nrodocto=" + NroGuia);
                }
                Sql.Commit();
                JOptionPane.showMessageDialog(null,"Guardado");
                Habilita(false);
                Tipo=2;

            } catch (Exception e) {
                fmMain.Mensaje("Error al guardar: "+e);
                Sql.Rollback();
            } finally {
                Sql.Close();
            }
        } 
        /*FACTURA ABIERTA EDITANDO*/ 
        else if (Tipo == 3) {
            try {
                //Modifica Orden de Compra
                Sql.ExeSql("update ctacteprv set\n"
                        + "cuotas=" + txCuotas.getText().trim() + ",\n"
                        + "dias='" + txDias.getText().trim() + "',\n"
                        + "tipopago = " + cbTipoPagoId.getSelectedItem().toString() + ",\n"
                        + "totalafecto= " + fmMain.SetGuardar(txNeto.getText()) + ",\n"
                        + "totaliva= " + fmMain.SetGuardar(txIva.getText()) + ",\n"
                        + "totaldocto= " + fmMain.SetGuardar(txTotal.getText()) + "\n"
                        + "where rut=" + txRut.getText().trim() + "\n"
                        + "and tipdocto='OCP'\n"
                        + "and nrodocto=" + txOrden.getText().trim());
                //Modifica Factura
                Sql.ExeSql("update ctacteprv set\n"
                        + "femision='" + getFechaEmisionAsString() + "',\n"
                        + "frecepcion='" + getFechaRecepcionAsString() + "',\n"
                        + "electronica=" + BoolToInt(chkElectronica.isSelected()) + ",\n"
                        + "tipopago = " + cbTipoPagoId.getSelectedItem().toString() + ",\n"
                        + "totalafecto= " + fmMain.SetGuardar(txNeto.getText()) + ",\n"
                        + "comision= " + fmMain.SetGuardar(txComision.getText()) + ",\n"
                        + "totaliva= " + fmMain.SetGuardar(txIva.getText()) + ",\n"
                        + "totaldocto= " + fmMain.SetGuardar(txTotal.getText()) + "\n"
                        + "where rut=" + txRut.getText().trim() + "\n"
                        + "and tipdocto='FAP'\n"
                        + "and nrodocto=" + txNroFactura.getText().trim());

                Sql.Commit();
                JOptionPane.showMessageDialog(null, "Guardado");
                Tipo=2;
              
                btIrFactura.doClick();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                Sql.Rollback();
            } finally {
                Sql.Close();
            }
        }

        BotonesEdita(false);
    }//GEN-LAST:event_btGuardarActionPerformed
    
    public void CargaFactura(String Rut,String NroDocto){
        btAbrir.doClick();
        txRut.setText(Rut);
        btIr.doClick();
        txNroFactura.setText(NroDocto);
        btIrFactura.doClick();
    }
    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        if(fmMain.OkCancel("¿Desea cancelar?")==JOptionPane.OK_OPTION){
            BotonesEdita(false);
            Limpia();
           // btIrFactura.doClick();
        }
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPagosActionPerformed
        jdCuotasPrv prv = new jdCuotasPrv(null, true);
        prv.setTitle("Pagos");
        prv.setLocationRelativeTo(null);
        prv.CargaPagos(txRut.getText().trim(), txNroFactura.getText().trim(), txNombre.getText().trim());
        prv.setVisible(true);
    }//GEN-LAST:event_btPagosActionPerformed

    private void cbTipoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoPagoActionPerformed
        if(Tipo!=-99){
            if(Tipo==2){
                cbTipoPago.setSelectedIndex(PosTipoPago);
            }
            cbTipoPagoId.setSelectedIndex(cbTipoPago.getSelectedIndex());
        }
            
        
    }//GEN-LAST:event_cbTipoPagoActionPerformed

    private void btNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoActionPerformed
        txRut.setEnabled(true);
        txRut.setEditable(true);
        btIr.setEnabled(true);
        txRut.requestFocus();
        SetTipo(1);
    }//GEN-LAST:event_btNuevoActionPerformed

    private void btAgregaGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregaGuiaActionPerformed
        String Condicional = "";
        String Guias = "";
        RutBusca = txRut.getText().trim();
        ExeSql Sql = new ExeSql();
        ExeSql Sql1 = new ExeSql();
        ResultSet Rs, Rs2;
        
        if (Tipo == 1) {
            //--------SI EL RUT PERTENECE A CHILEMAT PUEDE LEER CUALQUIER OTRO RUT SIEMPRE Y CUANDO SEA GUIA Y PERTENEZCA A LAS OC DE CHILEMAT--------------
            if (txRut.getText().trim().equals("96726970")){
                
                try{
                String OCCHM = JOptionPane.showInputDialog("Ingrese numero OC CHILEMAT");
                Rs2= Sql1.Select("select c.rut from ctacteprv  c\n" +
                                "left join recepcionprv r on c.nrodocto=r.nrodocto and c.tipdocto=r.tipdocto and c.rut = r.rut \n" +
                                "where r.ocp in (select nrodocto from ctacteprv where tipdocto='OCP' and occhilemat="+OCCHM.trim()+")");
                if (Rs2.next()){
                RutBusca = Rs2.getString("rut").trim();
                }
                else {
                    fmMain.Mensaje("No se encontro la OC: "+OCCHM);
                    return;
                }
                }
                catch (Exception e){
                    fmMain.Mensaje("Error al cargar OC Chilemat: "+e);
                }
                finally {
                    Sql1.Close();
                }
                                
            }
            
            jdAbrirDocumento ADoc = new jdAbrirDocumento(null, true);

            if (lstGuias.getModel().getSize() > 0) {
                for (int i = 0; i < lstGuias.getModel().getSize(); i++) {
                    Guias = Guias + lstGuias.getModel().getElementAt(i).toString() + ",";
                }
                Guias = Guias.substring(0, Guias.length() - 1);
                Condicional = "and nrodocorigen is null and  nrodocto not in (" + Guias + ")";
            } else {
                Condicional = "and nrodocorigen is null";
            }

            if (ADoc.ShowModalCondicional("GDP", RutBusca , Condicional) == JOptionPane.YES_OPTION) {

//                // COMPROBAR QUE EL NUMERO A AGREGAR NO ESTE EN LA LISTA
//                for (int i = 0; i < lstGuias.getModel().getSize(); i++) {
//                        if (lstGuias.getModel().getElementAt(i).toString().equals(ADoc.GetNumero()));{
//                            fmMain.Mensaje("Guia ya ingresada anteriormente");
//                            return; 
//                        }
//                    }
                dfListModel.addElement(ADoc.GetNumero());
                lstGuias.setModel(dfListModel);
                //AgregaGuias();
               
                
                try {
                    Rs = Sql.Select("select p.nrodocto,p.cuotas,p.dias\n"
                            + "from recepcionprv r\n"
                            + "left join ctacteprv p\n"
                            + "on r.rut=p.rut and r.ocp=p.nrodocto and p.tipdocto='OCP'\n"
                            + "where r.tipdocto='GDP'\n"
                            + "and r.rut=" + RutBusca + "\n"
                            + //" and r.nrodocto=" + ADoc.GetNumero());
                            "and r.nrodocto=" + lstGuias.getModel().getElementAt(0).toString().trim());
                    while (Rs.next()) {
                        txOrden.setText(Rs.getString("nrodocto").trim());
                        txCuotas.setText(Rs.getString("cuotas").trim());
                        txDias.setText(Rs.getString("dias").trim());
                    }

                } catch (Exception e) {
                    fmMain.Mensaje(e.getMessage());
                } finally {
                    Sql.Close();
                }

            }
        }

    }//GEN-LAST:event_btAgregaGuiaActionPerformed

    private void lstGuiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstGuiasMouseClicked
        if(evt.getClickCount()==2){
            lstGuias.remove(lstGuias.getSelectedIndex());
        }
    }//GEN-LAST:event_lstGuiasMouseClicked

    private void txDiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txDiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txDiasActionPerformed

    private void btReversarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReversarActionPerformed
        ExeSql Sql=new ExeSql();
        
        try {
            if(fmMain.OkCancel("¿Realmente desea reversar esta factura?")== JOptionPane.OK_OPTION){
                Sql.ExeSql("select fn_reversofacprv("+ txRut.getText().trim() +"," + txNroFactura.getText().trim() +")");
                Sql.Commit();
                fmMain.Mensaje("Factura Reversada");
                Tipo=2;
                btIrFactura.doClick();
            }
            
        } catch (Exception e) {
            fmMain.Mensaje(e.getMessage());
            Sql.Rollback();
        }finally{
            Sql.Close();
        }
    }//GEN-LAST:event_btReversarActionPerformed

    private void jPanel4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanel4PropertyChange
        
            
    }//GEN-LAST:event_jPanel4PropertyChange

    private void chkElectronicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkElectronicaActionPerformed
        if(Tipo==2)
          chkElectronica.setSelected(EsElectronica);
    }//GEN-LAST:event_chkElectronicaActionPerformed

    private void txNetoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txNetoMouseClicked
        if(evt.getClickCount()==2 && Tipo==3){
            jdAjustePesos Aju = new jdAjustePesos(null, true);
            Aju.setLocationRelativeTo(null);
            Aju.setVisible(true);
            if(SumadorNeto + Aju.GetAjuste() > 1 || SumadorNeto + Aju.GetAjuste() < -1)
                return;
            SumadorNeto = SumadorNeto + Aju.GetAjuste();
            double Neto = Double.valueOf(fmMain.SetGuardar(txNeto.getText()));
            double Total = Double.valueOf(fmMain.SetGuardar(txTotal.getText()));
            Neto = Neto + Aju.GetAjuste();
            Total = Total + Aju.GetAjuste();
            
            txNeto.setText(fmMain.FormatoTotal(Neto));
            txTotal.setText(fmMain.FormatoTotal(Total));
        }
    }//GEN-LAST:event_txNetoMouseClicked

    private void txIvaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txIvaMouseClicked
        if(evt.getClickCount()==2 && Tipo==3){
            jdAjustePesos Aju = new jdAjustePesos(null, true);
            Aju.setLocationRelativeTo(null);
            Aju.setVisible(true);
            if(SumadorIva + Aju.GetAjuste() > 1 || SumadorIva + Aju.GetAjuste() < -1)
                return;
            SumadorNeto = SumadorIva + Aju.GetAjuste();
            double Iva = Double.valueOf(fmMain.SetGuardar(txIva.getText()));
            double Total = Double.valueOf(fmMain.SetGuardar(txTotal.getText()));
            Iva = Iva + Aju.GetAjuste();
            Total = Total + Aju.GetAjuste();
            
            txIva.setText(fmMain.FormatoTotal(Iva));
            txTotal.setText(fmMain.FormatoTotal(Total));
        }
    }//GEN-LAST:event_txIvaMouseClicked

    private void GrillaDocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GrillaDocMouseClicked
        // TODO add your handling code here:
            String Tipodoc = GrillaDoc.getValueAt(GrillaDoc.getSelectedRow(), 0).toString().trim();
            String nrodoc = GrillaDoc.getValueAt(GrillaDoc.getSelectedRow(), 1).toString().trim();
            String fecha  = GrillaDoc.getValueAt(GrillaDoc.getSelectedRow(), 2).toString().trim();
            String rut ="";
        
            
            
        if (Tipodoc.equals("GDP")  )
        {    
//                pfFAProveedor Pro = new pfFAProveedor();
//                Pro.setOpaque(false);
//                pnPestanas.addTab("Factura Proveedor", Pro);
//                PanelTab btc = new PanelTab(pnPestanas, 0);
//                pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(Pro), btc);
//                pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
//                Pro.CargaFactura(txRut.getText().trim()  ,nrodoc);
        }
        else if (Tipodoc.equals("NCP")    )
        {    
                pfNCPProveedor NCP = new pfNCPProveedor();
                NCP.setOpaque(false);
                pnPestanas.addTab("Nota Credito Proveedor", NCP);
                PanelTab btc = new PanelTab(pnPestanas, 0);
                pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(NCP), btc);
                pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
                rut = txRut.getText().trim();
                NCP.CargaProveedor(rut);
                NCP.AbrirNCP(rut, nrodoc);
        }
        

    }//GEN-LAST:event_GrillaDocMouseClicked

    private void btProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProdActionPerformed
        // TODO add your handling code here:
        AgregaGuias();
    }//GEN-LAST:event_btProdActionPerformed

    private void btEliminaGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEliminaGuiaActionPerformed
        // TODO add your handling code here:
        dfListModel.remove(lstGuias.getSelectedIndex());
                
       //lstGuias.remove(lstGuias.getSelectedIndex());
    }//GEN-LAST:event_btEliminaGuiaActionPerformed

    private void btCorrigePesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCorrigePesoActionPerformed
        if (txEstado.getText().trim().equals("AUTORIZADO")){
            fmMain.Mensaje("La factura se encuentra Autorizada");
            return;
        }
        jdAjustePesos aju = new jdAjustePesos(null, true);
        aju.setLocationRelativeTo(null);
        aju.setTitle("Ajuste Pesos");
        aju.setVisible(true);
        PesoCorreccion = aju.GetAjuste();
        Sumador();

    }//GEN-LAST:event_btCorrigePesoActionPerformed
    private void AgregaGuias() {
        DefaultTableModel dfTm = (DefaultTableModel) Grilla.getModel();
        double cant_ingresa=0, cant_grilla=0;
        double valor_ingresa=0, valor_grilla=0;
        String Guias = "";

        if (lstGuias.getModel().getSize() > 0) {
            ExeSql Sql = new ExeSql();
            ResultSet Rs;
            ExeSqlLuvaly luv = new ExeSqlLuvaly();
            ResultSet producto = null;

            while (dfTm.getRowCount() > 0) {
                dfTm.removeRow(0);
            }

            for (int i = 0; i < lstGuias.getModel().getSize(); i++) {
                Guias = Guias + lstGuias.getModel().getElementAt(i).toString() + ",";
            }
            Guias = Guias.substring(0, Guias.length() - 1);

            try {
                Rs = Sql.Select("select d.sku,sum(d.cantidad) as cantidad,d.valorunitario,sum(d.totallinea) as totallinea\n"
                        + "from ctacteprvdet d\n"
                        + "where tipdocto='GDP'\n"
                        + "and rut="+ RutBusca +"\n"
                        + "and nrodocto in (" + Guias + ")\n"
                        + "group by d.sku,p.nombre,u.unidad,d.valorunitario");
                String codigo="";
                int pos =-1;
                while(Rs.next()){
                    // primero se valida si el codigo esta o no en la grilla
                    //--------------------------------------------------------------------------------------
                    // Busca si el codigo se ha ingresado anteriormente en la grilla
                    for (int i=0; i<=Grilla.getRowCount()-1; i++ ){
                        codigo= Grilla.getValueAt(i, 0).toString().trim();
                        if (codigo.equals(Rs.getString("sku").trim())){
                            pos = i;
                            break;
                        }
                    }

            // Si existe el codigo en la grilla modificara la cantidad ingresada
            if (pos>-1){
               // Grilla.changeSelection(pos, 0 , false, false);
                //Valida que el valor unitario sea igual al ingresado
                
                
                // Agrupa la cantidad en el codigo correspondiente.
                    cant_ingresa = Double.parseDouble(Rs.getString("cantidad").trim());
                    cant_grilla = Double.valueOf(fmMain.SetGuardar( Grilla.getValueAt(pos, 3).toString()));
                    Grilla.setValueAt(cant_grilla + cant_ingresa, pos, 3);
                
                
                valor_ingresa=Double.parseDouble(Rs.getString("valorunitario").trim());
                valor_grilla=   Double.valueOf(fmMain.SetGuardar( Grilla.getValueAt(pos, 7).toString()));
                
                
                
                
                if (valor_grilla > valor_ingresa){
                    Grilla.setValueAt(fmMain.FormatoTotal(valor_grilla) , pos, 4);
                    Grilla.setValueAt(valor_grilla, pos, 7);
                    // Se reasigna el total
                        Grilla.setValueAt( fmMain.FormatoTotal((cant_grilla + cant_ingresa)* valor_grilla), pos, 5);
                }
                else
                {
                    Grilla.setValueAt( fmMain.FormatoTotal(valor_ingresa), pos, 4);
                    Grilla.setValueAt(valor_ingresa, pos, 7);
                    // Se reasigna el total
                        Grilla.setValueAt( fmMain.FormatoTotal( (cant_grilla + cant_ingresa)* valor_ingresa), pos, 5);
                }
                // Asigna valores a unireal, cantreal   
                Grilla.setValueAt(cant_grilla + cant_ingresa, pos, 6);
                
                
                
                
                

            }  // Fin Si
            else
                {   
                    producto =  luv.Select("select p.nombre,trim(u.unidad) as unidad, u.um from producto p \n"+
                                           "LEFT JOIN par_unidad u ON u.codigo=p.unidad\n "+
                                           "where p.sku = '"+Rs.getString("sku").trim()+"'");
                    producto.next();
                    //--------------------------------------------------------------------------------------
                    dfTm.addRow(new Object[]{
                            Rs.getString("sku"),
                            producto.getString("nombre"),
                            producto.getString("unidad"),
                            fmMain.FormatoNumero(Rs.getDouble("cantidad")),
                            fmMain.FormatoNumero(Rs.getDouble("valorunitario")),
                            fmMain.FormatoNumero(Rs.getDouble("totallinea")),
                            Rs.getDouble("cantidad"),
                            Rs.getDouble("valorunitario")
                            });
                } // Fin Sino       
             } // End -while
                Sumador();
            } catch (Exception e) {
                fmMain.Mensaje(e.getMessage());
            } finally{
                Sql.Close();
                luv.Close();
            }

        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Grilla;
    private javax.swing.JTable GrillaDoc;
    private javax.swing.JButton btAbrir;
    private javax.swing.JButton btAgregaGuia;
    private javax.swing.JButton btAutorizar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btCorrigePeso;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btEliminaGuia;
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btIr;
    private javax.swing.JButton btIrFactura;
    private javax.swing.JButton btNuevo;
    private javax.swing.JButton btPagos;
    private javax.swing.JButton btProd;
    private javax.swing.JButton btReversar;
    private javax.swing.JComboBox cbTipoPago;
    private javax.swing.JComboBox cbTipoPagoId;
    private javax.swing.JCheckBox chkElectronica;
    private org.jdesktop.swingx.JXDatePicker dtEmision;
    private org.jdesktop.swingx.JXDatePicker dtRecepcion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private javax.swing.JLabel lbComision;
    private javax.swing.JLabel lbFolio;
    private javax.swing.JList lstGuias;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JTextField txComision;
    private javax.swing.JTextField txCuotas;
    private javax.swing.JTextField txDias;
    private javax.swing.JTextField txDv;
    private javax.swing.JTextField txEstado;
    private javax.swing.JTextField txEstadoPago;
    private javax.swing.JTextField txExento;
    private javax.swing.JTextField txImpEspecifico;
    private javax.swing.JTextField txIva;
    private javax.swing.JTextField txNeto;
    private javax.swing.JTextField txNombre;
    private javax.swing.JTextField txNroFactura;
    private javax.swing.JTextField txOrden;
    private javax.swing.JTextField txRut;
    private javax.swing.JTextField txTotal;
    // End of variables declaration//GEN-END:variables
}
