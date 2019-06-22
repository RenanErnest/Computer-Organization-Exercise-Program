package cpu;

public class MBR {
    
    private static String valor = "0000000000000000";

    public  static String get(){
        return valor;
    }
    public static void set(String x){
        x = Main.normaliza(x); //normaliza o valor para 16 bits
        valor = x;
    }
}