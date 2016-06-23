package cliente_redes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Comunicador{
    Socket socket;
    ObjectOutputStream output;
    ObjectInputStream input;
    
    /**
     *Estabelece uma conexão com o servidor.
     * @param 
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
     * @return Número da urna.
     */
    public int pConexao() throws IOException, ClassNotFoundException{
        Integer num_urna;
        this.output.writeObject(Integer.valueOf(999));
        num_urna = (Integer)this.input.readObject();
        this.desconectar();
        return num_urna;
    }
}
