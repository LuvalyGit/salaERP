package PanelForm;

import Conexion.ExeSql;
import Conexion.ExeSqlLuvaly;
import Dialogos.*;
import Formularios.fmMain;
import static Formularios.fmMain.pnPestanas;
import static PanelForm.pfBuscaDoc.BuscaArchivos;
import static Utilidades.DocElect.carpeta;  
import Utilidades.Ftp; 
import Utilidades.GeneraDTE;
import Utilidades.GeneraDTE_FTP;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Utilidades.Ftp;
import static Utilidades.Ftp.busca;
import Utilidades.LogError;
import Utilidades.PanelTab;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javamail.Window;
import java.util.concurrent.TimeUnit;
import static javamail.Window.PassWord;
import static javamail.Window.Username;
import javax.mail.Session;
import javax.*;
import javax.activation.*;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class pfFACCliente extends javax.swing.JPanel {
    String RutMaster;
    int Tipo; // 0::Nuevo    1:Abrir
    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();

    
    public void BajaArchivo() {
        String Archivo =Ftp.busca_SinAbrir("33", lbFolio.getText().trim(),"facturas","dte_dis");
    }

    private void MailRápido() {
        JOptionPane.showMessageDialog(null, "Se enviará factura, un momento.");
        String Archivo =Ftp.busca_SinAbrir("33", lbFolio.getText().trim(),"facturas","dte_dis");    
        String NumFactura = lbFolio.getText().trim();
        String Rut = txRut.getText().trim();
        String Orden = cbNroOrden.getSelectedItem().toString().trim();
        String CodigoOC=   cbCodigoOc.getSelectedItem().toString().trim();

        ExeSql sql = new ExeSql();
        ResultSet Rs = null;

        String usuario = "despachos@luvaly.cl";
        String pass = "3Logica.";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
        String recipient = "rortiz@luvaly.cl,pagos@luvaly.cl"; 
        
        //Contacto Orden
        String Query = "select nombre,fono,celular,email\n" +
                    " from clicontactopersonas\n" +
                    " where id IN(select contacto \n" +
                    " from occh\n" +
                    " where rut =" +  Rut + " and  codigo_oc =" + CodigoOC +
                    " and orden = '" + Orden + "')  and email <> ''";
        try {
            Rs = sql.Select(Query);
            for(int i = 0; Rs.next(); i++){
                recipient = recipient + ","+Rs.getString("email").trim();
            }
        } catch (SQLException ex) {
            Logger.getLogger(pfFACCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Contacto Factura
        String Query2 = "select nombre,fono,celular,email\n" +
                    " from clicontactopersonas\n" +
                    " where id_cargo IN (8) and rut =" +  Rut + " and  codigo_oc =" + CodigoOC+ " and email <> '' "; 
        try {
            Rs = sql.Select(Query2);
            for(int i = 0; Rs.next(); i++){
                recipient = recipient + ","+Rs.getString("email").trim();
            }
        } catch (SQLException ex) {
            Logger.getLogger(pfFACCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Contacto Guía
        String Query3 = "select nombre,fono,celular,email\n" +
                " from clicontactopersonas\n" +
                " where id IN(select  contacto from ctactecli\n" +
                " where rut =" + Rut + " and  codigo_oc = " + CodigoOC + " and nrodocrel = " + NumFactura + "  and tipdocrel= 'FAC')  and email <> ''";
        try {
            Rs = sql.Select(Query3);
            for(int i = 0; Rs.next(); i++){
                recipient = recipient + ","+Rs.getString("email").trim();
            }
        } catch (SQLException ex) {
            Logger.getLogger(pfFACCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[][] productos = null;
        String detalle = "select p.nombre, det.sku, det.cantidad\n"
                + "from ctacteclidet det \n"
                + "left join producto p on p.sku = det.sku\n"
                + "where nrodocto = "+NumFactura+" and tipdocto = 'FAC' and rut = "+Rut+"";
        try {
            Rs = sql.Select(detalle);
            Rs.last();
            productos = new String[Rs.getRow()][3];
            Rs.beforeFirst();
            for(int i = 0; Rs.next(); i++){
                productos[i][0]=Rs.getString("sku").trim();
                productos[i][1]=Rs.getString("nombre").trim();
                productos[i][2]=Rs.getString("cantidad").trim();
            }
        } catch (SQLException ex) {
            Logger.getLogger(pfFACCliente.class.getName()).log(Level.SEVERE, null, ex);
        } 
        String[] recipients = recipient.split(",");
        String sender = "despachos@luvaly.cl"; 
        String host = "127.0.0.1"; 

        // creating session object to get properties 
        Session session = Session.getDefaultInstance(props); 

        try { 
            // MimeMessage object. 
            MimeMessage message = new MimeMessage(session); 

            // Set From Field: adding senders email to from field. 
            message.setFrom(new InternetAddress(sender)); 

            // Set To Field: adding recipient's email to from field.
            for(int i = 0; i < recipients.length; i++){
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipients[i]));
            }

            // Set Subject: subject of the email 
            message.setSubject("FACTURA N°: "+NumFactura+" - DISOSUR"); 

            // set body of the email. 
            String body =   "<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "    <head>\n" +
                            "        <style>\n" +
                            "            body {\n" +
                            "                background-color: white;\n" +
                            "                text-decoration: none;\n" +
                            "                font-family:verdana;\n" +
                            "            }\n" +
                            "            #head {\n" +
                            "                letter-spacing: 2px;\n" +
                            "                text-transform: uppercase;\n" +
                            "                background-color: #b2ecff;\n" +
                            "                margin: auto;\n" +
                            "                width: 80%;\n" +
                            "                padding: 10px;\n" +
                            "                text-align: left;\n" +
                            "                color: #4CAF50;              \n" +
                            "            }\n" +
                            "            #factura {\n" +
                            "                color: red;\n" +
                            "            }\n" +
                            "            #body {\n" +
                            "                margin: auto;\n" +
                            "                width: 80%;\n" +
                            "                border: 1px solid #b2ecff;\n" +
                            "                padding: 10px;\n" +
                            "                text-align: left;\n" +
                            "                font-size: 15px;\n" +
                            "            }\n" +
                            "            #grande {\n" +
                            "                margin-left: 10px;\n" +
                            "                color: black;\n" +
                            "            }\n" +
                            "            #footer {\n" +
                            "                margin: auto;\n" +
                            "                width: 80%;\n" +
                            "                border: 1px solid #b2ecff;\n" +
                            "                padding: 10px;\n" +
                            "                text-align: left;\n" +
                            "                font-size: 10px;\n" +
                            "                letter-spacing: 0px;\n" +
                            "            }\n" +
                            "            #detalle {\n" +
                            "                margin: auto;\n" +
                            "                width: 80%;\n" +
                            "                border: 1px solid #b2ecff;\n" +
                            "                padding: 10px;\n" +
                            "                text-align: left;\n" +
                            "                font-size: 10px;\n" +
                            "                letter-spacing: 0px;\n" +
                            "            }\n" +
                            "            table {\n" +
                            "                width:100%;\n" +
                            "            }\n" +
                            "            table, th, td {\n" +
                            "                border: 1px solid b2ecff;\n" +
                            "                border-collapse: collapse;\n" +
                            "            }\n" +
                            "            th, td {\n" +
                            "                padding: 15px;\n" +
                            "                text-align: left;\n" +
                            "            }\n" +
                            "            #t01 tr:nth-child(even) {\n" +
                            "                background-color: #eee;\n" +
                            "            }\n" +
                            "                #t01 tr:nth-child(odd) {\n" +
                            "                background-color: #fff;\n" +
                            "            }\n" +
                            "            #t01 th {\n" +
                            "                background-color: #b2ecff;\n" +
                            "                color: black;\n" +
                            "            }\n" +
                            "        </style>\n" +
                            "    </head>\n" +
                            "    <body>\n" +
                            "        <div id=\"head\">\n" +
                            "            <span id=\"grande\"><b>FACTURA N° : </b></spanp><span id=\"factura\"><b>"+NumFactura+"</b></span><br>\n" +
                            "        </div>\n" +
                            "        <div id=\"body\">        \n" +
                            "            <span>Junto con saludar,\n" +
                            "            se envia factura número: "+NumFactura+" ,<br>\n" +
                            "            asociada a la Orden de Compra: "+CodigoOC+"-"+Orden+"<br>\n" +
                            "            <br>\n" +
                            "            \n" +
                            "        </div>\n" +
                            "        <br>\n" +
                            "        <div id=\"detalle\">\n" +
                            "            <span></span>\n" +
                            "            <table id=\"t01\">\n" +
                            "                <thead>\n" +
                            "                    <tr>\n" +
                            "                        <th>SKU</th>\n" +
                            "                        <th>Producto</th>\n" +
                            "                        <th>Cantidad</th>\n" +
                            "                    </tr>    \n" +
                            "                </thead>\n" +
                            "                <tbody>\n";
                            for(int o = 0; o < productos.length; o++){
                                body = body + "<tr>\n"
                                            + "<td>"+productos[o][0]+"</td>" 
                                            + "<td>"+productos[o][1]+"</td>"
                                            + "<td>"+productos[o][2]+"</td>"
                                            + "</tr>\n";
                            }
                            body = body + "  </tbody>\n" +
                            "            </table>\n" +
                            "        </div>\n" +
                            "        <br>\n" +
                            "        <br>\n" +
                            "        <br>\n" +
                            "        <br>\n" +
                            "        <br>\n" +
                            "        <br>\n" +
                            "        <div id=\"footer\">\n" +
                            "            <span>DISOSUR<br>\n" +
                            "                Huerfanos 01871, Modulo 3, Temuco, Araucanía</span>\n" +
                            "        </div>\n" +
                            "    </body>\n" +
                            "</html>";

            String filename = Archivo;
            String[] file = Archivo.split("/");
            String def_file = file[file.length-1];
            DataSource source = new FileDataSource(filename); 
           // Send email. 
            BodyPart messageBodyPart1 = new MimeBodyPart(); 
            messageBodyPart1.setContent(body, "text/html;  charset=ISO-8859-1");
            BodyPart messageBodyPart2 = new MimeBodyPart();  
            messageBodyPart2.setDataHandler(new DataHandler(source));   
            messageBodyPart2.setFileName(def_file);
            
            Multipart multipartObject = new MimeMultipart();   
            multipartObject.addBodyPart(messageBodyPart1);   
            multipartObject.addBodyPart(messageBodyPart2);    
            System.out.println(recipient);
            message.setSentDate(new Date());
            message.setContent(multipartObject,  "text/html; charset=utf-8"); 
            
            Transport t = session.getTransport("smtp");
            t.connect(usuario, pass);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            System.out.println("Mail successfully sent"); 
            JOptionPane.showMessageDialog(null, "Facturación se envió correctamente");
        } 
        catch (MessagingException mex)  
        { 
           mex.printStackTrace(); 
        } 
    }
    
    private enum Columnas{Sku,Nombre,UM,Cantidad,Unitario,Total,UniReal};
    int PesoCorreccion=0;
    

    public pfFACCliente() {
        initComponents();
        SetTipo(-1);
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        Grilla.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        Grilla.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        Grilla.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        btAgregar.setVisible(false);
        btEliminar.setVisible(false);
        cbReferencia.setVisible(false);
        txReferencia.setVisible(false);
        btPDF.setVisible(false);
        btMail.setVisible(false);
        btEmiteMan.setVisible(false);
        txRut.setEnabled(false);
        txRut.setEditable(false);
        btIr.setEnabled(false);
        
         //Despliega boton para emitir una Guian Manualmente
        if (fmMain.GetUsuarioAdministrador()){
            btEmiteMan.setVisible(true);
        }
        
//        cbCodigoOc.setSelectedIndex(-1);
//        cbNroOrden.setSelectedIndex(-1);
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
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        btCorrigePeso = new javax.swing.JButton();
        chk_esRefacturacion = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lbFolio = new javax.swing.JLabel();
        txEstado = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dtEmision = new org.jdesktop.swingx.JXDatePicker();
        btPDF = new javax.swing.JButton();
        btMail = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Grilla = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txRut = new javax.swing.JTextField();
        txDv = new javax.swing.JTextField();
        btIr = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txNombre = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txDireccion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txComuna = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txCiudad = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cbTipoDespacho = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        cbCodigoOc = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbNroOrden = new javax.swing.JComboBox();
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
        jLabel14 = new javax.swing.JLabel();
        txImpEspecifico = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstGuias = new javax.swing.JList();
        btGuias = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        rbReferencia = new javax.swing.JCheckBox();
        cbReferencia = new javax.swing.JComboBox<>();
        txReferencia = new javax.swing.JTextField();
        btEmiteMan = new javax.swing.JButton();
        btEmitir = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        pnMenu.setBackground(new java.awt.Color(220, 215, 226));

        btNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/add16.png"))); // NOI18N
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

        btAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/open16.png"))); // NOI18N
        btAbrir.setText("Abrir");
        btAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAbrirActionPerformed(evt);
            }
        });

        jXLabel1.setForeground(new java.awt.Color(0, 51, 0));
        jXLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/OCCliente24.png"))); // NOI18N
        jXLabel1.setText("FACTURA CLIENTE");
        jXLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        btCorrigePeso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/Peso16.png"))); // NOI18N
        btCorrigePeso.setText("Corrección");
        btCorrigePeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCorrigePesoActionPerformed(evt);
            }
        });

        chk_esRefacturacion.setText("Es Refacturacion");

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(169, 169, 169)
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
                .addComponent(btCorrigePeso)
                .addGap(129, 129, 129)
                .addComponent(chk_esRefacturacion)
                .addContainerGap(638, Short.MAX_VALUE))
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMenuLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jXLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btAbrir)
                        .addComponent(btCorrigePeso)
                        .addComponent(chk_esRefacturacion))
                    .addComponent(btGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7))
        );

        add(pnMenu);

        jPanel1.setBackground(new java.awt.Color(220, 215, 226));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("FACTURA");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 14, 56, -1));

        lbFolio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbFolio.setForeground(new java.awt.Color(255, 51, 51));
        jPanel4.add(lbFolio, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 14, -1, -1));

        txEstado.setEditable(false);
        jPanel4.add(txEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 118, -1));

        jLabel5.setText("F. Emisión");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jLabel6.setText("Estado");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        dtEmision.setBackground(new java.awt.Color(255, 255, 255));
        dtEmision.setEnabled(false);
        jPanel4.add(dtEmision, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 145, -1));

        btPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/pdf.png"))); // NOI18N
        btPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPDFActionPerformed(evt);
            }
        });
        jPanel4.add(btPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 21, 22));

        btMail.setText("Mail");
        btMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMailActionPerformed(evt);
            }
        });
        jPanel4.add(btMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 60, -1));

        Grilla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "UM", "Cantidad", "V. Unitario", "Total Linea", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        }

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel3.setPreferredSize(new java.awt.Dimension(600, 170));

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

        jLabel2.setText("Nombre");

        txNombre.setEnabled(false);

        jLabel9.setText("Dirección");

        txDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txDireccionActionPerformed(evt);
            }
        });

        jLabel7.setText("Comuna");

        jLabel8.setText("Ciudad");

        jLabel16.setText("Despacho");

        cbTipoDespacho.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Directo" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txDireccion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(txComuna, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txNombre))
                        .addContainerGap(73, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txRut, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txDv, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btIr, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTipoDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))))
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
                        .addComponent(txDv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(cbTipoDespacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txComuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel6.setPreferredSize(new java.awt.Dimension(239, 70));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbCodigoOc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCodigoOc.setSelectedIndex(-1);
        cbCodigoOc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCodigoOcActionPerformed(evt);
            }
        });
        jPanel6.add(cbCodigoOc, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 14, 145, -1));

        jLabel3.setText("Código OC");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 19, -1, -1));

        jLabel4.setText("Nro Orden");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 53, -1, -1));

        cbNroOrden.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbNroOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNroOrdenActionPerformed(evt);
            }
        });
        jPanel6.add(cbNroOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 48, 145, -1));

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

        txExento.setEditable(false);
        txExento.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txExento.setText("0");

        txNeto.setEditable(false);
        txNeto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txNeto.setText("0");

        jLabel10.setText("Neto");

        txIva.setEditable(false);
        txIva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txIva.setText("0");

        jLabel12.setText("I.V.A.");

        txTotal.setEditable(false);
        txTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txTotal.setText("0");

        jLabel13.setText("TOTAL");

        jLabel14.setText("Imp. específico");

        txImpEspecifico.setEditable(false);
        txImpEspecifico.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txImpEspecifico.setText("0");

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
                .addContainerGap()
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

        jScrollPane2.setViewportView(lstGuias);

        btGuias.setText("Editar");
        btGuias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuiasActionPerformed(evt);
            }
        });

        jLabel17.setText("Guias de Despacho");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addGap(32, 32, 32))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btGuias)
                .addGap(47, 47, 47))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addGap(9, 9, 9)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btGuias)
                .addGap(6, 6, 6))
        );

        rbReferencia.setText("Referencia Externa");
        rbReferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbReferenciaActionPerformed(evt);
            }
        });

        cbReferencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Orden de Compra", "Nota de Pedido", "Hoja Entrada Servicio", "Unidad de Pago (MOP)" }));
        cbReferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbReferenciaActionPerformed(evt);
            }
        });

        btEmiteMan.setText("Emite Manual");
        btEmiteMan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEmiteManActionPerformed(evt);
            }
        });

        btEmitir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/derecha16.png"))); // NOI18N
        btEmitir.setText("Emitir");
        btEmitir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEmitirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btAgregar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btEliminar))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(rbReferencia)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cbReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1048, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(183, 183, 183)
                                .addComponent(btEmiteMan))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btEmitir)))))
                .addContainerGap(298, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btEmitir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btEmiteMan))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btEliminar)
                            .addComponent(btAgregar))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(rbReferencia)
                            .addComponent(cbReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents
static private int GetCol(String Col){
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
          btNuevo.setEnabled(true);
          btEditar.setEnabled(false);
          btEmitir.setEnabled(false);
          btEmitir.setVisible(false);
          dtEmision.setEnabled(true);
          Tipo=-1;
          
        }
        // NUEVA ORDEN
        else if(ElTipo==1){
          fmMain.SetEstado(fmMain.pnPestanas.getSelectedIndex(),1);
          btGuardar.setEnabled(true);
          btCancelar.setEnabled(true);
          btEditar.setEnabled(false);
          btNuevo.setEnabled(false);
          btEmitir.setEnabled(false);
          btEmitir.setVisible(false);

          Habilita(true);
          Edicion(true);
          Limpia();
          txRut.requestFocus();
          Tipo=1;
        }
        else if(ElTipo==2){
          fmMain.SetEstado(fmMain.pnPestanas.getSelectedIndex(),0);
          btEditar.setEnabled(true);
          btNuevo.setEnabled(true);
          btGuardar.setEnabled(false);
          btCancelar.setEnabled(false);
          btEmitir.setEnabled(true);
          btEmitir.setVisible(false);
          Habilita(true);
          Edicion(false);
          txRut.setEditable(true);
          Tipo=2;
        }
        else if(ElTipo==3){
            btEmitir.setEnabled(false);
            btEmitir.setVisible(false);
            btGuardar.setEnabled(true);
            btCancelar.setEnabled(true);
            btEditar.setEnabled(false);
            btNuevo.setEnabled(false);
            Habilita(true);
            Edicion(true);
        }
    }
//--------------------------------------------------------------------------------
// EDICION
//--------------------------------------------------------------------------------
private void Edicion(boolean Estado){
    
//    txNroOc.setEditable(Estado);
    txDireccion.setEditable(Estado);
    txCiudad.setEditable(Estado);
    txComuna.setEditable(Estado);

    txRut.setEditable(Estado);
    txNombre.setEditable(Estado);
    txDv.setEditable(Estado);
  
    txNeto.setEditable(Estado);
    txExento.setEditable(Estado);
    txIva.setEditable(Estado);
    txTotal.setEditable(Estado);
    btAgregar.setEnabled(Estado);
    btEliminar.setEnabled(Estado);
    
//    dtEmision.setEditable(Estado);

}
//--------------------------------------------------------------------------------
// LIMPIA
//--------------------------------------------------------------------------------
private void Limpia(){
    DefaultComboBoxModel dfCm = new DefaultComboBoxModel();
    DefaultTableModel   dfTm = (DefaultTableModel) Grilla.getModel();
   
    DefaultListModel dfLista = new DefaultListModel();
    txRut.setText("");
    txNombre.setText("");
    txDv.setText("");
    txNeto.setText("");
    txExento.setText("");
    txIva.setText("");
    txTotal.setText("");
    txDireccion.setText("");
    txComuna.setText("");
    txCiudad.setText("");
           

    cbCodigoOc.setModel(dfCm);
//    cbContacto.setModel(dfCm);
//    lstGuias.removeAll();
    cbTipoDespacho.setSelectedIndex(-1);
    lbFolio.setText("");
    dtEmision.setDate(null);
    txEstado.setText("");
    cbNroOrden.setSelectedIndex(-1);
    while(dfTm.getRowCount()>0)
    dfTm.removeRow(0);

    lstGuias.setModel(dfLista);
    
}    
//--------------------------------------------------------------------------------
// HABILITA
//--------------------------------------------------------------------------------
private void Habilita(boolean Estado){
    
    cbCodigoOc.setEnabled(Estado);
    cbNroOrden.setEnabled(Estado);
//    cbContacto.setEnabled(Estado);
//    btDetalleCon.setEnabled(Estado);
//    txNroOc.setEnabled(Estado);
    txDireccion.setEnabled(Estado);
    txCiudad.setEnabled(Estado);
    txComuna.setEnabled(Estado);
    btAgregar.setEnabled(Estado);
    btEliminar.setEnabled(Estado);
   
    txRut.setEnabled(Estado);
    txNombre.setEnabled(Estado);
    txDv.setEnabled(Estado);
  
    //dtEmision.setEnabled(Estado);
    txNeto.setEnabled(Estado);
    txExento.setEnabled(Estado);
    txIva.setEnabled(Estado);
    txTotal.setEnabled(Estado);
    btIr.setEnabled(Estado);
    txEstado.setEnabled(Estado);
    cbTipoDespacho.setEnabled(Estado);
    //dtEmision.setEnabled(Estado);
    
}
    private void CargaProductosDesdeGuias(){
        DefaultTableModel   dfTm = (DefaultTableModel) Grilla.getModel();
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        ExeSqlLuvaly luv = new ExeSqlLuvaly();
        ResultSet producto = null;
        int j;
        boolean Existe;
        //Limpia
        while(dfTm.getRowCount()>0)
            dfTm.removeRow(0); 
        try { 
            for (int i = 0; i < lstGuias.getModel().getSize(); i++) {
           
                Existe = false;
                Rs = Sql.Select("select d.sku,p.nombre,u.um,d.cantidad,d.valorunitario,d.totallinea\n"
                        + "from ctacteclidet d \n"
                        + "left join producto p on d.sku=p.sku\n"
                        + "left join par_unidad u on p.unidad=u.codigo\n"
                        + "where d.rut=" + txRut.getText() + "\n"
                        + "and d.tipdocto='GDC'\n"
                        + "and d.nrodocto=" + lstGuias.getModel().getElementAt(i).toString());
                while (Rs.next()) {
                    producto = luv.Select("select p.nombre, u.um from producto p \n"
                        + "LEFT JOIN par_unidad u ON u.codigo=p.unidad\n "
                        + "where p.sku = '"+Rs.getString("sku").trim()+"'");
                    producto.next();
                    //Busca si el codigo ya fue cargado
                    for (j = 0; j < Grilla.getRowCount(); j++) {
                        if (Grilla.getValueAt(j, GetCol("Sku")).toString().equals(Rs.getString("sku"))) {
                            Existe = true;
                            break;
                        }
                    }
                    //Si existe suma las cantidades
                    if (Existe) {
                        double Antes = Double.valueOf(Grilla.getValueAt(j, GetCol("Cantidad")).toString().replace(",", ""));
                        System.out.println(Antes);
                        Grilla.setValueAt(fmMain.FormatoNumero(Antes + Rs.getDouble("cantidad")), j, GetCol("Cantidad"));
                        Antes = (Antes + Rs.getDouble("cantidad")) * Rs.getDouble("valorunitario");
                        Grilla.setValueAt(fmMain.FormatoNumero(Antes), j, GetCol("Total"));
                        Existe = false;
                    } //Si no existe, AGREGA EL PRODUCTO
                    else {
                        dfTm.addRow(new Object[]{
                            Rs.getString("sku"),
                            producto.getString("nombre"),
                            producto.getString("um"),
                            fmMain.FormatoNumero(Rs.getDouble("cantidad")),
                            fmMain.FormatoNumero(Rs.getDouble("valorunitario")),
                            fmMain.FormatoNumero(Rs.getDouble("totallinea")),
                            Rs.getDouble("valorunitario")});
                    }
                }
            
                Sumador();
                }
            } catch (Exception e) {
                System.out.println("2" + e);
            }
            finally{
                Sql.Close();
            }

        
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
        }
        
        else if(fmMain.OkCancel("¿Seguro de cancelar?")==JOptionPane.OK_OPTION){
            Limpia();
            Habilita(false);
            btGuardar.setEnabled(false);
            btCancelar.setEnabled(false);
            btNuevo.setEnabled(true);
            btEditar.setEnabled(false);
            btAbrir.setEnabled(true);
            
        }
    }//GEN-LAST:event_btCancelarActionPerformed

    
    public void boton_nuevo(){
           Tipo=1;
        Limpia();
        Habilita(false);
        Edicion(false);
        txRut.setEnabled(true);
        txRut.setEditable(true);
        txEstado.setText("Sin Emitir");
        btIr.setEnabled(true);
        btAbrir.setEnabled(false);
        btGuardar.setEnabled(true);
        btCancelar.setEnabled(true);
        txRut.requestFocus();
        cbTipoDespacho.setSelectedIndex(0);
    }
    public void setear_datos(String  rut, String codigo_oc, String orden){
        // Metodo que rescata el rut, codigo_oc y orden para abrir la factura con su guia
        // Seteo Rut
        txRut.setText(rut);
        btIr.doClick();
        cbCodigoOc.setSelectedItem(codigo_oc);
        cbNroOrden.setSelectedItem(orden);
        //btGuias.doClick();
    }
            
    
    
    
    
    
    private void btNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNuevoActionPerformed
        Tipo=1;
        Limpia();
        Habilita(false);
        Edicion(false);
        txRut.setEnabled(true);
        txRut.setEditable(true);
        txEstado.setText("Sin Emitir");
        btIr.setEnabled(true);
        btAbrir.setEnabled(false);
        btGuardar.setEnabled(true);
        btCancelar.setEnabled(true);
        txRut.requestFocus();
        cbTipoDespacho.setSelectedIndex(0);
    }//GEN-LAST:event_btNuevoActionPerformed

    private void txRutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txRutKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            btIr.doClick();
        }
    }//GEN-LAST:event_txRutKeyPressed
//------------------------------------------------------------------------------
// Carga Cliente
//------------------------------------------------------------------------------
    private boolean CargaCliente(String Rut){
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
            System.out.println(e);
            return false;
        } finally {
            Sql.Close();
        }
    }
//------------------------------------------------------------------------------
// Carga Codigos OC
//------------------------------------------------------------------------------
    private void CargaCodOc(String Rut) {
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        DefaultComboBoxModel cbMd = new DefaultComboBoxModel();
        cbCodigoOc.setModel(cbMd);

        try {
            Rs = Sql.Select("select distinct(codigo_oc) as codigo_oc from clicontacto where rut = " + Rut);
            while (Rs.next()) {
                cbMd.addElement(Rs.getString("codigo_oc"));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Sql.Close();
        }

    }
    private void txDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txDireccionActionPerformed

    private void btEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEliminarActionPerformed
        if(!Grilla.getValueAt(Grilla.getSelectedRow(), 0).toString().isEmpty())
        if(JOptionPane.showConfirmDialog(null, "¿Desea Eliminar el registro?") == JOptionPane.YES_OPTION){
            DefaultTableModel tbMd = (DefaultTableModel) Grilla.getModel();
            tbMd.removeRow(Grilla.getSelectedRow());
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
//        Sumador();
        }
        else {
            JOptionPane.showMessageDialog(null, "No se puede agregar más de 50 productos");
        }

    }//GEN-LAST:event_btAgregarActionPerformed

    private void btAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAbrirActionPerformed
        Habilita(false);
        Edicion(false);
        Limpia();
        txRut.setEnabled(true);
        txRut.setEditable(true);
        btIr .setEnabled(true);
        btCancelar.setEnabled(true);
        txRut.requestFocus();
        Tipo=2;
    }//GEN-LAST:event_btAbrirActionPerformed
    public void AbrirFactura(String Numero){
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        DefaultListModel dfLista = new DefaultListModel();;
        
        try {
            Rs = Sql.Select("select c.femision,c.codigo_oc,c.occh,cc.direccion,cc.comuna,cc.ciudad,c.estado as EstadoInt, case c.estado when 0 then 'Sin Emitir' when 1 then 'Emitida' else 'Anulada' end as Estado \n" +
                            " ,tipodespacho " +   
                            "from ctactecli c\n" +
                            "left join clicontacto cc on c.rut=cc.rut and c.codigo_oc=cc.codigo_oc \n" +
                            "where c.tipdocto='FAC'\n" +
                            "and c.nrodocto=" + Numero);
            
            Rs.next();
            lbFolio.setText(Numero);
            txDireccion.setText(Rs.getString("direccion").trim());
            txComuna.setText(Rs.getString("comuna").trim());
            txCiudad.setText(Rs.getString("ciudad").trim());
            txEstado.setText(Rs.getString("Estado"));
            
            if (Rs.getString("Estado").equals("Emitida")){
                btPDF.setVisible(true);
                btMail.setVisible(true);
            }
            else{
                btPDF.setVisible(false);
                btMail.setVisible(false);
            }
            dtEmision.setDate(Rs.getDate("femision"));
              cbTipoDespacho.setSelectedIndex(Rs.getInt("tipodespacho"));
            // Revisar 12/06/2018
            //if(Rs.getInt("estadoint")==0){ 
                btEmitir.setEnabled(true);
                btEmitir.setVisible(false);
                //btPDF.setVisible(false);
//            }
//            else{
//                //btEditar.setEnabled(false);
//                btPDF.setVisible(true);
//            }
            
            DefaultComboBoxModel cbMd = new DefaultComboBoxModel();
            cbMd.addElement(Rs.getString("codigo_oc")); 
            cbCodigoOc.setModel(cbMd);

            DefaultComboBoxModel cbMd2 = new DefaultComboBoxModel();
            cbMd2.addElement(Rs.getString("occh")); 
            cbNroOrden.setModel(cbMd2);
            
            
            Rs = Sql.Select("select nrodocto \n" +
                            "from ctactecli\n" +
                            "where tipdocto='GDC'\n" +
                            "and tipdocrel='FAC'\n" +
                            "and nrodocrel=" + Numero);
            
            while(Rs.next()){
                dfLista.addElement(Rs.getString("nrodocto"));
            }
            lstGuias.setModel(dfLista);
            CargaProductosDesdeGuias();
            CargaReferenciaExterna(Numero);
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally{
            Sql.Close();
        }
    }
    private void CargaReferenciaExterna(String NroDocto){
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        
        try {
            Rs = Sql.Select("select cod_ref,doc_ref,fecha\n" +
                            "from fac_referencias\n" +
                            "where tipdocto='FAC'\n" +
                            "and nrodocto=" + NroDocto);
            if(Sql.GetRowCount()>0){
                Rs.next();
                rbReferencia.setSelected(true);
                cbReferencia.setSelectedIndex(Rs.getInt("cod_ref")-801);
                txReferencia.setText(Rs.getString("doc_ref").trim());
                cbReferencia.setVisible(true);
                txReferencia.setVisible(true);
            }
            else{
                cbReferencia.setVisible(false);
                txReferencia.setVisible(false);
                rbReferencia.setSelected(false);
            }
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
        } finally{
            Sql.Close();
        }
    }
    private void AbrirGuia(String Rut, String Numero) {
        ExeSql Sql = new ExeSql();
        ExeSqlLuvaly luv = new ExeSqlLuvaly();
        ResultSet producto = null;
        ResultSet Rs;
        try {
            Rs = Sql.Select("select c.rut,c.nrodocto,c.nrodocorigen,c.femision,c.occh,c.totalexento,c.totalafecto,c.totaliva,c.totaldocto, cc.giro,cc.direccion,cc.ciudad,cc.comuna,cc.region "
                    + "from ctactecli c left join clicontacto cc "
                    + "on c.rut=cc.rut and c.nrodocorigen=cc.codigo_oc "
                    + "WHERE c.rut=" + Rut + " "
                    + "AND c.tipdocto='GDC' "
                    + "AND c.nrodocto=" + Numero);

            Rs.next();
            dtEmision.setDate(Rs.getDate("femision"));
            txDireccion.setText(Rs.getString("direccion").trim());
            txCiudad.setText(Rs.getString("ciudad").trim());
            txComuna.setText(Rs.getString("comuna").trim());
            txExento.setText(Rs.getString("totalexento"));
            txNeto.setText(Rs.getString("totalafecto"));
            txIva.setText(Rs.getString("totaliva"));
            txTotal.setText(Rs.getString("totaldocto"));

            DefaultComboBoxModel cbMd = new DefaultComboBoxModel();
            cbMd.addElement(Rs.getString("nrodocorigen"));
            cbCodigoOc.setModel(cbMd);

            DefaultComboBoxModel cbMd2 = new DefaultComboBoxModel();
            cbMd2.addElement(Rs.getString("occh"));
            cbNroOrden.setModel(cbMd2);

        } catch (Exception e) {
            System.out.println(e);
        }

        //Carga Detalles
        DefaultTableModel TableModel = (DefaultTableModel) Grilla.getModel();
        try {
            Rs = Sql.Select("select d.sku,p.nombre,u.um,d.cantidad,d.valorunitario,d.totallinea \n"
                    + "from ctacteclidet d\n"
                    + "left join producto p\n"
                    + "on d.sku=p.sku\n"
                    + "left join par_unidad u\n"
                    + "on p.unidad=u.codigo\n"
                    + "where d.rut=" + Rut + "\n"
                    + "and d.tipdocto='GDC'\n"
                    + "and d.nrodocto=" + Numero);
            while (Rs.next()) {
                producto = luv.Select("select p.nombre, u.um from producto p \n"
                        + "LEFT JOIN par_unidad u ON u.codigo=p.unidad\n "
                        + "where p.sku = '"+Rs.getString("sku").trim()+"'");
                 producto.next();
                TableModel.addRow(new Object[]{Rs.getString("sku"), producto.getString("nombre"), producto.getString("um"), Rs.getString("cantidad"), Rs.getString("valorunitario"), Rs.getString("totallinea")});
            }
            Sumador();
        } catch (Exception e) {
            System.out.println(e);
        }
        finally{
            Sql.Close();
            luv.Close();
        }
    }
    private void FindeAgno(){
        if(fmMain.GetFacNewYear()==1){
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                Date Fecha=df.parse("2015/12/31");
                dtEmision.setDate(Fecha);
                //dtEmision.setEditable(false);
                
            } catch (Exception e) {
            }
            
        }
            
        System.out.println("NewYear");
    }
    private void btIrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIrActionPerformed
                boolean Carga;
        if(!txRut.getText().isEmpty()){
            Carga=CargaCliente(txRut.getText());
            
        }
        else{
            jdBuscarCliPrv BPC = new jdBuscarCliPrv(null, true);
            BPC.setLocationRelativeTo(null);
            BPC.setTitle("Buscar Cliente");
            BPC.SetTipo(1);
            BPC.setVisible(true);
            Carga=CargaCliente(BPC.GetRut());
        }
        
        // Si cargó cliente y es nuevo.
        if(Carga && (Tipo==1 || Tipo==-1) ){
            CargaCodOc(RutMaster);
            if(fmMain.GetFacNewYear()==1)
                FindeAgno();
            Habilita(true);
            btGuardar.setEnabled(true);
            btCancelar.setEnabled(true);
            btNuevo.setEnabled(false);
            btEditar.setEnabled(false);
            btAbrir.setEnabled(false);
            fmMain.SetEstado(fmMain.pnPestanas.getSelectedIndex(),1);
        
        }
        // Si cargó cliente y esta abriendo
        if(Carga && Tipo==2){
            
            jdAbrirDocumento ADoc = new jdAbrirDocumento(null, true);
                if(ADoc.ShowModal("FAC",RutMaster)==JOptionPane.YES_OPTION){
                    AbrirFactura(ADoc.GetNumero());
                    SetTipo(2);
                   //VERIFICA NIVEL DE USUARIO PARA EDITAR FACTURA
                    int pruebaUusario = 100;
                    int intNivelUsuario = 0;
                    intNivelUsuario = fmMain.trae_nivel(fmMain.GetUsuario());
                    if( intNivelUsuario == pruebaUusario){
                         btEditar.setVisible(true);
                    }else{
                         btEditar.setVisible(false);
                    }
                }
                else{
                    txRut.setText("");
                    txNombre.setText("");
                    txRut.requestFocus();
                }
            
        }
        
            

    }//GEN-LAST:event_btIrActionPerformed
    private void cbCodigoOcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCodigoOcActionPerformed
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        DefaultComboBoxModel cbMd = new DefaultComboBoxModel();
        cbNroOrden.setModel(cbMd);

        try {
            // --Carga Datos de Cliente
            Rs = Sql.Select("select giro,direccion,ciudad,comuna,region\n"
                    + "from clicontacto\n"
                    + "where rut=" + txRut.getText()
                    + " and codigo_oc=" + cbCodigoOc.getSelectedItem().toString());
            Rs.next();
            txDireccion.setText(Rs.getString("direccion").trim());
            txCiudad.setText(Rs.getString("ciudad").trim());
            txComuna.setText(Rs.getString("comuna").trim());
        } catch (Exception e) {
            System.out.println(e);
        }

        //--Carga Ordenes Pendientes de Despacho
        if (Tipo != 2) {
            try {
                Rs = Sql.Select("select occh as orden\n"
                        + "from ctactecli\n"
                        + "where tipdocto='GDC'\n"
                        + "and rut=" + txRut.getText() + "\n"
                        + "and codigo_oc=" + cbCodigoOc.getSelectedItem().toString().trim() + "\n" 
                        + "and nrodocrel is null");

//                Rs = Sql.Select("select orden\n" +
//                            "from occh\n" +
//                            "where estado < 2\n" +
//                            "and  rut=" + txRut.getText() +
//                           " and codigo_oc=" + cbCodigoOc.getSelectedItem().toString());
                while (Rs.next()) {
                    cbMd.addElement(Rs.getString("orden").trim());
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally{
                Sql.Close();
            }
        }
    }//GEN-LAST:event_cbCodigoOcActionPerformed

    private void cbNroOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNroOrdenActionPerformed
//        ResultSet Rs;
//        DefaultTableModel TableModel = (DefaultTableModel) Grilla.getModel();
//        
//        //Limpia Grilla de Productos
//        while (TableModel.getRowCount() > 0)
//                TableModel.removeRow(0);
//        
//        
//        
//        String Query =  "select d.sku,p.nombre,u.um,d.separado,d.valorunitario,d.totlinea\n" +
//                        "from occhdet d\n" +
//                        "left join producto p \n" +
//                        "on d.sku=p.sku\n" +
//                        "left join par_unidad u\n" +
//                        "on p.unidad=u.codigo\n" +
//                        "where rut=" + txRut.getText() +
//                        " and codigo_oc=" + cbCodigoOc.getSelectedItem().toString().trim() +
//                        " and orden='" + cbNroOrden.getSelectedItem().toString().trim() + "'";
//        try{
//            Rs = Sql.Select(Query);
//            while(Rs.next()){
//                TableModel.addRow(new Object[]{Rs.getString("sku"), Rs.getString("nombre"), Rs.getString("um"),Rs.getString("separado"),Rs.getString("valorunitario"),Rs.getString("totlinea")});
//            }
//        } catch (Exception e) {
//                System.out.println(e);
//        }
    }//GEN-LAST:event_cbNroOrdenActionPerformed
    public String getFechaAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return( sdf.format( (dtEmision.getDate()).getTime() ) );
    }
    
    public boolean ExisteContactoFactura() {
        boolean existe = false;
        ExeSql sqlo = new ExeSql();
        ResultSet rss = null;
        try {
            rss = sqlo.Select("select cp.email\n" +
                             "from clicontactopersonas cp\n" +
                             "where cp.id_cargo = 8\n" +
                             "and cp.codigo_oc = "+cbCodigoOc.getSelectedItem().toString()+"\n" +  
                             "and cp.rut = "+txRut.getText()+"");
            
            if(rss.next()==false){
                existe = false;
                JOptionPane.showMessageDialog(null, "Cliente no posee contacto para facturación");
                if(JOptionPane.showConfirmDialog(null, "¿Desea Crearlo?")==0){
                    pfClientes Cli = new pfClientes();
            //        Cli.setOpaque(false);
                    pnPestanas.addTab("Nuevo Cliente", Cli);
                    PanelTab btc=new PanelTab(pnPestanas,0);
                    pnPestanas.setTabComponentAt(pnPestanas.indexOfComponent(Cli), btc);
                    pnPestanas.setSelectedIndex(pnPestanas.getTabCount()-1);
                    Cli.txRut.setText(txRut.getText());
                    Cli.btIr.doClick();
                }
                else {
 
                }
            }
            else {
                
                existe = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(pfFACCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }
    
    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed
       

        ResultSet Rs, Rs2;
        if(fmMain.OkCancel("¿Guardar Documento?")== JOptionPane.OK_OPTION && ExisteContactoFactura()){
            ExeSql Sql = new ExeSql();
            String Query = "";
            String Guia;
            
            if(rbReferencia.isSelected() && txReferencia.getText().isEmpty()){
                fmMain.Mensaje("Ninguna referencia");
                return;
            }

            //Graba Nueva Guia
            if (Tipo == 1) {
                try {
                    //Obtiene Correlativo
//                    Sql.ExeSql("update par_correlativo set numero=numero + 1 where tipo='FAC'");
//                    String NewCorrelativo = Sql.SelectUnico("select numero from par_correlativo where tipo='FAC'");
                        int resultado_ = Sql.ExeSqlInt("update par_correlativo\n" +
                                                       "set numero = numero + 1\n" +
                                                       "where tipo='FAC'");
                        Sql.Commit();
                        if(resultado_>0){
                            System.out.println("Se aumentó correlativo");
                        }
                        Rs = Sql.Select("select sp_getcorrelativo \n" +
                                        "from sp_getcorrelativo('FAC')");   
                        
                        Rs.next();
                        String NewCorrelativo_test  = Rs.getString("sp_getcorrelativo");
                        String NewCorrelativo = Rs.getString("sp_getcorrelativo").trim();
                        Rs.close();
                        String numero = "";

                    
                    
                    if(!NewCorrelativo.equals("")){      
                        if (lstGuias.getModel().getSize() > 1) {
                            Guia = "-99";
                        } else {
                            Guia = lstGuias.getModel().getElementAt(0).toString();
                        }
 
                        Query = " INSERT INTO ctactecli(\n"
                                + " rut, tipdocto, nrodocto, tipdocorigen, nrodocorigen, femision, \n"
                                + " totalexento, totalafecto, totaliva, totalimpespecifico,totaldocto,codigo_oc,occh,tipodespacho)\n"
                                + " VALUES ("
                                + txRut.getText() + ","
                                + "'FAC',"
                                + NewCorrelativo + ","
                                + "'GDC',"
                                + Guia + ",'"
                                + getFechaAsString() + "',"
                                + fmMain.SetGuardar(txExento.getText().trim()) + ","
                                + fmMain.SetGuardar(txNeto.getText().trim()) + ","
                                + fmMain.SetGuardar(txIva.getText().trim()) + ","
                                + fmMain.SetGuardar(txImpEspecifico.getText().trim()) + ","
                                + fmMain.SetGuardar(txTotal.getText().trim()) + ","
                                + cbCodigoOc.getSelectedItem().toString() + ",'"
                                + cbNroOrden.getSelectedItem().toString().trim() +  "'," + cbTipoDespacho.getSelectedIndex()+ ");";
                        Sql.ExeSql(Query);

                    // Actualiza Guias
                        for (int i = 0; i < lstGuias.getModel().getSize(); i++) {
                            String NroGuia = lstGuias.getModel().getElementAt(i).toString();
                            Query = "update ctactecli set tipdocrel='FAC', nrodocrel=" + NewCorrelativo + "\n";
                                    if (chk_esRefacturacion.isSelected()){
                                        Query = Query + ", es_refacturacion = true " + "\n";    
                                    }
                                    Query = Query + " where rut=" + txRut.getText() + "\n" +
                                    " and tipdocto='GDC' \n"
                                    + "and nrodocto=" + NroGuia;
                            Sql.ExeSql(Query);
                        }

                        for (int i = 0; i < Grilla.getRowCount(); i++) {
                            Query = " INSERT INTO ctacteclidet(\n"
                                    + " rut, tipdocto, nrodocto, sku, cantidad, valorunitario, totallinea)\n"
                                    + " VALUES ("
                                    + txRut.getText() + ","
                                    + "'FAC',"
                                    + NewCorrelativo + ",'"
                                    + Grilla.getModel().getValueAt(i, GetCol("Sku")).toString().trim() + "',"
                                    + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("Cantidad")).toString().trim()) + ","
                                    + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("UniReal")).toString().trim()) + ","
                                    + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, GetCol("Total")).toString().trim()) + ")";
                            Sql.ExeSql(Query);
                        }
                        //Agrega Referencias externas
                        if(rbReferencia.isSelected()){
                            int CodRef = cbReferencia.getSelectedIndex() + 801;
                            if (cbReferencia.getSelectedIndex()==2)
                            {
                                CodRef = 10801;
                            }
                            if (cbReferencia.getSelectedIndex()==3)
                            {
                                CodRef = 20801;  
                            }

                            Sql.ExeSql("insert into fac_referencias\n" +
                                       "(rut,tipdocto,nrodocto,cod_ref,doc_ref) values\n" +
                                       "("+ txRut.getText() +",'FAC',"+ NewCorrelativo +"," + CodRef + ",'"+ txReferencia.getText().trim() +"')");
                        }
                        
          
                        
                        Sql.Commit();
    
                        JOptionPane.showMessageDialog(null, "Guardado");
                        lbFolio.setText(NewCorrelativo);
                        SetTipo(2);
                        //btEmitir.doClick();
                        Emitir_Factura();
                    }
            } catch (Exception e) {
                Sql.Rollback();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            } finally {
                Sql.Close();

            }
             

            } else {
                String folio = lbFolio.getText().trim();
                if (lstGuias.getModel().getSize() > 1) {
                    Guia = "-99";
                } else {
                    Guia = lstGuias.getModel().getElementAt(0).toString();
                }
                try {
                    Query = "UPDATE ctactecli SET "
                            + "tipdocorigen='OCC', "
                            + "nrodocorigen="+Guia+","
                            + "occh = '" + cbNroOrden.getSelectedItem().toString().trim() + "',"
                            + "codigo_oc = "+cbCodigoOc.getSelectedItem().toString().trim()+","
                            + "femision='" + getFechaAsString() + "',"
                            + "totalexento=" +fmMain.SetGuardar( txExento.getText().trim()) + ","
                            + "totalafecto=" + fmMain.SetGuardar(txNeto.getText().trim()) + ","
                            + "totaliva=" + fmMain.SetGuardar(txIva.getText().trim() )+ ","
                            + "totaldocto=" + fmMain.SetGuardar(txTotal.getText().trim()) + ", "
                             + "tipodespacho= " + cbTipoDespacho.getSelectedIndex() + " "
                            + "WHERE tipdocto='FAC' "
                            + "AND   nrodocto="+folio+" "
                            + "AND   rut=" + txRut.getText();

                    Sql.ExeSql(Query);
                    System.out.println("Guarda Encabezado");

                    Query = "DELETE FROM ctacteclidet "
                            + "WHERE tipdocto='FAC' "
                            + "AND   nrodocto="+folio+" "
                            + "AND   rut=" + txRut.getText();
                    Sql.ExeSql(Query);

                    for (int i = 0; i < Grilla.getRowCount(); i++) {
                        Query = " INSERT INTO ctacteclidet(\n"
                                + " rut, tipdocto, nrodocto, sku, cantidad, valorunitario, totallinea)\n"
                                + " VALUES ("
                                + txRut.getText() + ","
                                + "'FAC',"
                                + ""+folio+",'"
                                + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, 0).toString().trim()) + "',"
                                + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, 3).toString().trim()) + ","
                                + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, 4).toString().trim()) + ","
                                + fmMain.SetGuardar(Grilla.getModel().getValueAt(i, 5).toString().trim()) + ")";

                        Sql.ExeSql(Query);
                    }
                    Sql.Commit();
                    JOptionPane.showMessageDialog(null, "Guardado");
                    String cuentasxcobrar = "update cli_cuentasxcobrar\n" +
                      "        \n" +
                      " set monto = "+fmMain.SetGuardar(txTotal.getText().trim()) +"\n"+
                      " where rut = "+txRut.getText()+" and nrodocto = "+folio+" and codigo_oc = "+cbCodigoOc.getSelectedItem().toString().trim()+" and tipdocto = 'FAC'";
                    int resultado = Sql.ExeSqlInt(cuentasxcobrar);
                    

                    Sql.Commit();
                    if(resultado > 0)
                    JOptionPane.showMessageDialog(null, "Se modificó cuenta por cobrar");
                    SetTipo(2);
                    
                    
                } catch (Exception e) {
                    Sql.Rollback();
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                } finally{
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
        double Comision=0;
        String Unitario,Cantidad;
    
        for(int i=0; i< Grilla.getRowCount(); i++){
            Unitario = Grilla.getValueAt(i,4).toString();
            Cantidad = Grilla.getValueAt(i,3).toString();
            Unitario = Unitario.replace(fmMain.GetMiles(), "");
            Cantidad = Cantidad.replace(fmMain.GetMiles(), "");
            Neto =  (Double.parseDouble(Unitario) * Double.parseDouble(Cantidad)) + Neto;
        }
        
        Neto = Math.round(Neto);
        if (txRut.getText().trim().equals("96726970")){
        
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
            
            if(jdNumero.GetNumero() != -99 ){   
       
                try {
                    
                    Grilla.setValueAt(jdNumero.GetNumero(), Grilla.getSelectedRow(),3);
                    
                    double vcantidad = Double.parseDouble(Grilla.getValueAt(Grilla.getSelectedRow(),3).toString().replaceAll("\\,",""));
                    
                    Grilla.setValueAt(fmMain.FormatoNumero(jdNumero.GetPrecio()), Grilla.getSelectedRow(),4);
                    double TotLinea = vcantidad * jdNumero.GetPrecio();
                    Grilla.setValueAt(fmMain.FormatoNumero(TotLinea),Grilla.getSelectedRow(),5);
                
                   // Grilla.setValueAt(jdNumero.GetNumero(), Grilla.getSelectedRow(),GetCol("CantReal"));
                    Grilla.setValueAt(vcantidad, Grilla.getSelectedRow(),3);
                    Grilla.setValueAt(jdNumero.GetPrecio(), Grilla.getSelectedRow(),4);  
                
                    Sumador();
                
                }catch (Exception e) {
                 
                    System.out.println(e);
                }
            }
        }
        
    }//GEN-LAST:event_GrillaMouseClicked

    private void btEmitirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEmitirActionPerformed

        Emitir_Factura();
//        ExeSql Sql = new ExeSql();
//        
//        try {
//            
//            Sql.ExeSql("update ctactecli set estado=1 where tipdocto='FAC' and nrodocto=" + lbFolio.getText().trim());
//            Sql.Commit();
//            //GeneraDTE.Generar(txRut.getText().trim(),"FAC" ,lbFolio.getText().trim());
//            GeneraDTE_FTP.Generar(txRut.getText().trim(),"FAC" ,lbFolio.getText().trim());
//            
//            
//            JOptionPane.showMessageDialog(null,"Documento Emitido");
//            btEmitir.setEnabled(false);
//            btEmitir.setVisible(false);
//            txEstado.setText("Emitido");
//            btEditar.setEnabled(false);
//        } catch (SQLException ex) {
//            fmMain.Mensaje("se produjo un error en el proceso: "+ex.getMessage());
//            Sql.Rollback();
//        } finally{
//            Sql.Close();
//        }
//        
       
       
    }//GEN-LAST:event_btEmitirActionPerformed
    
    private void Emitir_Factura(){
        Runnable miRunnable = new Runnable() {
            public void run() {
                try{
                    ExeSql Sql = new ExeSql();
                    try {

                        Sql.ExeSql("update ctactecli set estado=1 where tipdocto='FAC' and nrodocto=" + lbFolio.getText().trim());
                        Sql.Commit();

//                        GeneraDTE_FTP.Generar(txRut.getText().trim(),"FAC" ,lbFolio.getText().trim());

                        JOptionPane.showMessageDialog(null,"Documento Emitido");
                        btPDF.setVisible(true);

                        btEmitir.setEnabled(false);
                        btEmitir.setVisible(false);
                        txEstado.setText("Emitido");
                        btEditar.setEnabled(false);
                        
                    } catch (SQLException ex) {
                        fmMain.Mensaje("se produjo un error en el proceso: "+ex.getMessage());
                        Sql.Rollback();
                    } finally{
                        Sql.Close();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                     MailRápido();    
                }
            }

            
        };
        Thread hilo = new Thread(miRunnable);
        hilo.start();
        
        
    }
    
    
    private void btGuiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuiasActionPerformed
        jdAgregaGuia AddGDC = new jdAgregaGuia(null, true);
        AddGDC.setTitle("Guias");
        AddGDC.setLocationRelativeTo(lstGuias);
        AddGDC.CargaGuias(txRut.getText(), cbCodigoOc.getSelectedItem().toString(), cbNroOrden.getSelectedItem().toString());
        AddGDC.setVisible(true);
        try {
            lstGuias.setModel(AddGDC.GetLista());
            CargaProductosDesdeGuias();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }//GEN-LAST:event_btGuiasActionPerformed

    private void btCorrigePesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCorrigePesoActionPerformed
        jdAjustePesos aju = new jdAjustePesos(null, true);
        aju.setLocationRelativeTo(null);
        aju.setTitle("Ajuste Pesos");
        aju.setVisible(true);
        PesoCorreccion = aju.GetAjuste();
        Sumador();
    }//GEN-LAST:event_btCorrigePesoActionPerformed

    private void rbReferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbReferenciaActionPerformed
        if(rbReferencia.isSelected()){
            cbReferencia.setVisible(true);
            txReferencia.setVisible(true);
        }
        else{
            cbReferencia.setVisible(false);
            txReferencia.setVisible(false);
        }
            
    }//GEN-LAST:event_rbReferenciaActionPerformed

    private void btPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPDFActionPerformed
//        String path = "\\\\192.168.0.130\\Documentos Electronicos";
//            String files;
//            File folder = new File(path);
//            File[] listOfFiles = folder.listFiles(new FilenameFilter() {
//                @Override
//                public boolean accept(File folder, String Nombre) {
//                    return Nombre.contains("33F" + lbFolio.getText().trim());
//                }
//            });
//            for (int i = 0; i < listOfFiles.length; i++)         {
//
//            if (listOfFiles[i].isFile())             {
//                
//                files = listOfFiles[i].getName();
//                try {
//                    File pathCompleto = new File(path + "\\" +files);
//                    Desktop.getDesktop().open(pathCompleto);
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
         busca("33-F", lbFolio.getText().trim());
         System.out.println(lbFolio.getText().trim());
         StringSelection Transporte = new StringSelection(cbCodigoOc.getSelectedItem().toString().trim() + "-" + cbNroOrden.getSelectedItem().toString().trim());
         Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
         cb.setContents(Transporte, null);
    }//GEN-LAST:event_btPDFActionPerformed

    
    
    private void cbReferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbReferenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbReferenciaActionPerformed

    private void btEmiteManActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEmiteManActionPerformed
        // TODO add your handling code here:
        
        Emitir_Factura();
        
//          ExeSql Sql = new ExeSql();
//        
//        try {
//            
//            Sql.ExeSql("update ctactecli set estado=1 where tipdocto='FAC' and nrodocto=" + lbFolio.getText().trim());
//            Sql.Commit();
//            //GeneraDTE.Generar(txRut.getText().trim(),"FAC" ,lbFolio.getText().trim());
//            GeneraDTE_FTP.Generar(txRut.getText().trim(),"FAC" ,lbFolio.getText().trim());
//            
//            
//            JOptionPane.showMessageDialog(null,"Documento Emitido");
//            btEmitir.setEnabled(false);
//            btEmitir.setVisible(false);
//            txEstado.setText("Emitido");
//            btEditar.setEnabled(false);
//        } catch (SQLException ex) {
//            fmMain.Mensaje("se produjo un error en el proceso: "+ex.getMessage());
//            Sql.Rollback();
//        } finally{
//            Sql.Close();
//        }
        
    }//GEN-LAST:event_btEmiteManActionPerformed

    public void Mail() {
        Window ventana = new Window();// TODO add your handling code here:
        Runnable miRunnable = new Runnable() {
            public void run() {
                try{
                    ExeSql Sql = new ExeSql();
                    ResultSet Rs;
                    String Query ="";
                    String Archivo="";
                    String Guias[] = new String [50];
                    String Voucher[] = new String [50];
                    int Transporte_arr[] = new int [50];
                    String Archivos[] = new String [50];
                    int x=0;
                    int indArch =0;
                    String Valor = "";

                    Archivo =Ftp.busca_SinAbrir("33", lbFolio.getText().trim(),"facturas","dte");

                     try { 

                         //Busca Guias Asociadas
                         Query = " select nrodocto from ctactecli\n" +
                                " where tipdocrel = 'FAC' and nrodocrel = " + lbFolio.getText().trim();
                         Rs = Sql.Select(Query);

                         int i = 0;
                         while (Rs.next()){
                             Guias[i] = Rs.getString("nrodocto");
                             i++;
                         }

                        for (int j=0; j<= Guias.length-1; j++){
                                if (Guias[j] != null){
                                 Archivos[j]  =    Ftp.busca_SinAbrir("52", Guias[j],"facturas","dte");

                                }
                                else{
                                   Archivos[j]  = Archivo;
                                  indArch = j+1;
                                   break;
                                }
                        }

                        int k=0;
                        for (int j=0; j<= Guias.length-1; j++){
                         //-------------------------------------------------------------------------------------------
                                         //Busca Voucher Asociados
                                        Query = " select transporte, voucher , nrodocto, tipdocto  from transporte_despachos \n" +
                                               " where tipdocto = 'GDC"
                                                + "' and nrodocto = " + Guias[j] + " and pdf_voucher is not null";
                                        Rs = Sql.Select(Query);

                                        if (Rs.next()){
                                            Voucher[k] = Rs.getString("voucher");
                                            Transporte_arr[k]= Rs.getInt("transporte");
                                            k++;
                                        }
                        }

                                       Valor="";     
                                       for (int w=0; w<= Voucher.length-1; w++){
                                               if (Voucher[w] != null && Transporte_arr[w]==1){
                                                Archivos[indArch]  =    Ftp.busca_SinAbrir("52", Voucher[w],"tnt","tnt");
                                                indArch++;
                                               }
                                               else if (Voucher[w] != null && Transporte_arr[w]==7){
                                                    Valor = Ftp.busca_SinAbrir("52", Voucher[w],"blue","blue");
                                                   if (!Valor.equals(""))
                                                   {   
                                                        Archivos[indArch]  =    Valor;
                                                   }     
                                                indArch++;
                                               }
                                               else{
                                                  break;
                                               }
                                       }
                                 //-------------------------------------------------------------------------------------------




                    } catch (Exception e) {
                            System.out.println("2" + e);
                        }
                        finally{
                            Sql.Close();
                        }	


                    //fmMain.Mensaje("Archivo a enviar : " + Archivo);

                    ventana.Username = "despachos@luvaly.cl";
                    ventana.PassWord = "3Logica.";
                    ventana.Archivo = Archivo;
                    ventana.Archivos = Archivos;

                    ventana.NumFactura = lbFolio.getText().trim();
                    ventana.Rut = txRut.getText().trim();

                    ventana.Orden = cbNroOrden.getSelectedItem().toString().trim();
                    ventana.CodigoOC=   cbCodigoOc.getSelectedItem().toString().trim();
            //        ventana.setAdjunto(Archivo);
            //        ventana.setOC(cbCodigoOc.getSelectedItem().toString().trim() + "-" + cbNroOrden.getSelectedItem().toString().trim());

                    ventana.setDatos();
                    ventana.setVisible(true);


                     StringSelection Transporte = new StringSelection(cbCodigoOc.getSelectedItem().toString().trim() + "-" + cbNroOrden.getSelectedItem().toString().trim());
                     Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
                     cb.setContents(Transporte, null);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    ventana.jButtonEnviar.doClick();
                }
            }

            
        };
        Thread hilo = new Thread(miRunnable);
        hilo.start();
        
        
    }
    
    private void btMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMailActionPerformed
        MailRápido();
//        Mail();

                
    }//GEN-LAST:event_btMailActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        SetTipo(3);
    }//GEN-LAST:event_btEditarActionPerformed
    public static void wait(int ms) {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Grilla;
    private javax.swing.JButton btAbrir;
    private javax.swing.JButton btAgregar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btCorrigePeso;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btEliminar;
    private javax.swing.JButton btEmiteMan;
    private javax.swing.JButton btEmitir;
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btGuias;
    public javax.swing.JButton btIr;
    private javax.swing.JButton btMail;
    public javax.swing.JButton btNuevo;
    private javax.swing.JButton btPDF;
    public javax.swing.JComboBox cbCodigoOc;
    public javax.swing.JComboBox cbNroOrden;
    private javax.swing.JComboBox<String> cbReferencia;
    private javax.swing.JComboBox cbTipoDespacho;
    private javax.swing.JCheckBox chk_esRefacturacion;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private javax.swing.JLabel lbFolio;
    private javax.swing.JList lstGuias;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JCheckBox rbReferencia;
    private javax.swing.JTextField txCiudad;
    private javax.swing.JTextField txComuna;
    private javax.swing.JTextField txDireccion;
    private javax.swing.JTextField txDv;
    private javax.swing.JTextField txEstado;
    private javax.swing.JTextField txExento;
    private javax.swing.JTextField txImpEspecifico;
    private javax.swing.JTextField txIva;
    private javax.swing.JTextField txNeto;
    private javax.swing.JTextField txNombre;
    private javax.swing.JTextField txReferencia;
    public javax.swing.JTextField txRut;
    private javax.swing.JTextField txTotal;
    // End of variables declaration//GEN-END:variables
}
