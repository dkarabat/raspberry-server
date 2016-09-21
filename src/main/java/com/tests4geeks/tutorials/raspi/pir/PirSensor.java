package com.tests4geeks.tutorials.raspi.pir;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PirSensor {

    private static final Logger log = LoggerFactory.getLogger(PirSensor.class);
    final GpioController gpio = GpioFactory.getInstance();
    private static final Pin DEFAULT_PIN = RaspiPin.GPIO_01; // #12, GPIO 18.
    private Pin pirPin = null;
    private GpioPinDigitalInput pirInput;
    private MotionDetectionInterface detector;

    public PirSensor(MotionDetectionInterface parent)
    {
        this(DEFAULT_PIN, parent);
    }

    public PirSensor(Pin p, MotionDetectionInterface parent)
    {
        this.pirPin = p;
        this.detector = parent;
        init();
    }

    private void init()
    {
        this.pirInput = gpio.provisionDigitalInputPin(pirPin, "Motion");
        this.pirInput.addListener(new GpioPinListenerDigital()
        {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event)
            {
                if ("true".equals(System.getProperty("pir.verbose", "false")))
                    log.info(" >>> GPIO pin state changed: time=" + System.currentTimeMillis() + ", " + event.getPin() + " = " + event.getState());
                if (event.getState().isHigh())
                {
                    detector.motionDetected();
                }
            }
        });
    }

    public void shutdown()
    {
        gpio.shutdown();
    }

}
