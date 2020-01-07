package service.user;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserResponseList {
    private String operation = "";
    private String expression = "";
    private String result = "";
    private List<User> UserList = new ArrayList<>();

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public List<User> getUserList() {
        return UserList;
    }

    public void setUserList(List<User> UserList) {
        this.UserList = UserList;
    }

    public void addToResponseList(User User) {
        this.UserList.add(User);
    }
}
