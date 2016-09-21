package com.tests4geeks.tutorials;

import com.tests4geeks.tutorials.model.Dht11Model;
import com.tests4geeks.tutorials.raspi.dht11.Dht11;
import com.tests4geeks.tutorials.repository.Dht11MongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger log = LoggerFactory.getLogger(ApplicationStartup.class);

    @Autowired
    Dht11MongoRepository dht11MongoRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        new Thread() {
            public void run() {
                Dht11 dht = new Dht11();
                while (true){
                    Dht11Model dht11Model = dht.getTemperature();
                    if (dht11Model != null) {
                        dht11MongoRepository.save(dht11Model);
                        log.info("count {}", dht11MongoRepository.count());
                    }
                    try {
                        Thread.sleep(600 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
