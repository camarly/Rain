////package Project3;
////
////import org.json.JSONArray;
////import org.json.JSONException;
////import org.json.JSONObject;
////
////import javax.swing.*;
////import java.awt.*;
////import java.net.URI;
////import java.net.http.HttpClient;
////import java.net.http.HttpRequest;
////import java.net.http.HttpResponse;
////import java.util.HashMap;
////import java.util.Map;
////
////public class WeatherDisplay extends JFrame {
////
////    private Map<String, Double> temperatureData;
////
////    public WeatherDisplay(Map<String, Double> temperatureData) throws Exception {
////        this.temperatureData = temperatureData;
////
////        setTitle("Weather Map");
////        setSize(800, 600);
////        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////
////        add(new WeatherPanel());
////
////        setVisible(true);
////        displayWeatherData();
////    }
////
////    private class WeatherPanel extends JPanel {
////
////        @Override
////        protected void paintComponent(Graphics g) {
////            super.paintComponent(g);
////
////            for (Map.Entry<String, Double> entry : temperatureData.entrySet()) {
////                String[] parts = entry.getKey().split(",");
////                int lat = Integer.parseInt(parts[0]);
////                int lon = Integer.parseInt(parts[1]);
////                int temperature = entry.getValue().intValue();
////
////                // draw temperature data at (lat, lon) with color corresponding to temperature value
////                // you can customize this to your liking
////                Color color = getColorForTemperature(temperature);
////                g.setColor(color);
////                g.fillRect(lat, lon, 1, 1);
////            }
////        }
////
////        private Color getColorForTemperature(int temperature) {
////            // return a color based on temperature value
////            // you can customize this to your liking
////            if (temperature < 0) {
////                return Color.BLUE;
////            } else if (temperature < 10) {
////                return Color.CYAN;
////            } else if (temperature < 20) {
////                return Color.GREEN;
////            } else if (temperature < 30) {
////                return Color.YELLOW;
////            } else {
////                return Color.RED;
////            }
////        }
////
////    }
////
////
////    /**
////     * fetches JSON map data from API url
////     *
////     * @return
////     * @throws Exception
////     */
////    public static String getTempMapData() throws Exception {
////        String apiURL = "http://maps.openweathermap.org/maps/2.0/weather/TA2/2/1/1?appid=dbd8574d10549e41443636960496338d&fill_bound=true&opacity=0.6&palette=-65:821692;-55:821692;-45:821692;-40:821692;-30:8257db;-20:208cec;-10:20c4e8;0:23dddd;10:c2ff28;20:fff028;25:ffc228;30:fc8014";
////
////
////        HttpClient client = HttpClient.newHttpClient();
////        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURL)).build();
////
////        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
////                .thenApply(HttpResponse::body)
////                .thenApply(WeatherDisplay::parseTempMapData)
////                .join();
////        return null;
////    }
////
////
////    /**
////     * method to parse temperature map data from JSON object
////     * @param responseBody
////     * @return
////     * @throws JSONException
////     */
////    public static Map<String, Double> parseTempMapData(String responseBody) throws JSONException {
////
////        Map<String, Double> temperatureData = new HashMap<>();
////
////        JSONObject weatherData = new JSONObject(responseBody);
////
////        JSONArray temperatureLayers = weatherData.getJSONArray("layers");
////
////        for (int i = 0; i < temperatureLayers.length(); i++) {
////
////            JSONObject temperatureLayer = temperatureLayers.getJSONObject(i);
////
////            String layerName = temperatureLayer.getString("name");
////
////            if (layerName.equals("temp")) {
////                JSONArray layerData = temperatureLayer.getJSONArray("data");
////
////                for (int j = 0; j < layerData.length(); j++) {
////                    JSONArray rowData = layerData.getJSONArray(j);
////
////                    for (int k = 0; k < rowData.length(); k++) {
////
////                        JSONArray gridPointData = rowData.getJSONArray(k);
////
////                        String lat = gridPointData.getString(0);
////                        String lon = gridPointData.getString(1);
////                        Double temperature = gridPointData.getDouble(2);
////
////                        String key = lat + "," + lon;
////                        temperatureData.put(key, temperature);
////                    }
////                }
////            }
////        }
////
////        return temperatureData;
////    }
////
////    public static void displayWeatherData() throws Exception {
////        String apiURL = "http://maps.openweathermap.org/maps/2.0/weather/TA2/2/1/1?appid=dbd8574d10549e41443636960496338d&fill_bound=true&opacity=0.6&palette=-65:821692;-55:821692;-45:821692;-40:821692;-30:8257db;-20:208cec;-10:20c4e8;0:23dddd;10:c2ff28;20:fff028;25:ffc228;30:fc8014";
////        Map<String, Double> temperatureData = parseTempMapData(getTempMapData());
////        new WeatherDisplay(temperatureData);
////    }
////
////}
//
//package Project3;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import javax.swing.*;
//import java.awt.*;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.util.HashMap;
//import java.util.Map;
//
//public class WeatherDisplay extends JFrame {
//
//    private Map<String, Double> temperatureData;
//
//    public WeatherDisplay(Map<String, Double> temperatureData) throws Exception {
//        this.temperatureData = temperatureData;
//
//        setTitle("Weather Map");
//        setSize(800, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        add(new WeatherPanel());
//        setVisible(true);
//
//
//    }
//
//    private class WeatherPanel extends JPanel {
//
//        @Override
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g);
//
//            for (Map.Entry<String, Double> entry : temperatureData.entrySet()) {
//                String[] parts = entry.getKey().split(",");
//                int lat = Integer.parseInt(parts[0]);
//                int lon = Integer.parseInt(parts[1]);
//                int temperature = entry.getValue().intValue();
//
//                // draw temperature data at (lat, lon) with color corresponding to temperature value
//                // you can customize this to your liking
//                Color color = getColorForTemperature(temperature);
//                g.setColor(color);
//                g.fillRect(lat, lon, 1, 1);
//            }
//        }
//
//        private Color getColorForTemperature(int temperature) {
//            // return a color based on temperature value
//            // you can customize this to your liking
//            if (temperature < 0) {
//                return Color.BLUE;
//            } else if (temperature < 10) {
//                return Color.CYAN;
//            } else if (temperature < 20) {
//                return Color.GREEN;
//            } else if (temperature < 30) {
//                return Color.YELLOW;
//            } else {
//                return Color.RED;
//            }
//        }
//
//    }
//
//
//    /**
//     * fetches JSON map data from API url
//     *
//     * @return
//     * @throws Exception
//     */
//    public static String getTempMapData() throws Exception {
//        String apiURL = "http://maps.openweathermap.org/maps/2.0/weather/TA2/2/1/1?appid=dbd8574d10549e41443636960496338d&fill_bound=true&opacity=0.6&palette=-65:821692;-55:821692;-45:821692;-40:821692;-30:8257db;-20:208cec;-10:20c4e8;0:23dddd;10:c2ff28;20:fff028;25:ffc228;30:fc8014";
//
//
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURL)).build();
//
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        return response.body();
//    }
//
//    /**
//     * method to parse temperature map data from JSON object
//     *
//     * @param responseBody
//     * @return
//     * @throws JSONException
//     */
//    public static Map<String, Double> parseTempMapData(String responseBody) throws JSONException {
//        Map<String, Double> temperatureData = new HashMap<>();
//
//        JSONObject weatherData = new JSONObject(responseBody);
//        JSONArray temperatureLayers = weatherData.getJSONArray("layers");
//
//        for (int i = 0; i < temperatureLayers.length(); i++) {
//            JSONObject temperatureLayer = temperatureLayers.getJSONObject(i);
//            String layerName = temperatureLayer.getString("name");
//
//            if (layerName.equals("temp")) {
//                JSONArray layerData = temperatureLayer.getJSONArray("data");
//
//                for (int j = 0; j < layerData.length(); j++) {
//                    JSONArray rowData = layerData.getJSONArray(j);
//
//                    for (int k = 0; k < rowData.length(); k++) {
//                        JSONArray gridPointData = rowData.getJSONArray(k);
//
//                        String lat = gridPointData.getString(0);
//                        String lon = gridPointData.getString(1);
//                        Double temperature = gridPointData.getDouble(2);
//
//                        String key = lat + "," + lon;
//                        temperatureData.put(key, temperature);
//                    }
//                }
//            }
//        }
//
//        return temperatureData;
//    }
//
//}