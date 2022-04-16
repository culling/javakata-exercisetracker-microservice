package com.geneculling.javakata.servlet;

import com.atlassian.templaterenderer.TemplateRenderer;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExerciseTrackerServlet extends HttpServlet {
    TemplateRenderer renderer;

    ExerciseTrackerServlet(@ComponentImport TemplateRenderer renderer){
        this.renderer = renderer;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        Map<String, Object> map = new HashMap<>();

        renderer.render("templates/exercisetracker.vm", map, response.getWriter());
        response.flushBuffer();
    }

}
