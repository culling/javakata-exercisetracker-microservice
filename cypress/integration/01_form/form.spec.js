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
});
