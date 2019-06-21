package cpu;

public class Barramentos {
    
    private static String interno = "00000000";

    private static String externo = "00000000";
    
    public  static String getInterno(){
        return interno;
    }
    public static void setInterno(String x){
        interno = x;
    }
    
    public  static String getExterno(){
        return externo;
    }
    public static void setExterno(String x){
        externo = x;
    }
}
