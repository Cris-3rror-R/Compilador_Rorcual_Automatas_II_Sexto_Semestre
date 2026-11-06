import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reglas_Produccion {

    public int NumLinea = 0;
    public String Comienzo = "\\s*COMIENZO\\s+;";
    public String InicioVar = "\\s*IDEVAR\\s+;";
    public String FinVar = "\\s*FDEVAR\\s+;";
    public String Final = "\\s*FINAL\\s+;";
    public String LetrasM = "[A-Z]"; 
    public String RestoID = "[A-Za-z0-9]*";
    public String ID = LetrasM + RestoID;
    public String TipoDato = "(Ent|Dec|Cad)";
    public String Numero_E = "[0-9]+";
    public String Consta_Ent = Numero_E;
    public String Consta_Dec = Numero_E + "\\." + Numero_E;
    public String Consta_Cad = "\"[^\"]*\"";
    public String Consta = "(" + Consta_Cad + "|" + Consta_Dec + "|" + Consta_Ent + ")";
    public String Operador = "(" + ID + "|" + Consta + ")";
    public String Operarit = "[\\+\\-\\*\\/\\^\\%\\\\]";
    public String Definir = "\\s*"+TipoDato + "\\s+" + ID + "\\s*;";
    public String Asignar = "\\s*"+ID + "\\s+=\\s+" + Operador + "\\s+;";
    public String Leer = "\\s*"+"LEER\\s+\\(\\s+" + ID + "\\s+\\)\\s+;";
    public String Exhalar = "\\s*"+"EXHALAR\\s+\\(\\s+" + Operador + "\\s+\\)\\s+;";
    public String ExhalarLn = "\\s*"+"EXHALARLN\\s+\\(\\s+" + Operador + "\\s+\\)\\s+;";
    public String Arit ="\\s*"+ ID + "\\s+=\\s+" + Operador + "\\s+" + Operarit + "\\s+" + Operador + "\\s+;";
    public String OpeRela = "(<|>|<=|>=|!=|==)";
    public String OpeLog = "(LoY|LoO)";
    public String Cond_S = "\\(\\s+" + Operador + "\\s+" + OpeRela + "\\s+" + Operador + "\\s+\\)";
    public String Cond_C = "\\(\\s+" + Cond_S + "\\s+" + OpeLog + "\\s+" + Cond_S + "\\s+\\)";
    public String Cond = "(" + Cond_S + "|" + Cond_C + ")";
    public String Si = "\\s*"+"SI\\s+" + Cond + "\\s+ENTONCES\\s+;";
    public String Fsi = "\\s*"+"FSI ;";
    public String Sino = "\\s*"+"SINO\\s+ENTONCES\\s*;";
    public String Fsino = "\\s*"+"FSINO ;";
    public String Inmersion = "\\s*"+"INMERSION\\s+\\(\\s+"+ ID + "\\s+DE\\s+" + Operador + "\\s+HASTA\\s+" + Operador + "\\s+\\)\\s+HACER\\s+;";
    public String FinInmersion = "\\s*"+"FINMERSION ;";
    public String Sumergir = "\\s*"+"SUMERGIR\\s+" + Cond + "\\s+HACER\\s+;";
    public String FSumergir = "\\s*"+"FSUMERGIR\\s+;";

    public boolean EstruVal (String AxuReg, String Aux){
        Pattern Estru = Pattern.compile(AxuReg);
        Matcher Compro = Estru.matcher(Aux);
        return Compro.matches();
    }


    public void ValTokens (String Linea, ArrayList<String> Token, int NumLinea){
        if((Linea.isEmpty()) || (Token.isEmpty()))
           return;
        if (Comprobar(Linea, Token,NumLinea)) {
        }else{
            Tabla_Errores.AnadirError("ErrorSint1", NumLinea,Linea);
        }
    }

    public boolean Comprobar(String Linea, ArrayList<String> Token,int NumLinea){
        String Aux = Token.get(0).toString();
        switch (Aux) {
            case "COMIENZO": 
                    return ComproRex(Comienzo,Linea);
            case "IDEVAR": 
                    return ComproRex(InicioVar, Linea);
            case "FDEVAR": 
                    return ComproRex(FinVar,Linea);
            case "FINAL": 
                    return ComproRex(Final,Linea);
            case "Ent","Cad","Dec":
                    if(ComproRex(Definir,Linea)){
                        return true;
                    }else{
                        Tabla_Errores.AnadirError("ErrorSint3", NumLinea,Linea);
                        return false;
                    }
            case "LEER": 
                    return ComproRex(Leer,Linea);
            case "EXHALAR": 
                    return ComproRex(Exhalar,Linea);
            case "EXHALARLN":
                return ComproRex(ExhalarLn,Linea);
            case "SI": 
                    return ComproRex(Si,Linea);
            case "FSI": 
                    return ComproRex(Fsi,Linea);
            case "SINO": 
                    return ComproRex(Sino,Linea);
            case "FSINO": 
                    return ComproRex(Fsino,Linea);
            case "INMERSION":
                    if(ComproRex(Inmersion,Linea)){
                        return true;
                    }else{
                        Tabla_Errores.AnadirError("ErrorSint4", NumLinea,Token.get(2));
                        return false;
                    }
            case "FINMERSION":
                    return ComproRex(FinInmersion,Linea);
            case "SUMERGIR": 
                    return ComproRex(Sumergir,Linea);
            case "FSUMERGIR": 
                    return ComproRex(FSumergir,Linea);
            default:
                if (Token.size() > 1 && Token.get(1).equals("=")) {
                    if(ComproRex(Arit,Linea)) {
                        return true;
                    }else{
                        if(ComproRex(Asignar,Linea)) {
                            return true;
                        }else{
                            Tabla_Errores.AnadirError("ErrorSint5", NumLinea,Token.get(0));
                            return false;
                        }   
                    }
                }else{
                    if(ComproRex(ID,Linea)) {
                        return true;
                    }else{
                        if(ComproRex(Consta,Linea)) {
                            return true;
                        }
                    }
                }
        }

        return false;
    }


    public boolean ComproRex(String AuxReg,String Linea){
        return EstruVal(AuxReg,Linea);
    }

    public static void main(String[] args) {
        
    }
}
