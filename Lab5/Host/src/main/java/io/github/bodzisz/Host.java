package io.github.bodzisz;

import io.github.bodzisz.service.PersonServiceImpl;
import jakarta.xml.ws.Endpoint;

import java.io.IOException;

import static java.lang.System.exit;

public class Host {

    public static void main(String[] args) {
        System.out.println("Web Service PersonService is running ...");
        Endpoint.publish("http://localhost:8081/personservice", new PersonServiceImpl());
        System.out.println("Press ENTER to STOP PersonService ...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        exit(0);
    }
}
