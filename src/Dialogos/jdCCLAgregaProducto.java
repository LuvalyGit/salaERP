package Dialogos;

import Conexion.ExeSql;
import Conexion.ExeSqlLuvaly;
import Formularios.fmMain;
import Utilidades.ComboCodigos;
import Utilidades.Combo_CodStr;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;


public class jdCCLAgregaProducto extends javax.swing.JDialog {
    private boolean Retorno;
     public static boolean es_cotiza =true;
    int id_uni;
   
    public jdCCLAgregaProducto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cbDescuento.setVisible(false);
        CargaUnidad();
        cbUnidades.setVisible(false);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txCodigo = new javax.swing.JTextField();
        btIr = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txNombre = new javax.swing.JTextField();
        txUnidad = new javax.swing.JTextField();
        txCantidad = new javax.swing.JTextField();
        btAceptar = new javax.swing.JButton();
        txPrecio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txUM = new javax.swing.JTextField();
        btCancelar = new javax.swing.JButton();
        cbDescuento = new javax.swing.JComboBox();
        chk_nvocod = new javax.swing.JCheckBox();
        cbUnidades = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listProveedor = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Código");

        txCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txCodigoKeyPressed(evt);
            }
        });

        btIr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos22/Go.png"))); // NOI18N
        btIr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIrActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre");

        jLabel3.setText("Unidad");

        jLabel4.setText("Cantidad");

        txCantidad.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txCantidadKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txCantidadKeyTyped(evt);
            }
        });

        btAceptar.setText("Aceptar");
        btAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAceptarActionPerformed(evt);
            }
        });

        txPrecio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txPrecioActionPerformed(evt);
            }
        });
        txPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txPrecioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txPrecioKeyTyped(evt);
            }
        });

        jLabel5.setText("Precio");

        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        cbDescuento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3" }));

        chk_nvocod.setText("Codigo Nuevo");
        chk_nvocod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_nvocodActionPerformed(evt);
            }
        });

        cbUnidades.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));
        cbUnidades.setToolTipText("");
        cbUnidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbUnidadesActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Código proveedor a enviar"));

        jScrollPane1.setViewportView(listProveedor);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setText("(Para no enviar código seleccione 0)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(193, 193, 193)
                                .addComponent(cbDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btIr, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(chk_nvocod, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txUM, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btAceptar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btIr)
                            .addComponent(txCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(chk_nvocod))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txUM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42))
                                    .addComponent(cbUnidades, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel3)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(17, 17, 17)
                .addComponent(cbDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCancelar)
                    .addComponent(btAceptar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCodigoKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            btIr.doClick();
        }
    }//GEN-LAST:event_txCodigoKeyPressed
    public void SetProducto(String Codigo,String Cantidad,String Valor,boolean muestra_codNuevo){
        txCodigo.setText(Codigo);
        btIr.doClick();
        txCodigo.setEditable(false);
        btIr.setEnabled(false);
        chk_nvocod.setEnabled( muestra_codNuevo);
        txCantidad.setText(String.valueOf(Cantidad));
        txPrecio.setText(String.valueOf(Valor));
        
        txCantidad.requestFocus();
    }

    public void SelectIDCH(String code) {
        int selected = 0;
        for(int i = 0;i< listProveedor.getModel().getSize(); i++){
            if(code.equals(listProveedor.getModel().getElementAt(i).toString().trim())){
                selected = i;
                System.out.println(listProveedor.getModel().getElementAt(i).toString().trim());
                
            }
        }
        System.out.println("Selected: " +selected);
        listProveedor.setSelectedIndex(selected);
    }
    
        public void CargaUnidad(){
        DefaultComboBoxModel value;
        ExeSqlLuvaly Sql = new ExeSqlLuvaly();
        ResultSet Rs;
        String Query="";
        cbUnidades.removeAllItems();
        
//        CboProveedor.addItem("<Todos>");  
//        CboProveedor.setSelectedIndex(0);
        value =new DefaultComboBoxModel();
        try {
            Query=" select codigo, unidad,um from par_unidad order by unidad;";
            Rs = Sql.Select(Query);
            if(Sql.GetRowCount()==0) return;
           value.addElement(new Combo_CodStr("Seleccione","",0));     
           while(Rs.next()){
            value.addElement(new Combo_CodStr(Rs.getString("unidad").trim(),Rs.getString("um").trim(),Rs.getInt("codigo")));
            System.out.println(Rs.getString("unidad").trim());
           } 
           cbUnidades.setModel(value);
           cbUnidades.setSelectedIndex(0);

        } catch (Exception e) {
            System.out.println(e);
        }
        finally{
            Sql.Close();
        }
    }
    
    
    
    
    
    
//-----------------------------------------------------------------------------
//  BOTON IR
//-----------------------------------------------------------------------------
    private void btIrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIrActionPerformed
            if(!txCodigo.getText().isEmpty())
                CargaProductos(txCodigo.getText());
            else{
            jdBuscarProductos BP = new jdBuscarProductos(null, true);
            BP.SetCotiza(true);    
            BP.setLocationRelativeTo(null);
            BP.setTitle("Buscar Producto Cotiza");
            

            
            BP.setVisible(true);
     
            if(!BP.GetCodigo().isEmpty()){
                CargaProductos(BP.GetCodigo());
                }
            }
            
    }//GEN-LAST:event_btIrActionPerformed
//-----------------------------------------------------------------------------
//  BOTON ACEPTAR
//-----------------------------------------------------------------------------
    private void btAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAceptarActionPerformed
     String Query ="";   
     ExeSql Sql = new ExeSql();
     ResultSet Rs;
     
     try{
        String Cod_uni ="";   
        int codInt =0;
        if(txCantidad.getText().isEmpty() || txPrecio.getText().isEmpty() || txCodigo.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Faltan Datos");
            }
        else{
            txCantidad.setText(fmMain.SetGuardar(txCantidad.getText()));
            txPrecio.setText(fmMain.SetGuardar(txPrecio.getText()));
            if (chk_nvocod.isSelected()){
                // Inserta producto en la tabla de productos
                
                  if (cbUnidades.getSelectedIndex()>=0){
                        Combo_CodStr id = (Combo_CodStr) cbUnidades.getSelectedItem();
                        Cod_uni = id.getId();
                        String nombre = cbUnidades.getSelectedItem().toString();
                         txUM.setText(Cod_uni);
                         txUnidad.setText(nombre);
                         codInt = id.getCodId();
                        
                        //Combo.hidePopup();
                   } 
                  else{
                      fmMain.Mensaje("Debe seleccionar una unidad de medida");
                  }
                
                 Query = " insert into producto (sku,unidad,nombre,imptoiva,es_cotiza)" +
                 " values (" + txCodigo.getText().trim() + "," + codInt + ",'" + txNombre.getText().trim() + "',1,1)";
                Sql.ExeSql(Query);
                Sql.Commit();
                fmMain.Mensaje("Se ha grabado el nuevo producto Cotizado en la base de datos");
            }
            Retorno = true;
            dispose();
        }
          } catch (Exception e) {
            System.out.println(e);
        }
        finally{
            Sql.Close();
        }    
        
    }//GEN-LAST:event_btAceptarActionPerformed

    private void txCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCantidadKeyTyped
      
        char c=evt.getKeyChar();   
        if(c ==',' || c=='.'){
            evt.consume();
            if(!txCantidad.getText().contains(fmMain.GetDecimal()))
                txCantidad.setText(txCantidad.getText() + fmMain.GetDecimal() );
        }
        
         if(Character.isLetter(c)) { 
              getToolkit().beep(); 
              evt.consume(); 
              System.out.println("Ingresa Solo Letras -->" + c ); 
         }  
  
      
    }//GEN-LAST:event_txCantidadKeyTyped

    private void txCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCantidadKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            txPrecio.requestFocus();
        }
    }//GEN-LAST:event_txCantidadKeyPressed

    private void txPrecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txPrecioKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                btAceptar.doClick();
        }
            
    }//GEN-LAST:event_txPrecioKeyPressed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        Retorno= false;
        dispose();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Retorno=false;
    }//GEN-LAST:event_formWindowOpened

    private void txPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txPrecioKeyTyped
        char c=evt.getKeyChar();  
        if(evt.getKeyChar()==',' || evt.getKeyChar()=='.'){
            evt.consume();
            if(!txPrecio.getText().contains(fmMain.GetDecimal()))
                txPrecio.setText(txPrecio.getText() + fmMain.GetDecimal() );
        }
               if(Character.isLetter(c)) { 
              getToolkit().beep(); 
              evt.consume(); 
              System.out.println("Ingresa Solo Letras -->" + c ); 
         }  
    }//GEN-LAST:event_txPrecioKeyTyped

    private void txPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txPrecioActionPerformed

    private void cbUnidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbUnidadesActionPerformed
        // TODO add your handling code here:
        
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        String Query = "";
        
        try{
            
        
        ComboCodigos uni = (ComboCodigos) cbUnidades.getSelectedItem() ;
        if (cbUnidades.getSelectedItem()!= null){
            id_uni = uni.getId();
            
     
//            Query = "select unidad, um from par_unidad "
//                    + "where codigo = "+ id_uni + " order by unidad";
//            
//            Rs = Sql.Select(Query);
//            
//             if (Sql.GetRowCount()==0) return;
//            
//                Rs.next();
            
            
            System.out.println("id del documenro: " +  id_uni);
            
        }
         } catch (Exception e) {
            System.out.println(e);
        }
        finally{
            Sql.Close();
        }      
    }//GEN-LAST:event_cbUnidadesActionPerformed

    private void chk_nvocodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_nvocodActionPerformed
        // TODO add your handling code here:
        ExeSql Sql = new ExeSql();
        ResultSet Rs;
        String Query = "";
        int num=0;
     try
     {
        
         Query=" select  max(CAST(sku as Integer))  correlativo  from producto\n" +
                    " where es_cotiza=1";
        
        Rs = Sql.Select(Query);
        
         if(Sql.GetRowCount()==0) 
         {
             num=1;
         }    
         else
             {
              Rs.next();
                int maximo = Rs.getInt("correlativo");
                num = Integer.valueOf(maximo)+1;       
              }
             
             
             
        
        
        
        
        
        if (chk_nvocod.isSelected())
        {
            txUM.setVisible(false);
            txUnidad.setVisible(false);
            cbUnidades.setVisible(true);
            txCodigo.setText(String.valueOf(num));
            txNombre.setText("");
            cbUnidades.setSelectedIndex(cbUnidades.getItemCount()-1);
            txCodigo.setEnabled(false);
            btIr.setEnabled(false);
           
        }
         else 
            {
            txUM.setVisible(true);
            txUnidad.setVisible(true);
            cbUnidades.setVisible(false);
            txCodigo.setText("");
            txCodigo.requestFocus();
            txCodigo.setEnabled(true);
            btIr.setEnabled(true);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        finally{
            Sql.Close();
        }      
        
    }//GEN-LAST:event_chk_nvocodActionPerformed

    public String GetSku(){
        return txCodigo.getText().trim();
    }
//-----------------------------------------------------------------------------
//  RETORNA FILA
//-----------------------------------------------------------------------------    
    public Object[] GetFila(){
        double TotLinea =  Float.parseFloat(txCantidad.getText()) * Float.parseFloat(txPrecio.getText());
        if(Retorno)
            return new Object[]{txCodigo.getText(),
                                txNombre.getText(),
                                txUM.getText(),
                                fmMain.FormatoNumero(Double.valueOf(txCantidad.getText())),
                                0,
                                fmMain.FormatoNumero(Double.valueOf(txCantidad.getText())),
                                fmMain.FormatoNumero(Double.valueOf(txPrecio.getText())),
                                cbDescuento.getSelectedIndex(),
                                Double.valueOf(txPrecio.getText()) * cbDescuento.getSelectedIndex() /100,
                                fmMain.FormatoNumero(TotLinea),
                                Double.valueOf(txPrecio.getText())};   
            
        else
            return null;
    }
//-----------------------------------------------------------------------------
//  RETORNA FILA
//-----------------------------------------------------------------------------    
    public Object[] GetFilaNCC(){
        double TotLinea =  Float.parseFloat(txCantidad.getText()) * Float.parseFloat(txPrecio.getText());
        if(Retorno)
            return new Object[]{txCodigo.getText(),
                                txNombre.getText(),
                                txUM.getText(),
                                fmMain.FormatoNumero(Double.valueOf(txCantidad.getText())),
                                fmMain.FormatoNumero(Double.valueOf(txPrecio.getText())),
                                fmMain.FormatoNumero(TotLinea),
                                Double.valueOf(txPrecio.getText())};   
            
        else
            return null;
    }    
 //-----------------------------------------------------------------------------
//  RETORNA FILA OCP
//-----------------------------------------------------------------------------    
    public Object[] GetFilaOCP(){
        double TotLinea =  Float.parseFloat(txCantidad.getText()) * Float.parseFloat(txPrecio.getText());
        if(Retorno)
            return new Object[]{txCodigo.getText(),
                                txNombre.getText(),
                                txUM.getText(),
                                fmMain.FormatoNumero(Double.valueOf(fmMain.SetGuardar(txCantidad.getText()))),
                                fmMain.FormatoNumero(Double.valueOf(fmMain.SetGuardar(txPrecio.getText()))),
                                fmMain.FormatoNumeroSinDecimal(TotLinea),
                                Double.valueOf(fmMain.SetGuardar(txPrecio.getText())),
                                Double.valueOf(fmMain.SetGuardar(txCantidad.getText()))};  
                                
            
        else
            return null;
    }
    public Object[] GetFilaCCP(){
        String idch = "0";
        if(listProveedor.getSelectedIndices().length>0){
            System.out.println("ha seleccionado uno");
            idch = listProveedor.getSelectedValue();
        }
        else {
            idch = "0";
        }
        double TotLinea =  Float.parseFloat(txCantidad.getText()) * Float.parseFloat(txPrecio.getText());
        if(Retorno){
            return new Object[]{txCodigo.getText(),
                                txNombre.getText(),
                                txUM.getText(),
                                fmMain.FormatoNumero(Double.valueOf(fmMain.SetGuardar(txCantidad.getText()))),
                                fmMain.FormatoNumero(Double.valueOf(fmMain.SetGuardar(txPrecio.getText()))),
                                fmMain.FormatoNumeroSinDecimal(TotLinea),
                                Double.valueOf(fmMain.SetGuardar(txPrecio.getText())),
                                Double.valueOf(fmMain.SetGuardar(txCantidad.getText())),idch};  
        }           
            
        else
            return null;
    }
//-----------------------------------------------------------------------------
//  CARGA PRODUCTOS
//-----------------------------------------------------------------------------    
    private void CargaProductos(String Codigo){
        ExeSqlLuvaly luv = new ExeSqlLuvaly();
        ResultSet Rs;
        try{
            Rs = luv.Select("select p.discontinuado, p.sku,p.nombre,u.unidad,u.um \n" +
                            "from producto p\n" +
                            "left join par_unidad u\n" +
                            "on u.codigo=p.unidad\n" +
                            "where (p.sku='" + Codigo + "' or p.sku = (select sku from codchile where idch = '"+Codigo+"')"
                                    + " or p.sku in (select sku from codbar where codbar = '"+Codigo+"'))");
            
            if (luv.GetRowCount()==0) {
                
                fmMain.Mensaje("El producto Código = " + Codigo + "\n No Existe en nuestra Base de datos.");
                return;
            }
        
            Rs.next();

            txCodigo.setText(Rs.getString("sku"));
            txNombre.setText(Rs.getString("nombre"));
            txUnidad.setText(Rs.getString("unidad"));
            txUM.setText(Rs.getString("um"));
            txCantidad.requestFocus();
            if (Rs.getBoolean("discontinuado")==true)
            {
                fmMain.disc=1;
            }
            else
            {
               fmMain.disc=0;
            }
            String sql = "select idch from codchile where sku = '"+Rs.getString("sku")+"'";
            Rs = luv.Select(sql);
            Rs.beforeFirst();
            DefaultListModel model= new DefaultListModel();
            for(int i = 0; Rs.next(); i++){
                model.addElement(Rs.getString("idch"));
            }
            model.addElement("0");
            listProveedor.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            }finally{
            luv.Close();

        }
        
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(jdCCLAgregaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jdCCLAgregaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jdCCLAgregaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jdCCLAgregaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jdCCLAgregaProducto dialog = new jdCCLAgregaProducto(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAceptar;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btIr;
    private javax.swing.JComboBox cbDescuento;
    private javax.swing.JComboBox cbUnidades;
    private javax.swing.JCheckBox chk_nvocod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listProveedor;
    private javax.swing.JTextField txCantidad;
    private javax.swing.JTextField txCodigo;
    private javax.swing.JTextField txNombre;
    private javax.swing.JTextField txPrecio;
    private javax.swing.JTextField txUM;
    private javax.swing.JTextField txUnidad;
    // End of variables declaration//GEN-END:variables
}
