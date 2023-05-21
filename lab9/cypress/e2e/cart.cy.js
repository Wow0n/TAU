describe('Cart tests', () => {
    beforeEach(() => {
        cy.login("standard_user", "secret_sauce")
    })

    afterEach(() => {
        cy.clearAllLocalStorage()
    })

    it('Check number of products on the same site', () => {
        cy.addTwoItems()

        cy.get('.shopping_cart_badge').should('contain.text', '2')
    })

    it('Check number of products after refresh', () => {
        cy.addTwoItems()
        cy.reload()

        cy.get('.shopping_cart_badge').should('contain.text', '2')
    })

    it('Check number of products on different sites', () => {
        cy.addTwoItems()

        cy.get('#item_0_img_link > .inventory_item_img').click()
        cy.get('.shopping_cart_badge').should('contain.text', '2')

        cy.get('[data-test="back-to-products"]').click()
        cy.get('.shopping_cart_badge').should('contain.text', '2')

        cy.get('#item_5_img_link > .inventory_item_img').click()
        cy.get('.shopping_cart_badge').should('contain.text', '2')
    })

    it('Check number of products after add new', () => {
        cy.addTwoItems()
        cy.get('.shopping_cart_badge').should('contain.text', '2')

        cy.get('#item_5_img_link > .inventory_item_img').click()
        cy.get('[data-test="add-to-cart-sauce-labs-fleece-jacket"]').click()
        cy.get('.shopping_cart_badge').should('contain.text', '3')
    })
})