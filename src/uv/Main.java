package uv;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        String Data = "", Data1 = "", JsonData = "", JsonData1 = "";
        String city = "";
        Double latitude, longitude, uv_index;
        //Request to know the Location
        URL ipapi = new URL("https://ipapi.co/json/");
        URLConnection c = ipapi.openConnection();
        c.setRequestProperty("User-Agent", "java-ipapi-client");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(c.getInputStream()))) {
            while((Data =reader.readLine())!=null) {
                JsonData += Data;
            }
        }
        catch (IOException e){
            System.out.println("Error Occured in Fetching Location :"+e.getMessage().toString());
        }
        //Declare a JSON Parser
        org.json.simple.parser.JSONParser parse = new org.json.simple.parser.JSONParser();
        //JSON object to convert String Objects to JSON objects
        JSONObject obj = (JSONObject)parse.parse(JsonData);
        //Extract Required Data
        city = (String) obj.get("city");
        latitude = (Double) obj.get("latitude");
        longitude = (Double) obj.get("longitude");
        //Request to know the UV Index
        String ourl = String.format(Locale.ROOT,"http://api.openweathermap.org/data/2.5/uvi?appid=69797c063e80c8423de9aa25d436eb1c&lat=%f&lon=%f",Double.valueOf(latitude), Double.valueOf(longitude));
        URL open = new URL(ourl);
        HttpURLConnection con = (HttpURLConnection) open.openConnection();
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            while ((Data1 = br.readLine()) != null) {
                JsonData1 += Data1;
            }
        }
        catch (IOException e){
            System.out.println("Error Occured in Fetching UV Index :"+e.getMessage().toString());
        }
        obj = (JSONObject)parse.parse(JsonData1);
        uv_index = (Double) obj.get("value");
        System.out.println("Your Location "+city+" has an UV Index of "+uv_index);

        XMLReader xobj = new XMLReader(); // Know the  Risk of Exposure and Safety Measures.
        try {
            xobj.readData(uv_index);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }
}
