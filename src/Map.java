import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.*;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;


public class Map extends JFrame {
    JFrame pulpit = new JFrame();
    int x1,y1;
    int x2,y2;
    JXMapViewer mapa;
    public Map(int szer,int wys) {


        pulpit.setBounds(szer-500,wys-460,500,400);

        mapa();

        pulpit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){

                Waypoint waypoint = new DefaultWaypoint(mapa.convertPointToGeoPosition(e.getPoint()));
                WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
                waypointPainter.setWaypoints(Collections.singleton(waypoint));
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
}
