package client;

import service.HelloServiceInterface;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * Created by echo on 17/6/1.
 */
public class HelloClient {
    public static void main(String[] args) throws Exception {
        QName serviceName = new QName("http://service/", "MyService");
        QName portName = new QName("http://service/", "HelloServicePort");

        String addr = "http://localhost:9001/ServiceHello?wsdl";
        URL url = new URL(addr);

        Service service = Service.create(url, serviceName);
        HelloServiceInterface helloService = service.getPort(portName,HelloServiceInterface.class);


    }
}
