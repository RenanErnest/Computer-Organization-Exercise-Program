package cpu;

public class MBR {
    
    private static String valor = "00000000";

    public  static String getMBR(){
        return valor;
    }
    public static void setMBR(String x){
        valor = x;
    }
}