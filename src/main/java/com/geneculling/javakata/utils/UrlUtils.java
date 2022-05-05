package com.geneculling.javakata.utils;

public class UrlUtils {
    public static String getPathSection(String pathInfo, int section) {
        String[] parts = pathInfo.split("/");
        if(section < 0){
            return parts[parts.length + section];
        }
        return parts[section];
    }
}
