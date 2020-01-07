module presentation {
    requires io.javalin;
    requires j2html;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires org.eclipse.jetty.websocket.api;
    requires logic;

    opens presentation.models to com.fasterxml.jackson.databind;
    opens presentation.models.messages to com.fasterxml.jackson.databind;
}