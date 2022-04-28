/// <reference types="cypress" />

describe("canary", () => {
    it("should always be true", () => {
        expect(true).to.equal(true);
    });
});

describe("rest api - GET", ()=>{
    it("should not fail with GET page", ()=>{
        cy.request("/plugins/servlet/exercisetracker/api/users");
    });
});


describe("rest api - DELETE", ()=>{
    it.skip("NOT IMPLEMENTED - should not fail with DELETE User API", ()=>{
        cy.request({
            method: "DELETE",
            url:"/plugins/servlet/exercisetracker/api/users?user=key"
        });

    });
});
