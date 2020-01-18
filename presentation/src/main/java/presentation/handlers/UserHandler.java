package presentation.handlers;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import model.User;
import presentation.UserModule;
import presentation.models.view.UserViewModel;

import java.util.HashMap;
import java.util.Objects;

import static presentation.handlers.ResourceHandler.getErrorHtml;

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
        var password = getValidFormParam(ctx, "password");
        var user = userModule.registerUser(username, password);

        if (user == null) {
            throw new BadRequestResponse("Invalid Request");
        }
        ctx.redirect("/login.html");
    }

    public static void login(Context ctx) {
        var username = getValidFormParam(ctx, "username");
        var password = getValidFormParam(ctx, "password");
        var user = userModule.loginUser(username, password);

        if (user == null) {
            throw new BadRequestResponse("Invalid Request");
        }
        ctx.sessionAttribute("username", user.getName());
        ctx.sessionAttribute("userId", user.getId());
        ctx.redirect("/lobby.html");
    }

    public static boolean isLoggedIn(Context ctx) {
        String username = ctx.attribute("username");

        if (username != null) {
            if (!username.isEmpty()) {
                return true;
            }
        }

        return false;
    }

    public static void getUsername(Context ctx) {
        String username = ctx.sessionAttribute("username");
        Integer userId = ctx.sessionAttribute("userId");

        if (userId == null) {
            ctx.json(new UserViewModel());
        } else {
            ctx.json(new UserViewModel(userId, username));
        }
    }
}
