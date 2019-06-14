package cpu;

public class Barramentos {
    //sinais 1 - 12 saem do barramento
    //sinais 17 - 28 entram no barramento
    private static String interno = "00000000"; //34 bits
    
    //sinais 13 - 14 saem do barramento
    //sinais 29 - 31 entram no barramento
    private static String externo = "00000000";
    
    //a ideia é que quando for verificar os sinais, todos de 1-12 dão get no barramento interno
    //todos entre 17-28 dão set no barramento, etc
    
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
