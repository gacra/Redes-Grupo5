package servidor_redes;

import Comum.Candidato;
import java.util.HashMap;

/**
 *Classe que contém o conjunto de todos os candidatos, bem como os votos brancos 
 *e nulos. 
 */
public class Candidatos{
    private int num_candidatos; //Número de candidatos.
    private int brancos;        //Número de votos brancos.
    private int nulos;          //Número de votos nulos.
    private int total_votos;    //Número total de votos
    private HashMap<Integer, Candidato> listaCandidatos;        //Lista de candidatos.
    private HashMap<Integer, Candidato> listaCandidatos_urnas;  //Lista de candidatos para enviar às urnas.

    /**
     *
     * @param num_candidatos Número de candidatos.
     */
    public Candidatos(int num_candidatos){
        this.num_candidatos = num_candidatos;
        this.brancos = 0;
        this.nulos = 0;
        this.total_votos = 0;
        this.listaCandidatos = new HashMap<>();
        this.listaCandidatos_urnas = new HashMap<>();
    }
    
    /**
     *
     * @param codVot Código de votação do candidato.
     * @return true se já há um candidato com tal cógigo; false, caso contrário.
     */
    public boolean estaNaLista(Integer codVot){
        return listaCandidatos.containsKey(codVot);
    }
    
    /**
     *Adiciona um novo candidato à lista de candidatos.
     * @param novoCandidato Candidato a ser adicionado.
     * @return true se o candidato foi adicionado; false caso contrário.
     */
    public boolean addCandidato(Candidato novoCandidato){
        if(listaCandidatos.containsKey(novoCandidato.getCodigo_votacao())){
            return false;
        }else{
            listaCandidatos.put(novoCandidato.getCodigo_votacao(), novoCandidato);
            return true;
        }
    }

    public int getTotal_votos(){
        return total_votos;
    }

    public int getBrancos(){
        return brancos;
    }

    public int getNulos(){
        return nulos;
    }
    
    public HashMap<Integer, Candidato> getListaCandidatos(){
        return listaCandidatos;
    }

    public HashMap<Integer, Candidato> getListaCandidatos_urnas(){
        return listaCandidatos_urnas;
    }
    
    /**
     *Gera listaCandidatos_urnas, que é um clone completo de listaCandidatos
     * (clonando inclusive os objetos Candidatos).
     */
    public void listaParaUrnas(){
        for(Integer codCandidato  : listaCandidatos.keySet()){
                Candidato candidato = listaCandidatos.get(codCandidato);
                Candidato candidatoClone;
            try{
                candidatoClone = candidato.clone();
                listaCandidatos_urnas.put(candidatoClone.getCodigo_votacao(), candidatoClone);
            }catch(CloneNotSupportedException ex){}
        }
    }
    
    /**
     *Faz a apuração dos resultados de uma urna.
     * @param resultadoUrna Lista de candidatos enviada pela urna.
     * @param brancos Número de votos brancos enviados pela urna.
     * @param nulos Número de votos nulos enviados pela urna.
     */
    public synchronized void apuracao(HashMap<Integer, Candidato> resultadoUrna, int brancos, int nulos){
        for(Integer codCandidato  : resultadoUrna.keySet()){
            Candidato candidato_urna = resultadoUrna.get(codCandidato);
            Candidato candidato = listaCandidatos.get(candidato_urna.getCodigo_votacao());
            candidato.acresVotos(candidato_urna.getNum_votos());
            this.total_votos += candidato_urna.getNum_votos();
        }
        this.brancos += brancos;
        this.nulos += nulos;
        this.total_votos += (brancos+nulos);
    }
    
    @Override
    public synchronized String toString(){
        String texto = "";
        float porcento;
        
        for(Integer codCandidato  : listaCandidatos.keySet()){
                Candidato candidato = listaCandidatos.get(codCandidato);
                texto = texto.concat(candidato.toString());
                porcento = calcPorcento(candidato.getNum_votos(), total_votos);
                texto = texto.concat("Porcentagem dos votos: " + String.format("%.2f", porcento) + "% ");
                texto = texto.concat(porcGraf(porcento));  
        }
        texto = texto.concat("\n\nNúmero de votos brancos: " + brancos + "\n");
        porcento = calcPorcento(brancos, total_votos);
        texto = texto.concat("Porcentagem dos votos: " + String.format("%.2f", porcento) + "% ");
        texto = texto.concat(porcGraf(porcento));  
        texto = texto.concat("\n\nNúmero de votos nulos: " + nulos + "\n");
        porcento = calcPorcento(nulos, total_votos);
        texto = texto.concat("Porcentagem dos votos: " + String.format("%.2f", porcento) + "% ");
        texto = texto.concat(porcGraf(porcento));  
        texto = texto.concat("\n\n** Número total de votos: " + total_votos + "\n");
        return  texto;
    }
    
     /**
     * Calcula a porcentagem de determinado número de votos em relação ao total.
     * @param votos Número de votos
     * @param t_votos Número total de votos
     * @return Porcentagem.
     */
    private float calcPorcento(int votos, int t_votos){
        float resultado;
        resultado = (votos * 100.0f/t_votos);
        if(Float.isNaN(resultado)){
            resultado = 0;
        }
        return resultado;                
    }
    
     /**
     * Gera uma String de traços (||||), que representam graficamente uma procentagem.
     * @param porcento Porcentagem.
     * @return A String de traços.
     */
    private String porcGraf(float porcento){
        String texto = "";
        for(int i = 0; i<(int)porcento; i++){
            texto = texto.concat("|");
        }
        return texto;
    }
      
}
