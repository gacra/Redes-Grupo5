package servidor_redes;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Servidor implements Runnable{   
    int num_urna;
    int urnas_fechadas;
    Candidatos candidatos;
    Placar placar;
    ServerSocket server = null;
    
    public Servidor(){
        this.num_urna = 0;
        this.urnas_fechadas = 0;
    }
    
    public synchronized int novaUrna(){
        num_urna++;
        return num_urna;
    }
    
    public synchronized int urnaFechada(){
        urnas_fechadas++;
        return urnas_fechadas;
    }

    public int getNum_urna(){
        return num_urna;
    }
    
    public void terminar() throws IOException{
        server.close();
    }

    @Override
    public void run(){    
        try{
            server = new ServerSocket(40105, 100);
            
            //Loop para conectar aos clientes
            while (true) {
                //System.out.println("Aguardando conexões...");
                Socket socket = socket = server.accept();
                Comunicador urna = new Comunicador(socket, this);

                Thread t = new Thread(urna);
                t.start();
                t.join();
            }
        
         }catch(IOException | InterruptedException ex){}
    }
    
}
