/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguros;

import java.util.StringTokenizer;

/**
 *
 * @author Uriel
 */
public class ClienteDP {
    private String nombre, direccion, edoCivil;
    private int poliza, telefono;
	//Constructores (Permite inicializar los atributos y se llama igual que la clase)
	public ClienteDP()
	{
		this.poliza       = 0;
		this.nombre        = "";
		this.direccion     = "";
		this.telefono      = 0;
		this.edoCivil = "";
	}
        
	public ClienteDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos,"_");
			
		this.poliza       = Integer.parseInt(st.nextToken());
		this.nombre        = st.nextToken();
		this.direccion     = st.nextToken();
		this.telefono      = Integer.parseInt(st.nextToken());
		this.edoCivil = st.nextToken();	
	}

    public int getNoCliente() {
        return poliza;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getEdoCivil() {
        return edoCivil;
    }

    public void setNoCliente(int noCliente) {
        this.poliza = noCliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setEdoCivil(String edoCivil) {
        this.edoCivil = edoCivil;
    }

 
    @Override
    public String toString()
	 {
	 	return this.poliza+"_"+this.nombre+"_"+this.direccion+"_"+this.telefono+"_"+this.edoCivil;
	 }
 
    public String toStringSql()
    {
        return "'" + this.poliza + "','" + this.nombre + "','" + this.direccion + "'," + this.telefono + ",'" + this.edoCivil + "'";
    }
 
}
