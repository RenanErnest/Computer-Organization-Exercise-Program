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
        if (CBR.equals("0")) CAR = "0"; //retorna para o ciclo de busca
        else if (CBR.equals("11111111111111111111111111111111111")) CAR = IR.get(); //OPCODE já é o endereço da Firmware
        else {
            
            //porta de saida do PC
            if (CBR.charAt(20) == '1') ULA.setX(Integer.toBinaryString(1)); //pra dar INC
            
            //Sinais para a ULA
            if (CBR.charAt(34) == '1') {
                ULA.operation(Integer.parseInt(CAR,2) - 1);
            }
            
            //Sinais para a Memoria
            if (CBR.charAt(31) == '1') Memoria.setAddressValid("1");
            if (CBR.charAt(32) == '1') {
                Memoria.setRead("1");
                Memoria.operation();
            }
            
            String descricao = "";
            String descricao2 = "";
            
            /*------------------------ Entradas dos registradores para os barramentos ----------------------*/
            //sinais 17 - 28 entram no barramento interno
            for(int j = 16; j < 28; j++) {
                if (CBR.charAt(j) == '1') {
                    switch(j) {
                        case 16:
                            Barramentos.setInterno(AX.get());
                            descricao += " <- AX";
                            break;
                        case 17:
                            Barramentos.setInterno(BX.get());
                            descricao += " <- BX";
                            break;
                        case 18:
                            Barramentos.setInterno(CX.get());
                            descricao += " <- CX";
                            break;
                        case 19:
                            Barramentos.setInterno(DX.get());
                            descricao += " <- DX";
                            break;
                        case 20:
                            Barramentos.setInterno(PC.get());
                            descricao += " <- PC";
                            break; 
                        case 22:
                            Barramentos.setInterno(MBR.get());
                            descricao += " <- MBR";
                            break;
                        case 24:
                            Barramentos.setInterno(IR.getP2());
                            descricao += " <- IR-P2";
                            break;
                        case 25:
                            Barramentos.setInterno(IR.getP1());
                            descricao += " <- IR-P1";
                            break;
                        case 27:
                            Barramentos.setInterno(ULA.getAC());
                            descricao += " <- ULA";
                            break;    
                    }
                }
            }
            
            //sinais 29 - 31 entram no barramento externo
            for(int j = 28; j < 31; j++) {
                if (CBR.charAt(j) == '1') {
                    switch(j) {
                        case 28:
                            Barramentos.setExterno(MBR.get());
                            descricao2 += " <- MBR";
                            break;
                        case 29:
                            Barramentos.setExterno(Memoria.getBuffer());
                            descricao2 += " <- MERMORIA";
                            break;
                        case 30:
                            Barramentos.setExterno(MAR.get());
                            descricao2 += " <- MAR";
                            break;
                    }
                }
            }
            /*-----------------------------------------------------------------------------*/
            
            String final1 = descricao;
            String final2 = descricao2;
            
            /*---------------------- Saidas dos barramentos para os registradores ------------------*/
            //sinais 1 - 12 saem do barramento interno para os registradores
            for(int j = 0; j < 12; j++) {
                if (CBR.charAt(j) == '1') {
                    switch(j) {
                        case 0:
                            AX.set(Barramentos.getInterno());
                            if(descricao.charAt(0) != ' ') {
                                descricao += "\n" + "AX" + final1;
                            }
                            else descricao = "AX" + final1;
                            break;
                        case 1:
                            BX.set(Barramentos.getInterno());
                            if(descricao.charAt(0) != ' ') {
                                descricao += "\n" + "BX" + final1;
                            }
                            else descricao = "BX" + final1;
                            break;
                        case 2:
                            CX.set(Barramentos.getInterno());
                            if(descricao.charAt(0) != ' ') {
                                descricao += "\n" + "CX" + final1;
                            }
                            else descricao = "CX" + final1;
                            break;
                        case 3:
                            DX.set(Barramentos.getInterno());
                            if(descricao.charAt(0) != ' ') {
                                descricao += "\n" + "DX" + final1;
                            }
                            else descricao = "DX" + final1;
                            break;
                        case 4:
                            PC.set(Barramentos.getInterno());
                            if(descricao.charAt(0) != ' ') {
                                descricao += "\n" + "PC" + final1;
                            }
                            else descricao = "PC" + final1;
                            break;
                        case 5:
                            MAR.set(Barramentos.getInterno());
                            if(descricao.charAt(0) != ' ') {
                                descricao += "\n" + "MAR" + final1;
                            }
                            else descricao = "MAR" + final1;
                            break;
                        case 6:
                            MBR.set(Barramentos.getInterno());
                            if(descricao.charAt(0) != ' ') {
                                descricao += "\n" + "MBR" + final1;
                            }
                            else descricao = "MBR" + final1;
                            break;
                        case 7:
                            IR.set(Barramentos.getInterno());
                            if(descricao.charAt(0) != ' ') {
                                descricao += "\n" + "IR" + final1;
                            }
                            else descricao = "IR" + final1;
                            break;
                        case 8:
                            IR.setP2(Barramentos.getInterno());
                            if(descricao.charAt(0) != ' ') {
                                descricao += "\n" + "IR-P2" + final1;
                            }
                            else descricao = "IR-P2" + final1;
                            break;
                        case 9:
                            IR.setP1(Barramentos.getInterno());
                            if(descricao.charAt(0) != ' ') {
                                descricao += "\n" + "IR-P1" + final1;
                            }
                            else descricao = "IR-P1" + final1;
                            break;
                        case 10:
                            ULA.setX(Barramentos.getInterno());
                            if(descricao.charAt(0) != ' ') {
                                descricao += "\n" + "ULA-X" + final1;
                            }
                            else descricao = "ULA-X" + final1;
                            break;
                        case 11:
                            ULA.setULA(Barramentos.getInterno());
                            if(descricao.charAt(0) != ' ') {
                                descricao += "\n" + "ULA" + final1;
                            }
                            else descricao = "ULA" + final1;
                            break;
                    }
                }
            }

            //sinais 13 - 14 saem do barramento externo
            for(int j = 12; j < 14; j++) {
                if (CBR.charAt(j) == '1') {
                    switch(j) {
                        case 12:
                            MBR.set(Barramentos.getExterno());
                            if(descricao2.charAt(0) != ' ') {
                                descricao2 += "\n" + "MBR" + final2;
                            }
                            else descricao2 = "MBR" + final2;
                            break;
                        case 13:
                            if (CBR.charAt(31) == '1') Memoria.setEndereco(Barramentos.getExterno());
                            else Memoria.setBuffer(Barramentos.getExterno());
                            if(descricao2.charAt(0) != ' ') {
                                descricao2 += "\n" + "MEMORIA" + final2;
                            }
                            else descricao2 = "MEMORIA" + final2;
                            break;
                    }
                }
            }
            /*--------------------------------------------------------------------*/
            
            //Sinais Memória
            if (CBR.charAt(33) == '1') {
                Memoria.setWrite("1");
                Memoria.operation();
            }
            
            Main.description = !descricao.equals("") ? descricao + "\n" + descricao2 : descricao2;
        }
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
