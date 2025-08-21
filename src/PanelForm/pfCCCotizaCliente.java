package PanelForm;

import Conexion.ExeSql;
import Conexion.ExeSqlLuvaly;
import Dialogos.jdAgregaBlog;

import Dialogos.jdBuscarCliPrv;
import Dialogos.jdCCLAgregaProducto;
import Dialogos.jdContactos;
import Dialogos.jdDiasOCP;
import Dialogos.jdOCCAgregaProducto;
import Dialogos.jdOCCContacto;
import Dialogos.jdProductosAsociadosPrv;
import Dialogos.jdQuienFirmaCot;
import Formularios.fmMain;
import Utilidades.ComboCodigos;
import Utilidades.ImgTabla;
import Utilidades.ListarProductos;
import Utilidades.LogError;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class pfCCCotizaCliente extends javax.swing.JPanel {

    public String firmacompra;
    String RutMaster;
     int PosCodigoOc=0;
    public int Tipo; // 0::Nuevo    1:Abrir
   
    int PosCuotas;
    int IdVendedor;
    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    private enum Columnas{Sku,Nombre,UM,Cantidad,Unitario,Total,UniReal,CantReal};

    public pfCCCotizaCliente() {
        initComponents();
        txid_usuario.setVisible(false);
        dtEmision.setFormats(new String[] {"dd/MM/yyyy"});
//        dtDespacho.setFormats(new String[] {"dd/MM/yyyy"});
        Habilita(false);
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        Grilla.getColumnModel().getColumn(GetCol("Cantidad")).setCellRenderer(rightRenderer);
        Grilla.getColumnModel().getColumn(GetCol("Unitario")).setCellRenderer(rightRenderer);
        Grilla.getColumnModel().getColumn(GetCol("Total")).setCellRenderer(rightRenderer);
        btLiberar.setVisible(false);
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnMenu = new javax.swing.JPanel();
        btNuevo = new javax.swing.JButton();
        btGuardar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btAbrir = new javax.swing.JButton();
        btImprimir = new javax.swing.JButton();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        btLiberar = new javax.swing.JButton();
        btActualizar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Grilla = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txRut = new javax.swing.JTextField();
        txDv = new javax.swing.JTextField();
        btIr = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btContacto = new javax.swing.JButton();
        txNombre = new javax.swing.JTextField();
        txVendedor = new javax.swing.JTextField();
        cbCodigoOc = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txDirDespacho = new javax.swing.JTextField();
        txid_usuario = new javax.swing.JTextField();
        lbInfo = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txNroOc = new javax.swing.JTextField();
        btOtrasOrdenes = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txExento = new javax.swing.JTextField();
        txNeto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txIva = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txTotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txDescuento = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txTotNeto = new javax.swing.JTextField();
        btAgregar = new javax.swing.JButton();
        btEliminar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        dtEmision = new org.jdesktop.swingx.JXDatePicker();
        jLabel16 = new javax.swing.JLabel();
        txEstado = new javax.swing.JTextField();
        txPorc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txComentario = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        GrillaBlog = new javax.swing.JTable();
        btAgregarBlog = new javax.swing.JButton();

        setToolTipText("");
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        pnMenu.setBackground(new java.awt.Color(220, 215, 226));
        pnMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/New.png"))); // NOI18N
        btNuevo.setText("Nuevo");
        btNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevoActionPerformed(evt);
            }
        });

        btGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/save16.png"))); // NOI18N
        btGuardar.setText("Guardar");
        btGuardar.setBorder(null);
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

        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/Pencil16.png"))); // NOI18N
        btEditar.setText("Editar");
        btEditar.setEnabled(false);
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });

        btAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/open16.png"))); // NOI18N
        btAbrir.setText("Abrir");
        btAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAbrirActionPerformed(evt);
            }
        });

        btImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/impresora16.png"))); // NOI18N
        btImprimir.setText("Imprimir");
        btImprimir.setEnabled(false);
        btImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImprimirActionPerformed(evt);
            }
        });

        jXLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jXLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/OCPoveedor24.png"))); // NOI18N
        jXLabel1.setText("COTIZACION CLIENTE");
        jXLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        btLiberar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/erase16.png"))); // NOI18N
        btLiberar.setText("Liberar");
        btLiberar.setEnabled(false);
        btLiberar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLiberarActionPerformed(evt);
            }
        });

        btActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/refresh16.png"))); // NOI18N
        btActualizar.setText("Actualiza");
        btActualizar.setEnabled(false);
        btActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(btGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAbrir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btImprimir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btLiberar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btActualizar)
                .addContainerGap(456, Short.MAX_VALUE))
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btAbrir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btImprimir)
                        .addComponent(btLiberar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnMenuLayout.createSequentialGroup()
                        .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btEditar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btNuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btCancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jXLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        add(pnMenu);

        jPanel2.setBackground(new java.awt.Color(220, 215, 226));
        jPanel2.setToolTipText("");
        jPanel2.setOpaque(false);

        jPanel1.setBackground(new java.awt.Color(220, 215, 226));
        jPanel1.setToolTipText("");

        Grilla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "UM", "Cantidad", "V. Unitario", "Total Linea", "null", "null", "IDCH"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
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
        Grilla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GrillaMouseClicked(evt);
            }
        });
        Grilla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GrillaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                GrillaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(Grilla);
        if (Grilla.getColumnModel().getColumnCount() > 0) {
            Grilla.getColumnModel().getColumn(0).setResizable(false);
            Grilla.getColumnModel().getColumn(0).setPreferredWidth(40);
            Grilla.getColumnModel().getColumn(1).setResizable(false);
            Grilla.getColumnModel().getColumn(1).setPreferredWidth(350);
            Grilla.getColumnModel().getColumn(2).setResizable(false);
            Grilla.getColumnModel().getColumn(2).setPreferredWidth(20);
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

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel1.setText("Rut");

        txRut.setEnabled(false);
        txRut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txRutActionPerformed(evt);
            }
        });
        txRut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txRutKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txRutKeyReleased(evt);
            }
        });

        txDv.setEditable(false);
        txDv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txDvActionPerformed(evt);
            }
        });

        btIr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos22/Go.png"))); // NOI18N
        btIr.setBorderPainted(false);
        btIr.setEnabled(false);
        btIr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIrActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre");

        jLabel14.setText("Vendedor");

        btContacto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos22/Go.png"))); // NOI18N
        btContacto.setBorderPainted(false);
        btContacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btContactoActionPerformed(evt);
            }
        });

        txNombre.setEditable(false);
        txNombre.setEnabled(false);

        txVendedor.setEditable(false);

        cbCodigoOc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCodigoOcActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 51));
        jLabel9.setText("Código");

        jLabel18.setForeground(new java.awt.Color(0, 0, 51));
        jLabel18.setText("Dirección Envío");

        txDirDespacho.setEnabled(false);
        txDirDespacho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txDirDespachoActionPerformed(evt);
            }
        });
        txDirDespacho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txDirDespachoKeyTyped(evt);
            }
        });

        txid_usuario.setToolTipText("");

        lbInfo.setForeground(new java.awt.Color(255, 51, 51));
        lbInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/bloquear.png"))); // NOI18N
        lbInfo.setText("Cliente Bloqueado");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txRut, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txDv, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btIr, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jLabel9)
                        .addGap(9, 9, 9)
                        .addComponent(cbCodigoOc, 0, 152, Short.MAX_VALUE)
                        .addGap(29, 29, 29)
                        .addComponent(txid_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txDirDespacho)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txNombre)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txVendedor)
                        .addGap(18, 18, 18)
                        .addComponent(btContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(lbInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(txRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btIr)
                        .addComponent(txDv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(cbCodigoOc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txid_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btContacto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txDirDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(118, 118, 118))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Nro Cotiza");

        btOtrasOrdenes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos22/Go.png"))); // NOI18N
        btOtrasOrdenes.setBorderPainted(false);
        btOtrasOrdenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOtrasOrdenesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(txNroOc, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btOtrasOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btOtrasOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txNroOc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel11.setText("Exento");

        txExento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txExento.setText("0");

        txNeto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txNeto.setText("0");

        jLabel10.setText("Neto");

        txIva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txIva.setText("0");

        jLabel12.setText("I.V.A.");

        txTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txTotal.setText("0");

        jLabel13.setText("TOTAL");

        jLabel8.setText("Dscto");

        txDescuento.setEditable(false);
        txDescuento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txDescuento.setText("0");

        jLabel17.setText("T. Neto");

        txTotNeto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txTotNeto.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txIva, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txExento, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(txTotNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txExento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txNeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txTotNeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 19, Short.MAX_VALUE))
        );

        btAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos22/add2.png"))); // NOI18N
        btAgregar.setText("Agregar");
        btAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarActionPerformed(evt);
            }
        });

        btEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos22/Cancel.png"))); // NOI18N
        btEliminar.setText("Eliminar");
        btEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEliminarActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel5.setText("F. Emisión");

        jLabel16.setText("Estado");

        txEstado.setEditable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dtEmision, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                    .addComponent(txEstado))
                .addGap(135, 135, 135))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dtEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );

        txPorc.setText("0");
        txPorc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txPorcActionPerformed(evt);
            }
        });
        txPorc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txPorcKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txPorcKeyTyped(evt);
            }
        });

        jLabel3.setText("Descuento");

        jLabel6.setText("%");

        jLabel15.setText("Comentarios");

        txComentario.setColumns(20);
        txComentario.setRows(5);
        txComentario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txComentarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txComentarioKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(txComentario);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txPorc, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btEliminar)
                            .addComponent(btAgregar))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txPorc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))))
        );

        GrillaBlog.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        GrillaBlog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Historial", "id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        GrillaBlog.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane4.setViewportView(GrillaBlog);
        if (GrillaBlog.getColumnModel().getColumnCount() > 0) {
            GrillaBlog.getColumnModel().getColumn(0).setMinWidth(30);
            GrillaBlog.getColumnModel().getColumn(0).setPreferredWidth(30);
            GrillaBlog.getColumnModel().getColumn(0).setMaxWidth(30);
            GrillaBlog.getColumnModel().getColumn(2).setMinWidth(0);
            GrillaBlog.getColumnModel().getColumn(2).setPreferredWidth(0);
            GrillaBlog.getColumnModel().getColumn(2).setMaxWidth(0);
        }

        btAgregarBlog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/Agregar.png"))); // NOI18N
        btAgregarBlog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarBlogActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                        .addGap(17, 17, 17))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btAgregarBlog, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btAgregarBlog)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4)
                        .addGap(18, 18, 18))))
        );

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents
static private int GetCol(String Col){
        return Columnas.valueOf(Col).ordinal();
    }
//--------------------------------------------------------------------------------
// Funcion SET DE TIPOS
//--------------------------------------------------------------------------------
    private void SetTipo(int ElTipo) {
        //Nada
        if (ElTipo == -1) {
            fmMain.SetEstado(fmMain.pnPestanas.getSelectedIndex(), 0);
            Habilita(false);
            Edicion(false);
            Limpia();
            txRut.setEnabled(true);
            txRut.setEditable(true);
            btIr.setEnabled(true);
            txRut.requestFocus();
            btCancelar.setEnabled(false);
            btGuardar.setEnabled(false);
            btNuevo.setEnabled(true);
            btEditar.setEnabled(false);
            btAbrir.setEnabled(true);
//            btImprimir.setEnabled(false);
            btLiberar.setEnabled(false);
            Tipo = -1;

        } //Nueva Orden
        else if (ElTipo == 1) {
            fmMain.SetEstado(fmMain.pnPestanas.getSelectedIndex(), 1);
            btGuardar.setEnabled(true);
            btCancelar.setEnabled(true);
            btEditar.setEnabled(false);
            btNuevo.setEnabled(false);
            btAbrir.setEnabled(false);
 
            Habilita(true);
            Edicion(true);
            btOtrasOrdenes.setEnabled(false);
            txRut.requestFocus();
            Tipo = 1;
        } //Abre Orden
        else if (ElTipo == 2) {
            
            fmMain.SetEstado(fmMain.pnPestanas.getSelectedIndex(), 0);
            btEditar.setEnabled(true);
            btNuevo.setEnabled(true);
            btGuardar.setEnabled(false);
            btCancelar.setEnabled(false);
//            btImprimir.setEnabled(true);
            btLiberar.setEnabled(true);
            btAbrir.setEnabled(true);
            Habilita(true);
            Edicion(false);
            txRut.setEditable(false);
            
            Tipo = 2;
        } //Edita Orden
        else if (ElTipo == 3) {
            fmMain.SetEstado(fmMain.pnPestanas.getSelectedIndex(), 1);
            Habilita(true);
            Edicion(true);
            btGuardar.setEnabled(true);
            btCancelar.setEnabled(true);
            btNuevo.setEnabled(false);
            btImprimir.setEnabled(false);
            btLiberar.setEnabled(false);
            btEditar.setEnabled(false);
            btAbrir.setEnabled(false);
            txRut.setEditable(false);
            
            Tipo = 3;
        }
    }

    
    
//------------------------------------------------------------------------------
// Carga Codigos OC
//------------------------------------------------------------------------------
    private void CargaCodOc(String Rut){
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        DefaultComboBoxModel cbMd = new DefaultComboBoxModel();
        cbCodigoOc.setModel(cbMd);
         cbMd.addElement("Seleccione"); 
        try {
            Rs = Sql.Select("select distinct(codigo_oc) as codigo_oc from clicontacto where rut = " + Rut);
            while(Rs.next()){
            cbMd.addElement(Rs.getString("codigo_oc")); 
            }
        } catch (Exception e) {
        } finally{
            Sql.Close();
        }
            

    }
    
    
//--------------------------------------------------------------------------------
// SUMADOR
//--------------------------------------------------------------------------------
private void Sumador(){    
    
    double Neto=0;
    double Neto2=0;
    double Exento=0;
    double Iva=0;
    double Total=0;
    double descto = 0;
    double totalNeto=0;
    String Unitario,Cantidad;
    
    for(int i=0; i< Grilla.getRowCount(); i++){
        Unitario = Grilla.getModel().getValueAt(i,GetCol("UniReal")).toString();
        Cantidad = Grilla.getModel().getValueAt(i,GetCol("CantReal")).toString();
        Unitario = Unitario.replace(fmMain.GetMiles(), "");
        Cantidad = Cantidad.replace(fmMain.GetMiles(), "");
        Neto =  (Double.parseDouble(Unitario) * Double.parseDouble(Cantidad)) + Neto;
    }
    Neto = Math.round(Neto);
    descto =  Math.round(Neto * (Double.valueOf(txPorc.getText().trim())/100));
    //Neto2 = Math.round(Neto * (1- (Double.valueOf(txPorc.getText().trim())/100)));
    Neto2 = Neto - descto;
    Iva = Math.round((Neto2 * Double.parseDouble("1.19"))- Neto2);
    Total = Neto2 + Iva;
    
    
    txNeto.setText(fmMain.FormatoTotal(Neto));
    txExento.setText(fmMain.FormatoTotal(Exento));
    txTotNeto.setText(fmMain.FormatoTotal(Neto-descto));
    txIva.setText(fmMain.FormatoTotal(Iva));
    txTotal.setText(fmMain.FormatoTotal(Total));
    txDescuento.setText(fmMain.FormatoTotal(descto));
}

private String FormatoVisual (String Texto){
        DecimalFormat Formato = new DecimalFormat("###,###.##");
        return Formato.format(Texto);
    }
private String FormatoGuardar(String Texto){
        return Texto.replace(".", "");
}
//--------------------------------------------------------------------------------
    // ABRIR COTIZAC ION CLIENTE
//--------------------------------------------------------------------------------
public void AbrirOCP(String NumeroOCP){
    ExeSql Sql = new ExeSql();
    ExeSql Sql1 = new ExeSql();
    ResultSet Rs,Rs1;
    String Query="", QryVend="";
    DefaultTableModel TableModel = (DefaultTableModel) Grilla.getModel();
    
    
    while(TableModel.getRowCount()>0)
        TableModel.removeRow(0);
    
    try {
        Rs = Sql.Select("select * from ctactecli cp where tipdocto='CCL' and nrodocto=" + NumeroOCP);
        
        Rs.next();
        cbCodigoOc.removeAllItems();
        cbCodigoOc.addItem(Rs.getString("codigo_oc"));
        txRut.setText(Rs.getString("Rut"));
        btIr.setEnabled(true);
        
        
        btIr.doClick(); 
        
        txNroOc.setText(NumeroOCP);
        txComentario.setText(Rs.getString("comentarios").trim());
        dtEmision.setDate(Rs.getDate("femision"));
        
        
        
         if(Tipo==2 || Tipo==-1){
            
            cbCodigoOc.setSelectedItem(Rs.getString("codigo_oc"));
         }
         else
         {
             PosCodigoOc = cbCodigoOc.getSelectedIndex();
         } 
        
        txid_usuario.setText(Rs.getString("contacto"));
        
        //----------------------------------------------------------------
            //Trae Datos del Vendedor
            //----------------------------------------------------------------
        
            QryVend="select p.nombre || ' ' ||  p.apellidopaterno || ' ' ||   p.apellidomaterno as nombre, p.vendedor as id\n" +
                    "from personal p\n" +
                    "where p.vendedor =" + txid_usuario.getText();
        
            //Rs1 = Sql1.Select("select nombre,id from prv_contacto where rut=" + txRut.getText()  + " and id = " + txid_usuario.getText() + " order by tipo");
            Rs1 = Sql1.Select(QryVend);
            if(Sql1.GetRowCount()>0){
                Rs1.next();
                txVendedor.setText(Rs1.getString("nombre").trim());
                IdVendedor = Rs1.getInt("id");
            }
            else{
                IdVendedor=0;
            }
        
        
        txNeto.setText(fmMain.FormatoTotal(Rs.getDouble("totalafecto")));
        txExento.setText(fmMain.FormatoTotal(Rs.getDouble("totalexento")));
        txIva.setText(fmMain.FormatoTotal(Rs.getDouble("totaliva")));
        //txImpEspecifico.setText(fmMain.FormatoTotal(Rs.getDouble("totalimpespecifico")));
        txPorc.setText(fmMain.FormatoNumero(Rs.getDouble("porc_dscto")));
        double descto = (Rs.getDouble("porc_dscto")/100) * Rs.getDouble("totalafecto");
        txDescuento.setText(fmMain.FormatoTotal(descto));
        txTotNeto.setText(fmMain.FormatoTotal(Rs.getDouble("totalafecto")-descto));
        txTotal.setText(fmMain.FormatoTotal(Rs.getDouble("totaldocto")));
        

  //      jComboBox1.setSelectedIndex(Rs.getInt("tipodespacho"));
//        txDias.setText(Rs.getString("dias"));
//        cbCuotas.setSelectedIndex(Rs.getInt("cuotas") - 1);
 //       PosCuotas = Rs.getInt("cuotas") - 1;
        
        // ESTADOS
        
        if(Rs.getInt("estado")==-1){
            btImprimir.setEnabled(false);
            txEstado.setText("Por Autorizar");
            txEstado.setBackground(Color.yellow);
            btActualizar.setEnabled(true);
        }
        else if(Rs.getInt("estado")==-2){
            btImprimir.setEnabled(false);
            txEstado.setText("Rechazado");
            txEstado.setBackground(Color.red);
            btActualizar.setEnabled(true);
        }
        else if(Rs.getInt("estado")==-3){
            btImprimir.setEnabled(true);
            txEstado.setText("Autorizado");
            txEstado.setBackground(Color.green);
            btActualizar.setEnabled(true);
        }
        else{
            btImprimir.setEnabled(true);
            txEstado.setText("Emitida");
            txEstado.setBackground(Color.white);
            btActualizar.setEnabled(false);
        }
        ExeSqlLuvaly luv = new ExeSqlLuvaly();
        ResultSet producto = null;
        String query = "select pd.sku,p.nombre, pu.um, pd.cantidad, pd.valorunitario,pd.totallinea,\n"  +
                       "case when (select  rel.idch_seleccionado from rel_cotiza_idch rel \n" +
                       "where '0'||rel.sku = p.sku and cast(rel.nrodocto AS INTEGER) = pd.nrodocto) is null then '0'\n" +
                       "else (select  rel.idch_seleccionado from rel_cotiza_idch rel \n" +
                       "where '0'||rel.sku = p.sku and cast(rel.nrodocto AS INTEGER) = pd.nrodocto) end as idch\n" +
                       "from ctacteclidet pd\n" +
                       "left join producto   p  on pd.sku=p.sku\n" +
                       "left join par_unidad pu on p.unidad =pu.codigo\n" +
                       "where pd.nrodocto='"+NumeroOCP+"' and pd.tipdocto='CCL'" ;
//        
//        Query ="select pd.sku,p.nombre, pu.um, pd.cantidad, pd.valorunitario,pd.totallinea, case when cc.idch_seleccionado is null then '0' else cc.idch_seleccionado end as idch\n" +
//                        "from ctacteclidet pd\n" +
//                        "left join producto   p  on pd.sku=p.sku\n" +
//                        "left join par_unidad pu on p.unidad =pu.codigo  left join rel_cotiza_idch cc on '0'||cc.sku = p.sku \n" +
//                        "where pd.nrodocto=" + NumeroOCP + " and tipdocto='CCL' and cc.nrodocto = '" + NumeroOCP + "'";
        Rs = Sql.Select(query);
        while(Rs.next()){
               
                producto =  luv.Select("select p.nombre,trim(u.unidad) as unidad, u.um from producto p \n"
                                     + "LEFT JOIN par_unidad u ON u.codigo=p.unidad\n "
                                     + "where p.sku = '"+Rs.getString("sku").trim()+"'");
                producto.next();
                TableModel.addRow(new Object[]{ Rs.getString("sku").trim(), 
                                                producto.getString("nombre").trim(), 
                                                producto.getString("um").trim(),
//                                                Rs.getString("nombre").trim(), 
//                                                Rs.getString("um").trim(),
                                                fmMain.FormatoNumero(Rs.getDouble("cantidad")),
                                                fmMain.FormatoNumero(Rs.getDouble("valorunitario")),
                                                fmMain.FormatoNumero(Rs.getDouble("totallinea")),
                                                Rs.getDouble("valorunitario"),
                                                Rs.getDouble("cantidad"),
                                                Rs.getString("idch")});
            }
        CargaBlog();
        SetTipo(2);
        fmMain.pnPestanas.setTitleAt(fmMain.pnPestanas.getSelectedIndex(), "Cotización Cliente " + NumeroOCP);
        fmMain.pnPestanas.repaint();
        luv.Close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Cotización no encontrada");
         SetTipo(-1);
    }
    finally{
        Sql.Close();
        
    }
}
    
//--------------------------------------------------------------------------------
// EDICION
//--------------------------------------------------------------------------------

    private void Edicion(boolean Estado) {

        txNroOc.setEditable(!Estado);

        txRut.setEditable(Estado);
        txDv.setEditable(Estado);
        txNeto.setEditable(Estado);
        txExento.setEditable(Estado);
        txIva.setEditable(Estado);
        txTotNeto.setEditable(Estado);
        txTotal.setEditable(Estado);
        btAgregar.setEnabled(Estado);
        btEliminar.setEnabled(Estado);
//        btAsociados.setEnabled(Estado);
//        if(Estado==false) btAsociados.setForeground(Color.GRAY);
//        else    btAsociados.setForeground(Color.BLUE);
        txComentario.setEditable(Estado);
//        txNroOc.setEditable(Estado);
        
        dtEmision.setEditable(Estado);
//        dtDespacho.setEditable(Estado);

    }
//--------------------------------------------------------------------------------
// LIMPIA
//--------------------------------------------------------------------------------

    private void Limpia() {
        txRut.setText("");
        txNombre.setText("");
        txDv.setText("");
        txNeto.setText("");
        txExento.setText("");
        txIva.setText("");
        txDirDespacho.setText("");
        txPorc.setText("");
        txDescuento.setText("");
        txTotNeto.setText("");
        txTotal.setText("");
        txNroOc.setText("");
        txComentario.setText("");
        txVendedor.setText("");
        txNroOc.setText("");
        txEstado.setText("");
        cbCodigoOc.setSelectedIndex(-1);
        cbCodigoOc.removeAllItems();
//        Grilla.setModel(new DefaultTableModel());
        DefaultTableModel   dfTm = (DefaultTableModel) Grilla.getModel();
        while(dfTm.getRowCount()>0)
            dfTm.removeRow(0);
        
         DefaultTableModel tm = (DefaultTableModel) GrillaBlog.getModel();
        while(tm.getRowCount()>0)
            tm.removeRow(0); 
        
        dtEmision.setDate(null);
        
        
    //chbPrioridad.

    }
//--------------------------------------------------------------------------------
// HABILITA
//--------------------------------------------------------------------------------

    private void Habilita(boolean Estado) {

        txRut.setEnabled(Estado);
        txDv.setEnabled(Estado);
        txNombre.setEnabled(Estado);
        txVendedor.setEnabled(Estado);
        btContacto.setEnabled(Estado);
        
        
        txNroOc.setEnabled(Estado);
        dtEmision.setEnabled(Estado);
//        dtDespacho.setEnabled(Estado);
        btAgregar.setEnabled(Estado);
        btEliminar.setEnabled(Estado);
//        btAsociados.setEnabled(Estado);
        btIr.setEnabled(Estado);
//        cbCuotas.setEnabled(Estado);
//        txDias.setEnabled(Estado);
        txComentario.setEnabled(Estado);
        txNroOc.setEnabled(Estado);
        txEstado.setEnabled(Estado);
        
        
        
        txNeto.setEnabled(Estado);
        txExento.setEnabled(Estado);
        txIva.setEnabled(Estado);
        //txImpEspecifico.setEnabled(Estado);
        txPorc.setEnabled(Estado);
        txDescuento.setEnabled(Estado);
        txTotNeto.setEnabled(Estado);
        txTotal.setEnabled(Estado);
        btContacto.setEnabled(Estado);
        btOtrasOrdenes.setEnabled(Estado);
        txDirDespacho.setEnabled(Estado);
        cbCodigoOc.setEnabled(Estado);
        
//        if(Estado==false) btAsociados.setForeground(Color.GRAY);
//        else    btAsociados.setForeground(Color.BLUE);
    }
//--------------------------------------------------------------------------------
// Funcion CODIGO desde COMBOBOX
//--------------------------------------------------------------------------------

    private int cbId_Accion(JComboBox Combo) {
        int Codigo = -1;
        if (Combo.getSelectedIndex() != -1) {
            ComboCodigos id = (ComboCodigos) Combo.getSelectedItem();
            Codigo = id.getId();
            String nombre = Combo.getSelectedItem().toString();
            Combo.hidePopup();
        }
        return Codigo;
    }

//------------------------------------------------------------------------------
// Carga Proveedor
//------------------------------------------------------------------------------

    private boolean CargaProveedor(String Rut) {
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        DefaultComboBoxModel cbMd = new DefaultComboBoxModel();
        try {
            Rs = Sql.Select("select * from proveedor where rut="+Rut);
            
            Rs.next();
            txRut.setText(Rs.getString("Rut"));
            txDv.setText(Rs.getString("dv"));
            txNombre.setText(Rs.getString("nombre").trim());
            RutMaster = Rs.getString("Rut");
            try {
//                txDias.setText(Rs.getString("dias").trim());
//                cbCuotas.setSelectedIndex(Rs.getInt("cuotas")-1);
            } catch (Exception e) {
                
            }
            
             
            Rs = Sql.Select("select nombre,id from prv_contacto where rut=" + Rut  + " order by tipo");
            if(Sql.GetRowCount()>0){
                Rs.next();
                txVendedor.setText(Rs.getString("nombre").trim());
                IdVendedor = Rs.getInt("id");
            }
            else{
                IdVendedor=0;
            }
                
            
            dtEmision.setDate(new Date());
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 4);
//            dtDespacho.setDate(cal.getTime());
            
            SetTipo(1);
            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(txRut, e);
            return false;
        }
        finally{
            Sql.Close();
        }
    }
    
    
//Carga Cliente
 private boolean CargaCliente(String Rut){
    ExeSql Sql = new ExeSql();
    ResultSet Rs;
    
        try {

            Rs = Sql.Select("select rut,dv,nombre\n"
                    + "from cliente\n"
                    + "where rut=" + Rut);

            if (Rs.next()){
            txRut.setText(Rs.getString("Rut"));
            txDv.setText(Rs.getString("dv"));
            txNombre.setText(Rs.getString("nombre").trim());
            dtEmision.setDate(new Date());
            RutMaster = Rs.getString("Rut");}
            else
            {
                fmMain.Mensaje("Cliente consultado no existe");
                return false ;
            }
            CargaCodOc(RutMaster);
            
            //----------------------------------------------------------------
            //Trae Datos del Vendedor
            //----------------------------------------------------------------
            Rs = Sql.Select("select nombre,id from prv_contacto where rut=" + Rut  + " and id = " + txid_usuario.getText() + " order by tipo");
            if(Sql.GetRowCount()>0){
                Rs.next();
                txVendedor.setText(Rs.getString("nombre").trim());
                IdVendedor = Rs.getInt("id");
            }
            else{
                IdVendedor=0;
            }
            
            
            
            
            dtEmision.setDate(new Date());
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 4);
//            dtDespacho.setDate(cal.getTime());
            
           //SetTipo(1);
            return true;
            

        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            Sql.Close();
        }
    }
 
    
    
//------------------------------------------------------------------------------
// BOTONn  CANCELAR
//------------------------------------------------------------------------------
    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed

        if (fmMain.GetEstado(fmMain.pnPestanas.getSelectedIndex()) == 0) {
            SetTipo(-1);
        } else if(fmMain.OkCancel("¿Esta seguro de cancelar?") == JOptionPane.OK_OPTION){
            SetTipo(-1);
        }
        
      
    }//GEN-LAST:event_btCancelarActionPerformed

    private void txRutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txRutKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btIr.doClick();
        }
    }//GEN-LAST:event_txRutKeyPressed

//-----------------------------------------------------------------------------
//  BOTON IR
//-----------------------------------------------------------------------------
    private void btIrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIrActionPerformed
        boolean Carga;
        if (!txRut.getText().isEmpty()) {
            Carga = CargaCliente(txRut.getText());
        } else {
            jdBuscarCliPrv BPC = new jdBuscarCliPrv(null, true);
            BPC.setLocationRelativeTo(null);
            BPC.setTitle("Buscar Cliente");
            BPC.SetTipo(1);
            BPC.setVisible(true);
            Carga=CargaCliente(BPC.GetRut());
            txPorc.setText("0");
//               jdBuscarCliPrv BPC = new jdBuscarCliPrv(this, true);
//            BPC.setLocationRelativeTo(null);
//            BPC.setDefaultCloseOperation(BPC.DISPOSE_ON_CLOSE);
//            BPC.setTitle("Buscar Cliente");
//            BPC.SetTipo(0);
//            BPC.setVisible(true);
//          
        }
        
        click_codigo_oc();
        
    }//GEN-LAST:event_btIrActionPerformed
//-----------------------------------------------------------------------------
//  BOTON DETALLE
//-----------------------------------------------------------------------------
    private void btContactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btContactoActionPerformed
        String Vendedor;
        try {
            jdContactos Con = new jdContactos(null, true);
            //Vendedor = Con.Show(RutMaster, "TIPCONTACTOCLI",true).trim();
            Vendedor = Con.Show(RutMaster, "VENDEDORES",true).trim();
            if(!Vendedor.equals(""))
                txVendedor.setText(Con.GetUsuario());
                txid_usuario.setText(Con.GetUsuarioId());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
          
    }//GEN-LAST:event_btContactoActionPerformed
//-----------------------------------------------------------------------------
//  BOTON AGREGAR PRODUCTO
//-----------------------------------------------------------------------------
    private boolean ExisteEnGrilla(String Sku){
        for(int i=0; i< Grilla.getRowCount(); i++){
            if(Grilla.getValueAt(i,0).toString().trim().equals(Sku)){
//                Grilla.setRowSelectionInterval(i, 1);
                return true;
            }
        }
        return false;
    }

//-----------------------------------------------------------------------------
//  BOTON AGREGAR PRODUCTO
//-----------------------------------------------------------------------------
    private void btAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarActionPerformed
        DefaultTableModel tbModel = (DefaultTableModel) Grilla.getModel();


        jdCCLAgregaProducto AgregaProducto = new jdCCLAgregaProducto(null, true);
        AgregaProducto.setLocationRelativeTo(null);
        AgregaProducto.setTitle("Agregar Producto");
        AgregaProducto.setVisible(true);
        if(!ExisteEnGrilla(AgregaProducto.GetSku())){
            tbModel.addRow(AgregaProducto.GetFilaCCP());
            
            if (txPorc.getText().trim().equals("")){
                txPorc.setText("0");
            }
            Sumador();
        }
        else
            JOptionPane.showMessageDialog(null, "El producto ya esta en el listado");
        

    }//GEN-LAST:event_btAgregarActionPerformed
//-----------------------------------------------------------------------------
//  BOTON ELIMINAR PRODUCTO
//-----------------------------------------------------------------------------
    private void btEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEliminarActionPerformed
        if (!Grilla.getValueAt(Grilla.getSelectedRow(), 0).toString().isEmpty()) {
            if (JOptionPane.showConfirmDialog(null, "¿Desea Eliminar el registro?") == JOptionPane.YES_OPTION) {
                DefaultTableModel tbMd = (DefaultTableModel) Grilla.getModel();
                tbMd.removeRow(Grilla.getSelectedRow());
                Sumador();
            }
        }
    }//GEN-LAST:event_btEliminarActionPerformed
//-----------------------------------------------------------------------------
//  BOTON ABRIR
//-----------------------------------------------------------------------------
    private void btAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAbrirActionPerformed
        SetTipo(2);
        Limpia();
        AbrirOCP(JOptionPane.showInputDialog("Numero de Cotización:"));
        Sumador();
        CargaBlog();
    }//GEN-LAST:event_btAbrirActionPerformed
//-----------------------------------------------------------------------------
//  BOTON NUEVO
//-----------------------------------------------------------------------------
    private void btNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoActionPerformed
        if (fmMain.GetEstado(fmMain.pnPestanas.getSelectedIndex()) == 0) {
            SetTipo(-1);
            Limpia();
            txRut.setEnabled(true);
            txDv.setEnabled(true);
            btIr.setEnabled(true);
            txRut.requestFocus();
        }
          SetTipo(1);
    }//GEN-LAST:event_btNuevoActionPerformed
//-----------------------------------------------------------------------------
//  BOTON NUEVO
//-----------------------------------------------------------------------------

    private int BoolToInt(boolean Input) {
        if (Input == true) {
            return 1;
        } else {
            return 0;
        }
    }
public String getFechaEmisionAsString() {
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
return( sdf.format( (dtEmision.getDate()).getTime() ) );
}
//public String getFechaDespachoAsString() {
//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//return( sdf.format( (dtDespacho.getDate()).getTime() ) );
//}
//-----------------------------------------------------------------------------
//  BOTON GUARDAR
//-----------------------------------------------------------------------------
    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed
        Sumador();
        graba_cotiza();

     //Actuaiza Orden de Compra
    }//GEN-LAST:event_btGuardarActionPerformed

    
    public void graba_cotiza(){
    ExeSql Sql = new ExeSql();
    ExeSql Sql2 = new ExeSql();
    ResultSet Rs;
    String Query;
    String Codigos="",Codigos2 = "";
    String NewCorrelativo;
    
    if(JOptionPane.showConfirmDialog(null, "Guardar los cambios realizados.","Guardar",JOptionPane.YES_OPTION)!= JOptionPane.YES_OPTION){
        AbrirOCP(txNroOc.getText());
        return;
    }
    
    //Nueva Orden de Compra
     if(Tipo==1){
         try {
             if (txNeto.getText().equals("")){
                 fmMain.Mensaje("Debe ingresar items en el detalle, no se grabará");
                 return;
             }
           
//                Sql.ExeSql("update par_correlativo set  numero = numero + 1 where tipo='CCL'");
//                NewCorrelativo = Sql.SelectUnico("select numero from par_correlativo where tipo='CCL'");
//                
                        int resultado_ = Sql.ExeSqlInt("update par_correlativo\n" +
                                    "set numero = numero + 1\n" +
                                    "where tipo='CCL'");
                        Sql.Commit();
                        if(resultado_>0){
                            System.out.println("Se aumentó correlativo");
                        }
                        Rs = Sql.Select("select sp_getcorrelativo \n" +
                            "from sp_getcorrelativo('CCL')");   
                        Rs.next();
                        NewCorrelativo  = Rs.getString("sp_getcorrelativo");
                        Rs.close();
           
            Query ="  INSERT INTO ctactecli\n" +
                        "  (rut,tipdocto,nrodocto,femision,totalexento,totalafecto,totaliva,\n" +
                        "  porc_dscto,totaldocto,contacto,comentarios,codigo_oc) \n" +
                        "  VALUES (\n" +
                           txRut.getText() + ",\n" +
                        "  'CCL',\n" +
                           NewCorrelativo              +  ",'" +
                           getFechaEmisionAsString()   + "'," +
                           fmMain.SetGuardar(txExento.getText())          + "," +
                           fmMain.SetGuardar(txNeto.getText())             + "," +
                           fmMain.SetGuardar(txIva.getText())             + "," +
                           fmMain.SetGuardar(txPorc.getText())   + "," +
                           fmMain.SetGuardar(txTotal.getText())           + "," +
                          // getFechaDespachoAsString()  + "',\n" +
                           txid_usuario.getText()      + ",\n '" +
                           txComentario.getText()     + "'," + Integer.valueOf(cbCodigoOc.getSelectedItem().toString().trim()) + ")";
            
             Sql.ExeSql(Query);
             
             
             
             
             
//                           jComboBox1.getSelectedIndex() + "," +
//                           cbCuotas.getSelectedItem().toString().trim() +",'" + 
//                           txDias.getText().trim() + "')" );
             // Guarda Productos
                for(int i=0; i< Grilla.getRowCount(); i++){
                    if(!Grilla.getModel().getValueAt(i, 0).toString().equals("")){
                        Query = "INSERT INTO ctacteclidet\n" +
                                "(rut,tipdocto,nrodocto,sku,cantidad, valorunitario,totallinea) \n" +
                                "VALUES (\n" +
                                txRut.getText() + ",\n" +
                                "  'CCL',\n" +
                                NewCorrelativo               +  ",\n'" +
                                Grilla.getModel().getValueAt(i,GetCol("Sku")).toString() + "'," +
                                fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("CantReal")).toString()) + "," +
                                fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("UniReal")).toString()) + "," +
                                fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("Total")).toString()) + ")";
                        Sql.ExeSql(Query);
                        Sql2.ExeSql("insert into rel_cotiza_idch  (idch_seleccionado,nrodocto,sku) values ('"+Grilla.getModel().getValueAt(i,8).toString().trim()+"',"
                                             + "'"+ NewCorrelativo +"','"+ Grilla.getModel().getValueAt(i,0).toString().trim().substring(1,Grilla.getModel().getValueAt(i,0).toString().trim().length() ) +"') ");
        
                    }
                }
                
            Sql.Commit();
            Sql2.Commit();
            AbrirOCP(NewCorrelativo);
      
             JOptionPane.showMessageDialog(null, "Orden Guardada. Numero:" + NewCorrelativo);
         } catch (SQLException | HeadlessException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
             System.out.println(e.getMessage());
             Sql.Rollback();
             Sql2.Close();
         }
         finally{
             Sql.Close();
             Sql2.Close();
         }
             
     }
     /**  
      * Actualiza OCP
      */
     else{
         try{
             
          Query ="UPDATE ctactecli  \n" +
                    "SET \n" +
                    "femision = '"+ getFechaEmisionAsString()  +"',\n" +
                    "totalexento = "+ fmMain.SetGuardar(txExento.getText()) +",\n" +
                    "totalafecto = "+fmMain.SetGuardar(txNeto.getText())+",\n" +
                    "totaliva = "+fmMain.SetGuardar(txIva.getText())+",\n" +
                    "codigo_oc = "+ cbCodigoOc.getSelectedItem().toString().trim()   +",\n" +
                  "porc_dscto = "+fmMain.SetGuardar(txPorc.getText())+",\n" +
                    "totaldocto = "+fmMain.SetGuardar(txTotal.getText())+",\n" +
//                    "fdespacho = '"+getFechaDespachoAsString()+"',\n" +
                   "contacto = "+ txid_usuario.getText() +", \n" +
                    "comentarios = '"+txComentario.getText()+"'\n" +
                    "WHERE rut = "+txRut.getText()+"\n" +
                    "AND  tipdocto = 'CCL'\n" +
                    "AND  nrodocto = " + txNroOc.getText();    
             
         Sql.ExeSql(Query);
         
         //ACTUALIZA EL DETALLE
        for(int i=0; i< Grilla.getRowCount(); i++){
            
             // 1. Verifica si existe
            Rs = Sql.Select("select count(*) as Existe from ctacteclidet \n" +
                                    "  where  rut= "+ txRut.getText() +
                                    "  and nrodocto=" + txNroOc.getText().trim() + "\n" +
                                    "  and tipdocto='CCL'\n" + 
                                    "  and sku='"+ Grilla.getModel().getValueAt(i,GetCol("Sku")).toString().trim() +"';");
            Rs.next();
         // 2. Si Existe UPDATE
            if(Rs.getInt("Existe")>0){
                        Query =  "  update ctacteclidet set \n" +
                                     "  cantidad = " + fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("CantReal")).toString()) + ",\n" +
                                     "  valorunitario="+ fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("UniReal")).toString()) +",\n" +
                                     "  totallinea ="+ fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("Total")).toString()) +"\n" +
                                     "  where  rut= "+ txRut.getText() +
                                     "  and nrodocto=" + txNroOc.getText().trim() + "\n" +
                                     "  and tipdocto='CCL'\n" + 
                                     "  and sku='"+ Grilla.getModel().getValueAt(i,GetCol("Sku")).toString() +"'";
                                    
                                    Sql2.ExeSql("update rel_cotiza_idch set idch_seleccionado = '"+Grilla.getModel().getValueAt(i,8).toString().trim()+"' "
                                             + "where nrodocto = '"+ txNroOc.getText().trim() +"' and sku='"+ Grilla.getModel().getValueAt(i,0).toString().trim().substring(1,Grilla.getModel().getValueAt(i,0).toString().trim().length()) +"' ");
            }
         // 3. Si no existe INSERT
            else {
                        Query = "INSERT INTO ctacteclidet\n" +
                            "(rut,tipdocto,nrodocto,sku,cantidad, valorunitario,totallinea) \n" +
                            "VALUES (\n" +
                            txRut.getText() + ",\n" +
                            "  'CCL',\n" +
                            txNroOc.getText()               +  ",\n'" +
                            Grilla.getModel().getValueAt(i,GetCol("Sku")).toString().trim() + "'," +
                            fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("CantReal")).toString()) + "," +
                            fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("UniReal")).toString()) + "," +
                            fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("Total")).toString()) + ")";
                            Sql2.ExeSql("insert into rel_cotiza_idch  (idch_seleccionado,nrodocto,sku) values ('"+Grilla.getModel().getValueAt(i,8).toString().trim()+"',"
                                             + "'"+ txNroOc.getText().trim() +"','"+ Grilla.getModel().getValueAt(i,0).toString().trim().substring(1,Grilla.getModel().getValueAt(i,0).toString().trim().length() ) +"') ");
        
            }
        
        Sql.ExeSql(Query); 
        // 4. Agrega sku al LISTADO
            Codigos = Codigos + "'" + Grilla.getModel().getValueAt(i,GetCol("Sku")).toString().trim() + "',";
            Codigos2 = Codigos2 + "'" + Grilla.getModel().getValueAt(i,0).toString().trim().substring(1,Grilla.getModel().getValueAt(i,0).toString().trim().length() ) + "',";
        } 
             System.out.println(Codigos2);
        
         // 5. Elimina productos que ya no pertenecen
             Query =  " DELETE from ctacteclidet " + 
                      " WHERE  rut= "+ txRut.getText() +
                      " AND nrodocto=" + txNroOc.getText().trim() + "\n" +
                      " AND tipdocto='CCL'\n" + 
                      " AND sku not in (" + Codigos.substring(0, Codigos.length()-1) + ")";
             
             Sql.ExeSql(Query);
             Sql2.ExeSql("delete from rel_cotiza_idch where nrodocto =  '"+ txNroOc.getText().trim() +"' AND sku not in (" + Codigos2.substring(0, Codigos2.length()-1) + ")");
        //AbrirOCP(txNroOc.getText());
        
                
        Sql.Commit();
        Sql2.Commit();
        
        //AbrirOCP(txNroOc.getText());
        JOptionPane.showMessageDialog(null, "Cotización Actualizada"); 
         SetTipo(-1);
        
         } catch (SQLException | HeadlessException e) {
             JOptionPane.showMessageDialog(null, e);
             Sql.Rollback();
             Sql2.Rollback();

         }
         finally{
             Sql.Close();
             Sql2.Close();
         }
         
     }
    }  //Fin Graba Cotiza
    
    
    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        SetTipo(3);
    }//GEN-LAST:event_btEditarActionPerformed

    private void dtEmisionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtEmisionMouseClicked
        if (Tipo == 2) {
            evt.consume();
        }
    }//GEN-LAST:event_dtEmisionMouseClicked

    private void btOtrasOrdenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOtrasOrdenesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btOtrasOrdenesActionPerformed

    private void btImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImprimirActionPerformed
        Sumador();
        String Query ="";
         Query = "select lower(p.usuario)   as nombre\n" +
            " from personal p\n" +
            " where p.vendedor >0";
         
        jdQuienFirmaCot qf = new jdQuienFirmaCot(null, true);
        qf.setLocationRelativeTo(null);
        qf.setTitle("Firma Vendedor");
        qf.CargaCombo(Query);
        qf.setVisible(true);
        firmacompra=qf.getNombre();
        
        
        URL in = this.getClass().getResource("/Reportes/repCCLI.jasper");
        JasperReport repo = null;
        HashMap<String, Object> parametros = new HashMap<>();
        List listaProd = new ArrayList();
        String Atento = "Cotizacion";
        Query="";
        
        Atento = txVendedor.getText().trim();
        
        try {
            repo = (JasperReport)JRLoader.loadObject(in);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        }

        

        
        
        //String Atento = cbVendedor.getSelectedItem().toString();
        
//        if(Atento.isEmpty()) Atento="  ";
         
        ResultSet Rs;
        ExeSql Sql = new ExeSql();
        ExeSqlLuvaly luv = new ExeSqlLuvaly();
        //--------------
        try {
            // Se emite Cotizacion
            Sql.ExeSql("update ctactecli \n" +
                            "set estado=0\n" +
                            "where tipdocto='CCL'\n" +
                            "and nrodocto=" + txNroOc.getText() + " and estado=-3");
            
//            JOptionPane.showMessageDialog(null, System.getProperty("user.dir")+"\\src\\Reportes\\ClaudiaTorres.jpg");

            Rs = Sql.Select("select pd.sku,p.nombre, pu.um, pd.cantidad, pd.valorunitario,pd.totallinea, case when cc.idch_seleccionado is null then '0' else cc.idch_seleccionado end as idch\n" +
                            "from ctacteclidet pd\n" +
                            "left join producto   p  on pd.sku=p.sku\n" +
                            "left join par_unidad pu on p.unidad =pu.codigo\n" +
                            "left join rel_cotiza_idch cc on '0'||cc.sku = p.sku and cc.nrodocto = '"+ txNroOc.getText()+"'\n" +
                            "where pd.nrodocto="+ txNroOc.getText() + " and tipdocto='CCL'");
            
            while(Rs.next()){
                ResultSet producto = luv.Select("select p.nombre, u.um from producto p \n"
                + "LEFT JOIN par_unidad u ON u.codigo=p.unidad\n "
                + "where p.sku = '"+Rs.getString("sku").trim()+"'");
                producto.next();
                ListarProductos lista = new ListarProductos(
                                            Rs.getString("sku"), 
                                            producto.getString("nombre"),
                                            producto.getString("um"),
                                            fmMain.FormatoTotal(Rs.getDouble("cantidad")),
                                            fmMain.FormatoNumero(Rs.getDouble("valorunitario")),
                                            fmMain.FormatoTotal(Rs.getDouble("totallinea")), Rs.getString("idch").trim());

                listaProd.add(lista);
            }

            
// este qry estaba nov 17                    
//            Query = "select c.rut || '-' || c.dv as ElRut,\n" +
//                "c.nombre as nombreprv,cc.direccion,cp.* \n" +
//                "from ctactecli cp \n" +
//                "left join cliente c on c.rut=cp.rut\n" +
//                "left join clicontacto cc on cc.rut = cp.rut \n" +
//                "where cp.tipdocto='CCL' and cp.nrodocto=" + txNroOc.getText();
//            
//            Rs = Sql.Select(Query); 
            
//            Rs = Sql.Select( "select p.rut || '-' || p.dv as ElRut,p.nombre as nombreprv,p.direccion,p.fono,cp.* \n" +
//                             "from ctactecli cp \n" +
//                             "left join proveedor p on p.rut=cp.rut \n" +
//                             "where cp.tipdocto='CCL' and cp.nrodocto=" + txNroOc.getText()); 
            //Rs.next();
            parametros.put("NroOc",txNroOc.getText() );
            parametros.put("Nombre",txNombre.getText().trim() ); //Rs.getString("nombreprv")
            parametros.put("Rut", txRut.getText() + "-" + txDv.getText());//Rs.getString("ElRut")
            parametros.put("Direccion", txDirDespacho.getText());
            parametros.put("Fecha", dtEmision.getDate());
            parametros.put("Atencion",  Atento);
//            parametros.put("Fono", Rs.getString("fono"));

            
            
            //parametros.put("ImagenComprador", this.getClass().getResourceAsStream("/Reportes/adiaz.jpg"));
            String Ruta = "/Reportes/" + firmacompra + ".png";
            parametros.put("ImagenComprador", this.getClass().getResourceAsStream(Ruta));
            parametros.put("ImagenLogo", this.getClass().getResourceAsStream("/Reportes/Logo.jpg"));
//            parametros.put("Cuotas", "3");
//            parametros.put("Dias", Rs.getString("dias"));
            
            
            if (Double.valueOf(txPorc.getText())>0)
                {
                    parametros.put("descto", "Descto");
                    parametros.put("porcentaje",txPorc.getText());
                    parametros.put("Mto_descuento",txDescuento.getText());
                }
            else
                {
                    parametros.put("descto", "");
                    parametros.put("porcentaje","");
                    parametros.put("Mto_descuento","");
                }
            
//            parametros.put("descto", "Decto");
//            parametros.put("porcentaje",txPorc.getText());
//            parametros.put("Mto_descuento",txDescuento.getText());
            
            parametros.put("Comentarios", txComentario.getText());
            parametros.put("TotalNeto", txNeto.getText());
            
            
             parametros.put("TNeto",txTotNeto.getText());
            parametros.put("TotalIva", txIva.getText());
            parametros.put("TotalDocto",txTotal.getText());

//            JasperPrint informe =  JasperFillManager.fillReport(master, parametros ,new JREmptyDataSource());
            JasperPrint informe =  JasperFillManager.fillReport(repo, parametros, new JRBeanCollectionDataSource(listaProd));
            JasperViewer.viewReport(informe,false);
            Sql.Commit();
        } catch (SQLException | JRException e) {
            JOptionPane.showMessageDialog(null,e);
            
        }
        finally{
            Sql.Close();
        }
    }//GEN-LAST:event_btImprimirActionPerformed

    private void GrillaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GrillaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_GrillaKeyPressed

    private void GrillaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GrillaKeyTyped
//        if(evt.getKeyChar()==',' || evt.getKeyChar()=='.'){
//            evt.consume();
//            if(!txCantidad.getText().contains(fmMain.GetDecimal()))
//                txCantidad.setText(txCantidad.getText() + fmMain.GetDecimal() );
//        }
    }//GEN-LAST:event_GrillaKeyTyped

    private void GrillaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GrillaMouseClicked
        DefaultTableModel tbModel = (DefaultTableModel) Grilla.getModel();
        try {
            
        
        if(evt.getClickCount()==2 && Tipo==1 || Tipo==3){
            
            jdCCLAgregaProducto AgregaProducto = new jdCCLAgregaProducto(null,true);
            AgregaProducto.setLocationRelativeTo(null);
            if (Tipo==3){
                AgregaProducto.setTitle("Editar Producto");
                System.out.println(Grilla.getValueAt(Grilla.getSelectedRow(),8).toString().trim());
                
                AgregaProducto.SetProducto(Grilla.getValueAt(Grilla.getSelectedRow(),GetCol("Sku")).toString().trim(),
                                       Grilla.getValueAt(Grilla.getSelectedRow(),GetCol("Cantidad")).toString(),
                                       Grilla.getValueAt(Grilla.getSelectedRow(),GetCol("UniReal")).toString(),false);
                AgregaProducto.SelectIDCH(Grilla.getValueAt(Grilla.getSelectedRow(),8).toString().trim());
            }
            else
            {
                AgregaProducto.setTitle("Agregar Producto");
                
                AgregaProducto.SetProducto(Grilla.getValueAt(Grilla.getSelectedRow(),GetCol("Sku")).toString().trim(),
                                       Grilla.getValueAt(Grilla.getSelectedRow(),GetCol("Cantidad")).toString(),
                                       Grilla.getValueAt(Grilla.getSelectedRow(),GetCol("UniReal")).toString(),true);
                AgregaProducto.SelectIDCH(Grilla.getValueAt(Grilla.getSelectedRow(),8).toString().trim());
            }
            
            
            AgregaProducto.setVisible(true);
            if(AgregaProducto.GetFilaCCP()!=null){
                
                tbModel.insertRow(Grilla.getSelectedRow(), AgregaProducto.GetFilaCCP());
                tbModel.removeRow(Grilla.getSelectedRow()+1);
                AgregaProducto.SelectIDCH(Grilla.getValueAt(Grilla.getSelectedRow(),8).toString().trim());
                Sumador();
            }
        }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_GrillaMouseClicked

    private void btLiberarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLiberarActionPerformed
//        ExeSql Sql = new ExeSql();
//        ResultSet Rs;
//
//        try {
//            //Verificar Recepción
//            Rs = Sql.Select("select count(*) as Existe from recepcionprv where ocp=" + txNroOc.getText().trim());
//            Rs.next();
//            if(Rs.getInt("Existe")>0){
//                JOptionPane.showMessageDialog(null, "Orden de Compra con movimientos. No se puede liberar");
//                return;
//            }
//            if(fmMain.OkCancel("¿Realmente desea liberar ésta Orden de compra?")== JOptionPane.OK_OPTION){
//                Sql.ExeSql("delete from ctacteprv where rut=" + txRut.getText().trim() + " and tipdocto='OCP' and nrodocto=" + txNroOc.getText().trim());
//                Sql.ExeSql( "insert into folioslibres \n" +
//                            "(tipdocto,nrodocto) values\n" +
//                            "('OCP',"+ txNroOc.getText().trim() + ")");
//                Sql.Commit();
//                SetTipo(-1);
//                JOptionPane.showMessageDialog(null, "Orden Liberada");
//            }
//        } catch (SQLException | HeadlessException e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
//        finally{
//            Sql.Close();
//        }
        
    }//GEN-LAST:event_btLiberarActionPerformed

    private void btActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btActualizarActionPerformed
        AbrirOCP(txNroOc.getText());
    }//GEN-LAST:event_btActualizarActionPerformed

    private void btAgregarBlogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarBlogActionPerformed
        jdAgregaBlog AddBlog = new jdAgregaBlog(null, true);
        AddBlog.SetDatos(txRut.getText().trim(),"CCL",txNroOc.getText().trim());
        AddBlog.setTitle("Nuevo Registro");
        AddBlog.setLocationRelativeTo(btAgregarBlog);
        AddBlog.setVisible(true);
        if(AddBlog.GetGuardar())
        CargaBlog();
    }//GEN-LAST:event_btAgregarBlogActionPerformed

    // ********* CARGA OC DESDE CONTROL DE INVENTARIO NEGATIVOS ************** //
    
    public void CargaNuevaOC(String rutProv){
        DefaultTableModel Model = (DefaultTableModel)Grilla.getModel();
        btNuevo.doClick();
        txRut.setText(rutProv);
        btIr.doClick();
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        try {
            Rs = Sql.Select("select DISTINCT(i.sku) as sku, pro.nombre, u.um as umedida, ABS(i.stock+i.ocp+i.occ+i.gdc) as cantidad,\n" +
                    "round (cast(pro.valultcompra as numeric),2) as unitario,\n" +
                    "round(((cast(ABS(i.stock+i.ocp+i.occ+i.gdc) as numeric)) * round (cast(pro.valultcompra as numeric),2)),2) as totlinea from inventario i\n" +
                    "left join producto pro on i.sku=pro.sku\n" +
                    "left join par_unidad u on pro.unidad=u.codigo\n" +
                    "left join ctacteprvdet d on i.sku=d.sku\n" +
                    "left join proveedor p on d.rut=p.rut\n" +
                    "WHERE i.stock+i.ocp+i.occ+i.gdc<0 \n" +
                    "and p.nombre is not null\n" +
                    "and p.rut="+rutProv);
             while(Rs.next()){
                    Model.addRow(new Object[]{Rs.getString("sku").trim(), Rs.getString("nombre").trim(), Rs.getString("umedida").trim(), Rs.getString("cantidad").trim(),  Rs.getString("unitario").trim(),  Rs.getString("totlinea").trim(), Rs.getString("unitario").trim(), Rs.getString("cantidad").trim()});
                   
             }
            Sumador();
            //btAgregar.doClick();
        } catch (Exception e)  {
          System.out.println(e.getMessage());
        }
        finally{
            Sql.Close();
        }
        
    }
    
      // ********* FIN ************** //
    
    private void txRutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txRutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txRutActionPerformed

    private void txDvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txDvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txDvActionPerformed

    private void txPorcKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txPorcKeyPressed
        // TODO add your handling code here:
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sumador();
            graba_cotiza();
        }
        
    }//GEN-LAST:event_txPorcKeyPressed

    private void txPorcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txPorcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txPorcActionPerformed

    private void txPorcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txPorcKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();   
        if(c ==',' || c=='.'){
            evt.consume();
            if(!txPorc.getText().contains(fmMain.GetDecimal()))
                txPorc.setText(txPorc.getText() + fmMain.GetDecimal() );
        }
        
         if(Character.isLetter(c)) { 
              getToolkit().beep(); 
              evt.consume(); 
              System.out.println("Ingresa Solo Letras -->" + c ); 
         }  
    }//GEN-LAST:event_txPorcKeyTyped

    
    private void click_codigo_oc(){
//           if(Tipo==2 || Tipo==-1)
//               if (PosCodigoOc==0){
//                    cbCodigoOc.setSelectedItem(String.valueOf(PosCodigoOc));
//                }
//                else
//                    {cbCodigoOc.setSelectedIndex(0);
//                   }
//           
//        else{
            ExeSql Sql= new ExeSql();
            ResultSet Rs;

            try {
                Rs = Sql.Select("select morosidad,bloqueo,direccion || '(' || comuna  || ')' direccion\n" +
                    "from clicontacto\n" +
                    "where rut="+ txRut.getText().trim() +"\n" +
                    "and codigo_oc="+ cbCodigoOc.getSelectedItem().toString().trim() );
                Rs.next();
                if(Rs.getInt("bloqueo")==1){
                    lbInfo.setVisible(true);
                    lbInfo.setText("Cliente Bloqueado.");
                }
                //                else if (Rs.getInt("morosidad")==1){
                    //                    lbInfo.setVisible(true);
                    //                    lbInfo.setText("Cliente con facturas vencidas");
                    //                }
                else{
                    lbInfo.setVisible(false);
                }

                txDirDespacho.setText(Rs.getString("direccion").trim());

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }finally{
                Sql.Close();
            }
        //}
    }
    
    
    private void cbCodigoOcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCodigoOcActionPerformed
       click_codigo_oc();
    }//GEN-LAST:event_cbCodigoOcActionPerformed

    private void txDirDespachoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txDirDespachoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txDirDespachoActionPerformed

    private void txDirDespachoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txDirDespachoKeyTyped
        if (Character.isLowerCase(evt.getKeyChar()))
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));        // TODO add your handling code here:
    }//GEN-LAST:event_txDirDespachoKeyTyped

    private void txRutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txRutKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txRutKeyReleased

    private void txComentarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txComentarioKeyPressed
        // TODO add your handling code here:
          if (Character.isLowerCase(evt.getKeyChar()))
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar())); 
    }//GEN-LAST:event_txComentarioKeyPressed

    private void txComentarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txComentarioKeyReleased
        // TODO add your handling code here:
        txComentario.setText(txComentario.getText().toUpperCase());
    }//GEN-LAST:event_txComentarioKeyReleased
    
        private void CargaBlog(){
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        
        GrillaBlog.setDefaultRenderer(Object.class, new ImgTabla());
        DefaultTableModel tm = (DefaultTableModel) GrillaBlog.getModel();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy - hh:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MMM-yyyy");
        
        while(tm.getRowCount()>0)
            tm.removeRow(0); 
        
        JLabel lbCall = new JLabel();
        JLabel lbMail = new JLabel();
        JLabel lbFin  = new JLabel();
        JLabel lbIni  = new JLabel();
        JLabel lbInfo = new JLabel();
        
        URL urlCall = this.getClass().getResource("/Iconos/Llamada.png");  
        URL urlMail = this.getClass().getResource("/Iconos/email.png");  
        URL urlIni =  this.getClass().getResource("/Iconos22/Go.png");  
        URL urlFin =  this.getClass().getResource("/Iconos/camion.png");  
        URL urlInfo =  this.getClass().getResource("/Iconos/info.png");
         
        ImageIcon IconoCall =  new ImageIcon(urlCall); 
        ImageIcon IconoMail =  new ImageIcon(urlMail); 
        ImageIcon IconoIni  =  new ImageIcon(urlIni); 
        ImageIcon IconoFin  =  new ImageIcon(urlFin); 
        ImageIcon IconoInfo =  new ImageIcon(urlInfo); 
        
        lbCall.setIcon(IconoCall);
        lbMail.setIcon(IconoMail);
        lbIni.setIcon(IconoIni);
        lbFin.setIcon(IconoFin);
        lbInfo.setIcon(IconoInfo);
        
        try {
            Rs = Sql.Select("SELECT id,fecha,usuario,texto,tipo,compromiso,fcompromiso\n" +
                            "FROM blog_ocp\n" +
                            "where rut=" + txRut.getText().trim() + "\n" +
                            "and tipdocto='CCL' \n" + 
                            "and nrodocto=" + txNroOc.getText().trim());
            
            while(Rs.next()){
                 //Tipo 0 = Llamada
                if(Rs.getInt("tipo")==1){
                    tm.addRow(new Object[]{lbCall,sdf.format(Rs.getTimestamp("fecha"))+ "   [" + Rs.getString("usuario").trim().toUpperCase() + "]",Rs.getString("id")});
                    
                }
                //Tipo 1 = Correo
                else if(Rs.getInt("tipo")==2) {
                    tm.addRow(new Object[]{lbMail,sdf.format(Rs.getTimestamp("fecha"))+ "   [" + Rs.getString("usuario").trim().toUpperCase() + "]",Rs.getString("id")});
                }
                // Tipo 2 = Bloque
                else if(Rs.getInt("tipo")==3) {
                    tm.addRow(new Object[]{lbIni,sdf.format(Rs.getTimestamp("fecha"))+ "   [" + Rs.getString("usuario").trim().toUpperCase() + "]",Rs.getString("id")});
                }
                // Tipo 3 = Desbloqueo
                else if(Rs.getInt("tipo")==4) {
                    tm.addRow(new Object[]{lbFin,sdf.format(Rs.getTimestamp("fecha"))+ "   [" + Rs.getString("usuario").trim().toUpperCase() + "]",Rs.getString("id")});
                }
                
                else if(Rs.getInt("tipo")==5) {
                    tm.addRow(new Object[]{lbInfo,sdf.format(Rs.getTimestamp("fecha"))+ "   [" + Rs.getString("usuario").trim().toUpperCase() + "]",Rs.getString("id")});
                }
                
                 //Mensaje
                tm.addRow(new Object[]{" ",Rs.getString("texto"),Rs.getString("id")});
                if(Rs.getBoolean("compromiso"))
                    tm.addRow(new Object[]{" ", "Compromiso: " + sdf2.format(Rs.getTimestamp("fcompromiso"))});
                    
            }
            
        } catch (Exception e) {
            fmMain.Mensaje("Ha ocurrido un error");
            LogError.Guardar(this.getClass().getSimpleName(),e.getMessage());
        } finally {
            Sql.Close();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Grilla;
    private javax.swing.JTable GrillaBlog;
    private javax.swing.JButton btAbrir;
    private javax.swing.JButton btActualizar;
    private javax.swing.JButton btAgregar;
    private javax.swing.JButton btAgregarBlog;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btContacto;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btEliminar;
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btImprimir;
    private javax.swing.JButton btIr;
    private javax.swing.JButton btLiberar;
    private javax.swing.JButton btNuevo;
    private javax.swing.JButton btOtrasOrdenes;
    private javax.swing.JComboBox cbCodigoOc;
    private org.jdesktop.swingx.JXDatePicker dtEmision;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private javax.swing.JLabel lbInfo;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JTextArea txComentario;
    private javax.swing.JTextField txDescuento;
    private javax.swing.JTextField txDirDespacho;
    private javax.swing.JTextField txDv;
    private javax.swing.JTextField txEstado;
    private javax.swing.JTextField txExento;
    private javax.swing.JTextField txIva;
    private javax.swing.JTextField txNeto;
    private javax.swing.JTextField txNombre;
    private javax.swing.JTextField txNroOc;
    private javax.swing.JTextField txPorc;
    private javax.swing.JTextField txRut;
    private javax.swing.JTextField txTotNeto;
    private javax.swing.JTextField txTotal;
    private javax.swing.JTextField txVendedor;
    private javax.swing.JTextField txid_usuario;
    // End of variables declaration//GEN-END:variables
}

