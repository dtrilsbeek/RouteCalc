module presentation {
    requires logic;
    requires io.javalin;
    requires j2html;
    requires jackson.databind;
    requires jackson.annotations;
    requires org.eclipse.jetty.websocket.api;

    opens presentation.models to jackson.databind;
    opens presentation.models.messages to jackson.databind;
}