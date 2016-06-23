package cliente_redes;

import Comum.Candidato;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class Comunicador{
    Socket socket;
    ObjectOutputStream output;
    ObjectInputStream input;
    Urna urna;

    public Comunicador(Urna urna){
        this.urna = urna;
    }
    
    /**
     *Estabelece uma conexão com o servidor.
     * 
     * @return Se a conexão foi estabelecida. 
     */
    public boolean conectar(){
        try{
            this.socket = new Socket("127.0.0.1", 40105);
            this.input = new ObjectInputStream(socket.getInputStream());
            this.output = new ObjectOutputStream(socket.getOutputStream());
            return true;
        }catch(IOException ex){
            return false;
        }
    } 
    
    /**
     *Finaliza a conexão com o servidor.
     * @param 
     * @return  
     */
    private void desconectar() throws IOException{
        input.close();
        output.close();
        socket.close();
    }
    
    /**
     *Primeira conexão com o servidor (codOp = 999). 
     * Recebe o número da urna e a lista de candidatos.
     *
     */
    public void pConexao() throws IOException, ClassNotFoundException{
        this.output.writeObject(Integer.valueOf(999));
        urna.setNum_urna((Integer)this.input.readObject());
        System.out.println("Conectado. Sou a urna: " + urna.getNum_urna()); 
        
        urna.setListaCandidatos((HashMap<Integer, Candidato>) this.input.readObject());
        
        this.desconectar();
    }
    
    public void sConexao() throws IOException{
        this.output.writeObject(Integer.valueOf(888));
        this.output.writeObject(urna.getNum_urna());
        this.output.writeObject(urna.getListaCandidatos());
        this.output.writeObject(urna.getBrancos());
        this.output.writeObject(urna.getNulos());
        System.out.println("Urna " + urna.getNum_urna() + " encerrando e enviando dados.");
        
        this.desconectar();
    }
}
