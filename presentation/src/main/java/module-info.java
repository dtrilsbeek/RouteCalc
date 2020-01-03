module presentation {
    requires io.javalin;
    requires j2html;
    requires jackson.databind;
    requires jackson.annotations;
    requires org.eclipse.jetty.websocket.api;
    requires logic;

    opens presentation.models to jackson.databind;
    opens presentation.models.messages to jackson.databind;
}