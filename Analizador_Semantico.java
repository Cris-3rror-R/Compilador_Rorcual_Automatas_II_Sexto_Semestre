import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Analizador_Semantico {
    public static Analizador_Lexico Tabla = new Analizador_Lexico();
    public static SepararToken Tokens = new SepararToken();
    public static Reglas_Semanticas RS = new Reglas_Semanticas();
    public int NumLinea = 0;
    public Control Pila = new Control();


    public void LeerTxt(){
        try(FileReader Bloc = new FileReader("Prueba.txt");
            BufferedReader Lector = new BufferedReader(Bloc);){
            String AuxLinea = Lector.readLine();
            while(AuxLinea != null){
                NumLinea++;
                String [] AuxToken = Tokens.Separar(AuxLinea);
                ArrayList <String> ListaToken = new ArrayList<String>(Arrays.asList(AuxToken));
                ArrayList <String> TablaPalabrasR = new ArrayList<String>(Tabla.PalaReser);
                ArrayList <Character> TablaAlfebeto = new ArrayList<Character>(Tabla.Alfabeto);
                RS.ValSemantica(AuxLinea, ListaToken, TablaPalabrasR, TablaAlfebeto, NumLinea);
                AuxLinea = Lector.readLine();
            }
            Pila = RS.Pila;
            while(!Pila.PilaVacia()){
                Pila Ultimo = Pila.SacarPila();
                if(Ultimo.getSentencia().equals("IDEVAR")){
                    Tabla_Errores.AnadirError("ErrorSem4", NumLinea,"");
                }else if(Ultimo.getSentencia().equals("COMIENZO")){
                    if(RS.PosiElementos[3] == 0){
                        Tabla_Errores.AnadirError("ErrorSem30", NumLinea,"");
                    }
                }else{
                    Tabla_Errores.AnadirError("ErrorSem10", Ultimo.getNumLinea(), Ultimo.getSentencia());
                }
            }

        }catch(Exception e) {
            System.out.println("ERROR 404, No se encontro el archivo: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        Analizador_Semantico as = new Analizador_Semantico();
        as.LeerTxt();
    }
    

}
