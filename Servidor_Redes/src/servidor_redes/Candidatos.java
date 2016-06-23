package servidor_redes;

import Comum.Candidato;
import java.util.HashMap;

public class Candidatos{
    private int num_candidatos;
    private int brancos;
    private int nulos;
    private int total_votos;
    private HashMap<Integer, Candidato> listaCandidatos;
    private HashMap<Integer, Candidato> listaCandidatos_urnas;

    public Candidatos(int num_candidatos){
        this.num_candidatos = num_candidatos;
        this.brancos = 0;
        this.nulos = 0;
        this.total_votos = 0;
        this.listaCandidatos = new HashMap<>();
        this.listaCandidatos_urnas = new HashMap<>();
    }
    
    public boolean estaNaLista(Integer codVot){
        return listaCandidatos.containsKey(codVot);
    }
    
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
      
}
