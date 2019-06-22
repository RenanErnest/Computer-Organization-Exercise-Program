package cpu;
import java.util.*;
import javax.swing.JOptionPane;

public class Memoria {
    //memoria começa do endereço 0
    private static Map<Integer,String> memoria = new HashMap<Integer,String>();
    
    private static String buffer = "0000000000000000";
    private static String endereco = "0000000000000000";
    
    private static String addressValid = "0";
    private static String read = "0";
    private static String write = "0";
    
    public static boolean acabou = false; //verificador de fim de programa (nenhuma instrucao encontrada na memoria)
    
    public static void operation(){
        if(addressValid.equals("1")) {
            if (read.equals("1")) {
                String s = memoria.get(Integer.parseInt(endereco,2));
                if (s == null) {
                    JOptionPane.showMessageDialog(null, "Tentativa de acesso a posição inexistente de memória!", "Erro", JOptionPane.ERROR_MESSAGE);
                    buffer = "0000000000000000";
                }
                else buffer = s;
            }
            else if (write.equals("1")) memoria.put(Integer.parseInt(endereco,2),buffer);
        }
        //System.out.println(memoria.keySet() + "\n" + memoria);
        reset();
    }
    
    public static void reset() {
        addressValid = "0";
        read = "0";
        write = "0";
    }
    
    public static String getAddressValid() {
        return addressValid;
    }
    public static void setAddressValid(String x){
        addressValid = x;
    }
    public static String getRead() {
        return read;
    }
    public static void setRead(String x){
        read = x;
    }
    public static String getWrite() {
        return write;
    }
    public static void setWrite(String x){
        write = x;
    }
    public static String getEndereco() {
        return endereco;
    }
    public static void setEndereco(String x){
        x = Main.normaliza(x); //normaliza o valor para 16 bits
        endereco = x;
    }
    public static String getBuffer() {
        return buffer;
    }
    public static void setBuffer(String x){
        x = Main.normaliza(x); //normaliza o valor para 16 bits
        buffer = x;
    }
    

    //Inserir no endereço de memória o opcode
    public static void addInitialCode(int address,String code){
        memoria.put(address,code);
    }
    
    //Usado para o caso especial de constante
    public static String getConstante(int address){
        return memoria.get(address);
    }
    
}