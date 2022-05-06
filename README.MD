# Exercise Tracker
Build a micro service that is functionally similar to this: https://exercise-tracker.freecodecamp.rocks/

Your responses should have the following structures.
## Exercise
{
  username: "fcc_test",
  description: "test",
  duration: 60,
  date: "Mon Jan 01 1990",
  _id: "5fb5853f734231456ccb3b05"
}

## User
{
  username: "fcc_test",
  _id: "5fb5853f734231456ccb3b05"
}

## Log

{
  username: "fcc_test",
  count: 1,
  _id: "5fb5853f734231456ccb3b05",
  log: [{
    description: "test",
    duration: 60,
    date: "Mon Jan 01 1990",
  }]
}



Hint: For the date property, the toDateString method of the Date API can be used to achieve the expected output.



# Kata Requirements

## /api/users 
### @POST
+ POST to /api/users with form data username, creates a new user.
+ Response from POST to /api/users with form data username will be an object with username and _id properties. e.g {"username": "tom", "_id": "mylonguniqueid"}

###  @GET
+ Returns all users 
  + in an array 
  + as _id username pairs
  + e.g. [{"username": "tom", "_id": "uniqueid_1"}, {"username": "scott", "_id": "uniqueid_2"}]

## /api/users/:_id/exercises
### @POST
+ POST to /api/users/:_id/exercises with form data description, duration, and optionally date
+ If no date is supplied, the current date will be used
+ Returns user object with the exercise fields added

## /api/users/:_id/logs
### @GET
+ Returns a full exercise log of user with id == _id
+ Returns a user object with
  + a count property representing the number of exercises that belong to that user
  + a log array of each of the exercises added (for the user with id matching id) which should have
    + a description (string)
    + duration (number)
    + date (date)
      + formatted "<shortDay> <shortMonth> <day> <year>" 
      + e.g. Sun Feb 06 2022
#### Additional Parameters and Filtering
+ You can add from, to and limit parameters to a GET /api/users/:_id/logs request to specify date range to return. 
+ From and to are dates in yyyy-mm-dd format. 
+ limit (integer)
  + Maximum logs to send back
  
GET user's exercise log: GET /api/users/:_id/logs?[from][&to][&limit]
[ ] = optional
from, to = dates (yyyy-mm-dd); limit = number
