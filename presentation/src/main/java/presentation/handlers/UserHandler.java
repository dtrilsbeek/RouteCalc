package presentation.handlers;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import model.User;
import presentation.UserModule;
import presentation.models.view.UserViewModel;
import java.util.HashMap;

public class UserHandler {

    private static UserModule userModule = new UserModule();
    private static HashMap<Integer, User> users = new HashMap<>();

    private static String getValidFormParam(Context ctx, String key) {
        var param = ctx.formParam(key);
        if (param != null) {
            if (!param.isEmpty()) {
                return param;
            }
        }

        throw new BadRequestResponse("Missing parameter");
    }

    public static User getUser(Integer id) {
        return users.get(id);
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

        users.put(user.getId(), user);
        ctx.sessionAttribute("username", user.getName());
        ctx.sessionAttribute("userId", user.getId());
        ctx.redirect("/lobby.html");
    }

    public static boolean isLoggedIn(Context ctx) {
        String username = ctx.sessionAttribute("username");

        if (username != null) {
            return !username.isEmpty();
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
