package cliente_redes;

import Comum.Candidato;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Cliente_Redes{

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Urna urna = new Urna();
        
        System.out.println("Conectando.");
        Comunicador comunicador = new Comunicador(urna); 
        comunicador.conectar();
        comunicador.pConexao();
        
        BufferedReader entrada = new BufferedReader(new InputStreamReader((System.in)));
        
        do{            
            System.out.print("Digite o número da candidato: ");
            Candidato candidato = urna.encontraCandidato(Integer.parseInt(entrada.readLine()));
            if(candidato != null){
                System.out.println(candidato);
                System.out.print("Desaja votar nesse candidato? (s/N)(b/n) ");
                String resp = entrada.readLine();
                if(resp.equals("s")){
                    urna.votar(candidato);
                }else if(resp.equals("b")){
                    urna.votar_branco();
                }else if(resp.equals("n")){
                    urna.votar_nulo();
                }
            }
            urna.imprime();
            System.out.println("Deseja continuar? (s/n)");
        }while(entrada.readLine().equals("s"));
        
        System.out.println("\nVotação encerrada\n");
        
        if(urna.getTotal_votos()>0){
            comunicador.conectar();
            comunicador.sConexao();
        }else{
            System.out.println("Não houve nenhum voto");
        }
        
    }
    
}
