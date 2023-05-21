Cypress.Commands.addAll({
    login(username, password) {
        cy.visit('https://www.saucedemo.com')
        cy.intercept('/service-worker.js', {
            body: undefined
        })

        cy.get('[data-test="username"]').type(username)
        cy.get('[data-test="password"]').type(password)
        cy.get('[data-test="login-button"]').click()
    },
    addTwoItems() {
        cy.get('[data-test="add-to-cart-sauce-labs-bike-light"]').click()
        cy.get('[data-test="add-to-cart-sauce-labs-backpack"]').click()
    }
})