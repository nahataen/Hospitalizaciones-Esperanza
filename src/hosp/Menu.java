package hosp;
import hosp.colores.colores;
import hosp.Escribir.MenuEscribir;
import hosp.Escribir.MetodoGenerarExpediente;
import hosp.Menuconsultas.MenuBuscarPrincipal;
import hosp.Actualizar.update;
import hosp.Escribir2.Tratamiento;
import java.sql.*;
import java.util.Scanner;

public class Menu {
    
    public static void Mostrar(){ 




 

    //Objetos--------------------------------------------------------
        Scanner scan = new Scanner(System.in);
        MenuEscribir CrearPaciente = new MenuEscribir();
        MenuBuscarPrincipal MenuconsP = new MenuBuscarPrincipal();
        update altaUp = new update();
        Tratamiento inst = new Tratamiento();
//---------------------------------------------------------------        

        String selecion;
        boolean cicloCerrar = true;

        do{

            System.out.println("|-------------------------------------------|");
            System.out.println("|                   Inicio                  |");
            System.out.println("|-------------------------------------------|");
            System.out.println("|1| Ingresar un nuevo paciente              |");
            System.out.println("|2| Crear un expediente                     |");
            System.out.println("|3| Ingresar un prescripción                |");
            System.out.println("|4| Dar de alta a un paciente               |");
            System.out.println("|5| Buscar informacion                      |");
            System.out.println("|6| Cerrar                                  |");
            System.out.println("|-------------------------------------------|");
            System.out.print("| Seleccione un numero: ");
        
            selecion = scan.nextLine();
        
            switch(selecion){
                case "1" :{
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    CrearPaciente.insersion();
                } break;
                case "2" :{
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    MetodoGenerarExpediente genexpe=new MetodoGenerarExpediente();
                    genexpe.CreacionDeExpedientePArticular();
                } break;
                case "3" :{
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    inst.MetodoGenTratamiento();
                } break;
                case "4" :{
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    altaUp.alta();
                    System.out.println("--Preciona enter para continuar y volver al inicio--");
                    String continuar = scan.nextLine();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                } break;

                case "5" :{
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    MenuconsP.MenuCP();
                   
                } break;
                case "6" :{
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    cicloCerrar=false;
                } break;
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

