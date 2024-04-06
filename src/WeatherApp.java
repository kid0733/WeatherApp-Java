//Get Weather Data From API


import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class WeatherApp {
    //fetch weather data
    public static JSONObject getWeatherData(String locationName){
        //get current location:
        JSONArray locationData=getLocationData(locationName);
        //extract lat and long data
        JSONObject location=(JSONObject) locationData.get(0);
        double latitude=(double) location.get("latitude");
        double longitude=(double) location.get("longitude");


        //build API request URL with location cordinates
        String urlString="https://api.open-meteo.com/v1/forecast?" +
                "latitude="+latitude+"&longitude="+longitude+
                "&hourly=temperature_2m,relative_humidity_2m,weather_code,wind_speed_10m&timezone=Australia%2FSydney";

        try{
            //call api and get response
            HttpURLConnection conn=fetchApiResponse(urlString);
            //check response status
            if(conn.getResponseCode()!=200){
                System.out.println("Error: Could not connect");
                return null;
            }
            //store resulting json data
            StringBuilder resultJson=new StringBuilder();
            Scanner scanner=new Scanner(conn.getInputStream());
            while(scanner.hasNext()){
                //read and store data
                resultJson.append(scanner.nextLine());

            }

            //close scanner
            scanner.close();

            //close url connection
            conn.disconnect();

            //parse through our data
            JSONParser parser=new JSONParser();
            JSONObject resultJsonObj=(JSONObject) parser.parse(String.valueOf(resultJson));

            //retrive hourly data
            JSONObject hourly=(JSONObject) resultJsonObj.get("hourly");

            //getting current hour data
            JSONArray time=(JSONArray) hourly.get("time");
            int index=findIndexOfCurrentTime(time);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //retrive geographical  data
    public static JSONArray getLocationData(String locationName){
        //formatting the whitespace
        locationName=locationName.replace(" ","+");

        //buildAPI url with the location parameter
        String urlString="https://geocoding-api.open-meteo.com/v1/search?name="+locationName+"&count=10&language=en&format=json";
        try{
            //calling api to get a response
            HttpURLConnection conn=fetchApiResponse(urlString);

            //check response status
            if(conn.getResponseCode()!=200){
                System.out.println("Error: Could not connect to API");
            }else{
                //store api data
                StringBuilder resultJson =new StringBuilder();
                Scanner scanner=new Scanner(conn.getInputStream());

                //read and store the resulting json data
                while (scanner.hasNext()){
                    resultJson.append(scanner.nextLine());
                }

                //close scanner
                scanner.close();

                //close url connection
                conn.disconnect();

                //parse JSON
                JSONParser parser=new JSONParser();
                JSONObject resultsJsonObj=(JSONObject) parser.parse(String.valueOf(resultJson));

                //get the list of location data the API generated from the location name
                JSONArray locationData=(JSONArray) resultsJsonObj.get("results");
                return locationData;

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //Could not find location
        return null;


    }


    private static HttpURLConnection fetchApiResponse(String urlString){
        try{
            //attempt to create a connection
            URL url=new URL(urlString);
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();

            //set request to get

            conn.setRequestMethod("GET");

            //connect to the api
            conn.connect();
            return conn;

        } catch (IOException e) {
            e.printStackTrace();
        }
        //could not make connection
        return null;
    }

    private static int findIndexOfCurrentTime(JSONArray timeList){
        String currentTime=getCurrentTime();

    }

    private static String getCurrentTime(){
        //get curr date and time

        LocalDateTime currentDateTime=LocalDateTime.now();

        //Format date 2023-09-02T00:00
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':00'00'");

        //format and print current date and time

        String formattedDateTime=currentDateTime.format(formatter);

    }
}
