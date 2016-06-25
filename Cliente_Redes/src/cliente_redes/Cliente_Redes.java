package cliente_redes;

import java.io.IOException;

/**
 * Classe principal do cliente.
 */
public class Cliente_Redes{

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
        Urna urna = new Urna(); //Inicia uma urna.
        
        //Cria uma janela principal e passa a urna como parâmetro.
        IntGrafUrna igUrna = new IntGrafUrna(urna);
        igUrna.setVisible(true);        
    }
    
}
