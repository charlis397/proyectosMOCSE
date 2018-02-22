/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import bd.Alta;
import bd.Consulta;

/**
 *
 * @author charlis
 */
public class Empleado {
    private String nombre = "";
    private String codigo;
    private Cargo cargo;

    public Empleado(String nombre, String codigo, Cargo cargo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.cargo = cargo;
    }
    
    public Empleado(String codigo) {
        this.codigo = codigo;
    }
    public Empleado() {}
    

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public Cargo getCargo() {
        return cargo;
    }
    
    
    public void alta(){
        Alta alta = new Alta();
        alta.empleado(this , cargo);
    }
    
    public boolean consultaExistencia(){
        Consulta consulta = new Consulta();
        return consulta.empleado(this);
    }
    
    
    
}
