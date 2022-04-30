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

describe("rest api - POST", ()=>{
    it("should not fail with POST page", ()=>{
        cy.request({
            method: "POST",
            url: "/plugins/servlet/exercisetracker/api/users",
            form: true,
            body: {
                "username": "posttest"
            }
        });
    });
});

describe("rest api - DELETE", ()=>{
    it("should not fail with DELETE User API", ()=>{
        cy.request({
            method: "POST",
            url: "/plugins/servlet/exercisetracker/api/users",
            form: true,
            body: {
                "username": "userToBeDeleted"
            }
        });

        cy.request({
            method: "DELETE",
            form: true,
            url:"/plugins/servlet/exercisetracker/api/users?username=userToBeDeleted"
        });

    });
});
