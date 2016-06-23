package cliente_redes;

import Comum.Candidato;
import java.util.HashMap;

public class Urna{
    private int num_urna;
    private int brancos;
    private int nulos;
    private int total_votos;
    private HashMap<Integer, Candidato> listaCandidatos;

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
        this.imprime();
    }
    
    public Candidato encontraCandidato(int codigo_votacao){
        return listaCandidatos.get(codigo_votacao);
    }
    
    public void votar(Candidato candidato){
        candidato.votado();
        total_votos++;
    }
    
    public void votar_branco(){
        brancos++;
        total_votos++;
    }
    
    public void votar_nulo(){
        nulos++;
        total_votos++;
    }
    
    //Retirar ao final
    public void imprime(){
        for(Integer codCandidato  : listaCandidatos.keySet()){
                Candidato candidato = listaCandidatos.get(codCandidato);
                System.out.println(candidato.toString());
        }
        System.out.println("Brancos: " + brancos);
        System.out.println("Nulos " + nulos);
    }
}
