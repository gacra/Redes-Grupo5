package servidor_redes;

import Comum.Candidato;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class Comunicador implements Runnable{
    private final Socket socket;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;
    private final Servidor_Redes servidor;
    HashMap<Integer, Candidato> listaCandidatos_urnas;

    public Comunicador(Socket socket, Servidor_Redes servidor) throws IOException{
        this.socket = socket;
        this.servidor = servidor;
        this.output = new ObjectOutputStream(socket.getOutputStream());
        this.input = new ObjectInputStream(socket.getInputStream());
        this.listaCandidatos_urnas = this.servidor.candidatos.getListaCandidatos_urnas();
    }
    
    @Override
    public void run() {
        int num_urna;
        
        try{
            Integer codOp = (Integer) input.readObject();
            if(codOp == 999){
                num_urna = servidor.novaUrna();
                System.out.println("Urna nº " + num_urna + " conectou.");
                output.writeObject(Integer.valueOf(num_urna));
                output.writeObject(listaCandidatos_urnas);
            }else if(codOp == 888){
                num_urna = (Integer) input.readObject();
                System.out.println("Urna nº " + num_urna + " enviou seus dados");
                servidor.candidatos.apuracao((HashMap<Integer, Candidato>)input.readObject(), 
                        (Integer) input.readObject(), (Integer) input.readObject());
                System.out.println("Dados computados.");
            }else{
                System.out.println("codOp inválido.");
            }
        }catch(IOException | ClassNotFoundException ex){
            System.out.println("Erro ao ler codOp.");
        }finally{
            try{
                input.close();
                output.close();
                socket.close();
            }catch(IOException ex){}
        }
    }

}
