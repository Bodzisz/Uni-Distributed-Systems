package io.github.bodzisz;

import java.net.MalformedURLException;
import java.net.URL;

public class Client {

    public static void main(String[] args) throws PersonNotFoundException_Exception, MalformedURLException {
        int num =-1;
        URL addr = new URL("http://localhost:8081/personservice?wsdl");
        PersonService_Service pService = new PersonService_Service();
        PersonService pServiceProxy = pService.getPersonServiceImplPort();
        num = pServiceProxy.countPersons();
        System.out.println("Num of Persons = "+num);
        Person person = pServiceProxy.getPerson(2);
        System.out.println("Person "+person.getFirstName()+", id = "+person.getId());
    }
}
