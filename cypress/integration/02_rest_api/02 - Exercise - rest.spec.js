/// <reference types="cypress" />

const clearUsers = () => {
    cy.request({
        method: "GET",
        url: "/plugins/servlet/exercisetracker/api/users"
    }).then(response => {
        const users = response.body;
        for (const user of users) {
            cy.request({
                method: "DELETE",
                form: true,
                url: `/plugins/servlet/exercisetracker/api/users?username=${user.username}`
            });
        }
    });
}

const getNewUserId = (username) => {
    const id = cy.request({
        method: "POST",
        url: "/plugins/servlet/exercisetracker/api/users",
        form: true,
        body: {
            "username": username
        }
    }).then(response => {
        const users = response.body;
        const user = users.filter(user => user.username == username)[0];
        const id = user._id;
        return id;
    });
    return id
}

describe("canary", () => {
    it("should always be true", () => {
        expect(true).to.equal(true);
    });
});

describe("Unimplemented Use Cases", () => {
    it(`A request to a user's log GET /api/users/:_id/logs returns a user object with a count property representing the + number of exercises that belong to that user.`);
    it(`A GET request to /api/users/:id/logs will return the user object with a log array of all the exercises added.`);
    it(`Each item in the log array that is returned from GET /api/users/:id/logs is an object that should have a description, + duration, and date properties.`);
    it(`The description property of any object in the log array that is returned from GET /api/users/:id/logs should be a + string.`);
    it(`The duration property of any object in the log array that is returned from GET /api/users/:id/logs should be a number.`);
    it(`The date property of any object in the log array that is returned from GET /api/users/:id/logs should be a string.`);
});

describe("/api/users/:_id/logs GET", () => {
    before("clear all users", () => {
        clearUsers();
    });
    it(`User Story - You can make a GET request to /api/users/:_id/logs to retrieve a full exercise log of any user.`, () => { });
    it(`Should return an empty array for an existing user with no exercise logged`, () => {
        const username = "myUserOne";
        getNewUserId(username).then(id => {
            cy.request(`/plugins/servlet/exercisetracker/api/users/${id}/logs`).then(response => {
                const userLog = response.body;
                expect(Array.isArray(userLog)).to.be.true;
                expect(userLog.length).to.equal(0);
            });
        })
    });

    it(`Should return an array with single exercise for an existing user with one exercise logged`, () => {
        const username = "myUserOne";
        getNewUserId(username).then(id => {
            cy.request({
                url: `/plugins/servlet/exercisetracker/api/users/${id}/exercises`,
                method: "POST",
                form: true,
                body: {
                    ":_id": id,
                    "description": "coding",
                    "duration": "3 hr",
                    "date": ""
                }
            });
            cy.request(`/plugins/servlet/exercisetracker/api/users/${id}/logs`).then(response => {
                const exerciseLog = response.body;
                expect(Array.isArray(exerciseLog), "exercise log is array").to.be.true;

                const filteredLog = exerciseLog.filter(exercise => exercise.id == id);
                expect(exerciseLog.length, "exercises returned").to.equal(1);
            });
        })
    });


})