package links;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MalformedURLExceptionExample {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String URL = "mailto://examples.javacodegeeks.com/core-java/io/bufferedreader/how-to-get-the-standard-input-in-java/";

    public static void main(String[] args) {
        try {
            System.out.println(sendGetRequest(URL));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String sendGetRequest(String urlString) throws IOException {

        URL obj = new URL(urlString);
        HttpURLConnection httpConnection = (HttpURLConnection) obj
                .openConnection();

        httpConnection.setRequestMethod("GET");

        httpConnection.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = httpConnection.getResponseCode();
        if (responseCode == 200) {

            BufferedReader responseReader = new BufferedReader(new InputStreamReader(
                    httpConnection.getInputStream()));

            String responseLine;
            StringBuffer response = new StringBuffer();

            while ((responseLine = responseReader.readLine()) != null) {
                response.append(responseLine+"\n");
            }
            responseReader.close();

            // print result
            return response.toString();
        }
        return null;

    }
}