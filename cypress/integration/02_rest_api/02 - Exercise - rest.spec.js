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

describe("canary", () => {
    it("should always be true", () => {
        expect(true).to.equal(true);
    });
});

describe("/api/users/:_id/logs GET", () => {
    before("clear all users", () => {
        clearUsers();
    });
    it(`User Story - You can make a GET request to /api/users/:_id/logs to retrieve a full exercise log of any user.`, () => { });
    it(`Should return an empty array for an existing user with no exercise logged`, () => {
        const username = "myUserOne";
        cy.getNewUserId(username).then(id => {
            cy.request(`/plugins/servlet/exercisetracker/api/users/${id}/logs`).then(response => {
                const log = response.body;
                expect(log.username, "username").to.equal(username);
                expect(log._id, "user id").to.equal(id);
                expect(log.count, "exercise count").to.equal(0);
                expect(Array.isArray(log.exercises)).to.be.true;
                expect(log.exercises.length).to.equal(0);
            });
        })
    });

    it(`Should return an array with single exercise for an existing user with one exercise logged`, () => {
        const username = "myUserOne";
        cy.getNewUserId(username).then(id => {
            cy.request({
                url: `/plugins/servlet/exercisetracker/api/users/${id}/exercises`,
                method: "POST",
                form: true,
                body: {
                    ":_id": id,
                    "description": "coding",
                    "duration": "3",
                    "date": ""
                }
            });
            cy.request(`/plugins/servlet/exercisetracker/api/users/${id}/logs`).then(response => {
                const log = response.body;
                expect(log.username, "username").to.equal(username);
                expect(log._id, "user id").to.equal(id);
                expect(log.count, "exercise count").to.equal(1);
                expect(Array.isArray(log.exercises)).to.be.true;
                expect(log.exercises.length, "exercises returned").to.equal(1);
                const exercise = log.exercises[0];
                expect( typeof (exercise.duration)).to.equal("number");
                expect( typeof (exercise.description)).to.equal("string");
                expect( typeof (exercise.date)).to.equal("string");
            });
        })
    });
})