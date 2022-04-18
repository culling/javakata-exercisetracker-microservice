package com.geneculling.javakata.servlet;

import com.atlassian.confluence.setup.settings.SettingsManager;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.geneculling.javakata.api.DataStore;
import com.geneculling.javakata.impl.MemoryDataStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ExerciseTrackerServlet extends HttpServlet {
    private final SettingsManager settingsManager;
    TemplateRenderer renderer;
    DataStore dataStore = new MemoryDataStore(new HashMap<String, String>(){{
        put("key", "value");
    }});

    ExerciseTrackerServlet(
            @ComponentImport TemplateRenderer renderer,
            @ComponentImport SettingsManager settingsManager){
        this.renderer = renderer;
        this.settingsManager = settingsManager;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        Map<String, Object> map = new HashMap<>();
        String contextPath = new URL(settingsManager.getGlobalSettings().getBaseUrl()).getPath();
        map.put("contextPath", contextPath);
        renderer.render("templates/exercisetracker.vm", map, response.getWriter());
        response.flushBuffer();
    }

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
