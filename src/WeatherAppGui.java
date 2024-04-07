import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeatherAppGui extends JFrame {
    private JSONObject weatherData;
    public WeatherAppGui() {


        //set up the gui
        //calling super{JFrame}/parent element
        super("Weather App");

        //Exit process on close
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //set size of teh gui:
        setSize(450, 650);

        //load the gui at center of the screen:
        setLocationRelativeTo(null);

//        allowing custom layouts:
        setLayout(null);

        //No resize:
        setResizable(false);


        addGuiComponents();
        getContentPane().setBackground(new Color(155, 189, 171));
    }

    private void addGuiComponents(){
        //Search Field
        JTextField searchTextField=new JTextField();

        //set the location and size of our components
        searchTextField.setBounds(15,15,351,45);

        //change fontstyle
        searchTextField.setFont(new Font("Dialog", Font.PLAIN,20));

        add(searchTextField);

        //weather image
        JLabel weatherConditionImage= new JLabel(loadImage("assets/cloudy.png"));
        weatherConditionImage.setBounds(0,125,450,217);
        add(weatherConditionImage);

//       temperature text:
        JLabel temperatureText=new JLabel("10 C");
        temperatureText.setBounds(0,350,450,54);
        temperatureText.setFont(new Font("Dialog", Font.BOLD, 48));
        temperatureText.setForeground(new Color(79, 97, 88));
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureText);


        //weather condition description
        JLabel weatherConditionDesc=new JLabel("Cloudy");
        weatherConditionDesc.setBounds(0,425,450,36);
        weatherConditionDesc.setForeground(new Color(79, 97, 88));
        weatherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
        weatherConditionDesc.setFont(new Font("Dialog", Font.BOLD, 24));
        add(weatherConditionDesc);

        //humidity image
        JLabel humidityImage=new JLabel(loadImage("assets/humidity.png"));
        humidityImage.setBounds(15,500,50,60);
        add(humidityImage);

        //humidity text
        JLabel humidityText=new JLabel("<html><b>HUMIDITY:</b> 100%</html>");
        humidityText.setBounds(85,485,95,95);
        humidityText.setForeground(new Color(79, 97, 88));
        humidityText.setFont(new Font("Dialog", Font.BOLD, 16));
        add(humidityText);

        //windspeed image
        JLabel windspeedImage=new JLabel(loadImage("assets/windspeed.png"));
        windspeedImage.setBounds(220,500,60,50);
        add(windspeedImage);

        //windspeed  text:
        JLabel windspeedText=new JLabel("<html><b>WINDSPEED:</b> 15km/h</html>");
        windspeedText.setBounds(300,485,100,95);
        windspeedText.setForeground(new Color(79, 97, 88));
        windspeedText.setFont(new Font("Dialog", Font.BOLD, 16));
        add(windspeedText);

        //search button

        JButton searchButton=new JButton(loadImage("assets/search.png"));
//        change cursor when hovering over search button
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(375,13,50,50);
        searchButton.setBackground(new Color(221, 240, 230));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get location

                String userInput=searchTextField.getText();

                //validate input -remove whitespace to ensure non-empty text
                if(userInput.replaceAll("\\s", "").length()<=0){
                    return ;
                }

                //retrieve weather data
                weatherData=WeatherApp.getWeatherData(userInput);

                //update gui

                //update weather image
                String weatherCondition=(String) weatherData.get("weather_condition");

                //updating weather image
                switch (weatherCondition){
                    case "Clear":
                        weatherConditionImage.setIcon(loadImage("assets/clear.png"));
                        break;
                    case "Cloudy":
                        weatherConditionImage.setIcon(loadImage("assets/cloudy.png"));
                        break;
                    case "Rain":
                        weatherConditionImage.setIcon(loadImage("assets/rain.png"));
                        break;
                    case "Snow":
                        weatherConditionImage.setIcon(loadImage("assets/snow.png"));
                        break;

                }

                //update Temperature text

                double temperature=(double) weatherData.get("temperature");
                temperatureText.setText(temperature+" C");

                //update weather condition
                weatherConditionDesc.setText(weatherCondition);

                //update humidity text
                long humidity = (long) weatherData.get("humidity");
                humidityText.setText("<html><b>Humidity:</b>"+humidity+"%</html>");

                //update windspeed text
                double windspeed = (double) weatherData.get("windspeed");
                windspeedText.setText("<html><b>WINDSPEED:</b>"+windspeed+"km/h</html>");
            }
        });
        add(searchButton);
    }

    //We use this function to create images in the GUI
    private ImageIcon loadImage(String resourcePath){
        try{
            //Read Image file from path given
            BufferedImage image= ImageIO.read(new File(resourcePath));

            //return an image icon so that our component can render it
            return new ImageIcon(image);


        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("Could not find the resource");
        return null;

    }

}