package cpu;

public class PC {
    
    private static String valor = "00000000";

    public  static String get(){
        return valor;
    }
    public static void set(String x){
        valor = x;
    }
}