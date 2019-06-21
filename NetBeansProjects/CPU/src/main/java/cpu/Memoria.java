package cpu;
import java.util.*;

public class Memoria {
    //memoria começa do endereço 0
    private static LinkedList<String> ListaComando = new LinkedList<String>();
    
    private static String buffer = "00000000";
    private static String endereco = "00000000";
    
    private static String addressValid = "0";
    private static String read = "0";
    private static String write = "0";
    
    public static boolean operation(){
        if(addressValid.equals("1")) {
            if (read.equals("1")) buffer = ListaComando.get(Integer.parseInt(endereco,2));
            else if (write.equals("1")) ListaComando.add(Integer.parseInt(endereco,2),buffer);
            return true;
        }
        return false;
    }
    
    public static String getAddressValid() {
        return addressValid;
    }
    public static void setAddressValid(String s){
        addressValid = s;
    }
    public static String getRead() {
        return read;
    }
    public static void setRead(String s){
        read = s;
    }
    public static String getWrite() {
        return write;
    }
    public static void setWrite(String s){
        write = s;
    }
    public static String getEndereco() {
        return endereco;
    }
    public static void setEndereco(String s){
        endereco = s;
    }
    public static String getBuffer() {
        return buffer;
    }
    public static void setBuffer(String s){
        buffer = s;
    }
    

    //Inserir no endereço de memória o opcode
    public static void addInitialCode(int address,String code){
        ListaComando.add(address,code);
        System.out.println(address + " - " + code);
    }   
    
}