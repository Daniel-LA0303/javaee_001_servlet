package org.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/headers")
public class HeaderHttpRequestServlet extends jakarta.servlet.http.HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Esto obtiene el método y la URI de la petición
        String method = request.getMethod(); //método de la petición
        String requestURI = request.getRequestURI(); //uri completa
        String requestURL = request.getRequestURL().toString(); //url completa
        String contextPath = request.getContextPath(); //ruta del contexto
        String servletPath = request.getServletPath(); //ruta del servlet
        String ipClient = request.getRemoteAddr(); //ip del cliente que hace la petición
        String ip = request.getLocalAddr(); //ip del servidor
        int port = request.getLocalPort(); //puerto del servidor
        String scheme = request.getScheme(); //protocolo
        String host = request.getHeader("host"); //host
        String url = scheme + "://" + host + contextPath + servletPath; //url completa
        String url2 = requestURL.substring(0, requestURL.length() - requestURI.length()) + contextPath + servletPath; //url completa


        PrintWriter out = response.getWriter();

        out.println("<DOCTYPE html>");
        out.println("<html>");
        out.println("   <head>");
        out.println("       <title>Headers</title>");
        out.println("   </head>");
        out.println("   <body>");
        out.println("       <h1>Headers</h1>");
        out.println("       <p>Método: " + method + "</p>");
        out.println("       <p>URI: " + requestURI + "</p>");
        out.println("       <p>URL: " + requestURL + "</p>");
        out.println("       <p>Context Path: " + contextPath + "</p>");
        out.println("       <p>Servlet Path: " + servletPath + "</p>");
        out.println("       <p>IP: " + ip + "</p>");
        out.println("       <p>IP cliente: " + ipClient + "</p>");
        out.println("       <p>Puerto: " + port + "</p>");
        out.println("       <p>Protocolo: " + scheme + "</p>");
        out.println("       <p>Host: " + host + "</p>");
        out.println("       <p>URL completa: " + url + "</p>");
        out.println("       <p>URL completa 2: " + url2 + "</p>");
        out.println("       <h2>-------------------------</h2>");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            out.println("       <p>" + headerName + ": " + request.getHeader(headerName) + "</p>");
        }
        out.println("   </body>");
        out.println("</html>");


    }
}
