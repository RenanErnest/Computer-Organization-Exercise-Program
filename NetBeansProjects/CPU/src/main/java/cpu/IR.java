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
        
        //Casos de constante
        if( (i>=701 && i<=713) || (i>=124 && i<=137)) {
            //... pega a ct que esta no endereço PC da memória
            //... e coloca no IRp1
            PC.set(Integer.toBinaryString(Integer.parseInt(PC.get(),2)+1)); //PC++
        }
        
        if( (i>=685 && i<=697) || (i>=609 && i<=621) || (i>=533 && i<=545) || (i>=457 && i<=469) || (i>=381 && i<=393) || (i>=305 && i<=317) || (i>=141 && i<=155) || (i>=109 && i<=121) || (i>=37 && i<=43)) {
            //... pega a ct que esta no endereço PC da memória
            //... e coloca no IRp2
            PC.set(Integer.toBinaryString(Integer.parseInt(PC.get(),2)+1)); //PC++
        }
        
        if(i == 240) { //MOV [CT],CT
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