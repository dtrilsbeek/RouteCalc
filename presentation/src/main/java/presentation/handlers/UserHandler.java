package presentation.handlers;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import model.User;
import presentation.UserModule;

import java.util.HashMap;

public class UserHandler {

    private static UserModule userModule = new UserModule();
    private static HashMap<Integer, User> users;
    private static int roomCount = 1;

    private static String getValidFormParam(Context ctx, String key) {
        var param = ctx.formParam(key);
        if (param != null) {
            if (!param.isEmpty()) {
                return param;
            }
        }

        throw new BadRequestResponse("Missing parameter");
    }

    public static void register(Context ctx) {
        var username = getValidFormParam(ctx, "username");
        var password = getValidFormParam(ctx,"password");
        var user = userModule.registerUser(username, password);

        if (user == null) {
            throw new BadRequestResponse("Invalid Request");
        }
        ctx.redirect("/login.html");
    }

    public static void login(Context ctx) {
        var username = getValidFormParam(ctx, "username");
        var password = getValidFormParam(ctx,"password");
        var user = userModule.loginUser(username, password);

        if (user == null) {
            throw new BadRequestResponse("Invalid Request");
        }
        ctx.redirect("/lobby.html");
    }
}
