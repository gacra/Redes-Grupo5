package servidor_redes;

import Comum.Candidato;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Placar implements Runnable{
    Candidatos candidatos;
    HashMap<Integer, Candidato> listaCandidatos;

    public Placar(Candidatos candidatos){
        this.candidatos = candidatos;
        this.listaCandidatos = this.candidatos.getListaCandidatos();
    }

    
    
    @Override
    public void run(){
        
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        
        while(true){   
            try{
                Thread.sleep(10000);
            }catch(InterruptedException ex){}
            Date data = new Date();
            String dataFormat = formato.format(data);
            System.out.println("\n\nAtualização às " + dataFormat + ":");
            for(Integer codCandidato  : listaCandidatos.keySet()){
                Candidato candidato = listaCandidatos.get(codCandidato);
                System.out.print(candidato.toString());
            }
            System.out.println("\nNúmero de votos brancos: " + candidatos.getBrancos());
            System.out.println("Número de votos nulos: " + candidatos.getNulos());
            System.out.println("Número total de votos: " + candidatos.getTotal_votos());
            
        }
    }
    
}
