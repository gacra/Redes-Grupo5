package servidor_redes;

import Comum.Candidato;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe principal que contém a main do programa.
 */
public class Servidor_Redes{
   

    public static void main(String[] args) throws IOException, InterruptedException{
        Servidor servidor = new Servidor();
        
        
        // Obtem os dados dos candidatos.
        
        BufferedReader entrada = new BufferedReader(new InputStreamReader((System.in)));
        
        System.out.println("------------------------------------------------------\n"+
                "\t*** SISTEMA DE VOTAÇÃO ELETRÔNICA ***\n"+
                "------------------------------------------------------\n");
        
        System.out.println("Seja Bem-Vindo!!\nPara iniciar...");
        int num_candidatos;
        do{
            System.out.print("Digite o número de candidatos: ");
            try{
                num_candidatos = Integer.parseInt(entrada.readLine());
            }catch(NumberFormatException ex){
                num_candidatos = -1;
            }
        }while(num_candidatos <= 0);
        
        servidor.candidatos = new Candidatos(num_candidatos);
        
        int codigo_votacao;
        String nome_candidato;
        String partido;
        
        for(int i=0; i<num_candidatos; i++){
            System.out.println("\n** Candidato " + (i+1) + ":\n");
            System.out.print("Código de votação: ");
            try{
                codigo_votacao = Integer.parseInt(entrada.readLine());
            }catch(NumberFormatException ex){
                System.out.println("ERRO: O código de votação deve ser um número.\n" +
                        "Use um novo código para o candidato " + (i+1) + ".\n");
                i--;
                continue;
            }
            System.out.print("Nome do candidato: ");
            nome_candidato = entrada.readLine();
            System.out.print("Partido: ");
            partido = entrada.readLine();
            Candidato novoCandidato = new Candidato(codigo_votacao, nome_candidato, partido);
            
            if(codigo_votacao<1 || codigo_votacao>99){
                System.out.println("ERRO: O código de votação deve ter dois dígitos.\n" +
                        "Use um novo código para o candidato " + (i+1) + ".\n");
                i--;
            }else if(servidor.candidatos.addCandidato(novoCandidato) == false){
                System.out.println("\nERRO: O código de votação já foi usado por outro candidato.\n"+
                        "Use um novo código para o candidato " + (i+1) + ".\n");
                i--;
            }
        }
        
        servidor.candidatos.listaParaUrnas();
        servidor.placar = new Placar(servidor.candidatos);
        
        System.out.println("\nPARABÉNS: Os candidatos já foram cadastrados. As urnas já podem iniciar a votação.\n");
        System.out.println("O resultado parcial será mostrado em intervalos de 10 segundos.\n");
        System.out.println("Digite 'q' seguido de ENTER a qualquer momento para encerrar a votação.\n");
        
        //Inicia as threads de placar e de aceitação das conexões dos clientes.
        
        Thread placar_t = new Thread(servidor.placar);
        placar_t.start();
        
        Thread servidor_t = new Thread(servidor);
        servidor_t.start();
        
        //Caso o usuário deseje terminar a votação, interrompe as threads e imprime o placar final.
        
        do{  
            servidor.placar.pausar(false);
            while(!entrada.readLine().equals("q"));
            servidor.placar.pausar(true);
            System.out.printf("%.2f", (servidor.getUrnas_fechadas()*100.0)/(servidor.getNum_urna()) );  
                        System.out.println("% das urnas fechadas.\n"+
                                "Se deseja realmente encerrar, digite novamente 'q' seguido de ENTER.\n" +
                                "Se deseja continuar a votação aperte somente ENTER.");
        }while(!entrada.readLine().equals("q"));

        servidor.terminar();
        placar_t.interrupt();
        
        servidor.placar.placarFinal();
       
    }
    
}
