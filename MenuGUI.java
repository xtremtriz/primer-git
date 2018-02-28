/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguros;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.*;

/**
 *
 * @author Uriel
 */
public class MenuGUI extends JFrame implements ActionListener{
    private JMenuBar mbMenu;
    private JMenu mRegistro, mConsulta;
    private JMenuItem miCliente, miAuto, miSeguro,miConsultar, miSalir;
    
    private JPanel panel1, panel2,panelG;
    
    private ClienteGUI clienteGUI;  
    private AutoGUI autoGUI;
    private AccidentesGUI segurosGUI;
    private ConsultasGUI consultasGUI;
    //public JFrame frame;
    
    public MenuGUI(){
        super("MenuGUI");
        
        //frame = new JFrame();
        mbMenu = new JMenuBar();
        mRegistro = new JMenu("Registros: ");
        mConsulta = new JMenu("Consultas: ");
                
        miCliente = new JMenuItem("Registrar Cliente: ");
        miAuto = new JMenuItem("Registrar Auto");
        miSeguro = new JMenuItem("Registrar accidente: ");
        miConsultar = new JMenuItem("Consultar ");
        miSalir = new JMenuItem("Salir");
        
        panel1 = new JPanel();
        panel2 = new JPanel();
        
        miCliente.addActionListener(this);
        miAuto.addActionListener(this);
        miSeguro.addActionListener(this);
        miConsultar.addActionListener(this);
        miSalir.addActionListener(this);
                
        //frame.setJMenuBar(mbMenu);
        
        mRegistro.add(miCliente);
	mRegistro.add(miAuto);
        mRegistro.add(miSeguro);
        mRegistro.add(miSalir);
        mConsulta.add(miConsultar);
                
        mbMenu.add(mRegistro);
	mbMenu.add(mConsulta);
	setJMenuBar(mbMenu);
        
        /*frame.setVisible(true);
        frame.setSize(550,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
        
        panel1.setLayout(new GridLayout(1,1));
	setSize(500,500);
	setVisible(true);
	panel2.setLayout(new FlowLayout());
    }  
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == miCliente){			
            clienteGUI = new ClienteGUI();
            
            //cleanPanel();
            /*panel1.add(clienteGUI);
            add(panel1);
            setVisible(true);*/
	}
        if(e.getSource() == miAuto){			
            autoGUI = new AutoGUI();
            
            //cleanPanel();
        }
        if(e.getSource() == miSeguro){			
            segurosGUI = new AccidentesGUI();
            
            //cleanPanel();
        }
        if(e.getSource() == miConsultar){			
            consultasGUI = new ConsultasGUI();
            
            //cleanPanel();
        }
        if(e.getSource() == miSalir){
            System.exit(0);
            
        }
    }
    private void cleanPanel(){
		panel1.removeAll();
		panel1.repaint();
	}
    public static void main(String[] args) {
        new MenuGUI();
    }

}
