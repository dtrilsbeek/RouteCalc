package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import database.Database;
import model.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;

@Path("/users")
public class UserService {

    private Gson gson;
    private Database database;

    // TODO: Basic Auth

    public UserService() {
        this.gson = new Gson();
        this.database = new Database();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {

        UserResponse response = new UserResponse();
        response.setOperation("getUser");
        response.setExpression("/" + id);

        try {
            var userId = Integer.parseInt(id);
            var user = database.getUserById(userId);

            if (user == null) {
                response.setResult("User not found");
            } else {
                response.setResult("User found");
                response.setUser(user);
            }

        } catch (NumberFormatException nfe) {
            response.setResult("invalid value");
        }

        String output = gson.toJson(response);
        return Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(
            String UserInput
    ) {
        Type type = new TypeToken<UserRequest>() {
        }.getType();
        UserRequest request = gson.fromJson(UserInput, type);

        UserResponse response = new UserResponse();
        response.setOperation("loginUser");
        response.setExpression("POST");

        try {
            var requestName = request.getUsername();
            var requestPassword = request.getPassword();
            var user = new User(requestName, requestPassword);

            var userResult = database.checkPassword(user.getName(), user.getPassword());

            if (userResult == null) {
                response.setResult("No user found");
                return Response.status(400).entity(response).build();
            }

            response.setUser(userResult);
            response.setResult("success");

        } catch (NumberFormatException nfe) {
            response.setResult("invalid value");
            return Response.status(400).entity(response).build();
        }

        String output = gson.toJson(response);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(output).build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertUser(
            String UserInput
    ) {
        Type type = new TypeToken<UserRequest>() {
        }.getType();
        UserRequest request = gson.fromJson(UserInput, type);

        UserResponse response = new UserResponse();
        response.setOperation("insertUser");
        response.setExpression("POST");

        try {
            var requestName = request.getUsername();
            var requestPassword = request.getPassword();
            var user = new User(requestName, requestPassword);

            if (!database.insertUser(user)) {
                response.setResult("false");
            }
            else {
                response.setResult("success");
                response.setUser(user);
            }

        } catch (NumberFormatException nfe) {
            response.setResult("invalid value");
            return Response.status(400).entity(response).build();
        }

        String output = gson.toJson(response);
        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(output).build();
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
