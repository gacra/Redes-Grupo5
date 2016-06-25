package cliente_redes;

import Comum.Candidato;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

/**
 * Responsável pela comunicação do lado cliente.
 */
public class Comunicador implements Runnable{
    Socket socket;
    ObjectOutputStream output;
    ObjectInputStream input;
    Urna urna;
    IntGrafConect igConect; //Janela inicial de conexão

    /**
     *
     * @param urna Urna
     * @param igConect Janela inicial de conexão
     */
    public Comunicador(Urna urna, IntGrafConect igConect){
        this.urna = urna;
        this.igConect = igConect;
    }
    
    /**
     *Estabelece uma conexão com o servidor.
     * 
     * @return Se a conexão foi estabelecida. 
     */
    public boolean conectar(){
        try{
            this.socket = new Socket("cosmos.lasdpc.icmc.usp.br", 40105);
            this.input = new ObjectInputStream(socket.getInputStream());
            this.output = new ObjectOutputStream(socket.getOutputStream());
            return true;
        }catch(IOException ex){
            return false;
        }
    } 
    
    /**
     * Finaliza a conexão com o servidor. 
     */
    private void desconectar() throws IOException{
        input.close();
        output.close();
        socket.close();
    }
    
    /**
     * Primeira conexão com o servidor (codOp = 999). 
     * Recebe o número da urna e a lista de candidatos.
     *
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public void pConexao() throws IOException, ClassNotFoundException{
        this.output.writeObject(Integer.valueOf(999));
        urna.setNum_urna((Integer)this.input.readObject());        
        urna.setListaCandidatos((HashMap<Integer, Candidato>) this.input.readObject());
        
        this.desconectar();
    }
    
    /**
     * Segunda conexão com o servidor.
     * Envia o próprio número, a lista de candidatos (coms seus respectivos votos),
     * e o número de votos brancos e nulos.
     * @throws IOException
     */
    public void sConexao() throws IOException{
        this.output.writeObject(Integer.valueOf(888));
        this.output.writeObject(urna.getNum_urna());
        this.output.writeObject(urna.getListaCandidatos());
        this.output.writeObject(urna.getBrancos());
        this.output.writeObject(urna.getNulos());
        
        this.desconectar();
    }

     /**
     * Thread responsável pela conexão inicial. Atualiza o progresso na janela
     * inicial.
     */
    @Override
    public void run(){
        int naoConect = 0;
        
        try{
            igConect.texto(1);
            while(!conectar()){
                naoConect++;
                if(naoConect == 10){
                    igConect.texto(3);
                }
            };
            igConect.texto(2);
            pConexao();
            igConect.texto(4);
        }catch(InterruptedException | IOException | ClassNotFoundException ex){}
        }
}
