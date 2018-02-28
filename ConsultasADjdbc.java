/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import seguros.AccidentesDP;
import seguros.AutoDP;
import seguros.ClienteDP;

/**
 *
 * @author Uriel
 */
public class ConsultasADjdbc {
    //ConsultasADjdbc con
    Connection conexion;
    Statement statement;

    ClienteDP clienteDP;
    AutoDP autoDP;
    AccidentesDP accidentesDP;
    public ConsultasADjdbc()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/seguro?user=root");
			System.out.println("Conexion exitosa...");
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println("Error 1: "+cnfe);// if No driver
		}
		catch(InstantiationException ie)
		{
			System.out.println("Error 2: "+ie); // for newInstance
		}
		catch(IllegalAccessException iae)
		{
			System.out.println("Error 3: "+iae); // for newInstance
		}
		catch(SQLException sqle)
		{
			System.out.println("Error 4: "+sqle); // for newInstance

		}
	}
    String consultarClientePoliza(String poliza)
    {
		String datos="";
		String query   = "";
		boolean encontrado=false;
			
		ResultSet sr=null;
			query="SELECT * FROM cliente";
		
			try{
				statement=conexion.createStatement();
				sr=statement.executeQuery(query);
				clienteDP=new ClienteDP();
				
				while(sr.next())
				{
					
					if(sr.getString(1).equals(poliza))
					{
					
                clienteDP.setNoCliente(sr.getInt(1));	
        	clienteDP.setNombre(sr.getString(2));	
        	clienteDP.setDireccion(sr.getString(3));	
       		clienteDP.setTelefono(sr.getInt(4));	
       		clienteDP.setEdoCivil(sr.getString(5));	
       			
       		datos=datos+clienteDP.toString()+"\n";
       			
       			encontrado=true;				
					}
				}
				if(!encontrado)
				{
					  datos = "No se localiza intenta otra ves " + poliza;
				}
			statement.close();
			}
	
		catch(SQLException sqle){
			System.out.println("Error: "+sqle); 
			datos = "ERROR en la consulta "+ sqle;	
		}
		return datos;
	}
    
    String consultarAutosCliente(String poliza)
    {
		String datos="";
		String query   = "";
		boolean encontrado=false;
			
		ResultSet sr=null;
			query="SELECT * FROM auto";
		
			try{
				statement=conexion.createStatement();
				sr=statement.executeQuery(query);
				autoDP=new AutoDP();
				
				while(sr.next())
				{
					
					if(sr.getString(1).equals(poliza))
					{
					
                autoDP.setPoliza(sr.getInt(1));	
        	autoDP.setMarca(sr.getString(2));	
        	autoDP.setModelo(sr.getString(3));	
       		autoDP.setAño(sr.getInt(4));	
       		autoDP.setPlacas(sr.getString(5));
                autoDP.setColor(sr.getString(6));
       			
       		datos=datos+autoDP.toString()+"\n";
       			
       			encontrado=true;				
					}
				}
				if(!encontrado)
				{
					  datos = "No se localiza intenta otra ves " + poliza;
				}
			statement.close();
			}
	
		catch(SQLException sqle){
			System.out.println("Error: "+sqle); 
			datos = "ERROR en la consulta "+ sqle;	
		}
		return datos;
	}

    String ConsultarNombreyAuto(String poliza) 
    {
		String datos = "";
		String query   = "";
                boolean encontrado=false;
	
		ResultSet sr=null;
                
		query = "select auto.poliza,cliente.nombre,auto.marca from auto,cliente where auto.poliza = '"+poliza+"' AND cliente.poliza='"+poliza+"'";
          
		try{			
			statement=conexion.createStatement();			
			sr=statement.executeQuery(query);
                        autoDP = new AutoDP();
                        clienteDP = new ClienteDP();
			                
        	while(sr.next())
        	{
        		int no;
                        String nombre, marca;
                        no=(sr.getInt(1));	
                        nombre=(sr.getString(2));	
                        marca=(sr.getString(3));	
       		       			
       		datos=datos+no+"_"+nombre+"_"+marca+"\n";
                System.out.println(datos);
                
        	}
        	statement.close();
        	System.out.println(query); 
		}
	
		catch(SQLException sqle){
			System.out.println("Error: "+sqle); 
			datos = "ERROR en la consulta "+ sqle;	
		}
		return datos;		
	}  

    String ConsultarNombreysusAutos(String poliza) {
        String datos = "";
		String query   = "";
                boolean encontrado=false;
	
		ResultSet sr=null;
                
		//query = "select auto.poliza,cliente.nombre,auto.marca from auto,cliente where auto.poliza = '"+poliza+"' AND cliente.poliza='"+poliza+"'";
                query = "SELECT nombre FROM cliente WHERE poliza='"+poliza+"' UNION SELECT marca FROM auto WHERE poliza='"+poliza+"'";
		System.out.println(query);
                try{			
			statement=conexion.createStatement();			
			sr=statement.executeQuery(query);
                        clienteDP = new ClienteDP();
                        autoDP = new AutoDP();
                        
			                
        	while(sr.next())
        	{
        		//int no;
                        String nombre, nombre1, nombre2;
                        //no=(sr.getInt(1));
                        
                        nombre=(sr.getString(1));
                        nombre1=(sr.getString(1));
                        nombre2=(sr.getString(1));
                        
                        //marca2=(sr.getString(3));
       		       			
       		datos=datos+nombre+"\n";
                //datos=datos+no+"_"+nombre+"_"+marca+"\n";
                System.out.println(datos);
                
        	}
        	statement.close();
        	System.out.println(query); 
		}
	
		catch(SQLException sqle){
			System.out.println("Error: "+sqle); 
			datos = "ERROR en la consulta "+ sqle;	
		}
		return datos;
    }

    String ConsultarPlacasPago(String placas) 
    {
		String datos = "";
		String query   = "";
                boolean encontrado=false;
	
		ResultSet sr=null;
                //clientedp = new ClienteDP;
		//query = "select auto.poliza,cliente.nombre,auto.marca from auto,cliente where auto.poliza = '"+placas+"' AND cliente.poliza='"+placas+"'";
                query = "SELECT auto.placas,auto.marca,accidente.cantPagar FROM auto,accidente WHERE auto.placas = '"+placas+"' AND accidente.placas='"+placas+"'";
		try{			
			statement=conexion.createStatement();			
			sr=statement.executeQuery(query);
                        autoDP = new AutoDP();
                        accidentesDP = new AccidentesDP();
			                
        	while(sr.next())
        	{
        		int no;
                        String placa, marca;                        	
                        placa=(sr.getString(1));	
                        marca=(sr.getString(2));
                        no=(sr.getInt(3));
       		       			
       		datos=datos+placa+"_"+marca+"_"+no+"\n";
                System.out.println(datos);
                
        	}
        	statement.close();
        	System.out.println(query); 
		}
	
		catch(SQLException sqle){
			System.out.println("Error: "+sqle); 
			datos = "ERROR en la consulta "+ sqle;	
		}
		return datos;		
	}  

    String ConsultarAutoyAccidentes(String placas)
    {
		String datos = "";
                //String datos1="";
		String query   = "";
                boolean encontrado=false;
	
		ResultSet sr=null;
                accidentesDP= new AccidentesDP();
                clienteDP = new ClienteDP();
		//query = "select auto.poliza,cliente.nombre,auto.marca from auto,cliente where auto.poliza = '"+placas+"' AND cliente.poliza='"+placas+"'";
                //query = "SELECT cliente.nombre, auto.marca,accidente.fecha,accidente.tipoAccidente FROM auto,accidente,cliente WHERE auto.placas = '"+placas+"' AND accidente.placas='"+placas+"' AND cliente.poliza='"+poliza+"'";
               //query = "SELECT nombre FROM cliente WHERE nombre='"+clienteDP.getNombre()+"' AND SELECT marca FROM auto WHERE placas='"+placas+"' AND SELECT fecha FROM accidente WHERE fecha='"+accidentesDP.getFecha()+"' AND SELECT tipoAccidente FROM accidente WHERE tipoAccidente='"+accidentesDP.getTipoAccidente()+"'";
		query = "SELECT cliente.nombre,auto.marca,accidente.fecha,accidente.tipoAccidente FROM cliente,auto,accidente WHERE cliente.poliza=auto.poliza and accidente.placas='"+placas+"' and accidente.placas=auto.placas";
                try{			
			statement=conexion.createStatement();			
			sr=statement.executeQuery(query);
                        autoDP = new AutoDP();
                        accidentesDP = new AccidentesDP();
                  
        	while(sr.next())
        	{
                        //String nombre;
                        String nombre,marca,fecha, tipo; 
                        
                        nombre=(sr.getString(1));
                        //datos1=datos1+nombre;
                        
                        marca=(sr.getString(2));	
                        fecha=(sr.getString(3));
                        tipo=(sr.getString(4));
                        
                        //no=(sr.getInt(3));
       		       			
       		datos=datos+nombre+"_"+marca+"_"+fecha+"_"+tipo+"\n";
                //datos=datos+nombre+"\n";
                System.out.println(datos);
                
        	}
        	statement.close();
        	System.out.println(query); 
		}
	
		catch(SQLException sqle){
			System.out.println("Error: "+sqle); 
			datos = "ERROR en la consulta "+ sqle;	
		}
		return datos;		
	}  

   
}
