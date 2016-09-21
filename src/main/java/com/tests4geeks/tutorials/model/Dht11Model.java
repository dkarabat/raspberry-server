package com.tests4geeks.tutorials.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "dht11")
public class Dht11Model {

    public Dht11Model(float temperature, float humidity) {
        time = new Date();
        this.temperature = temperature;
        this.humidity = humidity;
    }

    private String id;

    private Date time;

    private float temperature;

    private float humidity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
}
