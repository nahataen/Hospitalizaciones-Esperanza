package hosp.Menuconsultas;

import java.util.Scanner;

public class MenuBuscarPrincipal {
    public static void MenuCP(){

//Objetos--------------------------------------------------------
        Scanner scan = new Scanner(System.in);
        MenuBuscaPaciente MBPaciente = new MenuBuscaPaciente();
        MenuBuscaMedico MBMedico = new MenuBuscaMedico();

//---------------------------------------------------------------        

        String selecion;
        boolean cicloCerrar = true;
        
        do{
            System.out.println("|-------------------------------------------|");
            System.out.println("|                   Buscar                  |");
            System.out.println("|-------------------------------------------|");
            System.out.println("|1| Paciente                                |");
            System.out.println("|2| Medico                                  |");
            System.out.println("|3| Volver                                  |");
            System.out.println("|-------------------------------------------|");
            System.out.print("| Seleccione un numero: ");
            selecion = scan.nextLine();
            switch(selecion){
                case "1":{
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    MBPaciente.MenuCpacien();
                }
                break;
                case "2":{
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    MBMedico.MenuCmedi();
                }
                break;
                case "3":{
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