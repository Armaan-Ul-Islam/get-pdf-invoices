package com.aui.getinvo;

import com.aui.getinvo.web.GPIHttpServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

public class MainApplication {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.getConnector();

        Context context = tomcat.addContext("/customer-a", null);
        Wrapper servletWrapper = Tomcat.addServlet(context, "GPIServlet", new GPIHttpServlet());
        servletWrapper.setLoadOnStartup(1);
        servletWrapper.addMapping("/*");

        tomcat.start();
    }
}
