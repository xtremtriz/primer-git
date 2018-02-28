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

public class AutoGUI extends JFrame implements ActionListener
{
	private JTextField tfPolizaAuto, tfMarca, tfModelo,tfAño, tfPlacas,tfColor;
	private JButton bCapturar, bConsultar ,bConsultarPlacas,bConsultarPolizAuto,bCancelar,bActualizar, bSalir;
	private JTextArea taDatos;
	private JPanel panel1, panel2;
	
	private ClienteADjdbc clientejdbc = new ClienteADjdbc();
        MenuGUI  menuGUI;
	
	public AutoGUI()
	{
		super("");
		
		// 1. Crear objetos
		tfPolizaAuto    = new JTextField();     
		tfMarca = new JTextField();
		tfModelo   = new JTextField();	
                tfAño =new JTextField();
                tfPlacas =new JTextField();
                tfColor=new JTextField();
		
		bCapturar  			  = new JButton("Capturar cliente");
		bConsultar			  = new JButton("Consulta cliente");	
		bConsultarPlacas = new JButton("Consultar placas");
		bConsultarPolizAuto = new JButton("Consultar poliza");
                //bCancelar = new JButton("Cancelar ");
                //bActualizar = new JButton("Actualizar datos ");
		bSalir     = new JButton("Salir"); 
		
		// Adicionar deteccion de eventos a los botones
		bCapturar.addActionListener(this);
		bConsultar.addActionListener(this);
		bConsultarPlacas.addActionListener(this);
		bConsultarPolizAuto.addActionListener(this);
                //bCancelar.addActionListener(this);
                //bActualizar.addActionListener(this);
		bSalir.addActionListener(this);
		
		taDatos    = new JTextArea(15,30);
		panel1     = new JPanel();
		panel2     = new JPanel();
		
		// 2. Adicionar los objetos al panel1
		panel1.setLayout(new GridLayout(10,6));
		panel2.setLayout(new FlowLayout());
		
		panel1.add(new JLabel("Introduce poliza"));
		panel1.add(tfPolizaAuto);		
		
		panel1.add(new JLabel("Marca"));
		panel1.add(tfMarca);
		
		panel1.add(new JLabel("Modelo"));
		panel1.add(tfModelo);
                
		panel1.add(new JLabel("Año del coche "));
		panel1.add(tfAño);
                
                panel1.add(new JLabel("Placas"));
		panel1.add(tfPlacas);
                
                panel1.add(new JLabel("Color"));
		panel1.add(tfColor);
		
			
		panel1.add(bCapturar);
		panel1.add(bConsultar);
		panel1.add(bConsultarPlacas);
		panel1.add(bConsultarPolizAuto);
                //panel1.add(bCancelar);
                //panel1.add(bActualizar);
		panel1.add(bSalir);
		
		panel2.add(panel1);
		panel2.add(new JScrollPane(taDatos));
                
                //bCancelar.setEnabled(false);
                //bActualizar.setEnabled(false);
		
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
		
		tfPolizaAuto.setText(st.nextToken());		
		tfMarca.setText(st.nextToken());
		tfModelo.setText(st.nextToken());
                tfAño.setText(st.nextToken());
                tfPlacas.setText(st.nextToken());
                tfColor.setText(st.nextToken());
	}
	
	private String obtenerDatos()
	{
		String datos="";
		
		String noCliente     = tfPolizaAuto.getText();		
		String marca    = tfMarca.getText();
		String modelo = tfModelo.getText();
                String año  = tfAño.getText();
                String placas = tfPlacas.getText();
                String color = tfColor.getText();
		
		if(noCliente.equals("") ||marca.equals("")|| modelo.equals("")|| año.equals("") ||  placas.equals("") || color.equals(""))
			datos = "VACIO";
			else{
					
				 datos = noCliente+"_"+marca+"_"+modelo+"_"+año+"_"+placas+"_"+color;
				}
		
		return datos;
	}
	
        private void activarBotones()
	{
		bCapturar.setEnabled(true);
		bConsultar.setEnabled(true);
		bConsultarPlacas.setEnabled(true);
		bConsultarPolizAuto.setEnabled(true);
                bSalir.setEnabled(true);               
		
		//bCancelar.setEnabled(false);
                //bActualizar.setEnabled(false);
	}
        private void inactivarBotones()
	{
		bCapturar.setEnabled(false);
		bConsultar.setEnabled(false);
		bConsultarPlacas.setEnabled(false);
		bConsultarPolizAuto.setEnabled(false);
                
                //bCancelar.setEnabled(true);
                //bActualizar.setEnabled(true);
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
				taDatos.setText("Algun campo esta vacio checalo ...");
			
			else{
			
				// 3. Capturar los datos del cliente
				//String poli = poliza.getText();
				resultado = clientejdbc.AltaA(datos);
				
				// 4. Desplegar resultado de la transaccion
				taDatos.setText(resultado);
			}
		}
		
		if(e.getSource() == bConsultar)
		{
			String datos = clientejdbc.consultarClienteAuto();
			taDatos.setText(datos);
                        //activarBotones();
		}
		

		if(e.getSource() == bConsultarPlacas)
		{
			String placas = tfPlacas.getText();
			String datos = clientejdbc.consultarPlacas(placas);
			taDatos.setText(datos); 
                        //activarBotones();
		}
		if(e.getSource() == bConsultarPolizAuto)
		{
			String poliza = tfPolizaAuto.getText();
			String datos = clientejdbc.ConsultarPolizaAuto(poliza);
			taDatos.setText(datos); 
                        //inactivarBotones();
		}
                /*if(e.getSource() == bActualizar)
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
                    resultado = clientejdbc.ActualizarPolizaAuto(datos);
                    
                    // 4. Desplegar resultado de la transaccion
                    taDatos.setText(resultado);
                    }
                }
                tfPolizaAuto.setEditable(true);
                activarBotones();
		}
                if(e.getSource() == bCancelar)
		{
			taDatos.setText("Transaccion cancelada...");
			activarBotones();
		}*/
		
		if(e.getSource() == bSalir)
			//new MenuGUI();
                    setVisible(false);
	}
	
	public static void main(String args[])
	{
            //AutoGUI autoGUI = new AutoGUI();
	}
}
