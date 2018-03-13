import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

    /**
     * The URL that is going to be used for the example HTTP call.
     */
    private static String EXAMPLE_HTTP_URL = "https://libs.stage.hive.pt/";

    private static void exampleHttp() throws IOException {
        exampleHttp(EXAMPLE_HTTP_URL);
    }

    private static void exampleHttp(String url) throws IOException {
        CloseableHttpResponse response = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        response = httpclient.execute(httpget);
        try {
            HttpEntity entity = response.getEntity();
            InputStream inputStream = entity.getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }
        } finally {
            response.close();
        }
    }

    public static void main(String[] args) {
        try {
            exampleHttp();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}