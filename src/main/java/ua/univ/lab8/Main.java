package ua.univ.lab8;


import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import ua.univ.lab8.Settings.Settings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.List;


/**
 * Created by Asus on 08.12.2018.
 */

public class Main {
    public static void main(String [] args) {
        try {
            String webappDirLocation = "src/main/java/ua/univ/lab8/";
            Tomcat tomcat = new Tomcat();

            String webPort = System.getenv("PORT");
            if (webPort == null || webPort.length() == 0) {
                webPort = "8080";
            }

            tomcat.setPort(Integer.valueOf(webPort));

            StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation + "\\JSP").getAbsolutePath());
            System.out.println("configuring app with basedir: " + new File(webappDirLocation + "\\JSP").getAbsolutePath());

            File additionWebInfClasses = new File("D:/Univ/V/MOOP/target/classes");
            WebResourceRoot resources = new StandardRoot(ctx);
            resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
            ctx.setResources(resources);

            Settings.infoLogger.info("**************************************************************");
            Settings.infoLogger.info("Starting server...............................................");

            tomcat.start();
            tomcat.getServer().await();
        } catch (Exception e) {
            Settings.errorLogger.error(e.getMessage());
        }
    }
}
