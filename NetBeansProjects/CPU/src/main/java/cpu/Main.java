package cpu;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//comandos principais para os valores binarios:
//Integer.parseInt(String, 2) - transforma string binaria em int
//Integer.toBinaryString(int) - transforma int em string binaria

public class Main {
    
    //Mapa <assemblyCode><OPCODE>
    public static Map<String,String> assemblyOpcode = new HashMap<String,String>();
    
    //Codigo em assembly colocado no programa
    public static String Code = ""; //apresentado na interface grafica
    public static String opcodeInMemory = "";
    public static String description = "";
    
    public static void program() {
        //primeiro código chamado ao iniciar o programa
        
        //Map assembly -> OPCODE
        try {
            InputStream inA = new FileInputStream("src\\main\\java\\cpu\\txt\\ComandosAssembly.txt");
            InputStream inO = new FileInputStream("src\\main\\java\\cpu\\txt\\OPCODES.txt");
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
        //"00000100000100000000100000000000000" //MAR <- PC, ULA <- PC
        //"00000000000001000000000000000011000" //Memoria <- MAR
        //"00001000000010000000000000010100101" //PC <- ULA(INC), MBR <- Memoria
        //"00000001000000000000001000000000000" //IR <- MBR
        //"11111111111111111111111111111111111" //indicador para o ciclo de instrução
        try { 
            InputStream in = new FileInputStream("src\\main\\java\\cpu\\txt\\Firmware.txt");
            Scanner scan = new Scanner(in);
            int i = 0;
            while(scan.hasNext()) {
                String s = scan.nextLine().replaceAll(" ","");
                //if (s.equals("0")) s = "00000000000000000000000000000000000";
                UC.Firmware[i] = s;
                i++;
            } 
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        //Pegar entrada do professor e transformar em OPCODE  e guardar na memória
        Scanner scan = new Scanner(Code);
        int i = 0;
        while(scan.hasNextLine()) {
            String s = scan.nextLine();
            
            Scanner scan2 = new Scanner(s);
            String certa = scan2.next();
            
            //Varios ifs para separar as constantes e adequar os textos de assembly para o futuro OPCODE
            if (s.contains(",")) {
                int r1 = -1;
                int r2 = -1;
                String r = scan2.next();
                String rs[] = r.split(",");
                
                if (rs[0].contains("[")) {
                   char[] rsc = rs[0].toCharArray();
                   char[] novo_rsc = new char[rs[0].length() - 2];
                   for(int q = 0; q < novo_rsc.length; q++) { //removendo colchetes
                       novo_rsc[q] = rsc[q+1];
                   }
                   rs[0] = new String(novo_rsc);
                   Scanner scan3 = new Scanner(rs[0]);
                   if (scan3.hasNextInt()) {
                       certa += " [CT],";
                       r1 = scan3.nextInt();
                   }
                   else {
                       certa += " [" + scan3.next() + "],";
                   }
                }
                else {
                   Scanner scan3 = new Scanner(rs[0]);
                   if (scan3.hasNextInt()) {
                       certa += " CT,";
                       r1 = scan3.nextInt();
                   }
                   else {
                       certa += " " + scan3.next() + ",";
                   }
                }
                
                if (rs[1].contains("[")) {
                   char[] rsc = rs[1].toCharArray();
                   char[] novo_rsc = new char[rs[1].length() - 2];
                   for(int q = 0; q < novo_rsc.length; q++) { //removendo colchetes
                       novo_rsc[q] = rsc[q+1];
                   }
                   rs[1] = new String(novo_rsc);
                   Scanner scan3 = new Scanner(rs[1]);
                   if (scan3.hasNextInt()) {
                       certa += "[CT]";
                       r2 = scan3.nextInt();
                   }
                   else {
                       certa += "[" + scan3.next() + "]";
                   }
                }
                else {
                    Scanner scan3 = new Scanner(rs[1]);
                    if (scan3.hasNextInt()) {
                       certa += "CT";
                       r2 = scan3.nextInt();
                    }
                    else {
                        certa += scan3.next();
                    }
                }
                Memoria.addInitialCode(i,assemblyOpcode.get(certa));
                opcodeInMemory += certa + "\n";
                if (r1 != -1) Memoria.addInitialCode(++i, Integer.toBinaryString(r1));
                if (r2 != -1) Memoria.addInitialCode(++i, Integer.toBinaryString(r2));
            }
            else { //é um opcode com apenas 1 parametro, no caso, jumps
                certa += " CT";
                Memoria.addInitialCode(i,assemblyOpcode.get(certa));
                opcodeInMemory += certa + "\n";
                Memoria.addInitialCode(++i, Integer.toBinaryString(scan2.nextInt()));
            }
            
            i++;
        }
    }
    
    public static String normaliza(String x) {
        int length = x.length();
        if (length < 16) x = String.format("%16s", x).replace(' ', '0');
        return x;
    }

}
/* Teste
MOV BX,10
ADD AX,29
MOV CX,AX
ADD CX,BX
MUL CX,BX
SUB DX,AX
CMP AX,BX
MOV AX,10
CMP AX,BX
MOV [100],AX
MOV AX,AX
MOV CX,[100]
SUB AX,40

ADD AX,5
MOV CX,5
CMP CX,AX
JE 0
*/