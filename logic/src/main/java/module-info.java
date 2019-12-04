module logic {
    requires data;
    requires io.javalin;
    requires java.logging;
    exports logic;
    exports logic.room;
    exports logic.round;
}