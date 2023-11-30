// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import com.sun.source.tree.NewArrayTree;

import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {
        Menu apk = new Menu();
        //Gra apk = new Gra();
        //test test = new test();
    }
}


class test extends JFrame{
    test(){

        //setLayout(new BorderLayout());

        JPanel on = new JPanel();
        on.setOpaque(false);
        JPanel tw = new JPanel();
        on.setLayout(new BorderLayout());
        tw.setLayout(null);
        add(tw);
        add(on);
        on.setOpaque(false);
        tw.setOpaque(true);
        setSize(new Dimension(400,400));
        JPanel a = new JPanel();
        a.setBackground(Color.BLACK);
        a.setPreferredSize(new Dimension(400,100));
        JPanel b = new JPanel();
        b.setBackground(Color.BLUE);
        b.setPreferredSize(new Dimension(400,100));
        JPanel c = new JPanel();
        c.setBackground(Color.RED);
        c.setBounds(0,0,getWidth(),getHeight());
        //c.setPreferredSize(new Dimension(400,400));

        //on.add(a,BorderLayout.NORTH);
       // on.add(b,BorderLayout.SOUTH);
        tw.add(c);


        setVisible(true);

    }

}