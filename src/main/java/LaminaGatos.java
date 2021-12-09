import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class LaminaGatos extends JPanel {

    private GatosFav[] gatos;
    private JComboBox combo;
    private ImageX imagenx;
    private ImageIcon imageIcon;
    private JLabel label;

    public LaminaGatos(GatosFav[] gatos){

        setLayout(new BorderLayout());
        this.gatos = gatos;

        combo = new JComboBox();
        addGatos();
        add(combo, BorderLayout.NORTH);

        combo.setSelectedIndex(0);

        combo.addActionListener(new Evento());



    }
    public void addGatos(){

        for (int i = 0; i < gatos.length; i++) {

            combo.addItem(gatos[i].getId());
        }
    }
    private class  Evento implements ActionListener{

        public void actionPerformed(ActionEvent actionEvent) {

            if(label!=null){

                remove(label);
            }

            for(int i=0; i < gatos.length; i++){

                if(combo.getSelectedItem().equals(gatos[i].getId())){

                    imagenx = gatos[i].getImage();

                    Image image = null;

                    try{

                        URL url = new URL(imagenx.getUrl());
                        image = ImageIO.read(url);
                        image = image.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                        imageIcon = new ImageIcon(image);

                        label = new JLabel(imageIcon);
                        add(label, BorderLayout.CENTER);


                    }catch(Exception e){

                        e.printStackTrace();
                    }


                }
            }

        }
    }
}
