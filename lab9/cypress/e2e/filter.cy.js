describe('Filter tests', () => {
    beforeEach(() => {
        cy.login("standard_user", "secret_sauce")
    })

    it('Filter by Name (A to Z)', () => {
        cy.get('.product_sort_container').select("az")
        cy.xpath("//div[@id='inventory_container']/div/div/div[2]/div/a/div").should('contain.text', 'Sauce Labs Backpack')
    })

    it('Filter by Name (Z to A)', () => {
        cy.get('.product_sort_container').select("za")
        cy.xpath("//div[@id='inventory_container']/div/div/div[2]/div/a/div").should('contain.text', 'Test.allTheThings() T-Shirt (Red)')
    })

    it('Filter by Price (low to high)', () => {
        cy.get('.product_sort_container').select("lohi")
        cy.xpath("//div[@id='inventory_container']/div/div/div[2]/div/a/div").should('contain.text', 'Sauce Labs Onesie')
    })

    it('Filter by Price (high to low)', () => {
        cy.get('.product_sort_container').select("hilo")
        cy.xpath("//div[@id='inventory_container']/div/div/div[2]/div/a/div").should('contain.text', 'Sauce Labs Fleece Jacket')
    })
})