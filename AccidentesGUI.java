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

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AccidentesGUI extends JFrame implements ActionListener
{
	private JTextField tfPlacas, tfFecha, tfTipo,tfCosto;
	private JButton bCapturar, bConsultarGeneral ,bConsultarPoliza,bConsultarTipo,bCancelar,bActualizar, bSalir;
	private JTextArea taDatos;
	private JPanel panel1, panel2;
	
	private ClienteADjdbc clientejdbc = new ClienteADjdbc();
	MenuGUI menuGUI;
	public AccidentesGUI()
	{
		super("");
		
		// 1. Crear objetos
		tfPlacas    = new JTextField();
		tfFecha = new JTextField();
		tfTipo   = new JTextField();
		tfCosto =new JTextField();
                tfFecha.setEditable(false);
		
		bCapturar  			  = new JButton("Capturar accidente");
		bConsultarGeneral			  = new JButton("Consulta genereal");	
		bConsultarPoliza = new JButton("Consultar x placas");
		bConsultarTipo = new JButton("Consultar autos");
                bCancelar = new JButton("Cancelar");
                bActualizar = new JButton("Actualizar datos");
                
		bSalir     = new JButton("Salir"); 
		
		// Adicionar deteccion de eventos a los botones
		bCapturar.addActionListener(this);
		bConsultarGeneral.addActionListener(this);
		bConsultarPoliza.addActionListener(this);
		bConsultarTipo.addActionListener(this);
                bCancelar.addActionListener(this);
                bActualizar.addActionListener(this);
		bSalir.addActionListener(this);
		
		taDatos    = new JTextArea(15,30);
		panel1     = new JPanel();
		panel2     = new JPanel();
		
		// 2. Adicionar los objetos al panel1
		panel1.setLayout(new GridLayout(10,4));
		panel2.setLayout(new FlowLayout());
		
		panel1.add(new JLabel("Placas"));
		panel1.add(tfPlacas);
                
                panel1.add(new JLabel("Asignar costo total: "));
		panel1.add(tfCosto);
		
		panel1.add(new JLabel("Fecha de accidente "));
		panel1.add(tfFecha);
		
		panel1.add(new JLabel("Tipo de accidente"));
		panel1.add(tfTipo);		
		
		
			
		panel1.add(bCapturar);
		panel1.add(bConsultarGeneral);
		panel1.add(bConsultarPoliza);
		panel1.add(bConsultarTipo);
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
                tfFecha.setText("fecha actual");
	}
	
	public void desplegar(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos,"_");
		
		tfPlacas.setText(st.nextToken());
		tfCosto.setText(st.nextToken());
		tfFecha.setText(st.nextToken());
		tfTipo.setText(st.nextToken());
	}
	
	private String obtenerDatos()
	{
		String datos="";
		
		String noPlacas     = tfPlacas.getText();
		String costo  = tfCosto.getText();
                
                SimpleDateFormat formatDate = new SimpleDateFormat(" yyyy-MM-dd ");
                Date fecha = new Date();
                String fechaActual = formatDate.format(fecha);
                tfFecha.setText(fechaActual);
                
		String fechas    = tfFecha.getText();
		String tipo = tfTipo.getText();
	
		
		if(noPlacas.equals("") || costo.equals("")|| tipo.equals(""))
			datos = "VACIO";
			else{
					
				 datos = noPlacas+"_"+costo+"_"+fechas+"_"+tipo;
				}
		
		return datos;
	}
        
        private void activarBotones()
	{
		bCapturar.setEnabled(true);
		bConsultarGeneral.setEnabled(true);
		bConsultarPoliza.setEnabled(true);
		bConsultarTipo.setEnabled(true);
                bSalir.setEnabled(true);               
		
		bCancelar.setEnabled(false);
                bActualizar.setEnabled(false);
	}
        private void inactivarBotones()
	{
		bCapturar.setEnabled(false);
		bConsultarGeneral.setEnabled(false);
		bConsultarPoliza.setEnabled(false);
		bConsultarTipo.setEnabled(false);
                
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
			
				// 3. Capturar los datos del cliente
				//String matricula = poliza.getText();
				resultado = clientejdbc.AltaSeguro(datos);
				
				// 4. Desplegar resultado de la transaccion
				taDatos.setText(resultado);
			}
		}
		
		if(e.getSource() == bConsultarGeneral)
		{
			String datos = clientejdbc.consultarSeguro();
			taDatos.setText(datos);
                        activarBotones();
		}
		

		if(e.getSource() == bConsultarPoliza)
		{
			String placas = tfPlacas.getText();
			String datos = clientejdbc.ConsultarPoliza(placas);
			taDatos.setText(datos); 
                        inactivarBotones();
                        tfPlacas.setEditable(false);
		}
		if(e.getSource() == bConsultarTipo)
		{
                        String datos = clientejdbc.consultarClienteAuto();
			//String tipo = tfTipo.getText();
			//String datos = clientejdbc.ComsultarTipo(tipo);
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
                    resultado = clientejdbc.actualizarSeguro(datos);
                    
                    // 4. Desplegar resultado de la transaccion
                    taDatos.setText(resultado);
                    }
                }
                tfPlacas.setEditable(true);
                activarBotones();
                }
                
                if(e.getSource() == bCancelar)
		{
			taDatos.setText("Transaccion cancelada...");
			activarBotones();
                        tfPlacas.setEditable(true);
		}
		if(e.getSource() == bSalir)
			//new MenuGUI();
                    setVisible(false);
	}
	
	public static void main(String args[])
	{
            //AccidentesGUI segurosGUI = new AccidentesGUI();
	}
}
