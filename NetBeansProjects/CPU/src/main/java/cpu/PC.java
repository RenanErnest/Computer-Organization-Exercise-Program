package cpu;

public class PC {
    
    private static String valor = "00000000";

    public  static String getPC(){
        return valor;
    }
    public static void setPC(String x){
        valor = x;
    }
}