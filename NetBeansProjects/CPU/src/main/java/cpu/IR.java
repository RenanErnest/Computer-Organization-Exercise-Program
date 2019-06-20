package cpu;

public class IR {
    
    private static String valor = "0000000000000000";
    private static String p1 = "0000000000000000"; //16bits para armezenar constantes
    private static String p2 = "0000000000000000";

    public  static String get(){
        return valor;
    }
    public static void set(String x){
        valor = x;
        int length = valor.length();
        int i = Integer.parseInt(valor,2);
        
        if( (i>=185 && i<=191) || (i>=40 && i<=43)) {
            //... pega a ct que esta no endereço PC da memória
            //... e coloca no IRp1
            PC.set(Integer.toBinaryString(Integer.parseInt(PC.get(),2)+1)); //PC++
        }
        
        if( (i>=181 && i<=184) || (i>=161 && i<=164) || (i>=141 && i<=144) || (i>=121 && i<=124) || (i>=101 && i<=104) || (i>=81 && i<=84) || (i>=44 && i<=47) || (i>=36 && i<=39) || (i>=16 && i<=19)) {
            //... pega a ct que esta no endereço PC da memória
            //... e coloca no IRp2
            PC.set(Integer.toBinaryString(Integer.parseInt(PC.get(),2)+1)); //PC++
        }
        
        if(i == 64) { //MOV [CT],CT
            //... pega a ct que esta no endereço PC da memória
            //... e coloca no IRp1
            PC.set(Integer.toBinaryString(Integer.parseInt(PC.get(),2)+1)); //PC++
            //... pega a ct que esta no endereço PC da memória
            //... e coloca no IRp2
            PC.set(Integer.toBinaryString(Integer.parseInt(PC.get(),2)+1)); //PC++
        }
    }
    
    public  static String getP1(){
        return p1;
    }
    public static void setP1(String x){
        p1 = x;
    }
    
    public  static String getP2(){
        return p2;
    }
    public static void setP2(String x){
        p2 = x;
    }
}