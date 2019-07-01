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
        if(i>=245 && i<=320) add(); //usa a operação add
        if(i>=321 && i<=396) sub(); // usa a operação sub
        if(i>=397 && i<=472) mul(); // usa a operação mul
        if(i>=473 && i<=548) div(); // usa a operação div
        if(i>=549 && i<=624) mod(); // usa a operação mod
        if(i>=625 && i<=700) cmp(); // usa a operação cmp
        if(i>=0 && i<=4) inc(); //para PC++
    }

    public static void atualizaFlag() {
        // atualizando flag ZERO
        if(parseInt(ac) == 0) UC.setZero(true);
        else UC.setZero(false);
        
        // atualizando flag SINAL
        if(parseInt(ac) >= 0) UC.setSinal(true);
        else UC.setSinal(false);
        
        // atualizando flag OVERFLOW
        int valor = parseInt(ac);
        String binString = "";
        if(valor < 0) binString = "-" + Integer.toBinaryString(-valor);
        else binString = "+" + Integer.toBinaryString(valor);
        System.out.println(binString);
        if(binString.length() > 16) UC.setOverflow(true);
        else UC.setOverflow(false);
    }
    
    public static void add() {
        ULA.setAC(Integer.toBinaryString(parseInt(x) + parseInt(ula)));
        atualizaFlag();
    }

    private static void sub() {
        ULA.setAC(Integer.toBinaryString(parseInt(x) - parseInt(ula)));
        atualizaFlag();
    }

    private static void mul() {
        ULA.setAC(Integer.toBinaryString(parseInt(x) * parseInt(ula)));
        atualizaFlag();
    }

    private static void div() {
        ULA.setAC(Integer.toBinaryString(parseInt(x) / parseInt(ula)));
        atualizaFlag();
    }

    private static void mod() {
        ULA.setAC(Integer.toBinaryString(parseInt(x) % parseInt(ula)));
        atualizaFlag();
    }

    private static void cmp() {
        String aux = Integer.toBinaryString(parseInt(x) - parseInt(ula));
        
        // atualizando flag ZERO
        if(parseInt(aux) == 0) UC.setZero(true);
        else UC.setZero(false);
        
        // atualizando flag SINAL
        if(parseInt(aux) >= 0) UC.setSinal(true);
        else UC.setSinal(false);
        
        // atualizando flag OVERFLOW
        int valor = parseInt(aux);
        String binString = "";
        if(valor < 0) binString = "-" + Integer.toBinaryString(-valor);
        else binString = "+" + Integer.toBinaryString(valor);
        System.out.println(binString);
        if(binString.length() > 16) UC.setOverflow(true);
        else UC.setOverflow(false);
    }

    public static void inc() {
        ULA.setAC(Integer.toBinaryString(parseInt(ula) + 1));
    }
    
    public static int parseInt(String s) {
        if(s.charAt(0) == '1') { //numero negativo
            char[] sc = s.toCharArray();
            //complemento
            for(int i = 0; i < sc.length; i++) {
                sc[i] = sc[i] == '1' ? '0' : '1';
            }
            String temp = new String(sc);
            //soma 1
            int i = sc.length -1;
            while(sc[i] != '0') {
                sc[i] = '0';
                i--;
            }
            sc[i] = '1';
            s = new String(sc);
            s = "-" + s;
        }
        return Integer.parseInt(s,2);
    }
    
    public static String toHexString(int a) {
        String s = "";
        if (a >= 0) {
            s = String.format("%4s",Integer.toHexString(a)).replace(' ', '0');
        }
        else {
            s = "-" + String.format("%4s",Integer.toHexString(-a)).replace(' ', '0');
        }
        
        return s;
    }
}
