package PanelForm;

import Conexion.ExeSql;
import Conexion.ExeSqlLuvaly;
import Dialogos.*;
import Formularios.fmMain;
import static PanelForm.pfBuscaDoc.BuscaArchivos;
import static Utilidades.DocElect.carpeta;
import Utilidades.Ftp;
import static Utilidades.Ftp.busca;
import Utilidades.GeneraDTE;
import Utilidades.GeneraDTE_FTP;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class pfGDCliente extends javax.swing.JPanel {
    String RutMaster;
    int Tipo; // 0::Nuevo    1:Abrir
    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();

    private void CargaCiudad() {
        ExeSql sql = new ExeSql();
        ResultSet rs = null;
        String query = "select \n" +
                        "case \n" +
                        "when (blue.localidad) is not null then (blue.localidad)\n" +
                        "when (ps.nombre) is not null then (ps.nombre)\n" +
                        "when (pt.ciudad) is not null then (pt.ciudad)\n" +
                        "end as ciudad\n" +
                        "from pd_zonageografica_blue blue\n" +
                        "full outer join pd_ciudades_samex ps on ps.nombre = blue.localidad\n" +
                        "full outer join pd_ciudades_tnt pt on pt.ciudad = blue.localidad \n" +
                        "group by blue.localidad, ps.nombre, pt.ciudad\n" +
                        "order by blue.localidad, ps.nombre, pt.ciudad";
        DefaultComboBoxModel model = (DefaultComboBoxModel) txCiudadDespacho.getModel();
        if(model.getSize()>0){
            model.removeAllElements();
        }
        DefaultComboBoxModel model2 = (DefaultComboBoxModel) txComunaDespacho.getModel();
        if(model2.getSize()>0){
            model2.removeAllElements();
        }
        try {
            rs = sql.Select(query);
            for(int i = 0; rs.next(); i++){
                model.addElement(rs.getString("ciudad"));
                model2.addElement(rs.getString("ciudad"));
            }
            model.addElement("");
            model2.addElement("");
        } catch (SQLException ex) {
            Logger.getLogger(pfGDCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            sql.Close();
        }
        
    }
    private enum Columnas{Sku,Nombre,UM,Cantidad,Unitario,Total,UniReal};
    String OrdenOC;
    int PesoCorreccion=0;
    int modificarCliente = 0;
    int LimiteDireccion =80;
    String TipDocto_Master="GDC";
    private final String  StTipoGDC = "GDC";
    private final String StTipoFAT = "FAT";
    

    public pfGDCliente() {
        initComponents();
        SetTipo(-1);
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        Grilla.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        Grilla.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        Grilla.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        btAgregar.setVisible(false);
        btContacto.setVisible(false);
//        btEliminar.setVisible(false);
        btEmitir.setEnabled(false);
        lbBloqueo.setVisible(false);
        btEmitir.setVisible(false);
        btEmiteMan.setVisible(false);
        txRut.setEnabled(false);
        txRut.setEditable(false);
        btIr.setEnabled(false);
        btEmiteTrans.setEnabled(false);
        bt_MermaStock.setEnabled(false);
        lbTransporte.setVisible(false);
        //Despliega boton para emitir una Guian Manualmente
        if (fmMain.GetUsuarioAdministrador()){
            btEmiteMan.setVisible(true);
        }
        CargaCiudad();
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnMenu = new javax.swing.JPanel();
        btNuevo = new javax.swing.JButton();
        btGuardar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btEmitir = new javax.swing.JButton();
        btAbrir = new javax.swing.JButton();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        btCorrigePeso = new javax.swing.JButton();
        btActualizarDatos = new javax.swing.JToggleButton();
        btEmiteMan = new javax.swing.JButton();
        btAbrir_FAT = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Grilla = new javax.swing.JTable();
        btEliminar = new javax.swing.JButton();
        btAgregar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txExento = new javax.swing.JTextField();
        txNeto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txIva = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txTotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txImpEspecifico = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txRut = new javax.swing.JTextField();
        txDv = new javax.swing.JTextField();
        btIr = new javax.swing.JButton();
        lbBloqueo = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbTipoDespacho = new javax.swing.JComboBox();
        txNombre = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txGiro = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txDireccion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txComuna = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txDirDespacho = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txContacto = new javax.swing.JTextField();
        txidContacto = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        cbCodigoOc = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbNroOrden = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lbNroDocto = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txEstado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        dtEmision = new org.jdesktop.swingx.JXDatePicker();
        btPDF = new javax.swing.JButton();
        lbTransporte = new javax.swing.JLabel();
        txCiudad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btContacto = new javax.swing.JButton();
        chk_Anticipado = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txObservaciones = new javax.swing.JTextArea();
        lbObs = new javax.swing.JLabel();
        bt_MermaStock = new javax.swing.JButton();
        btEmiteTrans = new javax.swing.JButton();
        txCiudadDespacho = new javax.swing.JComboBox<>();
        txComunaDespacho = new javax.swing.JComboBox<>();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        pnMenu.setBackground(new java.awt.Color(220, 215, 226));

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
        btGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarActionPerformed(evt);
            }
        });

        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/Cancel16.png"))); // NOI18N
        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/Pencil16.png"))); // NOI18N
        btEditar.setText("Editar");
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });

        btEmitir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/derecha16.png"))); // NOI18N
        btEmitir.setText("Emitir Guia");
        btEmitir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEmitirActionPerformed(evt);
            }
        });

        btAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/open16.png"))); // NOI18N
        btAbrir.setText("Abrir");
        btAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAbrirActionPerformed(evt);
            }
        });

        jXLabel1.setText("GUIA DE DESPACHO CLIENTE");
        jXLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        btCorrigePeso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/Peso16.png"))); // NOI18N
        btCorrigePeso.setText("Corrección");
        btCorrigePeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCorrigePesoActionPerformed(evt);
            }
        });

        btActualizarDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/Actualiza.png"))); // NOI18N
        btActualizarDatos.setText("Actualizar Datos");
        btActualizarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActualizarDatosActionPerformed(evt);
            }
        });

        btEmiteMan.setText("Emision Manual");
        btEmiteMan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEmiteManActionPerformed(evt);
            }
        });

        btAbrir_FAT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/open16.png"))); // NOI18N
        btAbrir_FAT.setText("Abrir FAT");
        btAbrir_FAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAbrir_FATActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAbrir)
                .addGap(18, 18, 18)
                .addComponent(btCorrigePeso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btActualizarDatos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btEmiteMan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btAbrir_FAT, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(btEmitir)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btEditar)
                        .addComponent(btAbrir)
                        .addComponent(btCorrigePeso)
                        .addComponent(btActualizarDatos)
                        .addComponent(btEmiteMan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btEmitir)
                        .addComponent(btAbrir_FAT)))
                .addGap(7, 7, 7))
        );

        add(pnMenu);

        jPanel1.setBackground(new java.awt.Color(220, 215, 226));

        Grilla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "UM", "Cantidad", "V. Unitario", "Total Linea", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                GrillaMouseEntered(evt);
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
        }

        btEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos22/Cancel.png"))); // NOI18N
        btEliminar.setText("Eliminar");
        btEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEliminarActionPerformed(evt);
            }
        });

        btAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos22/add2.png"))); // NOI18N
        btAgregar.setText("Agregar");
        btAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAgregarActionPerformed(evt);
            }
        });

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

        jLabel17.setText("Imp. específico");

        txImpEspecifico.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txImpEspecifico.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txNeto)
                    .addComponent(txIva)
                    .addComponent(txExento, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(txTotal)
                    .addComponent(txImpEspecifico))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txExento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txNeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txImpEspecifico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Rut");

        txRut.setEnabled(false);
        txRut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txRutKeyPressed(evt);
            }
        });

        txDv.setEditable(false);

        btIr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos22/Go.png"))); // NOI18N
        btIr.setBorderPainted(false);
        btIr.setEnabled(false);
        btIr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIrActionPerformed(evt);
            }
        });

        lbBloqueo.setForeground(new java.awt.Color(204, 0, 0));
        lbBloqueo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/bloquear.png"))); // NOI18N
        lbBloqueo.setText("Cliente Bloqueado");

        jLabel18.setText("Venta");

        cbTipoDespacho.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Directo" }));
        cbTipoDespacho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoDespachoActionPerformed(evt);
            }
        });

        txNombre.setEnabled(false);
        txNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txNombreKeyTyped(evt);
            }
        });

        jLabel9.setText("Giro");

        txGiro.setEnabled(false);
        txGiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txGiroActionPerformed(evt);
            }
        });
        txGiro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txGiroKeyReleased(evt);
            }
        });

        jLabel16.setText("Dirección");

        txDireccion.setEnabled(false);
        txDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txDireccionActionPerformed(evt);
            }
        });
        txDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txDireccionKeyReleased(evt);
            }
        });

        jLabel7.setText("Comuna");

        txComuna.setEnabled(false);
        txComuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txComunaActionPerformed(evt);
            }
        });
        txComuna.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txComunaKeyReleased(evt);
            }
        });

        jLabel14.setText("Despacho");

        txDirDespacho.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txDirDespacho.setEnabled(false);
        txDirDespacho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txDirDespachoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txDirDespachoKeyTyped(evt);
            }
        });

        jLabel19.setText("ComunaD");

        jLabel20.setText("Ciudad.D");

        jLabel21.setText("Contacto");

        txContacto.setEditable(false);
        txContacto.setEnabled(false);

        txidContacto.setEditable(false);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        cbCodigoOc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCodigoOc.setSelectedIndex(-1);
        cbCodigoOc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCodigoOcActionPerformed(evt);
            }
        });

        jLabel3.setText("Código OC");

        jLabel4.setText("Nro Orden");

        cbNroOrden.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbNroOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNroOrdenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbCodigoOc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbNroOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCodigoOc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbNroOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("GUÍA DE DESPACHO");

        lbNroDocto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbNroDocto.setForeground(new java.awt.Color(255, 51, 51));

        jLabel5.setText("F. Emisión");

        txEstado.setEditable(false);

        jLabel6.setText("Estado");

        dtEmision.setEnabled(false);

        btPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/pdf.png"))); // NOI18N
        btPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPDFActionPerformed(evt);
            }
        });

        lbTransporte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbTransporte.setForeground(new java.awt.Color(204, 0, 0));
        lbTransporte.setText("Transporte");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNroDocto)
                .addGap(65, 65, 65)
                .addComponent(lbTransporte, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(dtEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lbNroDocto)
                    .addComponent(lbTransporte))
                .addGap(4, 4, 4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(dtEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15))
        );

        txCiudad.setEnabled(false);
        txCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txCiudadActionPerformed(evt);
            }
        });
        txCiudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txCiudadKeyReleased(evt);
            }
        });

        jLabel8.setText("Ciudad");

        jLabel2.setText("Nombre");

        btContacto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Ok.png"))); // NOI18N
        btContacto.setBorderPainted(false);
        btContacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btContactoActionPerformed(evt);
            }
        });

        chk_Anticipado.setText("Factura Anticipada");

        txObservaciones.setColumns(20);
        txObservaciones.setRows(5);
        jScrollPane2.setViewportView(txObservaciones);

        lbObs.setText("Observaciones Guia de Despacho");

        bt_MermaStock.setText("Devolucion y Pago");
        bt_MermaStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_MermaStockActionPerformed(evt);
            }
        });

        btEmiteTrans.setText("Emision Transporte");
        btEmiteTrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEmiteTransActionPerformed(evt);
            }
        });

        txCiudadDespacho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txComunaDespacho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(txRut, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txDv, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btIr, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbBloqueo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chk_Anticipado)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbTipoDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txComunaDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(txCiudadDespacho, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txDirDespacho))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(17, 17, 17)
                                .addComponent(txComuna, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txCiudad))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel21)
                                        .addGap(10, 10, 10)
                                        .addComponent(txContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txidContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(4, 4, 4)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txGiro, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
                                            .addComponent(txNombre)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbObs)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 370, Short.MAX_VALUE)
                                .addComponent(btAgregar)
                                .addGap(58, 58, 58)
                                .addComponent(btEliminar))
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btEmiteTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_MermaStock, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(txRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btIr)
                                    .addComponent(txDv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel18)
                                        .addComponent(cbTipoDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbBloqueo)
                                        .addComponent(chk_Anticipado))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txComuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(txCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txDirDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20)
                                    .addComponent(txCiudadDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txComunaDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txGiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel21))
                                    .addComponent(txidContacto)
                                    .addComponent(btContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btEliminar)
                                            .addComponent(btAgregar))
                                        .addGap(10, 10, 10))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbObs)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btEmiteTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_MermaStock, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents
    static public int GetCol(String Col){
        return Columnas.valueOf(Col).ordinal();
    }

//--------------------------------------------------------------------------------
// Funcion SET DE TIPOS
//--------------------------------------------------------------------------------
    private void SetTipo(int ElTipo){
        // NADA
        if(ElTipo==-1){
          fmMain.SetEstado(fmMain.pnPestanas.getSelectedIndex(),0);
          Habilita(false);
          Edicion(false);
          Limpia();
          txRut.setEnabled(true);
          txRut.setEditable(true);
          btIr.setEnabled(true);
          txRut.requestFocus();
          btCancelar.setEnabled(false);
          btGuardar.setEnabled(false);
          btCorrigePeso.setEnabled(false);
          btNuevo.setEnabled(true);
          btEditar.setEnabled(false);
          btEmitir.setEnabled(false);
          txEstado.setText("Sin Emitir");
          
          Tipo=-1;
          
        }
        // NUEVA ORDEN
        else if(ElTipo==1){
            btGuardar.setEnabled(true);
            btCorrigePeso.setEnabled(true);
            btCancelar.setEnabled(true);
            btNuevo.setEnabled(false);
            btEditar.setEnabled(false);
            btAbrir.setEnabled(false);
            btAbrir_FAT.setEnabled(false);
            btEmitir.setEnabled(false);
            
            CargaCodOc(RutMaster);
            Habilita(true); 
            Edicion(true);
            
            
            fmMain.SetEstado(fmMain.pnPestanas.getSelectedIndex(),1);  
            
            txRut.requestFocus();
            Tipo=1;
        }
        else if(ElTipo==2){

          fmMain.SetEstado(fmMain.pnPestanas.getSelectedIndex(),0);

          btNuevo.setEnabled(true);
          btGuardar.setEnabled(false);
          btCorrigePeso.setEnabled(false);
          btCancelar.setEnabled(false);
          btEmitir.setEnabled(true);
          Habilita(true);
          Edicion(false);
          txRut.setEditable(true);
          Tipo=2;
        }
        else if(ElTipo==3){
          Habilita(true); 
            Edicion(true);
            
            btGuardar.setEnabled(true);
            btCorrigePeso.setEnabled(true);
            btCancelar.setEnabled(true);
            btNuevo.setEnabled(false);
            btAbrir.setEnabled(false);
            btAbrir_FAT.setEnabled(false);
            btEmitir.setEnabled(false);  
            txDirDespacho.setEditable(true);
            txDirDespacho.setEnabled(true);
            txComunaDespacho.setEnabled(true);
            txCiudadDespacho.setEnabled(true);
            txContacto.setEnabled(true);
            txContacto.setEditable(true);
            txidContacto.setEnabled(true);
            txidContacto.setEditable(true);
            txEstado.setEnabled(true);
            txEstado.setEditable(true);
            Tipo=3;
        }
    }
    
    private void FindeAgno(){
        
        if(fmMain.GetFacNewYear()==1){
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                Date Fecha=df.parse("2015/12/31");
                dtEmision.setDate(Fecha);
                //dtEmision.setEditable(false);
                System.out.println("NewYear");
            } catch (Exception e) {
                System.out.println("Error New Year");
            }
            
        }
        
    }
    
    public void NuevaGuia(String Rut, String CodigoOc, String Numero){
        btNuevo.doClick();
        txRut.setText(Rut);
        btIr.doClick();
        int posOrden =0, posCodigoOC=0;
        
        for(int i=0 ; i < cbCodigoOc.getItemCount();i++){
            cbCodigoOc.setSelectedIndex(i);
            
            if(cbCodigoOc.getSelectedItem().toString().equals(CodigoOc) ){
                CargaDatosCodigoOC();
                posCodigoOC=i;
                break; 
            }
               
        }
    
        for(int i=0 ; i < cbNroOrden.getItemCount();i++){
        
            cbNroOrden.setSelectedIndex(i);
            cbNroOrden.getSelectedItem().toString();
            
            if(cbNroOrden.getSelectedItem().toString().equals(Numero) ){
                posOrden= i;
               break; 
            }   
        }
    
        cbCodigoOc.setSelectedIndex(posCodigoOC);
        cbNroOrden.setSelectedIndex(posOrden);
    
    
 }
//--------------------------------------------------------------------------------
// EDICION
//--------------------------------------------------------------------------------
    private void Edicion(boolean Estado){

        txRut.setEditable(Estado);
        txDv.setEditable(Estado);
        txComunaDespacho.setEnabled(Estado);
        txCiudadDespacho.setEnabled(Estado);
        txObservaciones.setEnabled(Estado);
        txNeto.setEditable(Estado);
        txExento.setEditable(Estado);
        txIva.setEditable(Estado);
        txTotal.setEditable(Estado);
        btAgregar.setEnabled(Estado);
        btEliminar.setEnabled(Estado);
        chk_Anticipado.setEnabled(Estado);
    
    }
//--------------------------------------------------------------------------------
// LIMPIA
//--------------------------------------------------------------------------------
    private void Limpia(){
        DefaultComboBoxModel dfCm = new DefaultComboBoxModel();
        DefaultTableModel   dfTm = (DefaultTableModel) Grilla.getModel();
    
        txRut.setText("");
        txNombre.setText("");
        txDv.setText("");
        txNeto.setText("");
        txExento.setText("");
        txIva.setText("");
        txTotal.setText("");
        txGiro.setText("");
        txComuna.setText("");
        txCiudad.setText("");
        txDireccion.setText("");
        lbNroDocto.setText("");
        txEstado.setText("");
        txComunaDespacho.setSelectedItem("");
        txCiudadDespacho.setSelectedItem("");
        txContacto.setText("");
        dtEmision.setDate(new Date());
        txDirDespacho.setText("");
        cbCodigoOc.setModel(dfCm);
        cbTipoDespacho.setSelectedIndex(-1);
        chk_Anticipado.setSelected(false);
    
    // Deja fondo de color blanco.
        txDirDespacho.setBackground(Color.white);
        txComunaDespacho.setBackground(Color.white);

//    cbContacto.setModel(dfCm);
    
        
        while(dfTm.getRowCount()>0)
            dfTm.removeRow(0);
    //chbPrioridad.
    
        dtEmision.setDate(null);
        lbTransporte.setVisible(false);
        txObservaciones.setText("");

    }    
//--------------------------------------------------------------------------------
// HABILITA
//--------------------------------------------------------------------------------
    private void Habilita(boolean Estado){
    
        cbCodigoOc.setEnabled(Estado);
        cbNroOrden.setEnabled(Estado);

        btAgregar.setEnabled(Estado);
        btEliminar.setEnabled(Estado);

        txRut.setEnabled(Estado);
        txDv.setEnabled(Estado);
  
        txNeto.setEnabled(Estado);
        txExento.setEnabled(Estado);
        txIva.setEnabled(Estado);
        txTotal.setEnabled(Estado);
        btIr.setEnabled(Estado);
        txEstado.setEnabled(Estado);
        btActualizarDatos.setEnabled(Estado);
        cbTipoDespacho.setEnabled(Estado);
    
        txidContacto.setEnabled(Estado);
        txImpEspecifico.setEnabled(Estado);
        txContacto.setEnabled(Estado);
        btContacto.setVisible(Estado);
        btContacto.setEnabled(Estado);
        txDirDespacho.setEditable(false);
        txDirDespacho.setEnabled(false);
        txComunaDespacho.setEnabled(false);
        txCiudadDespacho.setEnabled(false);
        txContacto.setEnabled(false);
        txContacto.setEditable(false);
        txidContacto.setEnabled(false);
        txidContacto.setEditable(false);
        txEstado.setEnabled(false);
        txEstado.setEditable(false);
        chk_Anticipado.setEnabled(Estado);
    
        txObservaciones.setEnabled(false);
    }
    
    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        if(fmMain.GetEstado(fmMain.pnPestanas.getSelectedIndex())==0){
            Limpia();
            Habilita(false);
            btGuardar.setEnabled(false);
            btCancelar.setEnabled(false);
            btNuevo.setEnabled(true);
            btEditar.setEnabled(false);
            btAbrir.setEnabled(true);
            btAbrir_FAT.setEnabled(true);
            lbBloqueo.setVisible(false);
        }
        
        else if(fmMain.OkCancel("¿Seguro de cancelar?")==JOptionPane.OK_OPTION){
            Limpia();
            Habilita(false);
            btGuardar.setEnabled(false);
            btCancelar.setEnabled(false);
            btNuevo.setEnabled(true);
            btEditar.setEnabled(false);
            btAbrir.setEnabled(true);
            btAbrir_FAT.setEnabled(true);
            lbBloqueo.setVisible(false);
        }
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoActionPerformed
        Tipo=1;
        Limpia();
        Habilita(false);
        Edicion(false);
        txRut.setEnabled(true);
        txRut.setEditable(true);

        btIr.setEnabled(true);
        btAbrir.setEnabled(false);
        btAbrir_FAT.setEnabled(false);
        btGuardar.setEnabled(true);
        btCorrigePeso.setEnabled(true);
        btCancelar.setEnabled(true);
        txRut.requestFocus();
         cbTipoDespacho.setSelectedIndex(0);
    }//GEN-LAST:event_btNuevoActionPerformed
//------------------------------------------------------------------------------
// Carga Cliente
//------------------------------------------------------------------------------
    private boolean CargaCliente(String Rut) {
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        try {
            Rs = Sql.Select("select rut,dv,nombre\n"
                    + "from cliente\n"
                    + "where rut=" + Rut);
            Rs.next();
            txRut.setText(Rs.getString("Rut"));
            txDv.setText(Rs.getString("dv"));
            txNombre.setText(Rs.getString("nombre").trim());
            dtEmision.setDate(new Date());
            RutMaster = Rs.getString("Rut");
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            Sql.Close();
        }
    }
//------------------------------------------------------------------------------
// Carga Codigos OC
//------------------------------------------------------------------------------
    private void CargaCodOc(String Rut){
        ExeSql  Sql = new ExeSql();
        ResultSet Rs;
        DefaultComboBoxModel cbMd = new DefaultComboBoxModel();
        cbCodigoOc.setModel(cbMd);
        try {
            Rs = Sql.Select("select distinct(codigo_oc) as codigo_oc from clicontacto where rut = " + Rut);
            while (Rs.next()) {
                cbMd.addElement(Rs.getString("codigo_oc").trim());
            }
        } catch (Exception e) {
            System.out.println("e");
        } finally{
            Sql.Close();
        }
    }
    
    private void btEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEliminarActionPerformed
        if(!Grilla.getValueAt(Grilla.getSelectedRow(), 0).toString().isEmpty())
        if(JOptionPane.showConfirmDialog(null, "¿Desea Eliminar el registro?") == JOptionPane.YES_OPTION){
            DefaultTableModel tbMd = (DefaultTableModel) Grilla.getModel();
            tbMd.removeRow(Grilla.getSelectedRow());
            Sumador();
        }
    }//GEN-LAST:event_btEliminarActionPerformed

    private void btAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAgregarActionPerformed
        DefaultTableModel tbModel = (DefaultTableModel) Grilla.getModel();
        if(tbModel.getRowCount()<50){
            jdOCCAgregaProducto AgregaProducto = new jdOCCAgregaProducto(null,true);
            AgregaProducto.setLocationRelativeTo(null);
            AgregaProducto.setTitle("Agragar Producto");
            AgregaProducto.setVisible(true);
            tbModel.addRow(AgregaProducto.GetFila());
        }
        else {
            JOptionPane.showMessageDialog(null, "No se puede agregar más de 50 productos");
        }
//        Sumador();

    }//GEN-LAST:event_btAgregarActionPerformed

    private void btAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAbrirActionPerformed
        Habilita(false);
        Edicion(false);
        Limpia();
        txRut.setEnabled(true);
        txRut.setEditable(true);
        btIr.setEnabled(true);
        btCancelar.setEnabled(true);
        txDirDespacho.setEditable(false);
        txRut.requestFocus();
        btEditar.setEnabled(false);
        SetTipo(2);
        TipDocto_Master =StTipoGDC;
        lbTransporte.setVisible(false);
    }//GEN-LAST:event_btAbrirActionPerformed
    
    public void AbrirGuia(String Rut,String Numero, String TipDocto){
        ResultSet Rs, Rs2;
        ExeSql Sql = new ExeSql();
        boolean booEmitida=false;
        
        DefaultTableModel TableModel = (DefaultTableModel) Grilla.getModel();
        try{//Carga Detalles
        
            Sql.Commit();
        
            while(TableModel.getRowCount()>0)
                  TableModel.removeRow(0);
            
            Rs = Sql.Select("select c.rut,c.nrodocto,c.nrodocorigen,c.estado as estadoint, c.femision,c.occh,c.contacto,c.tipodespacho, "
                    +       " c.totalexento,c.totalafecto,c.totaliva,totalimpespecifico,c.totaldocto, cc.giro,"
                    +       " cc.direccion,cc.ciudad,cc.comuna,cc.region,c.estado, c.direccion_despacho, c.comuna_despacho, c.ciudad_despacho, anticipado, c.observacion " +
                            " from ctactecli c left join clicontacto cc " +
                            " on c.rut=cc.rut and c.nrodocorigen=cc.codigo_oc " +
                            " WHERE c.rut=" + Rut + " " +
                            " AND c.tipdocto='" + TipDocto + "'" +
                            " AND c.nrodocto=" + Numero);
            Rs.next();
            lbNroDocto.setText(Numero);
            cbTipoDespacho.setSelectedIndex(Rs.getInt("tipodespacho"));
            
            
            //BLOQUEA BOTÓN EDITAR
            
            if(Rs.getInt("estado")==1)
            {
//                btEditar.setVisible(false);
                booEmitida=true;
            }else
            {
                btEditar.setVisible(true);
                booEmitida=false;
            }
            
            
            if (Rs.getInt("anticipado")==1){
                chk_Anticipado.setSelected(true);}
            else{
                chk_Anticipado.setSelected(false);
            }
            
            
            dtEmision.setDate(Rs.getDate("femision"));
            
            txGiro.setBackground(Color.white);
            txDireccion.setBackground(Color.white);
            txCiudad.setBackground(Color.white);
            txComuna.setBackground(Color.white);
            txDirDespacho.setBackground(Color.white);
            txComunaDespacho.setBackground(Color.white);
            txCiudadDespacho.setBackground(Color.white);
            
            
            try{ txGiro.setText(Rs.getString("giro").trim()); txGiro.setBackground(Color.white);} catch (Exception e) {txGiro.setText(""); txGiro.setBackground(Color.red);}
            try{ txDireccion.setText(Rs.getString("direccion").trim()); txDireccion.setBackground(Color.white);} catch (Exception e) {txDireccion.setText("");txDireccion.setBackground(Color.red);}
            try{ txCiudad.setText(Rs.getString("ciudad").trim());txCiudad.setBackground(Color.white);}catch (Exception e) {txCiudad.setText("");txCiudad.setBackground(Color.red);}
            try{ txComuna.setText(Rs.getString("comuna").trim());txComuna.setBackground(Color.white);}catch (Exception e) {txComuna.setText("");txComuna.setBackground(Color.red);}
            try{ txDirDespacho.setText(Rs.getString("direccion_despacho").trim()); txDirDespacho.setBackground(Color.white);} catch (Exception e) {txDirDespacho.setText("");txDirDespacho.setBackground(Color.red);}
            try{ txComunaDespacho.setSelectedItem(Rs.getString("comuna_despacho").trim());txComunaDespacho.setBackground(Color.white);}catch (Exception e) {txComunaDespacho.setSelectedItem("");txComunaDespacho.setBackground(Color.red);}
            try{ txCiudadDespacho.setSelectedItem(Rs.getString("ciudad_despacho").trim());txCiudadDespacho.setBackground(Color.white);}catch (Exception e) {txCiudadDespacho.setSelectedItem("");txCiudadDespacho.setBackground(Color.red);}

            btEmitir.setEnabled(true);
            btPDF.setVisible(false);
            
            txNeto.setText(fmMain.FormatoTotal(Rs.getDouble("totalafecto")));
            txExento.setText(fmMain.FormatoTotal(Rs.getDouble("totalexento")));
            txIva.setText(fmMain.FormatoTotal(Rs.getDouble("totaliva")));
            txImpEspecifico.setText(fmMain.FormatoTotal(Rs.getDouble("totalimpespecifico")));
            txTotal.setText(fmMain.FormatoTotal(Rs.getDouble("totaldocto")));
            
            switch(Rs.getInt("estado")){
                case 0: txEstado.setText("Sin Emitir"); break;
                case 1: txEstado.setText("Emitida");break;
            }
                String qry = "SELECT trim(cp.nombre) || '--' || trim(cp.fono) || '--' || trim(cp.celular) Contacto \n" +
                    " FROM ctactecli c\n" +
                    " left join clicontactopersonas cp\n" +
                    " on c.contacto = cp.id \n" +
                    " where \n" +
                    " cp.id =" + Rs.getInt("contacto") + " and c.tipdocto ='" + TipDocto + "' and nrodocto = " + Numero;
                    Rs2 = Sql.Select(qry);
                    Rs2.next();
            txContacto.setText(Rs2.getString("Contacto"));
            
            // trae la observacio guardada
            txObservaciones.setText(Rs.getString("observacion"));
            
            
            
            if (txEstado.getText().trim().equals("Emitida")){
                
                txDirDespacho.setText(Rs.getString("direccion_despacho"));
                txComunaDespacho.setSelectedItem(Rs.getString("comuna_despacho").trim());
                txCiudadDespacho.setSelectedItem(Rs.getString("ciudad_despacho").trim());
            }

            DefaultComboBoxModel cbMd = new DefaultComboBoxModel();
            cbMd.addElement(Rs.getString("nrodocorigen").trim()); 
            cbCodigoOc.setModel(cbMd);

            DefaultComboBoxModel cbMd2 = new DefaultComboBoxModel();
            cbMd2.addElement(Rs.getString("occh").trim()); 
            cbNroOrden.setModel(cbMd2);
            OrdenOC = Rs.getString("occh").trim();
            

            
        } catch (Exception e) {
            System.out.println(e);
            }


         fmMain.LimpiaGrilla(TableModel);   
        
        try {
            ExeSqlLuvaly luvsql = new ExeSqlLuvaly();
            ResultSet nombre = null;
            Rs = Sql.Select("select d.sku,p.nombre,u.um,d.cantidad,d.valorunitario,d.totallinea \n"
                    + "from ctacteclidet d\n"
                    + "left join producto p\n"
                    + "on d.sku=p.sku\n"
                    + "left join par_unidad u\n"
                    + "on p.unidad=u.codigo\n"
                    + "where d.rut=" + Rut + "\n"
                    + "and d.tipdocto='" + TipDocto + "'\n"
                    + "and d.nrodocto=" + Numero);
            
            while (Rs.next()) {

                nombre = luvsql.Select("select p.nombre, u.um from producto p \n"
                + "LEFT JOIN par_unidad u ON u.codigo=p.unidad\n "
                + "where p.sku = '"+Rs.getString("sku").trim()+"'");
                nombre.next();
                TableModel.addRow(new Object[]{
                    Rs.getString("sku"),
                    nombre.getString("nombre"),
                    nombre.getString("um"),
                    fmMain.FormatoNumero(Rs.getDouble("cantidad")),
                    fmMain.FormatoNumero(Rs.getDouble("valorunitario")),
                    fmMain.FormatoNumero(Rs.getDouble("totallinea")),
                    Rs.getDouble("valorunitario")});
            }
            Sumador();
             if (TipDocto.equals(StTipoFAT)){
                lbTransporte.setVisible(true);
                
                if (booEmitida)
                {
                    btEmiteTrans.setEnabled(false);
                    bt_MermaStock.setEnabled(false);
                }
                else
                {
                    btEmiteTrans.setEnabled(true);
                    bt_MermaStock.setEnabled(true);
                }
                
                
             }
             else
             {
               lbTransporte.setVisible(false);
               btEmiteTrans.setEnabled(false);
               
             }
        } catch (Exception e) {
            System.out.println(e);
        } finally{
            Sql.Close();
        }
    }    
    
    private void CargaDatosCodigoOC() {
        ExeSql Sql = new ExeSql();
        ResultSet Rs, Rs1;
        DefaultComboBoxModel cbMd = new DefaultComboBoxModel();
        cbNroOrden.setModel(cbMd);

        try {
            // --Carga Datos de Cliente

            Rs = Sql.Select("select giro,direccion,ciudad,comuna,region,bloqueo\n"
                    + "from clicontacto\n"
                    + "where rut=" + txRut.getText()
                    + " and codigo_oc=" + cbCodigoOc.getSelectedItem().toString());

            Rs.next();
            
            if(Rs.getInt("bloqueo")==1){
                lbBloqueo.setVisible(true);
                btGuardar.setEnabled(false);
            }
            else{
                lbBloqueo.setVisible(false);
                btGuardar.setEnabled(true);
            }
                

            try {
                txGiro.setText(Rs.getString("giro").trim());
                txGiro.setBackground(Color.white);
            } catch (Exception e) {
                txGiro.setText("");
                txGiro.setBackground(Color.red);
            }
            try {
                txDireccion.setText(Rs.getString("direccion").trim());
                txDireccion.setBackground(Color.white);
            } catch (Exception e) {
                txDireccion.setText("");
                txDireccion.setBackground(Color.red);
            }
            try {
                txCiudad.setText(Rs.getString("ciudad").trim());
                txCiudad.setBackground(Color.white);
            } catch (Exception e) {
                txCiudad.setText("");
                txCiudad.setBackground(Color.red);
            }
            try {
                txComuna.setText(Rs.getString("comuna").trim());
                txComuna.setBackground(Color.white);
            } catch (Exception e) {
                txComuna.setText("");
                txComuna.setBackground(Color.red);
            }
            
            
            try {
                txDirDespacho.setText(Rs.getString("direccion").trim());
                txDirDespacho.setBackground(Color.white);
            } catch (Exception e) {
                txDirDespacho.setText("");
                txDirDespacho.setBackground(Color.red);
            }
            try {
                txCiudadDespacho.setSelectedItem(Rs.getString("ciudad").trim());
                txCiudadDespacho.setBackground(Color.white);
            } catch (Exception e) {
                txCiudadDespacho.setSelectedItem("");
                txCiudadDespacho.setBackground(Color.red);
            }
            try {
                txComunaDespacho.setSelectedItem(Rs.getString("comuna").trim());
                txComunaDespacho.setBackground(Color.white);
            } catch (Exception e) {
                txComunaDespacho.setSelectedItem("");
                txComunaDespacho.setBackground(Color.red);
            }
            
            
            
            //--Carga Ordenes Pendientes de Despacho
            if (Tipo != 2) {
                Rs = Sql.Select("select orden\n"
                        + "from occh\n"
                        //+ "where estado < 2\n"
                        + "where estadodespacho < 2\n"
                        + //"and esexento <>1 \n" +
                        "and  rut=" + txRut.getText()
                        + " and codigo_oc=" + cbCodigoOc.getSelectedItem().toString());
                while (Rs.next()) {
                    cbMd.addElement(Rs.getString("orden").trim());
                }
             
             // Trae datos del contacto   
                 Rs1 = Sql.Select("select p.nombre,c.contacto \n" +
                                "from occh c\n" +
                                "left join clicontactopersonas p on p.id= c.contacto\n" +
                                " where c.codigo_oc = " + cbCodigoOc.getSelectedItem().toString() + " \n" +
                                " and c.orden = '" + cbMd.getSelectedItem().toString() + "'");
                 if (Rs1.next())
                 {
                     txContacto.setText(Rs1.getString("nombre"));
                     txidContacto.setText(Rs1.getString("contacto"));
                 }
                
            }
            
        } catch (Exception e) {
            System.out.println(e);
        } finally{
            Sql.Close();
        }
    }
    
    private boolean ValidaMargen(){
    
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        String Respuesta="";
        
        try {
            Respuesta = Sql.SelectUnico("select * from fn_margen_occ(" + txRut.getText().trim() + "," + cbCodigoOc.getSelectedItem().toString().trim() + ",'" + cbNroOrden.getSelectedItem().toString().trim() +"')");
            if(!Respuesta.isEmpty()){
                
                Rs = Sql.Select("select count(*) as Autorizado \n" +
                                "from autorizaciones\n" +
                                "where tipo='AUTORIZAMARGENOCC'\n" +
                                "and rut="+ txRut.getText().trim() +"\n" +
                                "and codigo_oc="+ cbCodigoOc.getSelectedItem().toString().trim() +"\n" +
                                "and orden='"+ cbNroOrden.getSelectedItem().toString().trim() +"'\n" +
                                "and estado=0");
                Rs.next();
                
                if(Rs.getInt("Autorizado")==0){
                    
                    if(fmMain.OkCancel("Margen menor al LIMITE PERMITIDO. ¿Desea solicitar Autorización?")== JOptionPane.OK_OPTION){
                
                        Sql.ExeSql("insert into autorizaciones\n" +
                                   "(tipo,rut,codigo_oc,orden,solicita)values\n" +
                                   "('AUTORIZAMARGENOCC',"+ txRut.getText().trim() +","+ cbCodigoOc.getSelectedItem().toString().trim() +",'"+
                                     cbNroOrden.getSelectedItem().toString().trim() +"',user)");
                        Sql.Commit();
                    }
                }
                else{
                    fmMain.Mensaje("Margen aun no ha sido autorizado para esta Orden de Compra");
                }
                return false;
            }
            else{
                return true;
            }
        } catch (Exception e) {
            return false;
        }finally{
            Sql.Close();
        }
    }
    
    public String getFechaAsString() {
    
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return( sdf.format( (dtEmision.getDate()).getTime() ) );
    }
    
    private Boolean  GuardaGuia(){
        
        ExeSql Sql = new ExeSql();
        ExeSql Sql2 = new ExeSql();
        ResultSet Rs;
        boolean boo_esTransporte=false, guardada =false;
        int int_guia_transporte =-1;
        String StrTipoDocto = "";
        String Query = "";
        int intAnticipado=-1;
        

        
        
        if (txidContacto.getText().trim().equals("")){
            fmMain.Mensaje("Debe seleccionar un contacto para guardar la Guia");
            btContacto.doClick();
            return guardada;
        }
            
        if (modificarCliente >= 1)
        {
            if (JOptionPane.showConfirmDialog(null, "Usted realizo cambios en la ficha del cliente, desea guardarlos?.", "Guardar", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION)
            {
           try
            {
              Sql.ExeSql("update clicontacto set "
                      + " giro='" +txGiro.getText().trim()+"',"
                      + " direccion='" +txDireccion.getText().trim()+"',"
                      + " comuna='" +txComuna.getText().trim()+"',"
                      + " ciudad='" +txCiudad.getText().trim()+"'"
                      + " where rut=" +txRut.getText().trim()+ " and codigo_oc="+cbCodigoOc.getSelectedItem().toString().trim());
              Sql.Commit();
            }
            catch(Exception e) {
                    Sql.Rollback();
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
            }
        }
        
        try {
            //Revisa si la OC es de Tipo Transporte 
            //    ----------------------------------
                Rs = Sql2.Select("select  es_transporte, guia_transporte\n"
                        + "from occh\n"
                        + "where rut= " + txRut.getText().trim() + "\n"
                        + "and codigo_oc=" + cbCodigoOc.getSelectedItem().toString().trim() + "\n"
                        + "and orden='" + cbNroOrden.getSelectedItem().toString().trim() + "'");
                Rs.next();
                if (Rs.getBoolean("es_transporte")==true) 
                {
                    boo_esTransporte =true;
                    int_guia_transporte = Rs.getInt("guia_transporte");
                    StrTipoDocto = "FAT";
                    TipDocto_Master=StrTipoDocto;
                }
                else
                {
                    boo_esTransporte =false;
                    int_guia_transporte=-1;
                    StrTipoDocto = "GDC";
                    TipDocto_Master=StrTipoDocto;
                }
              } catch (Exception e) {
                    System.out.println(e);
                }finally{
                    Sql2.Close();
              }
        
         if (boo_esTransporte==false) 
         {
                if(!ValidaMargen()) return guardada;
         }       

        if (JOptionPane.showConfirmDialog(null, "Guardar los cambios realizados.", "Guardar", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {

        //GRABA NUEVA GUIA DE DESPACHO
            //----------------------------
            if (Tipo == 1) {
                try {
               //OBTIENE CORRELATIVO
                    //------------------
                    
                    String NewCorrelativo="";
                    if (boo_esTransporte){
                        NewCorrelativo =  String.valueOf(int_guia_transporte);
                    }
                    else{
                        Sql.ExeSql("update par_correlativo set numero=numero + 1 where tipo='"+ StTipoGDC + "'");
                        NewCorrelativo = Sql.SelectUnico("select numero from par_correlativo where tipo='" + StTipoGDC +"'");
                    }
                    

                //INSERTA NUEVA GUIA DE DESPACHO
                    //------------------------------
                    //Objetivo: grabar el tipo de despacho directo o normal, se agrega en campo tipodespacho Smallint Defecto Null
                    // en la tabla ctactecli.
                    // Autor: Cristian Ramirez
                    // Fecha: 16/05/2017
                    //------------------------------
                    //------------------------------
                    //------------------------------Codigo modificado-------------------------------------
                      Query = " INSERT INTO ctactecli(\n"
                            + " rut, tipdocto, nrodocto, tipdocorigen, nrodocorigen, occh,femision, \n"
                            + " totalexento, totalafecto, totaliva,totalimpespecifico,totaldocto,codigo_oc, tipodespacho, anticipado ,  \n"
                            + " contacto,direccion_despacho, comuna_despacho,observacion,ciudad_despacho)"
                            + " VALUES ("
                            + txRut.getText() + ","
                            + "'" + StrTipoDocto + "',"
                            + NewCorrelativo + ","
                            + "'OCC',"
                            + cbCodigoOc.getSelectedItem().toString().trim() + ",'"
                            + cbNroOrden.getSelectedItem().toString().trim() + "','"
                            + getFechaAsString() + "',"
                            + fmMain.SetGuardar(txExento.getText().trim()) + ","
                            + fmMain.SetGuardar(txNeto.getText().trim()) + ","
                            + fmMain.SetGuardar(txIva.getText().trim()) + ","
                            + fmMain.SetGuardar(txImpEspecifico.getText().trim()) + ","
                            + fmMain.SetGuardar(txTotal.getText().trim()) + ","
                            + cbCodigoOc.getSelectedItem().toString().trim() + ", ";
                            
                            if (chk_Anticipado.isSelected())
                            {
                                intAnticipado = 1;
                            }
                            else
                            {                                
                                intAnticipado=0;
                            }
                      
                            Query = Query + cbTipoDespacho.getSelectedIndex() + ", " +  intAnticipado  + ", " + Integer.valueOf(txidContacto.getText().trim()) + 
                             ",'" + txDirDespacho.getText().trim() + "','" + txComunaDespacho.getSelectedItem().toString().trim() +  
                             "','" + txObservaciones.getText().trim() +         
                             "','" + txCiudadDespacho.getSelectedItem().toString().trim() + "')";  
                    //------------------------------------------------------------------------------------

                    Sql.ExeSql(Query);

                //INSERTA NUEVA GUIA DE DESPACHO PRODUCTOS
                    //---------------------------------------
                    for (int i = 0; i < Grilla.getRowCount(); i++) {
                        //Si la cantidad es mayor a cero
                        if(Double.valueOf(Grilla.getValueAt(i,GetCol("Cantidad")).toString().replace(",",""))>0){
                            Query = " INSERT INTO ctacteclidet(\n"
                                + " rut, tipdocto, nrodocto, sku, cantidad, valorunitario, totallinea)\n"
                                + " VALUES ("
                                + txRut.getText() + ","
                                + "'" + StrTipoDocto + "',"
                                + NewCorrelativo + ",'"
                                + Grilla.getModel().getValueAt(i, 0).toString().trim() + "',"
                                + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("Cantidad")).toString()) + ","
                                + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("UniReal")).toString()) + ","
                                + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("Total")).toString()) + ")";
                        Sql.ExeSql(Query);
                        }
                        
                    }
                    
                    
                    if (boo_esTransporte==true) 
                    {
                        Sql.Select("select actualiza_estado_oc_fat(" + txRut.getText() + "," + NewCorrelativo + "," + cbCodigoOc.getSelectedItem().toString().trim() + ",'" + cbNroOrden.getSelectedItem().toString().trim() + "')");
                    }
                    else
                    {    
                        Sql.Select("select actualiza_estado_oc(" + txRut.getText() + "," + NewCorrelativo + "," + cbCodigoOc.getSelectedItem().toString().trim() + ",'" + cbNroOrden.getSelectedItem().toString().trim() + "')");
                    }

                    Sql.Commit();
                    JOptionPane.showMessageDialog(null,"Guardado");
                    

                    AbrirGuia(txRut.getText(), NewCorrelativo,StrTipoDocto);  //Se Aghrega Tipdocto aa la funcion
                    
                    //Emite_Guia();   092018
                     
                    if (TipDocto_Master.equals(StTipoGDC))
                    {    
//                        Emite_Guia();
                        SetTipo(2);
                    }  
                    else
                    {
                        SetTipo(1);
                        // deja PDF listo para imprimir
                        DocPDF(NewCorrelativo, StTipoGDC);
                    }  
                    
                    guardada=true;
                    return guardada;

                } catch (Exception e) {
                    Sql.Rollback();

                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                } finally {
                    Sql.Close();
                }

            } else {
                try {
                   // Actualiza la GDC si es de tipo FAT
                    if (StrTipoDocto.equals(StTipoFAT) )
                    {
                        
                        //Actualiza el encabezado
                            Query = "UPDATE ctactecli SET "
                            + "totalexento=" + fmMain.SetGuardar(txExento.getText().trim()) + ","
                            + "totalafecto=" + fmMain.SetGuardar(txNeto.getText().trim()) + ","
                            + "totaliva=" + fmMain.SetGuardar(txIva.getText().trim()) + ","
                            + "totalimpespecifico=" + fmMain.SetGuardar(txImpEspecifico.getText().trim()) + ",";
                            Query = Query + "tipodespacho =" + cbTipoDespacho.getSelectedIndex() + ", "
                            + "anticipado=" + intAnticipado + ", "        
                            + "observacion='" + txObservaciones.getText().trim() + "',"        
                            + "totaldocto=" + fmMain.SetGuardar(txTotal.getText().trim()) + " "
                            + " WHERE tipdocto= '" + StTipoFAT + "'" 
                            + " AND   nrodocto= " + lbNroDocto.getText().trim()
                            + " AND   rut=" + txRut.getText();
                            Sql.ExeSql(Query);
                        

                                for (int i = 0; i < Grilla.getRowCount(); i++) {
                                    if(Double.valueOf(Grilla.getValueAt(i,GetCol("Cantidad")).toString())>0){
                                        
                                            Query = " update  ctacteclidet \n" 
                                            + " set cantidad = " + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("Cantidad")).toString())
                                            + " , valorunitario = " + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("UniReal")).toString()) 
                                            + " , totallinea = "+ fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("Total")).toString())    
                                            + " WHERE "
                                            + "     rut      = " + txRut.getText()
                                            + " AND tipdocto = '" + StTipoFAT + "'" 
                                            + " AND nrodocto= " + lbNroDocto.getText().trim()
                                            + " AND sku ='" + Grilla.getModel().getValueAt(i, 0).toString().trim() + "'";
                                        
                                    Sql.ExeSql(Query);
                                    
                                    }
                               }
                    }
                    Sql.Commit();
                    fmMain.Mensaje("Guia de Despacho Tipo FAT modificada correctamente");
                    guardada=true;
                    
                } catch (Exception e) {
                    Sql.Rollback();
                     guardada=false;
                  
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                } finally {
                    Sql.Close();
                }
            }
        }    
        return guardada;
    }    
        
    
    
    
    
    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed
        
        ExeSql Sql = new ExeSql();
        ExeSql Sql2 = new ExeSql();
        ExeSql Sql3 = new ExeSql();
        
        ResultSet Rs;
        boolean boo_esTransporte=false;
        int int_guia_transporte =-1;
        String StrTipoDocto = "";
        String Query = "";
        int intAnticipado=-1;
        

        
        
        if (txidContacto.getText().trim().equals("")){
            fmMain.Mensaje("Debe seleccionar un contacto para guardar la Guia");
            btContacto.doClick();
            return;
        }
            
        if (modificarCliente >= 1)
        {
            if (JOptionPane.showConfirmDialog(null, "Usted realizo cambios en la ficha del cliente, desea guardarlos?.", "Guardar", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION)
            {
           try
            {
              Sql.ExeSql("update clicontacto set "
                      + " giro='" +txGiro.getText().trim()+"',"
                      + " direccion='" +txDireccion.getText().trim()+"',"
                      + " comuna='" +txComuna.getText().trim()+"',"
                      + " ciudad='" +txCiudad.getText().trim()+"'"
                      + " where rut=" +txRut.getText().trim()+ " and codigo_oc="+cbCodigoOc.getSelectedItem().toString().trim());
              Sql.Commit();


            }
            catch(Exception e) {
                    Sql.Rollback();
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
            }
        }
        
        try {
            //Revisa si la OC es de Tipo Transporte 
            //    ----------------------------------
                Rs = Sql2.Select("select  es_transporte, guia_transporte\n"
                        + "from occh\n"
                        + "where rut= " + txRut.getText().trim() + "\n"
                        + "and codigo_oc=" + cbCodigoOc.getSelectedItem().toString().trim() + "\n"
                        + "and orden='" + cbNroOrden.getSelectedItem().toString().trim() + "'");
                Rs.next();
                if (Rs.getBoolean("es_transporte")==true) 
                {
                    boo_esTransporte =true;
                    int_guia_transporte = Rs.getInt("guia_transporte");
                    StrTipoDocto = "FAT";
                    TipDocto_Master=StrTipoDocto;
                }
                else
                {
                    boo_esTransporte =false;
                    int_guia_transporte=-1;
                    StrTipoDocto = "GDC";
                    TipDocto_Master=StrTipoDocto;
                }
              } catch (Exception e) {
                    System.out.println(e);
                }finally{
                    Sql2.Close();
              }
        
         if (boo_esTransporte==false) 
         {
                if(!ValidaMargen()) return;
         }       

        if (JOptionPane.showConfirmDialog(null, "Guardar los cambios realizados.", "Guardar", JOptionPane.YES_OPTION) == JOptionPane.YES_OPTION) {

        //GRABA NUEVA GUIA DE DESPACHO
            //----------------------------
            if (Tipo == 1) {
                try {
               //OBTIENE CORRELATIVO
                    //------------------
                    int aumentara = 0;
                    String NewCorrelativo="";
                    String NewCorrelativo_test = "";
                    if (boo_esTransporte){
                        NewCorrelativo =  String.valueOf(int_guia_transporte);
                    }
                    else{
//                       
                        int resultado_ = Sql.ExeSqlInt("update par_correlativo set numero = numero + 1 where tipo='GDC'");
                        Sql.Commit();
                        
                        if(resultado_>0){
                            System.out.println("Se aumentó correlativo");
                        }
                        Rs = Sql.Select("select sp_getcorrelativo from sp_getcorrelativo('GDC')");   
                        Rs.next();
                        NewCorrelativo  = Rs.getString("sp_getcorrelativo").trim();
                        
                    }
                    


                //INSERTA NUEVA GUIA DE DESPACHO
                    //------------------------------
                    //Objetivo: grabar el tipo de despacho directo o normal, se agrega en campo tipodespacho Smallint Defecto Null
                    // en la tabla ctactecli.
                    // Autor: Cristian Ramirez
                    // Fecha: 16/05/2017
                    //------------------------------
                    //------------------------------
                    //------------------------------Codigo modificado-------------------------------------
                    if(!NewCorrelativo.equals("")){   
                        Query = " INSERT INTO ctactecli(\n"
                            + " rut, tipdocto, nrodocto, tipdocorigen, nrodocorigen, occh,femision, \n"
                            + " totalexento, totalafecto, totaliva,totalimpespecifico,totaldocto,codigo_oc, tipodespacho, anticipado ,  \n"
                            + " contacto,direccion_despacho, comuna_despacho,observacion,ciudad_despacho)"
                            + " VALUES ("
                            + txRut.getText() + ","
                            + "'" + StrTipoDocto + "',"
                            + NewCorrelativo + ","
                            + "'OCC',"
                            + cbCodigoOc.getSelectedItem().toString().trim() + ",'"
                            + cbNroOrden.getSelectedItem().toString().trim() + "','"
                            + getFechaAsString() + "',"
                            + fmMain.SetGuardar(txExento.getText().trim()) + ","
                            + fmMain.SetGuardar(txNeto.getText().trim()) + ","
                            + fmMain.SetGuardar(txIva.getText().trim()) + ","
                            + fmMain.SetGuardar(txImpEspecifico.getText().trim()) + ","
                            + fmMain.SetGuardar(txTotal.getText().trim()) + ","
                            + cbCodigoOc.getSelectedItem().toString().trim() + ", ";
                            
                            if (chk_Anticipado.isSelected())
                            {
                                intAnticipado = 1;
                            }
                            else
                            {                                
                                intAnticipado=0;
                            }
                      
                            Query = Query + cbTipoDespacho.getSelectedIndex() + ", " +  intAnticipado  + ", " + Integer.valueOf(txidContacto.getText().trim()) + 
                             ",'" + txDirDespacho.getText().trim() + "','" + txComunaDespacho.getSelectedItem().toString().trim() +  
                             "','" + txObservaciones.getText().trim() +         
                             "','" + txCiudadDespacho.getSelectedItem().toString().trim() + "')";  
                    //------------------------------------------------------------------------------------

                        Sql.ExeSql(Query);

                    //INSERTA NUEVA GUIA DE DESPACHO PRODUCTOS
                        //---------------------------------------
                        for (int i = 0; i < Grilla.getRowCount(); i++) {
                            //Si la cantidad es mayor a cero
                            if(Double.valueOf(Grilla.getValueAt(i,GetCol("Cantidad")).toString().replace(",",""))>0){
                                Query = " INSERT INTO ctacteclidet(\n"
                                    + " rut, tipdocto, nrodocto, sku, cantidad, valorunitario, totallinea)\n"
                                    + " VALUES ("
                                    + txRut.getText() + ","
                                    + "'" + StrTipoDocto + "',"
                                    + NewCorrelativo + ",'"
                                    + Grilla.getModel().getValueAt(i, 0).toString().trim() + "',"
                                    + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("Cantidad")).toString()) + ","
                                    + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("UniReal")).toString()) + ","
                                    + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("Total")).toString()) + ")";
                            Sql.ExeSql(Query);
                            }

                        }

                        
                        if (boo_esTransporte==true) 
                        {
                            Sql.Select("select actualiza_estado_oc_fat(" + txRut.getText() + "," + NewCorrelativo + "," + cbCodigoOc.getSelectedItem().toString().trim() + ",'" + cbNroOrden.getSelectedItem().toString().trim() + "')");
                        }
                        else
                        {    
                            Sql.Select("select actualiza_estado_oc(" + txRut.getText() + "," + NewCorrelativo + "," + cbCodigoOc.getSelectedItem().toString().trim() + ",'" + cbNroOrden.getSelectedItem().toString().trim() + "')");
                        }

                        Sql.Commit();
                        

                        
                        
                        JOptionPane.showMessageDialog(null,"Guardado");


                        //AbrirGuia(txRut.getText(), NewCorrelativo,StrTipoDocto);  //Se Agrega Tipdocto aa la funcion
                       

                        if (TipDocto_Master.equals(StTipoGDC))
                        {    
                            AbrirGuia(txRut.getText(), NewCorrelativo,StrTipoDocto);  //Se Agrega Tipdocto aa la funcion
                            Emite_Guia();
                            SetTipo(2);
                        }  
                        else
                        {
                            SetTipo(1);
                            // deja PDF listo para imprimir
                            DocPDF(NewCorrelativo, StTipoGDC);
                        }  
                        
//                        
//                        Sql3.ExeSql("update autorizaciones set guia=3 \n"+
//                                    "where codigo_oc="+ cbCodigoOc.getSelectedItem() +"\n" +
//                                    "and rut="+txRut.getText().toString().trim() +"\n" +
//                                    "and orden='"+ cbNroOrden.getSelectedItem().toString().trim() +"' \n" +
//                                    "and guia = 0");
//                        Sql3.Commit();
//
//                        
                        
                        
                    }
                    

                } catch (Exception e) {
                    Sql.Rollback();

                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                } finally {
                    Sql.Close();
                }
            

            } else {
                try {
                     //------------------------------
                    //Objetivo: grabar el tipo de despacho directo o normal, se agrega en campo tipodespacho Smallint Defecto Null
                    // en la tabla ctactecli.
                    // Autor: Cristian Ramirez
                    // Fecha: 16/05/2017
                    //------------------------------
                    // Codigo antes del Cambio
//                    Query = "UPDATE ctactecli SET "
//                            + "tipdocorigen='OCC', "
//                            + "nrodocorigen=" + cbCodigoOc.getSelectedItem().toString().trim() + ","
//                            + "occh = '" + cbNroOrden.getSelectedItem().toString().trim() + "',"
//                            + "femision='" + getFechaAsString() + "',"
//                            + "totalexento=" + fmMain.SetGuardar(txExento.getText().trim()) + ","
//                            + "totalafecto=" + fmMain.SetGuardar(txNeto.getText().trim()) + ","
//                            + "totaliva=" + fmMain.SetGuardar(txIva.getText().trim()) + ","
//                            + "totalimpespecifico=" + fmMain.SetGuardar(txImpEspecifico.getText().trim()) + ","
//                            + "totaldocto=" + fmMain.SetGuardar(txTotal.getText().trim()) + " "
//                            + "WHERE tipdocto='GDC' "
//                            + "AND   nrodocto="+lbNroDocto.getText().trim()+" "
//                            + "AND   rut=" + txRut.getText();

// //                      ------------------------------Codigo modificado-------------------------------------                             
                           Query = "UPDATE ctactecli SET "
                            + "tipdocorigen='OCC', "
                            + "nrodocorigen=" + cbCodigoOc.getSelectedItem().toString().trim() + ","
                            + "occh = '" + cbNroOrden.getSelectedItem().toString().trim() + "',"
                            + "femision='" + getFechaAsString() + "',"
                            + "totalexento=" + fmMain.SetGuardar(txExento.getText().trim()) + ","
                            + "totalafecto=" + fmMain.SetGuardar(txNeto.getText().trim()) + ","
                            + "totaliva=" + fmMain.SetGuardar(txIva.getText().trim()) + ","
                            + "totalimpespecifico=" + fmMain.SetGuardar(txImpEspecifico.getText().trim()) + ",";
                            if (chk_Anticipado.isSelected())
                            {
                                intAnticipado = 1;
                            }
                            else
                            {                                
                                intAnticipado=0;
                            }
                            Query = Query + "tipodespacho =" + cbTipoDespacho.getSelectedIndex() + ", "
                            + "anticipado=" + intAnticipado + ", "        
                            + "totaldocto=" + fmMain.SetGuardar(txTotal.getText().trim()) + " "
                            + "WHERE tipdocto='GDC' "
                            + "AND   nrodocto="+lbNroDocto.getText().trim()+"\n"
                            + "AND   rut=" + txRut.getText();
                    Sql.ExeSql(Query);
                    Sql.Commit();
                    
                    for (int i = 0; i < Grilla.getRowCount(); i++) {
                
                        Sql.ExeSql("update ctacteclidet set\n"
                                 + "valorunitario= " + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, 4).toString()) + ",\n"
                                 + "cantidad = "+ fmMain.SetGuardar(Grilla.getModel().getValueAt(i, 3).toString()) +",\n"
                                 + "totallinea= " + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, 5).toString()) + "\n"
                                 + "where rut=" + txRut.getText().trim() + "\n"
                                 + "and sku ='" + Grilla.getModel().getValueAt(i, GetCol("Sku")).toString().trim() + "'\n"
                                 + "and tipdocto= 'GDC' \n"
                                 + "and nrodocto=" + lbNroDocto.getText().trim()+"\n"
                                 + "and rut=" + txRut.getText().trim()+"");
                        Sql.Commit();



                    }
                    
                   // Actualiza la GDC si es de tipo FAT
                    
                    
                    
                    if (StrTipoDocto.equals(StTipoFAT) )
                    {
                        
                        //Actualiza el encabezado
                            Query = "UPDATE ctactecli SET "
                            + "totalexento=" + fmMain.SetGuardar(txExento.getText().trim()) + ","
                            + "totalafecto=" + fmMain.SetGuardar(txNeto.getText().trim()) + ","
                            + "totaliva=" + fmMain.SetGuardar(txIva.getText().trim()) + ","
                            + "totalimpespecifico=" + fmMain.SetGuardar(txImpEspecifico.getText().trim()) + ",";
                            Query = Query + "tipodespacho =" + cbTipoDespacho.getSelectedIndex() + ", "
                            + "anticipado=" + intAnticipado + ", "        
                            + "observacion='" + txObservaciones.getText().trim() + "',"        
                            + "totaldocto=" + fmMain.SetGuardar(txTotal.getText().trim()) + " "
                            + " WHERE tipdocto= '" + StTipoFAT + "'" 
                            + " AND   nrodocto= " + lbNroDocto.getText().trim()
                            + " AND   rut=" + txRut.getText();
                            Sql.ExeSql(Query);
                            Sql.Commit();
                        // Actualiza el detalle
//                                Query = " DELETE FROM ctacteclidet "
//                                        + " WHERE tipdocto= '" + StTipoFAT + "'" 
//                                        + " AND   nrodocto= " + lbNroDocto.getText().trim()
//                                        + " AND   rut=" + txRut.getText();
//                                Sql.ExeSql(Query);

                                for (int i = 0; i < Grilla.getRowCount(); i++) {
                                    if(Double.valueOf(Grilla.getValueAt(i,GetCol("Cantidad")).toString())>0){
//                                          
                                            Query = " update  ctacteclidet \n" 
                                            + " set  valorunitario = " + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("UniReal")).toString()) 
                                            + " , totallinea = "+ fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("Total")).toString())    
                                            + " WHERE "
                                            + "     rut      = " + txRut.getText()
                                            + " AND tipdocto = '" + StTipoFAT + "'" 
                                            + " AND nrodocto= " + lbNroDocto.getText().trim()
                                            + " AND sku ='" + Grilla.getModel().getValueAt(i, 0).toString().trim() + "'";
                                        
                                    Sql.ExeSql(Query);
                                    Sql.Commit();
                                    }
                               }
  
                    }
                    
                    fmMain.Mensaje("Guia de Despacho modificada correctamente");
                } catch (Exception e) {
                    Sql.Rollback();
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                } finally {
                    Sql.Close();
                }
            }
        }
    }//GEN-LAST:event_btGuardarActionPerformed
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
            Cantidad = Grilla.getModel().getValueAt(i,GetCol("Cantidad")).toString();
            Unitario = Unitario.replace(fmMain.GetMiles(), "");
            Cantidad = Cantidad.replace(fmMain.GetMiles(), "");
            Neto =  (Double.parseDouble(Unitario) * Double.parseDouble(Cantidad)) + Neto;
        }
        
        Neto = Math.round(Neto);
        Iva = Math.round((Neto * Double.parseDouble("0.19")));
    
        if(PesoCorreccion != 0){
        
            if(PesoCorreccion==1)
                Iva = Iva +1;
            else
                Neto = Neto -1;
        }
        
    
        Total = Neto + Iva;
        txNeto.setText(fmMain.FormatoTotal(Neto));
        txExento.setText(fmMain.FormatoTotal(Exento));
        txIva.setText(fmMain.FormatoTotal(Iva));
        txTotal.setText(fmMain.FormatoTotal(Total));
    
    }

//------------------------------------------------------------------------------
//  
//------------------------------------------------------------------------------
    private void GrillaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GrillaMouseClicked
        
        double cant,prec;    
        
        if(evt.getClickCount()==2 && !Grilla.getValueAt(Grilla.getSelectedRow(), 0).toString().isEmpty()){
            
            jdIngresaNumGdc jdNumero = new jdIngresaNumGdc(null, true);
            jdNumero.setLocationRelativeTo(null);
            prec = Double.parseDouble(fmMain.SetGuardar(Grilla.getValueAt(Grilla.getSelectedRow(), 4).toString()));
            cant = Double.parseDouble(fmMain.SetGuardar(Grilla.getValueAt(Grilla.getSelectedRow(), 3).toString()));
            jdNumero.SetNumero(cant);
        //    jdNumero.SetPrecio(prec);
            if (TipDocto_Master == null)
                {
                    TipDocto_Master="";
                }
            jdNumero.setTipoDocto(TipDocto_Master);
            jdNumero.setVisible(true);     
            
            if (jdNumero.GetNumero() > 0){
            
                Grilla.setValueAt(jdNumero.GetNumero(), Grilla.getSelectedRow(),3);

            }
            double vcantidad = Double.parseDouble(Grilla.getValueAt(Grilla.getSelectedRow(),3).toString().replaceAll("\\,",""));

           // Grilla.setValueAt(fmMain.FormatoNumero(jdNumero.GetPrecio()), Grilla.getSelectedRow(),4);
            //double TotLinea = vcantidad * jdNumero.GetPrecio();
            double TotLinea = vcantidad * prec;
            Grilla.setValueAt(fmMain.FormatoNumero(TotLinea),Grilla.getSelectedRow(),5);

            // Grilla.setValueAt(jdNumero.GetNumero(), Grilla.getSelectedRow(),GetCol("CantReal"));
            Grilla.setValueAt(vcantidad, Grilla.getSelectedRow(),3);
            //Grilla.setValueAt(jdNumero.GetPrecio(), Grilla.getSelectedRow(),4);
            Sumador();

            
        }
        
    }//GEN-LAST:event_GrillaMouseClicked
    
    private void DocPDF(String Numero, String TipoDoc){
        
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        ExeSql Sql1 = new ExeSql();
        ResultSet Rs1;
        String Query="";
        String path;
        String server,user,pass,ruta_local="";
        int puerto =21;
        String filtro="";
        String archivo_local = "";
        String Tipo="";            
     
        try {
           

//---------                     Trae Datos Ftp            ----------------------
//Creado por CRM - 15-06-2017            
 //trae datos ftp
            server="";
            puerto=0;
            user=""; 
            pass="";
            
                
                //Busqueda de Tipo 
                Rs1 = Sql1.Select("SELECT nrodoc from tipo_doc where tipo='" + TipoDoc + "'");
                if (Rs1.next())
                {
                    Tipo = String.valueOf(Rs1.getInt("nrodoc"));
                }
            
            	Rs = Sql.Select("SELECT * from conexiones where tipo='ftp_dte'");
                if (Rs.next())
                {
                    server = Rs.getString("serv");
                    user  = Rs.getString("usu");
                    puerto  = Rs.getInt("port");
                    pass = Rs.getString("pass");
                }
              
//                  //Verifica SO
                    String sistema = System.getProperty("os.name").toLowerCase();
                    File folder = new File("");
                    if (sistema.contains("win"))
                    {
                         ruta_local = "c:/" + carpeta + "/";
                         folder = new File(ruta_local.substring(0,ruta_local.length()-1));
                    }
                    else{
                          ruta_local = "/" + carpeta + "/";
                          folder = new File(ruta_local);
                    }
                    if (!folder.exists())
                    {
                        folder.mkdir();
                    }
                    
                     System.out.println("Carpeta Asignada " + ruta_local );
            
                    // Primer buscara el archivo en ruta local
                    filtro = Tipo + "F" + Numero + ".pdf";
                    System.out.println("Busca en carpeta  local" + ruta_local +"/"+filtro );
                    archivo_local = BuscaArchivos(new File(ruta_local),filtro);
                    System.out.println("Busca archivo en FTP -->" + ruta_local +"/"+filtro );
                    Ftp.busca_archivo_ftp(server, puerto, user, pass, ruta_local,archivo_local, filtro);
                    System.out.println("Sale de Busca Archivo" + filtro );
       
        }catch (Exception e) {
         
            fmMain.Mensaje("Existe una inconsistencia en la información.");
        }finally{
            Sql.Close();
        }
    }
    
    
    private void btEmitirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEmitirActionPerformed

        
    if (TipDocto_Master.equals(StTipoGDC)){    
        Emite_Guia();
    }//GEN-LAST:event_btEmitirActionPerformed
}
    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        SetTipo(3);
    }//GEN-LAST:event_btEditarActionPerformed

    private void btCorrigePesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCorrigePesoActionPerformed
        jdAjustePesos aju = new jdAjustePesos(null, true);
        aju.setLocationRelativeTo(null);
        aju.setTitle("Ajuste Pesos");
        aju.setVisible(true);
        PesoCorreccion = aju.GetAjuste();
        Sumador();
        
    }//GEN-LAST:event_btCorrigePesoActionPerformed

    private void btActualizarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btActualizarDatosActionPerformed
        if (!lbNroDocto.getText().equals(""))
        {JOptionPane.showMessageDialog(null,"La guia ya fue emitida, no se puede actualizar datos de cliente"); return;}
        if (btActualizarDatos.isSelected())
            {
            txGiro.setEnabled(false);
            txDireccion.setEnabled(false);
            txComuna.setEnabled(false);
            txCiudad.setEnabled(false);
            txDirDespacho.setEnabled(true);
            txComunaDespacho.setEnabled(true);
            txCiudadDespacho.setEnabled(true);
            txContacto.setEnabled(true);
            txidContacto.setVisible(false);
            btContacto.setVisible(true);
            btContacto.setEnabled(true);
            modificarCliente = 1;
            }
        else
        {
            txGiro.setEnabled(false);
            txDireccion.setEnabled(false);
            txComuna.setEnabled(false);
            txCiudad.setEnabled(false);
            txDirDespacho.setEnabled(false);
            
            txComunaDespacho.setEnabled(false);
            txCiudadDespacho.setEnabled(false);
            txContacto.setEnabled(false);
            txidContacto.setVisible(false);
            btContacto.setEnabled(false);
        }
    }//GEN-LAST:event_btActualizarDatosActionPerformed

    private void btEmiteManActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEmiteManActionPerformed
         Emite_Guia();
        if (TipDocto_Master.equals(StTipoGDC)){    
//            Emite_Guia();
        }      
      
    }//GEN-LAST:event_btEmiteManActionPerformed

    private void cbNroOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNroOrdenActionPerformed
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        DefaultTableModel TableModel = (DefaultTableModel) Grilla.getModel();

        if(Tipo==2) return;

        //Limpia Grilla de Productos
        while (TableModel.getRowCount() > 0)
        TableModel.removeRow(0);

        String Query =  "select d.sku,p.nombre,u.um,d.separado,d.valorunitario,d.totlinea\n" +
        "from occhdet d\n" +
        "left join producto p \n" +
        "on d.sku=p.sku\n" +
        "left join par_unidad u\n" +
        "on p.unidad=u.codigo\n" +
        "where rut=" + txRut.getText() +
        " and codigo_oc=" + cbCodigoOc.getSelectedItem().toString().trim() +
        " and orden='" + cbNroOrden.getSelectedItem().toString().trim() + "' \n" +
        "and separado > 0";
        try{
            //BLOQUEO - Revisa si esta bloqueado
            if(lbBloqueo.isVisible()){
                Rs = Sql.Select("select count(*) as contador\n" +
                    "from cli_desbloqueo\n" +
                    "where rut="+ txRut.getText() +"\n" +
                    "and Codigo_OC="+ cbCodigoOc.getSelectedItem().toString().trim() +"\n" +
                    "and orden='" + cbNroOrden.getSelectedItem().toString().trim() + "'");
                Rs.next();
                if(Rs.getInt("contador")>0){
                    lbBloqueo.setVisible(false);
                    btGuardar.setEnabled(true);
                }
            }
            ExeSqlLuvaly luvsql = new ExeSqlLuvaly();
            ResultSet nombre = null;
            Rs = Sql.Select(Query);
            
            while(Rs.next()){
                nombre = luvsql.Select("select p.nombre, u.um from producto p \n"
                                     + "LEFT JOIN par_unidad u ON u.codigo=p.unidad\n "
                                     + "where p.sku = '"+Rs.getString("sku").trim()+"'");
                nombre.next();
                TableModel.addRow(new Object[]{
                    Rs.getString("sku"),
                    nombre.getString("nombre"),
                    nombre.getString("um"),
                    fmMain.FormatoNumero(Rs.getDouble("separado")),
                    fmMain.FormatoNumero(Rs.getDouble("valorunitario")),
                    fmMain.FormatoNumero(Rs.getDouble("separado")*Rs.getDouble("valorunitario")),
                    Rs.getDouble("valorunitario")});
            }
        Sumador();

        //            Rs = Sql.Select(" select trim(dirdespacho) dirdespacho from occh" +
            //                            " where rut=" + txRut.getText() +
            //                            " and codigo_oc=" + cbCodigoOc.getSelectedItem().toString().trim() +
            //                            " and orden='" + cbNroOrden.getSelectedItem().toString().trim() + "'");
        //            Rs.next();
        //            txDirDespacho.setText(Rs.getString("dirdespacho"));
        } catch (Exception e) {
            System.out.println(e);
        } finally{
            Sql.Close();
        }
    }//GEN-LAST:event_cbNroOrdenActionPerformed

    private void cbCodigoOcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCodigoOcActionPerformed

        if(Tipo==2 || Tipo==3){
            DefaultComboBoxModel cbMd2 = new DefaultComboBoxModel();
            cbMd2.addElement(OrdenOC);
            cbNroOrden.setModel(cbMd2);

        }
        else{
            CargaDatosCodigoOC();
        }
    }//GEN-LAST:event_cbCodigoOcActionPerformed

    private void btPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPDFActionPerformed
        //        String path = "\\\\192.168.0.130\\Documentos Electronicos";
        //        String files;
        //        File folder = new File(path);
        //        File[] listOfFiles = folder.listFiles(new FilenameFilter() {
            //            @Override
            //            public boolean accept(File folder, String Nombre) {
                //                return Nombre.contains("52F" + lbNroDocto.getText().trim());
                //            }
            //        });
    //        for (int i = 0; i < listOfFiles.length; i++)         {
        //
        //            if (listOfFiles[i].isFile())             {
            //
            //                files = listOfFiles[i].getName();
            //                try {
                //                    File pathCompleto = new File(path + "\\" +files);
                    //                        Desktop.getDesktop().open(pathCompleto);
                    //                    } catch (IOException ex) {
                    //                        ex.printStackTrace();
                    //                    }
                //                }
            //            }
        busca("52-F", lbNroDocto.getText().trim());
    }//GEN-LAST:event_btPDFActionPerformed

    private void txDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txDireccionKeyReleased
        txDireccion.setText(txDireccion.getText().toUpperCase());
    }//GEN-LAST:event_txDireccionKeyReleased

    private void txDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txDireccionActionPerformed

    private void txDirDespachoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txDirDespachoKeyTyped
        // TODO add your handling code here:
            char c=evt.getKeyChar(); 
               if(c == '(' || c == ')') { 
                    getToolkit().beep(); 
                    evt.consume(); 
                    System.out.println("Ingresar sin espacios  ." ); 
                } 
        
        
        
        if (txDirDespacho.getText().length() >= LimiteDireccion)
        {
            evt.consume();
        }
        
    }//GEN-LAST:event_txDirDespachoKeyTyped

    private void txDirDespachoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txDirDespachoKeyReleased
        txDirDespacho.setText(txDirDespacho.getText().toUpperCase());
    }//GEN-LAST:event_txDirDespachoKeyReleased

    private void txCiudadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCiudadKeyReleased
        txCiudad.setText(txCiudad.getText().toUpperCase());
    }//GEN-LAST:event_txCiudadKeyReleased

    private void txCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txCiudadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txCiudadActionPerformed

    private void txComunaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txComunaKeyReleased
        txComuna.setText(txComuna.getText().toUpperCase());
    }//GEN-LAST:event_txComunaKeyReleased

    private void txComunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txComunaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txComunaActionPerformed

    private void txGiroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txGiroKeyReleased
        txGiro.setText(txGiro.getText().toUpperCase());
    }//GEN-LAST:event_txGiroKeyReleased

    private void txGiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txGiroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txGiroActionPerformed

    private void txNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txNombreKeyTyped

    }//GEN-LAST:event_txNombreKeyTyped

    private void txNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txNombreKeyReleased
        txNombre.setText(txNombre.getText().toUpperCase());
    }//GEN-LAST:event_txNombreKeyReleased

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
            Carga = CargaCliente(BPC.GetRut());   
        }

        // Si cargó cliente y es nuevo.
        if (Carga && Tipo == 1) {
            SetTipo(1);
            FindeAgno();
            txDirDespacho.setEditable(true);
            txDirDespacho.setEnabled(true);
            txContacto.setEditable(true);
            txContacto.setEnabled(true);
            txidContacto.setEditable(true);
            txidContacto.setEnabled(true);
        }
        // Si cargó cliente y esta abriendo
        if (Carga && Tipo == 2) {

            jdAbrirDocumento ADoc = new jdAbrirDocumento(null, true);
            //if (ADoc.ShowModal("GDC", RutMaster) == JOptionPane.YES_OPTION) {
            if (ADoc.ShowModal(TipDocto_Master, RutMaster) == JOptionPane.YES_OPTION) {
                 //VERIFICA NIVEL DE USUARIO PARA EDITAR GUIA DE DESPACHO
                int pruebaUusario = 100;
                int intNivelUsuario = 0;
                intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
//                if( intNivelUsuario == pruebaUusario){
//                    btEditar.setVisible(true);
//                }else{
//                    btEditar.setVisible(false);
//                }
                AbrirGuia(RutMaster, ADoc.GetNumero(),TipDocto_Master);
                SetTipo(2);
                btEditar.setEnabled(true);
                txDirDespacho.setEditable(false);
                txDirDespacho.setEnabled(false);
               
               
            }else{
                txRut.setText("");
                txNombre.setText("");
                txRut.requestFocus();
            }

        }
    }//GEN-LAST:event_btIrActionPerformed

    private void txRutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txRutKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            btIr.doClick();
        }
    }//GEN-LAST:event_txRutKeyPressed

    private void btContactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btContactoActionPerformed
        // TODO add your handling code here:
            String Vendedor;
        try {
            jdContactosCli Con = new jdContactosCli(null, true);

            Vendedor = Con.Show(RutMaster,cbCodigoOc.getSelectedItem().toString().trim(), "TIPCONTACTOCLI",true).trim();
            if(!Vendedor.equals(""))
            txContacto.setText(Con.GetUsuario());
            txidContacto.setText(Con.GetUsuarioId());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btContactoActionPerformed

    private void cbTipoDespachoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoDespachoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoDespachoActionPerformed

    private void btAbrir_FATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAbrir_FATActionPerformed
        Abre_FAT();
        lbTransporte.setVisible(true);
    }//GEN-LAST:event_btAbrir_FATActionPerformed

    private void btEmiteTransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEmiteTransActionPerformed
        // TODO add your handling code here:
        
        if (GuardaGuia()==true){
                Emite_Transporte();
                Habilita(false);
                Edicion(false);
                btGuardar.setEnabled(false);
                btCancelar.setEnabled(false);
                btNuevo.setEnabled(true);
                btAbrir.setEnabled(true);
                btAbrir_FAT.setEnabled(true);
        }
        else
        {
            fmMain.Mensaje("Error al Grabar Datos de Guia, intente nuevamente");
            
        }
      
    }//GEN-LAST:event_btEmiteTransActionPerformed
    
    private void merma()
    {
        
        //ESTADO =2 MERMA.
        
        // TODO add your handling code here:
         ExeSql Sql = new ExeSql();        
        try {
            Sql.ExeSql("update ctactecli set estado=2 where tipdocto='" + StTipoGDC + "' and nrodocto=" + lbNroDocto.getText().trim()+" and rut = "+txRut.getText()+"");
            Sql.Commit();
            JOptionPane.showMessageDialog(null,"Documento Emitido a Merma");
            btEmitir.setEnabled(false);
            txEstado.setText("Emitido");
            btEditar.setEnabled(false);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
    }
    
    private void bt_MermaStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_MermaStockActionPerformed
        
           jd_Emision_Merma_Stock jdMermaStock = new jd_Emision_Merma_Stock(null, true);
           jdMermaStock.Rut = txRut.getText().trim();
           jdMermaStock.Numero = lbNroDocto.getText().trim();
           jdMermaStock.TipDocto = TipDocto_Master;
           jdMermaStock.Cliente = txNombre.getText().trim();
           jdMermaStock.Codigo_oc = cbCodigoOc.getSelectedItem().toString().trim();
           jdMermaStock.AbrirGuia2();
           jdMermaStock.setVisible(true);
           
            AbrirGuia(txRut.getText().trim(), lbNroDocto.getText().trim(), TipDocto_Master);

    }//GEN-LAST:event_bt_MermaStockActionPerformed

    private void GrillaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GrillaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_GrillaMouseEntered

    public void Abre_FAT(){
        
        Habilita(false);
        Edicion(false);
        Limpia();
        lbTransporte.setVisible(true);
        txRut.setEnabled(true);
        txRut.setEditable(true);
        btIr.setEnabled(true);
        btCancelar.setEnabled(true);
        txDirDespacho.setEditable(false);
        txRut.requestFocus();
        btEditar.setEnabled(false);
        SetTipo(2);
        TipDocto_Master ="FAT";
    }
    
    
    private void trae_comuna(){
        // TODO add your handling code here:
           // TODO add your handling code here:
            jdComunas Comuna = new jdComunas(null, true);
            Comuna.CargaComunas();
            Comuna.setVisible(true);
           txComunaDespacho.setSelectedItem(Comuna.GetComuna());
           txCiudadDespacho.requestFocus();
    }
    
    
    
    private void Emite_Guia(){
     // TODO add your handling code here:
        ExeSql Sql = new ExeSql();        
        try {
            //Se ha cambiado el codigo para probar la copia del Fichero en FTP
//            GeneraDTE_FTP.Generar(txRut.getText().trim(),"GDC" ,lbNroDocto.getText().trim());
            GeneraDTE_FTP.Generar(txRut.getText().trim(),StTipoGDC ,lbNroDocto.getText().trim());
            Sql.ExeSql("update ctactecli set estado=1 where tipdocto='" + StTipoGDC + "' and nrodocto=" + lbNroDocto.getText().trim()+" and rut = "+txRut.getText()+"");
            Sql.Commit();
            JOptionPane.showMessageDialog(null,"Documento Emitido");
            btEmitir.setEnabled(false);
            txEstado.setText("Emitido");
            btEditar.setEnabled(false);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
      
    }

       
    private void Emite_Transporte(){
     
        ExeSql Sql = new ExeSql();        
        try {
            
            Sql.ExeSql("update ctactecli set estado=1 where tipdocto='" + StTipoFAT + "' and nrodocto=" + lbNroDocto.getText().trim()+" and rut = "+txRut.getText()+"");
            Sql.Commit();
            JOptionPane.showMessageDialog(null,"Documento Emitido Correctamente ");
            btEmiteTrans.setEnabled(false);
            bt_MermaStock.setEnabled(false);
            txEstado.setText("Emitido");
            btEditar.setEnabled(false);
            btEmiteTrans.setEnabled(false);
        
        } catch (SQLException ex) {
            
            System.out.println(ex);
        }
      
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Grilla;
    private javax.swing.JButton btAbrir;
    private javax.swing.JButton btAbrir_FAT;
    private javax.swing.JToggleButton btActualizarDatos;
    private javax.swing.JButton btAgregar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btContacto;
    private javax.swing.JButton btCorrigePeso;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btEliminar;
    private javax.swing.JButton btEmiteMan;
    private javax.swing.JButton btEmiteTrans;
    private javax.swing.JButton btEmitir;
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btIr;
    private javax.swing.JButton btNuevo;
    private javax.swing.JButton btPDF;
    private javax.swing.JButton bt_MermaStock;
    private javax.swing.JComboBox cbCodigoOc;
    private javax.swing.JComboBox cbNroOrden;
    private javax.swing.JComboBox cbTipoDespacho;
    private javax.swing.JCheckBox chk_Anticipado;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private javax.swing.JLabel lbBloqueo;
    private javax.swing.JLabel lbNroDocto;
    private javax.swing.JLabel lbObs;
    private javax.swing.JLabel lbTransporte;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JTextField txCiudad;
    private javax.swing.JComboBox<String> txCiudadDespacho;
    private javax.swing.JTextField txComuna;
    private javax.swing.JComboBox<String> txComunaDespacho;
    private javax.swing.JTextField txContacto;
    private javax.swing.JTextField txDirDespacho;
    private javax.swing.JTextField txDireccion;
    private javax.swing.JTextField txDv;
    private javax.swing.JTextField txEstado;
    private javax.swing.JTextField txExento;
    private javax.swing.JTextField txGiro;
    private javax.swing.JTextField txImpEspecifico;
    private javax.swing.JTextField txIva;
    private javax.swing.JTextField txNeto;
    private javax.swing.JTextField txNombre;
    private javax.swing.JTextArea txObservaciones;
    private javax.swing.JTextField txRut;
    private javax.swing.JTextField txTotal;
    private javax.swing.JTextField txidContacto;
    // End of variables declaration//GEN-END:variables
}
