package com.geneculling.javakata.servlet;

import com.atlassian.confluence.setup.settings.SettingsManager;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.geneculling.javakata.api.DataStore;
import com.geneculling.javakata.impl.DataStoreFactory;
import com.geneculling.javakata.impl.MemoryDataStore;
import com.geneculling.javakata.impl.DataStoreUserIDUtils;
import com.geneculling.javakata.pojo.UserId;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserServlet extends HttpServlet {

    private final TemplateRenderer renderer;
    private final SettingsManager settingsManager;
    private final static Gson GSON = new Gson();

    private static DataStore dataStore;

    private final static String USER_IDS_KEY = "userIds";
    private final static Type USER_LIST_CLASS_TOKEN = new TypeToken<List<UserId>>(){}.getType();


    UserServlet(
            @ComponentImport PluginSettingsFactory pluginSettingsFactory,
            @ComponentImport TemplateRenderer renderer,
            @ComponentImport SettingsManager settingsManager) {
        this.renderer = renderer;
        this.settingsManager = settingsManager;
        this.dataStore = new DataStoreFactory(pluginSettingsFactory).getDataStore();
    }

    /**
     * Get Method
     * <p>
     * User Stories
     * + You can make a GET request to /api/users to get a list of all users.
     * + The GET request to /api/users returns an array.
     * + Each element in the array returned from GET /api/users is an object literal containing a user's username and _id.
     * <p>
     * <p>
     * + You can make a GET request to /api/users/:_id/logs to retrieve a full exercise log of any user.
     * + A request to a user's log GET /api/users/:_id/logs returns a user object with a count property representing the + number of exercises that belong to that user.
     * + A GET request to /api/users/:id/logs will return the user object with a log array of all the exercises added.
     * + Each item in the log array that is returned from GET /api/users/:id/logs is an object that should have a description, + duration, and date properties.
     * + The description property of any object in the log array that is returned from GET /api/users/:id/logs should be a + string.
     * + The duration property of any object in the log array that is returned from GET /api/users/:id/logs should be a number.
     * + The date property of any object in the log array that is returned from GET /api/users/:id/logs should be a string.
     * <p>
     * + Use the dateString format of the Date API.
     * + You can add from, to and limit parameters to a GET /api/users/:_id/logs request to retrieve part of the log of any user. from and to are dates in yyyy-mm-dd format. limit is an integer of how many logs to send back.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        List<UserId> list = new ArrayList<>();
        String json = dataStore.load(USER_IDS_KEY);
        if(json != null){
            list = GSON.fromJson(json, USER_LIST_CLASS_TOKEN);
        }
        json = GSON.toJson(list);
        response.getWriter().write(json);
        response.flushBuffer();
    }

    /**
     * Post method
     *
     * User Stories
     * + You can POST to /api/users with form data username to create a new user.
     * + The returned response from POST /api/users with form data username will be an object with
     *      + username
     *      + _id
     *
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        if(username == null){
            response.sendError(400, "username is invalid"); // Send not implemented error
        }

        UserId userId = new UserId(username);
        DataStoreUserIDUtils.saveUserId(dataStore, USER_IDS_KEY, userId);
        String json = GSON.toJson(userId);
        response.setContentType("application/json");
        Writer writer = response.getWriter();
        writer.write(json);
        response.flushBuffer();
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        if(username == null){
            response.sendError(400, "username is invalid");
        }

        DataStoreUserIDUtils.removeUserIdByUsername(dataStore, USER_IDS_KEY, username);
        String json = dataStore.load(USER_IDS_KEY);

        response.setContentType("application/json");
        response.getWriter().write(json);
        response.flushBuffer();
    }


}