package cpu;

public class ULA {
    
    private static String x = "0000000000000000"; //variável para reservar o outro parametro
    private static String ula = "0000000000000000";
    private static String ac = "0000000000000000";
    
    public static void setX(String a){
        a = Main.normaliza(a); //normaliza o valor para 16 bits
        x = a;
    }
    public static void setULA(String a){
        a = Main.normaliza(a); //normaliza o valor para 16 bits
        ula = a;
    }
    public static String getAC(){ //resultado da operação
        return ac;
    }
    public static void setAC(String a) {
        a = Main.normaliza(a); //normaliza o valor para 16 bits
        ac = a;
    }
    
    //codigo de cada operação e também um código que diferencia de acordo com o sinal mandado pela UC
    public static void operation(int i) {
        if((i>=245 && i<=320) || (i>=0 && i<=4)) add(); //usa a operação add
        if(i>=321 && i<=396) sub(); // usa a operação sub
        if(i>=397 && i<=472) mul(); // usa a operação mul
        if(i>=473 && i<=548) div(); // usa a operação div
        if(i>=549 && i<=624) mod(); // usa a operação mod
        if(i>=625 && i<=700) cmp(); // usa a operação cmp
    }

    public static void atualizaFlag() {
        // atualizando flag ZERO
        if(Integer.parseInt(ac,2) == 0) UC.setZero(true);
        else UC.setZero(false);
        
        // atualizando flag SINAL
        if(Integer.parseInt(ac,2) >= 0) UC.setSinal(true);
        else UC.setSinal(false);
        
        // atualizando flag OVERFLOW
        if(ac.length() > 16) UC.setOverflow(true);
        else UC.setOverflow(false);
    }
    
    public static void add() {
        ULA.setAC(Integer.toBinaryString(Integer.parseInt(x,2) + Integer.parseInt(ula,2)));
        atualizaFlag();
    }

    private static void sub() {
        ULA.setAC(Integer.toBinaryString(Integer.parseInt(x,2) - Integer.parseInt(ula,2)));
        atualizaFlag();
    }

    private static void mul() {
        ULA.setAC(Integer.toBinaryString(Integer.parseInt(x,2) * Integer.parseInt(ula,2)));
        atualizaFlag();
    }

    private static void div() {
        ULA.setAC(Integer.toBinaryString(Integer.parseInt(x,2) / Integer.parseInt(ula,2)));
        atualizaFlag();
    }

    private static void mod() {
        ULA.setAC(Integer.toBinaryString(Integer.parseInt(x,2) % Integer.parseInt(ula,2)));
        atualizaFlag();
    }

    private static void cmp() {
        String aux = Integer.toBinaryString(Integer.parseInt(x,2) - Integer.parseInt(ula,2));
        aux = Main.normaliza(aux);
        
        // atualizando flag ZERO
        if(Integer.parseInt(aux,2) == 0) UC.setZero(true);
        else UC.setZero(false);
        
        // atualizando flag SINAL
        if(Integer.parseInt(aux,2) >= 0) UC.setSinal(true);
        else UC.setSinal(false);
        
        // atualizando flag OVERFLOW
        if(aux.length() > 16) UC.setOverflow(true);
        else UC.setOverflow(false);
    }


}
