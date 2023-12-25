import javax.swing.*;
import java.awt.*;


public class Mapka extends Okno{

    Mapka(){

        pulpit.setSize(100,100);
        pulpit.setBounds(szerokoscokna-400,wysokoscokna-460,400,400);
        pulpit.setAlwaysOnTop(true);
        pulpit.setLayout(new BorderLayout());
        //pulpit.setBackground(new Color(0,0,0,0));

        JPanel dol = new JPanel();
        dol.setLayout(new BorderLayout());
        dol.setOpaque(false);

        JButton zatwierdz = new JButton("zatwierdz");

        pulpit.add(dol,BorderLayout.SOUTH);
        dol.add(zatwierdz,BorderLayout.EAST);


        pulpit.setVisible(false);
    }

    void chowanie(Boolean bool){
        pulpit.setVisible(bool);

    }

}
