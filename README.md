# ☀️ JavaWeather: A GUI Weather App in Java ⛅

Welcome to JavaWeather, a sleek and intuitive weather application built using Java and its powerful GUI capabilities! ⚡ With JavaWeather, you can easily fetch and display real-time weather information for any location around the world. Stay informed about current weather conditions, temperature, humidity, wind speed, and more, all within a beautifully designed graphical user interface. ☔💨

## 🌟 Features

JavaWeather comes packed with a range of features to keep you updated on the latest weather conditions:

- 🌍 Search for weather information by city name 
- 🌡️ Display current temperature, humidity, wind speed, and weather description
- ☁️ Show weather icons representing the current weather conditions
- 🌅 Colorful and visually appealing GUI design
- 🌦️ Images that change according to the weather conditions
- 🌐 Fetches weather data from a reliable weather API
- 🎨 Customizable themes and color schemes
- 💾 Save favorite locations for quick access
- 📅 5-day weather forecast for selected location

## 🚀 Getting Started

To run JavaWeather on your local machine, follow these steps:

1. Clone the repository:

git clone https://github.com/kid0733/WeatherApp-Java.git


2. Navigate to the project directory:
cd javaweather

3. Compile the Java files:
javac AppLauncher.java WeatherAppGUI.java WeatherApp.java

4. Run the application:
java AppLauncher

5. The JavaWeather app will launch, and you can start exploring the weather information for different locations!

## 📂 File Structure

The JavaWeather project consists of three main files:

1. `AppLauncher.java`: This file contains the main method and is responsible for launching the JavaWeather application.

2. `WeatherAppGUI.java`: This file defines the graphical user interface (GUI) components and handles user interactions. It displays the weather information, allows users to search for locations, and updates the GUI based on the fetched weather data.

3. `WeatherApp.java`: This file handles the communication with the weather API. It sends requests to the API based on the user's input and retrieves the weather data in JSON format. It then parses the JSON response and provides the relevant information to the WeatherApp class.

## 🌐 Weather API

JavaWeather relies on a weather API to fetch accurate and up-to-date weather information. The application uses the [OpenWeatherMap API](https://openweathermap.org/) to retrieve weather data. To use the API, you need to sign up for a free API key and replace the placeholder `API_KEY` in the `WeatherAPI.java` file with your actual API key.


## 📧 Contact

If you have any questions, feedback, or just want to say hi, feel free to reach out:

- Email: siddhantskarki@gmail.com

---

Thank you for choosing JavaWeather as your go-to weather application! I hope it keeps you informed about the weather conditions and provides a delightful user experience. Don't forget to star ⭐ the repository if you find it helpful!

Happy weather checking! ☀️🌦️⛅
