package cpu;

public class UC {

    private static int clock;
    
    //flags (0 - false; 1 - true)
    private static boolean zero; //1 - resultado da ultima conta deu zero
    private static boolean negativo; //0 - positivo, 1 - negativo
    private static boolean overflow; //1 - ultima operação deu overflow
    
    public static void step() {
        
        //UC pegará o OPCODE
        //Do OPCODE ela tem que transformar em clocks e sinais de controle
        //Ela que deve coordenar toda a operação
        
        //criar uma mega matriz de firmware (EEPROM)
        //cada linha é um tzinho e cada coluna é um sinal
        //Pode ser criado uma matriz cúbica em que [OPCODE][tzinho][sinal]
        //também criar uma matriz de String [OPCODE][tzinho] para colocar literalmente MAR <- PC, etc
    }
    
    public static void setZero(boolean aZero) {
        zero = aZero;
    }

    public static void setNegativo(boolean aNegativo) {
        negativo = aNegativo;
    }

    public static void setOverflow(boolean aOverflow) {
        overflow = aOverflow;
    }
}
