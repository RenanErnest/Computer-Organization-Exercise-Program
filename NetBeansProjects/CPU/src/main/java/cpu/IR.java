package cpu;

public class IR {
    
    private static String ir = "0000000000000000";
    private static String p1 = "0000000000000000";
    private static String p2 = "0000000000000000";

    public  static String getIR(){
        return ir;
    }
    public static void setIR(String x){
        ir = x;
        int length = ir.length();
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