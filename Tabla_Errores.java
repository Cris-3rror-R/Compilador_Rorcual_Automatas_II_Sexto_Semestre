import java.util.ArrayList;

public class Tabla_Errores {

    public static String TablaErrores [][]  = {
        {"ErrorLex1","Caracter no valido en el lenguaje."},
        {"ErrorLex2","Token no valido en el lenguaje."},
        {"ErrorSint1","Sintaxis incorrecta"},
        {"ErrorSint2","No existe ese tipo de dato, solo se permiten: Ent, Cad y Dec"},
        {"ErrorSint3","El nombre de variable no es valido, no cumple con la forma de los identificadores."},
            {"ErrorSint4","La sentencia INMERSION su primer parametro debe ser una VARIABLE."},
            {"ErrorSint5","No se puede usar una constante para realizar una operación aritmetica."},
        {"ErrorSem1","No esta el bloque de inicio del programa."},
        {"ErrorSem2","No esta el bloque del fin del inicio de programa."},
        {"ErrorSem3","No esta la sentencia IDEVAR para la declaracion de variables."},
        {"ErrorSem4","No esta la sentencia FDEVAR para decir el fin de la declaracion de variables."},
        {"ErrorSem5","No se puede usar palabras reservadas como identificadores."},
        {"ErrorSem6","No existe esa variable en el programa."},
        {"ErrorSem7","No se pueden combinar tipos de datos en una operacion aritmetica."},
        {"ErrorSem8","Un tipo de dato Cad no puede ser utilizado en esta operacion."},
        {"ErrorSem9","La variable esta afuera del bloque de definición de variables."},
        {"ErrorSem10","El bloque esta abierto de forma incorrecta falta la sentencia de fin del bloque."},
        {"ErrorSem11","El bloque esta cerrado de forma incorrecta falta la sentencia de inicio del bloque."},
        {"ErrorSem12","Falta la palabra FSI para poder declarar la sentencia SINO."},
        {"ErrorSem13","La sentencia anterior al SINO no es un FSI."},
        {"ErrorSem14","La sentencia LEER solo se puede LEER en una variable."},
        {"ErrorSem15","Los argumentos del condicional tienen que ser del mismo tipo, el siguiente argumento es de tipo diferente:"},
        {"ErrorSem16","El siguiente condicional es incorrecto,"},
        {"ErrorSem17","El tipo de dato de la variable inicial del la sentencia INMERSION debe ser Ent."},
        {"ErrorSem18","El nombre esta en uso por otra variable."},
        {"ErrorSem19","La constante o variable debe ser de tipo Ent."},
        {"ErrorSem20","En operaciones aritmeticas de Cadenas solo se pueden ser de adicción."},
            {"ErrorSem21","La sentencia INMERSION esta incorrecta, los parametros estan mal: "},
            {"ErrorSem22","Solo puede existir una sentencia COMIENZO en el programa."},
            {"ErrorSem23","Se esta declarando sentencias antes de colocar la sentencia de COMIENZO."},
            {"ErrorSem24","Se esta declarando sentencias antes del inicio de declaracion de variables."},
            {"ErrorSem25","Se esta declarando sentencias que no son declaracion de variables adentro del bloque de declaracion de variables."},
            {"ErrorSem26","Solo puede existir una sentencia IDEVAR en el programa."},
            {"ErrorSem27","Solo puede existir una sentencia FDEVAR en el programa."},
            {"ErrorSem28","Se esta cerrando un bloque antes de tiemmpo."},
            {"ErrorSem29","No se puede usar la misma variable para bloques anidados de INMERSION."},
            {"ErrorSem30","Falto la sentencia FINAL para finalizar el programa."},
            {"ErrorSem31","Solo puede existir una sentencia FINAL en el programa."},
            {"ErrorSem32","Se estan declarando sentencias despues de la sentencia FINAL."},
            {"ErrorSem33","No se puede utilizar la misma variable para el valor inicial o final de la sentencia INMERSION."},
            {"ErrorSem34","Las constantes no pueden estar solas."},
    };

    public static ArrayList<TablaErrorEjecucion> ListaErrores = new ArrayList<>();

    public static void AnadirError(String Clave, int NumLinea, String Disparador) {
        String Descri = ObtenerDes(Clave) + " " + Disparador;
        ListaErrores.add(new TablaErrorEjecucion(Clave, Descri, NumLinea));
    }

    public static String ObtenerDes(String clave) {
        int i = 0;
        while (i < TablaErrores.length) {
            if (TablaErrores[i][0].equals(clave)) {
                return TablaErrores[i][1];
            }
            i++;
        }
        return "Descripción no encontrada";
    }

    public static void Imprimir() {
        for (TablaErrorEjecucion e : ListaErrores) {
            System.out.println("ID: " + e.getClaveError() + ", Descripción: " + e.getDescripcion() + ". Línea: " + e.getNumLinea());
        }
    }

    public static class TablaErrorEjecucion {
        private String ClaveError;
        private int NumLinea;
        private String Descripcion;

    public TablaErrorEjecucion(String ClaveError, String Descripcion, int NumLinea) {
        this.ClaveError = ClaveError;
        this.NumLinea = NumLinea;
        this.Descripcion = Descripcion;
    }
    public String getClaveError() {
        return ClaveError;
    }
    public String getDescripcion() {
        return Descripcion;
    }
    public int getNumLinea() {
        return NumLinea;
    }
    public void setClaveError(String ClaveError) {
        this.ClaveError = ClaveError;
    }
    public void setNumLinea(int NumLinea) {
        this.NumLinea = NumLinea;
    }
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
  }

}
class TablaSimbolos {
    private String Nombre;
    private String Tipo;
    
    public TablaSimbolos(String Nombre, String Tipo) {
        this.Nombre = Nombre;
        this.Tipo = Tipo;
    }
    
    public String getNombre() {
        return Nombre;
    }
    
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public String getTipo() {
        return Tipo;
    }
    
    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
}

class Pila {
    private String Sentencia;
    private int NumLinea;
    private ArrayList<String> Contenido;

    public Pila(String Sentencia, int Numlinea) {
        this.Sentencia = Sentencia;
        this.NumLinea = Numlinea;
        this.Contenido = new ArrayList<>();
    }

    public String getSentencia() {return Sentencia;}
    public void setSentencia(String sentencia) {Sentencia = sentencia;}

    public int getNumLinea() {return NumLinea;}
    public void setNumLinea(int numLinea) {NumLinea = numLinea;}

    public ArrayList<String> getContenido() {return Contenido;}
    public void setContenido(ArrayList<String> contenido) {Contenido = contenido;}
    public void AnadirContenido(String item) {Contenido.add(item);}
}

class Control {
    private ArrayList<Pila> PilaBlo = new ArrayList<>();

    public void MeterPila(String Sentencia, int numLinea) {
        PilaBlo.add(new Pila(Sentencia, numLinea));
    }

    public Pila SacarPila() {
        if (!PilaBlo.isEmpty()) {
            return PilaBlo.remove(PilaBlo.size() - 1);
        }
        return null;
    }

    public Pila BuscarPila() {
        if (!PilaBlo.isEmpty()) {
            return PilaBlo.get(PilaBlo.size() - 1);
        }
        return null;
    }

    public boolean PilaVacia() {
        return PilaBlo.isEmpty();
    }
}

