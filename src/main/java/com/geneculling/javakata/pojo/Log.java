package com.geneculling.javakata.pojo;

import java.util.List;

/**
 * JSON for app
 * {
 *   username: "fcc_test",
 *   count: 1,
 *   _id: "5fb5853f734231456ccb3b05",
 *   log: [{
 *     description: "test",
 *     duration: 60,
 *     date: "Mon Jan 01 1990",
 *   }]
 * }
 */
public class Log {
    private String username;
    private long count;
    private String _id;
    private List<Exercise> exerciseList;
    public Log(UserId userId, List<Exercise> exerciseList){
        this.username = userId.getUsername();
        this._id = userId.get_id();
        this.count = exerciseList.size();
        this.exerciseList = exerciseList;
    }
}
