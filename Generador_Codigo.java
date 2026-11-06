import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Generador_Codigo {
    public static SepararToken Tokens = new SepararToken();
    public int NumLinea = 0;
    public ArrayList<String> Generar = new ArrayList<>();
    public ArrayList<String> Variables = new ArrayList<>();
    public ArrayList<String> Procedimientos = new ArrayList<>();
    public Reglas_Produccion RP = new Reglas_Produccion();
    public int AuxMsg = 0;
    public int AuxSi = 0;
    public int AuxSino = 0;
    public int AuxFsi = 0;
    public int AuxInme = 0;
    public int AuxSumer = 0;
    public Control Pila = new Control();

    public void LeerTxt() {
        try (FileReader Bloc = new FileReader("Prueba.txt");
                BufferedReader Lector = new BufferedReader(Bloc)) {
            String AuxLinea = Lector.readLine();
            while (AuxLinea != null) {
                NumLinea++;
                String[] AuxToken = Tokens.Separar(AuxLinea);
                ArrayList<String> ListaToken = new ArrayList<String>(Arrays.asList(AuxToken));
                Comprobar(AuxLinea, ListaToken, NumLinea);
                AuxLinea = Lector.readLine();
            }
        } catch (Exception e) {
            System.out.println("ERROR 404, No se encontro el archivo: " + e.getMessage());
        }
        Variables.add(".STACK");
        Variables.add("END Inicio");
        Procedimientos.forEach(a -> Generar.add(a));
        Variables.forEach((a) -> Generar.add(a));

        try (BufferedWriter Escritor = new BufferedWriter(new FileWriter("Prueba.asm"))) {
            for (int x = 0; x < Generar.size(); x++) {
                Escritor.write(Generar.get(x));
                Escritor.newLine();
            }
        } catch (Exception e) {
            System.err.println("ERROR 404, No se encontro el archivo: " + e.getMessage());
        }
    }

    public void Comprobar(String Linea, ArrayList<String> Token, int NumLinea) {
        if ((Linea.isEmpty()) || (Token.isEmpty())) {
            return;
        }
        Infierno(Linea, Token, NumLinea);
    }

    public void Infierno(String Linea, ArrayList<String> Token, int NumLinea) {
        Magnitud();
        String Aux = Token.get(0).toString();
        Magnitud();
        switch (Aux) {
            case "COMIENZO":
                Generar.add(".MODEL SMALL");
                Generar.add(".CODE");
                Generar.add("Inicio:");
                Generar.add("mov Ax, @Data");
                Generar.add("mov Ds, Ax");
                break;
            case "IDEVAR":
                Variables.add(".DATA");
                break;
            case "FDEVAR":
                break;
            case "FINAL":
                Generar.add("mov Ax, 4C00h");
                Generar.add("int 21h");
                break;
            case "Ent", "Dec":
                Variables.add(Token.get(1) + " db 8,?,8 dup (24h)");
                break;
            case "Cad":
                Variables.add(Token.get(1) + " 20, ?, 20 dup (24h)");
                break;
            case "LEER":
                if (!Procedimientos.contains("LEER Proc")) {
                    Procedimientos.add("LEER Proc");
                    Procedimientos.add("mov Ah, 0Ah");
                    Procedimientos.add("int 21h");
                    Procedimientos.add("ret");
                    Procedimientos.add("LEER EndP");
                }
                Generar.add("mov Si, offset " + Token.get(2));
                Generar.add("inc Si");
                Generar.add("mov byte ptr [Si], 0");
                Generar.add("mov Dx, offset " + Token.get(2));
                Generar.add("call LEER");
                break;
            case "EXHALAR":
                if (!Procedimientos.contains("EXHALAR Proc")) {
                    Procedimientos.add("EXHALAR Proc");
                    Procedimientos.add("mov Ah, 09h");
                    Procedimientos.add("int 21h");
                    Procedimientos.add("ret");
                    Procedimientos.add("EXHALAR EndP");
                }
                if (RP.ComproRex(RP.Consta, Token.get(2))) {
                    Variables.add("Mge" + AuxMsg + " db " + GenerarCons(Token));
                    Generar.add("mov Dx, offset " + "Mge" + AuxMsg);
                    Generar.add("call EXHALAR ");
                    AuxMsg++;
                } else {
                    Generar.add("mov Dx, offset " + Token.get(2) + " +2");
                    Generar.add("call EXHALAR");
                }
                break;
            case "EXHALARLN":
                if (!Procedimientos.contains("EXHALAR Proc")) {
                    Procedimientos.add("EXHALAR Proc");
                    Procedimientos.add("mov Ah, 09h");
                    Procedimientos.add("int 21h");
                    Procedimientos.add("ret");
                    Procedimientos.add("EXHALAR EndP");
                }
                if (!Variables.contains("Salto db 10, 13, 24h")) {
                    Variables.add("Salto db 10, 13, 24h");
                }
                if (RP.ComproRex(RP.Consta, Token.get(2))) {
                    Variables.add("Mge" + AuxMsg + " db " + GenerarCons(Token));
                    Generar.add("mov Dx, offset " + "Mge" + AuxMsg);
                    Generar.add("call EXHALAR ");
                    AuxMsg++;
                } else {
                    Generar.add("mov Dx, offset " + Token.get(2) + " +2");
                    Generar.add("call EXHALAR");
                }
                Generar.add("mov Dx, offset " + "Salto");
                Generar.add("call EXHALAR ");
                break;
            case "SI":
                Condicionales(Token, "SI" + AuxSi, "FSI" + AuxSi);
                Generar.add("SI" + AuxSi + ":");
                Pila.MeterPila("SI", AuxSi);
                AuxSi++;
                break;
            case "FSI":
                int PosiFsi = Pila.SacarPila().getNumLinea();
                AuxFsi = PosiFsi;
                Generar.add("FSI" + PosiFsi + ":");
                break;
            case "SINO":
                AuxSino = AuxFsi;
                int IndiFsi = Generar.indexOf("FSI" + AuxSino + ":");
                Generar.add(IndiFsi, "jmp FSINO" + AuxSino);
                Pila.MeterPila("SINO", AuxSino);
                break;
            case "FSINO":
                int AuxFsino = Pila.SacarPila().getNumLinea();
                Generar.add("FSINO" + AuxFsino + ":");
                break;
            case "INMERSION":
                InmerValores(Token.get(2), Token.get(4), Token.get(6));
                break;
            case "FINMERSION":
                int IndiFinm = Pila.BuscarPila().getNumLinea();
                String AuxVariable = Pila.BuscarPila().getSentencia().split(" ")[1];
                Pila.SacarPila();
                Generar.add("mov Si, offset " + AuxVariable);
                Generar.add("call ConvertirDecimal");
                Generar.add("inc Al");
                Generar.add("mov Di, offset " + AuxVariable + " +2");
                Generar.add("call ConvertirAscii");
                Generar.add("pop Cx");
                Generar.add("loop INMERSION" + IndiFinm);
                Generar.add("pop Cx");
                break;
            case "SUMERGIR":
                Pila.MeterPila("SUMERGIR", AuxSumer);
                Generar.add("SUMERGIR" + AuxSumer + ":");
                Condicionales(Token, "SUMERGIR" + (AuxSumer + 1), "FSUMERGIR" + AuxSumer);
                Generar.add("SUMERGIR" + (AuxSumer + 1) + ":");
                AuxSumer = AuxSumer + 2;
                break;
            case "FSUMERGIR":
                int IndiSumer = Pila.SacarPila().getNumLinea();
                Generar.add("jmp SUMERGIR" + IndiSumer);
                Generar.add("FSUMERGIR" + IndiSumer + ":");
                break;
            default:
                Magnitud();
                if (Token.size() > 1 && Token.get(1).equals("=")) {
                    if (RP.ComproRex(RP.Arit, Linea)) {
                        switch (Token.get(3)) {
                            case "+":
                                if (!Procedimientos.contains("SUMA Proc")) {
                                    Procedimientos.add("SUMA Proc");
                                    Procedimientos.add("add Al, Ah");
                                    Procedimientos.add("mov Ah, 0");
                                    Procedimientos.add("ret");
                                    Procedimientos.add("SUMA EndP");
                                }
                                Generar.add("push Cx");
                                if (Variables.contains(Token.get(2) + " db 8,?,8 dup (24h)")) {
                                    Generar.add("mov Si, offset " + Token.get(2));
                                    Generar.add("call ConvertirDecimal");
                                } else {
                                    Generar.add("mov Al, " + Token.get(2));
                                }
                                Generar.add("push Ax");
                                if (Variables.contains(Token.get(4) + " db 8,?,8 dup (24h)")) {
                                    Generar.add("mov Si, offset " + Token.get(4));
                                    Generar.add("call ConvertirDecimal");
                                } else {
                                    Generar.add("mov Al, " + Token.get(4));
                                }
                                Generar.add("mov Bl, Al");

                                Generar.add("pop Ax");
                                Generar.add("mov Ah, Bl");
                                Generar.add("call SUMA");
                                Generar.add("mov Di, offset " + Token.get(0) + " +2");
                                Generar.add("call ConvertirAscii");
                                Generar.add("mov byte ptr " + Token.get(0) + "+1, 3");
                                Generar.add("pop Cx");
                                break;

                            case "-":
                                if (!Procedimientos.contains("RESTA Proc")) {
                                    Procedimientos.add("RESTA Proc");
                                    Procedimientos.add("sub Al, Ah");
                                    Procedimientos.add("mov Ah, 0");
                                    Procedimientos.add("ret");
                                    Procedimientos.add("RESTA EndP");
                                }
                                Generar.add("push Cx");
                                if (Variables.contains(Token.get(2) + " db 8,?,8 dup (24h)")) {
                                    Generar.add("mov Si, offset " + Token.get(2));
                                    Generar.add("call ConvertirDecimal");
                                } else {
                                    Generar.add("mov Al, " + Token.get(2));
                                }
                                Generar.add("push Ax");
                                if (Variables.contains(Token.get(4) + " db 8,?,8 dup (24h)")) {
                                    Generar.add("mov Si, offset " + Token.get(4));
                                    Generar.add("call ConvertirDecimal");
                                } else {
                                    Generar.add("mov Al, " + Token.get(4));
                                }
                                Generar.add("mov Bl, Al");

                                Generar.add("pop Ax");
                                Generar.add("mov Ah, Bl");
                                Generar.add("call RESTA");
                                Generar.add("mov Di, offset " + Token.get(0) + " +2");
                                Generar.add("call ConvertirAscii");
                                Generar.add("mov byte ptr " + Token.get(0) + "+1, 3");
                                Generar.add("pop Cx");
                                break;

                            case "*":
                                if (!Procedimientos.contains("MULTI Proc")) {
                                    Procedimientos.add("MULTI Proc");
                                    Procedimientos.add("mul Bl");
                                    Procedimientos.add("ret");
                                    Procedimientos.add("MULTI EndP");
                                }
                                Generar.add("push Cx");

                                if (Variables.contains(Token.get(2) + " db 8,?,8 dup (24h)")) {
                                    Generar.add("mov Si, offset " + Token.get(2));
                                    Generar.add("call ConvertirDecimal");
                                } else {
                                    Generar.add("mov Al, " + Token.get(2));
                                }
                                Generar.add("push Ax");
                                if (Variables.contains(Token.get(4) + " db 8,?,8 dup (24h)")) {
                                    Generar.add("mov Si, offset " + Token.get(4));
                                    Generar.add("call ConvertirDecimal");
                                } else {
                                    Generar.add("mov Al, " + Token.get(4));
                                }
                                Generar.add("mov Bl, Al");

                                Generar.add("pop Ax");
                                Generar.add("call MULTI");
                                Generar.add("mov Di, offset " + Token.get(0) + " +2");
                                Generar.add("call ConvertirAscii");
                                Generar.add("mov byte ptr " + Token.get(0) + "+1, 3");
                                Generar.add("pop Cx");
                                break;

                            case "%", "/", "\\":
                                if (!Procedimientos.contains("DIVIDIR Proc")) {
                                    if (!Variables.contains("AuxResi db ?")) {
                                        Variables.add("AuxResi db ?");
                                    }
                                    Procedimientos.add("DIVIDIR Proc");
                                    Procedimientos.add("xor Ah, Ah");
                                    Procedimientos.add("div Bl");
                                    Procedimientos.add("mov AuxResi, Ah");
                                    Procedimientos.add("ret");
                                    Procedimientos.add("DIVIDIR EndP");
                                }
                                Generar.add("push Cx");

                                if (Variables.contains(Token.get(2) + " db 8,?,8 dup (24h)")) {
                                    Generar.add("mov Si, offset " + Token.get(2));
                                    Generar.add("call ConvertirDecimal");
                                } else {
                                    Generar.add("mov Al, " + Token.get(2));
                                }
                                Generar.add("push Ax");
                                if (Variables.contains(Token.get(4) + " db 8,?,8 dup (24h)")) {
                                    Generar.add("mov Si, offset " + Token.get(4));
                                    Generar.add("call ConvertirDecimal");
                                } else {
                                    Generar.add("mov Al, " + Token.get(4));
                                }
                                Generar.add("mov Bl, Al");

                                Generar.add("pop Ax");
                                Generar.add("call DIVIDIR");

                                if (Token.get(3).equals("/") || Token.get(3).equals("\\")) {
                                    Generar.add("mov Di, offset " + Token.get(0) + " +2");
                                    Generar.add("call ConvertirAscii");
                                    Generar.add("mov byte ptr " + Token.get(0) + "+1, 3");
                                } else {
                                    Generar.add("mov Al, AuxResi");
                                    Generar.add("mov Di, offset " + Token.get(0) + " +2");
                                    Generar.add("call ConvertirAscii");
                                    Generar.add("mov byte ptr " + Token.get(0) + "+1, 3");
                                }
                                Generar.add("pop Cx");
                                break;

                            case "^":
                                if (!Procedimientos.contains("POTENCIA Proc")) {
                                    Procedimientos.add("POTENCIA Proc");
                                    Procedimientos.add("mov Bl, Al");
                                    Procedimientos.add("mov Al, 1");
                                    Procedimientos.add("mov Ah, 0");
                                    Procedimientos.add("cmp Cl, 0");
                                    Procedimientos.add("je FinPoten");
                                    Procedimientos.add("CicloPoten:");
                                    Procedimientos.add("mul Bl");
                                    Procedimientos.add("dec Cl");
                                    Procedimientos.add("jnz CicloPoten");
                                    Procedimientos.add("FinPoten:");
                                    Procedimientos.add("ret");
                                    Procedimientos.add("POTENCIA EndP");
                                }
                                Generar.add("push Cx");

                                if (Variables.contains(Token.get(2) + " db 8,?,8 dup (24h)")) {
                                    Generar.add("mov Si, offset " + Token.get(2));
                                    Generar.add("call ConvertirDecimal");
                                } else {
                                    Generar.add("mov Al, " + Token.get(2));
                                }
                                Generar.add("push Ax");
                                if (Variables.contains(Token.get(4) + " db 8,?,8 dup (24h)")) {
                                    Generar.add("mov Si, offset " + Token.get(4));
                                    Generar.add("call ConvertirDecimal");
                                } else {
                                    Generar.add("mov Al, " + Token.get(4));
                                }
                                Generar.add("mov Cl, Al");

                                Generar.add("pop Ax");
                                Generar.add("call POTENCIA");
                                Generar.add("mov Di, offset " + Token.get(0) + " +2");
                                Generar.add("call ConvertirAscii");
                                Generar.add("mov byte ptr " + Token.get(0) + "+1, 3");
                                Generar.add("pop Cx");
                                break;
                        }
                    } else {
                        if (RP.ComproRex(RP.Asignar, Linea)) {
                            Generar.add("push Cx");
                            if (Variables.contains(Token.get(2) + " db 8,?,8 dup (24h)")) {
                                Generar.add("mov Si, offset " + Token.get(2));
                                Generar.add("call ConvertirDecimal");
                            } else {
                                Generar.add("mov Al, " + Token.get(2));
                            }
                            Generar.add("mov Di, offset " + Token.get(0));
                            Generar.add("inc Di");
                            Generar.add("mov byte ptr [Di], 3");
                            Generar.add("mov Di, offset " + Token.get(0) + " +2");
                            Generar.add("call ConvertirAscii");
                            Generar.add("pop Cx");
                        }
                    }
                }
                break;
        }
    }

    public String GenerarCons(ArrayList<String> Aux) {
        String Consta = "";
        if (Aux.get(2).startsWith("\"") && Aux.get(2).endsWith("\"")) {
            Consta = Aux.get(2).substring(0, Aux.get(2).length() - 1) + " $\"";
        } else {
            Consta = "\"" + Aux.get(2) + " $\"";
        }
        return Consta;
    }

    public void Magnitud() {
        if (!Procedimientos.contains("ConvertirDecimal Proc")) {
            Procedimientos.add("ConvertirDecimal Proc");
            Procedimientos.add("xor Cx, Cx");
            Procedimientos.add("mov Cl, [Si+1]");
            Procedimientos.add("cmp Cl, 0");
            Procedimientos.add("je FinConvDec");
            Procedimientos.add("add Si, 2");
            Procedimientos.add("xor Bx, Bx");
            Procedimientos.add("ConvDecCiclo:");
            Procedimientos.add("mov Al, 10");
            Procedimientos.add("mul Bl");
            Procedimientos.add("mov Bl, Al");
            Procedimientos.add("mov Al, [Si]");
            Procedimientos.add("sub Al, 30h");
            Procedimientos.add("add Bl, Al");
            Procedimientos.add("inc Si");
            Procedimientos.add("loop ConvDecCiclo");
            Procedimientos.add("mov Al, Bl");
            Procedimientos.add("FinConvDec:");
            Procedimientos.add("ret");
            Procedimientos.add("ConvertirDecimal EndP");
        }
        if (!Procedimientos.contains("ConvertirAscii Proc")) {
            Procedimientos.add("ConvertirAscii Proc");
            Procedimientos.add("mov Ah, 0");
            Procedimientos.add("mov Bl, 100");
            Procedimientos.add("div Bl");
            Procedimientos.add("add Al, 30h");
            Procedimientos.add("mov [Di], Al");
            Procedimientos.add("inc Di");
            Procedimientos.add("mov Al, Ah");
            Procedimientos.add("mov Ah, 0");
            Procedimientos.add("mov Bl, 10");
            Procedimientos.add("div Bl");
            Procedimientos.add("add Al, 30h");
            Procedimientos.add("mov [Di], Al");
            Procedimientos.add("inc Di");
            Procedimientos.add("mov Al, Ah");
            Procedimientos.add("add Al, 30h");
            Procedimientos.add("mov [Di], Al");
            Procedimientos.add("mov byte ptr [Di+1], 24h");
            Procedimientos.add("ret");
            Procedimientos.add("ConvertirAscii EndP");
        }
    }

    public void SimboCond(String Aux, String Salto) {
        switch (Aux) {
            case ">":
                Generar.add("jna " + Salto);
                break;
            case "<":
                Generar.add("jnb " + Salto);
                break;
            case ">=":
                Generar.add("jnae " + Salto);
                break;
            case "<=":
                Generar.add("jnbe " + Salto);
                break;
            case "==":
                Generar.add("jne " + Salto);
                break;
            case "!=":
                Generar.add("je " + Salto);
                break;
        }
    }
    public void SimboCondInverso(String Aux, String Salto) {
        switch (Aux) {
            case ">":
                Generar.add("ja "  + Salto);
                break;
            case "<":
                Generar.add("jb "  + Salto);
                break;
            case ">=":
                Generar.add("jae " + Salto);
                break;
            case "<=":
                Generar.add("jbe " + Salto);
                break;
            case "==":
                Generar.add("je "  + Salto);
                break;
            case "!=":
                Generar.add("jne " + Salto);
                break;
        }
    }

    public void CondVariables(String Aux1, String Aux2) {
        Generar.add("push Cx");
        if (!RP.ComproRex(RP.Consta, Aux1)) {
            Generar.add("mov Si, offset " + Aux1);
            Generar.add("call ConvertirDecimal");
        } else {
            Generar.add("mov Al, " + Aux1);
        }
        Generar.add("push Ax");
        if (!RP.ComproRex(RP.Consta, Aux2)) {
            Generar.add("mov Si, offset " + Aux2);
            Generar.add("call ConvertirDecimal");
            Generar.add("mov Bl, Al");
        } else {
            Generar.add("mov Bl, " + Aux2);
        }
        Generar.add("pop Ax");
        Generar.add("cmp Al, Bl");
        Generar.add("pop Cx");
    }

    public void InmerValores(String Variable, String Inicial, String Final) {
        Generar.add("push Cx");
        Generar.add("xor Ax, Ax");
        if (Variables.contains(Inicial + " db 8,?,8 dup (24h)")) {
            Generar.add("mov Si, offset " + Inicial);
            Generar.add("call ConvertirDecimal");
        } else {
            Generar.add("mov Al, " + Inicial);
        }
        Generar.add("push Ax");
        if (Variables.contains(Final + " db 8,?,8 dup (24h)")) {
            Generar.add("mov Si, offset " + Final);
            Generar.add("call ConvertirDecimal");
        } else {
            Generar.add("mov Al, " + Final);
        }
        Generar.add("mov Dl, Al");
        Generar.add("pop Ax");
        Generar.add("mov Ah, Dl");
        Generar.add("push Ax");
        Generar.add("xor Cx, Cx");
        Generar.add("mov Cl, Ah");
        Generar.add("sub Cl, Al");
        Generar.add("add Cl, 1");
        Generar.add("pop Bx");
        Generar.add("push Cx");
        Generar.add("mov Ax, Bx");
        Generar.add("mov Di, offset " + Variable + " +2");
        Generar.add("call ConvertirAscii");
        Generar.add("mov byte ptr " + Variable + "+1, 3");
        Generar.add("pop Cx");
        Generar.add("INMERSION" + AuxInme + ":");
        Generar.add("push Cx");
        Pila.MeterPila("INMERSION " + Variable, AuxInme);
        AuxInme++;
    }

    public void Condicionales(ArrayList<String> Aux, String Inicial, String Final) {
        if (Aux.contains("LoY")) {
            CondVariables(Aux.get(3), Aux.get(5));
            SimboCond(Aux.get(4), Final);

            CondVariables(Aux.get(9), Aux.get(11));
            SimboCond(Aux.get(10), Final);

        } else if (Aux.contains("LoO")) {
            CondVariables(Aux.get(3), Aux.get(5));
            SimboCondInverso(Aux.get(4), Inicial);

            CondVariables(Aux.get(9), Aux.get(11));
            SimboCond(Aux.get(10), Final);

        } else {
            CondVariables(Aux.get(2), Aux.get(4));
            SimboCond(Aux.get(3), Final);
        }
    }

    public static void main(String[] args) {
        Generador_Codigo as = new Generador_Codigo();
        as.LeerTxt();
    }

}
