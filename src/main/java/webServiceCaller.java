

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.omg.CORBA.ContextIdSeqHelper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;


public class webServiceCaller {
    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        webServiceCaller ws = new webServiceCaller();
     ws.makethecall();
    }

    public void makethecall() throws ClientProtocolException, IOException
    {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://127.0.0.1:8000/getData/");

// Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(14);
        params.add(new BasicNameValuePair("drunk", String.valueOf(Context.drunk)));
        params.add(new BasicNameValuePair("agressive",String.valueOf(Context.agressiv)));
        params.add(new BasicNameValuePair("distance", String.valueOf(Context.distance)));
        params.add(new BasicNameValuePair("distraction",String.valueOf(Context.distraction)));
        params.add(new BasicNameValuePair("fatigue", String.valueOf(Context.fatigue)));
        params.add(new BasicNameValuePair("illumination", String.valueOf(Context.illumination)));
        params.add(new BasicNameValuePair("speed_lim_ex",String.valueOf(Context.speedlimit)));
        params.add(new BasicNameValuePair("speed_diff", String.valueOf(Context.speed_diff)));
        params.add(new BasicNameValuePair("curved",String.valueOf(Context.curved)));
        params.add(new BasicNameValuePair("road", String.valueOf(Context.road)));
        params.add(new BasicNameValuePair("weather",String.valueOf(Context.weather)));
        params.add(new BasicNameValuePair("age_impact", String.valueOf(Context.age_impact)));
        params.add(new BasicNameValuePair("security",String.valueOf(Context.security)));
        params.add(new BasicNameValuePair("rented",String.valueOf(Context.rented)));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

//Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();



        if (entity != null) {
            InputStream instream = entity.getContent();
            try {
                String resp = convertStreamToString(instream);
                String[] r = resp.split(",");
                resp = r[1];
                Context.webservicecall = Character.getNumericValue(resp.charAt(resp.length()-1));
                System.out.println("WEB SERVICE : "+ resp.charAt(resp.length()-1));

            } finally {
                instream.close();
            }

        }}    }

