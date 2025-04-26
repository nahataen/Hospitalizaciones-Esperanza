package hosp;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MenuMain {
    public static void main(String[] args) {
  almacena();
    }

    public static void almacena(){
              Scanner scan = new Scanner(System.in);
        int n=0;
       
        System.out.println("\u001B[34m.%%.......%%%%...........%%%%%%...%%%%...%%%%%...%%%%%%..%%%%%....%%%%...%%..%%..%%%%%%...%%%%..\r\n" +
                ".%%......%%..%%..........%%......%%......%%..%%..%%......%%..%%..%%..%%..%%%.%%.....%%...%%..%%.\r\n" +
                ".%%......%%%%%%..........%%%%.....%%%%...%%%%%...%%%%....%%%%%...%%%%%%..%%.%%%....%%....%%%%%%.\r\n" +
                ".%%......%%..%%..........%%..........%%..%%......%%......%%..%%..%%..%%..%%..%%...%%.....%%..%%.\r\n" +
                ".%%%%%%..%%..%%..........%%%%%%...%%%%...%%......%%%%%%..%%..%%..%%..%%..%%..%%..%%%%%%..%%..%%.\u001B[0m");
        System.out.println(" ");
System.out.println("presiona enter");
scan.nextLine(); 
n = 1;

if(n==1){
    System.out.print("\033[H\033[2J");
    System.out.flush();
                           Menu mp = new Menu();
        mp.Mostrar();
        
}
        
       
    }


    public static void animateText() throws InterruptedException {
        String[] lines = {
            ".%%.......%%%%...........%%%%%%...%%%%...%%%%%...%%%%%%..%%%%%....%%%%...%%..%%..%%%%%%...%%%%..",
            ".%%......%%..%%..........%%......%%......%%..%%..%%......%%..%%..%%..%%..%%%.%%.....%%...%%..%%.",
            ".%%......%%%%%%..........%%%%.....%%%%...%%%%%...%%%%....%%%%%...%%%%%%..%%.%%%....%%....%%%%%%.",
            ".%%......%%..%%..........%%..........%%..%%......%%......%%..%%..%%..%%..%%..%%...%%.....%%..%%.",
            ".%%%%%%..%%..%%..........%%%%%%...%%%%...%%......%%%%%%..%%..%%..%%..%%..%%..%%..%%%%%%..%%..%%.",
            "..............................................................................................."
        };

        String heart = "  \u001B[31m♥\u001B[0m  ";

        for (String line : lines) {
            StringBuilder animatedLine = new StringBuilder();
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                animatedLine.append("\u001B[34m").append(c).append("\u001B[0m");
            }
            animatedLine.append(heart);
            System.out.println(animatedLine);
            TimeUnit.MILLISECONDS.sleep(100); // Adjust the delay as needed
        }
    }
    
}
