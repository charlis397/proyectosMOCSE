
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

// Variables a Usar para la conexion 
public class altaCaja extends javax.swing.JFrame {

    Connection con=null;
    Statement stmt=null;
    String url = "jdbc:mysql://localhost:3306/ticket3";
    String usuario = "root";
    String contraseña = "5515";  
//Constructor. Agregamos la consulta del comboBox a la base de datos
    public altaCaja() {
        initComponents();
        ConsultaSucursal();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        agregar = new javax.swing.JButton();
        salir = new javax.swing.JButton();
        sucursal = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        noCaja = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Arial Unicode MS", 2, 12)); // NOI18N
        jLabel2.setText("Selecciona la sucursal donde se quiere dar de alta una caja");

        jLabel4.setFont(new java.awt.Font("Arial Unicode MS", 1, 12)); // NOI18N
        jLabel4.setText("Sucursal");

        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        sucursal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sucursalItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel1.setText("Número de Caja");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(noCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel2)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(sucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(noCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Consulta de la base para obtener la Sucursal
    
        private void ConsultaSucursal(){
            try{
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,usuario,contraseña);
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombreSucursal FROM sucursal GROUP BY nombreSucursal ORDER BY idSucursal");
            sucursal.removeAllItems();
            while(rs.next()){
                sucursal.addItem(rs.getString(1));
            }
        }
        catch (ClassNotFoundException ex) {
           Logger.getLogger(altaSucursal.class.getName()).log(Level.SEVERE, null, ex);
       }
        catch (SQLException ex) {
           Logger.getLogger(altaSucursal.class.getName()).log(Level.SEVERE, null, ex);
       }

    }
   
        
    //Agregamos la caja a la BD
        
 
    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        String noCajaVar;
        int idCajaVar;
        idCajaVar=(sucursal.getSelectedIndex())+1;
        noCajaVar=noCaja.getText();
            try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(url,usuario,contraseña);
                if ( con != null )
                System.out.println("Se ha establecido una conexión a la base de datos " +
                    "\n " + url );
                //'" + 0 + "',
                stmt = con.createStatement();
                stmt.executeUpdate("INSERT INTO caja VALUES(NULL,'"+idCajaVar+"','"+noCajaVar+"')");
                System.out.println("Los valores han sido agregados a la base de datos ");
            } catch (InstantiationException ex) {
                Logger.getLogger(altaCaja.class.getName()).log(Level.SEVERE, null, ex);  
            } catch (IllegalAccessException ex) {
                Logger.getLogger(altaCaja.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(altaCaja.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(altaCaja.class.getName()).log(Level.SEVERE, null, ex);
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
    //javax.swing.JOptionPane.showMessageDialog(this,"Registro exitoso! \n","AVISO!",javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_agregarActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        menu abrir = new menu();
        abrir.setVisible(true);
        this.setVisible(false);  
    }//GEN-LAST:event_salirActionPerformed

    private void sucursalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sucursalItemStateChanged
            String nombreSucursal;
            String resultado =null;
            nombreSucursal=sucursal.getSelectedItem().toString();
            try{
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,usuario,contraseña);
            CallableStatement proc=con.prepareCall("call cajas(?,?)");
            proc.setString(1,nombreSucursal);
            proc.setString(2,"@cajaDisponible");
            proc.registerOutParameter("cajaDisponible", Types.INTEGER);
            proc.execute();
            resultado = proc.getString("cajaDisponible");
            //System.out.println(resultado);
            noCaja.setText(String.valueOf(Integer.parseInt(resultado)+1));
        }
           catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(altaSucursal.class.getName()).log(Level.SEVERE, null, ex);
       }
                   catch (Exception e) {                  
           System.out.println(e);
       }
    }//GEN-LAST:event_sucursalItemStateChanged

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
            java.util.logging.Logger.getLogger(altaCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(altaCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(altaCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(altaCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new altaCaja().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField noCaja;
    private javax.swing.JButton salir;
    private javax.swing.JComboBox<String> sucursal;
    // End of variables declaration//GEN-END:variables
}
