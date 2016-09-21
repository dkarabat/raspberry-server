package com.tests4geeks.tutorials.raspi.pir;

import com.pi4j.io.gpio.RaspiPin;

public class SampleMain {

    public static void main(String[] args)
    {
        final PirSensor md = new PirSensor(RaspiPin.GPIO_05, () -> System.out.println("Something is moving!!"));
        final Thread coreThread = Thread.currentThread();

        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            public void run()
            {
                System.out.println("\nUser interrupted.");
                synchronized (coreThread)
                {
                    coreThread.notify();
                }
            }
        });

        System.out.println("...On watch.");
        try
        {
            synchronized (coreThread)
            {
                coreThread.wait();
            }
        }
        catch (Exception ex) { ex.printStackTrace(); }
        md.shutdown();
        System.out.println("Done.");
    }
}
