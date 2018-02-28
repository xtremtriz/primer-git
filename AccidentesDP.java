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
public class AccidentesDP {
    String placas,fecha, tipoAccidente;
    int  cantPagar;
    public AccidentesDP() {
        this.placas = "";
        this.cantPagar = 0;
        this.fecha = "";         
        this.tipoAccidente = "";
    }
    public AccidentesDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos,"_");
			
		this.placas       = st.nextToken();
		this.cantPagar        = Integer.parseInt(st.nextToken());
		this.fecha     = st.nextToken();
		this.tipoAccidente      = st.nextToken();
	}

    public String getPlacas() {
        return placas;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTipoAccidente() {
        return tipoAccidente;
    }

    public int getCantPagar() {
        return cantPagar;
    }
 

     public void setPlacas(String placas) {
        this.placas = placas;
    }     

    public void setCantPagar(int cantPagar) {
        this.cantPagar = cantPagar;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setTipoAccidente(String tipoAccidente) {
        this.tipoAccidente = tipoAccidente;
    } 

 
    
    @Override
    public String toString()
	 {
	 	return this.placas+"_"+this.cantPagar+"_"+this.fecha+"_"+this.tipoAccidente;
	 }
    
    public String toStringSql()
    {
        return "'" + this.placas + "','" + this.cantPagar + "','" + this.fecha + "','" + this.tipoAccidente + "'";
    }
}
