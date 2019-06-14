package cpu;

public class IR {
    
    private static String ir;
    private static int length; //length da String ir, deve conter 16 bits (6 OPCODE, 5 R1, 5 R2)
    private static String op;
    private static String p1;
    private static String p2;

    public  static String getIR(){
        return ir;
    }
    public static void setIR(String x){
        ir = x;
        length = ir.length();
        p2 = ir.substring(length-5, length);
        p1 = ir.substring(length-10, length-5);
        op = ir.substring(0, length-10);
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