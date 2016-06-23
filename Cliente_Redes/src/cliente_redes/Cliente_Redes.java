package cliente_redes;

import java.io.IOException;


public class Cliente_Redes{

    public static void main(String[] args) throws IOException{
        int num_urna;
        
        System.out.println("Conectando.");
        Comunicador comunicador = new Comunicador(); 
        comunicador.conectar();
        num_urna = comunicador.pConexao();
        System.out.println("Conectado. Sou a urna: " + num_urna);               
    }
    
}
