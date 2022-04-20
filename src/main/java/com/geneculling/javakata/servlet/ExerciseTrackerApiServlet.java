package com.geneculling.javakata.servlet;

import com.atlassian.confluence.setup.settings.SettingsManager;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.geneculling.javakata.api.DataStore;
import com.geneculling.javakata.impl.MemoryDataStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ExerciseTrackerApiServlet extends HttpServlet {

    private final TemplateRenderer renderer;
    private final SettingsManager settingsManager;
    DataStore dataStore = new MemoryDataStore(new HashMap<String, String>(){{
        put("key", "value");
    }});


    ExerciseTrackerApiServlet(
            @ComponentImport TemplateRenderer renderer,
            @ComponentImport SettingsManager settingsManager){
        this.renderer = renderer;
        this.settingsManager = settingsManager;
    }

    /**
     * Get Method
     *
     * User Stories
     * + You can make a GET request to /api/users to get a list of all users.
     * + The GET request to /api/users returns an array.
     * + Each element in the array returned from GET /api/users is an object literal containing a user's username and _id.
     *
     *
     * + You can make a GET request to /api/users/:_id/logs to retrieve a full exercise log of any user.
     * + A request to a user's log GET /api/users/:_id/logs returns a user object with a count property representing the + number of exercises that belong to that user.
     * + A GET request to /api/users/:id/logs will return the user object with a log array of all the exercises added.
     * + Each item in the log array that is returned from GET /api/users/:id/logs is an object that should have a description, + duration, and date properties.
     * + The description property of any object in the log array that is returned from GET /api/users/:id/logs should be a + string.
     * + The duration property of any object in the log array that is returned from GET /api/users/:id/logs should be a number.
     * + The date property of any object in the log array that is returned from GET /api/users/:id/logs should be a string.
     *
     * + Use the dateString format of the Date API.
     * + You can add from, to and limit parameters to a GET /api/users/:_id/logs request to retrieve part of the log of any user. from and to are dates in yyyy-mm-dd format. limit is an integer of how many logs to send back.
     *
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().write("" +
                "     * User Stories\n" +
                "     * + You can make a GET request to /api/users to get a list of all users.\n" +
                "     * + The GET request to /api/users returns an array.\n" +
                "     * + Each element in the array returned from GET /api/users is an object literal containing a user's username and _id.\n");
        response.flushBuffer();
    }


    /**
     * Post method
     *
     * User Stories
     * + You can POST to /api/users with form data username to create a new user.
     * + The returned response from POST /api/users with form data username will be an object with username and _id properties.
     *
     * + You can POST to /api/users/:_id/exercises with form data description, duration, and optionally date. If no date is + supplied, the current date will be used.
     * + The response returned from POST /api/users/:_id/exercises will be the user object with the exercise fields added.
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String value = dataStore.load(username);
        dataStore.save(username, value);

        response.setContentType("application/json");
        response.getWriter().write("{\"post\":\"hit\"}");
        response.flushBuffer();
    }


    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String key = request.getParameter("user");
        dataStore.remove(key);

        response.setContentType("application/json");
        response.getWriter().write("{\"delete\":\"hit\"}");
        response.flushBuffer();
    }




}