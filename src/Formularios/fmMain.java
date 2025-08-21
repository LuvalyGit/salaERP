package Formularios;

import Conexion.Conector;
import Conexion.ExeSql;
import Dialogos.jdBuscarCliPrv;
import Dialogos.jdCambioClave;
import Dialogos.jdFolios;
import Dialogos.jdLogin;
import PanelForm.AsignaUbicacion_Producto;
import PanelForm.Movimiento_Productos2;
import PanelForm.pfAdminParametros;
import PanelForm.pfAjustePrecio;
import PanelForm.pfAjusteStock;
import PanelForm.pfAutorizaNCC;
import PanelForm.pfCCCotizaCliente;
import PanelForm.pfProductos;
import PanelForm.pfClientes;
import PanelForm.pfNCCCliente;
import PanelForm.pfOCPReporte;
import PanelForm.pfNPProveedor;
import PanelForm.pfProveedores;
import PanelForm.pfReporteVentasFinal;
import PanelForm.pfBuscaDoc;
import PanelForm.pfControlStock;
import PanelForm.pfIndicadoresInventario;
import PanelForm.pfNPP_Pendientes;
import PanelForm.pfReportePrecio;
import PanelForm.pfReportesMargen;
import PanelForm.pfUsuarios;
import Utilidades.PanelTab;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Roberto López
 */
public class fmMain extends javax.swing.JFrame {

    static int[] PanEstado = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};//new int[10];
    private static double Sys_IVA = 0;
    private static String Sys_Decimal = ".";
    private static String Sys_Miles = ",";
    private static int FacNewYear = 0;
    private static String UsuarioNombreReal;
    private static String UsuarioClave;
    private static String UsuarioNombre;
    private static boolean UsuarioAdministrador;
    private static boolean UsuarioAjuste;
    private static boolean UsuarioCobranza;
    private static boolean UsuarioTransforma;
    private static boolean UsuarioAtorizaOCP;
    private static boolean UsuarioGastos;
    private static boolean UsuarioBodega;
    private static int UsuarioCCosto;
    private static int UsuarioId;
    private static boolean Internet;
    public static int disc = 0;
    public int intNivelMnu = 0;
    public static int intNivelUsuario = 0;   //Variable nueva
    public static int ccosto_usr = 0;
    public static int IntPosicionFinal = -1;
    private static int AdministraBodega;
    public static boolean cierra = false;
    public static boolean elimina = false;

    public fmMain() {

        initComponents();
        setTitle("SALA ERP");
        jdLogin Login = new jdLogin(null, true);
        Login.setLocationRelativeTo(null);
        Login.setVisible(true);
        CargaVariablesSistema();
        lbNombre.setText(UsuarioNombreReal);
        String Servidor = Conector.GetSistema();

        jButton1.setVisible(false);
        jButton2.setVisible(false);  //Boton Actualizar
        jButton3.setVisible(false);  //Para realizar consultas, delete, update o insert 
        jButton4.setVisible(false);  //Para realizar consultas, delete, update o insert
        jButton5.setVisible(false);  //Para realizar consultas, delete, update o insert

        lblServidor.setText(Servidor);

        try {
            setIconImage(new ImageIcon(getClass().getResource("/Iconos/Cash1.png")).getImage());
        } catch (Exception e) {
        }

        muestraDatos();

        System.out.println(GetUsuario());

//        setIconImage(new ImageIcon(getClass().getResource("../Iconos/L.png")).getImage());
//        muestraDatos();
        if (fmMain.trae_nivel(fmMain.GetUsuario()) < 100) {

            jMenuItem77.setEnabled(false);
            jMenuItem80.setEnabled(false);

            jButton2.setEnabled(false);
            jButton5.setEnabled(false);

        } else {

            jMenuItem77.setEnabled(true);
            jMenuItem80.setEnabled(true);

            jButton2.setEnabled(true);

        }

        AbrirCliente.setVisible(false);

        jMenu1.setVisible(false);  //Configuracion
        jMenuItem40.setVisible(false);  //Reporte de Ventas
        jMenuItem41.setVisible(false);  //Reporte de Margen

        jMenu6.setVisible(false);  //Administracion

        jMenuItem77.setVisible(false); //Ajuste Inventario

        ajustePrecio.setVisible(false); //Ajuste precio

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem8 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jMenuItem38 = new javax.swing.JMenuItem();
        jMenuItem39 = new javax.swing.JMenuItem();
        jMenuItem44 = new javax.swing.JMenuItem();
        jMenuItem52 = new javax.swing.JMenuItem();
        jMenuItem62 = new javax.swing.JMenuItem();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenuItem73 = new javax.swing.JMenuItem();
        jMenuItem74 = new javax.swing.JMenuItem();
        pnPestanas = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lbNombre = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblServidor = new javax.swing.JLabel();
        Calendario = new org.jdesktop.swingx.JXMonthView();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fecha_version = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        panelDatos2 = new javax.swing.JPanel();
        lbNDC2 = new javax.swing.JLabel();
        lbFEC2 = new javax.swing.JLabel();
        lbGDC2 = new javax.swing.JLabel();
        lbFAC2 = new javax.swing.JLabel();
        lbNCC2 = new javax.swing.JLabel();
        lbTituloDocumentos = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        MnuArchivo = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        mnuUsuarios = new javax.swing.JMenuItem();
        mnFolios = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        MnuVentas = new javax.swing.JMenu();
        NCC = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jMenuItem80 = new javax.swing.JMenuItem();
        jSeparator16 = new javax.swing.JPopupMenu.Separator();
        jMenuItem40 = new javax.swing.JMenuItem();
        jMenuItem41 = new javax.swing.JMenuItem();
        MnReportePrecios = new javax.swing.JMenuItem();
        jSeparator17 = new javax.swing.JPopupMenu.Separator();
        jMenuItem51 = new javax.swing.JMenuItem();
        MnuProductos = new javax.swing.JMenu();
        AbrirProducto = new javax.swing.JMenuItem();
        jSeparator18 = new javax.swing.JPopupMenu.Separator();
        AjustedeStock = new javax.swing.JMenuItem();
        MnuClientes = new javax.swing.JMenu();
        BuscarCliente = new javax.swing.JMenuItem();
        AbrirCliente = new javax.swing.JMenuItem();
        jMenuItem47 = new javax.swing.JMenuItem();
        MnuProveedores = new javax.swing.JMenu();
        mnuControlInventario = new javax.swing.JMenuItem();
        OCP = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        AbrirProveedor = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        ReporteOCP = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        ajustePrecio = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem19 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem20 = new javax.swing.JMenuItem();
        Control = new javax.swing.JMenu();
        jMenuItem55 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem77 = new javax.swing.JMenuItem();

        jMenuItem8.setText("jMenuItem8");

        jLabel1.setText("jLabel1");

        jMenuItem38.setText("jMenuItem38");

        jMenuItem39.setText("jMenuItem39");

        jMenuItem44.setText("jMenuItem44");

        jMenuItem52.setText("jMenuItem52");

        jMenuItem62.setText("jMenuItem62");

        jMenuItem34.setText("jMenuItem34");

        jMenuItem73.setText("jMenuItem73");

        jMenuItem74.setText("jMenuItem74");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LUVALY SPA");

        pnPestanas.setBackground(new java.awt.Color(161, 182, 197));
        pnPestanas.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        pnPestanas.setOpaque(true);

        jPanel2.setBackground(new java.awt.Color(161, 182, 197));
        jPanel2.setForeground(new java.awt.Color(51, 51, 255));
        jPanel2.setName(""); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbNombre.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbNombre.setForeground(new java.awt.Color(0, 0, 51));
        lbNombre.setText("Usuario");

        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setText("Usuario:");

        jLabel5.setForeground(new java.awt.Color(0, 0, 51));
        jLabel5.setText("Servidor:");

        lblServidor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblServidor.setForeground(new java.awt.Color(0, 0, 51));
        lblServidor.setText("Servidor");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblServidor, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblServidor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Calendario.setBackground(new java.awt.Color(255, 255, 255));
        Calendario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Calendario.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/Actualiza.png"))); // NOI18N
        jButton2.setText("Actualizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Calibri", 3, 36)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("SALA DE VENTAS ERP");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("Versión");

        fecha_version.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fecha_version.setForeground(new java.awt.Color(255, 0, 0));
        fecha_version.setText("16.03.2022");
        fecha_version.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(fecha_version))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fecha_version))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        panelDatos2.setBackground(new java.awt.Color(255, 255, 255));
        panelDatos2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelDatos2.setToolTipText("");

        lbNDC2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        lbFEC2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        lbGDC2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbGDC2.setText(" ");

        lbFAC2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        lbNCC2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout panelDatos2Layout = new javax.swing.GroupLayout(panelDatos2);
        panelDatos2.setLayout(panelDatos2Layout);
        panelDatos2Layout.setHorizontalGroup(
            panelDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatos2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbNCC2, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                    .addComponent(lbFAC2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbGDC2, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                    .addComponent(lbFEC2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNDC2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        panelDatos2Layout.setVerticalGroup(
            panelDatos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatos2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbNDC2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbFEC2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(lbGDC2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbFAC2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNCC2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        lbTituloDocumentos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTituloDocumentos.setText("DOCUMENTOS DISPONIBLES");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(Calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTituloDocumentos)
                            .addComponent(panelDatos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)))
                .addGap(84, 84, 84))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(Calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbTituloDocumentos)
                        .addGap(10, 10, 10)
                        .addComponent(panelDatos2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );

        jPanel2.add(jPanel4);

        pnPestanas.addTab("Inicio", jPanel2);

        getContentPane().add(pnPestanas, java.awt.BorderLayout.CENTER);

        MnuArchivo.setText("Archivo");

        jMenu1.setText("Configuración");

        mnuUsuarios.setText("Usuarios");
        mnuUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuUsuariosActionPerformed(evt);
            }
        });
        jMenu1.add(mnuUsuarios);

        mnFolios.setText("Folios");
        mnFolios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnFoliosActionPerformed(evt);
            }
        });
        jMenu1.add(mnFolios);

        MnuArchivo.add(jMenu1);

        jMenuItem5.setText("Salir");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        MnuArchivo.add(jMenuItem5);

        jMenuBar1.add(MnuArchivo);

        MnuVentas.setText("Ventas");

        NCC.setText("Nota de Crédito Cliente");
        NCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NCCActionPerformed(evt);
            }
        });
        MnuVentas.add(NCC);
        MnuVentas.add(jSeparator10);

        jMenuItem80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/billetera24.png"))); // NOI18N
        jMenuItem80.setText("Autorizar NCC");
        jMenuItem80.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem80ActionPerformed(evt);
            }
        });
        MnuVentas.add(jMenuItem80);
        MnuVentas.add(jSeparator16);

        jMenuItem40.setText("Reporte de Ventas");
        jMenuItem40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem40ActionPerformed(evt);
            }
        });
        MnuVentas.add(jMenuItem40);

        jMenuItem41.setText("Reporte Margen");
        jMenuItem41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem41ActionPerformed(evt);
            }
        });
        MnuVentas.add(jMenuItem41);

        MnReportePrecios.setText("Reporte Precios");
        MnReportePrecios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnReportePreciosActionPerformed(evt);
            }
        });
        MnuVentas.add(MnReportePrecios);
        MnuVentas.add(jSeparator17);

        jMenuItem51.setText("Buscar Documento...");
        jMenuItem51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem51ActionPerformed(evt);
            }
        });
        MnuVentas.add(jMenuItem51);

        jMenuBar1.add(MnuVentas);

        MnuProductos.setText("Productos");

        AbrirProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos22/Open.png"))); // NOI18N
        AbrirProducto.setText("Ficha de Producto");
        AbrirProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirProductoActionPerformed(evt);
            }
        });
        MnuProductos.add(AbrirProducto);
        MnuProductos.add(jSeparator18);

        AjustedeStock.setText("Ajuste Stock");
        AjustedeStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AjustedeStockActionPerformed(evt);
            }
        });
        MnuProductos.add(AjustedeStock);

        jMenuBar1.add(MnuProductos);

        MnuClientes.setText("Clientes");

        BuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos22/search.png"))); // NOI18N
        BuscarCliente.setText("Buscar...");
        BuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarClienteActionPerformed(evt);
            }
        });
        MnuClientes.add(BuscarCliente);

        AbrirCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos22/Open.png"))); // NOI18N
        AbrirCliente.setText("Abrir Ficha");
        AbrirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirClienteActionPerformed(evt);
            }
        });
        MnuClientes.add(AbrirCliente);

        jMenuItem47.setText("Cotizacion Cliente");
        jMenuItem47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem47ActionPerformed(evt);
            }
        });
        MnuClientes.add(jMenuItem47);

        jMenuBar1.add(MnuClientes);

        MnuProveedores.setText("Proveedores");

        mnuControlInventario.setText("Control de Stock");
        mnuControlInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuControlInventarioActionPerformed(evt);
            }
        });
        MnuProveedores.add(mnuControlInventario);

        OCP.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        OCP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/OCPoveedor16.png"))); // NOI18N
        OCP.setText("Nota de Pedido");
        OCP.setToolTipText("");
        OCP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OCPActionPerformed(evt);
            }
        });
        MnuProveedores.add(OCP);

        jMenuItem2.setText("Notas de Pedido Pendientes");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        MnuProveedores.add(jMenuItem2);

        AbrirProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Provider24.png"))); // NOI18N
        AbrirProveedor.setText("Proveedor");
        AbrirProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirProveedorActionPerformed(evt);
            }
        });
        MnuProveedores.add(AbrirProveedor);
        MnuProveedores.add(jSeparator3);

        ReporteOCP.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        ReporteOCP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos22/checklist22.png"))); // NOI18N
        ReporteOCP.setText("Reporte Notas de Pedido");
        ReporteOCP.setToolTipText("");
        ReporteOCP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReporteOCPActionPerformed(evt);
            }
        });
        MnuProveedores.add(ReporteOCP);
        MnuProveedores.add(jSeparator2);

        ajustePrecio.setText("Reporte Ajuste Precio");
        ajustePrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajustePrecioActionPerformed(evt);
            }
        });
        MnuProveedores.add(ajustePrecio);

        jMenuBar1.add(MnuProveedores);

        jMenu6.setText("Administracion");

        jMenuItem19.setText("Cambiar mi clave");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem19);
        jMenu6.add(jSeparator7);

        jMenuItem20.setText("Definir Parametros");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem20);

        jMenuBar1.add(jMenu6);

        Control.setText("Control");

        jMenuItem55.setText("Ubicación de Productos");
        jMenuItem55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem55ActionPerformed(evt);
            }
        });
        Control.add(jMenuItem55);

        jMenuItem1.setText("Movimiento de Productos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        Control.add(jMenuItem1);

        jMenuItem77.setText("Ajuste Inventario");
        jMenuItem77.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem77ActionPerformed(evt);
            }
        });
        Control.add(jMenuItem77);

        jMenuBar1.add(Control);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public static void SetEstado(int Pos, int Estado) {
        PanEstado[Pos] = Estado;
    }

    public void muestraDatos() {

        this.setExtendedState(MAXIMIZED_BOTH);
        intNivelMnu = 100;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if (intNivelMnu > intNivelUsuario) {
            return;
        }
        lbTituloDocumentos.setVisible(true);
        buscaFactura();
        buscaNotaDebito();
        buscaFacturaExentaCliente();
        buscaGuiaDespachoCliente();
        buscaNotaCreditoCliente();

    }

    public void buscaFactura() {
             //BUSCA CANTIDAD FACTURAS

        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        int ContReg = 0;
        String Query = "";
        lbFAC2.setText("");
        int almFAC = 0;
        try {
            Query = "SELECT *, (hasta - numero ) AS ResultadoFAC FROM par_correlativo WHERE tipo = 'FAC'";
            Rs = Sql.Select(Query);
            while (Rs.next()) {
                ContReg++;
                almFAC = Rs.getInt("ResultadoFAC");
                if (almFAC > 20) {
                    lbFAC2.setText("Facturas:                                    " + Rs.getString("ResultadoFAC"));
                    lbFAC2.setForeground(Color.black);
                } else {
                    lbFAC2.setText("Facturas:                                    " + Rs.getString("ResultadoFAC"));
                    lbFAC2.setForeground(Color.red);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pueden encontrar datos");
        } finally {
            Sql.Close();
        }
    }

    public void buscaNotaDebito() {
     //BUSCA CANTIDAD NOTAS DE CREDITO

        ExeSql SqlNDC = new ExeSql();
        ResultSet RsNDC;
        int ContRegNDC = 0;
        String QueryNDC = "";
        lbNDC2.setText("");
        int almNDC = 0;
        try {
            QueryNDC = "SELECT *, (hasta -numero) AS ResultadoNDC FROM par_correlativo WHERE tipo = 'NDC'";
            RsNDC = SqlNDC.Select(QueryNDC);
            while (RsNDC.next()) {
                ContRegNDC++;
                almNDC = RsNDC.getInt("ResultadoNDC");
                if (almNDC > 20) {
                    lbNDC2.setText("Notas de Debito Cliente:         " + RsNDC.getString("ResultadoNDC"));
                    lbNDC2.setForeground(Color.black);
                } else {
                    lbNDC2.setText("Notas de Debito Cliente:         " + RsNDC.getString("ResultadoNDC"));
                    lbNDC2.setForeground(Color.red);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pueden encontrar datos");
        } finally {
            SqlNDC.Close();
        }
    }

    public void buscaFacturaExentaCliente() {
    //BUSCA CANTIDAD FACTURA EXENTA CLIENTE

        ExeSql SqlFEC = new ExeSql();
        ResultSet RsFEC;
        int ContRegFEC = 0;
        String QueryFEC = "";
        lbFEC2.setText("");
        int almFEC = 0;
        try {
            QueryFEC = "SELECT *, (hasta - numero ) AS ResultadoFEC FROM par_correlativo WHERE tipo = 'FEC'";
            RsFEC = SqlFEC.Select(QueryFEC);
            while (RsFEC.next()) {
                ContRegFEC++;
                almFEC = RsFEC.getInt("ResultadoFEC");
                if (almFEC > 20) {
                    lbFEC2.setText("Facturas Exenta a Cliente:    " + RsFEC.getString("ResultadoFEC"));
                    lbFEC2.setForeground(Color.black);
                } else {
                    lbFEC2.setText("Facturas Exenta a Cliente:    " + RsFEC.getString("ResultadoFEC"));
                    lbFEC2.setForeground(Color.red);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pueden encontrar datos");
        } finally {
            SqlFEC.Close();
        }
    }

    public void buscaGuiaDespachoCliente() {
  //BUSCA CANTIDAD GUIA DESPACHO CLIENTE

        ExeSql SqlGDC = new ExeSql();
        ResultSet RsGDC;
        int ContRegGDC = 0;
        String QueryGDC = "";
        lbGDC2.setText("");
        int almGDC = 0;
        try {
            QueryGDC = "SELECT *, (hasta - numero ) AS ResultadoGDC FROM par_correlativo WHERE tipo = 'GDC'";
            RsGDC = SqlGDC.Select(QueryGDC);
            while (RsGDC.next()) {
                ContRegGDC++;
                almGDC = RsGDC.getInt("ResultadoGDC");
                if (almGDC > 20) {
                    lbGDC2.setText("Guias de Despacho:                 " + RsGDC.getString("ResultadoGDC"));
                    lbGDC2.setForeground(Color.black);
                } else {
                    lbGDC2.setText("Guias de Despacho:                 " + RsGDC.getString("ResultadoGDC"));
                    lbGDC2.setForeground(Color.red);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pueden encontrar datos");
        } finally {
            SqlGDC.Close();
        }
    }

    public void buscaNotaCreditoCliente() {
      //BUSCA CANTIDAD NOTA DE CREDITO CLIENTE

        ExeSql SqlNCC = new ExeSql();
        ResultSet RsNCC;
        int ContRegNCC = 0;
        String QueryNCC = "";
        lbNCC2.setText("");
        int almNCC = 0;
        try {
            QueryNCC = "SELECT *, (hasta - numero ) AS ResultadoNCC FROM par_correlativo WHERE tipo = 'NCC'";
            RsNCC = SqlNCC.Select(QueryNCC);
            while (RsNCC.next()) {
                ContRegNCC++;
                almNCC = RsNCC.getInt("ResultadoNCC");
                if (almNCC > 20) {
                    lbNCC2.setText("Notas de Credito Cliente:       " + RsNCC.getString("ResultadoNCC"));
                    lbNCC2.setForeground(Color.black);
                } else {
                    lbNCC2.setText("Notas de Credito Cliente:       " + RsNCC.getString("ResultadoNCC"));
                    lbNCC2.setForeground(Color.red);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pueden encontrar datos");
        } finally {
            SqlNCC.Close();
        }
    }

    public static void SetUsuario(String Nombre,
            String Usuario,
            String Clave,
            boolean EsInternet,
            boolean Admin,
            boolean Ajuste,
            boolean Cobranza,
            boolean Gastos,
            boolean Transformacion,
            boolean AdminOCP,
            boolean Bodega,
            int CCosto,
            int Id,
            int Nivel, //Variabel Nueva
            int AdminBodega) {
        UsuarioNombreReal = Nombre;
        UsuarioNombre = Usuario;
        UsuarioClave = Clave;

        UsuarioAdministrador = Admin;
        UsuarioAjuste = Ajuste;
        UsuarioCobranza = Cobranza;
        UsuarioTransforma = Transformacion;
        UsuarioAtorizaOCP = AdminOCP;
        UsuarioGastos = Gastos;
        UsuarioBodega = Bodega;
        AdministraBodega = AdminBodega;

        UsuarioCCosto = CCosto;
        UsuarioId = Id;
        intNivelUsuario = Nivel;      //Codigo nuevo
        Internet = EsInternet;

    }

    public static void SetNombreUsuario(String ElNombre) {

        UsuarioNombre = ElNombre;
    }

    public static int GetFacNewYear() {
        ResultSet Rs;
        ExeSql Sql = new ExeSql();
        try {
            Rs = Sql.Select("select fac_newyear from parsys");
            Rs.next();
            return Rs.getInt("fac_newyear");
        } catch (Exception e) {
            return 0;
        } finally {
            Sql.Close();
        }
    }

    public static String BodegaTransito() {
        return "SAL.1001.1";
    }

    public static String Rack1() {
        return "SAL.101.1";
    }

    public static String BodegaTransformacion() {
        return "TRAN.1005.1";
    }

    public static String BodegaNCC() {
        return "TRAN.1004.1";
    }

    public static String BodegaOCDirecta() {
        return "TRAN.1003.1";
    }

    public static String BodegaNCP() {
        return "TRAN.1002.1";
    }

    public static String BodegaAnticipada() {
        return "TRAN.1007.1";
    }

    public static String BodegaNegativos() {
        return "INV.1011.2";
    }

    public static String BodegaPositivos() {
        return "INV.1011.1";
    }

    public static void ErrorUsuarioLog(String Usuario, String Error) {
        ExeSql Sql = new ExeSql();

        try {
            Sql.ExeSql("insert into usuarios_eventos (usuario,tipo)values \n"
                    + "('" + Usuario + "','" + Error + "')");
            Sql.Commit();

        } catch (Exception e) {

            Sql.Rollback();
        } finally {
            Sql.Close();
        }
    }

    public static boolean GetInternet() {
        return Internet;
    }

    public static int GetAdminBodega() {
        return AdministraBodega;
    }

    public static String GetUsuario() {
        return UsuarioNombre;
    }

    public static String GetPass() {
        return UsuarioClave;
    }

    public static int GetEstado(int Pos) {
        return PanEstado[Pos];
    }

    public static void DeleteEstado(int Pos) {

        for (int i = Pos; i <= 12; i++) {
            PanEstado[i] = PanEstado[i + 1];
        }
    }

    public static String GetDecimal() {

        return Sys_Decimal;
    }

    public static String GetMiles() {
        return Sys_Miles;
    }

    public static double GetIva() {
        return Sys_IVA;
    }

    public static boolean GetUsuarioAdministrador() {
        return UsuarioAdministrador;
    }

    public static boolean GetUsuarioCobranza() {
        return UsuarioCobranza;
    }

    public static boolean GetUsuarioAjuste() {
        return UsuarioAjuste;
    }

    public static boolean GetUsuarioTransforma() {
        return UsuarioTransforma;
    }

    public static boolean GetUsuarioAdminOCP() {
        return UsuarioAtorizaOCP;
    }

    public static boolean GetUsuarioBodega() {
        return UsuarioBodega;
    }

    public static boolean GetUsuarioGastos() {
        return UsuarioGastos;
    }

    public static String GetCentrodeCosto() {
        return String.valueOf(UsuarioCCosto);
    }

    public static String GetUsuarioId() {
        return String.valueOf(UsuarioId);
    }

    public static String GetStringDeFinal(char cara, String nombrePalabra) {

        int j;
        j = 0;

        for (int i = nombrePalabra.length() - 1; i > 0; i--) {

            if (nombrePalabra.charAt(i) == cara) {
                j = i;
                break;
            }
        }
        return nombrePalabra.substring(j + 1).trim();
    }

    public static String GetStringDeInicio(char cara, String nombrePalabra) {

        int j;
        j = 0;

        for (int i = 0; i <= nombrePalabra.length(); i++) {

            if (nombrePalabra.charAt(i) == cara) {
                j = i;
                break;
            }
        }

        return nombrePalabra.substring(j + 1).trim();
    }

    public static boolean validarRut(String rut) {
        boolean validacion = false;
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dvx = rut.charAt(rut.length() - 1);
            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dvx == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }
            //fmMain.Mensaje("EL DV ES :" + (s - 1));
        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
    }

    public static String FormatoNumeroBig(BigInteger Numero) {
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        DecimalFormat formateador = new DecimalFormat("###,##0", simbolo);
        return formateador.format(Numero);
    }

    public static String FormatoNumeroInt(Integer Numero) {
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        DecimalFormat formateador = new DecimalFormat("###,##0", simbolo);
        return formateador.format(Numero);
    }

    public static String FormatoNumeroBigLimpio(BigInteger Numero) {
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        DecimalFormat formateador = new DecimalFormat("#0", simbolo);
        return formateador.format(Numero);
    }

    public static String FormatoNumero(double Numero) {
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        DecimalFormat formateador = new DecimalFormat("###,##0.00", simbolo);
        return formateador.format(Numero);
    }

    public static String FormatoNumeroSinDecimal(double Numero) {
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        DecimalFormat formateador = new DecimalFormat("###,##0", simbolo);
        return formateador.format(Numero);
    }

    public static String FormatoTotal(double Numero) {
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        DecimalFormat formateador = new DecimalFormat("###,###", simbolo);
        return formateador.format(Numero);
    }

    public static String SetGuardar(String Numero) {
        String Retorno = Numero.replace(",", "");
        Retorno = Retorno.replace(",", "");
        Retorno = Retorno.replace("$", "");
        Retorno = Retorno.replace("'", "");

        Retorno = EliminaCaracteres(Retorno, ",");

        return Retorno;
    }

    public static void Msje(String Titulo, String Mensaje, int xx, int yy) {
        JOptionPane pane = new JOptionPane(Mensaje);
        JDialog dialog = pane.createDialog(Titulo);
        dialog.setLocation(xx, yy);
        dialog.setVisible(true);
    }

    public static String SetGuardarEntero(String Numero) {
        String Retorno = Numero.replace(",", "");

        Retorno = Retorno.replace(",", "");
        Retorno = Retorno.replace("$", "");
        Retorno = Retorno.replace(".", "");
        Retorno = Retorno.replace("$", "");

        Retorno = EliminaCaracteres(Retorno, ",");

        return Retorno;
    }

    public static String EliminaCaracteres(String s_cadena, String s_caracteres) {

        String nueva_cadena = "";
        Character caracter = null;
        boolean valido = true;

        /* Va recorriendo la cadena s_cadena y copia a la cadena que va a regresar,
         sólo los caracteres que no estén en la cadena s_caracteres */
        for (int i = 0; i < s_cadena.length(); i++) {

            valido = true;

            for (int j = 0; j < s_caracteres.length(); j++) {

                caracter = s_caracteres.charAt(j);

                if (s_cadena.charAt(i) == caracter) {

                    valido = false;
                    break;
                }
            }
            if (valido) {
                nueva_cadena += s_cadena.charAt(i);
            }
        }

        return nueva_cadena;
    }

    public static boolean EsLetra(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() > KeyEvent.VK_A && evt.getKeyCode() < KeyEvent.VK_Z || evt.getKeyChar() == 'ñ') {
            return true;
        } else {
            return false;
        }
    }

    public static String SetString(String Texto) {

        if (Texto.isEmpty() || Texto.trim().equals("")) {
            return "null";
        } else {
            return "'" + Texto.trim() + "'";
        }
    }

    public static void LimpiaGrilla(DefaultTableModel dfTm) {
        while (dfTm.getRowCount() > 0) {
            dfTm.removeRow(0);
        }
    }

    public static int OkCancel(String Mensaje) {

        int result = JOptionPane.showConfirmDialog(null, Mensaje, "Confirmar", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }

    public static void Mensaje(String Mensaje) {
        int Pos = 0;

        if (Mensaje != null) {

            Pos = Mensaje.indexOf("Where");

            if (Pos > 0) {

                System.out.println(Mensaje);
                Mensaje = Mensaje.substring(0, Pos);
            }
            JOptionPane.showMessageDialog(null, Mensaje);
        }

    }

    private void CargaVariablesSistema() {

        ExeSql Sql = new ExeSql();
        try {
            ResultSet Rs = Sql.Select("select iva,decimal,fac_newyear from parsys");
            Rs.next();
            Sys_IVA = Rs.getFloat("iva") / 100;
            Sys_Decimal = Rs.getString("decimal");
            FacNewYear = Rs.getInt("fac_newyear");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Carga Variables " + e);
        } finally {
            Sql.Close();
        }

    }

    public static int trae_nivel(String Usu) {

        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        String Ubica = "", Query = "";
        int i = 0;
        int Salida = 0;
        try {

            Query = "SELECT u.usuario,p.rut,cc.ccosto ccosto_pcc, u.nivel\n"
                    + "FROM personal p\n"
                    + "left join pacceso a on p.rut=a.rut \n"
                    + "left join personal_ccostos cc on p.rut=cc.rut\n"
                    + "left join usuario u on u.usuario = p.usuario\n"
                    + "WHERE u.usuario ='" + Usu.trim() + "'";

            Rs = Sql.Select(Query);

            if (Sql.GetRowCount() == 0) {

                Toolkit.getDefaultToolkit().beep();
                fmMain.Mensaje("No existe Usuario");
                Salida = 0;
            }

            if (Rs.next()) {

                Salida = Rs.getInt("nivel");
                ccosto_usr = Rs.getInt("ccosto_pcc");
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());

        } finally {

            Sql.Close();
        } //Finally        

        return Salida;
    }

    public void PestanaProducto(String Codigo) {
        pfProductos Pro = new pfProductos();
        //Pro.setOpaque(false);

        pnPestanas.addTab("Nuevo Producto", Pro);

        PanelTab btc = new PanelTab(pnPestanas, 0);
        btc.setBorder(null);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(Pro), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);

        Pro.txSku.requestFocus();
        if (!Codigo.isEmpty()) {
            Pro.CargaProducto(Codigo);
        }
    }
//-----------------------------------------------------------------------------
    private void AbrirClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirClienteActionPerformed

      //--------------------------------------------------------------------------------------
        /*     
         Valida el nivel de usuario 
         Administrador = 100
         Admin Area     = 90
         Supervisores   = 80
         Normal        =  70
         Normal Vtas        =  71
         Visita =         60
         Cada menu del sistema tendra un valor de acuerdo al nivel que quiero que acceda.        
         */
        intNivelMnu = 70;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if (((intNivelMnu > intNivelUsuario) || (ccosto_usr != 5 && ccosto_usr != 3)) && (intNivelUsuario <= 80)) {
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado");
            return;
        }
    //--------------------------------------------------------------------------------------   

        pfClientes Cli = new pfClientes();
//        Cli.setOpaque(false);
        pnPestanas.addTab("Nuevo Cliente", Cli);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(Cli), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
    }//GEN-LAST:event_AbrirClienteActionPerformed

    private void AbrirProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirProveedorActionPerformed

        //--------------------------------------------------------------------------------------
        /*     
         Valida el nivel de usuario 
         Administrador = 100
         Admin Area     = 90
         Supervisores   = 80
         Normal        =  70
         Normal Vtas        =  71
         Visita =         60
         Cada menu del sistema tendra un valor de acuerdo al nivel que quiero que acceda.        
         */
        intNivelMnu = 70;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if (intNivelMnu > intNivelUsuario) {
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado");
            return;
        }
    //--------------------------------------------------------------------------------------        

        pfProveedores Prv = new pfProveedores();
//        Prv.setOpaque(false);
        pnPestanas.addTab("Nuevo Proveedor", Prv);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(Prv), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
        Prv.txRut.requestFocus();
    }//GEN-LAST:event_AbrirProveedorActionPerformed

    private void BuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarClienteActionPerformed
      //--------------------------------------------------------------------------------------
        /*     
         Valida el nivel de usuario 
         Administrador = 100
         Admin Area    = 90
         Supervisores  = 80
         Normal        = 70
         Normal Vtas   = 71
         Visita        = 60
         Cada menu del sistema tendra un valor de acuerdo al nivel que quiero que acceda.        
         */

        intNivelMnu = 70;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if (((intNivelMnu > intNivelUsuario) || (ccosto_usr != 5)) && (intNivelUsuario <= 80)) {
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado");
            return;
        }
    //--------------------------------------------------------------------------------------        

        jdBuscarCliPrv BPC = new jdBuscarCliPrv(this, true);
        BPC.setLocationRelativeTo(null);
        BPC.setDefaultCloseOperation(BPC.DISPOSE_ON_CLOSE);
        BPC.setTitle("Buscar Cliente");
        BPC.SetTipo(0);
        BPC.setVisible(true);


    }//GEN-LAST:event_BuscarClienteActionPerformed

    private void OCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OCPActionPerformed

          //--------------------------------------------------------------------------------------
        /*     
         Valida el nivel de usuario 
         Administrador = 100
         Admin Area    = 90
         Supervisores  = 80
         Normal        =  70
         Normal Vtas   =  71
         Visita        = 60
         Cada menu del sistema tendra un valor de acuerdo al nivel que quiero que acceda.        
         */
        intNivelMnu = 70;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if (intNivelMnu > intNivelUsuario) {
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado");
            return;
        }
    //--------------------------------------------------------------------------------------        

        pfNPProveedor PrvNP = new pfNPProveedor();
//        PrvOC.setOpaque(false);
        pnPestanas.addTab("Nota de Pedido         ", PrvNP);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(PrvNP), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);

    }//GEN-LAST:event_OCPActionPerformed

    private void mnuControlInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuControlInventarioActionPerformed
   //--------------------------------------------------------------------------------------
        /*     
         Valida el nivel de usuario 
         Administrador = 100
         Admin Area    = 90
         Supervisores  = 80
         Normal        = 70
         Normal Vtas   = 71
         Visita        = 60
         Cada menu del sistema tendra un valor de acuerdo al nivel que quiero que acceda.        
         */

        intNivelMnu = 70;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());

        if (intNivelMnu > intNivelUsuario) {
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado");
            return;
        }
    //--------------------------------------------------------------------------------------        

        pfControlStock ControlStock = new pfControlStock();
        pnPestanas.addTab("Control de Stock", ControlStock);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(ControlStock), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);


    }//GEN-LAST:event_mnuControlInventarioActionPerformed

    private void mnuUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuUsuariosActionPerformed

//--------------------------------------------------------------------------------------
        /*     
         Valida el nivel de usuario 
         Administrador = 100
         Admin Area    = 90
         Supervisores  = 80
         Normal        = 70
         Normal Vtas   = 71
         Visita        = 60
         Cada menu del sistema tendra un valor de acuerdo al nivel que quiero que acceda.        
         */
        intNivelMnu = 100;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if (((intNivelMnu > intNivelUsuario) || (ccosto_usr != 4)) && (intNivelUsuario < 100)) {
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado");
            return;
        }
    //--------------------------------------------------------------------------------------           

        pfUsuarios Usuarios = new pfUsuarios();
//        Usuarios.setOpaque(false);
        pnPestanas.addTab("Configuración Usuarios", Usuarios);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(Usuarios), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
    }//GEN-LAST:event_mnuUsuariosActionPerformed

    private void ReporteOCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReporteOCPActionPerformed
          //--------------------------------------------------------------------------------------
        /*     
         Valida el nivel de usuario 
         Administrador = 100
         Admin Area     = 90
         Supervisores   = 80
         Normal        =  70
         Normal Vtas        =  71
         Visita =         60
         Cada menu del sistema tendra un valor de acuerdo al nivel que quiero que acceda.        
         */

        intNivelMnu = 70;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if (intNivelMnu > intNivelUsuario) {
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado");
            return;
        }
        //--------------------------------------------------------------------------------------        
        pfOCPReporte repOCP = new pfOCPReporte();
//        repOCP.setOpaque(false);
        pnPestanas.addTab("ORDENES DE COMPRA PROVEEDOR", repOCP);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(repOCP), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
    }//GEN-LAST:event_ReporteOCPActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        System.exit(1);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        jdCambioClave Clave = new jdCambioClave(this, true);
        Clave.setLocationRelativeTo(null);
        Clave.setDefaultCloseOperation(Clave.DISPOSE_ON_CLOSE);
        Clave.setTitle("Cambiar Mi Clave");
        Clave.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed

        //--------------------------------------------------------------------------------------
        /*     
         Valida el nivel de usuario 
         Administrador = 100
         Admin Area     = 90
         Supervisores   = 80
         Normal        =  70
         Normal Vtas        =  71
         Visita =         60
         Cada menu del sistema tendra un valor de acuerdo al nivel que quiero que acceda.        
         */
        intNivelMnu = 100;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if (((intNivelMnu > intNivelUsuario) || (ccosto_usr != 4)) && (intNivelUsuario <= 80)) {
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado");
            return;
        }
    //--------------------------------------------------------------------------------------           

//if(GetUsuarioAdministrador()){
        pfAdminParametros Admin = new pfAdminParametros();
//            Admin.setOpaque(false);
        pnPestanas.addTab("Parametros", Admin);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(Admin), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
//        }    
//        else{
//            Mensaje("Usuario no autorizado");
//        }
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void AjustedeStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AjustedeStockActionPerformed

	//--------------------------------------------------------------------------------------
        /*     
         Valida el nivel de usuario 
         Administrador = 100
         Admin Area     = 90
         Supervisores   = 80
         Normal        =  70
         Normal Vtas        =  71
         Visita =         60
         Cada menu del sistema tendra un valor de acuerdo al nivel que quiero que acceda.        
         */
        intNivelMnu = 80;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
//         if (((intNivelMnu > intNivelUsuario )|| (ccosto_usr!=7) ) && (intNivelUsuario<=80)){
        if (((intNivelMnu > intNivelUsuario) || (ccosto_usr != 7)) && (intNivelUsuario < 100)) {
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado");
            return;
        }
    //--------------------------------------------------------------------------------------   

        pfAjusteStock AJU = new pfAjusteStock();
        //            AJU.setOpaque(false);
        pnPestanas.addTab("AJUSTE", AJU);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(AJU), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
    }//GEN-LAST:event_AjustedeStockActionPerformed
    private void AbrirProductoActionPerformed(java.awt.event.ActionEvent evt) {

        //--------------------------------------------------------------------------------------
        /*     
         Valida el nivel de usuario 
         Administrador = 100
         Admin Area     = 90
         Supervisores   = 80
         Normal        =  70
         Visita =         60
         Cada menu del sistema tendra un valor de acuerdo al nivel que quiero que acceda.        
         */
        intNivelMnu = 70;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());

        if (intNivelMnu > intNivelUsuario) {
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado");
            return;
        }
    //--------------------------------------------------------------------------------------

        pfProductos Pro = new pfProductos();
        pnPestanas.addTab("Ficha Producto", Pro);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(Pro), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
        Pro.txSku.requestFocus();
    }
//GEN-FIRST:event_AbrirProductoActionPerformed
//GEN-LAST:event_AbrirProductoActionPerformed

    private void NCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NCCActionPerformed
        //--------------------------------------------------------------------------------------
        /*     
         Valida el nivel de usuario 
         Administrador = 100
         Admin Area     = 90
         Supervisores   = 80
         Normal        =  70
         Visita =         60
         Cada menu del sistema tendra un valor de acuerdo al nivel que quiero que acceda.        
         */
        intNivelMnu = 70;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if (intNivelMnu > intNivelUsuario) {
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado");
            return;
        }
    //--------------------------------------------------------------------------------------

        pfNCCCliente NCC = new pfNCCCliente();
        //        NCC.setOpaque(false);
        pnPestanas.addTab("NOTA DE CRÉDITO", NCC);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(NCC), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
    }//GEN-LAST:event_NCCActionPerformed

    private void jMenuItem40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem40ActionPerformed

        //--------------------------------------------------------------------------------------
        /*     
         Valida el nivel de usuario 
         Administrador = 100
         Admin Area     = 90
         Supervisores   = 80
         Normal        =  70
         Visita =         60
         Cada menu del sistema tendra un valor de acuerdo al nivel que quiero que acceda.        
         */
        intNivelMnu = 100;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if (intNivelMnu > intNivelUsuario) {
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado");
            return;
        }
    //--------------------------------------------------------------------------------------

        //if(GetUsuarioAdministrador()){
        pfReporteVentasFinal RepoVenta = new pfReporteVentasFinal();
        pnPestanas.addTab("Reporte de Ventas", RepoVenta);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(RepoVenta), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);

//        }    
//        else{
//            Mensaje("Usuario no autorizado");
//        }
    }//GEN-LAST:event_jMenuItem40ActionPerformed

    private void jMenuItem41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem41ActionPerformed

    //--------------------------------------------------------------------------------------
        /*     
         Valida el nivel de usuario 
         Administrador = 100
         Admin Area     = 90
         Supervisores   = 80
         Normal        =  70
         Visita =         60
         Cada menu del sistema tendra un valor de acuerdo al nivel que quiero que acceda.        
         */
        intNivelMnu = 100;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if (intNivelMnu > intNivelUsuario) {
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado");
            return;
        }
    //--------------------------------------------------------------------------------------

//        if(GetUsuarioAdministrador()){
        pfReportesMargen RepoMargen = new pfReportesMargen();
        pnPestanas.addTab("Reporte de Margen", RepoMargen);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(RepoMargen), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
//        }    
//        else{
//            Mensaje("Usuario no autorizado");
//        }
    }//GEN-LAST:event_jMenuItem41ActionPerformed

    private void jMenuItem47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem47ActionPerformed

       //--------------------------------------------------------------------------------------
        /*     
         Valida el nivel de usuario 
         Administrador = 100
         Admin Area     = 90
         Supervisores   = 80
         Normal        =  70
         Normal Vtas        =  71
         Visita =         60
         Cada menu del sistema tendra un valor de acuerdo al nivel que quiero que acceda.        
         */
        intNivelMnu = 70;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if (((intNivelMnu > intNivelUsuario) || ((ccosto_usr != 5) && (ccosto_usr != 6))) && (intNivelUsuario < 80)) {
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado");
            return;
        }
    //--------------------------------------------------------------------------------------   

        pfCCCotizaCliente cotizacli = new pfCCCotizaCliente();
        pnPestanas.addTab("Cotizacion Clientes", cotizacli);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(cotizacli), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
    }//GEN-LAST:event_jMenuItem47ActionPerformed

    private void jMenuItem51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem51ActionPerformed

        //--------------------------------------------------------------------------------------
        /*     
         Valida el nivel de usuario 
         Administrador = 100
         Admin Area     = 90
         Supervisores   = 80
         Normal        =  70
         Visita =         60
         Cada menu del sistema tendra un valor de acuerdo al nivel que quiero que acceda.        
         */
        intNivelMnu = 60;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if (intNivelMnu > intNivelUsuario) {
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado");
            return;
        }
    //--------------------------------------------------------------------------------------

        pfBuscaDoc buscaDoc = new pfBuscaDoc();
        pnPestanas.addTab("Buscar Documento", buscaDoc);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(buscaDoc), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
    }//GEN-LAST:event_jMenuItem51ActionPerformed

    private void jMenuItem55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem55ActionPerformed
        // TODO add your handling code here:

    //--------------------------------------------------------------------------------------
        /*     
         Valida el nivel de usuario 
         Administrador = 100
         Admin Area     = 90
         Supervisores   = 80
         Normal        =  70
         Normal Vtas        =  71
         Visita =         60
         Cada menu del sistema tendra un valor de acuerdo al nivel que quiero que acceda.        
         */
        intNivelMnu = 70;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if ((((intNivelMnu > intNivelUsuario) && (!fmMain.UsuarioBodega)) || (ccosto_usr != 7)) && (intNivelUsuario <= 80)) {
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado");
            return;
        }
    //--------------------------------------------------------------------------------------  

        //if(GetUsuarioAdministrador() || GetUsuarioBodega() ){
        AsignaUbicacion_Producto AsUbica = new AsignaUbicacion_Producto();
        pnPestanas.addTab("Ubicacion de Productos", AsUbica);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(AsUbica), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
        AsUbica.limpia_all();
        AsUbica.set_ubicacion(fmMain.BodegaTransito());
        AsUbica.enter_ubicacion();
//        }
//        else{
//            Mensaje("Usuario no autorizado");
//        }

    }//GEN-LAST:event_jMenuItem55ActionPerformed

    private void jMenuItem77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem77ActionPerformed
        pfIndicadoresInventario AsInd = new pfIndicadoresInventario();
        pnPestanas.addTab("Indicadores de INVENTARIO.", AsInd);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(AsInd), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
        //AsInd.limpia_all();
    }//GEN-LAST:event_jMenuItem77ActionPerformed

    private void jMenuItem80ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem80ActionPerformed
        pfAutorizaNCC ncc = new pfAutorizaNCC();
        pnPestanas.addTab("Autoriza NCC", ncc);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(ncc), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
    }//GEN-LAST:event_jMenuItem80ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        ExeSql Sql = new ExeSql();
        ExeSql Sql2 = new ExeSql();
        ExeSql Sql3 = new ExeSql();
        ResultSet Rs, Rs2;

        try {

            String Query = "SELECT sku, cantidad FROM ctacteprvdet\n"
                    + "WHERE tipdocto IN ('NPP') AND nrodocto = 48";

            Rs = Sql.Select(Query);

            if (Sql.GetRowCount() > 0) {

                while (Rs.next()) {

                    String sku = Rs.getString("sku");
                    double cant = Rs.getDouble("cantidad");

                    String Query2 = "select sku FROM inventario_sala \n"
                            + "WHERE sku ='" + sku.trim() + "'";
                    Rs2 = Sql2.Select(Query2);

                    if (Sql2.GetRowCount() > 0) {

                        Sql3.ExeSql("UPDATE inventario_sala SET \n"
                                + "stock = 0, \n"
                                + "ocp = " + cant + " \n"
                                + "WHERE sku ='" + sku.trim() + "'");

                        Sql3.Commit();
                    }
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(fmMain.class.getName()).log(Level.SEVERE, null, ex);
            Sql3.Rollback();
        } finally {

            Sql3.Close();

        }

    }//GEN-LAST:event_jButton1ActionPerformed


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        muestraDatos();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        ExeSql Sql = new ExeSql();
        ExeSql Sql2 = new ExeSql();
        ExeSql Sql3 = new ExeSql();
        ResultSet Rs, Rs2;

        try {

            String Query = "SELECT ctp.sku, mt.cant FROM ctacteprvdet ctp\n"
                    + "LEFT JOIN mt_productos mt ON ctp.sku = mt.sku\n"
                    + "WHERE mt.ubicacion IN ('SAL.1001.1') AND ctp.tipdocto IN ('NPP') AND ctp.nrodocto = 48";

            Rs = Sql.Select(Query);

            if (Sql.GetRowCount() > 0) {

                while (Rs.next()) {

                    String sku = Rs.getString("sku");

                    Sql3.ExeSql("UPDATE mt_productos SET \n"
                            + "cant = 0 "
                            + "WHERE sku = '" + sku.trim() + "' AND ubicacion IN ('SAL.1001.1') ");

                    Sql3.Commit();
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(fmMain.class.getName()).log(Level.SEVERE, null, ex);
            Sql3.Rollback();
        } finally {

            Sql3.Close();

        }


    }//GEN-LAST:event_jButton5ActionPerformed

    private void MnReportePreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnReportePreciosActionPerformed

        //--------------------------------------------------------------------------------------
        /*     
         Valida el nivel de usuario 
         Administrador = 100
         Admin Area     = 90
         Supervisores   = 80
         Normal        =  70
         Visita =         60
         Cada menu del sistema tendra un valor de acuerdo al nivel que quiero que acceda.        
         */
        intNivelMnu = 100;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if (intNivelMnu > intNivelUsuario) {
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado");
            return;
        }
    //--------------------------------------------------------------------------------------

//        if(GetUsuarioAdministrador()){
        pfReportePrecio RepoPrecio = new pfReportePrecio();
        pnPestanas.addTab("Reporte de Precios", RepoPrecio);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(RepoPrecio), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
//        }    
//        else{
//            Mensaje("Usuario no autorizado");
//        }


    }//GEN-LAST:event_MnReportePreciosActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

//        ExeSql Sql = new ExeSql();
//        ExeSql Sql2 = new ExeSql();
//        ExeSql Sql3 = new ExeSql();
//        ResultSet Rs,Rs2;
//        
//        try {
//            
//            String Query = "SELECT sku, sum(cant) as cantidad FROM mt_productos\n" +
//                            "WHERE ubicacion like '%SAL.%'\n" +
//                            "GROUP BY sku";
//            
//            Rs = Sql.Select(Query);
//                        
//            if (Sql.GetRowCount() > 0){
//           
//                while (Rs.next()){
//                
//                    String sku = Rs.getString("sku");
//                    double cant = Rs.getDouble("cantidad");
//                    
//                    String Query2 = "select sku FROM inventario_sala \n" +
//                                    "WHERE sku ='"+sku.trim()+"' AND stock <> "+cant;
//                    Rs2 = Sql2.Select(Query2);
//                    
//                    
//                    if (Sql2.GetRowCount() > 0){
//                    
//                    
//                        Sql3.ExeSql("UPDATE inventario_sala SET \n" +
//                                    "stock = "+cant + " \n"+
//                                    "WHERE sku ='"+sku.trim()+"'");
//                    
//                        Sql3.Commit();
//                   } 
//                }
//                
//            }
//        
//        
//         } catch (SQLException ex) {
//            Logger.getLogger(fmMain.class.getName()).log(Level.SEVERE, null, ex);
//            Sql3.Rollback();
//        }finally {
//        
//            Sql3.Close();
//        
//        } 
//        
        int numero = 0;
        String folio = "";

        for (int i = 0; i < 5; i++) {

            numero = (int) (Math.random() * 9) + 1;

            folio = folio + String.valueOf(numero);

        }

        System.out.println("EL Resultado ES :" + folio);


    }//GEN-LAST:event_jButton3ActionPerformed

    private void mnFoliosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnFoliosActionPerformed

          //--------------------------------------------------------------------------------------
            /*     
         Valida el nivel de usuario 
         Administrador = 100
         Admin Area    = 90
         Supervisores  = 80
         Normal        = 70
         Normal Vtas   = 71
         Visita        = 60
         Cada menu del sistema tendra un valor de acuerdo al nivel que quiero que acceda.        
         */
        intNivelMnu = 100;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if (((intNivelMnu > intNivelUsuario) || (ccosto_usr != 4)) && (intNivelUsuario <= 80)) {
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado");
            return;
        }
    //--------------------------------------------------------------------------------------           

        jdFolios Folios = new jdFolios(null, true);
        Folios.setTitle("Folios");
        Folios.setLocationRelativeTo(null);
        Folios.setVisible(true);


    }//GEN-LAST:event_mnFoliosActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        ExeSql Sql = new ExeSql();
        ExeSql Sql3 = new ExeSql();
        ResultSet Rs;

        try {

            String Query = "SELECT sku FROM inventario2 ";

            Rs = Sql.Select(Query);

            if (Sql.GetRowCount() > 0) {

                while (Rs.next()) {

                    String sku = Rs.getString("sku");

                    Sql3.ExeSql("INSERT INTO producto_valores (sku) \n"
                            + "VALUES ('" + sku.trim() + "')");

                    Sql3.Commit();

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(fmMain.class.getName()).log(Level.SEVERE, null, ex);
            Sql3.Rollback();
        } finally {

            Sql3.Close();

        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void ajustePrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajustePrecioActionPerformed

        pfAjustePrecio AjustePrecio = new pfAjustePrecio();

        pnPestanas.addTab("Reporte Ajuste Precios", AjustePrecio);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(AjustePrecio), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);

    }//GEN-LAST:event_ajustePrecioActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

           // TODO add your handling code here:
    //--------------------------------------------------------------------------------------
        /*     
         Valida el nivel de usuario 
         Administrador = 100
         Admin Area     = 90
         Supervisores   = 80
         Normal        =  70
         Normal Vtas        =  71
         Visita =         60
         Cada menu del sistema tendra un valor de acuerdo al nivel que quiero que acceda.        
         */
        intNivelMnu = 70;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if ((((intNivelMnu > intNivelUsuario) && (!fmMain.UsuarioBodega)) || (ccosto_usr != 7)) && (intNivelUsuario <= 80)) {
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado");
            return;
        }
    //--------------------------------------------------------------------------------------  

        Movimiento_Productos2 MueveProducto = new Movimiento_Productos2();
        pnPestanas.addTab("Movimiento de Productos", MueveProducto);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(MueveProducto), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
//        MueveProducto.limpia_all();
//        MueveProducto.set_ubicacion(fmMain.Rack1());
//        MueveProducto.enter_ubicacion();


    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

            // TODO add your handling code here:
    //--------------------------------------------------------------------------------------
        /*     
         Valida el nivel de usuario 
         Administrador = 100
         Admin Area     = 90
         Supervisores   = 80
         Normal        =  70
         Normal Vtas        =  71
         Visita =         60
         Cada menu del sistema tendra un valor de acuerdo al nivel que quiero que acceda.        
         */
        intNivelMnu = 70;
        intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        if ((((intNivelMnu > intNivelUsuario) && (!fmMain.UsuarioBodega)) || (ccosto_usr != 7)) && (intNivelUsuario <= 80)) {
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado");
            return;
        }
    //--------------------------------------------------------------------------------------  

        //if(GetUsuarioAdministrador() || GetUsuarioBodega() ){
        pfNPP_Pendientes Pendientes = new pfNPP_Pendientes();
        pnPestanas.addTab("Notas Pendientres", Pendientes);
        PanelTab btc = new PanelTab(pnPestanas, 0);
        pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(Pendientes), btc);
        pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
        Pendientes.limpia_all();
        Pendientes.set_ubicacion(fmMain.BodegaTransito());
        Pendientes.enter_ubicacion();
//        }
//        else{
//            Mensaje("Usuario no autorizado");
//        }


    }//GEN-LAST:event_jMenuItem2ActionPerformed

    public static String FormatoNumero3(double Numero) {
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        DecimalFormat formateador = new DecimalFormat("#0.00", simbolo);
        return formateador.format(Numero);
    }

    public static String FormatoTotal3(double Numero) {
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        DecimalFormat formateador = new DecimalFormat("#", simbolo);
        return formateador.format(Numero);
    }

    public static String FormatoNumero4(double Numero) {
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        DecimalFormat formateador = new DecimalFormat("#0.00000", simbolo);
        return formateador.format(Numero);
    }

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(fmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fmMain().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AbrirCliente;
    private javax.swing.JMenuItem AbrirProducto;
    private javax.swing.JMenuItem AbrirProveedor;
    private javax.swing.JMenuItem AjustedeStock;
    private javax.swing.JMenuItem BuscarCliente;
    private org.jdesktop.swingx.JXMonthView Calendario;
    private javax.swing.JMenu Control;
    private javax.swing.JMenuItem MnReportePrecios;
    private javax.swing.JMenu MnuArchivo;
    private javax.swing.JMenu MnuClientes;
    private javax.swing.JMenu MnuProductos;
    private javax.swing.JMenu MnuProveedores;
    private javax.swing.JMenu MnuVentas;
    private javax.swing.JMenuItem NCC;
    private javax.swing.JMenuItem OCP;
    private javax.swing.JMenuItem ReporteOCP;
    private javax.swing.JMenuItem ajustePrecio;
    private javax.swing.JLabel fecha_version;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem40;
    private javax.swing.JMenuItem jMenuItem41;
    private javax.swing.JMenuItem jMenuItem44;
    private javax.swing.JMenuItem jMenuItem47;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem51;
    private javax.swing.JMenuItem jMenuItem52;
    private javax.swing.JMenuItem jMenuItem55;
    private javax.swing.JMenuItem jMenuItem62;
    private javax.swing.JMenuItem jMenuItem73;
    private javax.swing.JMenuItem jMenuItem74;
    private javax.swing.JMenuItem jMenuItem77;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem80;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator16;
    private javax.swing.JPopupMenu.Separator jSeparator17;
    private javax.swing.JPopupMenu.Separator jSeparator18;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JLabel lbFAC2;
    private javax.swing.JLabel lbFEC2;
    private javax.swing.JLabel lbGDC2;
    private javax.swing.JLabel lbNCC2;
    private javax.swing.JLabel lbNDC2;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbTituloDocumentos;
    private javax.swing.JLabel lblServidor;
    private javax.swing.JMenuItem mnFolios;
    private javax.swing.JMenuItem mnuControlInventario;
    private javax.swing.JMenuItem mnuUsuarios;
    private javax.swing.JPanel panelDatos2;
    public static javax.swing.JTabbedPane pnPestanas;
    // End of variables declaration//GEN-END:variables
}
