package cliente_redes;

import Comum.Candidato;
import java.util.HashMap;

/**
 * Classe que representa a urna.
 */
public class Urna{
    private int num_urna;       //Número da urna
    private int brancos;        //Número de votos em branco
    private int nulos;          //Número de votos nulos
    private int total_votos;    //Número total de votos
    private HashMap<Integer, Candidato> listaCandidatos;    //Lista de candidatos

    public Urna(){
        this.brancos = 0;
        this.nulos = 0;
        this.total_votos = 0;
    }
    
    public void setNum_urna(int num_urna){
        this.num_urna = num_urna;
    }

    public int getNum_urna(){
        return num_urna;
    }

    public int getBrancos(){
        return brancos;
    }

    public int getNulos(){
        return nulos;
    }

    public int getTotal_votos(){
        return total_votos;
    }

    public HashMap<Integer, Candidato> getListaCandidatos(){
        return listaCandidatos;
    }

    public void setListaCandidatos(HashMap<Integer, Candidato> listaCandidatos){
        this.listaCandidatos = listaCandidatos;
    }
    
    /**
     * Econtra, e retorna, o candidato com determinado Código de Votação.
     * @param codigo_votacao
     * @return Candidato.
     */
    public Candidato encontraCandidato(int codigo_votacao){
        return listaCandidatos.get(codigo_votacao);
    }
    
    /**
     * Dá um voto ao canditado.
     * @param candidato
     */
    public void votar(Candidato candidato){
        candidato.votado();
        total_votos++;
    }
    
    /**
     * Vota em branco.
     */
    public void votar_branco(){
        brancos++;
        total_votos++;
    }
    
    /**
     * Vota nulo.
     */
    public void votar_nulo(){
        nulos++;
        total_votos++;
    }
}
