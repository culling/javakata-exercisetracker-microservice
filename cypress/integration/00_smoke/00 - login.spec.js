/// <reference types="cypress" />

describe("canary", () => {
    it("should always be true", () => {
        expect(true).to.equal(true);
    });
});

describe("visit form", () => {
    it("should be able to visit the form", () => {
        cy.visit("/plugins/servlet/exercisetracker");
    });
});
