package com.geneculling.javakata.servlet;

import com.atlassian.confluence.setup.settings.SettingsManager;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.geneculling.javakata.api.DataStore;
import com.geneculling.javakata.impl.DataStoreExerciseUtils;
import com.geneculling.javakata.impl.DataStoreFactory;
import com.geneculling.javakata.impl.DataStoreUserIDUtils;
import com.geneculling.javakata.pojo.Exercise;
import com.geneculling.javakata.pojo.Log;
import com.geneculling.javakata.pojo.UserId;
import com.geneculling.javakata.utils.ExerciseValidator;
import com.geneculling.javakata.utils.UrlUtils;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class ExerciseServlet extends HttpServlet {

    private final static Gson GSON = new Gson();

    private static DataStore dataStore;

    private final static String EXERCISE_KEY = "exercises";
    private final static String USER_IDS_KEY = "userIds";

    ExerciseServlet(
            @ComponentImport PluginSettingsFactory pluginSettingsFactory) {
        this.dataStore = new DataStoreFactory(pluginSettingsFactory).getDataStore();
    }

    /**
     * + You can POST to /api/users/:_id/exercises with form data description, duration, and optionally date. If no date is supplied, the current date will be used.
     * + The response returned from POST /api/users/:_id/exercises will be the user object with the exercise fields added.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter(":_id");
        String description = request.getParameter("description");
        String duration = request.getParameter("duration");
        String date = request.getParameter("date");

        if (invalidParameter(response, id, description, duration, date)){
            return;
        }

        UserId userId = DataStoreUserIDUtils.getUserIdById(dataStore, USER_IDS_KEY, id);
        if(userId == null){
            response.sendError(400, "User with id: \"" + id + "\" not found");
            return;
        }

        String username = userId.getUsername();
        Exercise exercise = new Exercise(id, username, description, duration, date);
        DataStoreExerciseUtils.saveExercise(dataStore, EXERCISE_KEY, exercise);

        String json = GSON.toJson(exercise);

        response.setContentType("application/json");
        Writer writer = response.getWriter();
        writer.write(json);
        response.flushBuffer();
    }

    private boolean invalidParameter(HttpServletResponse response, String id, String description, String duration, String date) throws IOException {
        if(StringUtils.isBlank(id)){
            response.sendError(400, "id is invalid");
            return true;
        }

        if(StringUtils.isBlank(description)){
            response.sendError(400, "description is invalid");
            return true;
        }

        if(! ExerciseValidator.isValidDuration(duration)){
            response.sendError(400, "duration is invalid");
            return true;
        }

        if(! ExerciseValidator.isValidDate(date)) {
            response.sendError(400, "date is invalid");
            return true;
        }
        return false;
    }

}
