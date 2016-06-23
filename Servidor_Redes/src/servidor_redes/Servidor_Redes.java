package servidor_redes;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Guilherme
 */
public class Servidor_Redes{
    int num_urna;
    
    public Servidor_Redes(){
        this.num_urna = 0;
    }
    
    public synchronized int novaUrna(){
        num_urna++;
        return num_urna;
    }
   
    public static void main(String[] args) throws IOException{
        Servidor_Redes servidor = new Servidor_Redes();
        
        //Servidor aguardando conecções na porta 1500
        ServerSocket server = new ServerSocket(40105, 100);
        
        //Loop para conectar aos clientes
        while (true) {
            System.out.println("Aguardando conexões...");
            Socket socket = server.accept();
            System.out.println("Conectado!");

            Comunicador urna = new Comunicador(socket, servidor);
           
            Thread t = new Thread(urna);
            t.start();
        }
    }
    
}
