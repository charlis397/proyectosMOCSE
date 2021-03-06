
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Timestamp.from;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Carlos
 */
public class altaProducto extends javax.swing.JFrame {

    Connection con=null;
    Statement stmt=null;
    String url = "jdbc:mysql://localhost:3306/ticket3";  
    String usuario = "root";
    String contraseña = "5515"; 
    public altaProducto() {
        initComponents();
        ConsultaCategoria();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nombreProducto = new javax.swing.JTextField();
        precio = new javax.swing.JTextField();
        agregar = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        categoria = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(384, 300));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial Unicode MS", 2, 12)); // NOI18N
        jLabel2.setText("Ingresa los datos, para dar de alta un producto");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 260, -1));

        jLabel4.setFont(new java.awt.Font("Arial Unicode MS", 1, 12)); // NOI18N
        jLabel4.setText("Categoria");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial Unicode MS", 1, 12)); // NOI18N
        jLabel5.setText("Nombre");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        nombreProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreProductoKeyTyped(evt);
            }
        });
        getContentPane().add(nombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 210, -1));

        precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                precioKeyTyped(evt);
            }
        });
        getContentPane().add(precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 130, -1));

        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });
        getContentPane().add(agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 90, 30));

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        getContentPane().add(salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, -1, -1));

        limpiar.setText("Limpiar");
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });
        getContentPane().add(limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, -1, 30));

        categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaActionPerformed(evt);
            }
        });
        getContentPane().add(categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 180, -1));

        jLabel6.setFont(new java.awt.Font("Arial Unicode MS", 1, 12)); // NOI18N
        jLabel6.setText("Precio");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nombreProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreProductoKeyTyped
        char c = evt.getKeyChar();
        if((c<'a' || c>'z') && (c<'A' || c>'Z') && (c<' ' || c>' ')&& (c<'0'||c>'9')&&(c<'.'||c>'.')) evt.consume();
    }//GEN-LAST:event_nombreProductoKeyTyped

    private void precioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precioKeyTyped
        char c = evt.getKeyChar();
        if((c<'0'||c>'9')&&(c<'.'||c>'.')) evt.consume();
    }//GEN-LAST:event_precioKeyTyped

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        String nombreProductoVar,precioVar,CategoriaVar;
                int idCategoria;

        nombreProductoVar=nombreProducto.getText();
        precioVar=precio.getText();
        CategoriaVar= categoria.getSelectedItem().toString();
        idCategoria=(categoria.getSelectedIndex())+1;

        if(nombreProducto.getText().equals("")||
            precio.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this,"Existen Campos Vacios \n","AVISO",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            nombreProducto.requestFocus();
        }

        else{
            try{ 
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(url,usuario,contraseña);
                if ( con != null )
                System.out.println("Se ha establecido una conexión a la base de datos " +
                    "\n " + url );
                //'" + 0 + "',
                stmt = con.createStatement();
                stmt.executeUpdate("INSERT INTO producto VALUES(NULL,'"+nombreProductoVar+"','"+precioVar+"','"+idCategoria+"')");
                System.out.println("Los valores han sido agregados a la base de datos ");
            } catch (InstantiationException ex) {
                Logger.getLogger(altaProducto.class.getName()).log(Level.SEVERE, null, ex);  //
            } catch (IllegalAccessException ex) {
                Logger.getLogger(altaProducto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(altaProducto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(altaProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                if (con != null) {
                    try {
                        con.close();
                        stmt.close();
                    } catch ( Exception e ) {
                        System.out.println( e.getMessage());
                    }
                }
            }
            javax.swing.JOptionPane.showMessageDialog(this,"Registro exitoso! \n","AVISO",javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
        this.nombreProducto.setText("");
        this.precio.setText("");
    }//GEN-LAST:event_agregarActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed

        nombreProducto.setText("");
        precio.setText("");
    }//GEN-LAST:event_limpiarActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
         menu abrir = new menu();
        abrir.setVisible(true);
        this.setVisible(false);          // TODO add your handling code here:
    }//GEN-LAST:event_salirActionPerformed

    private void categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoriaActionPerformed

        private void ConsultaCategoria(){
            try{
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,usuario,contraseña);
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombreCategoria FROM categoria ORDER BY idCategoria");
            categoria.removeAllItems();
            while(rs.next()){
                categoria.addItem(rs.getString(1));
                
            }
        }
        catch (ClassNotFoundException ex) {
           Logger.getLogger(altaSucursal.class.getName()).log(Level.SEVERE, null, ex);
       }
        catch (SQLException ex) {
           Logger.getLogger(altaSucursal.class.getName()).log(Level.SEVERE, null, ex);
       }

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(altaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(altaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(altaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(altaProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new altaProducto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JComboBox<String> categoria;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton limpiar;
    private javax.swing.JTextField nombreProducto;
    private javax.swing.JTextField precio;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
