// aggiunto commento

import java.net.*;
import java.io.*;

public class Client {
	
	public static void main(String[] args) {
		
		String frase = "";
		PrintStream out;
		Socket socket;
		BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
		String IP_Dest, portDest;
		
		try {
			System.out.println("Indirizzo IP destinatario: ");
			IP_Dest = tastiera.readLine();
			System.out.println("Port destinatario: ");
			portDest = tastiera.readLine();
			socket = new Socket(IP_Dest, Integer.parseInt(portDest));
			
			//Partenza dei thread
			Thread ricevi = new Ricezione(socket);
			ricevi.start();
			
			out = new PrintStream(socket.getOutputStream());
			while (frase.compareTo("#") != 0) {
				frase = tastiera.readLine();
				out.println(frase);
			}
			
			//Invio e ricezione di messaggi...
		
			try { 
				ricevi.join();
			}
			catch (Exception e) {}
			
			socket.close();
		}
		catch(IOException e) {}
	}
}

//Thread per la ricezione dei messaggi dal server
class Ricezione extends Thread {
	
	private String risposta = "";
	private Socket socket;
	private BufferedReader datiIN;
	
	public Ricezione(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		
		try {
			datiIN = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (risposta.compareTo("#") != 0) {
				risposta = datiIN.readLine();
				System.out.println("Server > " + risposta);
			}
		}
		catch (IOException e) {}
	}
}
