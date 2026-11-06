import java.util.ArrayList;

public class Reglas_Semanticas {

    public static Reglas_Produccion RP = new Reglas_Produccion();
    static int PosiElementos[] = { 0, 0, 0, 0 };
    public ArrayList<TablaSimbolos> TablaSim = new ArrayList<>();
    public ArrayList<String> AuxVariables = new ArrayList<>();
    public Control Pila = new Control();
    public String LineaAnte;

    public void ValSemantica(String Linea, ArrayList<String> Token, ArrayList<String> PalaRes,
            ArrayList<Character> Alfa, int NumLinea) {
        if ((Linea.isEmpty()) || (Token.isEmpty())) {
            return;
        }
        Token.replaceAll(s -> s.trim());
        if (Comprobar(Linea, Token, PalaRes, NumLinea)) {
        } else {
        }
        LineaAnte = Linea;
    }

    public boolean Comprobar(String Linea, ArrayList<String> Token, ArrayList<String> PalaRes, int NumLinea) {
        String Aux = Token.get(0).toString();
        switch (Aux) {
            case "COMIENZO":
                if (RP.ComproRex(RP.Comienzo, Linea)) {
                    if (ComHorizontal(Linea, Token, PalaRes, NumLinea)) {
                        if (ComVertical(Linea, Token, NumLinea)) {
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            case "IDEVAR":
                if (RP.ComproRex(RP.InicioVar, Linea)) {
                    if (ComHorizontal(Linea, Token, PalaRes, NumLinea)) {
                        if (ComVertical(Linea, Token, NumLinea)) {
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            case "FDEVAR":
                if (RP.ComproRex(RP.FinVar, Linea)) {
                    if (ComHorizontal(Linea, Token, PalaRes, NumLinea)) {
                        if (ComVertical(Linea, Token, NumLinea)) {
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            case "FINAL":
                if (RP.ComproRex(RP.Final, Linea)) {
                    if (ComHorizontal(Linea, Token, PalaRes, NumLinea)) {
                        if (ComVertical(Linea, Token, NumLinea)) {
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            case "Ent", "Cad", "Dec":
                if (RP.ComproRex(RP.Definir, Linea)) {
                    if (ComVertical(Linea, Token, NumLinea)) {
                        if (ComHorizontal(Linea, Token, PalaRes, NumLinea)) {
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            case "LEER":
                if (RP.ComproRex(RP.Leer, Linea)) {
                    if (ComHorizontal(Linea, Token, PalaRes, NumLinea)) {
                        if (ComVertical(Linea, Token, NumLinea)) {
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            case "EXHALAR","EXHALARLN":
                if (RP.ComproRex(RP.Exhalar, Linea) || RP.ComproRex(RP.ExhalarLn, Linea)) {
                    if (ComHorizontal(Linea, Token, PalaRes, NumLinea)) {
                        if (ComVertical(Linea, Token, NumLinea)) {
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            case "SI":
                if (RP.ComproRex(RP.Si, Linea)) {
                    if (ComVertical(Linea, Token, NumLinea)) {
                        if (ComHorizontal(Linea, Token, PalaRes, NumLinea)) {
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            case "FSI":
                if (RP.ComproRex(RP.Fsi, Linea)) {
                    if (ComVertical(Linea, Token, NumLinea)) {
                        if (ComHorizontal(Linea, Token, PalaRes, NumLinea)) {
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            case "SINO":
                if (RP.ComproRex(RP.Sino, Linea)) {
                    if (ComVertical(Linea, Token, NumLinea)) {
                        if (ComHorizontal(Linea, Token, PalaRes, NumLinea)) {
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            case "FSINO":
                if (RP.ComproRex(RP.Fsino, Linea)) {
                    if (ComVertical(Linea, Token, NumLinea)) {
                        if (ComHorizontal(Linea, Token, PalaRes, NumLinea)) {
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            case "SUMERGIR":
                if (RP.ComproRex(RP.Sumergir, Linea)) {
                    if (ComVertical(Linea, Token, NumLinea)) {
                        if (ComHorizontal(Linea, Token, PalaRes, NumLinea)) {
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            case "INMERSION":
                if (RP.ComproRex(RP.Inmersion, Linea)) {
                    if (ComVertical(Linea, Token, NumLinea)) {
                        if (ComHorizontal(Linea, Token, PalaRes, NumLinea)) {
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            case "FINMERSION":
                if (RP.ComproRex(RP.FinInmersion, Linea)) {
                    if (ComVertical(Linea, Token, NumLinea)) {
                        if (ComHorizontal(Linea, Token, PalaRes, NumLinea)) {
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            case "FSUMERGIR":
                if (RP.ComproRex(RP.FSumergir, Linea)) {
                    if (ComVertical(Linea, Token, NumLinea)) {
                        if (ComHorizontal(Linea, Token, PalaRes, NumLinea)) {
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            default:
                if(Token.size() > 1 && Token.get(1).equals("=")) {
                    if (RP.ComproRex(RP.Arit, Linea)) {
                        if (ComHorizontal(Linea, Token, PalaRes, NumLinea)) {
                            if (ComVertical(Linea, Token, NumLinea)) {
                                return true;
                            }
                        } else {
                            return false;
                        }
                    }else{
                        if(RP.ComproRex(RP.Asignar, Linea)){
                            if (ComHorizontal(Linea, Token, PalaRes, NumLinea)) {
                                if (ComVertical(Linea, Token, NumLinea)) {
                                    return true;
                                }
                            } else {
                                return false;
                            }
                        }else{
                            return false;
                        }
                    }
                }else{
                    String lineaLimpia = Linea.trim().replaceAll(";", "").trim();
                    if(RP.ComproRex(RP.Consta, lineaLimpia)){
                        Tabla_Errores.AnadirError("ErrorSem34", NumLinea, Linea);
                        return false;
                    }
                }
        }
        return false;
    }

    public boolean ComHorizontal(String Linea, ArrayList<String> Token, ArrayList<String> PalaRes, int NumLinea) {
        String Aux = Token.get(0).toString();
        switch (Aux) {
            case "COMIENZO", "IDEVAR", "FDEVAR", "FINAL", "FSI", "SINO", "FSINO", "FINMERSION", "FSUMERGIR":
                return true;
            case "Ent", "Cad", "Dec":
                if (ValidarID(Token.get(1), PalaRes)) {
                    if (AnadirID(Token.get(1), Aux, NumLinea)) {
                        return true;
                    } else {
                        Tabla_Errores.AnadirError("ErrorSem18", NumLinea, Token.get(1));
                        return false;
                    }
                } else {
                    Tabla_Errores.AnadirError("ErrorSem5", NumLinea, Token.get(1));
                    return false;
                }
            case "LEER":
                if (ExisteID(Token.get(2))) {
                    return true;
                } else {
                    if (RP.ComproRex(RP.Consta, Token.get(2))) {
                        Tabla_Errores.AnadirError("ErrorSem14", NumLinea, Token.get(2));
                    } else {
                        Tabla_Errores.AnadirError("ErrorSem6", NumLinea, Token.get(2));
                    }
                    return false;
                }
            case "EXHALAR","EXHALARLN":
                if (ExisteID(Token.get(2))) {
                    return true;
                } else {
                    if (RP.ComproRex(RP.Consta, Token.get(2))) {
                        return true;
                    } else {
                        Tabla_Errores.AnadirError("ErrorSem6", NumLinea, Token.get(2));
                        return false;
                    }
                }
            case "SI", "SUMERGIR":
                if (CompCondicionales(Token, NumLinea)) {
                    return true;
                } else {
                    return false;
                }
            case "INMERSION":
                if (CompInmersion(Token, NumLinea)) {
                    return true;
                } else {
                    return false;
                }
            default:
                if (RP.ComproRex(RP.Arit, Linea)) {
                    if (ExisteID(Aux)) {
                        if ((ExisteID(Token.get(2))) || (RP.ComproRex(RP.Consta, Token.get(2)))) {
                            if ((ExisteID(Token.get(4))) || (RP.ComproRex(RP.Consta, Token.get(4)))) {
                                if (ObtenerTipo(Aux).equals(ObtenerTipo(Token.get(2)))
                                        && (ObtenerTipo(Aux).equals(ObtenerTipo(Token.get(4))))) {
                                    if (CompArit(ObtenerTipo(Aux), Token.get(3), NumLinea)) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                } else {
                                    Tabla_Errores.AnadirError("ErrorSem7", NumLinea,
                                            Aux + "," + Token.get(2) + "," + Token.get(4));
                                    return false;
                                }
                            } else {
                                Tabla_Errores.AnadirError("ErrorSem6", NumLinea, Token.get(4));
                                return false;
                            }
                        } else {
                            Tabla_Errores.AnadirError("ErrorSem6", NumLinea, Token.get(2));
                            if ((ExisteID(Token.get(4))) || (RP.ComproRex(RP.Consta, Token.get(4)))) {
                            } else {
                                Tabla_Errores.AnadirError("ErrorSem6", NumLinea, Token.get(4));
                            }
                            return false;
                        }
                    } else {
                        Tabla_Errores.AnadirError("ErrorSem6", NumLinea, Aux);
                        if ((ExisteID(Token.get(2))) || (RP.ComproRex(RP.Consta, Token.get(2)))) {
                        } else {
                            Tabla_Errores.AnadirError("ErrorSem6", NumLinea, Token.get(2));
                        }
                        if ((ExisteID(Token.get(4))) || (RP.ComproRex(RP.Consta, Token.get(4)))) {
                        } else {
                            Tabla_Errores.AnadirError("ErrorSem6", NumLinea, Token.get(4));
                        }
                        return false;
                    }

                } else {
                    if (RP.ComproRex(RP.Asignar, Linea)) {
                        if (ExisteID(Aux)){
                            if (ObtenerTipo(Aux).equals(ObtenerTipo(Token.get(2)))) {
                                return true;
                            } else {
                                Tabla_Errores.AnadirError("ErrorSem7", NumLinea, Aux);
                                return false;
                            }
                        } else {
                            Tabla_Errores.AnadirError("ErrorSem6", NumLinea, Aux);
                            return false;
                        }
                    }
                }
        }
        return false;
    }

    public boolean ComVertical(String Linea, ArrayList<String> Token, int NumLinea) {
        String Aux = Token.get(0).toString();
        switch (Aux) {
            case "COMIENZO":
                if (PosiElementos[0] == 0) {
                    PosiElementos[0] = NumLinea;
                    Pila.MeterPila("COMIENZO", NumLinea);
                    return true;
                } else {
                    Tabla_Errores.AnadirError("ErrorSem22", NumLinea, Linea);
                    return false;
                }
            case "IDEVAR":
                if (CompInicio(NumLinea, Linea)) {
                    if (PosiElementos[1] == 0) {
                        PosiElementos[1] = NumLinea;
                        Pila.MeterPila("IDEVAR", NumLinea);
                        return true;
                    } else {
                        Tabla_Errores.AnadirError("ErrorSem26", NumLinea, Linea);
                        return false;
                    }
                }
                return false;
            case "FDEVAR":
                if (CompInicio(NumLinea, Linea)) {
                    if (PosiElementos[1] == 0) {
                        Tabla_Errores.AnadirError("ErrorSem11", NumLinea, Aux);
                        return false;
                    }
                    if (PosiElementos[2] == 0) {
                        PosiElementos[2] = NumLinea;
                        if (!Pila.PilaVacia()) {
                            if (Pila.BuscarPila().getSentencia().equals("IDEVAR")) {
                                Pila.SacarPila();
                                return true;
                            } else {
                                Tabla_Errores.AnadirError("ErrorSem28", NumLinea, Aux);
                                return false;
                            }
                        } else {
                            Tabla_Errores.AnadirError("ErrorSem11", NumLinea, Aux);
                            return false;
                        }
                    } else {
                        Tabla_Errores.AnadirError("ErrorSem27", NumLinea, Linea);
                        return false;
                    }
                }
                return false;
            case "Ent", "Cad", "Dec":
                if (CompInicio(NumLinea, Linea)) {
                    if (CompBloqueDeclaracion(NumLinea, Linea)) {
                        return true;
                    }
                }
                return false;
            case "LEER", "EXHALAR","EXHALARLN":
                if (CompEstruBasica(NumLinea, Linea)) {
                    return true;
                }
                return false;
            case "SI":
                if (CompEstruBasica(NumLinea, Linea)) {
                    Pila.MeterPila("SI", NumLinea);
                    return true;
                }
                return false;
            case "FSI":
                if (CompEstruBasica(NumLinea, Linea)) {
                    if (!Pila.PilaVacia()) {
                        if (Pila.BuscarPila().getSentencia().equals("SI")) {
                            Pila.SacarPila();
                            return true;
                        } else {
                            Tabla_Errores.AnadirError("ErrorSem28", NumLinea, Aux);
                            return false;
                        }
                    } else {
                        Tabla_Errores.AnadirError("ErrorSem11", NumLinea, Aux);
                        return false;
                    }
                }
                return false;
            case "SINO":
                if (CompEstruBasica(NumLinea, Linea)) {
                    if (RP.ComproRex(RP.Fsi, LineaAnte)) {
                        Pila.MeterPila("SINO", NumLinea);
                        return true;
                    } else {
                        Tabla_Errores.AnadirError("ErrorSem13", NumLinea, Aux);
                        return false;
                    }
                }
                return false;
            case "FSINO":
                if (CompEstruBasica(NumLinea, Linea)) {
                    if (!Pila.PilaVacia()) {
                        if (Pila.BuscarPila().getSentencia().equals("SINO")) {
                            Pila.SacarPila();
                            return true;
                        } else {
                            Tabla_Errores.AnadirError("ErrorSem28", NumLinea, Aux);
                            return false;
                        }
                    } else {
                        Tabla_Errores.AnadirError("ErrorSem11", NumLinea, Aux);
                        return false;
                    }
                }
                return false;
            case "INMERSION":
                if (CompEstruBasica(NumLinea, Linea)) {
                    if (!AuxVariables.contains(Token.get(2))) {
                        AuxVariables.add(Token.get(2));
                        Pila.MeterPila("INMERSION", NumLinea);
                        return true;
                    } else {
                        Tabla_Errores.AnadirError("ErrorSem33", NumLinea, Aux);
                        return false;
                    }
                }
                return false;
            case "FINMERSION":
                if (CompEstruBasica(NumLinea, Linea)) {
                    if (!Pila.PilaVacia()) {
                        if (Pila.BuscarPila().getSentencia().equals("INMERSION")) {
                            Pila.SacarPila();
                            if(!AuxVariables.isEmpty()) {
                                AuxVariables.remove(AuxVariables.size() - 1);
                            }
                            return true;
                        } else {
                            Tabla_Errores.AnadirError("ErrorSem28", NumLinea, Aux);
                            return false;
                        }
                    } else {
                        Tabla_Errores.AnadirError("ErrorSem11", NumLinea, Aux);
                        return false;
                    }
                }
                return false;
            case "SUMERGIR":
                if (CompEstruBasica(NumLinea, Linea)) {
                    Pila.MeterPila("SUMERGIR", NumLinea);
                    return true;
                }
                return false;
            case "FSUMERGIR":
                if (CompEstruBasica(NumLinea, Linea)) {
                    if (!Pila.PilaVacia()) {
                        if (Pila.BuscarPila().getSentencia().equals("SUMERGIR")) {
                            Pila.SacarPila();
                            return true;
                        } else {
                            Tabla_Errores.AnadirError("ErrorSem28", NumLinea, Aux);
                            return false;
                        }
                    } else {
                        Tabla_Errores.AnadirError("ErrorSem11", NumLinea, Aux);
                        return false;
                    }
                }
                return false;
            case "FINAL":
                if (CompEstruBasica(NumLinea, Linea)) {
                    if (PosiElementos[3] == 0) {
                        PosiElementos[3] = NumLinea;
                        if (!Pila.PilaVacia()) {
                            if (Pila.BuscarPila().getSentencia().equals("COMIENZO")) {
                                Pila.SacarPila();
                                return true;
                            } else {
                                Tabla_Errores.AnadirError("ErrorSem10", NumLinea, Pila.BuscarPila().getSentencia());
                                return false;
                            }
                        } else {
                            Tabla_Errores.AnadirError("ErrorSem11", NumLinea, Aux);
                            return false;
                        }
                    } else {
                        Tabla_Errores.AnadirError("ErrorSem31", NumLinea, Linea);
                        return false;
                    }
                }
                return false;

            default:
                if (Token.size() > 1 && Token.get(1).equals("=")) {
                    if (RP.ComproRex(RP.Arit, Linea)) {
                        if (CompInicio(NumLinea, Linea)) {
                            return true;
                        }
                    } else {
                        if (RP.ComproRex(RP.Asignar, Linea)) {
                            if (CompInicio(NumLinea, Linea)) {
                                return true;
                            }
                        } else {
                            return false;
                        }
                    }
                }
                return false;
        }
    }

    public boolean ValidarID(String Aux, ArrayList<String> PalaRes) {
        return (!PalaRes.contains(Aux)) && (RP.ComproRex(RP.ID, Aux));
    }

    public boolean ExisteID(String Aux) {
        int Cont = 0;
        boolean Existe = false;
        while ((Cont < TablaSim.size()) && (!Existe)) {
            TablaSimbolos AuxTabla = TablaSim.get(Cont);
            if (AuxTabla.getNombre().equals(Aux)) {
                Existe = true;
            }
            Cont++;
        }
        return Existe;
    }

    public boolean AnadirID(String Aux, String Tipo, int NumLinea) {
        if (!ExisteID(Aux)) {
            TablaSimbolos Nuevo = new TablaSimbolos(Aux, Tipo);
            TablaSim.add(Nuevo);
            return true;
        }
        Tabla_Errores.AnadirError("ErrorSem18", NumLinea, Aux);
        return false;
    }

    public String ObtenerTipo(String Aux) {
        if (RP.ComproRex(RP.Consta_Cad, Aux)) {
            return "Cad";
        } else {
            if (RP.ComproRex(RP.Consta_Ent, Aux)) {
                return "Ent";
            } else {
                if (RP.ComproRex(RP.Consta_Dec, Aux)) {
                    return "Dec";
                } else {
                    int Cont = 0;
                    while (Cont < TablaSim.size()) {
                        TablaSimbolos AuxTabla = TablaSim.get(Cont);
                        if (AuxTabla.getNombre().equals(Aux)) {
                            return AuxTabla.getTipo();
                        }
                        Cont++;
                    }
                }
            }
        }
        return "NoExiste";
    }

    public boolean CompConds(String Aux1, String Aux2, int NumLinea) {
        if ((ExisteID(Aux1)) || RP.ComproRex(RP.Consta, Aux1)) {
            if ((ExisteID(Aux2)) || RP.ComproRex(RP.Consta, Aux2)) {
                if (ObtenerTipo(Aux1).equals(ObtenerTipo(Aux2))) {
                    return true;
                } else {
                    Tabla_Errores.AnadirError("ErrorSem15", NumLinea, Aux1 + " y " + Aux2 + " son diferentes.");
                    return false;
                }
            } else {
                Tabla_Errores.AnadirError("ErrorSem6", NumLinea, Aux2);
                return false;
            }
        } else {
            Tabla_Errores.AnadirError("ErrorSem6", NumLinea, Aux1);
            if ((ExisteID(Aux2)) || RP.ComproRex(RP.Consta, Aux2)) {
            } else {
                Tabla_Errores.AnadirError("ErrorSem6", NumLinea, Aux2);
            }
            return false;
        }
    }

    public boolean CompCondicionales(ArrayList<String> Token, int NumLinea) {
        if (Token.get(2).equals("(")) {
            if (CompConds(Token.get(3), Token.get(5), NumLinea)) {
                if (CompConds(Token.get(9), Token.get(11), NumLinea)) {
                    return true;
                } else {
                    Tabla_Errores.AnadirError("ErrorSem16", NumLinea, Token.get(8) + " " + Token.get(9) + " "
                            + Token.get(10) + " " + Token.get(11) + " " + Token.get(12));
                    return false;
                }
            } else {
                Tabla_Errores.AnadirError("ErrorSem16", NumLinea, Token.get(2) + " " + Token.get(3) + " " + Token.get(4)
                        + " " + Token.get(5) + " " + Token.get(6));
                return false;
            }
        } else {
            if ((CompConds(Token.get(2), Token.get(4), NumLinea))) {
                return true;
            } else {
                Tabla_Errores.AnadirError("ErrorSem15", NumLinea, Token.get(1) + " " + Token.get(2) + " " + Token.get(3)
                        + " " + Token.get(4) + " " + Token.get(5));
                return false;
            }
        }
    }

    public boolean CompInmersion(ArrayList<String> Aux, int NumLinea) {
        if (ExisteID(Aux.get(2))) {
            if (ObtenerTipo(Aux.get(2)).equals("Ent")) {
                if ((AuxInmersion(Aux.get(4), NumLinea)) && (AuxInmersion(Aux.get(6), NumLinea))) {
                    if (!(Aux.get(2).equals(Aux.get(4))) && (!Aux.get(2).equals(Aux.get(6)))) {
                        return true;
                    } else {
                        Tabla_Errores.AnadirError("ErrorSem33", NumLinea, Aux.get(2));
                        return false;
                    }
                } else {
                    Tabla_Errores.AnadirError("ErrorSem21", NumLinea, Aux.get(1) + " " + Aux.get(2) + " " + Aux.get(3)
                            + " " + Aux.get(4) + " " + Aux.get(5) + " " + Aux.get(6) + " " + Aux.get(7));
                    return false;
                }
            } else {
                Tabla_Errores.AnadirError("ErrorSem17", NumLinea, Aux.get(2));
                AuxInmersion(Aux.get(4), NumLinea);
                AuxInmersion(Aux.get(6), NumLinea);
                Tabla_Errores.AnadirError("ErrorSem21", NumLinea, Aux.get(1) + " " + Aux.get(2) + " " + Aux.get(3) + " "
                        + Aux.get(4) + " " + Aux.get(5) + " " + Aux.get(6) + " " + Aux.get(7));
                return false;
            }
        } else {
            Tabla_Errores.AnadirError("ErrorSem6", NumLinea, Aux.get(2));
            AuxInmersion(Aux.get(4), NumLinea);
            AuxInmersion(Aux.get(6), NumLinea);
            Tabla_Errores.AnadirError("ErrorSem21", NumLinea, Aux.get(1) + " " + Aux.get(2) + " " + Aux.get(3) + " "
                    + Aux.get(4) + " " + Aux.get(5) + " " + Aux.get(6) + " " + Aux.get(7));
            return false;
        }
    }

    public boolean AuxInmersion(String Aux, int NumLinea) {
        if (ExisteID(Aux)) {
            if (ObtenerTipo(Aux).equals("Ent")) {
                return true;
            } else {
                Tabla_Errores.AnadirError("ErrorSem19", NumLinea, Aux);
                return false;
            }
        } else {
            if (RP.ComproRex(RP.Consta, Aux)) {
                if (RP.ComproRex(RP.Consta_Ent, Aux)) {
                    return true;
                } else {
                    Tabla_Errores.AnadirError("ErrorSem19", NumLinea, Aux);
                    return false;
                }
            } else {
                Tabla_Errores.AnadirError("ErrorSem6", NumLinea, Aux);
                return false;
            }
        }
    }

    public boolean CompArit(String Tipo, String Operador, int NumLinea) {
        switch (Tipo) {
            case "Cad":
                if (Operador.equals("+")) {
                    return true;
                } else {
                    Tabla_Errores.AnadirError("ErrorSem20", NumLinea, "");
                    return false;
                }
            default:
                return true;
        }
    }

    public boolean CompEstruBasica(int NumLinea, String Linea) {
        if (CompInicio(NumLinea, Linea)) {
            if (PosiElementos[1] != 0) {
                if ((PosiElementos[2] != 0) && (NumLinea > PosiElementos[2])) {
                    return true;
                } else {
                    if (PosiElementos[2] == 0) {
                        Tabla_Errores.AnadirError("ErrorSem25", NumLinea, Linea);
                        return false;
                    } else {
                        Tabla_Errores.AnadirError("ErrorSem25", NumLinea, Linea);
                        return false;
                    }
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean CompBloqueDeclaracion(int NumLinea, String Linea) {
        if ((PosiElementos[1] != 0) && (PosiElementos[1] < NumLinea)) {
            if ((PosiElementos[2] == 0) || (PosiElementos[2] > NumLinea)) {
                return true;
            } else {
                Tabla_Errores.AnadirError("ErrorSem9", NumLinea, Linea);
                return false;
            }
        } else {
            Tabla_Errores.AnadirError("ErrorSem24", NumLinea, Linea);
            return false;
        }
    }

    public boolean CompInicio(int NumLinea, String Linea) {
        if (PosiElementos[0] != 0) {
            if ((PosiElementos[3] != 0) && (NumLinea > PosiElementos[3])) {
                Tabla_Errores.AnadirError("ErrorSem32", NumLinea, Linea);
                return false;
            }
            return true;
        } else {
            Tabla_Errores.AnadirError("ErrorSem23", NumLinea, Linea);
            return false;
        }
    }
}
