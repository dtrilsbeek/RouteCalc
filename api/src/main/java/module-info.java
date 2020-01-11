module api {
    requires java.sql;
    requires javax.ws.rs.api;
    requires jersey.container.servlet.core;
    requires javax.servlet.api;
    requires org.eclipse.jetty.servlet;
    requires org.eclipse.jetty.server;
    requires org.eclipse.jetty.util;
    requires shared;
    requires data;
    requires gson;

    exports service;
}