package servidor_redes;

import Comum.Candidato;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Classe responsável por mostrar o placar da votação.
 * 
 */
public class Placar implements Runnable{
    private Candidatos candidatos;
    private HashMap<Integer, Candidato> listaCandidatos;
    private boolean pausa = false;  //Indica de a atualização do placar está pausada.

    /**
     *
     * @param candidatos
     */
    public Placar(Candidatos candidatos){
        this.candidatos = candidatos;
        this.listaCandidatos = this.candidatos.getListaCandidatos();
    }
    
    /**
     * Placar mostrado ao final na votação.
     */
    public void placarFinal(){
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
        Date data = new Date();
        String dataFormat = formato.format(data);
        
        System.out.println("\n------------------------\n"+
                "*** RESULTADO FINAL ***\n" +
                "------------------------\n");
        System.out.println("Votação encerrada às " + dataFormat + ".");
        System.out.println(candidatos.toString());
    }
    
    /**
     * Pausa ou "despausa" a atualização do placar na tela.
     * @param pausa Se true, pausa a votação. Se false, "despausa".
     */
    public void pausar(boolean pausa){
        this.pausa = pausa;
    }
    
     /**
     * Thread responsável por imprimir o placar na tela.
     */
    @Override
    public void run(){
        
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
         try{
            while(true){   

                Thread.sleep(10000);

                while(pausa){
                    Thread.sleep(500);
                    pausa = pausa;
                }
                Date data = new Date();
                String dataFormat = formato.format(data);
                System.out.print("\n------------------------------------\n" + ""
                    + "Atualização às " + dataFormat + 
                    "\n------------------------------------");
                System.out.println(candidatos.toString());

            }
        }catch(InterruptedException ex){
                return;
        }
    }
    
}
