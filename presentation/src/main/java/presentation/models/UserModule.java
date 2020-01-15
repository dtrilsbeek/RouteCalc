package presentation.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import model.User;
import model.UserResponse;
import model.UserResponseList;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class UserModule {

    private static String host = "http://localhost:8090";

    private Gson gson = new Gson();

    public List<User> getAllUsers() {

        URL url = null;
        try {
            url = new URL(host + "/user");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer result = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
            }
            in.close();

            Type type = new TypeToken<UserResponseList>() {}.getType();
            UserResponseList userResponseList = gson.fromJson(String.valueOf(result), type);

            if (userResponseList != null) {
                return userResponseList.getUserList();
            }

            return new ArrayList<>();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public User loginUser(String username, String password) {
        try {
            var json = new JsonObject();
            json.addProperty("username", username);
            json.addProperty("password", password);

            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(URI.create(host + "/users/login"))
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(json)))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Type type = new TypeToken<UserResponse>() {}.getType();
                UserResponse userResponse = gson.fromJson(String.valueOf(response.body()), type);

                if (userResponse != null) {
                    return userResponse.getUser();
                }
            }
            else {
                System.out.println(response.statusCode());
            }

            System.out.println(response);

            throw new Exception("User API Unavailable");


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public User registerUser(String username, String password) {
        try {

            var json = new JsonObject();
            json.addProperty("username", username);
            json.addProperty("password", password);

            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(URI.create(host + "/users/register"))
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(json)))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            if (response.statusCode() == 200) {
                Type type = new TypeToken<UserResponse>() {}.getType();
                UserResponse userResponse = gson.fromJson(String.valueOf(response.body()), type);

                if (userResponse != null) {
                    return userResponse.getUser();
                }
            }
            else {
                System.out.println(response.statusCode());
            }

            System.out.println(response);

            throw new Exception("User API Unavailable");


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private String urlEncode(Map<String, String> arguments) throws UnsupportedEncodingException {
        StringJoiner sj = new StringJoiner("&");
        for(Map.Entry<String,String> entry : arguments.entrySet())
            sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                    + URLEncoder.encode(entry.getValue(), "UTF-8"));
        return sj.toString();
    }
}

