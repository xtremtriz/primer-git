/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguros;

/**
 *
 * @author Uriel
 */
//import Server.ClienteADjdbc;
//import serverseguros.ClienteADjdbc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class ClienteGUI extends JFrame implements ActionListener
{

        
	private JTextField poliza, tfdireccion, tftelefono,tfnombre, tfedoCivil;
	private JButton bCapturar, bConsultar ,bConsultarPoliza,bConsultarNombre,bCancelar,bActualizar, bSalir;
	private JTextArea taDatos;
	private JPanel panel1, panel2;
        
	
	private ClienteADjdbc clientejdbc = new ClienteADjdbc();
        //private Conexion conexion = new Conexion();
        MenuGUI menuGUI;
	
	public ClienteGUI()
	{
		super("ClienteGUI");
		
		// 1. Crear objetos
                        
		poliza    = new JTextField();
		tfdireccion = new JTextField();
		tftelefono   = new JTextField();
		tfnombre =new JTextField();
                tfedoCivil =new JTextField();
		
		bCapturar  			  = new JButton("Capturar cliente");
		bConsultar			  = new JButton("Consulta cliente");	
		bConsultarPoliza = new JButton("Consultar x poliza para actualizar");
		bConsultarNombre = new JButton("Consultar x nombre");
                bCancelar = new JButton("Cancelar ");
                bActualizar = new JButton("Actualizar datos ");
		bSalir     = new JButton("Salir"); 
		
		// Adicionar deteccion de eventos a los botones
                
		bCapturar.addActionListener(this);
		bConsultar.addActionListener(this);
		bConsultarPoliza.addActionListener(this);
		bConsultarNombre.addActionListener(this);
                bCancelar.addActionListener(this);
                bActualizar.addActionListener(this);
		bSalir.addActionListener(this);
		
		taDatos    = new JTextArea(25,30);
		panel1     = new JPanel();
		panel2     = new JPanel();
		
		// 2. Adicionar los objetos al panel1
		panel1.setLayout(new GridLayout(13,5));
                
		panel2.setLayout(new FlowLayout());
		
		panel1.add(new JLabel("Asignar poliza"));
		panel1.add(poliza);
		
		panel1.add(new JLabel("Nombre "));
		panel1.add(tfnombre);
		
		panel1.add(new JLabel("Direccion"));
		panel1.add(tfdireccion);
		
		panel1.add(new JLabel("telefono"));
		panel1.add(tftelefono);
                
                panel1.add(new JLabel("Estado civil"));
		panel1.add(tfedoCivil);
		
			
		panel1.add(bCapturar);
		panel1.add(bConsultar);
		panel1.add(bConsultarPoliza);
		panel1.add(bConsultarNombre);
                panel1.add(bCancelar);
                panel1.add(bActualizar);
		panel1.add(bSalir);
		
		panel2.add(panel1);
		panel2.add(new JScrollPane(taDatos));
                
                bCancelar.setEnabled(false);
                bActualizar.setEnabled(false);
		
		// 3. Adicionar panel2 al JFrame
                //menuGUI.frame.add(panel2).setVisible(true);
		//setVisible(true);
		add(panel2);
		setSize(550,550);
		setVisible(true);
	}
	
	public void desplegar(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos,"_");
		
		poliza.setText(st.nextToken());
		tfnombre.setText(st.nextToken());
		tfdireccion.setText(st.nextToken());
		tftelefono.setText(st.nextToken());
                tfedoCivil.setText(st.nextToken());
	}
	
	private String obtenerDatos()
	{
		String datos="";
		
		String noCliente     = poliza.getText();
		String nombre  = tfnombre.getText();
		String direccion    = tfdireccion.getText();
		String telefono = tftelefono.getText();
                String edoCivil = tfedoCivil.getText();
	
		
		if(noCliente.equals("") || nombre.equals("") || direccion.equals("")|| telefono.equals("")|| edoCivil.equals(""))
			datos = "VACIO";
			else{
					
				 datos = noCliente+"_"+nombre+"_"+direccion+"_"+telefono+"_"+edoCivil;
				}
		
		return datos;
	}
	private boolean validar()
        {
            boolean valid = false;
            String noCliente     = poliza.getText();
            String nombre  = tfnombre.getText();
            String direccion    = tfdireccion.getText();
            String telefono = tftelefono.getText();
            String edoCivil = tfedoCivil.getText();
	
		
		if(noCliente.equals("") || nombre.equals("") || direccion.equals("")|| telefono.equals("")|| edoCivil.equals(""))
          {
              valid=false;
          }
          else{
              valid = true;     
          }
       
            return valid;
           
        }
        
        private void activarBotones()
	{
		bCapturar.setEnabled(true);
		bConsultar.setEnabled(true);
		bConsultarPoliza.setEnabled(true);
		bConsultarNombre.setEnabled(true);
                bSalir.setEnabled(true);               
		
		bCancelar.setEnabled(false);
                bActualizar.setEnabled(false);
	}
        private void inactivarBotones()
	{
		bCapturar.setEnabled(false);
		bConsultar.setEnabled(false);
		bConsultarPoliza.setEnabled(false);
		bConsultarNombre.setEnabled(false);
                
                bCancelar.setEnabled(true);
                bActualizar.setEnabled(true);
                bSalir.setEnabled(true);
		
		
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == bCapturar)
		{                                               
			String datos="";
			String resultado="";
			activarBotones();
			// 1. Obtner dato de los JTextFields
			datos = obtenerDatos();
			
			// 2. Checar si algun campo es vacio o saldo no numerico
			if(datos.equals("VACIO"))
				taDatos.setText("Algun campo esta vacio checalo...");
			
			else{
				//resultado = clientejdbc.Alta(datos);
				
				// 4. Desplegar resultado de la transaccion
				taDatos.setText(resultado);
			}
		}
		
		if(e.getSource() == bConsultar)
		{
			String datos = clientejdbc.consultarCliente();
			taDatos.setText(datos);
                        /*conexion.establecerConexion();
                        conexion.enviarDatos("obtenercliente");
                        String datos = conexion.recibirDatos();
                        conexion.cerrarConexion();*/
                        //taDatos.setText(datos);
                        activarBotones();
		}
		

		if(e.getSource() == bConsultarPoliza)
		{
			String noCliente = poliza.getText();
			String datos = clientejdbc.consultarPoliza(noCliente);
			taDatos.setText(datos); 
                        inactivarBotones();
                        poliza.setEditable(false);
		}
		if(e.getSource() == bConsultarNombre)
		{
			String nombre = tfnombre.getText();
			String datos = clientejdbc.ConsultarNombre(nombre);
			taDatos.setText(datos); 
                        activarBotones();
                        
		}
                if(e.getSource() == bActualizar)
                {
                String datos="";
                String resultado="";
            
                // 1. Obtener dato de los JTextFields
                datos = obtenerDatos();
            
                // 2. Checar si algun campo es vacio o saldo no numerico
                if(datos.equals("VACIO"))
                taDatos.setText("Algun campo esta vacío...");
                else
                {
                if(datos.equals("NO_NUMERICO"))
                    taDatos.setText("Saldo debe ser numerico...");
                else
                    {
                    // 3. Capturar los datos del cliente
                    resultado = clientejdbc.actualizarCliente(datos);
                    
                    // 4. Desplegar resultado de la transaccion
                    taDatos.setText(resultado);
                    }
                }
                poliza.setEditable(true);
                activarBotones();
                }
                if(e.getSource() == bCancelar)
		{
			taDatos.setText("Transaccion cancelada...");
			activarBotones();
                        poliza.setEditable(true);
		}
		
		if(e.getSource() == bSalir)
                    //System.exit(0);   
                    setVisible(false);
                }
	
	public static void main(String args[])
	{
            //ClienteGUI clienteGUI = new ClienteGUI();
	}
}