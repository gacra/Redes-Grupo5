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
    
    public void placarFinal(){
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        Date data = new Date();
        String dataFormat = formato.format(data);
        
        System.out.println("\n------------------------\n"+
                "*** RESULTADO FINAL ***\n" +
                "------------------------\n");
        System.out.println("Votação encerrada às " + dataFormat + ".");
        System.out.println(candidatos.toString2());
    }
    
    @Override
    public void run(){
        
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        
        while(true){   
            try{
                Thread.sleep(10000);
            }catch(InterruptedException ex){
                return;
            }
            Date data = new Date();
            String dataFormat = formato.format(data);
            System.out.println("\n------------------------------------\n" + ""
                    + "Atualização às " + dataFormat + 
                    "\n------------------------------------");
            System.out.println(candidatos.toString());
        }
    }
    
}
