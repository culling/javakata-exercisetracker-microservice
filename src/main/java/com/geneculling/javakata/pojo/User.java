package com.geneculling.javakata.pojo;
import com.geneculling.javakata.api.Jsonable;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.UUID;

public class User implements Jsonable{
    private static final Gson GSON = new Gson();
    private String username;
    private String _id;

    public User(String username) {
        this.username = username;
        this._id = UUID.randomUUID().toString();
    }

    public User(String username, UUID _id) {
        this.username = username;
        this._id = _id.toString();
    }

    public User(String username, String _id) {
        this.username = username;
        this._id = _id;
    }

    public String get_id() {
        return _id.toString();
    }

    public String getUsername() {
        return username;
    }

    @Override
    public JsonElement getJson() {
        return GSON.toJsonTree( GSON.toJson(this));
    }

}
