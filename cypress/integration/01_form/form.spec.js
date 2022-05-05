/// <reference types="cypress" />

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

describe("form", () => {
    it("should create a user when submitted", () => {
        cy.visit("/plugins/servlet/exercisetracker");
        cy.get('#uname').type("testuser");
        cy.get('#createUser').click();
    });

    it("should create an exercise log when submitted", () => {
        const username = "post-form-username";
        getNewUserId(username).then(id => {

            cy.visit("/plugins/servlet/exercisetracker");
            cy.get('#uid').type(id);
            cy.get('#desc').type("description");
            cy.get('#dur').type("1 minute");
            cy.get('#date').type("1/1/2022");
            cy.get('#exercise-form > [type="submit"]').click();
        });
    });
});
