import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Analizador_Sintatico {

    public static SepararToken Tokens = new SepararToken();
    public static Reglas_Produccion RP = new Reglas_Produccion();
    public int NumLinea = 0;


    public void LeerTxt(){
        try(FileReader Bloc = new FileReader("Prueba.txt");
            BufferedReader Lector = new BufferedReader(Bloc);){
            String AuxLinea = Lector.readLine();
            while(AuxLinea != null){
                NumLinea++;
                String [] AuxToken = Tokens.Separar(AuxLinea);
                ArrayList <String> ListaToken = new ArrayList<String>(Arrays.asList(AuxToken));
                RP.ValTokens(AuxLinea, ListaToken, NumLinea);
                AuxLinea = Lector.readLine();
            }

        }catch(Exception e) {
            System.out.println("ERROR 404, No se encontro el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Analizador_Sintatico as = new Analizador_Sintatico();
        as.LeerTxt();
    }
}
