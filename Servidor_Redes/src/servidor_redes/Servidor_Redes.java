package servidor_redes;

import Comum.Candidato;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor_Redes{

   
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws IOException, InterruptedException{
        Servidor servidor = new Servidor();
        
        BufferedReader entrada = new BufferedReader(new InputStreamReader((System.in)));
        
        System.out.println("-----------------------------------------------------\n"+
                "\t*** SISTEMA DE VOTAÇÃO ELETÔNICA ***\n"+
                "-----------------------------------------------------\n");
        
        System.out.println("Seja Bem-Vindo!!\nPara iniciar...");
        System.out.print("Digite o número de candidatos: ");
        int num_candidatos = Integer.parseInt(entrada.readLine());
        servidor.candidatos = new Candidatos(num_candidatos);
        
        int codigo_votacao;
        String nome_candidato;
        String partido;
        
        for(int i=0; i<num_candidatos; i++){
            System.out.println("\n** Candidato " + (i+1) + ":\n");
            System.out.print("Código de votação: ");
            codigo_votacao = Integer.parseInt(entrada.readLine());
            System.out.print("Nome do candidato: ");
            nome_candidato = entrada.readLine();
            System.out.print("Partido: ");
            partido = entrada.readLine();
            Candidato novoCandidato = new Candidato(codigo_votacao, nome_candidato, partido);
            
            if(servidor.candidatos.addCandidato(novoCandidato) == false){
                System.out.println("\nERRO: O código de votação já foi usado por outro candidato.\n"+
                        "Use um novo código para o candidato " + (i+1) + "\n");
                i--;
            }
        }
        
        servidor.candidatos.listaParaUrnas();
        servidor.placar = new Placar(servidor.candidatos);
        
        Thread placar_t = new Thread(servidor.placar);
        placar_t.start();
        
        System.out.println("\nPARABÉNS: Os candidatos já foram cadastrados. As urnas já podem iniciar a votação.\n");
        System.out.println("O resultado parcial será mostrado em intervalos de 10 segundos.\n");
        System.out.println("Digite 'q' seguido de ENTER a qualquer momento para encerrar a votação.\n");
        
        Thread servidor_t = new Thread(servidor);
        servidor_t.start();
        
        while(entrada.read() != 'q');  
        
        Thread.sleep(5000);
        servidor.terminar();
        placar_t.interrupt();
        
        servidor.placar.placarFinal();
       
    }
    
}
