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

    public static void register(Context ctx) {
        var username = ctx.formParam("username");
        var password = ctx.formParam("password");
        if (username == null) throw new BadRequestResponse("Missing parameter");
        if (password == null) throw new BadRequestResponse("Missing parameter");

        var user = userModule.registerUser(username, password);

        System.out.println(user.getName());
        System.out.println(user.getPassword());

        if (user == null) {
            throw new BadRequestResponse("Invalid Request");
        }
        ctx.redirect("/login.html");
    }
}
