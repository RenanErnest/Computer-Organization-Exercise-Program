package cpu;

public class ULA {
    
    private static String x; //variável para reservar o segundo parametro
    private static String ula;
    private static String ac;
    
    public static void setX(String a){
        x = a;
    }
    public static void setULA(String a){
        ula = a;
    }
    public  static String getULA(){ //resultado da operação
        return ula;
    }
    public static String getAC(){
        return ac;
    }
    //codigo de cada operação e também um código que diferencia de acordo com o sinal mandado pela UC
    //ex: quero multiplicar, UC tem que mandar o sinal MUL e ai a ULA tem que fazer a operação de multiplicação
    public static void operation(int i) {
        if(i>=65 && i<=84) add(); //usa a operação add
        if(i>=85 && i<=104) sub(); // usa a operação sub
        if(i>=105 && i<=124) mul(); // usa a operação mul
        if(i>=125 && i<=144) div(); // usa a operação div
        if(i>=145 && i<=164) mod(); // usa a operação mod
        if(i>=165 && i<=184) cmp(); // usa a operação cmp
    }

    public static void atualizaFlag() {
        // atualizando flag ZERO
        if(Integer.parseInt(ac,2) == 0) UC.setZero(true);
        else UC.setZero(false);
        
        // atualizando flag SINAL
        if(Integer.parseInt(ac,2) > 0) UC.setSinal(true);
        else UC.setSinal(false);
        
        // atualizando flag OVERFLOW
        if(ac.length() > 16) UC.setSinal(true);
        else UC.setSinal(false);
    }
    
    public static void add() {
        ac = Integer.toBinaryString(Integer.parseInt(x,2) + Integer.parseInt(ula,2));
        atualizaFlag();
    }

    private static void sub() {
        ac = Integer.toBinaryString(Integer.parseInt(x,2) - Integer.parseInt(ula,2));
        atualizaFlag();
    }

    private static void mul() {
        ac = Integer.toBinaryString(Integer.parseInt(x,2) * Integer.parseInt(ula,2));
        atualizaFlag();
    }

    private static void div() {
        ac = Integer.toBinaryString(Integer.parseInt(x,2) / Integer.parseInt(ula,2));
        atualizaFlag();
    }

    private static void mod() {
        ac = Integer.toBinaryString(Integer.parseInt(x,2) % Integer.parseInt(ula,2));
        atualizaFlag();
    }

    private static void cmp() {
        String aux = Integer.toBinaryString(Integer.parseInt(x,2) - Integer.parseInt(ula,2));
        
        // atualizando flag ZERO
        if(Integer.parseInt(aux,2) == 0) UC.setZero(true);
        else UC.setZero(false);
        
        // atualizando flag SINAL
        if(Integer.parseInt(aux,2) > 0) UC.setSinal(true);
        else UC.setSinal(false);
        
        // atualizando flag OVERFLOW
        if(aux.length() > 16) UC.setSinal(true);
        else UC.setSinal(false);
    }


}
