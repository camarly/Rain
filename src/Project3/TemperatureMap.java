package Project3;

import javax.swing.*;
import java.awt.*;

public class TemperatureMap extends JFrame {


    private String layer = null;
    //PAR0 - rain
    //TA2 = air temp
    private String zoom = null;

    private String xcoord = null;

    private String ycoord = null;

    private String datetime = null;

    //private <T> T tempMap = null;
    //private <T> T rainMap = null;


    public TemperatureMap(String layer, String zoom, String xcoord, String ycoord, String datetime) throws HeadlessException {
        this.layer = layer;
        this.zoom = zoom;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.datetime = datetime;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getZoom() {
        return zoom;
    }

    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

    public String getXcoord() {
        return xcoord;
    }

    public void setXcoord(String xcoord) {
        this.xcoord = xcoord;
    }

    public String getYcoord() {
        return ycoord;
    }

    public void setYcoord(String ycoord) {
        this.ycoord = ycoord;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }


}
