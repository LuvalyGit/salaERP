package PanelForm;

import Conexion.ExeSql;
import Conexion.ExeSqlLuvaly;
import Dialogos.*;
import Formularios.fmMain;
import Utilidades.Ftp;
import Utilidades.GeneraDTE;
import Utilidades.GeneraDTE_FTP;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class pfFACClienteExenta extends javax.swing.JPanel {
    String RutMaster;
    int Tipo; // 0::Nuevo    1:Abrir
    DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    private enum Columnas{Sku,Nombre,UM,Cantidad,Unitario,Total};
    
    private void MailRápido() {
        JOptionPane.showMessageDialog(null, "Se enviará factura, un momento.");
        String Archivo =Ftp.busca_SinAbrir("33", lbFolio.getText().trim(),"facturas","dte");    
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
                    " and orden = '" + Orden + "')";
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
                    " where id_cargo IN (8) and rut =" +  Rut + " and  codigo_oc =" + CodigoOC; 
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
                " where rut =" + Rut + " and  codigo_oc = " + CodigoOC + " and nrodocrel = " + NumFactura + "  and tipdocrel= 'FAC')";
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
            message.setSubject("FACTURA N°: "+NumFactura+" - LUVALY SPA"); 

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
                            "            <span>Luvaly SPA<br>\n" +
                            "                Huerfanos 01871, Modulo 11, Temuco, Araucanía</span>\n" +
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
    

    public pfFACClienteExenta() {
        initComponents();
        SetTipo(-1);
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        Grilla.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        Grilla.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        Grilla.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        btAgregar.setVisible(false);
        btEliminar.setVisible(false);
        btEmitir.setVisible(false);
        btEmiteMan.setVisible(false);
        txRut.setEnabled(false);
        txRut.setEditable(false);
        btIr.setEnabled(false);
//        cbCodigoOc.setSelectedIndex(-1);
//        cbNroOrden.setSelectedIndex(-1);
        
        if(fmMain.GetUsuarioAdministrador()){
            btEmiteMan.setVisible(true);
        }
        
        
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
        btEmiteMan = new javax.swing.JButton();
        chk_Refacturacion = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lbFolio = new javax.swing.JLabel();
        txEstado = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dtEmision = new org.jdesktop.swingx.JXDatePicker();
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
        jLabel18 = new javax.swing.JLabel();
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
        txTotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();

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

        btEmitir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos16/derecha16.png"))); // NOI18N
        btEmitir.setText("Emitir");
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

        jXLabel1.setForeground(new java.awt.Color(0, 51, 0));
        jXLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/OCCliente24.png"))); // NOI18N
        jXLabel1.setText("FACTURA EXENTA CLIENTE");
        jXLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        btEmiteMan.setText("Emite Man");
        btEmiteMan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEmiteManActionPerformed(evt);
            }
        });

        chk_Refacturacion.setText("Es Refacturacion");

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
                .addGap(84, 84, 84)
                .addComponent(chk_Refacturacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
                .addComponent(btEmiteMan)
                .addGap(82, 82, 82)
                .addComponent(btEmitir)
                .addGap(43, 43, 43))
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
                        .addComponent(btEmitir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btEmiteMan)
                        .addComponent(chk_Refacturacion))
                    .addComponent(btGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7))
        );

        add(pnMenu);

        jPanel1.setBackground(new java.awt.Color(220, 215, 226));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("FACTURA");

        lbFolio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbFolio.setForeground(new java.awt.Color(255, 51, 51));

        txEstado.setEditable(false);

        jLabel5.setText("F. Emisión");

        jLabel6.setText("Estado");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txEstado)
                    .addComponent(lbFolio)
                    .addComponent(dtEmision, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lbFolio))
                .addGap(4, 4, 4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dtEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        Grilla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "UM", "Cantidad", "V. Unitario", "Total Linea"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        jLabel18.setText("Despacho");

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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txDireccion, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(txRut, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txDv, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btIr, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTipoDespacho, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(txComuna, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txNombre))
                .addContainerGap(108, Short.MAX_VALUE))
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
                            .addComponent(jLabel18)
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
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cbNroOrden, javax.swing.GroupLayout.Alignment.LEADING, 0, 145, Short.MAX_VALUE)
                    .addComponent(cbCodigoOc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCodigoOc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbNroOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

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

        txTotal.setEditable(false);
        txTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txTotal.setText("0");

        jLabel13.setText("TOTAL");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txExento, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(txTotal))
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
                    .addComponent(jLabel13)
                    .addComponent(txTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 983, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(457, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btEliminar)
                        .addComponent(btAgregar))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
          Habilita(true);
          Edicion(false);
          txRut.setEditable(true);
          Tipo=2;
        }
        else if(ElTipo==3){
            btEmitir.setEnabled(false);
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
  
    
    txExento.setEditable(Estado);
    
    txTotal.setEditable(Estado);
    btAgregar.setEnabled(Estado);
    btEliminar.setEnabled(Estado);
    
    dtEmision.setEditable(Estado);
    
    
    

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
    
    txExento.setText("");
    
    txTotal.setText("");
    txDireccion.setText("");
    txComuna.setText("");
    txCiudad.setText("");
           
    cbTipoDespacho.setSelectedIndex(0); 
    cbCodigoOc.setModel(dfCm);
//    cbContacto.setModel(dfCm);
    
        
    while(dfTm.getRowCount()>0)
        dfTm.removeRow(0);
    //chbPrioridad.
    dtEmision.setDate(null);
    
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
  
    dtEmision.setEnabled(Estado);
    txExento.setEnabled(Estado);
    txTotal.setEnabled(Estado);
    btIr.setEnabled(Estado);
    txEstado.setEnabled(Estado);
    dtEmision.setEnabled(Estado);
    cbTipoDespacho.setEnabled(Estado);
    
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
    }//GEN-LAST:event_btNuevoActionPerformed

    private void txRutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txRutKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            btIr.doClick();
        }
    }//GEN-LAST:event_txRutKeyPressed
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
            System.out.println(e);
            return false;
        } finally{
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
        
        try{
            Rs = Sql.Select("select distinct(codigo_oc) as codigo_oc from clicontacto where rut = " + Rut);
            while(Rs.next()){
                cbMd.addElement(Rs.getString("codigo_oc")); 
            }
         } catch (Exception e) {
            System.out.println(e); 
         } finally{
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
        }
//        Sumador();
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
public void AbrirFactura(String Rut,String Numero){
        ResultSet Rs;
        ExeSql Sql = new ExeSql();
        ExeSqlLuvaly luv = new ExeSqlLuvaly();
        ResultSet producto = null;
        DefaultTableModel TableModel = (DefaultTableModel) Grilla.getModel();
    try {//Carga Detalles

        while (TableModel.getRowCount() > 0) {
            TableModel.removeRow(0);
        }

        Rs = Sql.Select("select c.rut,c.nrodocto,c.nrodocorigen,c.estado as estadoint, c.femision,c.occh,c.totalexento,c.totalafecto,c.totaliva,totalimpespecifico,c.totaldocto, cc.giro,cc.direccion,cc.ciudad,cc.comuna,cc.region,c.estado "
                + "from ctactecli c left join clicontacto cc "
                + "on c.rut=cc.rut and c.nrodocorigen=cc.codigo_oc "
                + "WHERE c.rut=" + Rut + " "
                + "AND c.tipdocto='FEC' "
                + "AND c.nrodocto=" + Numero);
        Rs.next();
        lbFolio.setText(Numero);
        dtEmision.setDate(Rs.getDate("femision"));
        txDireccion.setText(Rs.getString("direccion").trim());
        txCiudad.setText(Rs.getString("ciudad").trim());
        txComuna.setText(Rs.getString("comuna").trim());

        if (Rs.getInt("estadoint") == 0) {
            btEmitir.setEnabled(true);
        } else {
            btEmitir.setEnabled(false);
            btEditar.setEnabled(false);
        }

        txExento.setText(fmMain.FormatoTotal(Rs.getDouble("totalexento")));
        txTotal.setText(fmMain.FormatoTotal(Rs.getDouble("totaldocto")));

        switch (Rs.getInt("estado")) {
            case 0:
                txEstado.setText("Sin Emitir");
                break;
            case 1:
                txEstado.setText("Emitida");
                break;
        }

        DefaultComboBoxModel cbMd = new DefaultComboBoxModel();
        cbMd.addElement(Rs.getString("nrodocorigen").trim());
        cbCodigoOc.setModel(cbMd);

        DefaultComboBoxModel cbMd2 = new DefaultComboBoxModel();
        cbMd2.addElement(Rs.getString("occh"));
        cbNroOrden.setModel(cbMd2);

//        Rs = Sql.Select(" select dirdespacho from occh"
//                + " where rut=" + txRut.getText()
//                + " and codigo_oc=" + cbCodigoOc.getSelectedItem().toString().trim()
//                + " and orden='" + cbNroOrden.getSelectedItem().toString().trim() + "'");
//        Rs.next();


        Rs = Sql.Select("select d.sku,p.nombre,u.um,d.cantidad,d.valorunitario,d.totallinea \n"
                + "from ctacteclidet d\n"
                + "left join producto p\n"
                + "on d.sku=p.sku\n"
                + "left join par_unidad u\n"
                + "on p.unidad=u.codigo\n"
                + "where d.rut=" + Rut + "\n"
                + "and d.tipdocto='FEC'\n"
                + "and d.nrodocto=" + Numero);
        while (Rs.next()) {
            producto =  luv.Select("select p.nombre,trim(u.unidad) as unidad, u.um from producto p \n"
                    + "LEFT JOIN par_unidad u ON u.codigo=p.unidad\n "
                    + "where p.sku = '"+Rs.getString("sku").trim()+"'");
            producto.next();
            TableModel.addRow(new Object[]{
                Rs.getString("sku"),
                producto.getString("nombre"),
                producto.getString("um"),
                fmMain.FormatoNumero(Rs.getDouble("cantidad")),
                fmMain.FormatoNumero(Rs.getDouble("valorunitario")),
                fmMain.FormatoNumero(Rs.getDouble("totallinea"))});
        }
        Sumador();
    } catch (Exception e) {
        System.out.println(e);
    } finally{
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
                dtEmision.setEditable(false);
                System.out.println("NewYear");
            } catch (Exception e) {
                System.out.println("Error New Year");
            }
            
        }
            
        
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
            Habilita(true);
            btGuardar.setEnabled(true);
            btCancelar.setEnabled(true);
            btNuevo.setEnabled(false);
            btEditar.setEnabled(false);
            btAbrir.setEnabled(false);
            fmMain.SetEstado(fmMain.pnPestanas.getSelectedIndex(),1);
            FindeAgno();
        }
        // Si cargó cliente y esta abriendo
        if(Carga && Tipo==2){
            
            jdAbrirDocumento ADoc = new jdAbrirDocumento(null, true);
                if(ADoc.ShowModal("FEC",RutMaster)==JOptionPane.YES_OPTION){
                    AbrirFactura(txRut.getText().trim(), ADoc.GetNumero());
                    SetTipo(2);
                    //VERIFICA NIVEL DE USUARIO PARA EDITAR FACTURA EXENTA 
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

            if (Tipo != 2) {
                Rs = Sql.Select("select orden\n"
                        + "from occh\n"
                        + "where estado < 2\n"
                        + "and esexento=1 \n"
                        + "and  rut=" + txRut.getText()
                        + " and codigo_oc=" + cbCodigoOc.getSelectedItem().toString());
                while (Rs.next()) {
                    cbMd.addElement(Rs.getString("orden"));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Sql.Close();
        }
    }//GEN-LAST:event_cbCodigoOcActionPerformed

    private void cbNroOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNroOrdenActionPerformed

        
        
        ExeSql  Sql = new ExeSql();
        ResultSet Rs;
        DefaultTableModel TableModel = (DefaultTableModel) Grilla.getModel();
        ExeSqlLuvaly luv = new ExeSqlLuvaly();
        ResultSet producto = null;
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
                        " and orden='" + cbNroOrden.getSelectedItem().toString().trim() + "'\n and d.separado>0";
        
        
        try {
            Rs = Sql.Select(Query);
            Sumador();
            while (Rs.next()) {
                producto =  luv.Select("select p.nombre,trim(u.unidad) as unidad, u.um from producto p \n"
                    + "LEFT JOIN par_unidad u ON u.codigo=p.unidad\n "
                    + "where p.sku = '"+Rs.getString("sku").trim()+"'");
                producto.next();
                TableModel.addRow(new Object[]{Rs.getString("sku"),
                    producto.getString("nombre"),
                    producto.getString("um"),
                    fmMain.FormatoNumero(Rs.getDouble("separado")),
                    fmMain.FormatoNumero(Rs.getDouble("valorunitario")),
                    fmMain.FormatoNumero(Rs.getDouble("totlinea"))});
                Sumador();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            Sql.Close();
            luv.Close();
        }
    }//GEN-LAST:event_cbNroOrdenActionPerformed
public String getFechaAsString() {
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
return( sdf.format( (dtEmision.getDate()).getTime() ) );
}
    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed
    if(fmMain.OkCancel("¿Guardar Documento?")== JOptionPane.OK_OPTION){
        ExeSql  Sql = new ExeSql();
        String Query="";
        String Guia;
        String NewCorrelativo = "";
        String NewCorrelativo_test = "";
        ResultSet Rs;
        
        //Graba Nueva Guia
        if(Tipo==1){
            try{
                //Obtiene Correlativo
//                Sql.ExeSql("update par_correlativo set numero=numero + 1 where tipo='FEC'");
//                NewCorrelativo = Sql.SelectUnico("select numero from par_correlativo where tipo='FEC'");
                
                        Rs = Sql.Select("select sp_getcorrelativo \n" +
                            "from sp_getcorrelativo('FEC')");   
                        Rs.next();
                        NewCorrelativo_test  = Rs.getString("sp_getcorrelativo");
                        Rs.close();
                
                        String numero = "";
                        if(JOptionPane.showConfirmDialog(null,"¿Desea ingresar n° documento?")==0){
                            numero = JOptionPane.showInputDialog(null, "Introduzca n° documento: ").toString();
                            if(Integer.valueOf(numero) <= Integer.valueOf(NewCorrelativo_test)){
                                NewCorrelativo = numero;
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "El número que ingresó puede que ya esté siendo usado\n");
                                return;
                            }
                        }
                        else {
                            if(JOptionPane.showConfirmDialog(null, "¿Usar correlativo en su lugar?")==0){
                                NewCorrelativo = NewCorrelativo_test;
                            }
                        }
            
            /*------------------------------
            Creador     : Cristian Ramirez
            Fecha       : 16/05/2017
            Objetivo    : Grabar el tipo de despacho directo o normal, se agrega en campo tipodespacho Smallint Defecto Null
                          en la tabla ctactecli.
            ------------------------------*/
            // Codigo antes del Cambio
//                Query = " INSERT INTO ctactecli(\n" +
//                        " rut, tipdocto, nrodocto, tipdocorigen, nrodocorigen, femision, \n" +
//                        " totalexento, totalafecto, totaliva, totalimpespecifico,totaldocto,codigo_oc,occh)\n" +
//                        " VALUES (" +
//                        txRut.getText() + "," +
//                        "'FEC'," + 
//                        NewCorrelativo +  "," +
//                        "'OCC'," +
//                        cbCodigoOc.getSelectedItem().toString().trim() + ",'" +
//                        getFechaAsString() + "'," +
//                        fmMain.SetGuardar(txExento.getText().trim()) + "," +
//                        "0," +
//                        "0," +
//                        "0," +
//                        fmMain.SetGuardar(txTotal.getText().trim()) + "," +
//                        cbCodigoOc.getSelectedItem().toString() + ",'" +
//                        cbNroOrden.getSelectedItem().toString().trim() + "')";
               if(!NewCorrelativo.equals("")) {
                    Query = " INSERT INTO ctactecli(\n" +
                        " rut, tipdocto, nrodocto, tipdocorigen, nrodocorigen, femision, \n" +
                        " totalexento, totalafecto, totaliva, totalimpespecifico,totaldocto,codigo_oc,occh, tipodespacho, es_refacturacion)\n" +
                        " VALUES (" +
                        txRut.getText() + "," +
                        "'FEC'," + 
                        NewCorrelativo +  "," +
                        "'OCC'," +
                        cbCodigoOc.getSelectedItem().toString().trim() + ",'" +
                        getFechaAsString() + "'," +
                        fmMain.SetGuardar(txExento.getText().trim()) + "," +
                        "0," +
                        "0," +
                        "0," +
                        fmMain.SetGuardar(txTotal.getText().trim()) + "," +
                        cbCodigoOc.getSelectedItem().toString() + ",'" +
                        cbNroOrden.getSelectedItem().toString().trim() + "'," +
                        cbTipoDespacho.getSelectedIndex();
                    
                    
                    if (chk_Refacturacion.isSelected())
                    {
                        Query = Query + ", true )";
                    }
                    else
                    {   
                        Query = Query + ", false )";
                    }    
                
                Sql.ExeSql(Query);
                

            
            
                    
                for(int i=0; i< Grilla.getRowCount(); i++){
                    Query = " INSERT INTO ctacteclidet(\n" +
                            " rut, tipdocto, nrodocto, sku, cantidad, valorunitario, totallinea)\n" + 
                            " VALUES (" +
                             txRut.getText() +"," +
                            "'FEC'," + 
                            NewCorrelativo + ",'" +
                            Grilla.getModel().getValueAt(i,GetCol("Sku")).toString() + "'," +
                            fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("Cantidad")).toString()) + "," +
                            fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("Unitario")).toString()) + "," +
                            fmMain.SetGuardar(Grilla.getModel().getValueAt(i,GetCol("Total")).toString()) + ")";
                    Sql.ExeSql(Query);
                }        
            Sql.Commit();
            JOptionPane.showMessageDialog(null,"Guardado");
            lbFolio.setText(NewCorrelativo);
            SetTipo(2);
            /*--------------------------------------------------------------------------------------------
            btEmitir.doClick();  // Se comenta esta instrucción.
            Creador : Cristian Ramírez
            Fecha   : 25/07/2017
            Objetivo:   Cambia el doclick del boton Emitir por la funcion Emite_Fac_Exenta()
                        ya que esta no funciona correctamente.
            ------------------------------------------------------------------------------------------------------*/
            Emite_Fac_Exenta();
            }
            } catch (Exception e) {
                Sql.Rollback();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            } finally{
                Sql.Close();
            }
            
        }
        else{
            try{
                 //------------------------------
            //Objetivo: grabar el tipo de despacho directo o normal, se agrega en campo tipodespacho Smallint Defecto Null
            // en la tabla ctactecli.
            // Autor: Cristian Ramirez
            // Fecha: 16/05/2017
            //------------------------------
            // Codigo antes del Cambio
//            Query = "UPDATE ctactecli SET " +
//                    "tipdocorigen='OCC', " +
//                    "nrodocorigen=" + cbNroOrden.getSelectedItem().toString().trim() + ","+
//                    "occh = " + cbNroOrden.getSelectedItem().toString().trim() + "," +
//                    "femision='"+ getFechaAsString() + "'," + 
//                    "totalexento=" + txExento.getText().trim() + "," +
//                    "totaldocto=" + txTotal.getText().trim() +" " +
//                    "WHERE tipdocto='FEC' " + 
//                    "AND   nrodocto=" +  lbFolio.getText().trim() + " \n" +
//                    "AND   rut=" + txRut.getText();
//            
 //------------------------------DESPUES DEL CAMBIO------------------------------------------------            
              Query = "UPDATE ctactecli SET " +
                    "tipdocorigen='OCC', " +
                    "nrodocorigen=" + cbNroOrden.getSelectedItem().toString().trim() + ","+
                    "occh = " + cbNroOrden.getSelectedItem().toString().trim() + "," +
                    "femision='"+ getFechaAsString() + "'," + 
                    "totalexento=" + txExento.getText().trim() + "," +
                    "tipodespacho=" + cbTipoDespacho.getSelectedIndex() + "," +
                    "totaldocto=" + txTotal.getText().trim() +",";
                   if (chk_Refacturacion.isSelected())
                    {
                        Query = Query + "es_refacturacion = true ";
                    }
                    else
                    {   
                        Query = Query + "es_refacturacion = false ";
                    }    
                   
                    Query = Query + " WHERE tipdocto='FEC' " + 
                    "AND   nrodocto=" +  lbFolio.getText().trim() + " \n" +
                    "AND   rut=" + txRut.getText();
//------------------------------DESPUES DEL CAMBIO------------------------------------------------                        
            
            Sql.ExeSql(Query);
            System.out.println("Guarda Encabezado");

            Query = "DELETE FROM ctacteclidet "+
                    "WHERE tipdocto='FEC' " + 
                    "AND   nrodocto=" +  lbFolio.getText().trim() + " \n" +
                    "AND   rut=" + txRut.getText();
            Sql.ExeSql(Query);
            
            for(int i=0; i< Grilla.getRowCount(); i++){
                Query = " INSERT INTO ctacteclidet(\n" +
                        " rut, tipdocto, nrodocto, sku, cantidad, valorunitario, totallinea)\n" + 
                        " VALUES (" +
                         txRut.getText() +"," +
                        "'FEC'," + 
                        lbFolio.getText().trim() + ",'" +
                        Grilla.getModel().getValueAt(i,0).toString() + "'," +
                        Grilla.getModel().getValueAt(i,3).toString() + "," +
                        Grilla.getModel().getValueAt(i,4).toString() + "," +
                        Grilla.getModel().getValueAt(i,5).toString() + ")";
                
                
                Sql.ExeSql(Query);
                        
                
            }
            Sql.Commit();
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
    float Neto=0;
    float Exento=0;
    float Iva=0;
    float Total=0;
    String Valor;
    
    for(int i=0; i< Grilla.getRowCount(); i++){
        Valor = Grilla.getModel().getValueAt(i,GetCol("Total")).toString();
        Valor = Valor.replace(fmMain.GetMiles(), "");
        Exento =  Float.parseFloat(Valor) + Exento;
    }
//    Iva = (Neto * Float.parseFloat("1.19"))- Neto;
    Total = Exento;
    
    
    txExento.setText(fmMain.FormatoTotal(Exento));
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
                Grilla.setValueAt(jdNumero.GetNumero(), Grilla.getSelectedRow(),3);
                Grilla.setValueAt(Float.parseFloat(Grilla.getModel().getValueAt(Grilla.getSelectedRow(),3).toString()) * Float.parseFloat(fmMain.SetGuardar(Grilla.getModel().getValueAt(Grilla.getSelectedRow(),4).toString())), Grilla.getSelectedRow(),5);
                Sumador();
            }
        }
        
    }//GEN-LAST:event_GrillaMouseClicked

    
    private void Emite_Fac_Exenta(){
            ExeSql Sql = new ExeSql();
        
        try {
            
            Sql.ExeSql("update ctactecli set estado=1 where tipdocto='FEC' and nrodocto=" + lbFolio.getText().trim());
            Sql.Commit();
            //GeneraDTE.Generar(txRut.getText().trim(),"FEC" ,lbFolio.getText().trim());
//            GeneraDTE_FTP.Generar(txRut.getText().trim(),"FEC" ,lbFolio.getText().trim());
            JOptionPane.showMessageDialog(null,"Documento Emitido");
            btEmitir.setEnabled(false);
            txEstado.setText("Emitido");
            btEditar.setEnabled(false);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    
    
    
    
    private void btEmitirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEmitirActionPerformed
//        Emite_Fac_Exenta();
//        ExeSql Sql = new ExeSql();
//        
//        try {
//            
//            Sql.ExeSql("update ctactecli set estado=1 where tipdocto='FEC' and nrodocto=" + lbFolio.getText().trim());
//            Sql.Commit();
//            //GeneraDTE.Generar(txRut.getText().trim(),"FEC" ,lbFolio.getText().trim());
//            GeneraDTE_FTP.Generar(txRut.getText().trim(),"FEC" ,lbFolio.getText().trim());
//            JOptionPane.showMessageDialog(null,"Documento Emitido");
//            btEmitir.setEnabled(false);
//            txEstado.setText("Emitido");
//            btEditar.setEnabled(false);
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//        }
        
       
       
    }//GEN-LAST:event_btEmitirActionPerformed

    private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btEditarActionPerformed

    private void btEmiteManActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEmiteManActionPerformed
        // TODO add your handling code here:
//        Emite_Fac_Exenta();
//          ExeSql Sql = new ExeSql();
//        
//        try {
//            
//            Sql.ExeSql("update ctactecli set estado=1 where tipdocto='FEC' and nrodocto=" + lbFolio.getText().trim());
//            Sql.Commit();
//            //GeneraDTE.Generar(txRut.getText().trim(),"FEC" ,lbFolio.getText().trim());
//            GeneraDTE_FTP.Generar(txRut.getText().trim(),"FEC" ,lbFolio.getText().trim());
//            JOptionPane.showMessageDialog(null,"Documento Emitido");
//            btEmitir.setEnabled(false);
//            txEstado.setText("Emitido");
//            btEditar.setEnabled(false);
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//        }
    }//GEN-LAST:event_btEmiteManActionPerformed
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Grilla;
    private javax.swing.JButton btAbrir;
    private javax.swing.JButton btAgregar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btEditar;
    private javax.swing.JButton btEliminar;
    private javax.swing.JButton btEmiteMan;
    private javax.swing.JButton btEmitir;
    private javax.swing.JButton btGuardar;
    private javax.swing.JButton btIr;
    private javax.swing.JButton btNuevo;
    private javax.swing.JComboBox cbCodigoOc;
    private javax.swing.JComboBox cbNroOrden;
    private javax.swing.JComboBox cbTipoDespacho;
    private javax.swing.JCheckBox chk_Refacturacion;
    private org.jdesktop.swingx.JXDatePicker dtEmision;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private javax.swing.JLabel lbFolio;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JTextField txCiudad;
    private javax.swing.JTextField txComuna;
    private javax.swing.JTextField txDireccion;
    private javax.swing.JTextField txDv;
    private javax.swing.JTextField txEstado;
    private javax.swing.JTextField txExento;
    private javax.swing.JTextField txNombre;
    private javax.swing.JTextField txRut;
    private javax.swing.JTextField txTotal;
    // End of variables declaration//GEN-END:variables
}
