package com.example.servlet;

import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;

import io.github.ghosthack.turismo.servlet.Servlet;

/**
 * Runs {@link AppRoutes} in embedded Jetty. The same servlet setup works in
 * any Jakarta EE 10 container through web.xml (see the README).
 *
 * <p>Run with {@code mvn compile exec:java -Dexec.mainClass=com.example.servlet.ServletMain}
 */
public class ServletMain {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler ctx = new ServletContextHandler();
        ctx.setContextPath("/");
        ServletHolder holder = new ServletHolder(new Servlet());
        holder.setInitParameter("routes", AppRoutes.class.getName());
        ctx.addServlet(holder, "/*");
        server.setHandler(ctx);
        server.start();
        server.join();
    }

}
