package hosp.Menuconsultas;

import hosp.Leer.Cons1;
import hosp.Leer.Cons2;
import hosp.Leer.Cons3;
import hosp.Leer.Cons6;
import hosp.Leer.Cons7;
import hosp.Leer.Cons8;
import hosp.Leer.Cons10;
import java.util.Scanner;

public class MenuBuscaPaciente {
    public static void MenuCpacien(){

//Objetos--------------------------------------------------------
        Scanner scan = new Scanner(System.in);
        Cons1 c1 = new Cons1();
        Cons2 c2 = new Cons2();
        Cons3 c3 = new Cons3();
        Cons6 c6 = new Cons6();
        Cons7 c7 = new Cons7();
        Cons8 c8 = new Cons8();
        Cons10 c10 = new Cons10();
//---------------------------------------------------------------        

        String selecion;
        boolean cicloCerrar = true;
        
        do{

            System.out.println("|-------------------------------------------|");
            System.out.println("|              Buscar paciente              |");
            System.out.println("|-------------------------------------------|");
            System.out.println("|1| Ingrsos                                 |");
            System.out.println("|2| Un ingreso en especifico                |");  
            System.out.println("|3| En la misma habitacion                  |");
            System.out.println("|4| Medicos que atendieron a un paciente    |");
            System.out.println("|5| Buscar por dia                          |");
            System.out.println("|6| Menores de edad (-13)                   |");
            System.out.println("|7| Ingrseos por especialidad               |");
            System.out.println("|8| Volver                                  |");
            System.out.println("|-------------------------------------------|");
            System.out.print("| Seleccione un numero: ");
            selecion = scan.nextLine();
            switch(selecion){
                case "1":{
                    boolean subcontrolador = true;
                    System.out.print("| Ingrese el numero del paciente: ");
                    String c11 =scan.nextLine();
                    c2.info(c11);
                    System.out.println("--Preciona enter para continuar--");
                    String continuar = scan.nextLine();
                    do{
                        System.out.println("    |-------------------------------|");
                        System.out.println("    |   ¿A donde quieres volover?   |");
                        System.out.println("    |   1.Menu buscar paciente      |");
                        System.out.println("    |   2.Menu buscar               |");
                        System.out.println("    |-------------------------------|");
                        System.out.print("    | ");
                        String ssub = scan.nextLine();
                        switch(ssub){
                            case "1":{
                                subcontrolador = false;
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                            }
                            break;
                            case "2":{
                                cicloCerrar = false;
                                subcontrolador = false;
                                System.out.print("\033[H\033[2J");
                                System.out.flush();}
                            break;
                            default:{
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println("seleccion incorecta.");}
                        }
                    }while(subcontrolador);        
                }
                break;
                case "2":{
                        boolean subcontrolador = true;
                        System.out.print("| Ingrese el numero de ingreso: ");
                        String c11 =scan.nextLine();
                        c1.info(c11);
                        System.out.println("--Preciona enter para continuar--");
                        String continuar = scan.nextLine();
                        do{                    
                            System.out.println("    |-------------------------------|");
                            System.out.println("    |   ¿A donde quieres volover?   |");
                            System.out.println("    |   1.Menu buscar paciente      |");
                            System.out.println("    |   2.Menu buscar               |");
                            System.out.println("    |-------------------------------|");
                            System.out.print("    | ");
                            String ssub = scan.nextLine();
                            switch(ssub){
                                case "1":{
                                    subcontrolador = false;
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                }
                                break;
                                case "2":{
                                    cicloCerrar = false;
                                    subcontrolador = false;
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();}
                                break;
                                default:{
                                    System.out.print("\033[H\033[2J");
                                    System.out.flush();
                                    System.out.println("seleccion incorecta.");}
                            }
                        }while(subcontrolador);
                }
                break;
                case "3":{
                    boolean subcontrolador = true;
                    System.out.print("| Ingrese el numero del paciente: ");
                    String c11 =scan.nextLine();
                    c3.info(c11);
                    System.out.println("--Preciona enter para continuar--");
                    String continuar = scan.nextLine();
                    do{
                        System.out.println("    |-------------------------------|");
                        System.out.println("    |   ¿A donde quieres volover?   |");
                        System.out.println("    |   1.Menu buscar paciente      |");
                        System.out.println("    |   2.Menu buscar               |");
                        System.out.println("    |-------------------------------|");
                        System.out.print("    | ");
                        String ssub = scan.nextLine();
                        switch(ssub){
                            case "1":{
                                subcontrolador = false;
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                            }
                            break;
                            case "2":{
                                cicloCerrar = false;
                                subcontrolador = false;
                                System.out.print("\033[H\033[2J");
                                System.out.flush();}
                            break;
                            default:{
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println("seleccion incorecta.");}
                        }
                    }while(subcontrolador);    
                }
                break;
                case "4":{
                    boolean subcontrolador = true;
                    System.out.print("| Ingrese el numero del paciente: ");
                    String c11 =scan.nextLine();
                    c6.info(c11);
                    System.out.println("--Preciona enter para continuar--");
                    String continuar = scan.nextLine();
                    do{
                        System.out.println("    |-------------------------------|");
                        System.out.println("    |   ¿A donde quieres volover?   |");
                        System.out.println("    |   1.Menu buscar paciente      |");
                        System.out.println("    |   2.Menu buscar               |");
                        System.out.println("    |-------------------------------|");
                        System.out.print("    | ");
                        String ssub = scan.nextLine();
                        switch(ssub){
                            case "1":{
                                subcontrolador = false;
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                            }
                            break;
                            case "2":{
                                cicloCerrar = false;
                                subcontrolador = false;
                                System.out.print("\033[H\033[2J");
                                System.out.flush();}
                            break;
                            default:{
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println("seleccion incorecta.");}
                        }
                    }while(subcontrolador);   
                    
                }
                break;
                case "5":{
                    boolean subcontrolador = true;
                    System.out.print("| Ingrese el numero del dia: ");
                    String d =scan.nextLine();
                    System.out.print("| Ingrese el numero del mes: ");
                    String m =scan.nextLine();
                    System.out.print("| Ingrese el año (numero):   ");
                    String a =scan.nextLine();

                    String c11 = a+"-"+m+"-"+d;
                    
                    c7.info(c11);
                    System.out.println("--Preciona enter para continuar--");
                    String continuar = scan.nextLine();
                    do{                    
                        System.out.println("    |-------------------------------|");
                        System.out.println("    |   ¿A donde quieres volover?   |");
                        System.out.println("    |   1.Menu buscar paciente      |");
                        System.out.println("    |   2.Menu buscar               |");
                        System.out.println("    |-------------------------------|");
                        System.out.print("    | ");
                        String ssub = scan.nextLine();
                        switch(ssub){
                            case "1":{
                                subcontrolador = false;
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                            }
                            break;
                            case "2":{
                                cicloCerrar = false;
                                subcontrolador = false;
                                System.out.print("\033[H\033[2J");
                                System.out.flush();}
                            break;
                            default:{
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println("seleccion incorecta.");}
                            }
                        }while(subcontrolador);  
                }
                break;

                case "6":{                      
                    boolean subcontrolador = true;
                    
                    c8.info();
                    System.out.println("--Preciona enter para continuar--");
                    String continuar = scan.nextLine();
                    do{                    
                        System.out.println("    |-------------------------------|");
                        System.out.println("    |   ¿A donde quieres volover?   |");
                        System.out.println("    |   1.Menu buscar paciente      |");
                        System.out.println("    |   2.Menu buscar               |");
                        System.out.println("    |-------------------------------|");
                        System.out.print("    | ");
                        String ssub = scan.nextLine();
                        switch(ssub){
                            case "1":{
                                subcontrolador = false;
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                            }
                            break;
                            case "2":{
                                cicloCerrar = false;
                                subcontrolador = false;
                                System.out.print("\033[H\033[2J");
                                System.out.flush();}
                            break;
                            default:{
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println("seleccion incorecta.");}
                        }
                    }while(subcontrolador);
                }   
                break;
                case "7":{                      
                    boolean subcontrolador = true;
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    c10.info();
                    System.out.println("--Preciona enter para continuar--");
                    String continuar = scan.nextLine();
                    do{                    
                        System.out.println("    |-------------------------------|");
                        System.out.println("    |   ¿A donde quieres volover?   |");
                        System.out.println("    |   1.Menu buscar paciente      |");
                        System.out.println("    |   2.Menu buscar               |");
                        System.out.println("    |-------------------------------|");
                        System.out.print("    | ");
                        String ssub = scan.nextLine();
                        switch(ssub){
                            case "1":{
                                subcontrolador = false;
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                            }
                            break;
                            case "2":{
                                cicloCerrar = false;
                                subcontrolador = false;
                                System.out.print("\033[H\033[2J");
                                System.out.flush();}
                            break;
                            default:{
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println("seleccion incorecta.");}
                        }
                    }while(subcontrolador);
                }
                break;
                case "8":{
                    cicloCerrar=false;
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    
                }
                break;
                default:{
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("|-------------------------------------------|");
                    System.out.println("|-----------seleccion incorrecta------------|");
                }

            }
        }while(cicloCerrar);
        
    }
}