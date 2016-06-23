package servidor_redes;

import Comum.Candidato;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor_Redes{
    int num_urna;
    Candidatos candidatos;
    Placar placar;
    
    public Servidor_Redes(){
        this.num_urna = 0;
    }
    
    public synchronized int novaUrna(){
        num_urna++;
        return num_urna;
    }
   
    public static void main(String[] args) throws IOException{
        Servidor_Redes servidor = new Servidor_Redes();
        
        BufferedReader entrada = new BufferedReader(new InputStreamReader((System.in)));
        
        System.out.print("Digite o número de candidatos: ");
        int num_candidatos = Integer.parseInt(entrada.readLine());
        servidor.candidatos = new Candidatos(num_candidatos);
        
        int codigo_votacao;
        String nome_candidato;
        String partido;
        
        for(int i=0; i<num_candidatos; i++){
            System.out.println("\n\nCandidato " + (i+1) + ":");
            System.out.print("Código de votação: ");
            codigo_votacao = Integer.parseInt(entrada.readLine());
            System.out.print("Nome do candidato: ");
            nome_candidato = entrada.readLine();
            System.out.print("Partido: ");
            partido = entrada.readLine();
            Candidato novoCandidato = new Candidato(codigo_votacao, nome_candidato, partido);
            
            if(servidor.candidatos.addCandidato(novoCandidato) == false){
                i--;
            }
        }
        
        servidor.candidatos.listaParaUrnas();
        servidor.placar = new Placar(servidor.candidatos);
        
        Thread placar_t = new Thread(servidor.placar);
        placar_t.start();
        
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
