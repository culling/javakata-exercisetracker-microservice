package com.geneculling.javakata.servlet;

import com.atlassian.confluence.setup.settings.SettingsManager;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.geneculling.javakata.api.DataStore;
import com.geneculling.javakata.impl.DataStoreExerciseUtils;
import com.geneculling.javakata.impl.DataStoreFactory;
import com.geneculling.javakata.impl.DataStoreUserIDUtils;
import com.geneculling.javakata.impl.MemoryDataStore;
import com.geneculling.javakata.pojo.Exercise;
import com.geneculling.javakata.pojo.Log;
import com.geneculling.javakata.pojo.UserId;
import com.geneculling.javakata.utils.UrlUtils;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;

public class ExerciseServlet extends HttpServlet {

    private final TemplateRenderer renderer;
    private final SettingsManager settingsManager;
    private final static Gson GSON = new Gson();

    private final static DataStore dataStore = new DataStoreFactory(
            (DataStore) new MemoryDataStore(new HashMap<String, String>() {{
        put("key", "value");
    }})).getDataStore();

    private final static String EXERCISE_KEY = "exercises";
    private final static String USER_IDS_KEY = "userIds";

    ExerciseServlet(
            @ComponentImport TemplateRenderer renderer,
            @ComponentImport SettingsManager settingsManager) {
        this.renderer = renderer;
        this.settingsManager = settingsManager;
    }

    /**
     * + You can make a GET request to /api/users/:_id/logs to retrieve a full exercise log of any user.
     * + A request to a user's log GET /api/users/:_id/logs returns a user object with a count property representing the + number of exercises that belong to that user.
     * + A GET request to /api/users/:id/logs will return the user object with a log array of all the exercises added.
     * + Each item in the log array that is returned from GET /api/users/:id/logs is an object that should have a description, + duration, and date properties.
     * + The description property of any object in the log array that is returned from GET /api/users/:id/logs should be a + string.
     * + The duration property of any object in the log array that is returned from GET /api/users/:id/logs should be a number.
     * + The date property of any object in the log array that is returned from GET /api/users/:id/logs should be a string.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        String id = UrlUtils.getPathSection(pathInfo, -2);

        List<Exercise> exercises = DataStoreExerciseUtils.getExercisesById(dataStore, EXERCISE_KEY, id);
        UserId userId = DataStoreUserIDUtils.getUserIdById(dataStore, USER_IDS_KEY, id);
        if(userId == null){
            response.sendError(400, "User with id: " + id + " does not exist");
            return;
        }
        Log log = new Log(userId, exercises);
        String json = GSON.toJson(log);

        response.setContentType("application/json");
        response.getWriter().write(json);
        response.flushBuffer();
    }

    /**
     * + You can POST to /api/users/:_id/exercises with form data description, duration, and optionally date. If no date is supplied, the current date will be used.
     * + The response returned from POST /api/users/:_id/exercises will be the user object with the exercise fields added.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter(":_id");
        if(id == null){
            response.sendError(400, "id is invalid");
        }

        String description = request.getParameter("description");
        if(description == null){
            response.sendError(400, "description is invalid");
        }

        String duration = request.getParameter("duration");
        if(duration == null){
            response.sendError(400, "duration is invalid");
        }

//        TODO: Check Date Parameter and use Date parameter for logs
        String date = request.getParameter("date");
        UserId userId = DataStoreUserIDUtils.getUserIdById(dataStore, USER_IDS_KEY, id);
        if(userId == null){
            response.sendError(400, "User with id: \"" + id + "\" not found");
            return;
        }
        String username = userId.getUsername();
        Exercise exercise = new Exercise(id, username, description, duration);
        DataStoreExerciseUtils.saveExercise(dataStore, EXERCISE_KEY, exercise);

        String json = dataStore.load(EXERCISE_KEY);

        response.setContentType("application/json");
        Writer writer = response.getWriter();
        writer.write(json);
        response.flushBuffer();
    }

}
