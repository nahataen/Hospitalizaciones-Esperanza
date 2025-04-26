package hosp.Menuconsultas;
import hosp.Leer.Cons4;
import hosp.Leer.Cons5;
import hosp.Leer.Cons9;
import java.util.Scanner;

public class MenuBuscaMedico {
    public static void MenuCmedi(){

//Objetos--------------------------------------------------------
        Scanner scan = new Scanner(System.in);
        Cons4 c4 = new Cons4();
        Cons5 c5 = new Cons5();
        Cons9 c9 = new Cons9();
//---------------------------------------------------------------        

        String selecion;
        boolean cicloCerrar = true;
        
        do{
 

            System.out.println("|-------------------------------------------|");
            System.out.println("|               Buscar Medico               |");
            System.out.println("|-------------------------------------------|");
            System.out.println("|1| Buscar medicos especialistas            |");
            System.out.println("|2| La especialidad de un medico            |");
            System.out.println("|3| Cantidad de pacientes atendidos         |");
            System.out.println("|4| Volver                                  |");
            System.out.println("|-------------------------------------------|");
            System.out.print("| Seleccione un numero: ");
            
            selecion = scan.nextLine();
            switch(selecion){
                case "1":{
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    boolean subcontrolador = true;
                    c4.info();
                    System.out.println("--Preciona enter para continuar--");
                    String continuar = scan.nextLine();
                    do{                    
                        System.out.println("    |-------------------------------|");
                        System.out.println("    |   ¿A donde quieres volover?   |");
                        System.out.println("    |   1.Menu buscar medicos       |");
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
                    System.out.print("| Ingrese el numero del medico: ");
                    String c11 =scan.nextLine();
                    c5.info(c11);
                    System.out.println("--Preciona enter para continuar--");
                    String continuar = scan.nextLine();
                    do{                    
                        System.out.println("    |-------------------------------|");
                        System.out.println("    |   ¿A donde quieres volover?   |");
                        System.out.println("    |   1.Menu buscar medicos       |");
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
                    System.out.print("| Ingrese el numero del medico: ");
                    String c11 =scan.nextLine();
                    c9.info();
                    System.out.println("--Preciona enter para continuar--");
                    String continuar = scan.nextLine();
                    do{                    
                        System.out.println("    |-------------------------------|");
                        System.out.println("    |   ¿A donde quieres volover?   |");
                        System.out.println("    |   1.Menu buscar medicos       |");
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