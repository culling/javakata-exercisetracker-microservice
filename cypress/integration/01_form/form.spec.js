/// <reference types="cypress" />

describe("canary", () => {
    it("should always be true", () => {
        expect(true).to.equal(true);
    });
});

describe("form", ()=>{
    it("should create a user when submitted", ()=>{
        cy.visit("/plugins/servlet/exercisetracker");
        cy.get('#uname').type("testuser");
        cy.get('#createUser').click();
    });

    it("NOT IMPLEMENTED - should create an exercise log when submitted", ()=>{
        cy.visit("/plugins/servlet/exercisetracker");
        cy.get('#uid').type("uid");
        cy.get('#desc').type("description");
        cy.get('#dur').type("1 minute");
        cy.get('#date').type("1/1/2022");
        cy.get('#exercise-form > [type="submit"]').click();
    });
});
