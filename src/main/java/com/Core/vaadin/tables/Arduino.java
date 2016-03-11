package com.Core.vaadin.tables;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.vaadin.ui.Notification;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class Arduino {
	
	private static final String AMARILLO_OFF = "0"; //APAGAR
	private static final String AMARILLO_ON = "1";  //ENCENDER
	
	private static final String ROJO_OFF = "2"; //APAGAR
	private static final String ROJO_ON = "3";  //ENCENDER

	//variable de conexcion con RX/TX
	
	private static OutputStream outPut = null;
	SerialPort serialPort;
	private static String PUERTO = "/dev/ttyS0";
	
	private static final int TIME_OUT = 2000; // milisegundos;
	private static final int DATA_RATE = 9600;
	private static final int ERROR = 0;
	
	
	Scanner teclado = new Scanner(System.in);
	
	public Arduino() {
		
		initComponentes();
	}
	
	public void initComponentes() {
		
		CommPortIdentifier puertoID = null;
        Enumeration<?> puertoEnum = CommPortIdentifier.getPortIdentifiers();
        
        while( puertoEnum.hasMoreElements()) {
            CommPortIdentifier actualPuertoID = (CommPortIdentifier) puertoEnum.nextElement();
           
            if(PUERTO.equals(actualPuertoID.getName())) {
                puertoID = actualPuertoID;
                break;
            }
        }
        if( puertoID == null ) {
            mostrarError("NO SE PUEDE CONECTAR AL PUERTO");
            System.exit( 0 );
            return;
        }
        try {
            serialPort = (SerialPort) puertoID.open( this.getClass().getName(), TIME_OUT );
            serialPort.setSerialPortParams(DATA_RATE,SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            outPut = serialPort.getOutputStream();
        } catch( Exception e) {
            mostrarError(e.getMessage());
            System.exit( 0 );
        }
    }
    public static void enviarDato( String datos ) {
       
        try {
            outPut.write( datos.getBytes() );
        }catch( Exception e ) {
            mostrarError("ERROR");
            System.exit(0);
        }
    }
    
    public static void mostrarError( String mensaje ) {
        
      //  JOptionPane.showMessageDialog(this, mensaje,"ERROR",JOptionPane.ERROR_MESSAGE);
    	System.out.println(mensaje);
    	Notification.show("ERROR AL ABRI EL PUERTO "+mensaje);
    }
	
}