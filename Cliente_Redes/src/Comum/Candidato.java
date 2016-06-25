package Comum;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

    /**
     *Classe que representa cada candidato da votação.
     *Presente no projeto cliente e no servidor, pois os objetos dessa classe
     * são trocados entre os dois por meio da rede.
     */
public class Candidato implements Serializable, Cloneable{
    private final int codigo_votacao;
    private final String nome_candidato;
    private final String partido;
    private int num_votos;  //Número de votos do candidato.

    /**
     *Construtor da Classe Candidato. 
     * @param codigo_votacao Código de votação do candidato.
     * @param nome_candidato Nome do candidato.
     * @param partido Partido da candidato.
     */
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
     * Acrescenta votos ao candidato.
     * @param num Número de votos a serem acrescentados no total do candidato.
     */
    public synchronized void acresVotos(int num){
        num_votos += num;
    }  
    
    @Override
    public String toString(){
        NumberFormat formatter = new DecimalFormat("00");
        String sCod = formatter.format(codigo_votacao);
        return  "\n\nCódigo de votação: " + sCod  + "\nNome do candidato: " + nome_candidato
                + "\nPartido: " + partido + "\nNúmero de votos: " + num_votos + "\n";
    }
    
    @Override
    public Candidato clone() throws CloneNotSupportedException {
        return (Candidato) super.clone();
    }
    
    /**
     *Acrescenta um único voto ao candidato
     */
    public void votado(){
        num_votos++;
    }
}
