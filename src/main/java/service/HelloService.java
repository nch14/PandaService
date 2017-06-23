package service;

import com.sun.mail.util.MailSSLSocketFactory;
import common.FaultBean;
import common.LoginException;
import common.LoginInfo;
import sun.rmi.runtime.Log;
import sun.rmi.transport.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.mail.*;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.ws.Endpoint;
import javax.xml.ws.spi.*;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Properties;

/**
 * WebService
 * 将 Java 类标记为实现 Web Service，或者将 Java 接口标记为定义 Web Service 接口
 */
@WebService(serviceName="MyService", endpointInterface = "service.HelloServiceInterface")
public class HelloService implements HelloServiceInterface {



    @WebResult(name = "LoginResults", partName = "part",
            targetNamespace = "http://mail/login")
    @WebMethod(operationName = "login")
    public LoginInfo login(@WebParam(name = "LoginUsername", partName = "part", targetNamespace = "http://mail/login") String username,
                 @WebParam(name = "LoginPassword", partName = "part", targetNamespace = "http://mail/login") String password) throws LoginException{

        LoginInfo loginInfo = new LoginInfo();

        System.out.println("username: "+username+" |  password: "+password);
        loginInfo.setUsername(username);
        loginInfo.setPassword(password);

        String student_regEx = "^[0-9]{9}@smail.nju.edu.cn$";
        String assistant_regEx = "^MF[0-9]*@smail.nju.edu.cn";
        String teacher_regEx = "^.*@nju.edu.cn$";

        Pattern pattern = Pattern.compile(student_regEx);
        Matcher matcher = pattern.matcher(username);
        boolean student_match = matcher.matches();

        pattern = Pattern.compile(assistant_regEx);
        matcher = pattern.matcher(username);
        boolean assistant_match = matcher.matches();

        pattern = Pattern.compile(teacher_regEx);
        matcher = pattern.matcher(username);
        boolean teacher_match = matcher.matches();

        if(teacher_match){
            loginInfo.setKind("教师");
            loginInfo.setValid(true);
            return loginInfo;
        }

        if(student_match || assistant_match ){
            System.out.println("let's verify");
            if(check(username, password)){
                loginInfo.setValid(true);

                if(student_match){
                    loginInfo.setKind("学生");
                }else{
                    loginInfo.setKind("助教");
                }

            }else{
                loginInfo.setValid(false);
                throw new LoginException("账号不存在或者密码错误", new FaultBean("账号不存在或者密码错误"));
            }
        }else{
            System.out.println("user not valid");
            loginInfo.setValid(false);
            throw new LoginException("账号不是南大邮箱标准格式", new FaultBean("邮箱格式错误"));
        }

        return loginInfo;
    }


    public static boolean check(final String user,
                             final String password)
    {
        try {

            // create properties field
            Properties properties = new Properties();


            properties.setProperty("mail.store.protocol", "pop3");
            properties.setProperty("mail.pop3.host", "smtp.nju.edu.cn");

//            properties.setProperty("mail.transport.protocol", "smtp");
//            //服务器
//            properties.setProperty("mail.smtp.host", "smtp.nju.edu.cn");
//            //使用smtp身份验证
//            properties.setProperty("mail.smtp.auth", "true");


//            MailSSLSocketFactory sf = null;
//            try {
//                sf = new MailSSLSocketFactory();
//                sf.setTrustAllHosts(true);
//            } catch (GeneralSecurityException e1) {
//                e1.printStackTrace();
//            }
//            properties.put("mail.smtp.ssl.enable", "true");
//            properties.put("mail.smtp.ssl.socketFactory", sf);


            //获取Session对象
            Session s = Session.getDefaultInstance(properties,new Authenticator() {
                //此访求返回用户和密码的对象
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    PasswordAuthentication pa = new PasswordAuthentication(user, password);
                    return pa;
                }
            });
            //设置session的调试模式，发布时取消
            s.setDebug(true);


            Store store = s.getStore();
            store.connect("smtp.nju.edu.cn", user, password);
            System.out.println(store.isConnected());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }



    public static void main(String[] args) {
        /**
         * 参数1：服务的发布地址
         * 参数2：服务的实现者
         *  Endpoint  会重新启动一个线程
         */
        Endpoint.publish("http://localhost:9002/ServiceHello", new HelloService());
        System.out.println("Server ready...");

    }

}
