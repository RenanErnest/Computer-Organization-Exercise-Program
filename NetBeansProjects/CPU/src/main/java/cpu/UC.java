package cpu;

import java.util.HashMap;
import java.util.Map;

public class UC {

    private static int clock;
    
    //flags (0 - false; 1 - true)
    private static boolean zero = false; //1 - resultado da ultima conta deu zero
    private static boolean sinal = false; //0 - positivo, 1 - negativo
    private static boolean overflow = false; //1 - ultima operação deu overflow
    
    //Registradores que lidam com a Firmware
    private static String CAR = "0";
    private static String CBR = "0";
    
    //Firmware
    public static String[] Firmware = new String[715];
    //0000000000000000000000000000000000 - Indicador para voltar ao ciclo de busca
    //1111111111111111111111111111111111 - Indicador para ciclo de execução
    
    //Mapa
    public static Map<String,String> instrucaoDescricao = new HashMap<String,String>();
    
    public static void step() {
        //UC pegará o OPCODE
        //Do OPCODE ela tem que transformar em clocks e sinais de controle
        //Ela que deve coordenar toda a operação
        
        CBR = Firmware[Integer.parseInt(CAR,2)];
        CAR = Integer.toBinaryString(Integer.parseInt(CAR,2) + 1); //INC CAR
        if (CBR.equals("0000000000000000000000000000000000")) CAR = "0"; //retorna para o ciclo de busca
        else if (CBR.equals("1111111111111111111111111111111111")) CAR = IR.getIR(); //OPCODE já é o endereço da Firmware
        else {
            //for nos barramentos para saber quais portas abrir
        }
        //criar um mega array de firmware (Firmware)
        //cada linha é uma palavra horizontal
        //Fazer Hashmap(OPCODE,indice) onde o indice é o início das palavras dessa instrução na Firmware
        //A cada fim das palavras, ou seja, começo de uma nova instrução, o iterador para quando achar um OPCODE específico(vamos usar tudo 0).
        //criar um Hashmap(palavraHorizontal,comentário) onde cada palavra horizontal tem seu literal ex: MAR <- PC
    }
    
    public static String getCBR() {
        return CBR;
    }
    public static void setCBR(String CBR) {
        UC.CBR = CBR;
    }
    
    public static String getCAR() {
        return CAR;
    }
    public static void setCAR(String CAR) {
        UC.CAR = CAR;
    }

    public static boolean getZero() {
        return zero;
    }
    public static void setZero(boolean aZero) {
        zero = aZero;
    }
    
    public static boolean getSinal() {
        return sinal;
    }
    public static void setSinal(boolean aSinal) {
        sinal = aSinal;
    }
    
    public static boolean getOverflow() {
        return overflow;
    }
    public static void setOverflow(boolean aOverflow) {
        overflow = aOverflow;
    }
}
