package cpu;

public class ULA {
    
    private static String x; //variável para reservar o segundo parametro
    private static String ula;

    public static void setX(String a){
        x = a;
    }
    public static void setULA(String a){
        ula = a;
    }
    public  static String getULA(){ //resultado da operação
        return ula;
    }
    
    //codigo de cada operação e também um código que diferencia de acordo com o sinal mandado pela UC
    //ex: quero multiplicar, UC tem que mandar o sinal MUL e ai a ULA tem que fazer a operação de multiplicação
    
}