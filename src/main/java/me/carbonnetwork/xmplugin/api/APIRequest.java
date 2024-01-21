package me.carbonnetwork.xmplugin.api;

import me.carbonnetwork.xmplugin.XmPlugin;
import org.json.JSONObject;

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
    private String apiURL = "http://api.carbonnetwork.net/";

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

    public int sendDELETERequest(String apiUrl) {

        String token = plugin.getAPIToken();
        if (token == null) {
            login();
            token = plugin.getAPIToken();
        }

        this.plugin.getLogger().severe("Token: " + token);

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Authorization", "Bearer " + token);

            int responseCode = connection.getResponseCode();

            connection.disconnect();

            return responseCode;

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }

        return 500;
    }

    public int sendPUTRequest(String apiUrl, String data) {

        String token = plugin.getAPIToken();
        if (token == null) {
            login();
            token = plugin.getAPIToken();
        }

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);

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

    public String sendPATCHRequest(String apiUrl, String data) {

        String token = plugin.getAPIToken();
        if (token == null) {
            login();
            token = plugin.getAPIToken();
        }

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);

            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = data.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

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
            e.getMessage();
        }

        return "ERROR: API request failed with an exception";
    }

    public void login() {

        final String d = null;

        try {
            URL url = new URL(apiURL + "auth/login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String data = "{\"user\":\"server\",\"password\":\"rc($T-41gmw5~sAIOz3P'UspP\"}";
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = data.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                this.plugin.getLogger().severe("Response: " + response.toString());
                if (response.toString().startsWith("{")) {
                    JSONObject json = new JSONObject(response.toString());
                    String token = json.getString("token");
                    plugin.setAPIToken(token);
                }
            } else {
                this.plugin.getLogger().severe("API request failed with response code: " + responseCode);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getMessage();
        }

    }
}
