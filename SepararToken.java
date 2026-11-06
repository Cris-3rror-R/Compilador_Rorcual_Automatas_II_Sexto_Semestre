import java.util.ArrayList;

public class SepararToken {
 
    public static String[] Separar(String Aux){
    char AuxCar;
    String AuxTok = "";
    ArrayList<String> Tokens = new ArrayList<>();
    for(int x = 0; x<Aux.length(); x++){
        AuxCar = Aux.charAt(x);
        if(AuxCar == '"'){
            if(!AuxTok.isEmpty()){
                Tokens.add(AuxTok);
                AuxTok = "";
            }
            AuxTok = AuxTok + AuxCar;
            x++;
            while(x < Aux.length()){
                AuxCar = Aux.charAt(x);
                AuxTok = AuxTok + AuxCar;
                if(AuxCar == '"'){                   
                    break;
                }
                x++;
            }
            Tokens.add(AuxTok);
            AuxTok = "";
        }else{
            if((AuxCar != ' ') && (AuxCar != '\t')){
                AuxTok = AuxTok + AuxCar;
            }else{
                if(!AuxTok.isEmpty()){
                    Tokens.add(AuxTok);
                    AuxTok = "";
                }
            }
        }
    }
    if(!AuxTok.isEmpty()){
        Tokens.add(AuxTok);
    }
    return Tokens.toArray(new String[0]);
}
    
}
