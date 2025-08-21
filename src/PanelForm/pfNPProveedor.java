package PanelForm;
//PRUEBA
import Conexion.ExeSql;
import Conexion.ExeSqlLuvaly;
import Dialogos.jdAgregaBlog;

import Dialogos.jdBuscarCliPrv;
import Dialogos.jdAsociaCodChilemat;
import Dialogos.jdBuscarHistorial;
import Dialogos.jdContactos;
import Dialogos.jdDiasOCP;
import Dialogos.jdIngresaNumero;
import Dialogos.jdOCCAgregaProducto;
import Dialogos.jdOCCContacto;
import Dialogos.jdProductosAsociadosPrv;
import Dialogos.jdQuienFirma;
import Formularios.fmMain;
import static Formularios.fmMain.intNivelUsuario;
import static Formularios.fmMain.pnPestanas;
import static PanelForm.pfBuscaDoc.BuscaArchivos;
import Utilidades.ComboCodigos;
import Utilidades.Ftp;
import Utilidades.ImgTabla;
import Utilidades.ListarProductos;
import Utilidades.LogError;
import Utilidades.PanelTab;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author MauricioLuvaly
 */
public class pfNPProveedor extends javax.swing.JPanel{

     ArrayList<ArrayList<String>> ListaOCC  = new ArrayList<ArrayList<String>>();
  //***********************************************************************************  
    
    public boolean OCCL = false;
     
    public int intNivelMnu = 0;
    public String firmacompra;
    String RutMaster;
    int Tipo; // 0::Nuevo    1:Abrir
    int PosCuotas;
    int IdVendedor;
    int genera = 0;
    
    String NroOc = "";
    String NroOp = "";
    
    Date Hoy = new Date();
    
    
    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    private enum Columnas{Sku,Codprv,Nombre,UM,Cantidad,Unitario,Total,UniReal,CantReal,TotalPorcentaje,n,n1,n2,n3,Recibido};

    public pfNPProveedor() {
        initComponents();
        txSkuTemporal.setVisible(false);
        txDescTemporal.setVisible(false);
        txUMTemporal.setVisible(false);
        txCodBarTemporal.setVisible(false);
        txCantidadTemporal.setVisible(false);
        txUnitarioTemporal.setVisible(false);
        txTotlineaTemporal.setVisible(false);
        btGenLuvaly.setVisible(false);
        
        btLiberar.setVisible(false);
        btActualizar.setVisible(false);
        btCerrar.setVisible(false);
        
        btAgregar.setVisible(false);
        btEliminar.setVisible(false);
        
        
        dtEmision.setFormats(new String[] {"dd/MM/yyyy"});
        dtDespacho.setFormats(new String[] {"dd/MM/yyyy"});
        Habilita(false);
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        Grilla.getColumnModel().getColumn(GetCol("Codprv")).setCellRenderer(centerRenderer);
        Grilla.getColumnModel().getColumn(GetCol("Cantidad")).setCellRenderer(rightRenderer);
        Grilla.getColumnModel().getColumn(GetCol("Unitario")).setCellRenderer(rightRenderer);
        Grilla.getColumnModel().getColumn(GetCol("Total")).setCellRenderer(rightRenderer);
        Grilla.getColumnModel().getColumn(GetCol("TotalPorcentaje")).setCellRenderer(rightRenderer);
        Grilla.getColumnModel().getColumn(GetCol("Recibido")).setCellRenderer(rightRenderer);
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
        btCerrar = new javax.swing.JButton();
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
        jLabel7 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txNombre = new javax.swing.JTextField();
        txComentario = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        txVendedor = new javax.swing.JTextField();
        OCLuvalyEcona = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txNroOc = new javax.swing.JTextField();
        btOtrasOrdenes = new javax.swing.JButton();
        btGenLuvaly = new javax.swing.JButton();
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
        txImpEspecifico = new javax.swing.JTextField();
        lbExento = new javax.swing.JTextField();
        lbNeto = new javax.swing.JTextField();
        lbIVA = new javax.swing.JTextField();
        lbImp = new javax.swing.JTextField();
        lbTotal = new javax.swing.JTextField();
        btAgregar = new javax.swing.JButton();
        btEliminar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dtEmision = new org.jdesktop.swingx.JXDatePicker();
        dtDespacho = new org.jdesktop.swingx.JXDatePicker();
        jLabel16 = new javax.swing.JLabel();
        txEstado = new javax.swing.JTextField();
        btAsociados = new javax.swing.JButton();
        txSkuTemporal = new javax.swing.JTextField();
        txDescTemporal = new javax.swing.JTextField();
        txUMTemporal = new javax.swing.JTextField();
        txCodBarTemporal = new javax.swing.JTextField();
        txCantidadTemporal = new javax.swing.JTextField();
        txUnitarioTemporal = new javax.swing.JTextField();
        txTotlineaTemporal = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        GrillaBlog = new javax.swing.JTable();
        btAgregarBlog = new javax.swing.JButton();
        txSku = new javax.swing.JLabel();
        btMostrarHistorial = new javax.swing.JButton();

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
        btImprimir.setText("Emitir");
        btImprimir.setEnabled(false);
        btImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImprimirActionPerformed(evt);
            }
        });

        jXLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jXLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/OCPoveedor24.png"))); // NOI18N
        jXLabel1.setText("NOTA DE PEDIDO");
        jXLabel1.setToolTipText("");
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

        btCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/ok_16.png"))); // NOI18N
        btCerrar.setText("Cerrar NPP");
        btCerrar.setEnabled(false);
        btCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btCerrar)
                .addGap(102, 102, 102)
                .addComponent(btLiberar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btActualizar)
                .addContainerGap(198, Short.MAX_VALUE))
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btAbrir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(btGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(btActualizar)
                    .addComponent(jXLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(btLiberar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                "Código", "CodPrv", "Nombre", "UM", "Cantidad", "V. Unitario", "Total Linea", "null", "null", "TotalPorcentaje", "Porcentaje", "Codigo_Econa", "Nombre_Econa", "ocp", "Recibido"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
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
            Grilla.getColumnModel().getColumn(0).setMinWidth(70);
            Grilla.getColumnModel().getColumn(0).setPreferredWidth(70);
            Grilla.getColumnModel().getColumn(0).setMaxWidth(70);
            Grilla.getColumnModel().getColumn(1).setMinWidth(0);
            Grilla.getColumnModel().getColumn(1).setPreferredWidth(0);
            Grilla.getColumnModel().getColumn(1).setMaxWidth(0);
            Grilla.getColumnModel().getColumn(2).setResizable(false);
            Grilla.getColumnModel().getColumn(2).setPreferredWidth(350);
            Grilla.getColumnModel().getColumn(3).setResizable(false);
            Grilla.getColumnModel().getColumn(3).setPreferredWidth(15);
            Grilla.getColumnModel().getColumn(4).setResizable(false);
            Grilla.getColumnModel().getColumn(4).setPreferredWidth(50);
            Grilla.getColumnModel().getColumn(5).setResizable(false);
            Grilla.getColumnModel().getColumn(5).setPreferredWidth(60);
            Grilla.getColumnModel().getColumn(6).setResizable(false);
            Grilla.getColumnModel().getColumn(6).setPreferredWidth(80);
            Grilla.getColumnModel().getColumn(7).setMinWidth(0);
            Grilla.getColumnModel().getColumn(7).setPreferredWidth(0);
            Grilla.getColumnModel().getColumn(7).setMaxWidth(0);
            Grilla.getColumnModel().getColumn(8).setMinWidth(0);
            Grilla.getColumnModel().getColumn(8).setPreferredWidth(0);
            Grilla.getColumnModel().getColumn(8).setMaxWidth(0);
            Grilla.getColumnModel().getColumn(9).setMinWidth(0);
            Grilla.getColumnModel().getColumn(9).setPreferredWidth(0);
            Grilla.getColumnModel().getColumn(9).setMaxWidth(0);
            Grilla.getColumnModel().getColumn(10).setMinWidth(0);
            Grilla.getColumnModel().getColumn(10).setPreferredWidth(0);
            Grilla.getColumnModel().getColumn(10).setMaxWidth(0);
            Grilla.getColumnModel().getColumn(11).setMinWidth(0);
            Grilla.getColumnModel().getColumn(11).setPreferredWidth(0);
            Grilla.getColumnModel().getColumn(11).setMaxWidth(0);
            Grilla.getColumnModel().getColumn(12).setMinWidth(0);
            Grilla.getColumnModel().getColumn(12).setPreferredWidth(0);
            Grilla.getColumnModel().getColumn(12).setMaxWidth(0);
            Grilla.getColumnModel().getColumn(13).setMinWidth(0);
            Grilla.getColumnModel().getColumn(13).setPreferredWidth(0);
            Grilla.getColumnModel().getColumn(13).setMaxWidth(0);
            Grilla.getColumnModel().getColumn(14).setResizable(false);
            Grilla.getColumnModel().getColumn(14).setPreferredWidth(50);
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

        jLabel7.setText("Despacho");

        jLabel15.setText("Comentarios");

        txNombre.setEditable(false);
        txNombre.setEnabled(false);

        txComentario.setEditable(false);
        txComentario.setEnabled(false);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Directo" }));

        jLabel17.setText("Gastos");

        txVendedor.setEditable(false);

        OCLuvalyEcona.setText("OC Luvaly/ECONA");
        OCLuvalyEcona.setEnabled(false);
        OCLuvalyEcona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OCLuvalyEconaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel15)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(OCLuvalyEcona))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txRut, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txDv, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btIr, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(txRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btIr)
                        .addComponent(txDv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btContacto, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txComentario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jCheckBox1))
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(OCLuvalyEcona)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Nro Orden");

        btOtrasOrdenes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos22/Go.png"))); // NOI18N
        btOtrasOrdenes.setBorderPainted(false);
        btOtrasOrdenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOtrasOrdenesActionPerformed(evt);
            }
        });

        btGenLuvaly.setText("Generar OCC Luvaly");
        btGenLuvaly.setToolTipText("");
        btGenLuvaly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGenLuvalyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txNroOc, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btOtrasOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btGenLuvaly))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btOtrasOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txNroOc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btGenLuvaly, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        jLabel8.setText("Imp. específico");

        txImpEspecifico.setEditable(false);
        txImpEspecifico.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txImpEspecifico.setText("0");

        lbExento.setEditable(false);
        lbExento.setText("0");
        lbExento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbExentoActionPerformed(evt);
            }
        });

        lbNeto.setEditable(false);
        lbNeto.setText("0");

        lbIVA.setEditable(false);
        lbIVA.setText("0");

        lbImp.setEditable(false);
        lbImp.setText("0");

        lbTotal.setEditable(false);
        lbTotal.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txImpEspecifico, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(txIva, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txNeto, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txExento, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbExento)
                    .addComponent(lbNeto)
                    .addComponent(lbIVA, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(lbImp)
                    .addComponent(lbTotal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txExento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbExento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txNeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txImpEspecifico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

        jLabel6.setText("Despacho");

        dtDespacho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dtDespachoActionPerformed(evt);
            }
        });

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
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dtEmision, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                    .addComponent(dtDespacho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dtEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(dtDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        btAsociados.setForeground(new java.awt.Color(0, 0, 255));
        btAsociados.setText("Productos Asociados");
        btAsociados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAsociadosActionPerformed(evt);
            }
        });

        txSkuTemporal.setEnabled(false);

        txDescTemporal.setEnabled(false);

        txUMTemporal.setEnabled(false);

        txCodBarTemporal.setEnabled(false);

        txCantidadTemporal.setEnabled(false);

        txUnitarioTemporal.setEnabled(false);

        txTotlineaTemporal.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btAgregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btEliminar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btAsociados)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txDescTemporal, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txCodBarTemporal)
                                                .addComponent(txSkuTemporal, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txCantidadTemporal, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txUMTemporal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txUnitarioTemporal, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txTotlineaTemporal, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btEliminar)
                            .addComponent(btAgregar))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txSkuTemporal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txUnitarioTemporal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txCodBarTemporal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txTotlineaTemporal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txCantidadTemporal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txUMTemporal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txDescTemporal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btAsociados)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(104, 104, 104))
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
        GrillaBlog.setToolTipText("");
        GrillaBlog.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
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

        btMostrarHistorial.setText("Mostrar Historial Detallado");
        btMostrarHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMostrarHistorialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btAgregarBlog, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btMostrarHistorial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txSku)))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txSku)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btAgregarBlog)
                            .addComponent(btMostrarHistorial))
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11))
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
            btIr.setEnabled(false);
            txRut.setEditable(false);
            txRut.setEnabled(false);
            btCancelar.setEnabled(false);
            btGuardar.setEnabled(false);
            btNuevo.setEnabled(true);
            btEditar.setEnabled(false);
            btAbrir.setEnabled(true);
//            btImprimir.setEnabled(false);
            btLiberar.setEnabled(false);
            Tipo = -1;
            jCheckBox1.setEnabled(false);
            btAgregarBlog.setEnabled(false);
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
            jCheckBox1.setEnabled(true);
            btAgregarBlog.setEnabled(true);
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
            jCheckBox1.setEnabled(true);
            
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
            txRut.setEnabled(false);
            txDv.setEnabled(false);
            txDv.setEditable(false);
            jCheckBox1.setEnabled(false);
            Tipo = 3;
        }
//        lbExento.setVisible(false);
//        lbIVA.setVisible(false);
//        lbImp.setVisible(false);
//        lbNeto.setVisible(false);
//        lbTotal.setVisible(false);
    }

//--------------------------------------------------------------------------------
// SUMADOR
//--------------------------------------------------------------------------------
private void Sumador(){    
    
    double Neto=0;
    double Exento=0;
    double Iva=0;
    double Total=0;
    String Unitario,Cantidad;
    
    for(int i=0; i< Grilla.getRowCount(); i++){
        Unitario = Grilla.getModel().getValueAt(i,GetCol("UniReal")).toString();
        Cantidad = Grilla.getModel().getValueAt(i,GetCol("CantReal")).toString();
        
        Unitario = Unitario.replace(fmMain.GetMiles(), "");
        Cantidad = Cantidad.replace(fmMain.GetMiles(), "");
        Neto =  (Double.parseDouble(Unitario) * Double.parseDouble(Cantidad)) + Neto;
        System.out.println(Neto);
    }
    Neto = Math.round(Neto);
    
    Iva = Math.round((Neto * Double.parseDouble("1.19"))- Neto);
    Total = Neto + Iva;
    
    
    txNeto.setText(fmMain.FormatoTotal(Neto));
    txExento.setText(fmMain.FormatoTotal(Exento));
    txIva.setText(fmMain.FormatoTotal(Iva));
    txTotal.setText(fmMain.FormatoTotal(Total));
    
    
}

private void Sumador_Porcentaje(){    
    
    double Neto=0;
    double Exento=0;
    double Iva=0;
    double Total=0;
    String Unitario,Cantidad;
    
    for(int i=0; i< Grilla.getRowCount(); i++){
        Unitario = Grilla.getModel().getValueAt(i,GetCol("UniReal")).toString();
        Cantidad = Grilla.getModel().getValueAt(i,GetCol("CantReal")).toString();
        Unitario = Unitario.replace(fmMain.GetMiles(), "");
        Cantidad = Cantidad.replace(fmMain.GetMiles(), "");
        Neto =  (((Double.parseDouble(Unitario) * Double.parseDouble(Cantidad)))/Double.parseDouble(Grilla.getModel().getValueAt(i, 10).toString())) + Neto;
//        Neto =  ((Double.parseDouble(Unitario)+((Double.parseDouble(Unitario)*porcentaje)/100)) * Double.parseDouble(Cantidad)) + Neto;
    }
    System.out.println("Neto; "+Neto);
    Neto = Math.round(Neto);
    
    Iva = Math.round((Neto * Double.parseDouble("1.19"))- Neto);
    Total = Neto + Iva;
    System.out.println(Neto);
    
    lbNeto.setText(fmMain.FormatoTotal(Neto));
    lbExento.setText(fmMain.FormatoTotal(Exento));
    lbIVA.setText(fmMain.FormatoTotal(Iva));
    lbTotal.setText(fmMain.FormatoTotal(Total));
    lbImp.setText("0");

}

private String FormatoVisual (String Texto){
        DecimalFormat Formato = new DecimalFormat("###,###.##");
        return Formato.format(Texto);
    }
private String FormatoGuardar(String Texto){
        return Texto.replace(".", "");
}
//--------------------------------------------------------------------------------
// ABRIR ORDEN DE COMPRA PROVEEDOR
//--------------------------------------------------------------------------------
public void AbrirOCP(String NumeroNPP){
    ExeSql SqlEcona = new ExeSql();
    ExeSqlLuvaly SqlLuv = new ExeSqlLuvaly();
        
    ResultSet RsEcona = null;
    ResultSet RsLuv = null;
    ResultSet Rscodbar = null;
    
    DefaultTableModel TableModel = (DefaultTableModel) Grilla.getModel();
    
    while(TableModel.getRowCount()>0)
        TableModel.removeRow(0);
    
    try {
        RsEcona = SqlEcona.Select("SELECT * from ctacteprv cp \n"+
                                  "WHERE tipdocto='NPP' and nrodocto=" + NumeroNPP + " \n"+
                                  "OR tipdocto='NPP' and occhilemat =" + NumeroNPP);
        
        RsEcona.next();
  
        txRut.setText(RsEcona.getString("Rut"));
        btIr.setEnabled(true);
        btIr.doClick();

        
        txNroOc.setText(RsEcona.getString("nrodocto"));
        
        NroOp = RsEcona.getString("nrodocto");
        txComentario.setText(RsEcona.getString("comentarios").trim());
        
        dtEmision.setDate(RsEcona.getDate("femision"));
        dtDespacho.setDate(RsEcona.getDate("fdespacho"));
        
        txNeto.setText(fmMain.FormatoTotal(RsEcona.getDouble("totalafecto")));
        txExento.setText(fmMain.FormatoTotal(RsEcona.getDouble("totalexento")));
        txIva.setText(fmMain.FormatoTotal(RsEcona.getDouble("totaliva")));
        txImpEspecifico.setText(fmMain.FormatoTotal(RsEcona.getDouble("totalotroimp")));
        txTotal.setText(fmMain.FormatoTotal(RsEcona.getDouble("totaldocto")));
        txSku.setText(RsEcona.getString("nrodocto"));     
        txSku.setVisible(false);

        jComboBox1.setSelectedIndex(RsEcona.getInt("tipodespacho"));
       
        PosCuotas = RsEcona.getInt("cuotas") - 1;
        
        
        
        // ESTADOS
        
        if(RsEcona.getInt("estado")==-1){
            btImprimir.setEnabled(true);
            txEstado.setText("Por Emitir");
            txEstado.setBackground(Color.yellow);
            btActualizar.setEnabled(true);
            genera = -1;
        }else if(RsEcona.getInt("estado")==1){
            btImprimir.setEnabled(true);
            txEstado.setText("Emitida");
            txEstado.setBackground(Color.white);
            btActualizar.setEnabled(false);
            btCerrar.setEnabled(true);
            genera = 1;
           
        }else if(RsEcona.getInt("estado")==2){
            btImprimir.setEnabled(true);
            txEstado.setText("Recibida");
            txEstado.setBackground(Color.white);
            btActualizar.setEnabled(false);
            btCerrar.setEnabled(true);
            genera = 2;
           
        }else if(RsEcona.getInt("estado")==4){
            btImprimir.setEnabled(true);
            txEstado.setText("Cerrada");
            txEstado.setBackground(Color.green);
            btActualizar.setEnabled(true);
            genera = 4;
        }else if(RsEcona.getInt("estado")==-2){
            btImprimir.setEnabled(false);
            txEstado.setText("Rechazado");
            txEstado.setBackground(Color.red);
            btActualizar.setEnabled(true);
            genera = -2;
        }
        else if(RsEcona.getInt("estado")==-3){
            btImprimir.setEnabled(true);
            txEstado.setText("Autorizado");
            txEstado.setBackground(Color.green);
            btActualizar.setEnabled(true);
            genera = -3;
        }
        
        
        OCCL = RsEcona.getBoolean("generaocc");

        
         RsEcona = SqlEcona.Select("select pd.sku, pd.cantidad, pd.valorunitario,pd.totallinea, pd.rut, pd.recibido\n" +
                                  "from ctacteprvdet pd\n" +
                                  "left join producto p on pd.sku=p.sku\n" +
                                  "left join par_unidad pu on p.unidad =pu.codigo \n" +
                                  "where pd.nrodocto=" + txNroOc.getText() + " and pd.tipdocto='NPP'");
        while(RsEcona.next()){

                RsLuv = SqlLuv.Select("SELECT p.nombre, u.um FROM producto p \n"+
                                       "LEFT JOIN par_unidad u ON u.codigo=p.unidad\n "+
                                       "WHERE p.sku = '"+RsEcona.getString("sku").trim()+"'");
                RsLuv.next();
                
                Rscodbar = SqlLuv.Select("SELECT case when cb.codbar is null then '0' else cb.codbar end as codbar \n"+
                                       "FROM codbar cb \n"+
                                       "WHERE cb.sku = '"+RsEcona.getString("sku").trim()+"'"+
                                       "and cb.rutprv = "+RsEcona.getString("rut").trim()+"");
                String codbar_ = "";
                
                if(!Rscodbar.next()) codbar_ = "";
                else codbar_ = Rscodbar.getString("codbar");
                
                System.out.println(codbar_);
                
                TableModel.addRow(new Object[]{ RsEcona.getString("sku").trim(), 
                                                codbar_, 
                                                RsLuv.getString("nombre"), 
                                                RsLuv.getString("um"),
                                                fmMain.FormatoNumero(RsEcona.getDouble("cantidad")),
                                                fmMain.FormatoNumero(RsEcona.getDouble("valorunitario")),
                                                fmMain.FormatoNumero(RsEcona.getDouble("totallinea")),
                                                RsEcona.getDouble("valorunitario"),
                                                RsEcona.getDouble("cantidad"),0,0,0,0,
                                                RsEcona.getDouble("cantidad"),
                                               fmMain.FormatoNumero(RsEcona.getDouble("recibido"))
                });
            }
        CargaBlog();
        SetTipo(2);
        
        
        
        fmMain.pnPestanas.setTitleAt(fmMain.pnPestanas.getSelectedIndex(), "Nota de Pedido  " + NumeroNPP);
        fmMain.pnPestanas.repaint();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Nota de Pedido no encontrada");
        Limpia();
        Habilita(false);
    }
    finally{
        SqlEcona.Close();
        SqlLuv.Close();
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
        txTotal.setEditable(Estado);
        btAgregar.setEnabled(Estado);
        btEliminar.setEnabled(Estado);
        btAsociados.setEnabled(Estado);
        if(Estado==false) btAsociados.setForeground(Color.GRAY);
        else    btAsociados.setForeground(Color.BLUE);
        txComentario.setEditable(Estado);
//        txNroOc.setEditable(Estado);
        
        dtEmision.setEditable(Estado);
        dtDespacho.setEditable(Estado);

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
        txImpEspecifico.setText("0");
        txTotal.setText("");
        txComentario.setText("");
        txVendedor.setText("");
        txNroOc.setText("");
        
        dtEmision.setDate(null);
        dtDespacho.setDate(null);
        txEstado.setText("");
       
        jCheckBox1.setSelected(false);
        
        //LIMPIA LAS GRILLAS
        DefaultTableModel   dfTm = (DefaultTableModel) Grilla.getModel();
        while(dfTm.getRowCount()>0)
        dfTm.removeRow(0);
        DefaultTableModel   dfTmh = (DefaultTableModel) GrillaBlog.getModel();
        while(dfTmh.getRowCount()>0)
        dfTmh.removeRow(0);
        


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
        jComboBox1.setEnabled(Estado);
        txNroOc.setEnabled(Estado);
        dtEmision.setEnabled(Estado);
        dtDespacho.setEnabled(Estado);
        btAgregar.setEnabled(Estado);
        btEliminar.setEnabled(Estado);
        btAsociados.setEnabled(Estado);
        btIr.setEnabled(Estado);
      
        txComentario.setEnabled(Estado);
        txNroOc.setEnabled(Estado);
        txEstado.setEnabled(Estado);
        
        
        txNeto.setEnabled(Estado);
        txExento.setEnabled(Estado);
        txIva.setEnabled(Estado);
        txImpEspecifico.setEnabled(Estado);
        txTotal.setEnabled(Estado);
        btContacto.setEnabled(Estado);
        btOtrasOrdenes.setEnabled(Estado);
        btMostrarHistorial.setVisible(Estado);
        
        if(Estado==false) btAsociados.setForeground(Color.GRAY);
        else    btAsociados.setForeground(Color.BLUE);
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
              if(Sql.GetRowCount()==0){
                    fmMain.Mensaje("No Existen Registros");
                  return false;
                }
      
            Rs.next();
            txRut.setText(Rs.getString("Rut"));
            txDv.setText(Rs.getString("dv"));
            txNombre.setText(Rs.getString("nombre").trim());
            RutMaster = Rs.getString("Rut");
            
             
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
            dtDespacho.setDate(cal.getTime());
            
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
//------------------------------------------------------------------------------
// BOTON  CANCELAR
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
        
        System.out.println("EL RUT btIr ES : "+txRut.getText());
        
        if (!txRut.getText().isEmpty()) {
            Carga = CargaProveedor(txRut.getText());

        } else {
            jdBuscarCliPrv BPC = new jdBuscarCliPrv(null, true);
            BPC.setLocationRelativeTo(null);
            BPC.setTitle("Buscar Proveedor");
            BPC.SetTipo(1);
            BPC.setVisible(true);
            Carga = CargaProveedor(BPC.GetRut());
            
        }
        
            if (!Carga){
                txRut.requestFocus();
            } 
            
    }//GEN-LAST:event_btIrActionPerformed
//-----------------------------------------------------------------------------
//  BOTON DETALLE
//-----------------------------------------------------------------------------
    private void btContactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btContactoActionPerformed
        String Vendedor;
        try {
            jdContactos Con = new jdContactos(null, true);
            Vendedor = Con.Show(RutMaster, "PRV",true).trim();
            if(!Vendedor.equals(""))
                txVendedor.setText(Con.GetUsuario());
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
        int cantidad = Grilla.getRowCount();
        
        if(cantidad<=1000){
            
            DefaultTableModel tbModel = (DefaultTableModel) Grilla.getModel();

            jdOCCAgregaProducto AgregaProducto = new jdOCCAgregaProducto(null, true);
            AgregaProducto.setLocationRelativeTo(null);
            AgregaProducto.setTitle("Agregar Producto");
            AgregaProducto.Activa_Prv(txRut.getText().trim());
            AgregaProducto.setVisible(true);
            String sku_rel, nombre_rel;

            if(!ExisteEnGrilla(AgregaProducto.GetSku())){
              
                tbModel.addRow(AgregaProducto.GetFilaOCP());
                Sumador();

            }else{
            
                JOptionPane.showMessageDialog(null, "El producto ya esta en el listado");
            }

        }else{
        
            JOptionPane.showMessageDialog(null, "No se puede ingresar más de 15 productos");
        
        }
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
        AbrirOCP(JOptionPane.showInputDialog("Numero de Nota Pedido:"));
    }//GEN-LAST:event_btAbrirActionPerformed
//-----------------------------------------------------------------------------
//  BOTON NUEVO
//-----------------------------------------------------------------------------
    private void btNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoActionPerformed
        
        intNivelMnu = 100;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if (intNivelMnu > intNivelUsuario ){
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado") ;
                    return;
        }
        
        if (fmMain.GetEstado(fmMain.pnPestanas.getSelectedIndex()) == 0) {
            SetTipo(-1);
            Limpia();
            txRut.setEnabled(true);
            txDv.setEnabled(true);
            txRut.setEditable(true);
            txDv.setEditable(true);
            btIr.setEnabled(true);
            txRut.requestFocus();
           
        }
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
public String getFechaDespachoAsString() {
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
return( sdf.format( (dtDespacho.getDate()).getTime() ) );
}
//-----------------------------------------------------------------------------
//  BOTON GUARDAR
//-----------------------------------------------------------------------------
    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed

       
        GuardarLuvalyOCP();
        

     //Actuaiza Orden de Compra
    }//GEN-LAST:event_btGuardarActionPerformed

    public void GuardarLuvalyOCP() {
    ExeSql Sql = new ExeSql();
    ExeSql Sql2 = new ExeSql();
    ExeSql Sql3 = new ExeSql();
    ExeSql Sql4 = new ExeSql();
    ExeSql Sql5 = new ExeSql();
    ExeSql Sql6 = new ExeSql();
    int existe = 0;
    int docfap = 0;
    ResultSet Rs, Rs2;
    String Query = "";
    String Query3 = "";
    String Codigos="";
    String NewCorrelativo;
    
    if(JOptionPane.showConfirmDialog(null, "Guardar los cambios realizados.","Guardar",JOptionPane.YES_OPTION)!= JOptionPane.YES_OPTION){
        return;
    }
    
            
    
    //Nueva Orden de Compra
     if(Tipo==1){
         try {

            Sql.ExeSql("update par_correlativo set numero=numero + 1 where tipo='NPP'");
            NewCorrelativo = Sql.SelectUnico("select numero from par_correlativo where tipo='NPP'");
             
             
             
             Sql.ExeSql("  INSERT INTO ctacteprv\n" +
                        "  (rut,tipdocto,nrodocto,femision,totalexento,totalafecto,totaliva,\n" +
                        "  totalotroimp,totaldocto,fdespacho,contacto,comentarios, tipodespacho, generaocc) \n" +
                        "  VALUES (\n" +
                           txRut.getText() + ",\n" +
                        "  'NPP',\n" +
                           NewCorrelativo              +  ",\n'" +
                           getFechaEmisionAsString()   + "',\n" +
                           fmMain.SetGuardar(txExento.getText())          + ",\n" +
                           fmMain.SetGuardar(txNeto.getText())             + ",\n" +
                           fmMain.SetGuardar(txIva.getText())             + ",\n" +
                           fmMain.SetGuardar(txImpEspecifico.getText())   + ",\n" +
                           fmMain.SetGuardar(txTotal.getText())           + ",'\n" +
                           getFechaDespachoAsString()  + "',\n" +
                           IdVendedor     + ",\n'" +
                           txComentario.getText()     + "',\n" +
                           jComboBox1.getSelectedIndex() + ",'" +
                           OCCL+"')" );
             

// Guarda Productos
                for(int i=0; i< Grilla.getRowCount(); i++){
                    Query = "INSERT INTO ctacteprvdet\n" +
                            "(rut,tipdocto,nrodocto,sku,cantidad, valorunitario,totallinea) \n" +
                            "VALUES (\n" +
                            txRut.getText() + ",\n" +
                            "'NPP',\n" +
                            NewCorrelativo +  ",\n'" +
                            Grilla.getModel().getValueAt(i,GetCol("Sku")).toString().trim() + "'," +
                            fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("CantReal")).toString()) + "," +
                            fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("UniReal")).toString()) + "," +
                            fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("Total")).toString()) + ")";
                    Sql.ExeSql(Query);
                }
                
                 Sql.Commit();
// FIN
            
                    // Se guarda OCP
                    try{
         
                        Sql6.ExeSql("update ctacteprv \n" +
                                      " set estado=-1\n" +
                                      " where tipdocto='NPP'\n" +
                                      " and rut= "+ txRut.getText() +
                                      " and nrodocto=" + NewCorrelativo );	
                        Sql6.Commit();

                    } catch (SQLException | HeadlessException e) {

                        JOptionPane.showMessageDialog(null, e);
                        Sql6.Rollback();

                   }finally{
                    
                        Sql6.Close();
                   }
             
                
            
             AbrirOCP(NewCorrelativo);
//             txNroOc.setText(NewCorrelativo);
//             SetTipo(2);
             JOptionPane.showMessageDialog(null, "Orden Guardada. Numero:" + NewCorrelativo);
         } catch (SQLException | HeadlessException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
             System.out.println(e.getMessage());
             Sql.Rollback();
         }
         finally{
             Sql.Close();
         }
             
     }
     /**  
      * Actualiza OCP
      */
     else{
         
         try{
         
             Sql.ExeSql("UPDATE ctacteprv  \n" +
                    "SET \n" +
                    "femision = '"+ getFechaEmisionAsString()  +"',\n" +
                    "totalexento = "+ fmMain.SetGuardar(txExento.getText()) +",\n" +
                    "totalafecto = "+fmMain.SetGuardar(txNeto.getText())+",\n" +
                    "totaliva = "+fmMain.SetGuardar(txIva.getText())+",\n" +
                    "totalotroimp = "+fmMain.SetGuardar(txImpEspecifico.getText())+",\n" +
                    "totaldocto = "+fmMain.SetGuardar(txTotal.getText())+",\n" +
                    "fdespacho = '"+getFechaDespachoAsString()+"',\n" +
                    "contacto = "+ IdVendedor +",\n" +
                    "comentarios = '"+txComentario.getText()+"',\n" +
                    "tipodespacho = " + jComboBox1.getSelectedIndex() +" \n" +
                    "WHERE rut = "+txRut.getText()+"\n" +
                    "AND  tipdocto = 'NPP'\n" +
                    "AND  nrodocto = " + txNroOc.getText());
         
         
         Rs2 = Sql2.Select("SELECT nrodocto FROM ctacteprv WHERE nrodocorigen="+ txNroOc.getText()+" LIMIT 1");
         Rs2.next();
         
         if (Sql2.GetRowCount() > 0){
         
             docfap = Rs2.getInt("nrodocto");
             existe = 1;
         
         }else{
         
             existe = 0;
         }
                    
         
         //ACTUALIZA EL DETALLE
        for(int i=0; i< Grilla.getRowCount(); i++){
            
             // 1. Verifica si existe
            Rs = Sql.Select("select count(*) as Existe from ctacteprvdet \n" +
                                    "  where  rut= "+ txRut.getText() +
                                    "  and nrodocto=" + txNroOc.getText().trim() + "\n" +
                                    "  and tipdocto='NPP'\n" + 
                                    "  and sku='"+ Grilla.getModel().getValueAt(i,GetCol("Sku")).toString().trim() +"'");
            Rs.next();
         // 2. Si Existe UPDATE
            if(Rs.getInt("Existe")>0){
                        Query =  "  update ctacteprvdet set \n" +
                                     "  cantidad = " + fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("CantReal")).toString()) + ",\n" +
                                     "  valorunitario="+ fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("UniReal")).toString()) +",\n" +
                                     "  nuevo_valor="+ fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("UniReal")).toString()) +",\n" +
                                     "  totallinea ="+ fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("Total")).toString()) +"\n" +
                                     "  where  rut= "+ txRut.getText() +
                                     "  and nrodocto=" + txNroOc.getText().trim() + "\n" +
                                     "  and tipdocto='NPP'\n" + 
                                     "  and sku='"+ Grilla.getModel().getValueAt(i,GetCol("Sku")).toString() +"'";
                        
                        Query3 = "UPDATE ctacteprvdet set \n" +
                                 "nuevo_valor="+ fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("UniReal")).toString()) +"\n" +
                                 "WHERE rut= "+ txRut.getText() +
                                 "AND nrodocto=" + docfap + "\n" +
                                 "AND tipdocto IN ('FAP', 'GDP') \n" + 
                                 "AND sku='"+ Grilla.getModel().getValueAt(i,GetCol("Sku")).toString() +"'";  
                                
            
         // 3. Si no existe INSERT
        }else{
                        Query = "INSERT INTO ctacteprvdet\n" +
                            "(rut,tipdocto,nrodocto,sku,cantidad, valorunitario,totallinea) \n" +
                            "VALUES (\n" +
                            txRut.getText() + ",\n" +
                            "'NPP',\n" +
                            txNroOc.getText()               +  ",\n'" +
                            Grilla.getModel().getValueAt(i,GetCol("Sku")).toString().trim() + "'," +
                            fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("CantReal")).toString()) + "," +
                            fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("UniReal")).toString()) + "," +
                            fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("Total")).toString()) + ")";
                        
        

        }
        Sql5.ExeSql(Query); 
        Sql5.Commit();
        
        
        if (existe == 1){
            
            Sql3.ExeSql(Query3); 
            Sql3.Commit();
        
        }
        
        // 4. Agrega sku al LISTADO
            Codigos = Codigos + "'" + Grilla.getModel().getValueAt(i,GetCol("Sku")).toString().trim() + "',";
            
            double nueva_cant = Double.parseDouble(Grilla.getModel().getValueAt(i,GetCol("CantReal")).toString());
            double ant_cant = Double.parseDouble(Grilla.getModel().getValueAt(i,13).toString());
            
            
            Sql4.ExeSql("UPDATE inventario_sala SET \n" +
                       "ocp = ocp - " + ant_cant + " + "+nueva_cant +" \n "+
                       "WHERE sku='"+ Grilla.getModel().getValueAt(i,GetCol("Sku")).toString() +"'");  
            
            Sql4.Commit();
            
        } 
        

// FIN
        
        
         // 5. Elimina productos que ya no pertenecen
             Query =  " DELETE from ctacteprvdet " + 
                      " WHERE  rut= "+ txRut.getText() +
                      " AND nrodocto=" + txNroOc.getText().trim() + "\n" +
                      " AND tipdocto='NPP'\n" + 
                      " AND sku not in (" + Codigos.substring(0, Codigos.length()-1) + ")";
             Sql.ExeSql(Query);        

            

        Sql.Commit();
//        SetTipo(2);
             AbrirOCP(txNroOc.getText());
             JOptionPane.showMessageDialog(null, "Orden de Compra Actualizada"); 
        
         } catch (SQLException | HeadlessException e) {
             JOptionPane.showMessageDialog(null, e);
             Sql.Rollback();
             Sql3.Rollback();
             Sql4.Rollback();
             Sql5.Rollback();

         }
         finally{
             Sql.Close();
             Sql3.Close();
             Sql4.Close();
             Sql5.Close();
         }
         
     }
    }
    

    
    
    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        
        intNivelMnu = 100;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if (intNivelMnu > intNivelUsuario ){
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado") ;
            return;
        }
        
        
        SetTipo(3);
        btIr.setEnabled(false);
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
        
//        jdQuienFirma qf = new jdQuienFirma(null, true);
//        qf.setLocationRelativeTo(null);
//        qf.setTitle("Firma Comprador");
//        qf.CargaComboPrv();
//        qf.setVisible(true);
//        firmacompra=qf.getNombre();
        
        
//        URL in = this.getClass().getResource("/Reportes/repNPP.jasper");
//        JasperReport repo = null;
//        HashMap<String, Object> parametros = new HashMap<>();
//        List listaProd = new ArrayList();
//        String Atento = "Ventas";
        
        
//        Atento = txVendedor.getText().trim();
//        
//        try {
//            repo = (JasperReport)JRLoader.loadObject(in);
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null,ex);
//        }
         
        ResultSet Rs, Rs4;
        ExeSql Sql = new ExeSql();
        ExeSqlLuvaly luv = new ExeSqlLuvaly();
        ResultSet producto = null;
        String codprv="";
        //--------------
//        try {
            
//            Sql.ExeSql("update ctacteprv \n" +
//                            "set estado=0\n" +
//                            "where tipdocto='NPP'\n" +
//                            "and nrodocto=" + txNroOc.getText() + " and estado=-3");
//            
//            JOptionPane.showMessageDialog(null, System.getProperty("user.dir")+"\\src\\Reportes\\ClaudiaTorres.jpg");
//            Rs = Sql.Select("select pd.sku,p.nombre, pu.um, pd.cantidad, pd.valorunitario,pd.totallinea\n" +
//                            "from ctacteprvdet pd\n" +
//                            "left join producto   p  on pd.sku=p.sku\n" +
//                            "left join par_unidad pu on p.unidad =pu.codigo\n" +
//                            "where pd.nrodocto="+ txNroOc.getText() + " and tipdocto='NPP'");
//            while(Rs.next()){
//                Rs4 = Sql.Select("Select codbar from codbar where rutprv="+txRut.getText().trim()+"  and sku='"+Rs.getString("sku").trim()+"'" );
//                if (Rs4.next())
//                    {
//                    codprv=Rs4.getString("codbar");
//                    }
//                else
//                {
//                    codprv="- - -";
//                }
//                producto =    luv.Select("select p.nombre, u.um from producto p \n"
//                                        + "LEFT JOIN par_unidad u ON u.codigo=p.unidad\n "
//                                        + "where p.sku = '"+Rs.getString("sku").trim()+"'");
//                producto.next();
//                ListarProductos lista = new ListarProductos(
//                                            Rs.getString("sku").trim(), 
//                                            producto.getString("nombre").trim(), 
//                                            producto.getString("um").trim(),
//                                            fmMain.FormatoTotal(Rs.getDouble("cantidad")),
//                                            fmMain.FormatoNumero(Rs.getDouble("valorunitario")),
//                                            fmMain.FormatoTotal(Rs.getDouble("totallinea")),
//                                            codprv);
//                listaProd.add(lista);
//            }
//            
                    
            
//            Rs = Sql.Select( "select p.rut || '-' || p.dv as ElRut,p.nombre as nombreprv,p.direccion,p.fono,cp.* \n" +
//                             "from ctacteprv cp \n" +
//                             "left join proveedor p on p.rut=cp.rut \n" +
//                             "where cp.tipdocto='NPP' and cp.nrodocto=" + txNroOc.getText()); 
//            Rs.next();
//            parametros.put("NroOc",txNroOc.getText() );
//            parametros.put("Nombre", Rs.getString("nombreprv"));
//            parametros.put("Rut", Rs.getString("ElRut"));
//            parametros.put("Direccion", Rs.getString("Direccion"));
//            parametros.put("Fecha", dtEmision.getDate());
//            parametros.put("Atencion",  Atento);
//            parametros.put("Fono", Rs.getString("fono"));
//            parametros.put("FechaDespacho", dtDespacho.getDate());
//            parametros.put("ImagenComprador", this.getClass().getResourceAsStream("/Reportes/"+firmacompra+".png"));
//            parametros.put("ImagenLogo", this.getClass().getResourceAsStream("/Reportes/Logo.jpg"));
//            parametros.put("Cuotas", "3");
//            parametros.put("Dias", Rs.getString("dias"));
//            parametros.put("Comentarios", txComentario.getText());
//            parametros.put("TotalNeto", txNeto.getText());
//            parametros.put("TotalIva", txIva.getText());
//            parametros.put("TotalDocto",txTotal.getText());
//
//            JasperPrint informe =  JasperFillManager.fillReport(repo, parametros, new JRBeanCollectionDataSource(listaProd));
//            JasperViewer.viewReport(informe,false);
//            Sql.Commit();
            
          
            System.out.println("LA OCCL ES : "+OCCL);    
            
            
            if (OCCL){
            
                btGenLuvaly.doClick();
            
            }else {
               
                
               System.out.println("No se generó NPC !!!!!!");     
                  
            }
            
            
            
            if (genera == -1) {
            
            // Se Emite NCP
            
        
                    ExeSql Sql6 = new ExeSql();
                    ExeSql Sql7 = new ExeSql();
                
                
        
                    try{
         
                        Sql6.ExeSql("update ctacteprv \n" +
                                      " set estado= 1\n" +
                                      " where tipdocto='NPP'\n" +
                                      " and rut= "+ txRut.getText() +
                                      " and nrodocto=" + txNroOc.getText().trim() );	
                        Sql6.Commit();
                        
                        
                        
                        for (int i = 0; i < Grilla.getRowCount(); i++) {
                                        
                                Sql7.ExeSql("UPDATE inventario_sala SET \n" +
                                            "ocp = ocp + "+fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("Cantidad")).toString().trim())+" \n" +
                                            "where sku ='"+Grilla.getModel().getValueAt(i, GetCol("Sku")).toString().trim()+"'");	
                                Sql7.Commit();
                                        
                        }
                        

                    } catch (SQLException | HeadlessException e) {

                        JOptionPane.showMessageDialog(null, e);
                        Sql6.Rollback();
                        Sql7.Rollback();

                   }finally{
                    
                        Sql6.Close();
                        Sql7.Close();
                   }
            
            
            }
            
            btActualizar.doClick();
            
            
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null,e);
//            
//        }
//        finally{
//            Sql.Close();
//        }
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
        double cant,prec;    
        try {
            
            if(evt.getClickCount()==2 && Tipo==1 || Tipo==3){
            
                jdIngresaNumero jdNumero = new jdIngresaNumero(null, true);
                jdNumero.setLocationRelativeTo(null);
                prec = Double.parseDouble(fmMain.SetGuardar(Grilla.getValueAt(Grilla.getSelectedRow(), GetCol("Unitario")).toString()));
                cant = Double.parseDouble(fmMain.SetGuardar(Grilla.getValueAt(Grilla.getSelectedRow(), GetCol("Cantidad")).toString()));
                jdNumero.SetNumero(cant);
                jdNumero.SetPrecio(prec);
           
                jdNumero.setVisible(true);        
          
                Grilla.setValueAt(fmMain.FormatoNumero(jdNumero.GetNumero()), Grilla.getSelectedRow(),GetCol("Cantidad"));
                Grilla.setValueAt(fmMain.FormatoNumero(jdNumero.GetNumero()), Grilla.getSelectedRow(),GetCol("CantReal"));
                Grilla.setValueAt(fmMain.FormatoNumero(jdNumero.GetPrecio()), Grilla.getSelectedRow(), GetCol("Unitario"));
                Grilla.setValueAt(fmMain.FormatoNumero(jdNumero.GetPrecio()), Grilla.getSelectedRow(), GetCol("UniReal"));
                double Total = jdNumero.GetNumero() * jdNumero.GetPrecio();
                Grilla.setValueAt(fmMain.FormatoNumero(Total), Grilla.getSelectedRow(), GetCol("Total"));
                Sumador();
            
            
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

    private void btAsociadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAsociadosActionPerformed
        String Codigos="";
        DefaultTableModel tbModel = (DefaultTableModel) Grilla.getModel();
        
        for(int i=0; i< Grilla.getRowCount();i++){
            Codigos = "'" + Grilla.getValueAt(i,0).toString().trim() + "',";
        }
        if(Codigos.length()>0){
            Codigos = Codigos.substring(0, Codigos.length()-1);
        }
        else{
            Codigos = "'09090'";
        }
        
        jdProductosAsociadosPrv Prod = new jdProductosAsociadosPrv(null, true);
        Prod.setLocationRelativeTo(null);
        Prod.setTitle("Productos Asociados al Proveedor");
        Prod.Show(RutMaster, Codigos);
        if(!Prod.GetCodigoSelect().isEmpty()){
            jdOCCAgregaProducto AgregaProducto = new jdOCCAgregaProducto(null,true);
            AgregaProducto.setLocationRelativeTo(null);
            AgregaProducto.setTitle("Agragar Producto");
            AgregaProducto.Activa_Prv(txRut.getText().trim());
            AgregaProducto.SetProducto(Prod.GetCodigoSelect(),
                                       "",
                                       Prod.GetCostoSelect());
            AgregaProducto.setVisible(true);
            tbModel.addRow(AgregaProducto.GetFilaOCP());
            Sumador();
        }
        
    }//GEN-LAST:event_btAsociadosActionPerformed

    private void dtDespachoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dtDespachoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dtDespachoActionPerformed

    private void btAgregarBlogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarBlogActionPerformed
        jdAgregaBlog AddBlog = new jdAgregaBlog(null, true);
        AddBlog.SetDatos(txRut.getText().trim(),"NPP",txNroOc.getText().trim());
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
        ExeSqlLuvaly luv = new ExeSqlLuvaly();
        ResultSet producto = null;
        ResultSet Rs;
        try {
            Rs = Sql.Select("select DISTINCT(i.sku) as sku, pro.nombre, u.um as umedida, ABS(i.stock+i.ocp+i.occ+i.gdc) as cantidad,\n" +
                    "round (cast(pro.valultcompra as numeric),2) as unitario,\n" +
                    "round(((cast(ABS(i.stock+i.ocp+i.occ+i.gdc) as numeric)) * round (cast(pro.valultcompra as numeric),2)),2) as totlinea, d.recibido from inventario i\n" +
                    "left join producto pro on i.sku=pro.sku\n" +
                    "left join par_unidad u on pro.unidad=u.codigo\n" +
                    "left join ctacteprvdet d on i.sku=d.sku\n" +
                    "left join proveedor p on d.rut=p.rut\n" +
                    "WHERE i.stock+i.ocp+i.occ+i.gdc<0 \n" +
                    "and p.nombre is not null\n" +
                    "and p.rut="+rutProv);
             while(Rs.next()){
                    producto =    luv.Select("select p.nombre, u.um as umedida from producto p \n"
                           + "LEFT JOIN par_unidad u ON u.codigo=p.unidad\n "
                           + "where p.sku = '"+Rs.getString("sku").trim()+"'");
                    producto.next();
                    Model.addRow(new Object[]{Rs.getString("sku").trim(), producto.getString("nombre").trim(), producto.getString("umedida").trim(), 
                                              Rs.getString("cantidad").trim(),  Rs.getString("unitario").trim(),  Rs.getString("totlinea").trim(), 
                                              Rs.getString("unitario").trim(), Rs.getString("cantidad").trim(), Rs.getString("recibido").trim()
                                 });
                   
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

    
    
    private void btMostrarHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMostrarHistorialActionPerformed
        String StrRut, NroDocto;
        jdBuscarHistorial BuscaHistorial = new jdBuscarHistorial(null,true);
        BuscaHistorial.setLocationRelativeTo(null);
        StrRut = txRut.getText().trim();
        NroDocto = txNroOc.getText().trim();
        BuscaHistorial.AbrirHistorial(StrRut, NroDocto);
        BuscaHistorial.setVisible(true);
    }//GEN-LAST:event_btMostrarHistorialActionPerformed

    private void lbExentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbExentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbExentoActionPerformed

    private void btGenLuvalyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGenLuvalyActionPerformed
          
        if (genera == 0){
        
            if (!txEstado.getText().equals("Emitida")) {
        
             fmMain.Mensaje("NPP no ha sido emitida!!");
             return;
        
            }
            
            genera = 0;
        
        }else if (genera == 1 || genera == 2 || genera == 4){
        
            
            if (txEstado.getText().equals("Emitida")) {
        
                fmMain.Mensaje("La NPC ya fue Generada!!");
                return;
        
            }
            
            genera = 0;
        
        }
        
        if (txRut.getText().equals("76231391")){      //LUVALY
        
            int CodigoVendedor = 0;                //VENDEDOR LUVALY
            NuevaOCC_Luvaly(CodigoVendedor,"99999999");
        
        }
        
    }//GEN-LAST:event_btGenLuvalyActionPerformed

    private void OCLuvalyEconaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OCLuvalyEconaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OCLuvalyEconaActionPerformed

    private void btCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCerrarActionPerformed
        
        ExeSql Sql = new ExeSql();
        ExeSql Sql2 = new ExeSql();
        ExeSql Sql3 = new ExeSql();
        ExeSql Sql4 = new ExeSql();
        ExeSql Sql5 = new ExeSql();
        ExeSql Sql6 = new ExeSql();
        ExeSql Sql7 = new ExeSql();
        
        ResultSet Rs2;
        ResultSet Rs5;
        
        try {
        
            Sql.ExeSql("update ctacteprv \n" +
                       "set estado=4 \n" +
                       "where tipdocto='NPP'\n" +
                       "and nrodocto=" + txNroOc.getText() + " and estado=1");
            
            Sql.Commit();   
            
            
            Rs2 = Sql2.Select("select sku, cantidad, recibido from ctacteprvdet \n" +
                              "where tipdocto='NPP'\n" +
                              "and nrodocto=" + txNroOc.getText());
                                
            if (Sql2.GetRowCount() > 0){
            
                
                while (Rs2.next()){
                
                    String SkuDet = Rs2.getString("sku");
                    double cantidad = Rs2.getDouble("cantidad");
                    double recibido = Rs2.getDouble("recibido");
                    
                    if (cantidad > recibido){
                    
                       
                       if (recibido == 0){
                       
                       
                            Sql3.ExeSql("delete from ctacteprvdet \n" +
                                        "where tipdocto='NPP'\n" +
                                        "and nrodocto=" + txNroOc.getText() + "\n"+
                                        "and sku='"+SkuDet.trim()+ "'");     
                        
                            Sql3.Commit();  
                       
                       
                       
                       }else if (recibido > 0) { 
                        
                        
                            Sql3.ExeSql("update ctacteprvdet \n" +
                                        "set cantidad = recibido \n" +
                                        "where tipdocto='NPP'\n" +
                                        "and nrodocto=" + txNroOc.getText() + "\n"+
                                        "and sku='"+SkuDet.trim()+ "'");     
                        
                            Sql3.Commit();  
                       
                       }
                        
                        double dif = (cantidad - recibido);
                        
                        Sql4.ExeSql("UPDATE inventario_sala SET \n"+
                                    "ocp = ocp - " + dif + " \n" +
                                    "WHERE sku = '"+SkuDet.trim()+ "'");
                        
                        Sql4.Commit();
                        
                    
                    }
                
                }
            
            }
            
            
            
            actualiza_pventa();
            
            
            //*********** ACTUALIZA Y CIERRA NOTA PEDIDO CLIENTE **********************************
            
            int Rut = 0;
            int ocp = 0;
            
            
            
            Rs5 = Sql5.Select("select ocd.rut, ocd.sku, ocd.cantidad,ocd.separado, oc.ocp_econa FROM occhdet ocd\n" +
                              "left join occh oc ON ocd.rut = oc.rut and ocd.codigo_oc = oc.codigo_oc and ocd.orden = oc.orden\n" +
                              "where ocd.rut = 99999999 and oc.ocp_econa = "+txNroOc.getText().trim());
            
            if (Sql5.GetRowCount() > 0){
            
                Rs5.next();
                ocp = Rs5.getInt("ocp_econa");
                
                Sql7.ExeSql("UPDATE occh SET \n"+
                            "estado = 0, \n"+
                            "estadodespacho = 3,  \n"+
                            "usuario_separacion = 'LIBRES' \n"+
                            "WHERE rut= 99999999 \n"+
                            "AND ocp_econa =" +ocp );

                Sql7.Commit();
            
            
            }
             
        
        } catch (SQLException ex) {
            
            Logger.getLogger(pfNPProveedor.class.getName()).log(Level.SEVERE, null, ex);
            Sql.Rollback();
            Sql3.Rollback();
            Sql4.Rollback();
            Sql6.Rollback();
            Sql7.Rollback();
        
        }finally {
        
            Sql.Close();
            Sql3.Close();
            Sql4.Close();
            Sql6.Close();
            Sql7.Close();
            
        }
        
        
    }//GEN-LAST:event_btCerrarActionPerformed

    
    
    private void actualiza_pventa (){
    
    
        ExeSql  Sql = new ExeSql();
        ExeSql  Sql2 = new ExeSql();
        
        ResultSet Rs;
        
        double valor_web = 0;
        double cost_ref = 0;
        
        double cost_vent_neto2 = 0;
            
        
        try{
            
            for (int i = 0; i < Grilla.getRowCount(); i++) {
                
                Rs = Sql.Select("SELECT sku, pventa_web FROM producto \n" +
                                "WHERE sku = '"+Grilla.getModel().getValueAt(i, GetCol("Sku")).toString().trim()+"'" );
            
                if (Sql.GetRowCount() > 0){
            
                    Rs.next();
                
                    valor_web = Rs.getDouble("pventa_web");
                
            
                }else {
                
                    valor_web = 0;
                
                }
            
                cost_ref = valor_web;
            
                cost_vent_neto2 = Math.round((cost_ref/0.65*1.19));
                
                 Sql2.ExeSql("UPDATE producto_valores SET\n" +
                             "pventa = " +cost_vent_neto2  + ",\n" +
                             "compra = 1 \n" +
                             "WHERE sku = '" + Grilla.getModel().getValueAt(i, GetCol("Sku")).toString().trim() + "'\n");
                Sql2.Commit();
            
            }
            
        } catch (Exception e) {
            Sql2.Rollback();
            JOptionPane.showMessageDialog(null, e);
        }
    
    }
    
    
    
    
    public void CargarChilemat(String NumeroOC) throws ParseException {
        
        LimpiaTemporales();

        ExeSql Sql = new ExeSql();
        
        ResultSet Rs;
        String server, user, pass, ruta_local = "";
        int puerto = 21;
        String filtro = "";
        String archivo_local = "";

        try {

            Rs = Sql.Select("SELECT count(occhilemat) as cuantos from ctacteprv where occhilemat=" + NumeroOC+"");
            Rs.next();
            if (Rs.getInt("cuantos") >= 1) {

                if (JOptionPane.showConfirmDialog(null, "La orden " + NumeroOC + " ya esta ingresada, desea abrirla?") == JOptionPane.YES_NO_OPTION) {

                    btCancelar.doClick();
                    AbrirOCP(NumeroOC);
                }

                return;
            }
            

            final String Tipo;
            final String Numero;
            int cont = 0;

            server = "";
            puerto = 0;
            user = "";
            pass = "";
            Rs = Sql.Select("SELECT * from conexiones where tipo='xml_chilem'");
            if (Rs.next()) {
                if(!fmMain.GetInternet()){
                        server = Rs.getString("serv");
                        user  = Rs.getString("usu");
                        puerto  = Rs.getInt("port");
                        pass = Rs.getString("pass");
                    }
                    else {
                        server = "186.67.157.227";
                        user  = Rs.getString("usu");
                        puerto  = 21;
                        pass = Rs.getString("pass");
                    }
            }

//                  //Verifica SO
            String sistema = System.getProperty("os.name").toLowerCase();
            File folder = new File("");
            if (sistema.contains("win")) {
                ruta_local = "c:/xml/";
                folder = new File(ruta_local.substring(0, ruta_local.length() - 1));
            } else {
                ruta_local = "/xml/";
                folder = new File(ruta_local);
            }
            if (!folder.exists()) {
                folder.mkdir();
            }

            System.out.println("Carpeta Asignada " + ruta_local);

            // Primer buscara el archivo en ruta local
            filtro = NumeroOC + ".xml";
            System.out.println("Busca en carpeta  local" + ruta_local + "/" + filtro);
            archivo_local = BuscaArchivos(new File(ruta_local), filtro);
            System.out.println("Busca archivo en FTP -->" + ruta_local + "/" + filtro);
            Ftp.busca_archivo_ftp(server, puerto, user, pass, ruta_local, archivo_local, filtro);
            System.out.println("Sale de Busca Archivo" + filtro);
        } catch (Exception e) {
            fmMain.Mensaje("Error al buscar xml: " + e);
        } finally {
            Sql.Close();
        }
        
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(ruta_local + NumeroOC + ".xml");

        try {
          
            Document OrdenCompra = (Document) builder.build(xmlFile);

            Element rootNode      = OrdenCompra.getRootElement();
            Element cp            = (Element) rootNode.getChild("CondicionPago");
            Element detalle       = (Element) rootNode.getChild("Detalle");
            Element Proveedor     = (Element) rootNode.getChild("Proveedor");
            Element Observaciones = (Element) rootNode.getChild("Observaciones");
            Element FechaIngreso  = (Element) rootNode.getChild("FechaIngreso");
                     
            List condicion_pago  = cp.getChildren();
            String plazo_pago = "";
            if (condicion_pago.size()>1)
                {
                for ( int i = 0; i < condicion_pago.size(); i++ )
                    {
                         Element plazo = (Element) condicion_pago.get(i);
                         
                         if (i>0)
                            {
                                plazo_pago =  plazo_pago+"-"+plazo.getValue();   
                            }
                         else{
                                plazo_pago =  plazo.getValue();   
                         }
                    }
                }
            else{
                Element plazo = (Element) condicion_pago.get(0);
                 plazo_pago =  plazo.getValue();
                
            }
            
            List pro = detalle.getChildren();
            for ( int p = 0; p < pro.size(); p++ )
                {
                    ExeSql Sql2 = new ExeSql();
                    ExeSqlLuvaly luv = new ExeSqlLuvaly();
                    ResultSet Rs1, producto;
                    
                    Element Producto = (Element) pro.get(p);
                    System.out.println("Codigo: "+Producto.getChildText("CodigoProducto"));
                    System.out.println("Codigo: "+Producto.getChildText("DescripcionProducto"));
                    System.out.println("Unidad: "+Producto.getChildText("UnidadProducto"));
                    try{
                        Rs1 = Sql2.Select("select count(sku) as cuantos from codbar where rutprv=96726970 and codbar='"+Producto.getChildText("CodigoProducto").trim()+"'");
                        Rs1.next();
                        if (Rs1.getInt("cuantos")<=0)
                            {
                            jdAsociaCodChilemat acc = new jdAsociaCodChilemat(null, true);
                            acc.setLocationRelativeTo(null);
                            acc.setTitle("Asociar Codigos Chilemat");
                            acc.txCodChilemat.setText(Producto.getChildText("CodigoProducto").trim());
                            acc.txDescChilemat.setText(Producto.getChildText("DescripcionProducto").trim());
                            acc.txUmChilemat.setText(Producto.getChildText("UnidadProducto").trim());
                            acc.setVisible(true);
                            }
                        Rs1 = Sql2.Select("select c.sku,c.codbar, p.nombre, u.um from codbar c left join producto p on c.sku=p.sku left"
                                + " join par_unidad u on p.unidad=u.codigo where c.codbar='"+Producto.getChildText("CodigoProducto").trim()+"'");
                        Rs1.next();
                        producto =    luv.Select("select p.nombre, u.um from producto p \n"
                           + "LEFT JOIN par_unidad u ON u.codigo=p.unidad\n "
                           + "where p.sku = '"+Rs1.getString("sku").trim()+"'");
                        producto.next();
                        txSkuTemporal.setText(Rs1.getString("sku").trim());
                        txDescTemporal.setText(producto.getString("nombre").trim());
                        txUMTemporal.setText(producto.getString("um").trim());
                        txCodBarTemporal.setText(Rs1.getString("codbar").trim());
                        txCantidadTemporal.setText(Producto.getChildText("CantidadProducto").trim().replace(",", "."));
                        txUnitarioTemporal.setText(Producto.getChildText("PrecioFinalProducto").trim().replace(",", "."));
                        txTotlineaTemporal.setText(Producto.getChildText("TotalProducto").trim().replace(",", "."));
                    }
                    catch (Exception e)
                    {
                        fmMain.Mensaje("Error al buscar codigo: "+e);
                    }
                    finally{
                        Sql2.Close();
                    }
                    
                    DefaultTableModel tbModel = (DefaultTableModel) Grilla.getModel();

                    if(!ExisteEnGrilla(txSkuTemporal.getText().trim())){
                                tbModel.addRow(GetTemporalOCP()); 
                                Sumador();
                            }
                    
                    else
                            {
                                JOptionPane.showMessageDialog(null, "El producto ya esta en el listado");
                            } 
                    
                }
            
            

            String rutprv = Proveedor.getChildText("RutProveedor");
            rutprv = rutprv.substring(0, rutprv.length() - 2);
            rutprv = rutprv.replace(".", "");
           
            txRut.setText(rutprv);
            btIr.doClick();

            String Femision = FechaIngreso.getValue().trim();
            Femision = Femision.substring(0, 10);
            Femision = Femision.replace("-", "//");

            SimpleDateFormat sdf = new SimpleDateFormat("dd//MM//yyyy");
            Date fe = sdf.parse(Femision);
            Calendar c = Calendar.getInstance();
            c.setTime(fe);
            c.add(Calendar.DATE, 4);
            Date fr = c.getTime();

            String obs = Observaciones.getValue().trim();
                        
            
            txComentario.setText(obs);
            dtEmision.setDate(fe);
            dtDespacho.setDate(fr);
           
            LimpiaTemporales();
        } catch (JDOMException | IOException e) {
            fmMain.Mensaje("Error al cargar xml: " + e);
            LimpiaTemporales();
        }
    }
    
    public void LimpiaTemporales(){
                        txSkuTemporal.setText("");
                        txDescTemporal.setText("");
                        txUMTemporal.setText("");
                        txCodBarTemporal.setText("");
                        txCantidadTemporal.setText("");
                        txUnitarioTemporal.setText("");
                        txTotlineaTemporal.setText("");
    }
    
    public Object[] GetTemporalOCP(){
        double TotLineaTemporal =  Float.parseFloat(txCantidadTemporal.getText().trim()) * Float.parseFloat(txUnitarioTemporal.getText().trim());
        return new Object[]{txSkuTemporal.getText().trim(),
                                                    txCodBarTemporal.getText().trim(),
                                                    txDescTemporal.getText().trim(),
                                                    txUMTemporal.getText().trim(),
                                                    fmMain.FormatoNumero(Double.valueOf(fmMain.SetGuardar(txCantidadTemporal.getText().trim()))),
                                                    fmMain.FormatoNumero(Double.valueOf(fmMain.SetGuardar(txUnitarioTemporal.getText().trim()))),
                                                    fmMain.FormatoNumeroSinDecimal(TotLineaTemporal),
                                                    Double.valueOf(fmMain.SetGuardar(txUnitarioTemporal.getText().trim())),
                                                    Double.valueOf(fmMain.SetGuardar(txCantidadTemporal.getText().trim()))};  
    }
    

    
    public void CargaBlog(){
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        String StrRut, NroDocto;
        
        
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
                            "and tipdocto='NPP' \n" + 
                            "and nrodocto=" + txNroOc.getText().trim()+" group by id");
            
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
                    
                    System.out.println("ENTRA AQUI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
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
                String texto = Rs.getString("texto").trim(); 
                tm.addRow(new Object[]{" ",texto ,Rs.getString("id")});
                if(Rs.getBoolean("compromiso"))
                    tm.addRow(new Object[]{" ", "Compromiso: " + sdf2.format(Rs.getTimestamp("fcompromiso"))});
                    
            }
            
        } catch (Exception e) {
            //fmMain.Mensaje("Ha ocurrido un error");
            LogError.Guardar(this.getClass().getSimpleName(),e.getMessage());
            Logger.getLogger(pfNPProveedor.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            Sql.Close();
        }
    }

    
    //********************************************************* FUNCIONES LUVALY *******************************************************************//
    
    public void GeneraNumOCC_Luvaly(String rut){
    
        Calendar c1 = Calendar.getInstance();
        String annio = Integer.toString(c1.get(Calendar.YEAR));
        String prfix = (annio.substring(2));
//        ExeSqlLuvaly SqlLuv = new ExeSqlLuvaly();
//        
//        
//        ResultSet Rs = null;
        String tipo = "-sala"+prfix;
        NroOc = NroOp.trim()+""+tipo;
        
        
    
//        try {
//
//            Rs = SqlLuv.Select("select case when (max(cast(split_part(orden,'-',1) as integer))+1) is null then 1 \n" +
//                               "else (max(cast(split_part(orden,'-',1) as integer))+1) end as correlativo \n" +
//                               "from occh where orden like '%"+tipo+"%' and rut = "+rut+"");
//            Rs.next();
//            
//            NroOc = Rs.getString("correlativo")+""+tipo;
//        
//        }catch (SQLException ex) {
//            
//            Logger.getLogger(pfNPProveedor.class.getName()).log(Level.SEVERE, null, ex);
//        
//        } finally {
//        
//            SqlLuv.Close();
//        
//        }
//        
    
    }
    
    
    
    public void NuevaOCC_Luvaly(int codvend, String rut) {
        
        ExeSqlLuvaly SqlLuv= new ExeSqlLuvaly();
        ExeSqlLuvaly SqlLuv3= new ExeSqlLuvaly();
        ExeSqlLuvaly SqlLuv4= new ExeSqlLuvaly();
        ResultSet Rs3 = null;
        
       
        String Query = "";
        int codigoOc = 99999;
        int cod_contacto = 0;
        
        GeneraNumOCC_Luvaly(rut);
        
        try{
          
        
            Query = "INSERT INTO occh(vendedor, rut, codigo_oc, orden, femision,contacto, totalafecto,totalexento, totaliva, totaldocto,\n" +
                    "prioridad,esexento,directoc, tipopago, tipodoc, ocp_econa)\n" +
                    "VALUES (" +
                                codvend+ "," + rut + "," + codigoOc+ "," +
                                "'" + NroOc.toLowerCase() + "','" + getFechaAsString() + "'," +  cod_contacto + "," +
                                fmMain.SetGuardar(txNeto.getText()) + "," + fmMain.SetGuardar(txExento.getText()) + "," + fmMain.SetGuardar(txIva.getText()) + "," +
                                fmMain.SetGuardar(txTotal.getText()) + "," + 0 + "," + 0  + "," +
                                false+ ", "+2+","+1+","+NroOp.trim()+")"; 
                               

            SqlLuv.ExeSql(Query);
                        
                        
                        
         // Guarda Productos
            for (int i = 0; i < Grilla.getRowCount(); i++) {
                            
                Query = "INSERT INTO occhdet( rut, codigo_oc, orden, sku, cantidad, valorunitario, totlinea,descuento_porcentaje,descuento_valor, directo,tdesc)\n" +
                        "VALUES ("  +
                        rut + "," +
                        codigoOc + ",'" +
                        NroOc.toLowerCase() + "','" +
                        Grilla.getModel().getValueAt(i, GetCol("Sku")).toString() + "'," +
                        fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("Cantidad")).toString()) + "," +
                        fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("UniReal")).toString()) + "," +
                        fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("Total")).toString()) + "," +
                        fmMain.SetGuardar(Grilla.getModel().getValueAt(i, 7).toString()) + "," +
                        fmMain.SetGuardar(Grilla.getModel().getValueAt(i, 8).toString()) + "," +
                        false+ ","+
                        false + ")";
                
                SqlLuv.ExeSql(Query);
            }
                 
                        

            SqlLuv.Commit();
     
            JOptionPane.showMessageDialog(null, "Orden Guardada");
                        
         //*************************************************************** PAGO MULTIPLE ******************************************************//                     
            pago_mult(1,codigoOc,NroOc.toLowerCase());
                        
       //*************************************************************************************************************************************************//                 
                        
      //     ValidaMargen();

        //********************************************************** ASIGNACION SEPARACION OCC *****************************************************//      
////         
                           String Orden = NroOc.toLowerCase();
                           String Rut = "76440015";
                           String Qr1 = "";
                           int separador = 0;
                           
                           //Se verifica el tipo de Orden de Compra
                           
                           if (Orden.contains("-cm") || Orden.contains("-ag") || Orden.contains("-se")){    ///
                               
                               separador = 1;
                           
                           
                           }else if (Rut.contains("76440015")){
                               
                               separador = 2;
                           
                           
                           }else if (Rut.contains("77244658")){
                               
                               separador = 3;
                           
                           
                           }else if (!Orden.contains("-cm") && !Orden.contains("-ag") && !Orden.contains("-se") && !Rut.contains("76440015") && !Rut.contains("77244658")){
                               
                               separador = 4;
                           
                           
                           }
                           
                           
                           Qr1 = "SELECT usuario FROM usuario WHERE separador = "+separador;        //Se busca el usuario de acuerdo a su asignacion de separacion
                           
                           Rs3 = SqlLuv3.Select(Qr1);
                           
                           if (SqlLuv3.GetRowCount() > 0){
                           
                               Rs3.next();
                               String usuario = Rs3.getString("usuario").trim();
                               
                               
                               SqlLuv4.ExeSql("UPDATE occh set usuario_separacion = '" + usuario + "'\n" +
                                              "WHERE codigo_oc = "+codigoOc+" \n"+
                                              "AND orden = '"+ NroOc.toLowerCase()+"' \n"+
                                              "AND usuario_separacion = 'LIBRES' ");
                           
                               SqlLuv4.Commit();
                           }
               
         //*****************************************************************************************************************************************//                       
                           
            
            
            
           SetTipo(2);
            
            
        }catch (SQLException e){
                SqlLuv.Rollback();    
                System.out.println(e.getMessage());
                fmMain.Mensaje("Error: "  + e);
                Logger.getLogger(pfNPProveedor.class.getName()).log(Level.SEVERE, null, e);
                
        }finally{
                SqlLuv.Close();
        }

    }
    
    
   
    
    
    
    
    
    public String getFechaAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return( sdf.format(Hoy) );
    }
    
    
    private void pago_mult(int tipo,int codigo_oc, String orden ){
    
        ExeSqlLuvaly SqlLuv4 = new ExeSqlLuvaly();
        String Query_multi = "";
            
        String valorpago1 = "";
        int id_pago1 = 0;
           
        id_pago1 = 2;
        valorpago1 = txTotal.getText().trim().replaceAll("\\,","");
           
        Query_multi = "INSERT INTO pago_multiple (rut,codigo_oc,orden,tipopago1,monto1,tipopago2,monto2,tipopago3,monto3,tipopago4,monto4,por_desc,usuario)\n" +
                      "VALUES \n" +
                      "("+txRut.getText()+","+codigo_oc+",'"+orden+"',"+id_pago1+","+valorpago1+","+0+","+0+","
                         + 0+","+0+","+0+","+0+","+0+",'"+fmMain.GetUsuario()+"')";
                     
        try {

            SqlLuv4.ExeSql(Query_multi);
            SqlLuv4.Commit();
                                
        } catch (SQLException ex) {
                
            SqlLuv4.Rollback();
            Logger.getLogger(pfNPProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    
    
    
    
  //***************************************************************************************************************************************//  
    
    public void NuevaOcp(String rut,ArrayList<ArrayList<String>> Listap){
    
    ExeSql SqlEc2= new ExeSql();
    ExeSql SqlEc3= new ExeSql();
    ExeSql SqlEc4= new ExeSql();
    ResultSet RsEc1 = null;   
        
        
        
    OCCL = true;    
        
    System.out.println("LA OCCL ES : "+OCCL);    
        
    System.out.println("EL TAMAÑO ES : "+Listap.size());
    
    System.out.println("EL RUT ES : "+rut);
    btNuevo.doClick();
    txRut.setText(rut);
    btIr.doClick();
    
    
    
    
    
    for(int i=0 ; i < Listap.size();i++){
            
        try {
            System.out.println("SKU ES : "+Listap.get(i).get(0));
            System.out.println("CANTIDAD ES : "+Listap.get(i).get(1));
            System.out.println("UNIDAD DE MEDIDA ES : "+Listap.get(i).get(2));
            System.out.println("PRECIO DE VENTA ES : "+Listap.get(i).get(3));
            
            
            //***************************************************** REGISTRO de Sku en ECONA*****************************************************************//
            
            
            RsEc1 =  SqlEc2.Select("select * from inventario \n"+
                                   "where sku='"+Listap.get(i).get(0)+"'");
            
            //****************************************************************************************************************************************//
            
            //int cantidad = Grilla.getRowCount();
            double TotLinea = 0;
            double cantidad = 0;
            
            if(cantidad<=100){
                
                DefaultTableModel tbModel = (DefaultTableModel) Grilla.getModel();
                
                if(!ExisteEnGrilla(Listap.get(i).get(0))){              //Si el producto aún no ha sido ingresado
                    
                    
                    cantidad = Float.parseFloat(Listap.get(i).get(3));
                    System.out.println("cantidad ES : "+cantidad);
                    
                    if (cantidad > 0){
                    
                       //TotLinea =  (Float.parseFloat(Listap.get(i).get(3))) * Float.parseFloat(Listap.get(i).get(4)); 
                        TotLinea =  (cantidad * Float.parseFloat(Listap.get(i).get(4))); 
                    
                    }else{
                    
                        System.out.println("ENTRA A OTROS!!!!!!!!!"); 
                        
                        cantidad = (cantidad*-1);
                        //TotLinea =  (Float.parseFloat(Listap.get(i).get(3))*-1) * Float.parseFloat(Listap.get(i).get(4));
                        TotLinea =  (cantidad * Float.parseFloat(Listap.get(i).get(4))); 
                    }
                    
                    tbModel.addRow(new Object[]{ Listap.get(i).get(0),
                        "",
                        Listap.get(i).get(1),       //Nombre Producto
                        Listap.get(i).get(2),       //Unidad de Medida
                        //fmMain.FormatoNumero(Double.valueOf(Listap.get(i).get(3))*-1),   //Cantidad
                        fmMain.FormatoNumero(cantidad),   //Cantidad
                        fmMain.FormatoNumero(Double.valueOf(Listap.get(i).get(4))),   //Valor Unitario
                        fmMain.FormatoNumeroSinDecimal(TotLinea),
                        Double.valueOf(fmMain.SetGuardar(Listap.get(i).get(4))),
                        Double.valueOf(fmMain.SetGuardar(Listap.get(i).get(3).replaceAll("-", ""))),
                        fmMain.FormatoNumeroSinDecimal(TotLinea)
                    });
                    
                    Sumador();
                    
                    
                }
                else
                    JOptionPane.showMessageDialog(null, "El producto ya esta en el listado");
                
            }
            else {
                JOptionPane.showMessageDialog(null, "No se puede ingresar más de 15 productos");
            }
            
            
            
            
            
            
            
            //****************************************************************************************************************************************//
        } catch (SQLException ex) {
            Logger.getLogger(pfNPProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
       
    
    }
    
    
    
    
 }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Grilla;
    private javax.swing.JTable GrillaBlog;
    private javax.swing.JCheckBox OCLuvalyEcona;
    private javax.swing.JButton btAbrir;
    private javax.swing.JButton btActualizar;
    private javax.swing.JButton btAgregar;
    private javax.swing.JButton btAgregarBlog;
    private javax.swing.JButton btAsociados;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btCerrar;
    private javax.swing.JButton btContacto;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btEliminar;
    private javax.swing.JButton btGenLuvaly;
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btImprimir;
    private javax.swing.JButton btIr;
    private javax.swing.JButton btLiberar;
    private javax.swing.JButton btMostrarHistorial;
    private javax.swing.JButton btNuevo;
    private javax.swing.JButton btOtrasOrdenes;
    private org.jdesktop.swingx.JXDatePicker dtDespacho;
    private org.jdesktop.swingx.JXDatePicker dtEmision;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private javax.swing.JTextField lbExento;
    private javax.swing.JTextField lbIVA;
    private javax.swing.JTextField lbImp;
    private javax.swing.JTextField lbNeto;
    private javax.swing.JTextField lbTotal;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JTextField txCantidadTemporal;
    private javax.swing.JTextField txCodBarTemporal;
    private javax.swing.JTextField txComentario;
    private javax.swing.JTextField txDescTemporal;
    private javax.swing.JTextField txDv;
    private javax.swing.JTextField txEstado;
    private javax.swing.JTextField txExento;
    private javax.swing.JTextField txImpEspecifico;
    private javax.swing.JTextField txIva;
    private javax.swing.JTextField txNeto;
    private javax.swing.JTextField txNombre;
    private javax.swing.JTextField txNroOc;
    private javax.swing.JTextField txRut;
    private javax.swing.JLabel txSku;
    private javax.swing.JTextField txSkuTemporal;
    private javax.swing.JTextField txTotal;
    private javax.swing.JTextField txTotlineaTemporal;
    private javax.swing.JTextField txUMTemporal;
    private javax.swing.JTextField txUnitarioTemporal;
    private javax.swing.JTextField txVendedor;
    // End of variables declaration//GEN-END:variables
}

