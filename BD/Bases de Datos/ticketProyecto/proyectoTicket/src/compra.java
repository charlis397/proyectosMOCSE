
import java.awt.event.ItemEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
public class compra extends javax.swing.JFrame {

  

    Connection con=null;
    Statement stmt=null;
    String url = "jdbc:mysql://localhost:3306/ticket3";
    String usuario = "root";
    String contraseña = "5515"; 
    String fecha;
    
    public compra() {
        initComponents();
        ConsultaProducto();
    }

     
    @SuppressWarnings("unchecked")
    
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        producto = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        precio = new javax.swing.JTextField();
        descuento = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        agregarProducto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        compra = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        finalizarCompra = new javax.swing.JButton();
        noTicket = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        TotalPagar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        producto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                productoItemStateChanged(evt);
            }
        });
        producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel3.setText("Selecciona un producto");

        precio.setEditable(false);
        precio.setEnabled(false);

        descuento.setText("0");
        descuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descuentoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel5.setText("Descuento");

        agregarProducto.setText("Agregar");
        agregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarProductoActionPerformed(evt);
            }
        });

        compra.setColumns(20);
        compra.setFont(new java.awt.Font("Monospaced", 0, 10)); // NOI18N
        compra.setRows(5);
        compra.setText("       \t  \tWALMART\nNUEVA WAL MA   DE MEXICO S DE RL DE CV\nNEXTENGO 78 STA. CRUZ ACAYUCAN 02770\nAZCAPOTZALCO MEX DF RFC. NWM9709244W4\n");
        compra.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(compra);
        compra.getAccessibleContext().setAccessibleDescription("");

        jLabel6.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel6.setText("Precio");

        finalizarCompra.setText("Finalizar Compra");
        finalizarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarCompraActionPerformed(evt);
            }
        });

        noTicket.setEditable(false);
        noTicket.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("%");

        TotalPagar.setText("Total a Pagar");
        TotalPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalPagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(producto, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(precio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(TotalPagar))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(finalizarCompra)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(descuento, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)))
                        .addGap(48, 48, 48)
                        .addComponent(agregarProducto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(noTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(agregarProducto)
                        .addGap(18, 18, 18)
                        .addComponent(TotalPagar)
                        .addContainerGap(95, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(descuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(finalizarCompra)
                        .addGap(21, 21, 21))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noTicket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    
    private void ConsultaProducto(){
    try{
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,usuario,contraseña);
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombre,precio FROM producto ORDER BY idProducto");
            producto.removeAllItems();
            while(rs.next()){
                producto.addItem(rs.getString(1));                   
            }
            
        }
     
        catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(altaSucursal.class.getName()).log(Level.SEVERE, null, ex);
       }
}
 
    
    private void descuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descuentoActionPerformed

    private void productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productoActionPerformed
    
        
        
    }//GEN-LAST:event_productoActionPerformed

    private void agregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarProductoActionPerformed
            float descuentoAplicado,precioProducto,totalProducto,totalProductoFrame;
            int idTicket,idProducto;
            
                 
            idTicket=Integer.parseInt(noTicket.getText());
            idProducto= (producto.getSelectedIndex())+1;
            precioProducto=Float.parseFloat(precio.getText());
            descuentoAplicado=Float.parseFloat(descuento.getText());
            totalProducto=precioProducto-((precioProducto*descuentoAplicado)/100);
            compra.append((producto.getSelectedItem().toString()).toUpperCase()+"      "+"$"+totalProducto+"\n"); 
            
            
           try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(url,usuario,contraseña);
                if ( con != null )
                stmt = con.createStatement();
                stmt.executeUpdate("INSERT INTO detalle VALUES(NULL,'"+idTicket+"','"+idProducto+"','"+totalProducto+"')");
                System.out.println("Los valores han sido agregados a la base de datos ");
            } catch (InstantiationException ex) {
                Logger.getLogger(altaCaja.class.getName()).log(Level.SEVERE, null, ex);  //
            } catch (IllegalAccessException ex) {
                Logger.getLogger(altaCaja.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(altaCaja.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(altaCaja.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }//GEN-LAST:event_agregarProductoActionPerformed
    
    private void obtenerTotal(){
        
        String identificadorTicket=noTicket.getText(),resultado;
        DecimalFormat decimales = new DecimalFormat("0.00");
        modoPago abrir = new modoPago();
        this.setVisible(true);
        abrir.setVisible(true);
    try{
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,usuario,contraseña);
            CallableStatement proc=con.prepareCall("call sumaTotal(?,?)");
            proc.setString(1,identificadorTicket);
            proc.setString(2,"@sumaPorTicket");
            proc.registerOutParameter("sumaPorTicket",Types.FLOAT);
            proc.execute();
            resultado = proc.getString("sumaPorTicket");
            //System.out.println(resultado);
            compra.append("\n"+"                       TOTAL $"+decimales.format(Float.parseFloat(resultado))+"\n");
            
            modoPago.total.setText(String.valueOf(decimales.format(Float.parseFloat(resultado))));
            
        }
           catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(altaSucursal.class.getName()).log(Level.SEVERE, null, ex);
       }
                   catch (Exception e) {                  
           System.out.println(e);
       }
    }
    
    private void productoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_productoItemStateChanged
            String nombreProducto;
            String resultado =null;
            nombreProducto=producto.getSelectedItem().toString();
            try{
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,usuario,contraseña);
            CallableStatement proc=con.prepareCall("call precio(?,?)");
            proc.setString(1,nombreProducto);
            proc.setString(2,"@precioProducto");
            proc.registerOutParameter("precioProducto", Types.FLOAT);
            proc.execute();
            resultado =proc.getString("precioProducto");
            //System.out.println(resultado);
            precio.setText(resultado);
        }
           catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(altaSucursal.class.getName()).log(Level.SEVERE, null, ex);
       }
                   catch (Exception e) {                  
           System.out.println(e);
       }
    }//GEN-LAST:event_productoItemStateChanged

    private void finalizarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarCompraActionPerformed
        int idTicket;
        idTicket=Integer.parseInt(noTicket.getText());

        nuevaCompra abrir = new nuevaCompra();
        abrir.setVisible(true);
        this.setVisible(false);
        nuevaCompra.noTicket.setText(String.valueOf(idTicket+1));
    }//GEN-LAST:event_finalizarCompraActionPerformed

    private void TotalPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalPagarActionPerformed
        obtenerTotal();
    }//GEN-LAST:event_TotalPagarActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new compra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton TotalPagar;
    private javax.swing.JButton agregarProducto;
    public static javax.swing.JTextArea compra;
    public static javax.swing.JTextField descuento;
    private javax.swing.JButton finalizarCompra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField noTicket;
    private javax.swing.JTextField precio;
    private javax.swing.JComboBox<String> producto;
    // End of variables declaration//GEN-END:variables
}
