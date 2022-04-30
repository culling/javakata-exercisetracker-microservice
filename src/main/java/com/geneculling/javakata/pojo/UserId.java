package com.geneculling.javakata.pojo;
import com.geneculling.javakata.api.Jsonable;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.UUID;

public class UserId {
    private String username;
    private String _id;

    public UserId(String username) {
        this.username = username;
        this._id = UUID.randomUUID().toString();
    }

    public UserId(String username, UUID _id) {
        this.username = username;
        this._id = _id.toString();
    }

    public UserId(String username, String _id) {
        this.username = username;
        this._id = _id;
    }

}
