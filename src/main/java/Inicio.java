import javax.swing.*;
import java.io.IOException;

public class Inicio {

    public static void main(String[] args) throws IOException {

        int opcion_menu = -1;
        String[] botones = {"1. Ver gatos", "2. Salir"};

        do {

            String opcion = (String) JOptionPane.showInputDialog(null, "Gatos Java", "Menu", JOptionPane.INFORMATION_MESSAGE,
                    null, botones, botones[0]);

            for (int i = 0; i < botones.length; i++) {

                if (opcion.equals(botones[i])){
                    opcion_menu = i;
                }
            }

            switch (opcion_menu){
                case 0:
                    GatoService.verGatos();
                    break;
                default:
                    break;
            }
        }while (opcion_menu != 1);

        System.out.println("Bye. ");

    }
}
