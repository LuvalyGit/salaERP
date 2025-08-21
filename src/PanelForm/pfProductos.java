package PanelForm;
import java.sql.ResultSet;
import java.sql.SQLException;
import Conexion.ExeSql;
import Conexion.ExeSqlLuvaly;
import Dialogos.jdAgregaIDCH;
import Dialogos.jdBuscarCliPrv;
import Dialogos.jdBuscarProductos;
import Dialogos.jdBuscarProductos2;
import Dialogos.jdCargaValores;
import Dialogos.jdFechaLlegadaProveedor;
import Dialogos.jdImagenProducto;
import Dialogos.jdRelacionadosReporte;
import Dialogos.jdRelacionarProductos;
import Dialogos.jdVoucher;
import java.security.Principal;
import Dialogos.jd_UbicacionesP;
import Formularios.fmMain;
import static Formularios.fmMain.intNivelUsuario;
import static Formularios.fmMain.pnPestanas;
import Utilidades.ComboCodigos;
import Utilidades.Ftp;
import Utilidades.PanelTab;
import Utilidades.SubeFTP;
import Utilidades.MyCellRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ValueAxis;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author DavidAlcaman
 */
public class pfProductos extends javax.swing.JPanel {
    
    
    int PosUM;
    int PosLinea;
    int PosSubLinea;
    int PosImpuesto;
    int PosMarca;
    int PosConvenio;
    boolean IVA;
    FileInputStream fis;
    int longitudBytes;
    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    int Tipo=-1; //-1 Nada de nada      1 Nuevo      2 Abierto      3.Editando
    public int guardo=0;
     
    public static Color DARK_GREEN = new Color(0,153,51);
    
    //****************************************************************************** 
    
    Calendar calendario = Calendar.getInstance(); 
    Calendar calendario1 = Calendar.getInstance(); 
    Calendar calendario2 = Calendar.getInstance(); 
    Calendar calendario3 = Calendar.getInstance(); 
    Calendar calendario4 = Calendar.getInstance(); 
    Calendar calendario5 = Calendar.getInstance(); 
    Calendar calendario6 = Calendar.getInstance(); 
    
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    DefaultTableModel tModel = null;
    
    String fechaIni = "";
    String fechaFin = "";
    
     String ucant = "";
     
      
    
    //************************************************************************ 
    
    public pfProductos() {
        initComponents();
        txSku.requestFocus();
        Tipo=-99;
        CargaUnidades();
        CargaLineas();
        CargaConvenios();
//        CargaMarcas();
        CargaOtrosImpuestos();
       
        int intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
       
        //btCargarImagen.setVisible(false);
        Tipo=0;
        cbConvenio.setSelectedIndex(-1);
        cbFamilia.setSelectedIndex(-1);
        cbUnidad.setSelectedIndex(-1);
        Tipo=-1;
        Edicion(false);
        Habilita(false);
       
        cbConvenioCod.setVisible(false);
        cbFamiliaCod.setVisible(false);
        cbSubFamiliaCod.setVisible(false);
        cbConvenioSku.setVisible(false);
        cbFamiliaSku.setVisible(false);
        cbSubFamiliaSku.setVisible(false);
        cbUnidadId.setVisible(false);
        cbOtroImpuestoId.setVisible(false);
        cbOtroImpuestoTaza.setVisible(false);
        txUbicados.setEnabled(false);
        txTransito.setEnabled(false);
        lsCodbar.setVisible(false);
        
        btUbica.setEnabled(false);
        txMinimo.setEnabled(false);
        txMinimo.setEditable(false);
      
        txNuevoMinimo.setVisible(false);
        btGuardarMinimo.setVisible(false);
        lbNuevoMinimo.setVisible(false);
       
        chkIva.setEnabled(false);
        chk_minimo.setVisible(false);
       
        skulabel.setVisible(false);
        
        jLabel10.setVisible(false);
        jLabel12.setVisible(false);
        jLabel22.setVisible(false);
        
        txStockOCC.setVisible(false);
        txSeparado.setVisible(false);
        txStockTotal.setVisible(false);
        
        jButton5.setVisible(false);
        
        btEd.setVisible(false);
        btGuar.setVisible(false);
             
        
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        GrillaMovimientos.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        GrillaMovimientos.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
        GrillaMovimientos.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
        btNuevo.setEnabled(true);
        btNuevo.setVisible(false);
       
        btUbica.setVisible(false);
        
        Pestanas.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Runnable miRunnable = new Runnable() {
                public void run() {
                    try{

                        lbcargando.setEnabled(true);
                        lbcargando.setVisible(true);

                        if(Pestanas.getSelectedComponent().equals(Pestanas.getComponentAt(1))){
                
                            CargaMovimientos(txSku.getText().trim());
                        }
                        if(Pestanas.getSelectedComponent().equals(Pestanas.getComponentAt(2))){
                            CargaCompras(txSku.getText().trim());
                        }
                       
                        jButton5.setEnabled(true);

                        lbcargando.setVisible(false);
                        lbcargando.setEnabled(false);


                    } catch (Exception e) { 
                        e.printStackTrace();
                    }
                }
            };
         
            
            Thread hilo = new Thread(miRunnable);
            hilo.start();        
            lbcargando.setText("Cargando.....");
            URL urlInfo =  this.getClass().getResource("/Iconos16/wait.gif");
            ImageIcon IconoInfo =  new ImageIcon(urlInfo); 
            lbcargando.setIcon(IconoInfo);
            lbcargando.setForeground(Color.red);
        }
    });

        Tipo=0;
        PosImpuesto=-1;
        
        
         
        //        Enlaza OC con sus Facturas, Notas de creditos y Guias asiociadas
        GrillaCompras.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        @Override
       public void valueChanged(ListSelectionEvent e){
           if (GrillaFacturasC.getSelectedRowCount()>=0)
               
               carga_facturas_orden(GrillaCompras.getValueAt(GrillaCompras.getSelectedRow(), 3).toString().trim());
       } 
    });   
        
        
        
            
    }
    
    private void carga_facturas_orden(String StOrden){
        
        ExeSql Sql = new ExeSql();
        ResultSet Rs,Rs1;
        String Query, QTot="";
        DefaultTableModel TableModel = (DefaultTableModel) GrillaFacturasC.getModel();
        
        try{     
            Query = "select c.rut,c.nrodocto, c.tipdocto from \n" +
                    "ctacteprv c\n" +
                    "where \n" +
                    "c.nrodocorigen =" + StOrden + "\n" +
                    "and c.tipdocorigen  ='OCP' and\n" +
                    "c.tipdocto ='FAP'";
        
        
//     Limpia Ordenes Encabezado   
            while(TableModel.getRowCount()>0)
                   TableModel.removeRow(0);    
            
            Rs = Sql.Select(Query);
            
            while(Rs.next()){
               
                TableModel.addRow(new Object[]{Rs.getString("rut").trim(), 
                                                  Rs.getInt("nrodocto"),Rs.getString("tipdocto")});
            }
            Rs.close();
       
        }catch (Exception e) {
            fmMain.Mensaje("Existe una inconsistencia en la información.");
        }finally{
            Sql.Close();
        }    
        
}

    @SuppressWarnings("unchecked")
   private int BooleanToInt(boolean Input){
       if(Input==true)return 1;
       else           return 0;
   }
    private String ElTrim(String Texto)   {
    try {
        return Texto.trim();
    } catch (Exception e) {
        return Texto;
    }
}
//--------------------------------------------------------------------------------
// Funcion SET DE TIPOS
//--------------------------------------------------------------------------------
    
    

    private void SetTipo(int ElTipo){
        //Nada
        if(ElTipo==-1){
            
            fmMain.SetEstado(fmMain.pnPestanas.getSelectedIndex(),0);
            Tipo=-1;         
            Limpia();
            Habilita(false);
            Edicion(false);
            txSku.setEnabled(true);
            txSku.setEditable(true);
            btIr.setEnabled(true);
            txSku.requestFocus();
            jCheckBox1.setEnabled(true);
            btCancelar.setEnabled(false);
            btGuardar.setEnabled(false);
            btNuevo.setEnabled(true);
            btEditar.setEnabled(false);
            txSku.requestFocus();
            txUbicados.setEnabled(false);
            txTransito.setEnabled(false);
            lsCodbar.setVisible(false);
            btUbica.setEnabled(false);
            txMinimo.setEnabled(false);
            txMinimo.setEditable(false);
            txNuevoMinimo.setVisible(false);
            chk_minimo.setVisible(false);
            btGuardarMinimo.setVisible(false);
            lbNuevoMinimo.setVisible(false);
       
            chkIva.setEnabled(false);
         

        }
        //Nuevo Producto
        else if(ElTipo==1){
            jCheckBox1.setEnabled(true);
            Tipo=1;
            fmMain.SetEstado(fmMain.pnPestanas.getSelectedIndex(),1);
            btGuardar.setEnabled(true);
            btCancelar.setEnabled(true);
            btEditar.setEnabled(false);
            btNuevo.setEnabled(false);
            Habilita(true);
            Edicion(true);
            Limpia();
            txSku.setEditable(false);
            txNombre.requestFocus();
            Tipo=1;
//            btUbica.setEnabled(true);
//            txUbicados.setEnabled(true);
//            txTransito.setEnabled(true);
//            lsCodbar.setVisible(true);
//            lsChilecompras.setVisible(true);
//            txUbicados.setEditable(false);
//            btUbica.setEnabled(false);
            chkIva.setEnabled(true);
        }
        //Abre Producto
        else if (ElTipo==2){
            jCheckBox1.setEnabled(false);
            fmMain.SetEstado(fmMain.pnPestanas.getSelectedIndex(),0);
            btEditar.setEnabled(true);
            btNuevo.setEnabled(true);
            btGuardar.setEnabled(false);
            btCancelar.setEnabled(false);
//            btEliminar.setEnabled(true);
            Habilita(true);
            Edicion(false);
            txSku.setEditable(true);
            Tipo=2;
//            btUbica.setEnabled(false);
        }
        // Edita Producto 
        else if(ElTipo==3){
            jCheckBox1.setEnabled(true);
            fmMain.SetEstado(fmMain.pnPestanas.getSelectedIndex(),1);
            Habilita(true);
            Edicion(true);
//            txUbicados.setEditable(true);
//            btUbica.setEnabled(false);
            
//            ComboCodigos cbAux = (ComboCodigos) cbUnidad.getSelectedItem();
//            
//            cbUnidad.setModel(cbmUnidad);
//            cbUnidad.setSelectedItem(AuxUnidad);
            btGuardar.setEnabled(true);
            btCancelar.setEnabled(true);
            btNuevo.setEnabled(false);
            txSku.setEditable(false);
            Tipo=3;
            btUbica.setEnabled(true);
            
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btGroupMovimientos = new javax.swing.ButtonGroup();
        btGroupProveedor = new javax.swing.ButtonGroup();
        pnDetalle = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btIr = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txNombre = new javax.swing.JTextField();
        txSku = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        chkCodigo = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        txPeso = new javax.swing.JTextField();
        cbUnidad = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbSubFamilia = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        cbFamilia = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        cbConvenio = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        chkIva = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        cbOtroImpuesto = new javax.swing.JComboBox();
        txTazaImpuesto = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        lsCodbar = new javax.swing.JList();
        lbcantidad = new javax.swing.JLabel();
        txCantUnidad = new javax.swing.JTextField();
        jtCantidad = new javax.swing.JToggleButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel24 = new javax.swing.JLabel();
        txDisplay = new javax.swing.JTextField();
        Pestanas = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txPNeto = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txMinimo = new javax.swing.JTextField();
        lbNuevoMinimo = new javax.swing.JLabel();
        txNuevoMinimo = new javax.swing.JTextField();
        btGuardarMinimo = new javax.swing.JButton();
        chk_minimo = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        txStockInv = new javax.swing.JTextField();
        txStockTotal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txSeparado = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txTransito = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txUbicados = new javax.swing.JTextField();
        btUbica = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txStockOCP = new javax.swing.JTextField();
        btEd = new javax.swing.JButton();
        btGuar = new javax.swing.JButton();
        cbConvenioCod = new javax.swing.JComboBox();
        cbConvenioSku = new javax.swing.JComboBox();
        cbUnidadId = new javax.swing.JComboBox();
        cbFamiliaCod = new javax.swing.JComboBox();
        cbFamiliaSku = new javax.swing.JComboBox();
        cbSubFamiliaCod = new javax.swing.JComboBox();
        cbSubFamiliaSku = new javax.swing.JComboBox();
        cbOtroImpuestoTaza = new javax.swing.JComboBox();
        cbOtroImpuestoId = new javax.swing.JComboBox();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        Grilla_Inv = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        txStockOCC = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        GrillaMovimientos = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        GrillaCompras = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        GrillaFacturasC = new javax.swing.JTable();
        skulabel = new javax.swing.JLabel();
        pnMenu = new javax.swing.JPanel();
        btNuevo = new javax.swing.JButton();
        btGuardar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btEditar = new javax.swing.JButton();
        btEliminar = new javax.swing.JButton();
        lbcargando = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setName("pnProductos"); // NOI18N
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        pnDetalle.setPreferredSize(new java.awt.Dimension(1200, 722));
        pnDetalle.setRequestFocusEnabled(false);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        btIr.setText("Ir");
        btIr.setBorder(null);
        btIr.setMaximumSize(new java.awt.Dimension(15, 15));
        btIr.setMinimumSize(new java.awt.Dimension(15, 15));
        btIr.setPreferredSize(new java.awt.Dimension(15, 15));
        btIr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIrActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre");

        txNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNombreActionPerformed(evt);
            }
        });
        txNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txNombreKeyTyped(evt);
            }
        });

        txSku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txSkuActionPerformed(evt);
            }
        });
        txSku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txSkuKeyPressed(evt);
            }
        });

        jLabel1.setText("Código");

        chkCodigo.setText("Cod");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(chkCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txSku, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btIr, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txSku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btIr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkCodigo))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        txPeso.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txPeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txPesoKeyTyped(evt);
            }
        });

        cbUnidad.setMaximumRowCount(15);
        cbUnidad.setEnabled(false);
        cbUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbUnidadActionPerformed(evt);
            }
        });

        jLabel7.setText("Unidad");

        jLabel5.setText("Cod. Barra");

        cbSubFamilia.setMaximumRowCount(20);
        cbSubFamilia.setEnabled(false);
        cbSubFamilia.setOpaque(false);
        cbSubFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSubFamiliaActionPerformed(evt);
            }
        });

        jLabel9.setText("Sub Familia");

        cbFamilia.setMaximumRowCount(10);
        cbFamilia.setEnabled(false);
        cbFamilia.setOpaque(false);
        cbFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFamiliaActionPerformed(evt);
            }
        });

        jLabel8.setText("Familia");

        jLabel29.setText("Convenio");

        cbConvenio.setEnabled(false);
        cbConvenio.setOpaque(false);
        cbConvenio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbConvenioActionPerformed(evt);
            }
        });

        jLabel20.setText("Peso");

        chkIva.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkIva.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel21.setText("IVA");

        jLabel23.setText("Otro Impuesto");

        cbOtroImpuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOtroImpuestoActionPerformed(evt);
            }
        });

        txTazaImpuesto.setEditable(false);
        txTazaImpuesto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lsCodbar.setEnabled(false);
        jScrollPane5.setViewportView(lsCodbar);

        lbcantidad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbcantidad.setText("Cantidad (unidades)");

        txCantUnidad.setEditable(false);
        txCantUnidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txCantUnidad.setEnabled(false);

        jtCantidad.setText("Editar");
        jtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtCantidadActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Guardar en Disosur");
        jCheckBox1.setEnabled(false);

        jLabel24.setText("Cant. por Embalaje");

        txDisplay.setEditable(false);
        txDisplay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(328, 328, 328)
                        .addComponent(lbcantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(cbUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(150, 150, 150))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel24)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbSubFamilia, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbFamilia, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbConvenio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(cbOtroImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txTazaImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(txPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkIva)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox1)))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txCantUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21))
                            .addComponent(chkIva)
                            .addComponent(jCheckBox1)))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbOtroImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txTazaImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jLabel5))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbSubFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbcantidad)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jtCantidad)
                                    .addComponent(txCantUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24))))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        Pestanas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                PestanasStateChanged(evt);
            }
        });
        Pestanas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PestanasMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PestanasMousePressed(evt);
            }
        });

        jPanel3.setEnabled(false);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel14.setText("Precio Venta");

        txPNeto.setEditable(false);
        txPNeto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel28.setText("Mínimo");

        txMinimo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txMinimo.setEnabled(false);
        txMinimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txMinimoActionPerformed(evt);
            }
        });

        lbNuevoMinimo.setText("Nuevo Mínimo");

        txNuevoMinimo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txNuevoMinimo.setEnabled(false);
        txNuevoMinimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txNuevoMinimoActionPerformed(evt);
            }
        });
        txNuevoMinimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txNuevoMinimoKeyTyped(evt);
            }
        });

        btGuardarMinimo.setText("OK");
        btGuardarMinimo.setEnabled(false);
        btGuardarMinimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarMinimoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txPNeto, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                            .addComponent(txMinimo)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lbNuevoMinimo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txNuevoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chk_minimo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btGuardarMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txPNeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNuevoMinimo)
                        .addComponent(txNuevoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chk_minimo)
                    .addComponent(btGuardarMinimo))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        txStockInv.setEditable(false);
        txStockInv.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txStockTotal.setEditable(false);
        txStockTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel3.setText("Inventario");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Total");

        jLabel10.setText("Separado");
        jLabel10.setToolTipText("");

        txSeparado.setEditable(false);
        txSeparado.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel26.setText("En Transito");

        txTransito.setEditable(false);
        txTransito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txTransito.setToolTipText("");

        jLabel27.setText("Ubicados");

        txUbicados.setEditable(false);
        txUbicados.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txUbicados.setToolTipText("");

        btUbica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/search16.png"))); // NOI18N
        btUbica.setToolTipText("");
        btUbica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUbicaActionPerformed(evt);
            }
        });

        jLabel4.setText("Nota Pedido");

        txStockOCP.setEditable(false);
        txStockOCP.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btEd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/Pencil16.png"))); // NOI18N
        btEd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEdActionPerformed(evt);
            }
        });

        btGuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/save16.png"))); // NOI18N
        btGuar.setEnabled(false);
        btGuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txTransito, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txUbicados, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btUbica)
                                .addGap(102, 102, 102))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txStockOCP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(txStockInv, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btEd, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btGuar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txSeparado, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txStockTotal)
                        .addGap(27, 27, 27))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(txStockInv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEd)
                    .addComponent(btGuar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel27)
                    .addComponent(txUbicados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btUbica))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel26)
                    .addComponent(txTransito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txStockOCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txSeparado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txStockTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbConvenioCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbConvenioCodActionPerformed(evt);
            }
        });

        cbFamiliaCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFamiliaCodActionPerformed(evt);
            }
        });

        cbSubFamiliaCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSubFamiliaCodActionPerformed(evt);
            }
        });

        cbOtroImpuestoId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOtroImpuestoIdActionPerformed(evt);
            }
        });

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Ubicaciones Inventario"));

        Grilla_Inv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Cod. Ubc", "Ubicación", "Sku", "Cant", "Unidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(Grilla_Inv);
        if (Grilla_Inv.getColumnModel().getColumnCount() > 0) {
            Grilla_Inv.getColumnModel().getColumn(0).setMinWidth(80);
            Grilla_Inv.getColumnModel().getColumn(0).setPreferredWidth(80);
            Grilla_Inv.getColumnModel().getColumn(0).setMaxWidth(80);
            Grilla_Inv.getColumnModel().getColumn(2).setMinWidth(75);
            Grilla_Inv.getColumnModel().getColumn(2).setPreferredWidth(75);
            Grilla_Inv.getColumnModel().getColumn(2).setMaxWidth(75);
            Grilla_Inv.getColumnModel().getColumn(3).setMinWidth(50);
            Grilla_Inv.getColumnModel().getColumn(3).setPreferredWidth(50);
            Grilla_Inv.getColumnModel().getColumn(3).setMaxWidth(50);
            Grilla_Inv.getColumnModel().getColumn(4).setMinWidth(85);
            Grilla_Inv.getColumnModel().getColumn(4).setPreferredWidth(85);
            Grilla_Inv.getColumnModel().getColumn(4).setMaxWidth(85);
        }

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Pedidos");

        txStockOCC.setEditable(false);
        txStockOCC.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbOtroImpuestoId, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbOtroImpuestoTaza, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbUnidadId, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbFamiliaCod, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbConvenioCod, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbSubFamiliaCod, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbConvenioSku, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbFamiliaSku, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbSubFamiliaSku, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txStockOCC, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbConvenioSku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbFamiliaSku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbSubFamiliaSku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbConvenioCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbFamiliaCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbSubFamiliaCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txStockOCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbOtroImpuestoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbOtroImpuestoTaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbUnidadId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Pestanas.addTab("Datos", jPanel3);

        jPanel2.setBackground(new java.awt.Color(220, 215, 226));
        jPanel2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel2FocusGained(evt);
            }
        });

        GrillaMovimientos.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        GrillaMovimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Fecha", "Hora", "Usuario", "Documento", "Numero", "Cantidad", "Stock Antes", "Stock Despues", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(GrillaMovimientos);
        if (GrillaMovimientos.getColumnModel().getColumnCount() > 0) {
            GrillaMovimientos.getColumnModel().getColumn(0).setMinWidth(85);
            GrillaMovimientos.getColumnModel().getColumn(0).setPreferredWidth(85);
            GrillaMovimientos.getColumnModel().getColumn(0).setMaxWidth(85);
            GrillaMovimientos.getColumnModel().getColumn(1).setMinWidth(75);
            GrillaMovimientos.getColumnModel().getColumn(1).setPreferredWidth(75);
            GrillaMovimientos.getColumnModel().getColumn(1).setMaxWidth(75);
            GrillaMovimientos.getColumnModel().getColumn(2).setMinWidth(90);
            GrillaMovimientos.getColumnModel().getColumn(2).setPreferredWidth(90);
            GrillaMovimientos.getColumnModel().getColumn(2).setMaxWidth(90);
            GrillaMovimientos.getColumnModel().getColumn(3).setMinWidth(90);
            GrillaMovimientos.getColumnModel().getColumn(3).setPreferredWidth(90);
            GrillaMovimientos.getColumnModel().getColumn(3).setMaxWidth(90);
            GrillaMovimientos.getColumnModel().getColumn(4).setMinWidth(90);
            GrillaMovimientos.getColumnModel().getColumn(4).setPreferredWidth(90);
            GrillaMovimientos.getColumnModel().getColumn(4).setMaxWidth(90);
            GrillaMovimientos.getColumnModel().getColumn(5).setMinWidth(80);
            GrillaMovimientos.getColumnModel().getColumn(5).setPreferredWidth(80);
            GrillaMovimientos.getColumnModel().getColumn(5).setMaxWidth(80);
            GrillaMovimientos.getColumnModel().getColumn(6).setMinWidth(80);
            GrillaMovimientos.getColumnModel().getColumn(6).setPreferredWidth(80);
            GrillaMovimientos.getColumnModel().getColumn(6).setMaxWidth(80);
            GrillaMovimientos.getColumnModel().getColumn(7).setMinWidth(80);
            GrillaMovimientos.getColumnModel().getColumn(7).setPreferredWidth(80);
            GrillaMovimientos.getColumnModel().getColumn(7).setMaxWidth(80);
            GrillaMovimientos.getColumnModel().getColumn(8).setMinWidth(90);
            GrillaMovimientos.getColumnModel().getColumn(8).setPreferredWidth(90);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1024, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2))
        );

        Pestanas.addTab("Movimientos", jPanel2);

        GrillaCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Fecha", "Rut", "Proveedor", "Nro Orden", "Cantidad", "Recibido", "Val.Unidad", "Val.Rebajado", "Val.Total", "Objetivo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        GrillaCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GrillaComprasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(GrillaCompras);

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 127, 80));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("REBAJA TODO EL DOCUMENTO");

        jTextField2.setEditable(false);
        jTextField2.setBackground(java.awt.Color.pink);
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("DESCUENTO A TODO EL DOCUMENTO");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setEditable(false);
        jTextField3.setBackground(java.awt.Color.orange);
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("DESCUENTO AL ITEM");

        jTextField4.setEditable(false);
        jTextField4.setBackground(java.awt.Color.white);
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText("SIN NOTA CREDITO ASOCIADA");

        GrillaFacturasC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Rut", "Nro Factura.", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        GrillaFacturasC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GrillaFacturasCMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(GrillaFacturasC);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 32, Short.MAX_VALUE))
        );

        Pestanas.addTab("OC Proveedores", jPanel9);

        pnMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnMenu.setPreferredSize(new java.awt.Dimension(800, 80));
        pnMenu.setVerifyInputWhenFocusTarget(false);

        btNuevo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/add16.png"))); // NOI18N
        btNuevo.setText("Nuevo");
        btNuevo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btNuevo.setBorderPainted(false);
        btNuevo.setEnabled(false);
        btNuevo.setFocusPainted(false);
        btNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNuevoActionPerformed(evt);
            }
        });

        btGuardar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/save16.png"))); // NOI18N
        btGuardar.setText("Guardar");
        btGuardar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btGuardar.setBorderPainted(false);
        btGuardar.setEnabled(false);
        btGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarActionPerformed(evt);
            }
        });

        btCancelar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/Cancel16.png"))); // NOI18N
        btCancelar.setText("Cancelar");
        btCancelar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btCancelar.setBorderPainted(false);
        btCancelar.setEnabled(false);
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        btEditar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/Pencil16.png"))); // NOI18N
        btEditar.setText("Editar");
        btEditar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btEditar.setBorderPainted(false);
        btEditar.setEnabled(false);
        btEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarActionPerformed(evt);
            }
        });

        btEliminar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/trush16.png"))); // NOI18N
        btEliminar.setText("Eliminar");
        btEliminar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btEliminar.setBorderPainted(false);
        btEliminar.setEnabled(false);
        btEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEliminarActionPerformed(evt);
            }
        });

        lbcargando.setText("Cargando");
        lbcargando.setEnabled(false);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/Imagen16.png"))); // NOI18N
        jButton5.setEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(lbcargando, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jButton5)
                .addGap(460, 460, 460))
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnMenuLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton5))
                    .addGroup(pnMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbcargando))))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout pnDetalleLayout = new javax.swing.GroupLayout(pnDetalle);
        pnDetalle.setLayout(pnDetalleLayout);
        pnDetalleLayout.setHorizontalGroup(
            pnDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 1250, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnDetalleLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(skulabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addComponent(Pestanas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnDetalleLayout.setVerticalGroup(
            pnDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(skulabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnDetalleLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Pestanas, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        add(pnDetalle);
    }// </editor-fold>//GEN-END:initComponents
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
private void CargaOtrosImpuestos(){
    ExeSql Sql = new ExeSql();
    ResultSet Rs;
    
    try {
        cbOtroImpuesto.removeAllItems();
        cbOtroImpuestoId.removeAllItems();
        
        Rs = Sql.Select("select *\n" +
                        "from par_impuesto\n" +
                        "order by codigo");
        while(Rs.next()){
            cbOtroImpuestoId.addItem(Rs.getInt("codigo"));
            cbOtroImpuestoTaza.addItem(Rs.getDouble("taza"));
            cbOtroImpuesto.addItem(Rs.getString("impuesto"));
        }
    } catch (Exception e) {
    }
}
    
    private void CargaMovimientos (String Codigo){
    
        ExeSql SqlEcona = new ExeSql();
        DefaultTableModel dftm = (DefaultTableModel) GrillaMovimientos.getModel();
        ResultSet Rs;
    
    // Limpia Grilla
        while (dftm.getRowCount() > 0) {
            dftm.removeRow(0);
        }

        try {
            Rs = SqlEcona.Select("select m.fecha,cast(m.hora as time) as hora,m.usuario,m.tipdocto,m.nrodocto,m.cantidad,m.stockini,m.stockfin,m.tipomovimiento \n"+
                                 "from producto_valores pv \n" +
                                 "left join movimientos_stock m on pv.sku=m.sku \n" +
                                 "where pv.sku='" + Codigo + "'\n"+
                                 "order by m.id desc");
            while (Rs.next()) {
            
                dftm.addRow(new Object[]{
                                        Rs.getDate("fecha"),
                                        Rs.getTime("hora"),
                                        Rs.getString("usuario"),
                                        Rs.getString("tipdocto"),
                                        Rs.getString("nrodocto"),
                                        fmMain.FormatoNumero(Rs.getDouble("cantidad")),
                                        fmMain.FormatoNumero(Rs.getDouble("stockini")),
                                        fmMain.FormatoNumero(Rs.getDouble("stockfin")),
                                        Rs.getString("tipomovimiento")});
            }
        } catch (Exception e) {
        } finally{
            SqlEcona.Close();
        }
    }


    
   
//--------------------------------------------------------------------------------
// Habilita o deshabilita edicion de productos
//--------------------------------------------------------------------------------
private void GeneraCodigo() {
    ExeSql Sql = new ExeSql();
    ResultSet Rs;
    String Codigo;
    String Convenio;
    String Familia;
    String SubFamilia;
    String Contador;
    try {
        if (Tipo == 1 && cbConvenio.getSelectedIndex() > -1 && cbFamilia.getSelectedIndex() > -1 && cbSubFamilia.getSelectedIndex() > -1) {
            SubFamilia = cbSubFamiliaCod.getSelectedItem().toString().trim();
            Rs = Sql.Select("select contador from par_sublinea where codigo=" + SubFamilia);
            Rs.next();
            if (Rs.getInt("contador") < 10) {
                Contador = "00" + String.valueOf(Rs.getInt("contador"));
            } else if (Rs.getInt("contador") < 100) {
                Contador = "0" + String.valueOf(Rs.getInt("contador"));
            } else {
                Contador = String.valueOf(Rs.getInt("contador"));
            }

            Convenio = cbConvenioSku.getSelectedItem().toString().trim();
            Familia = cbFamiliaSku.getSelectedItem().toString().trim();
            SubFamilia = cbSubFamiliaSku.getSelectedItem().toString().trim();

            Codigo = Convenio + Familia + SubFamilia + Contador;
            txSku.setText(Codigo);
        }
    } catch (Exception e) {
        System.out.println(e);
    } finally{
        Sql.Close();
    }
}
//--------------------------------------------------------------------------------
// Habilita o deshabilita edicion de productos
//--------------------------------------------------------------------------------
private void Limpia(){
    int RespTipo=Tipo;
    
    
    
    
    //Limpia Grilla Movimientos
    DefaultTableModel ModelMov = (DefaultTableModel) GrillaMovimientos.getModel();
    while(ModelMov.getRowCount()>0)
        ModelMov.removeRow(0);
    
    //Limpia Grilla Compras
   
    
    Tipo=0;
    txSku.setText("");
    txNombre.setText("");
    //txCodBar.setText("");
    //txCodBar2.setText("");
    txPeso.setText("");
   
  
    txPNeto.setText("");
   
    txStockInv.setText("");
   
    txStockOCC.setText("");
    txStockOCP.setText("");
   
    txStockTotal.setText("");
    txSeparado.setText("");
    
    txMinimo.setText("");
    
    cbFamilia.setSelectedIndex(-1);
    cbSubFamilia.setSelectedIndex(-1);
    cbUnidad.setSelectedIndex(-1);
    cbConvenio.setSelectedIndex(-1);
  
    txNuevoMinimo.setText("");
    chk_minimo.setSelected(false);
    lsCodbar.removeAll();
    
      
    
    if (lsCodbar.isEnabled() && lsCodbar.isVisible()){
    DefaultListModel limpiaLsChilecompras = new DefaultListModel();    

    }
    
    
    txUbicados.setText("");
    txTransito.setText("");
    
   
    txMinimo.setBackground(Color.white);
    Tipo=RespTipo;
}
//--------------------------------------------------------------------------------
// Habilita o deshabilita edicion de productos
//--------------------------------------------------------------------------------
private void Habilita(boolean Estado){
//    cbLinea.setEnabled(Estado);
//    cbSubLinea.setEnabled(Estado);
//    cbUnidad.setEnabled(Estado);
    
    txNombre.setEnabled(Estado);
    //txCodBar2.setEnabled(Estado);
    txPeso.setEnabled(Estado);
    
    Pestanas.setEnabled(Estado);
 
  
    txPNeto.setEnabled(Estado);
 
    txStockInv.setEnabled(Estado);
    
    txStockOCC.setEnabled(Estado);
    txStockOCP.setEnabled(Estado);
   
    txStockTotal.setEnabled(Estado);
   
    txDisplay.setEnabled(Estado);
    
    txTazaImpuesto.setEnabled(Estado);
    
   
    cbConvenio.setEnabled(Estado);
    cbFamilia.setEnabled(Estado);
    cbSubFamilia.setEnabled(Estado);

    cbUnidad.setEnabled(Estado);
    txSeparado.setEnabled(Estado);
    
    cbOtroImpuesto.setEnabled(Estado);
   
    btUbica.setEnabled(Estado);
    txUbicados.setEnabled(Estado);
    txTransito.setEnabled(Estado);

    lsCodbar.setVisible(Estado);
    
    lsCodbar.setEnabled(Estado);
   
    chkIva.setEnabled(true);
    
}
//--------------------------------------------------------------------------------
// Habilita o deshabilita edicion de productos
//--------------------------------------------------------------------------------
private void Edicion(boolean Estado){
    txNombre.setEditable(Estado);
    //txCodBar2.setEditable(Estado);
    txPeso.setEditable(Estado);
    txDisplay.setEditable(Estado);
  
    
    btGuardar.setEnabled(Estado);
    btCancelar.setEnabled(Estado);
  
}
//--------------------------------------------------------------------------------
// Carga combo UNIDAD DE MEDIDA
//--------------------------------------------------------------------------------
private void CargaUnidades() {
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        DefaultComboBoxModel cbmUnidad,cbmUnidadId;
        cbmUnidad   = new DefaultComboBoxModel();
        cbmUnidadId = new DefaultComboBoxModel();
        
        cbUnidad.setModel(cbmUnidad);
        cbUnidadId.setModel(cbmUnidadId);
        try {
            Rs = Sql.Select("select codigo,trim(unidad) as unidad from par_unidad");
            while (Rs.next()) {
                cbUnidad.addItem(Rs.getString("unidad"));
                cbUnidadId.addItem(Rs.getString("codigo"));
                //cbmUnidad.addElement(Rs.getString("unidad"));
//                cbmUnidadId.addElement(Rs.getString("codigo").trim());
            }
           // cbUnidad.setSelectedIndex(-1);
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            Sql.Close();
        }
    }
//--------------------------------------------------------------------------------
// Carga OTROS IMPUESTOS
//--------------------------------------------------------------------------------
//--------------------------------------------------------------------------------
// Carga CONVENIOS
//--------------------------------------------------------------------------------
//--------------------------------------------------------------------------------
// Carga CONVENIOS
//--------------------------------------------------------------------------------
private void CargaConvenios() {
        ExeSql Sql = new ExeSql();
        ResultSet Rs;

        try {
            Rs = Sql.Select("select codigo,convenio,cod from par_convenio order by codigo");
            while (Rs.next()) {
                cbConvenio.addItem(Rs.getString("convenio"));
                cbConvenioCod.addItem(Rs.getString("codigo"));
                cbConvenioSku.addItem(Rs.getString("cod"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(fmMain.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Sql.Close();
        }
    }
//--------------------------------------------------------------------------------
// Carga LINEAS
//--------------------------------------------------------------------------------
private void CargaLineas() {
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        int TipoAux;
        
        cbFamilia.removeAllItems();
        cbFamiliaCod.removeAllItems();
        cbFamiliaSku.removeAllItems();
        TipoAux=Tipo;
        
        
        try {
            if(Tipo==1 && cbConvenioCod.getSelectedIndex()>-1)
                Rs = Sql.Select("select codigo,linea,cod from par_linea where convenio = "+ cbConvenioCod.getSelectedItem().toString().trim() +" order by codigo");
            else
                Rs = Sql.Select("select codigo,linea,cod from par_linea order by codigo");
            Tipo=-99;
            // Recorre Query
            while (Rs.next()) {
                // Carga lineas en Combo
                cbFamilia.addItem(Rs.getString("linea"));
                cbFamiliaCod.addItem(Rs.getString("codigo"));
                cbFamiliaSku.addItem(Rs.getString("cod"));
            }
            
        } catch (Exception ex) {
            System.out.println(ex);
        }finally{
            Sql.Close();
            Tipo=TipoAux;
        }
       
    }
//--------------------------------------------------------------------------------
// Carga PRODUCTOS
//--------------------------------------------------------------------------------
public void CargaProducto(String Codigo) {
        ExeSql SqlSala = new ExeSql();
        ExeSql SqlSala3 = new ExeSql();
        ExeSqlLuvaly SqlLuv = new ExeSqlLuvaly();
        
        ResultSet RsLuv = null;
        ResultSet RsSala = null;
        ResultSet RsSala2 = null;
        ResultSet RsSala3 = null;
        
        Tipo = 0;
        double Margen;
        double calculoMinimo = 0;
        
        double cost_redon = 0;
        double cost_vent_iva = 0;
        
        
        int revisa_codbar =0;
        int revisa_codchile =0;
        int valorMinimo = 0;
        
        
        int TotalUbicados = 0;
        int TotalTransito = 0;
        int TotalSeparados = 0;
        int TotalStock = 0;
        
        
        //Trabajando con Listas
        JList lista = new JList();
        DefaultListModel modelo2 = new DefaultListModel();
        DefaultListModel modelo = new DefaultListModel();
        

        cbUnidad.setEnabled(true);
        cbConvenio.setEnabled(true);
        cbFamilia.setEnabled(true);
        cbSubFamilia.setEnabled(true);
        lsCodbar.setVisible(true);
        
        txUbicados.setEnabled(true);
        txTransito.setEnabled(true);
        btUbica.setEnabled(true);
        txMinimo.setEnabled(true);
       
     
        try {
            String QueryLuvaly = "";
            String QuerySala = "";
            String QuerySala2 = "";
            String QuerySala3 = "";
          
            modelo.clear();
            modelo2.clear();
            
            lsCodbar.setModel(modelo);
          

            RsLuv = SqlLuv.Select("SELECT codbar, sku FROM codbar WHERE sku='" + Codigo + "' OR codbar='"+ Codigo + "'" );        
                    if (RsLuv.next())
                        {
                        Codigo = RsLuv.getString("sku").trim();
                        revisa_codbar++;
                        }
            RsLuv = SqlLuv.Select("SELECT idch, sku FROM codchile WHERE sku='" + Codigo +"' OR idch='"+ Codigo + "'" );
                    if (RsLuv.next())
                        {
                        Codigo = RsLuv.getString("sku").trim();
                        revisa_codchile++;
                        }

             
            System.out.println("revisa_codbar :"+revisa_codbar);
            System.out.println("revisa_codchile :"+revisa_codchile);
                    
                    
            if (revisa_codbar>=1){
                 
                RsLuv = SqlLuv.Select("SELECT c.codbar, CASE WHEN p.nombre is null THEN 'nulo' ELSE p.nombre END FROM codbar c \n"+
                                      "LEFT JOIN proveedor p ON c.rutprv=p.rut WHERE c.sku='" + Codigo +"'");
                modelo.clear();
                String prove="";
                 
                while (RsLuv.next()){
                    
                     if (RsLuv.getString("nombre").trim().equals("nulo")){
                    
                        prove="";
                    
                    }else{
                        
                        prove=" - "+RsLuv.getString("nombre").trim();
                         
                    }
                    
                    modelo.addElement(RsLuv.getString("codbar").trim()+prove);
                }
            }
            
            if (revisa_codchile>=1){
                
                RsLuv = SqlLuv.Select("select idch from codchile where sku='" + Codigo +"'");
                modelo2.clear();
                
                while (RsLuv.next()){
                
                    modelo2.addElement(RsLuv.getString("idch").trim());
                }

            
            }else {
                
                RsLuv = SqlLuv.Select("SELECT c.idch ,c.sku FROM codchile as c \n"+
                                      "WHERE c.sku=(select sku_rel FROM codproducto_rel WHERE sku ='"+Codigo+"') ");
                
                while (RsLuv.next()){
                    
                    modelo2.addElement(RsLuv.getString("idch").trim()+"-"+"rel");
                   
                }
                
            }
           
            
            QueryLuvaly ="select p.sku, p.nombre,p.unidad,p.linea,p.sublinea,p.codbar,p.codbar2,p.display,p.peso, \n"+
                         "cast(p.fcreacion as date) as fcreacion,p.usuariocreacion,p.otroimpuesto, \n"+
                         "p.imptoiva,p.convenio,p.otroimpuesto,p.marca, p.minimo, p.calcula_minimo,p.pventa_web2 \n"+
                         "from producto p\n"+
                         "where p.sku='" + Codigo + "'";
            
            
            RsLuv = SqlLuv.Select(QueryLuvaly);  
              
            if(SqlLuv.GetRowCount() >0){
              
                    RsLuv.next();
                    
                    cost_vent_iva = RsLuv.getInt("pventa_web2");
                    Codigo = RsLuv.getString("sku").trim();
                        skulabel.setText(Codigo);
                        String Nombre = RsLuv.getString("nombre");
                        
                        if (Nombre.length() > 20) {
                            Nombre.substring(0, 20);//***
                        }
            
                        
            
                        ResultSet producto = SqlLuv.Select("select p.nombre, u.um from producto p \n"
                                            + "LEFT JOIN par_unidad u ON u.codigo=p.unidad\n "
                                            + "where p.sku = '"+RsLuv.getString("sku").trim()+"'");
                        producto.next();
            
            
                        fmMain.pnPestanas.setTitleAt(fmMain.pnPestanas.getSelectedIndex(), Nombre);
                        fmMain.pnPestanas.repaint();
                        txSku.setText(RsLuv.getString("sku"));
                        txNombre.setText(ElTrim(producto.getString("nombre")));
                        txDisplay.setText(ElTrim(RsLuv.getString("display")));
                        txPeso.setText(String.valueOf(RsLuv.getDouble("peso")));
                    
                    
      //*****************************************************************************************************************************************//
                    
                    QuerySala ="select pv.sku, i.stock,i.ocp,i.occ,i.gdc, pv.pventa_ant,pv.valultcompra,pv.costofinal,pv.minimo,pv.calcula_minimo, \n"+
                                "(select sum(separado) from occhdet where sku=pv.sku) as separado, pv.margen, pv.esoferta \n"+
                                "from producto_valores pv\n"+
                                "left join inventario_sala i on pv.sku=i.sku\n"+
                                "where pv.sku='" + Codigo + "'";
                  
                    RsSala = SqlSala.Select(QuerySala);  
                   
                    if(SqlSala.GetRowCount() >0){
                                        
                        RsSala.next();
                    
                        //txStockInv.setText(RsEcona.getString("stock"));
           
                        txStockOCC.setText(RsSala.getString("occ"));
                        txStockOCP.setText(RsSala.getString("ocp"));
          
                        
                        //double StockTotal = RsSala.getDouble("stock") + RsSala.getDouble("occ") + RsSala.getDouble("gdc");
                      
                       
                        
                        TotalSeparados = RsSala.getInt("separado");
                        txSeparado.setText(RsSala.getString("separado"));
                        
                        
                       
                        
                        
                        //txPNeto.setText(RsEcona.getString("pventa_ant"));
                        
                         cost_redon = cost_vent_iva%10;   //Extrae el el utlimo digito de la cifra
            

            
                        double dif = 10-cost_redon;
            
                        if (cost_redon >= 5){
            
                            cost_vent_iva = cost_vent_iva + dif;
                
            
                        }else if (cost_redon < 5 && cost_redon > 0) {
            
                            cost_vent_iva = cost_vent_iva + dif;
            
                        }
               
                        
                        txPNeto.setText(""+Math.round(cost_vent_iva));
                        
                        
                        
                   
            
            //MOSTRAR CAMPOS PARA EDICIÓN DEL MINIMO
                int pruebaUusario = 85;
                int intNivelUsuario = 0;
                intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
                if( intNivelUsuario >= pruebaUusario){
                    lbNuevoMinimo.setVisible(true);
                    txNuevoMinimo.setVisible(true);
                    btGuardarMinimo.setVisible(true);
                    chk_minimo.setVisible(true);
                }else{
                      lbNuevoMinimo.setVisible(false);
                    txNuevoMinimo.setVisible(false);
                    btGuardarMinimo.setVisible(false);
                    chk_minimo.setVisible(false);
                }
           
            
            //-----------------------------  trae Transito y Ubicados-----------
                QuerySala ="select \n" +
                            "case sum(cant) when null then 0 else sum(cant) end as cantidad\n" +
                            "from mt_productos\n" +
                            "where sku = '" + Codigo + "'\n" +
                            "and ubicacion not in  ('" +  fmMain.BodegaOCDirecta() +   "','"  + fmMain.BodegaTransito() + "','"  + fmMain.BodegaAnticipada() + "','"  + 
                                                          fmMain.BodegaNegativos() + "','"+fmMain.BodegaNCP()+"','" + fmMain.BodegaPositivos() + "')";
                   
          
                RsSala2 = SqlSala.Select(QuerySala);
            
                if (RsSala2.next()){
                    
                    TotalUbicados = RsSala2.getInt("cantidad");
                    
                    txUbicados.setText(String.valueOf(RsSala2.getDouble("cantidad")));
                }
            
                QuerySala2 ="select \n" +
                             "case sum(cant) when null then 0 else sum(cant) end as cantidad \n" +
                             "from mt_productos \n" +
                             "where sku = '" + Codigo + "' \n" +
                            "and ubicacion = '" + fmMain.BodegaTransito() + "'";
                                
                RsSala2 = SqlSala.Select(QuerySala2);
            
                if (RsSala2.next()){
                    
                    TotalTransito = RsSala2.getInt("cantidad");
                    txTransito.setText(String.valueOf(RsSala2.getDouble("cantidad")));
                }
                
                
                
                QuerySala3 ="select \n" +
                            "case sum(cant) when null then 0 else sum(cant) end as cantidad \n" +
                            "from mt_productos \n" +
                            "where sku = '" + Codigo + "' \n" +
                            "and (ubicacion like '%SAL.%' or ubicacion IN ('TRAN.1007.2'))";
                
                RsSala3 = SqlSala3.Select(QuerySala3);
                
                if (RsSala3.next()){
                 
                    txStockInv.setText(String.valueOf(RsSala3.getDouble("cantidad")));
                 
                 
                }
                
                 double StockTotal = Double.parseDouble(txStockInv.getText().trim());
                 
                 
                  //******************************************************************//    
                        
                         valorMinimo = 0;
                        if( RsSala.getString("minimo") == null){
                            
                            valorMinimo = 0;
                        
                        }else{
                        
                            valorMinimo = RsSala.getInt("minimo");
                        }
                        calculoMinimo = ((valorMinimo*80)/100);
            
                    // carga el check_minimo
                        if (RsSala.getBoolean("calcula_minimo")){
                
                            chk_minimo.setSelected(true);
                        }else{
                
                            chk_minimo.setSelected(false);
                        }
                        
         
                        if(StockTotal > valorMinimo){
                            
                            txMinimo.setBackground(Color.green);
                        
                        }else if(StockTotal < valorMinimo && StockTotal > calculoMinimo){
                            
                            txMinimo.setBackground(Color.yellow);
            
                        }else if (StockTotal <= calculoMinimo){
                            txMinimo.setBackground(Color.red);
                        }
                        
                        
                        txMinimo.setText(Integer.toString(valorMinimo));
                           
                    //*******************************************************************//    
                
                
                
                
               
                //------------------------------------------------------------------
            
            }else if(SqlSala.GetRowCount() == 0){
                    
                
                if(fmMain.OkCancel("Producto no está creado en SALA, ¿Desea Crearlo?")== JOptionPane.OK_OPTION){
                
                    
                    ExeSql Sql = new ExeSql();
                    ExeSql Sql2 = new ExeSql();
                    ExeSql Sql3 = new ExeSql();
        
                    ResultSet Rs3 = null;

                    String Query, Query2, Query3;   
                    String Sku = txSku.getText().trim();
                    
                    
                    try {    
                        
                            boolean esoferta = false;
                            double margen = 0;
                            int cant = 0;
                            
                            Query3 = "SELECT * FROM producto_valores WHERE sku ='" + txSku.getText().trim()+"'";
                            Rs3 = Sql3.Select(Query3);
                            
                            if (Sql3.GetRowCount() == 0){
                            
                                Query = "INSERT INTO producto_valores (sku,valultcompra,costofinal,margen,esoferta) VALUES ('" + txSku.getText().trim()+"',"+
                                                                   0+","+0+","+margen +","+esoferta+")";
                                Sql.ExeSql(Query);
                                Sql.Commit();
                    
                            }
                            
                            
//                            Query3 = "SELECT sum(cant) as cant FROM mt_productos WHERE sku ='" + txSku.getText().trim()+"' AND ubicacion like '%SAL.%'";
//                            Rs3 = Sql3.Select(Query3);
//                            
//                            if (Sql3.GetRowCount() > 0){
//                                
//                                Rs3.next();
//                                
//                                cant = Rs3.getInt("cant");
//                            
//                            
//                            }else if (Sql3.GetRowCount() == 0) {
//                            
//                                cant = 0;
//                            
//                            }
                            
                            
//                            Query3 = "SELECT * FROM inventario_sala WHERE sku ='" + txSku.getText().trim()+"'";
//                            Rs3 = Sql3.Select(Query3);
//                            
//                            if (Sql3.GetRowCount() == 0){
//                                
//                               Query2 = "INSERT INTO inventario_sala (sku, stock,ocp) VALUES ('" + txSku.getText().trim()+"',"+cant+","+0+")";
//                                
//                               Sql2.ExeSql(Query2);
//                               Sql2.Commit();
//                            
//                            }
                            

                            JOptionPane.showMessageDialog(null, "Producto Guardado");

                            SetTipo(-1);
                            txSku.setText(Sku);
                            btIr.doClick();
                        
              
                    
                    } catch (SQLException e) {
                        Sql.Rollback();
                        Sql2.Rollback();
    
                        Logger.getLogger(pfProductos.class.getName()).log(Level.SEVERE, null, e);
                
                    } finally {
                        Sql.Close();
                        Sql2.Close();

                    }
                
                }
                    
            }    
                    
                    
           //*************************************************************************************************************************************************************//         
            
                    
             //IVA
            
                cbUnidadId.setSelectedItem(RsLuv.getString("unidad").trim());
                cbUnidad.setSelectedIndex(cbUnidadId.getSelectedIndex());
            
                cbConvenioCod.setSelectedItem(RsLuv.getString("convenio").trim());
                cbConvenio.setSelectedIndex(cbConvenioCod.getSelectedIndex());
            
                cbFamiliaCod.setSelectedItem(RsLuv.getString("linea").trim());
                cbFamilia.setSelectedIndex(cbFamiliaCod.getSelectedIndex());
            
                cbSubFamiliaCod.setSelectedItem(RsLuv.getString("sublinea").trim());
                cbSubFamilia.setSelectedIndex(cbSubFamiliaCod.getSelectedIndex());
            
                cbOtroImpuestoId.setSelectedItem(RsLuv.getInt("otroimpuesto"));
                cbOtroImpuesto.setSelectedIndex(cbOtroImpuestoId.getSelectedIndex());

                PosUM       = cbUnidad.getSelectedIndex();
                PosLinea    = cbFamilia.getSelectedIndex();
                PosSubLinea = cbSubFamilia.getSelectedIndex();
                PosConvenio = cbConvenio.getSelectedIndex();
                PosImpuesto = cbOtroImpuesto.getSelectedIndex();        
                   
            
          }else {
              
              
                  fmMain.Mensaje("Producto no existe !!");
                  return;
              
              
         }
            
           SetTipo(2);
           Tipo = 1;
            
            Pestanas.setEnabled(true);
            txPNeto.setEnabled(false);
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(pfProductos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            SqlLuv.Close();
        }
    }



    
    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        if(Tipo==1 || Tipo==3){
            if(JOptionPane.showConfirmDialog(null, "¿Cancelar sin guardar?")==JOptionPane.YES_OPTION){
                if(Tipo==1)
                     SetTipo(-1);
                else
                    CargaProducto(txSku.getText());
                    SetTipo(-1);
                    txNuevoMinimo.setEnabled(false);
                    btGuardarMinimo.setEnabled(false);
          
                    
            }
        }
       else
            SetTipo(-1);
    }//GEN-LAST:event_btCancelarActionPerformed

    private void cbFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFamiliaActionPerformed
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        int AuxTipo;
        if (Tipo == 2 || Tipo == -1 || Tipo == 3)
        
            cbFamilia.setSelectedIndex(PosLinea);
        
        else if (cbFamilia.getSelectedIndex() > -1 && Tipo != -99) {

            cbFamiliaCod.setSelectedIndex(cbFamilia.getSelectedIndex());

            // Carga Combo Linea
            if (cbFamilia.getSelectedIndex() >= 0) {
                int CodLinea = Integer.valueOf(cbFamiliaCod.getSelectedItem().toString());

                cbSubFamilia.removeAllItems();
                cbSubFamiliaCod.removeAllItems();
                cbSubFamiliaSku.removeAllItems();
                AuxTipo=Tipo;
                Tipo=-99;
                try {
                    Rs = Sql.Select("select codigo,sublinea,cod from par_sublinea where linea = " + CodLinea);
                    System.out.println("select codigo,sublinea,cod from par_sublinea where linea = " + CodLinea);
                    while (Rs.next()) {
                        cbSubFamilia.addItem(Rs.getString("sublinea"));
                        cbSubFamiliaCod.addItem(Rs.getString("codigo"));
                        cbSubFamiliaSku.addItem(Rs.getString("cod"));
                    }
                    Tipo=AuxTipo;
                } catch (SQLException ex) {
                    System.out.println(ex);
                } finally {
                    Sql.Close();
                }
            }
            GeneraCodigo();
        }        
    }//GEN-LAST:event_cbFamiliaActionPerformed
    
    
    private void btIrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIrActionPerformed
        
        Runnable miRunnable = new Runnable() {
            public void run() {
                try{

                    lbcargando.setEnabled(true);
                    lbcargando.setVisible(true);

                    Pestanas.setSelectedIndex(0);
                    carga_producto();
                    jButton5.setEnabled(true);

                    lbcargando.setVisible(false);
                    lbcargando.setEnabled(false);
                    
                    carga_ubicainv(txSku.getText().trim());
                    
//                    btGuardar.setEnabled(true);
//                    btCancelar.setEnabled(true);
                  
                    
                } catch (Exception e) { 
                    e.printStackTrace();
                }
            }
        };
        Thread hilo = new Thread(miRunnable);
        hilo.start();
        
            
        lbcargando.setText("Cargando.....");
        URL urlInfo =  this.getClass().getResource("/Iconos16/wait.gif");
        ImageIcon IconoInfo =  new ImageIcon(urlInfo); 
        lbcargando.setIcon(IconoInfo);
        lbcargando.setForeground(Color.red);  
    }//GEN-LAST:event_btIrActionPerformed

    private void carga_ubicainv(String Sku){
    
        try {
            ExeSql Sql = new ExeSql();
            ResultSet Rs = null;
            DefaultTableModel TableModel2 = (DefaultTableModel) Grilla_Inv.getModel();
            
            while(TableModel2.getRowCount()>0){
                 TableModel2.removeRow(0);
            }
            
            
            Rs = Sql.Select("SELECT mt.ubicacion, cm.nombre, mt.sku, mt.cant, pu.unidad FROM mt_productos mt \n"+
                            "LEFT JOIN mt_codmetro cm ON mt.ubicacion = cm.codmetro "+
                            "LEFT JOIN producto p ON mt.sku = p.sku \n"+
                            "LEFT JOIN par_unidad pu ON pu.codigo = p.unidad \n"+
                            "WHERE mt.sku ='"+Sku+"' AND mt.cant <> 0");
            
            if (Sql.GetRowCount() > 0){
            
                while (Rs.next()){
                
                            TableModel2.addRow(new Object[] {Rs.getString("ubicacion"),Rs.getString("nombre"),Rs.getString("sku"),Rs.getString("cant"), Rs.getString("unidad")});        
                
                
                }
                
            }
            
             Grilla_Inv.setDefaultRenderer(Object.class, new Elrender()); 
            
        } catch (SQLException ex) {
            Logger.getLogger(pfProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    
    
    
    private void carga_producto(){
        String codbarfinal = txSku.getText().replace("'", "-");
        txSku.setText(codbarfinal.trim());
        
        
           // if(!txSku.getText().isEmpty() && Tipo != 1){
             if(!txSku.getText().isEmpty()){    
            
                if(CheckIfExist(txSku.getText()) || CheckIDCH(txSku.getText()) || CheckCOD(txSku.getText())){
                
                    CargaProducto(txSku.getText());
                
                }else {
                 
                    JOptionPane.showMessageDialog(null, "No existe producto.");
                    txSku.setText("");
                    Tipo=0;
                    Limpia();
                    Tipo=-1;
                    Edicion(false);
                    Habilita(false);
                    lbcargando.setEnabled(false);
                }
            
            }else if(!txSku.getText().isEmpty() && Tipo == 1){
                txNombre.requestFocus();
                JOptionPane.showMessageDialog(null, "No existe producto.");
                    txSku.setText("");
                    Tipo=0;
                    Limpia();
                    Tipo=-1;
                    Edicion(false);
                    Habilita(false);
                    lbcargando.setEnabled(false);
            }else{
             
                jdBuscarProductos2 BP = new jdBuscarProductos2(null, true);
                BP.setLocationRelativeTo(null);
                BP.setTitle("Buscar Producto");
                BP.setVisible(true);
                BP.SetCotiza(false);
                if(!"".equals(BP.GetCodigo()))
                    CargaProducto(BP.GetCodigo());
            }
       
    }
    
    public void ActualizarMinimo(){
        ExeSql Sql = new ExeSql();
        ResultSet Rs = null, Rs1 = null;
        String Query;
        String Sku = txSku.getText();
        try {
            
                    Query = "UPDATE producto_valores SET\n" +
                            " minimo ="+ txNuevoMinimo.getText() + "," + "calcula_minimo = "  + chk_minimo.isSelected() +
                            " WHERE sku = '" + txSku.getText() + "'";

                    Sql.ExeSql(Query);
                    Sql.Commit();
                    JOptionPane.showMessageDialog(null, "Nuevo Mínimo Registrado");
                    CargaProducto(Sku);
                    txNuevoMinimo.setText("");
                } catch (Exception e) {
                    Sql.Rollback();
                    JOptionPane.showMessageDialog(null, "Error al guardar !!!:\n" + e.getMessage());
                    System.out.println(e);
                } finally {
                    Sql.Close();
                }
        
    }
    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        
         int NivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
         
         
         
         if (NivelUsuario < 100){
         
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado") ;
            return;
         
         }
        
        
        
        
        
        SetTipo(3);
        btEditar.setEnabled(false);
        txPNeto.setEnabled(true);
        txPNeto.setEditable(true);
       
        txNuevoMinimo.setEnabled(true);
        btGuardarMinimo.setEnabled(true);
                     
        
        Tipo = 3;
        
    }//GEN-LAST:event_btEditarActionPerformed
    
    public void CargaFechaLlegadaProveedor() {
        ExeSql sql = new ExeSql();
        ResultSet rs = null;
        String query = "select date_part('day', fecha_llegada)|| '/' || \n" +
                        "date_part('month', fecha_llegada)|| '/' ||\n" +
                        "date_part('year', fecha_llegada) as fecha from fechallegadaprv\n"+
                       "where sku = '"+txSku.getText()+"'\n"+
                       "order by fecha_llegada desc\n" +
                       "limit 1";
        try {
            rs = sql.Select(query);
            rs.next();
            if(rs.getRow()>0){
    //            jtFechaLlegada.setText(rs.getString("fecha"));
            }
            else {
    //            jtFechaLlegada.setText("Sin fecha");
            }
        } catch (SQLException ex) {
            Logger.getLogger(pfProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            sql.Close();
        }
        
    }
    
    public boolean CheckIfExist(String sku) {
        
        boolean existe = false;
        ExeSqlLuvaly sql = new ExeSqlLuvaly();
        ResultSet rs = null;
        String Query = "select p.sku from producto p\n" +
                       "where p.sku='"+sku+"' or p.sku in (select sku from codbar where codbar='"+sku+"')";
        try {
            rs = sql.Select(Query);
            rs.last();
            if(rs.getRow()>0){
                existe = true;
            }
            else {
                existe = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(pfProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return existe;
    } 
    
    public boolean CheckIDCH(String idch) {
        boolean existe = false;
        ExeSqlLuvaly sql = new ExeSqlLuvaly();
        ResultSet rs = null;
        
        try {
            rs = sql.Select("select sku from codchile where idch='" + idch +"'");
            if(rs.next()){
                existe = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(pfProductos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return existe;
    }
    public boolean CheckCOD(String cod) {
        boolean existe = false;
        ExeSqlLuvaly sql = new ExeSqlLuvaly();
        ResultSet rs = null;
        
        try {
            rs = sql.Select("select sku from codbar where codbar='" + cod +"'");
            if(rs.next()){
                existe = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(pfProductos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return existe;
    }
    private void txSkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txSkuKeyPressed
            if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                //btIr.doClick();     
                Runnable miRunnable = new Runnable() {
                    public void run() {
                        try{

                            lbcargando.setEnabled(true);
                            lbcargando.setVisible(true);
                            
                                carga_producto();
                            
                            lbcargando.setVisible(false);
                            lbcargando.setEnabled(false);
                            
                             carga_ubicainv(txSku.getText().trim());


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                Thread hilo = new Thread(miRunnable);
                hilo.start();


                lbcargando.setText("Cargando.....");
                URL urlInfo =  this.getClass().getResource("/Iconos16/wait.gif");
                ImageIcon IconoInfo =  new ImageIcon(urlInfo); 
                lbcargando.setIcon(IconoInfo);
                lbcargando.setForeground(Color.red);
            } 
             
             else if(Character.isLetter(evt.getKeyChar()) && !chkCodigo.isSelected()){
                    
                jdBuscarProductos BP = new jdBuscarProductos(null, true);
                BP.setLocationRelativeTo(null);
                BP.setTitle("Buscar Producto");
                BP.SetTexto(txSku.getText() + Character.toString(evt.getKeyChar()));
                //txSku.setText("");  
                BP.setVisible(true);
                BP.SetCotiza(false);
             
                if(!"".equals(BP.GetCodigo())){
                    Limpia();
                    CargaProducto(BP.GetCodigo());

                }
             
            }
    }//GEN-LAST:event_txSkuKeyPressed

    private void txSkuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txSkuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txSkuActionPerformed

    private void btNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoActionPerformed
        
        int NivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
        
        if (NivelUsuario < 100){
         
            fmMain.Mensaje("Usuario: " + fmMain.GetUsuario() + " no esta autorizado") ;
            return;
         
         }
        
        
        
        if(fmMain.GetEstado(fmMain.pnPestanas.getSelectedIndex())==0){
            SetTipo(1);
            
            txSku.setEnabled(true);
            txSku.setEditable(true);
            txSku.requestFocus();
            Tipo=1;
        }
       
       
    }//GEN-LAST:event_btNuevoActionPerformed
   
    private int AfectoIva(){
        if(chkIva.isSelected())
            return 1;
        else
            return 0;
    }

//-----------------------------------------------------------------------------
//  BOTON GUARDAR
//-----------------------------------------------------------------------------
    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed
        ExeSql Sql = new ExeSql();
        ExeSql Sql2 = new ExeSql();
        ExeSql Sql3 = new ExeSql();
        ExeSql Sql4 = new ExeSql();
        
         ResultSet Rs3, Rs4 = null;

        String Query, Query2, Query4;   
        String Sku = txSku.getText().trim();
    
    if(cbConvenio.getSelectedIndex()    ==-1    || 
       cbFamilia.getSelectedIndex()     ==-1    || 
       cbSubFamilia.getSelectedIndex()  ==-1    ||
       cbUnidad.getSelectedIndex()==-1){
            JOptionPane.showMessageDialog(null, "Faltan datos");
            return;
    }
        if(fmMain.OkCancel("¿Guardar Cambios?")== JOptionPane.OK_OPTION){
            boolean Publicado = false;
            boolean Web = false;
            boolean DesxPrecio = false;
            boolean NoTransado = false;
            
            boolean DesHabilitado = false;
            boolean Discontinuado = false;
            boolean SinStock = false;
            String PPublico = txPNeto.getText();
            String Peso     = txPeso.getText();
            String Display  = txDisplay.getText();
            String Familia;
            String SubFamilia;
            String Marca;
            String Convenio;
            String OtroImpuesto;
            fmMain.SetGuardar(Display);
            if(PPublico.isEmpty())  PPublico= "0";
            
            
            if (Peso.isEmpty()) {
                Peso = "0";
            }
            if (Display.isEmpty()) {
                Display = null;
            }
            try {
                Familia = cbFamiliaCod.getSelectedItem().toString().trim();
            } catch (Exception e) {
                Familia = "0";
            }

            try {
                SubFamilia = cbSubFamiliaCod.getSelectedItem().toString().trim();
            } catch (Exception e) {
                SubFamilia = "0";
            }

            try {
                Marca = "0";
            } catch (Exception e) {
                Marca = "1";
            }

            try {
                Convenio = cbConvenioCod.getSelectedItem().toString().trim();
            } catch (Exception e) {
                Convenio = "1";
            }

            
            
            if(txNombre.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Debe definir un nombre para el producto");
                return;
            }
            
            
            System.out.println("EL Tipo ES : "+Tipo);
           //Nuevo Producto
            if (Tipo == 1) {
                try {

                    Rs3 = Sql3.Select("SELECT sku FROM inventario WHERE sku='" + txSku.getText().trim() +"'");
                    
                    if (Sql3.GetRowCount() == 0){
                        
                        boolean esoferta = false;
                        double margen = 0;
                        
                      
                       
                    
                        Query2 = "INSERT INTO inventario (sku) VALUES ('" + txSku.getText().trim()+"')";
                        Sql2.ExeSql(Query2);
                        
                        
                        Query4 = "SELECT * FROM producto_valores WHERE sku ='" + txSku.getText().trim()+"'";
                        Rs4 = Sql4.Select(Query4);
                        
                        
//                         if (Sql4.GetRowCount() == 0){
//                        
//                            Query = "INSERT INTO producto_valores (sku,valultcompra,costofinal,margen,esoferta) VALUES ('" + txSku.getText().trim()+"',"+
//                                                                   txPUltCompra.getText().trim()+","+txPCosto.getText().trim()+","+margen +","+esoferta+")";
//                            Sql.ExeSql(Query);
//                            Sql.Commit();
//                        
//                         }
//                        
                        
                        Sql2.Commit();
//                    guardarImagen(); //Cuando este OK
                        JOptionPane.showMessageDialog(null, "Producto Guardado");

                        SetTipo(-1);
                        CargaProducto(Sku);
                    
                    }else if (Sql3.GetRowCount() > 0){
                        
                        
                        JOptionPane.showMessageDialog(null, "Producto ya Existe!!");
                        return;
                    
                    }
              
                    
                } catch (SQLException e) {
                    Sql.Rollback();
                    Sql2.Rollback();
//                    JOptionPane.showMessageDialog(null, "Error al guardar2 " + e.getMessage());
                    Logger.getLogger(pfProductos.class.getName()).log(Level.SEVERE, null, e);
                
                } finally {
                    Sql.Close();
                    Sql2.Close();

                }
            }
           //Update Producto
           else if (Tipo == 3) {
                 
                boolean esoferta = false;
                double margen = 0;
                               
               
              
                
               
                try {
                    Query = "UPDATE producto_valores SET \n"+
                            "pventa_ant=" + txPNeto.getText().trim().replaceAll("\\,", "") + ", \n"+
                            "margen =" + margen + ", \n"+
                            "esoferta =" + esoferta + " \n"+
                            "WHERE sku = '"+txSku.getText().trim()+"'";
                      
                    
                    Sql.ExeSql(Query);
                    Sql.Commit();
                    
//                    guardarImagen();  // comentar
                    JOptionPane.showMessageDialog(null, "Producto Modificado");
                    SetTipo(-1);
                    CargaProducto(Sku);
                    
                    
                   
                    txNuevoMinimo.setEnabled(false);
                    btGuardarMinimo.setEnabled(false);
        
                    
                } catch (Exception e) {
                    Sql.Rollback();
                    JOptionPane.showMessageDialog(null, "Error al guardar !!!:\n" + e.getMessage());
                    System.out.println(e);
                } finally {
                    Sql.Close();
                }
            }
       }
    }//GEN-LAST:event_btGuardarActionPerformed
    
    private void CargaCompras(String Codigo) {
        ExeSql Sql = new ExeSql();
        String Query = "";
        DefaultTableModel dftm = (DefaultTableModel) GrillaCompras.getModel();
        ResultSet Rs;
        
        // Limpia Grilla
//        while (dftm.getRowCount() > 0) {
//            dftm.removeRow(0);
//        }
        
        dftm.setRowCount(0);

        try {
            Query = "select cast(e.femision as date) as fecha, e.rut,trim(p.nombre) as nombre,e.nrodocto,d.cantidad,d.recibido, d.valorunitario, "
                    + "(select nuevo_valor from ctacteprvdet d \n" +
                        "inner join (select femision,tipdocto,nrodocto,nrodocorigen from ctacteprv\n" +
                        "where nrodocorigen  = e.nrodocto\n" +
                        "and tipdocorigen = 'OCP' ) o\n" +
                        "on  d.nrodocto = o.nrodocto\n" +
                        "and d.tipdocto = o.tipdocto\n" +
                        "and d.sku='" + Codigo + "' order by o.femision desc LIMIT 1),\n"
                    + "d.totallinea, \n"
                    + "(select objetivo from ctacteprvdet d \n" +
                        "inner join (select femision,tipdocto,nrodocto,nrodocorigen from ctacteprv\n" +
                        "where nrodocorigen  = e.nrodocto\n" +
                        "and tipdocorigen = 'OCP' ) o\n" +
                        "on  d.nrodocto = o.nrodocto\n" +
                        "and d.tipdocto = o.tipdocto\n" +
                        "and d.sku='" + Codigo + "' order by o.femision desc LIMIT 1)\n"
                    + "from ctacteprvdet d\n"
                    + "left join ctacteprv e on d.rut=e.rut and d.tipdocto=e.tipdocto and d.nrodocto=e.nrodocto\n"
                    + "left join proveedor p on p.rut=d.rut\n"
                    + "where d.tipdocto='OCP'\n"
                    + "and d.sku='" + Codigo + "'\n"
                    + "order by e.femision desc";
            Rs = Sql.Select(Query);
            while (Rs.next()) {
                dftm.addRow(new Object[]{
                    Rs.getDate("fecha"),
                    Rs.getString("rut"),
                    Rs.getString("nombre"),
                    Rs.getString("nrodocto"),
                    fmMain.FormatoNumero(Rs.getDouble("cantidad")),
                    fmMain.FormatoNumero(Rs.getDouble("recibido")),
                    fmMain.FormatoNumero(Rs.getDouble("valorunitario")),
                    fmMain.FormatoNumero(Rs.getDouble("nuevo_valor")),
                    fmMain.FormatoNumero(Rs.getDouble("totallinea")),
                    Rs.getInt("objetivo"),
                });
                
            GrillaCompras.setDefaultRenderer(Object.class,new Utilidades.TableColor_OCProvee());
            GrillaCompras.repaint();
            TableColumnModel columnModel = GrillaCompras.getColumnModel();
            TableColumn column = columnModel.getColumn(3); // Give column index here
            column.setCellRenderer(new Utilidades.TableColor_OCProvee());
            
            TableColumn column2 = columnModel.getColumn(4); // Give column index here
            column2.setCellRenderer(new Utilidades.TableColor_OCProvee());
            
            TableColumn column3 = columnModel.getColumn(5); // Give column index here
            column3.setCellRenderer(new Utilidades.TableColor_OCProvee());
            
            TableColumn column4 = columnModel.getColumn(6); // Give column index here
            column4.setCellRenderer(new Utilidades.TableColor_OCProvee());
            TableColumn column5 = columnModel.getColumn(7); // Give column index here
            column5.setCellRenderer(new Utilidades.TableColor_OCProvee());
            TableColumn column6 = columnModel.getColumn(8); // Give column index here
            column6.setCellRenderer(new Utilidades.TableColor_OCProvee());
            
            }
        } catch (Exception e) {
            fmMain.Mensaje("Error al cargar: "+e);
        } finally {
            Sql.Close();
        }
    }
    
    
    private void cbUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbUnidadActionPerformed
        if(Tipo==2 || Tipo==-1)
            cbUnidad.setSelectedIndex(PosUM);
        else if(Tipo!=-99)
        cbUnidadId.setSelectedIndex(cbUnidad.getSelectedIndex());
    }//GEN-LAST:event_cbUnidadActionPerformed

    private void cbSubFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSubFamiliaActionPerformed
        if(Tipo==2 || Tipo==-1 || Tipo==3)
            cbSubFamilia.setSelectedIndex(PosSubLinea);
        else if(cbSubFamilia.getSelectedIndex()>=0 && cbSubFamiliaCod.getItemCount()>0 ){
            cbSubFamiliaCod.setSelectedIndex(cbSubFamilia.getSelectedIndex());
            GeneraCodigo();
        }
            
    }//GEN-LAST:event_cbSubFamiliaActionPerformed

    private void cbConvenioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbConvenioActionPerformed
        if(Tipo==2 || Tipo==-1 || Tipo==3)
            cbConvenio.setSelectedIndex(PosConvenio);
        else if(Tipo==1){
            cbConvenioCod.setSelectedIndex(cbConvenio.getSelectedIndex());
            CargaLineas();
        }
        else if (Tipo != -99 ){
           cbConvenioCod.setSelectedIndex(cbConvenio.getSelectedIndex());
           GeneraCodigo();
        }

    }//GEN-LAST:event_cbConvenioActionPerformed

    private void btEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btEliminarActionPerformed

    private void txPesoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txPesoKeyTyped
       if(evt.getKeyChar()==',' || evt.getKeyChar()=='.' ){
            evt.consume();
            if(!txPeso.getText().contains(fmMain.GetDecimal()))
                txPeso.setText(txPeso.getText() + fmMain.GetDecimal() );
        }
    }//GEN-LAST:event_txPesoKeyTyped

    private void txNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txNombreActionPerformed

    private void txNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txNombreKeyTyped
       int asciivalor; 
//         if(evt.getKeyChar()==',' || evt.getKeyChar()=='.'    ){
//            evt.consume();
//        }
        
            char c=evt.getKeyChar(); 
            asciivalor = (int) c;
            
            
            switch (asciivalor){
            case 39:
            case 44:
            //case 46:
              getToolkit().beep(); 
              evt.consume(); 
              System.out.println("Ingresar sin apostrofes, comas ni puntos" + "  caracter---> " + evt.getKeyChar() + "   (-----)    codigo ascii " + asciivalor ); 
            }  
            
//             if(asciivalor == 39) { 
//              getToolkit().beep(); 
//              evt.consume(); 
//              System.out.println("Ingresar sin apostrofes  ." ); 
//         }  
        
        if (Character.isLowerCase(evt.getKeyChar()))
            evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));        // TODO add your handling code here:
    }//GEN-LAST:event_txNombreKeyTyped

    private void cbConvenioCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbConvenioCodActionPerformed
        if(Tipo!=-99)    
            cbConvenioSku.setSelectedIndex(cbConvenioCod.getSelectedIndex());
            
    }//GEN-LAST:event_cbConvenioCodActionPerformed

    private void cbFamiliaCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFamiliaCodActionPerformed
        if(Tipo!=-99)   
            cbFamiliaSku.setSelectedIndex(cbFamiliaCod.getSelectedIndex());
    }//GEN-LAST:event_cbFamiliaCodActionPerformed

    private void cbSubFamiliaCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSubFamiliaCodActionPerformed
        if(Tipo!=-99)   
            cbSubFamiliaSku.setSelectedIndex(cbSubFamiliaCod.getSelectedIndex());
    }//GEN-LAST:event_cbSubFamiliaCodActionPerformed

    private void cbOtroImpuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOtroImpuestoActionPerformed
        if(Tipo==2 || Tipo==-1)
            cbOtroImpuesto.setSelectedIndex(PosImpuesto);
        else if(Tipo!=-99){
            cbOtroImpuestoId.setSelectedIndex(cbOtroImpuesto.getSelectedIndex());
            cbOtroImpuestoTaza.setSelectedIndex(cbOtroImpuesto.getSelectedIndex());
            txTazaImpuesto.setText(cbOtroImpuestoTaza.getSelectedItem().toString().trim() + "%");
        }
            
    }//GEN-LAST:event_cbOtroImpuestoActionPerformed

    private void cbOtroImpuestoIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOtroImpuestoIdActionPerformed
        if(Tipo!=-99)    {
            cbOtroImpuesto.setSelectedIndex(cbOtroImpuestoId.getSelectedIndex());
            cbOtroImpuestoTaza.setSelectedIndex(cbOtroImpuestoId.getSelectedIndex());
            txTazaImpuesto.setText(cbOtroImpuestoTaza.getSelectedItem().toString().trim() + "%");
        }
            
            cbConvenioSku.setSelectedIndex(cbConvenioCod.getSelectedIndex());
    }//GEN-LAST:event_cbOtroImpuestoIdActionPerformed

    
    private void jtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtCantidadActionPerformed
        
        if (jtCantidad.isSelected()){
           
            txCantUnidad.setEnabled(true);
            txCantUnidad.setEditable(true);
            jtCantidad.setText("Actualizar");
                 
        }else{
            
            txCantUnidad.setEnabled(false);
            txCantUnidad.setEditable(false);
            jtCantidad.setText("Editar"); 
            
            
            if (txCantUnidad.getText().equals("0") || txCantUnidad.getText().equals("")){
            
                txCantUnidad.setText(ucant);
                               
                fmMain.Mensaje("La cantidad mínima debe ser 1");
                return;
            }
           
        }
    }//GEN-LAST:event_jtCantidadActionPerformed

    private void btUbicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUbicaActionPerformed
        
        jd_UbicacionesP Ubicacion = new jd_UbicacionesP(null, true);
        Ubicacion.CargaBultos(txSku.getText().trim(),txNombre.getText().trim());
        //Ubicacion.selecciona_reg();

        Ubicacion.setVisible(true);
       
    }//GEN-LAST:event_btUbicaActionPerformed

    private void btGuardarMinimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarMinimoActionPerformed
        String NuevoMinimo = txNuevoMinimo.getText();
        if(!txNuevoMinimo.getText().equals("")){
            if(fmMain.OkCancel("El mínimo actual de "+txNombre.getText()+" es "+txMinimo.getText()+", ¿Quiere que el nuevo mínimo sea "+txNuevoMinimo.getText()+"?")== JOptionPane.OK_OPTION){
                ActualizarMinimo();
               txNuevoMinimo.setEnabled(false);
               btGuardarMinimo.setEnabled(false);
                
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe ingresar un valor al campo Nuevo Mínimo");
        }
    }//GEN-LAST:event_btGuardarMinimoActionPerformed

    private void txNuevoMinimoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txNuevoMinimoKeyTyped
        
        char C= evt.getKeyChar();
        
//        if(Character.isLetter(C)){
//        
//            getToolkit().beep();
//            evt.consume();
//            JOptionPane.showMessageDialog(this, "Ingrese solo Números");
//            txNuevoMinimo.setCursor(null);
//        
//        }
        
       // else 
            if((int)evt.getKeyChar()>=32 && (int)evt.getKeyChar()<=47 || (int)evt.getKeyChar()>=58 && (int)evt.getKeyChar()<=64
            ||(int)evt.getKeyChar()>=91 && (int)evt.getKeyChar()<=96 || (int)evt.getKeyChar()>=123 && (int)evt.getKeyChar()<=255){
           
            getToolkit().beep();  // 
            evt.consume();
            JOptionPane.showMessageDialog(this, "Ingrese solo Números");
            txNuevoMinimo.setCursor(null);
        }
    }//GEN-LAST:event_txNuevoMinimoKeyTyped

    private void txNuevoMinimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txNuevoMinimoActionPerformed

    }//GEN-LAST:event_txNuevoMinimoActionPerformed

    private void txMinimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txMinimoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txMinimoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String sku = txSku.getText();
        jdImagenProducto BPC = new jdImagenProducto(null, true);
        BPC.setLocationRelativeTo(null);
        BPC.setTitle("Imagen Producto");
        BPC.setSku(sku);
        BPC.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void PestanasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PestanasMouseClicked
        
            
    }//GEN-LAST:event_PestanasMouseClicked

    private void PestanasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_PestanasStateChanged
        
    }//GEN-LAST:event_PestanasStateChanged

    private void PestanasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PestanasMousePressed
        
    }//GEN-LAST:event_PestanasMousePressed

    private void jPanel2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel2FocusGained
        
        
        
    }//GEN-LAST:event_jPanel2FocusGained

    private void GrillaComprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GrillaComprasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_GrillaComprasMouseClicked

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void GrillaFacturasCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GrillaFacturasCMouseClicked
        // TODO add your handling code here:
        //System.out.println( Grilla_Det.getValueAt(Grilla_Det.getSelectedRow(), 2).toString().trim());

        String Tipodoc = GrillaFacturasC.getValueAt(GrillaFacturasC.getSelectedRow(), 2).toString().trim();
        String nrodoc = GrillaFacturasC.getValueAt(GrillaFacturasC.getSelectedRow(), 1).toString().trim();
        String rut  = GrillaFacturasC.getValueAt(GrillaFacturasC.getSelectedRow(), 0).toString().trim();

        if (Tipodoc.equals("FAP")  )
        {
            pfFAProveedor Pro = new pfFAProveedor();
            Pro.setOpaque(false);
            pnPestanas.addTab("Factura Proveedor", Pro);
            PanelTab btc = new PanelTab(pnPestanas, 0);
            pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(Pro), btc);
            pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
            Pro.CargaFactura(rut,nrodoc);

        }
        //        else if (Tipodoc.equals("NCP")    )
        //        {
            //                pfNCPProveedor NCP = new pfNCPProveedor();
            //                NCP.setOpaque(false);
            //                pnPestanas.addTab("Nota Credito Proveedor", NCP);
            //                PanelTab btc = new PanelTab(pnPestanas, 0);
            //                pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(NCP), btc);
            //                pnPestanas.setSelectedIndex(pnPestanas.getTabCount() - 1);
            //                 NCP.AbrirNCP(rut, nrodoc);
            //        }
        //
    }//GEN-LAST:event_GrillaFacturasCMouseClicked

    private void btGuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuarActionPerformed
        
        if (!txStockInv.getText().isEmpty()){
        
            try {
                ExeSql Sql = new ExeSql();
                
                String Query = "";
                
                Query = "UPDATE inventario SET \n"+
                        "stock = " + txStockInv.getText().trim() + " \n"+
                        "WHERE sku = '"+txSku.getText().trim()+"'";
                
                Sql.ExeSql(Query);
                Sql.Commit();
                
                txStockInv.setEditable(false);
                btGuar.setEnabled(false);
                
            
            } catch (SQLException ex) {
                Logger.getLogger(pfProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
        
        
    }//GEN-LAST:event_btGuarActionPerformed

    private void btEdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEdActionPerformed
        
        
        if (!txSku.getText().isEmpty()){
        
            txStockInv.setEnabled(true);
            txStockInv.setEditable(true);
            txStockInv.requestFocus();
            btGuar.setEnabled(true);
        
        }
        
    }//GEN-LAST:event_btEdActionPerformed

    class Elrender extends DefaultTableCellRenderer {
         
        @Override
        public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean isSelected, boolean hasFocus, int fila, int columna) {
        super.getTableCellRendererComponent(tabla,valor,isSelected, hasFocus, fila, columna);
         
            if( tabla.getValueAt(fila,0).toString().equals(fmMain.BodegaNCP()) || tabla.getValueAt(fila,0).toString().equals(fmMain.BodegaNCC())){
                
                if(isSelected==true){
                    this.setForeground(Color.white);    
                    this.setBackground(Color.RED);
                     this.setFont(new Font("Tahoma", Font.BOLD, 11));
                }else{
                
                    this.setForeground(Color.RED);
                    this.setBackground(Color.white);
                    this.setFont(new Font("Tahoma", Font.BOLD, 11));
                
                }
            
            }else if( tabla.getValueAt(fila,1).toString().contains("SALA-%")){
                
                
                if(isSelected==true){
                
                    this.setForeground(Color.white);
                    this.setBackground(DARK_GREEN);    
                    this.setFont(new Font("Tahoma", Font.BOLD, 11));
                    
                    
                
                }else{
                
                    this.setForeground(DARK_GREEN);
                    this.setBackground(Color.white);    
                    this.setFont(new Font("Tahoma", Font.BOLD, 11));
                    
                }
                
            
            }else {
                
                if(isSelected==true){
                
                    this.setForeground(Color.white);  
                    this.setBackground(Color.BLUE);
                    
                
                }else{
                
                    this.setForeground(Color.black);  
                    this.setBackground(Color.white);
                }
            } 
            
            
           
            
            
            return this;
        }
    }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable GrillaCompras;
    private javax.swing.JTable GrillaFacturasC;
    private javax.swing.JTable GrillaMovimientos;
    private javax.swing.JTable Grilla_Inv;
    private javax.swing.JTabbedPane Pestanas;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btEd;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btEliminar;
    private javax.swing.ButtonGroup btGroupMovimientos;
    private javax.swing.ButtonGroup btGroupProveedor;
    private javax.swing.JButton btGuar;
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btGuardarMinimo;
    public javax.swing.JButton btIr;
    private javax.swing.JButton btNuevo;
    private javax.swing.JButton btUbica;
    private javax.swing.JComboBox cbConvenio;
    private javax.swing.JComboBox cbConvenioCod;
    private javax.swing.JComboBox cbConvenioSku;
    private javax.swing.JComboBox cbFamilia;
    private javax.swing.JComboBox cbFamiliaCod;
    private javax.swing.JComboBox cbFamiliaSku;
    private javax.swing.JComboBox cbOtroImpuesto;
    private javax.swing.JComboBox cbOtroImpuestoId;
    private javax.swing.JComboBox cbOtroImpuestoTaza;
    private javax.swing.JComboBox cbSubFamilia;
    private javax.swing.JComboBox cbSubFamiliaCod;
    private javax.swing.JComboBox cbSubFamiliaSku;
    private javax.swing.JComboBox cbUnidad;
    private javax.swing.JComboBox cbUnidadId;
    private javax.swing.JCheckBox chkCodigo;
    private javax.swing.JCheckBox chkIva;
    private javax.swing.JCheckBox chk_minimo;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JToggleButton jtCantidad;
    private javax.swing.JLabel lbNuevoMinimo;
    private javax.swing.JLabel lbcantidad;
    private javax.swing.JLabel lbcargando;
    private javax.swing.JList lsCodbar;
    private javax.swing.JPanel pnDetalle;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JLabel skulabel;
    private javax.swing.JTextField txCantUnidad;
    private javax.swing.JTextField txDisplay;
    private javax.swing.JTextField txMinimo;
    private javax.swing.JTextField txNombre;
    private javax.swing.JTextField txNuevoMinimo;
    private javax.swing.JTextField txPNeto;
    private javax.swing.JTextField txPeso;
    private javax.swing.JTextField txSeparado;
    public javax.swing.JTextField txSku;
    private javax.swing.JTextField txStockInv;
    private javax.swing.JTextField txStockOCC;
    private javax.swing.JTextField txStockOCP;
    private javax.swing.JTextField txStockTotal;
    private javax.swing.JTextField txTazaImpuesto;
    private javax.swing.JTextField txTransito;
    private javax.swing.JTextField txUbicados;
    // End of variables declaration//GEN-END:variables
}
