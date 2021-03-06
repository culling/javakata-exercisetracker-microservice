/// <reference types="cypress" />


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
        cy.getNewUserId(username).then(id => {

            cy.visit("/plugins/servlet/exercisetracker");
            cy.get('#uid').type(id);
            cy.get('#desc').type("description");
            cy.get('#dur').type("120");
            cy.get('#date').type("2022 02 07");
            cy.get('#exercise-form > [type="submit"]').click();
        });
    });
});
