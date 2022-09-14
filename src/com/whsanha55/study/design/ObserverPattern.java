package com.whsanha55.study.design;

import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class ObserverPattern {

    public static void main(String[] args) {
        var weatherData = new WeatherData();

        var currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);

        weatherData.setMeasurements(20, 50,30);
        weatherData.setMeasurements(40, 40,32);
        weatherData.setMeasurements(60, 30,36);
    }


    interface Subject {

        void registerObserver(Observer observer);

        void removeObserver(Observer observer);

        void notifyObservers();

    }

    interface Observer {

        void update(double temperature, double humidity, double pressure);
    }

    interface DisplayElement {

        void display();
    }


    @NoArgsConstructor
    @ToString
    static class WeatherData implements Subject {

        private List<Observer> observers = new ArrayList<>();
        private double temperature;
        private double humidity;
        private double pressure;

        @Override
        public void registerObserver(Observer observer) {
            observers.add(observer);
        }

        @Override
        public void removeObserver(Observer observer) {
            observers.remove(observer);
        }

        @Override
        public void notifyObservers() {
            observers.forEach(observer -> observer.update(temperature, humidity, pressure));
        }

        public void setMeasurements(double temperature, double humidity, double pressure) {
            this.temperature = temperature;
            this.humidity = humidity;
            this.pressure = pressure;
            notifyObservers();
        }
    }


    @ToString
    static class CurrentConditionsDisplay implements  Observer, DisplayElement {
        private double temperature;
        private double humidity;
        @ToString.Exclude
        private WeatherData weatherData;

        public CurrentConditionsDisplay(WeatherData weatherData) {
            this.weatherData = weatherData;
            weatherData.registerObserver(this);
        }

        @Override
        public void update(double temperature, double humidity, double pressure) {
            this.temperature = temperature;
            this.humidity = humidity;
            display();
        }

        @Override
        public void display() {
            System.out.println("CurrentConditionsDisplay = " + this);
            System.out.println(weatherData.toString());
        }
    }
}
