package servidor_redes;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe que representa o servidor.
 */
public class Servidor implements Runnable{   
    int num_urna;               //Número de identificação da última urna conectada.
    int urnas_fechadas;         //Número de urnas fechadas.
    Candidatos candidatos;      //Votos
    Placar placar;              //Placar
    ServerSocket server = null; //Socket para receber as requisições.
    
    /**
     * Construtor.
     */
    public Servidor(){
        this.num_urna = 0;
        this.urnas_fechadas = 0;
    }
    
    /**
     * Gera o número de identificação de uma nova urna.
     * @return Número de identificação da nova urna.
     */
    public synchronized int novaUrna(){
        num_urna++;
        return num_urna;
    }
    
    /**
     * Indica que uma urna fechou.
     * @return Número de urnas fechadas.
     */
    public synchronized int urnaFechada(){
        urnas_fechadas++;
        return urnas_fechadas;
    }

    public int getUrnas_fechadas(){
        return urnas_fechadas;
    }

    public int getNum_urna(){
        return num_urna;
    }
    
    /**
     * Chamado ao final da votação, faz com que o servidor para de aceitar requisições.
     * @throws IOException
     */
    public void terminar() throws IOException{
        server.close();
    }

    public Placar getPlacar(){
        return placar;
    }
    
     /**
     * Thread que aceita as requisições das urnas e gera uma thread do objeto 
     * Comunicador para atendê-las.
     */
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
