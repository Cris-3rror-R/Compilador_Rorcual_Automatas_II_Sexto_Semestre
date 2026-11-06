public class Compilador_Rorcual_Venegas {

    public static void main(String[] args) {
        Analizador_Lexico AnalizadorLex = new Analizador_Lexico();
        AnalizadorLex.LeerTxt();
        Analizador_Sintatico AnalizadorSinta = new Analizador_Sintatico();
        AnalizadorSinta.LeerTxt();
        Analizador_Semantico AnalizadorSema = new Analizador_Semantico();
        AnalizadorSema.LeerTxt();
        if(!Tabla_Errores.ListaErrores.isEmpty()){
            Tabla_Errores.Imprimir();
        }else{
            Generador_Codigo Ensamblador = new Generador_Codigo();
            Ensamblador.LeerTxt();
        }

    }
}
