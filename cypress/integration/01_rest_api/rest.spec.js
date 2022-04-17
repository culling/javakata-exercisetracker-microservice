/// <reference types="cypress" />

describe("canary", () => {
    it("should always be true", () => {
        expect(true).to.equal(true);
    });
});

describe("rest api - GET", ()=>{
    it("should not fail with GET page", ()=>{
        cy.visit("/plugins/servlet/exercisetracker/api/users");

    });
});

describe("rest api - DELETE", ()=>{
    it("should not fail with DELETE User API", ()=>{
        cy.request({
            method: "DELETE",
            url:"/plugins/servlet/exercisetracker/api/users"
        });

    });
});
