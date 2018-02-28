/*******************************************************/
/*** Programa de Intercambio de Mensajes con Sockets ***/
/***              Programa Cliente                  ***/
/*******************************************************/
package seguros;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.BufferedInputStream;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;

import java.io.IOException;
import java.io.InputStream;

import java.net.Socket;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Conexion
{
  	private JTextField tfDatos;
 	 private JButton bEnviar;
  	private JTextArea taDatos;
  	private JPanel panel1;
  	private JPanel panel2;

  	private Socket socket;

  	private BufferedReader bufferEntrada;
	private PrintWriter    bufferSalida;


	public void enviarDatos(String datos)
	{
		bufferSalida.println(datos);
		bufferSalida.flush();
	}

	public String recibirDatos()
	{
		String datos="";

		try
		{
			datos = bufferEntrada.readLine();
			bufferSalida.flush();
		}
		catch(IOException ioe)
		{
			System.out.println("Error: "+ioe);
		}

		return datos;
	}
        public InputStream getStreamed() throws Exception{
		InputStream in = new BufferedInputStream(socket.getInputStream());
		
		return in;
	}
	
	public void cerrarConexion()
	{
		try
		{
			bufferEntrada.close();
			bufferSalida.close();
			socket.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error: "+ioe);
		}
	}

  public void establecerConexion()
  {
    try{
    //  Establecer conexion con el server en el puerto 5005
    socket = new Socket("localhost",5005);
    //socket = new Socket("10.25.194.147",5005);

      // Preparar canales o buffers de comunicacion
    bufferEntrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    bufferSalida  = new PrintWriter(socket.getOutputStream());
    bufferSalida.flush();
    }

    catch(Exception e)
    {
      System.out.println("Error: "+e);
    }

  }

  public static void main(String args[])
  {
  	new Conexion();	
  }


}
