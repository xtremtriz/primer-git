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
public class AutoDP {
    String marca, modelo, placas, color;
    int poliza, año;
    public AutoDP() {
        
        this.poliza= 0;
        this.marca = "";
        this.modelo = "";  
        this.año = 0;
        this.placas = "";
        this.color = "";
    }
    public AutoDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos,"_");
		
                this.poliza =Integer.parseInt(st.nextToken());
		this.marca       = st.nextToken();
		this.modelo      = st.nextToken();
                this.año     =Integer.parseInt(st.nextToken());
		this.placas    = st.nextToken();
                this.color     = st.nextToken();
	}

    public int getPoliza() {
        return poliza;
    }
 
    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }   
    
    public int getAño() {
        return año;
    }

    public String getPlacas() {
        return placas;
    }

    public String getColor() {
        return color;
    }

    public void setPoliza(int poliza) {
        this.poliza = poliza;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public void setAño(int año) {
        this.año = año;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public void setColor(String color) {
        this.color = color;
    }
    @Override
    public String toString()
	 {
	 	return this.poliza+"_"+this.marca+"_"+this.modelo+"_"+this.año+"_"+this.placas+"_"+this.color;
	 }
    
    
    public String toStringSql()
    {
        return "'" + this.poliza + "','" + this.marca + "','" + this.modelo + "','" + this.año + "','" + this.placas + "','" + this.color + "'";
    }
   
}
