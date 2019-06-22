package cpu;

public class Barramentos {
    
    private static String interno = "0000000000000000";

    private static String externo = "0000000000000000";
    
    public  static String getInterno(){
        return interno;
    }
    public static void setInterno(String x){
        x = Main.normaliza(x); //normaliza o valor para 16 bits
        interno = x;
    }
    
    public  static String getExterno(){
        return externo;
    }
    public static void setExterno(String x){
        x = Main.normaliza(x); //normaliza o valor para 16 bits
        externo = x;
    }
}
