package io.github.bodzisz;

import jakarta.xml.ws.Endpoint;

import java.io.IOException;

import static java.lang.System.exit;

public class ServiceHost2 {

    public static void main(String[] args) {
        System.out.println("Web Service PersonService is running ...");
        Endpoint.publish("http://localhost:8081/personservice", new PersonServiceImpl2());
        System.out.println("Press ENTER to STOP PersonService ...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        exit(0);
    }
}
