import javax.swing.*;
import java.awt.*;

public class MarcoGatos extends JFrame {

    private GatosFav[] gatos;
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int heigth = Toolkit.getDefaultToolkit().getScreenSize().height;

    public MarcoGatos(GatosFav[] gatos){

        this.gatos = gatos;

        setBounds((int) width / 4,(int) heigth / 4, 1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new LaminaGatos(gatos));
        setVisible(true);

    }
}

