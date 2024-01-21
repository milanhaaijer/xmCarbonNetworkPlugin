package me.carbonnetwork.xmplugin.api;

import me.carbonnetwork.xmplugin.XmPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIRequest {

    private final XmPlugin plugin;

    public APIRequest(XmPlugin plugin) {
        this.plugin = plugin;
    }

    public String sendGETRequest(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                connection.disconnect();

                return response.toString();
            } else {
                return "ERROR: API request failed with response code: " + responseCode;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: API request failed with an exception: " + e.getMessage();
        }
    }

    public int sendPOSTRequest(String apiUrl, String data) {

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = data.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();

            connection.disconnect();

            return responseCode;

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }

        return 500;
    }
}
