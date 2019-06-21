package cpu;
import java.util.*;
public class Memoria {
 private static String buffer;
 private static int endereco;
 private static boolean addressValid;
 LinkedList<String> ListaComando = new LinkedList<String>();
  
public static void setBuffer(String a){
        buffer = a;
}
public static void setEndereco(int a){
       endereco = a;
}
public static void setAddressValid(boolean a){
       addressValid = a;
}
public static int getEndereco() {
        return endereco;
    }
public static String getBuffer() {
        return buffer;
    }
public static boolean getAddressValid() {
        return addressValid;
} 
 
  
//Inserir na memoria usando a Linha da memoria e o Upcode da operaçao
  public void InserirMemoria(int indice,String code){
   ListaComando.add(indice,code); 
  }   
//Retorna o Upcode que ta na memoria de acordo com o Indice
 public String RetornarMemoria(int indice){
  return ListaComando.get(indice); 
 }


 
}