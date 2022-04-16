/// <reference types="cypress" />

describe("canary", () => {
    it("should always be true", () => {
        expect(true).to.equal(true);
    });
});

describe("rest api", ()=>{
    it("should not fail with User API", ()=>{
        cy.visit("/plugins/servlet/exercisetracker/api/users");

    });
})