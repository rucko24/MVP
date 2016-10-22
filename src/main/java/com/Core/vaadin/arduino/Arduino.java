package com.Core.vaadin.arduino;

import com.vaadin.ui.Notification;
import jssc.SerialPort;
import jssc.SerialPortException;

public class Arduino {

	private SerialPort serialPort;
	
	public Arduino() {
		
		serialPort = new SerialPort("/dev/ttyACM0");
		
		try {
			
			System.out.println("Puerto Abierto: "+serialPort.openPort());
			System.out.println("Params setted: " + serialPort.setParams(9600, 8, 1, 0));
            
		}catch(SerialPortException ex ) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void enviarDato( String datos ) {
	       
        try {
        	
            serialPort.writeBytes( datos.getBytes() );
        }catch( Exception e ) {
            System.out.println(e.getMessage());
            
        }
    }
		
}
	/*private static final String AMARILLO_OFF = "0"; //APAGAR
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
        
        try {
        while( puertoEnum.hasMoreElements()) {
            CommPortIdentifier actualPuertoID = (CommPortIdentifier) puertoEnum.nextElement();
           
            if(PUERTO.equals(actualPuertoID.getName())) {
                puertoID = actualPuertoID;
                break;
            }
        }
        }catch(UnsatisfiedLinkError e) {
        	Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
        }catch(ExceptionInInitializerError e) {
        	Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
        if( puertoID == null ) {
            Notification.show("Puerto en uso", Notification.Type.ERROR_MESSAGE);
            return;
        }
	        try {
	            serialPort = (SerialPort) puertoID.open( this.getClass().getName(), TIME_OUT );
	            serialPort.setSerialPortParams(DATA_RATE,SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
	            outPut = serialPort.getOutputStream();
	        } catch( Exception e) {
	           Notification.show(e.getMessage());
	        }
    }
	
    public void enviarDato( String datos ) {
       
        try {
        	
            outPut.write( datos.getBytes() );
        }catch( Exception e ) {
            Notification.show(e.getMessage());
            
        }
    }
    */
  