/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import bd.Alta;
import bd.Consulta;
import java.sql.ResultSet;
import vista.VistaAgregarEmpleado;

/**
 *
 * @author charlis
 */
public class Cargo {
    private String nombre;

    public Cargo(String nombre) {
        this.nombre = nombre;
    }
    
    public Cargo() {    }

    public String getNombre() {
        return nombre;
    }
    
    
    public void alta(){
        Alta alta = new Alta();
        alta.cargo(this);
    }
    
    public ResultSet consulta(VistaAgregarEmpleado vista){
        Consulta consulta = new Consulta();
        return consulta.cargos(vista);
    }
    
    
}
