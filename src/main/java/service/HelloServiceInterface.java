package service;

import common.LoginException;
import common.LoginInfo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Created by echo on 17/6/1.
 */
@WebService(serviceName="MyService", endpointInterface = "service.HelloServiceInterface")
public interface HelloServiceInterface {


    @WebResult(name = "LoginResults", partName = "part",
            targetNamespace = "http://mail/login")
    @WebMethod(operationName = "login")
    LoginInfo login(@WebParam(name = "LoginUsername", partName = "part", targetNamespace = "http://mail/login") String username,
                    @WebParam(name = "LoginPassword", partName = "part", targetNamespace = "http://mail/login") String password)
            throws LoginException;

}
