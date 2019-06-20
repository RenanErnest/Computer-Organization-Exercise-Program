package cpu;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//comandos principais para os valores binarios:
//Integer.parseInt(String, 2) - transforma string binaria em int
//Integer.toBinaryString(int) - transforma int em string binaria

public class Main {
    
    //Mapa
    public static Map<String,String> assemblyOpcode = new HashMap<String,String>();
    
    //Codigo em assembly colocado no programa
    public static String Code; //apresentado na interface grafica
    
    public static void program() {
        //primeiro código chamado ao iniciar o programa
        //deve conter o assembler transformando em OPCODE e guardando na estrutura de memória sequencialmente
        
        //Map assembly -> OPCODE
        try {
            InputStream inA = new FileInputStream("src\\main\\java\\cpu\\ComandosAssembly.txt");
            InputStream inO = new FileInputStream("src\\main\\java\\cpu\\OPCODES.txt");
            Scanner scanA = new Scanner(inA);
            Scanner scanO = new Scanner(inO);
            int i = 0;
            while(scanA.hasNext()) {
                String assembly = scanA.nextLine();
                String opcode = scanO.nextLine();
                assemblyOpcode.put(assembly, opcode);
                i++;
            } 
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        /*for(String s : assemblyOpcode.keySet()) {
            String assembly = s;
            String opcode = assemblyOpcode.get(assembly);
            System.out.println(assembly + " - " + opcode);
        }*/
        
        //Preenchendo Firmware
        //Fetch Cycle
        UC.Firmware[0] = "0000010000010000000010000000000000"; //MAR <- PC, ULA <- PC
        UC.Firmware[1] = "0000000000000100000000000000001100"; //Memoria <- MAR
        UC.Firmware[2] = "0000100000001000000000000001010010"; //PC <- ULA(INC), MBR <- Memoria
        UC.Firmware[3] = "0000000100000000000000100000000000"; //IR <- MBR
        UC.Firmware[4] = "1111111111111111111111111111111111"; //indicador para o ciclo de instrução
        //Resto
        try { 
            InputStream in = new FileInputStream("src\\main\\java\\cpu\\Firmware.txt");
            Scanner scan = new Scanner(in);
            int i = 5;
            while(scan.hasNext()) {
                String s = scan.nextLine().replaceAll(" ","");
                UC.Firmware[i] = s;
                i++;
            } 
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        
        //Pegar entrada do professor e transformar em OPCODE  e guardar na memória
        
    }
}