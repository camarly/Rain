package Project3;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MapDisplay extends JFrame {

    private Image mapImage;

    public MapDisplay() throws Exception {
        setTitle("Map Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // fetch map image from API
        String apiURL = "http://maps.openweathermap.org/maps/2.0/weather/TA2/2/1/1?appid=dbd8574d10549e41443636960496338d&fill_bound=true&opacity=0.6&palette=-65:821692;-55:821692;-45:821692;-40:821692;-30:8257db;-20:208cec;-10:20c4e8;0:23dddd;10:c2ff28;20:fff028;25:ffc228;30:fc8014";
        URL url = new URL(apiURL);
        InputStream in = url.openStream();
        byte[] bytes = in.readAllBytes();
        in.close();

        // convert bytes to image
        InputStream is = new ByteArrayInputStream(bytes);
        mapImage = ImageIO.read(is);

        // add map panel to frame
        add(new MapPanel());
        setVisible(true);
    }

    private class MapPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // draw map image
            if (mapImage != null) {
                Dimension size = getSize();
                g.drawImage(mapImage, 0, 0, size.width, size.height, this);
            }
        }
    }

}
