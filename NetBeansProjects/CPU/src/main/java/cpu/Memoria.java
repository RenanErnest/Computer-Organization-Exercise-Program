package cpu;
import java.util.*;
public class Memoria {
  LinkedList<String> ListaComando = new LinkedList<String>();
  
//Inserir na memoria usando a Linha da memoria e o Upcode da operaçao
  public void InserirMemoria(int indice,String code){
   ListaComando.add(indice,code); 
  }   
//Retorna o Upcode que ta na memoria de acordo com o Indice
 public String RetornarMemoria(int indice){
  return ListaComando.get(indice); 
 }
}