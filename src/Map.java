
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

import java.util.HashSet;
import java.util.Set;


public class Map extends JFrame {
    JFrame pulpit = new JFrame();
    int x1,y1;
    double latp=-1,latc=-1,longp=-1,longc=-1;
    JXMapViewer mapa;

    public Map(int szer, int wys) {
        pulpit.setBounds(szer - 500, wys - 460, 500, 400);

        mapa();

        pulpit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                 y1 = e.getY();
                 x1 = e.getX();

                Waypoint waypoint1 = new DefaultWaypoint(mapa.convertPointToGeoPosition(new Point(x1, y1)));
                longp = waypoint1.getPosition().getLongitude();
                latp = waypoint1.getPosition().getLatitude();
                //policz(waypoint1.getPosition().getLatitude(),waypoint2.getPosition().getLatitude(),waypoint1.getPosition().getLongitude(),waypoint2.getPosition().getLongitude());

                Set<Waypoint> waypointSet = new HashSet<>();
                waypointSet.add(waypoint1);


                WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
                waypointPainter.setWaypoints(waypointSet);
                mapa.setOverlayPainter(waypointPainter);
            }
        });
    }

    public Map(double X1, double X2,double Y1,double Y2, JPanel panel) {
        longp=X1;longc=X2;latp=Y1;latc=Y2;
        double srednialong=(longp+longc)/2;
        double srednialat=(latp+latc)/2;
        //mapa();
        panel.setLayout(new BorderLayout());

        mapa = new JXMapViewer();
        mapa.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo("", "https://tile.openstreetmap.org"))); // Parametry kafelka

        mapa.setAddressLocation(new GeoPosition(srednialat, srednialong));
        mapa.setZoom(8);
        MouseInputListener a = new PanMouseInputListener(mapa);
        panel.addMouseListener(a);
        panel.addMouseMotionListener(a);
        panel.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapa));


        Waypoint waypoint1 = new DefaultWaypoint(new GeoPosition(latp,longp));
        Waypoint waypoint2 = new DefaultWaypoint(new GeoPosition(latc,longc));


        policz();

        Set<Waypoint> waypointSet = new HashSet<>();
        waypointSet.add(waypoint1);
        waypointSet.add(waypoint2);


        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(waypointSet);
        mapa.setOverlayPainter(waypointPainter);

        panel.add(mapa,BorderLayout.CENTER);

        //pulpit.setVisible(true);
    }

    void widoczny(boolean bool) {
        pulpit.setVisible(bool);
    }

    void mapa() {

        pulpit.setAlwaysOnTop(true);

        mapa = new JXMapViewer();
        mapa.setTileFactory(new DefaultTileFactory(new OSMTileFactoryInfo("", "https://tile.openstreetmap.org"))); // Parametry kafelka

        mapa.setAddressLocation(new GeoPosition(54.434195, 18.570538));
        mapa.setZoom(8);
        MouseInputListener a = new PanMouseInputListener(mapa);
        pulpit.addMouseListener(a);
        pulpit.addMouseMotionListener(a);
        pulpit.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapa));
        pulpit.setUndecorated(true);
        pulpit.getContentPane().add(mapa);


    }

    void policz() {
        int req = 6378137;
        double f = 1 / 298.257223563;
        double rpol = 6356752.314245;
        latp = Math.PI * latp / 180;
        latc = Math.PI * latc / 180;
        longp = Math.PI * longp / 180;
        longc = Math.PI * longc / 180;

        double sin_sigma = 0, cos_sigma = 0, sigma = 0, sin_alpha, cos_sq_alpha = 0, cos2sigma = 0;
        double C, lam_pre;

        double U1 = Math.atan((1 - f) * Math.tan(latc));
        double U2 = Math.atan((1 - f) * Math.tan(latp));

        double lon = longp - longc;
        double lam = lon;
        double tol = Math.pow(10., -12);
        double diff = 1.;

        double cosU1 = Math.cos(U1);
        double cosU2 = Math.cos(U2);
        double sinU1 = Math.sin(U1);
        double sinU2 = Math.sin(U2);
        double coslam = Math.cos(lam), sinlam = Math.sin(lam);

        while (Math.abs(diff) > tol) {

            sin_sigma = Math.sqrt(Math.pow((cosU2 * sinlam), 2.) + Math.pow(cosU1 * sinU2 - sinU1 * cosU2 * coslam, 2.));
            cos_sigma = sinU1 * sinU2 + cosU1 * cosU2 * coslam;
            sigma = Math.atan(sin_sigma / cos_sigma);
            sin_alpha = (cosU1 * cosU2 * sinlam) / sin_sigma;
            cos_sq_alpha = 1 - Math.pow(sin_alpha, 2.);
            cos2sigma = cos_sigma - ((2 * sinU1 * sinU2) / cos_sq_alpha);
            C = (f / 16) * cos_sq_alpha * (4 + f * (4 - 3 * cos_sq_alpha));
            lam_pre = lam;
            lam = lon + (1 - C) * f * sin_alpha * (sigma + C * sin_sigma * (cos2sigma + C * cos_sigma * (2 * Math.pow(cos2sigma, 2.) - 1)));
            diff = Math.abs(lam_pre - lam);
            coslam = Math.cos(lam);
            sinlam = Math.sin(lam);

        }

        double usq = cos_sq_alpha * ((Math.pow(req, 2.) - Math.pow(rpol, 2.)) / Math.pow(rpol, 2.));
        double A = 1 + (usq / 16384) * (4096 + usq * (-768 + usq * (320 - 175 * usq)));
        double B = (usq / 1024) * (256 + usq * (-128 + usq * (74 - 47 * usq)));
        double delta_sig = B * sin_sigma * (cos2sigma + 0.25 * B * (cos_sigma * (-1 + 2 * Math.pow(cos2sigma, 2.)) -
                ((double) 1 / 6) * B * cos2sigma * (-3 + 4 * Math.pow(sin_sigma, 2.)) *
                        (-3 + 4 * Math.pow(cos2sigma, 2.))));
        double dis = rpol * A * (sigma - delta_sig);
        System.out.println(dis);

    }
}