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
//import Server.ConsultasADjdbc;
//import Server.ConsultasADjdbc;
//import Server.ClienteADjdbc;
//import serverseguros.ClienteADjdbc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class ConsultasGUI extends JFrame implements ActionListener
{
	private JTextField tfPolizaAuto, tfNombreCliente, tfMarcaAuto,tfTipoAccidente, tfPlacas,tfColor;
	private JButton bClientePoliza ,bConsultarAutos,bConsultarAutosCliente,bConsultarNombreYAuto,bConsultaGeneral,bConsultaUnion,bConsultarPlacasPago, bSalir;
	
        private JButton bConsultaAutoyAccidentes;
                private JTextArea taDatos;
	private JPanel panel1, panel2;
	
	private ConsultasADjdbc consultasjdbc = new ConsultasADjdbc();
        //private ClienteADjdbc clientejdbc  = new ClienteADjdbc();
        Conexion conexion = new Conexion();
        MenuGUI menuGUI;
	
	public ConsultasGUI()
	{
		super("");
		
		// 1. Crear objetos
		tfPolizaAuto    = new JTextField();  
                tfPlacas =new JTextField();
		/*tfNombreCliente = new JTextField();
		tfMarcaAuto   = new JTextField();	
                tfTipoAccidente =new JTextField();
                
                tfColor=new JTextField();*/
		
		//bCapturar  			  = new JButton("Capturar cliente");
                bConsultaGeneral = new JButton("Consultar clientes de manera General ");         
                bConsultarAutos = new JButton("Consultar autos de manera general");
		bClientePoliza	 = new JButton("Consulta cliente x poliza");	
		bConsultarAutosCliente = new JButton("Consultar autos del cliente x poliza");
		bConsultarNombreYAuto = new JButton("Consultar autos x nombre del cliente"); 
                bConsultaUnion = new JButton("Consultar Cliente y sus autos ");
                bConsultarPlacasPago= new JButton("Consultar Placas y su pago");
                bConsultaAutoyAccidentes = new JButton("Consultar auto y sus accidentes");
               
		bSalir     = new JButton("Salir"); 
		
		// Adicionar deteccion de eventos a los botones
		//bCapturar.addActionListener(this);
                bConsultaGeneral.addActionListener(this);
                bConsultarAutos.addActionListener(this);
		bClientePoliza.addActionListener(this);
		bConsultarAutosCliente.addActionListener(this);
		bConsultarNombreYAuto.addActionListener(this);               
                bConsultaUnion.addActionListener(this);
                bConsultarPlacasPago.addActionListener(this);
                bConsultaAutoyAccidentes.addActionListener(this);
		bSalir.addActionListener(this);
		
		taDatos    = new JTextArea(15,30);
		panel1     = new JPanel();
		panel2     = new JPanel();
		
		// 2. Adicionar los objetos al panel1
		panel1.setLayout(new GridLayout(10,6));
		panel2.setLayout(new FlowLayout());
		
		panel1.add(new JLabel("Introduce poliza"));
		panel1.add(tfPolizaAuto);
                
                panel1.add(new JLabel("Placas del auto"));
		panel1.add(tfPlacas);
                		
		/*panel1.add(new JLabel("Nombre del Cliente"));
		panel1.add(tfNombreCliente);
		
		panel1.add(new JLabel("Marca del auto"));
		panel1.add(tfMarcaAuto);
                
		panel1.add(new JLabel("Tipo de accidente "));
		panel1.add(tfTipoAccidente);
                
                
                panel1.add(new JLabel("Color"));
		panel1.add(tfColor);*/
			
		//panel1.add(bCapturar);
                panel1.add(bConsultaGeneral);
                panel1.add(bConsultarAutos);
		panel1.add(bClientePoliza);
		panel1.add(bConsultarAutosCliente);
		panel1.add(bConsultarNombreYAuto);                
                panel1.add(bConsultaUnion);
                panel1.add(bConsultarPlacasPago);
                panel1.add(bConsultaAutoyAccidentes);
		panel1.add(bSalir);
		
		panel2.add(panel1);
		panel2.add(new JScrollPane(taDatos));
                
                //bConsultaGeneral.setEnabled(false);
                //bConsultaUnion.setEnabled(false);
		
		// 3. Adicionar panel2 al JFrame
		add(panel2);
		setSize(550,550);
                setVisible(true);
                
                
	}
	
	public void desplegar(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos,"_");
		
		tfPolizaAuto.setText(st.nextToken());	
                tfPlacas.setText(st.nextToken());
		/*tfNombreCliente.setText(st.nextToken());
		tfMarcaAuto.setText(st.nextToken());
                tfTipoAccidente.setText(st.nextToken());
                tfPlacas.setText(st.nextToken());
                tfColor.setText(st.nextToken());*/
	}
	
	private String obtenerDatos()
	{
		String datos="";
		
		String noCliente     = tfPolizaAuto.getText();	
                 String placas = tfPlacas.getText();
		/*String marca    = tfNombreCliente.getText();
		String modelo = tfMarcaAuto.getText();
                String año  = tfTipoAccidente.getText();
               
                String color = tfColor.getText();*/
		
		if(noCliente.equals("") || placas.equals(""))
			datos = "VACIO";
			else{
					
				 datos = noCliente+"_"+placas;
				}
		
		return datos;
	}
	
      
	public void actionPerformed(ActionEvent e)
	{
              if(e.getSource() == bConsultaGeneral)
		{
			//String datos = clientejdbc.consultarCliente();
			//taDatos.setText(datos);
                       			
		}
              if(e.getSource() == bConsultarAutos)
		{
			//String datos = clientejdbc.consultarClienteAuto();
			//taDatos.setText(datos);
                       			
		}
		
		if(e.getSource() == bClientePoliza)
		{
                    String poliza = tfPolizaAuto.getText();
                    String datos = consultasjdbc.consultarClientePoliza(poliza);
                    taDatos.setText(datos);
                    
		}
		

		if(e.getSource() == bConsultarAutosCliente)
		{
			String poliza = tfPolizaAuto.getText();
			String datos = consultasjdbc.consultarAutosCliente(poliza);
			taDatos.setText(datos); 
                        
		}
		if(e.getSource() == bConsultarNombreYAuto)
		{
			String poliza = tfPolizaAuto.getText();
			String datos = consultasjdbc.ConsultarNombreyAuto(poliza);
			taDatos.setText(datos); 
                       
		}
                if(e.getSource() == bConsultaUnion)
		{
			String poliza = tfPolizaAuto.getText();
			conexion.establecerConexion();	
                        conexion.enviarDatos("Reporte1");
                        conexion.enviarDatos(poliza);
                        String datos = conexion.recibirDatos();
                        taDatos.setText(datos);
                        conexion.cerrarConexion();
		}
                if(e.getSource() == bConsultarPlacasPago)
		{
			String placas = tfPlacas.getText();
                        
			String datos = consultasjdbc.ConsultarPlacasPago(placas);
                      
			taDatos.setText(datos); 
                        
                       
		}
		if(e.getSource() == bConsultaAutoyAccidentes)
		{
			/*String placas = tfPlacas.getText();
                        //String poliza = tfPolizaAuto.getText();
                        
			String datos = consultasjdbc.ConsultarAutoyAccidentes(placas);
                        //String datos1 = consultasjdbc.ConsultarAutoyAccidentes(placas,poliza);
			taDatos.setText(datos); */
                        String placas = tfPlacas.getText();
			conexion.establecerConexion();	
                        conexion.enviarDatos("Reporte2");
                        conexion.enviarDatos(placas);
                        String datos = conexion.recibirDatos();
                        taDatos.setText(datos);
                        conexion.cerrarConexion();
                       
		}
		if(e.getSource() == bSalir)
			//new MenuGUI();
                    setVisible(false);
	}
	
	public static void main(String args[])
	{
            ConsultasGUI consultasGUI = new ConsultasGUI();
	}
}
