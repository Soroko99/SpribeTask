package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class User implements Pojo{
    @JsonProperty("age")
    private String age;
    @JsonProperty("editor")
    private String editor;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("login")
    private String login;
    @JsonProperty("password")
    private String password;
    @JsonProperty("role")
    private String role;
    @JsonProperty("screenName")
    private String screenName;

    public User(String age, String gender, String login, String password, String role, String screenName) {
        this.age = age;
        this.gender = gender;
        this.login = login;
        this.password = password;
        this.role = role;
        this.screenName = screenName;
    }
}
