//java server port_di_ascolto			(es.  java server 8000)
import java.net.*;
import java.io.*;

public class server_invia_sempre
{
	public static void main(String s[]) 
	{
		ServerSocket canale;
		Socket socket;
		DataInputStream in;
		PrintStream out;
		
		InputStreamReader input=new InputStreamReader(System.in);
		BufferedReader tastiera= new BufferedReader(input);
		String frase=new String("");
		
		try {
			canale=new ServerSocket(Integer.parseInt(s[0]));
			System.out.println("In ascolto di connessioni su port "+s[0]+"...");
			socket=canale.accept();
			System.out.println("Connesso. In attesa di leggere dal socket ...");
			in=new DataInputStream(socket.getInputStream());
			out=new PrintStream(socket.getOutputStream());
			miothread t=new miothread(out);
			t.start();
			while(frase.compareTo("#")!=0)
			{
				frase=in.readLine();
				if (frase!=null)
					System.out.println(frase);
			 }
			socket.close();
		} catch (IOException e) {System.out.println(""+e);}
	}
}

class miothread extends Thread
{
	int i=0;
	PrintStream out;
	public miothread(PrintStream out )	//costruttore
	{
		this.out=out;
	}
	public void run()	//equivale a un main
	{	
		while(true)
		{
						    out.println("Numero "+i);
							i++;
							//$$$$$$$$$$$$ MODIFICA $$$$$$$$$$$
		}
	}

}
		