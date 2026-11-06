// Estudiante: Cristopher Axel Venegas Venegas
// Asignatura: Lenguajes y Autómatas II
// Analizador léxico 3.0
// Programa que compruebe los caracteres y token que sean del lenguaje.
// Tercera version 

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.*;


public class Analizador_Lexico {

    static ArrayList<String> PalaReser = new ArrayList<>(java.util.Arrays.asList( "LEER", "EXHALAR", "EXHALARLN", "COMIENZO", "FINAL", "IDEVAR", "FDEVAR",
            "SI", "ENTONCES", "FSI", "SINO", "FSINO", "INMERSION", "DE", "HASTA", "FINMERSION", "SUMERGIR", "HACER", "FSUMERGIR", "Cad", "Ent", "Dec",
            ">=", "<=", "!=", "==", "LoY", "LoO"));

    static ArrayList<Character> Alfabeto = new ArrayList<>(java.util.Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o','p', 
    'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' ,'"', ';' , ',' , '.' , '(' , ')' , ' ' ,
    '<','>','!','=','+','-','/','*','^','\\','%'));

    static ArrayList<String> VariablesTemporales = new ArrayList<>();

    public static final Pattern ConsNum = Pattern.compile("[0-9]+");
    public static final Pattern ConsDec = Pattern.compile("[0-9]+\\.[0-9]+");

    public static SepararToken Tokens = new SepararToken();
    public static Reglas_Produccion RP = new Reglas_Produccion();

    static int NumLinea = 0;

    public static void LeerTxt(){
    try(FileReader Bloc = new FileReader("Prueba.txt");
        BufferedReader Lector = new BufferedReader(Bloc);){
        String AuxLinea = Lector.readLine();

        while(AuxLinea != null){
            NumLinea++;
            AnalizadorLexico(AuxLinea);
            AuxLinea = Lector.readLine();
        }

    }catch(Exception e) {
        System.out.println("ERROR 404, No se encontro el archivo: ");
    }
}


    public static void AnalizadorLexico (String Aux){
        boolean TokVal = true;
        String AuxTokens [] = Tokens.Separar(Aux);
        for(int x = 0; x < AuxTokens.length; x++){
            TokVal = true;
            if((AuxTokens[x].length()>=2) && (AuxTokens[x].charAt(0) == '"') && (AuxTokens[x].charAt(AuxTokens[x].length()-1) == '"')){
                //No hace nada, solo es una constante es decir "Lo que sea"
            }else{
                for(int y = 0; y < AuxTokens[x].length(); y++){
                    if(!ValidarAlfab(AuxTokens[x].charAt(y))){
                    }
                }
                if(RP.ComproRex(RP.Definir,Aux)){
                    if(AuxTokens.length >= 2){
                        VariablesTemporales.add(AuxTokens[1]);
                    }
                }else{
                    for(int y = 0; y < AuxTokens[x].length(); y++){
                        if(!ValidarAlfab(AuxTokens[x].charAt(y))){
                            Tabla_Errores.AnadirError("ErrorLex1", NumLinea, String.valueOf(AuxTokens[x].charAt(y)));
                        }
                    }
                    if(!ValidarToken(AuxTokens[x])){
                        Tabla_Errores.AnadirError("ErrorLex2", NumLinea, AuxTokens[x]);
                    }
                }
            }

        }


    }

public static boolean ValidarAlfab(Character Aux){
        if(Alfabeto.contains(Aux)){
            return true;
        }else{
            return false;
        }
}

public static boolean ValidarToken(String Aux) {
    if (PalaReser.contains(Aux)) {
        return true;
    }else{
        if(VariablesTemporales.contains(Aux)){
            return true;
        }else{
            if(Alfabeto.contains(Aux.charAt(0)) && (Aux.length() == 1)){
                return true;
            }else{
                if(ConsNum.matcher(Aux).matches()){
                    return true;
                }else{
                    if(ConsDec.matcher(Aux).matches()){
                        return true;
                    }else{
                        if(RP.ComproRex(RP.ID,Aux)){
                            return true;
                        }else{
                            return false;
                        }
                    }
                }
            }
        }
    }
}

public static void main(String[] args) {
        LeerTxt();
}

}
