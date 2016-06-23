package Comum;

import java.io.Serializable;

public class Candidato implements Serializable, Cloneable{
    private final int codigo_votacao;
    private final String nome_candidato;
    private final String partido;
    private int num_votos;

    public Candidato(int codigo_votacao, String nome_candidato, String partido){
        this.codigo_votacao = codigo_votacao;
        this.nome_candidato = nome_candidato;
        this.partido = partido;
        this.num_votos = 0;
    }

    public int getCodigo_votacao(){
        return codigo_votacao;
    }

    public String getNome_candidato(){
        return nome_candidato;
    }

    public String getPartido(){
        return partido;
    }

    public int getNum_votos(){
        return num_votos;
    }
    
    /**
     *
     * @param num Número de votos a serem acrescentados no total do candidato.
     */
    public synchronized void acresVotos(int num){
        num_votos += num;
    }  
    
    @Override
    public String toString(){
        return  "\n\nCódigo de votação: " + codigo_votacao + "\nNome do candidato: " + nome_candidato
                + "\nPartido: " + partido + "\nNúmero de votos: " + num_votos + "\n";
    }
    
    @Override
    public Candidato clone() throws CloneNotSupportedException {
        return (Candidato) super.clone();
    }
    
    public void votado(){
        num_votos++;
    }
}
