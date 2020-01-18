module presentation {
    requires io.javalin;
    requires j2html;
    requires gson;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires org.eclipse.jetty.websocket.api;
    requires logic;
    requires shared;
    requires java.net.http;

    opens presentation.models to com.fasterxml.jackson.databind;
    opens presentation.models.messages to com.fasterxml.jackson.databind;
    opens presentation.models.view to com.fasterxml.jackson.databind;
}