import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.*;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class Map extends JFrame {
    JFrame pulpit = new JFrame();
    int x1,y1;
    int x2,y2;
    JXMapViewer mapa;
    public Map(int szer,int wys) {


        pulpit.setBounds(szer-500,wys-460,500,400);

        mapa();
        x1=0;y1=0;x2=0;y2=0;
        pulpit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){

                //y1 = e.getY();x1 = e.getX();
                //y2 = e.getY()+100;x2 = e.getX();

                if(e.getButton()==1){
                    y1 = e.getY();x1 = e.getX();
                }
                if(e.getButton()==3){
                    y2 = e.getY();x2 = e.getX();
                }

                Waypoint waypoint1 = new DefaultWaypoint(mapa.convertPointToGeoPosition(new Point(x1,y1)));
                Waypoint waypoint2 = new DefaultWaypoint(mapa.convertPointToGeoPosition(new Point(x2,y2)));

                System.out.println(Math.abs(waypoint2.getPosition().getLatitude()-waypoint1.getPosition().getLatitude())*111);

                Set<Waypoint> waypointSet = new HashSet<>();
                waypointSet.add(waypoint1);
                waypointSet.add(waypoint2);

                WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
                waypointPainter.setWaypoints(waypointSet);
                mapa.setOverlayPainter(waypointPainter);
                x1= e.getX();y1=e.getY();


            }

        });



    }
    void widoczny(boolean bool){
        pulpit.setVisible(bool);
    }
    void mapa(){

        pulpit.setAlwaysOnTop(true);
        mapa = new JXMapViewer();
        mapa.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo("","https://tile.openstreetmap.org"))); // Parametry kafelka

        mapa.setAddressLocation(new GeoPosition(54.434195, 18.570538));
        mapa.setZoom(8);
        MouseInputListener a = new PanMouseInputListener(mapa);
        pulpit.addMouseListener(a);
        pulpit.addMouseMotionListener(a);
        pulpit.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapa));
        pulpit.setUndecorated(true);
        pulpit.getContentPane().add(mapa);



    }
    void policz(){


    }

}
