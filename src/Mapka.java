import javax.swing.*;


public class Mapka extends Okno{


    Mapka(){

        pulpit.setSize(100,100);

        pulpit.setBounds(szerokoscokna-400,wysokoscokna-460,400,400);
        pulpit.setAlwaysOnTop(true);
        
        JButton zatwierdz = new JButton("zatwierdz");
        JPanel dol = new JPanel();


        pulpit.setVisible(false);
    }

    void chowanie(Boolean bool){
        pulpit.setVisible(bool);


    }

}
