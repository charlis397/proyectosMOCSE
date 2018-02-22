/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author charlis
 */
public class CrearTabla extends DefaultTableModel {
    
        boolean[] canEdit = new boolean [] {
                false, false, false, false,false,false
            };
    
    
    
    public CrearTabla(Object[][] datos,String[] columnas)
    {
        super(datos,columnas);
    }
//    @Override
//    public Class getColumnClass(int columnIndex) {
//                return types [columnIndex];
//            }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
          return canEdit [columnIndex];
    }
    
}
