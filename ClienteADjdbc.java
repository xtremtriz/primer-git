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
class ClienteADjdbc {

    
    Connection conexion;
    Statement statement;

    ClienteDP clienteDP;
    AutoDP autoDP;
    AccidentesDP segurosDP;
    public ClienteADjdbc()
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
    String Alta(String datos) 
    {
		String resultado = "";
		String insert="";

		clienteDP = new ClienteDP(datos);
		//insert="INSERT INTO Cliente VALUES("+clienteDP.toStringSql()+")";
                insert="INSERT INTO cliente VALUES("+clienteDP.toStringSql()+")";
		try{
			// 1. Abrir archivo de datos(abrir la base de datos para manipular cualquier tabla de la base datos )
			// archivoOut = new PrintWriter(new FileWriter("Clientes.txt",true));	 
			statement=conexion.createStatement();
		
			// 2. Abrir o almacenar los datos en archivo	
			//archivoOut.println(datos);
			// INSERT= "INSERT INTO CLIENTE VALUES(...)"
		//	archivoOut.println(clienteDP.toString());
			statement.executeUpdate(insert);
		
			// 3. Cerrar archivo	
		//	archivoOut.close();	
		statement.close();

			// 4. Enviar recibo de 
			resultado = "Captura correctisima";	
			System.out.println(insert); 	
			
		}catch(SQLException sqle){	
			System.out.println("Incorrecta: "+sqle); 
			resultado = "No puedes repetir poliza";	
		}
		return resultado;	
	}
	
    public String consultarCliente()
	{
		String datos = "";
		String query   = "";
	
		ResultSet sr=null;
		
		query="SELECT *FROM cliente";

	//	clienteDP = new ClienteDP();

		try{
			// 1. Abrir archivo de datos
		//	archivoIn = new BufferedReader(new FileReader("Clientes.txt"));
			//1.-abrir la base de datos 
			statement=conexion.createStatement();
			// 2. Procesar datos
			//while(archivoIn.ready())	{	datos = datos + archivoIn.readLine() + "\n";	}
		///	while(archivoIn.ready())
			sr=statement.executeQuery(query);	
			//{	
			//	str = archivoIn.readLine();
			//	clienteDP = new ClienteDP(str);
			//	datos = datos + clienteDP.toString() + "\n";
			//}				
			// 3. Cerrar
        	//archivoIn.close();
        	clienteDP=new ClienteDP();
        	while(sr.next())
        	{
        		
        	clienteDP.setNoCliente(sr.getInt(1));	
        	clienteDP.setNombre(sr.getString(2));	
        	clienteDP.setDireccion(sr.getString(3));	
       		clienteDP.setTelefono(sr.getInt(4));	
       		clienteDP.setEdoCivil(sr.getString(5));
       			
       		datos=datos+clienteDP.toString()+"\n";
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

    String consultarPoliza(String noCliente) 
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
					
					if(sr.getString(1).equals(noCliente))
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
					  datos = "No se localiza intenta otra ves " + noCliente;
				}
			statement.close();
			}
	
		catch(SQLException sqle){
			System.out.println("Error: "+sqle); 
			datos = "ERROR en la consulta "+ sqle;	
		}
		return datos;
	}

    String ConsultarNombre(String nombre) 
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
					
					if(sr.getString(2).equals(nombre))
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
					  datos = "No se localiza intenta otra ves " + nombre;
				}
			statement.close();
			}
	
		catch(SQLException sqle){
			System.out.println("Error: "+sqle); 
			datos = "ERROR en la consulta "+ sqle;	
		}
		return datos;
	}
    
    
    public String actualizarCliente(String datos)
    {
        String strUpdate="";
        String respuesta="";

        ClienteDP cliente = new ClienteDP(datos);

        strUpdate = "UPDATE cliente SET nombre='"+cliente.getNombre()+"', "+"direccion='"+cliente.getDireccion()+"', "+"telefono='"+cliente.getTelefono()+"' "+" WHERE poliza='"+cliente.getNoCliente()+"'";

        try
        {
            statement = conexion.createStatement();
            
            statement.executeUpdate(strUpdate);
            
            statement.close();
            
            System.out.println(conexion.nativeSQL(strUpdate));
            
            respuesta = "Actualizacion de datos correcta...";
        }
        catch(SQLException sqle)
        {
            System.out.println("Error al abrir la BD...\n"+sqle);
            respuesta = "Error en Actualizacion...";
        }
        
        return respuesta;
    }
    
    
    String AltaA(String datos) 
    {
		String resultado = "";
		String insert="";

		autoDP = new AutoDP(datos);
		//insert="INSERT INTO Cliente VALUES("+clienteDP.toStringSql()+")";
                insert="INSERT INTO auto VALUES("+autoDP.toStringSql()+")";
		try{
			// 1. Abrir archivo de datos(abrir la base de datos para manipular cualquier tabla de la base datos )
			// archivoOut = new PrintWriter(new FileWriter("Clientes.txt",true));	 
			statement=conexion.createStatement();
		
			// 2. Abrir o almacenar los datos en archivo	
			//archivoOut.println(datos);
			// INSERT= "INSERT INTO CLIENTE VALUES(...)"
		//	archivoOut.println(clienteDP.toString());
			statement.executeUpdate(insert);
		
			// 3. Cerrar archivo	
		//	archivoOut.close();	
		statement.close();

			// 4. Enviar recibo de 
			resultado = "Captura correctisima";	
			System.out.println(insert); 	
			
		}catch(SQLException sqle){	
			System.out.println("Incorrecta: "+sqle); 
			resultado = "Debes introducir una POLIZA existente y no repetir PLACAS ";	
		}
		return resultado;	
	}

    String consultarClienteAuto()
    {
		String datos = "";
		String query   = "";
	
		ResultSet sr=null;
		
		query="SELECT *FROM auto";

	//	clienteDP = new ClienteDP();

		try{
			// 1. Abrir archivo de datos
		//	archivoIn = new BufferedReader(new FileReader("Clientes.txt"));
			//1.-abrir la base de datos 
			statement=conexion.createStatement();
			// 2. Procesar datos
			//while(archivoIn.ready())	{	datos = datos + archivoIn.readLine() + "\n";	}
		///	while(archivoIn.ready())
			sr=statement.executeQuery(query);	
			//{	
			//	str = archivoIn.readLine();
			//	clienteDP = new ClienteDP(str);
			//	datos = datos + clienteDP.toString() + "\n";
			//}				
			// 3. Cerrar
        	//archivoIn.close();
        	autoDP=new AutoDP();
        	while(sr.next())
        	{
        		
        	autoDP.setPoliza(sr.getInt(1));	
        	autoDP.setMarca(sr.getString(2));	
        	autoDP.setModelo(sr.getString(3));	
       		autoDP.setAño(sr.getInt(4));	
       		autoDP.setPlacas(sr.getString(5));
                autoDP.setColor(sr.getString(6));
       			
       		datos=datos+autoDP.toString()+"\n";
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

    String consultarPlacas(String placas) 
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
					
					if(sr.getString(5).equals(placas))
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
					  datos = "No se localiza intenta otra ves " + placas;
				}
			statement.close();
			}
	
		catch(SQLException sqle){
			System.out.println("Error: "+sqle); 
			datos = "ERROR en la consulta "+ sqle;	
		}
		return datos;
	}

    String ConsultarPolizaAuto(String poliza) 
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
    
    String ActualizarPolizaAuto(String datos) {
        String strUpdate="";
        String respuesta="";

        AutoDP auto = new AutoDP(datos);

        strUpdate = "UPDATE cliente SET marca='"+auto.getMarca()+"', "+"modelo='"+auto.getModelo()+"', "+"año='"+auto.getAño()+"', "+"placas='"+auto.getPlacas()+"' " + " WHERE poliza='"+auto.getPoliza()+"'";

        try
        {
            statement = conexion.createStatement();
            
            statement.executeUpdate(strUpdate);
            
            statement.close();
            
            System.out.println(conexion.nativeSQL(strUpdate));
            
            respuesta = "Actualizacion de datos correcta...";
        }
        catch(SQLException sqle)
        {
            System.out.println("Error al abrir la BD...\n"+sqle);
            respuesta = "Error en Actualizacion...";
        }
        
        return respuesta;
    }
    
    String AltaSeguro(String datos) 
    {
		String resultado = "";
		String insert="";

		segurosDP = new AccidentesDP(datos);
		//insert="INSERT INTO Cliente VALUES("+clienteDP.toStringSql()+")";
                insert="INSERT INTO accidente VALUES("+segurosDP.toStringSql()+")";
		try{
			// 1. Abrir archivo de datos(abrir la base de datos para manipular cualquier tabla de la base datos )
			// archivoOut = new PrintWriter(new FileWriter("Clientes.txt",true));	 
			statement=conexion.createStatement();
		
			// 2. Abrir o almacenar los datos en archivo	
			//archivoOut.println(datos);
			// INSERT= "INSERT INTO CLIENTE VALUES(...)"
		//	archivoOut.println(clienteDP.toString());
			statement.executeUpdate(insert);
		
			// 3. Cerrar archivo	
		//	archivoOut.close();	
		statement.close();

			// 4. Enviar recibo de 
			resultado = "Captura correctisima";	
			System.out.println(insert); 	
			
		}catch(SQLException sqle){	
			System.out.println("Incorrecta: "+sqle); 
			resultado = "Algun campo esta mal";	
		}
		return resultado;	
	}
    String consultarSeguro()
    {
		String datos = "";
		String query   = "";
	
		ResultSet sr=null;
		
		query="SELECT *FROM accidente";

	//	clienteDP = new ClienteDP();

		try{
			// 1. Abrir archivo de datos
		//	archivoIn = new BufferedReader(new FileReader("Clientes.txt"));
			//1.-abrir la base de datos 
			statement=conexion.createStatement();
			// 2. Procesar datos
			//while(archivoIn.ready())	{	datos = datos + archivoIn.readLine() + "\n";	}
		///	while(archivoIn.ready())
			sr=statement.executeQuery(query);	
			//{	
			//	str = archivoIn.readLine();
			//	clienteDP = new ClienteDP(str);
			//	datos = datos + clienteDP.toString() + "\n";
			//}				
			// 3. Cerrar
        	//archivoIn.close();
        	segurosDP=new AccidentesDP();
        	while(sr.next())
        	{
        		
        	segurosDP.setPlacas(sr.getString(1));	
        	segurosDP.setCantPagar(sr.getInt(2));	
        	segurosDP.setFecha(sr.getString(3));	
       		segurosDP.setTipoAccidente(sr.getString(4));	
       			
       		datos=datos+segurosDP.toString()+"\n";
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

    String ConsultarPoliza(String placas) 
    {
		String datos="";
		String query   = "";
		boolean encontrado=false;
			
		ResultSet sr=null;
			query="SELECT * FROM accidente";
		
			try{
				statement=conexion.createStatement();
				sr=statement.executeQuery(query);
				segurosDP=new AccidentesDP();
				
				while(sr.next())
				{
					
					if(sr.getString(1).equals(placas))
					{
					
                segurosDP.setPlacas(sr.getString(1));	
        	segurosDP.setCantPagar(sr.getInt(2));	
        	segurosDP.setFecha(sr.getString(3));	
       		segurosDP.setTipoAccidente(sr.getString(4));	
       			
       		datos=datos+segurosDP.toString()+"\n";
       			
       			encontrado=true;				
					}
				}
				if(!encontrado)
				{
					  datos = "No se localiza intenta otra ves " + placas;
				}
			statement.close();
			}
	
		catch(SQLException sqle){
			System.out.println("Error: "+sqle); 
			datos = "ERROR en la consulta "+ sqle;	
		}
		return datos;
	}       

    String ComsultarTipo(String tipo) 
    {
		String datos="";
		String query   = "";
		boolean encontrado=false;
			
		ResultSet sr=null;
			query="SELECT * FROM accidente";
		
			try{
				statement=conexion.createStatement();
				sr=statement.executeQuery(query);
				segurosDP=new AccidentesDP();
				
				while(sr.next())
				{
					
					if(sr.getString(4).equals(tipo))
					{
					
                segurosDP.setPlacas(sr.getString(1));	
        	segurosDP.setCantPagar(sr.getInt(2));	
        	segurosDP.setFecha(sr.getString(3));	
       		segurosDP.setTipoAccidente(sr.getString(4));	
       			
       		datos=datos+segurosDP.toString()+"\n";
       			
       			encontrado=true;				
					}
				}
				if(!encontrado)
				{
					  datos = "No se localiza intenta otra ves " + tipo;
				}
			statement.close();
			}
	
		catch(SQLException sqle){
			System.out.println("Error: "+sqle); 
			datos = "ERROR en la consulta "+ sqle;	
		}
		return datos;
	} 
    
    String actualizarSeguro(String datos){
    String strUpdate="";
        String respuesta="";

        AccidentesDP seguros = new AccidentesDP(datos);

        strUpdate = "UPDATE accidente SET cantPagar='"+seguros.getCantPagar()+"', "+"fecha='"+seguros.getFecha()+"', "+"tipoAccidente='"+seguros.getTipoAccidente()+"' "+" WHERE placas='"+seguros.getPlacas()+"'";

        try
        {
            statement = conexion.createStatement();
            
            statement.executeUpdate(strUpdate);
            
            statement.close();
            
            System.out.println(conexion.nativeSQL(strUpdate));
            
            respuesta = "Actualizacion de datos correcta...";
        }
        catch(SQLException sqle)
        {
            System.out.println("Error al abrir la BD...\n"+sqle);
            respuesta = "Error en Actualizacion...";
        }
        
        return respuesta;
    }
}
