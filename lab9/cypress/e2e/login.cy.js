describe('Login tests', () => {
  beforeEach(() => {
    cy.visit('https://www.saucedemo.com')
    cy.intercept('/service-worker.js', {
      body: undefined
    })
  })

  it('Correct login', () => {
    cy.get('[data-test="username"]').type('standard_user')
    cy.get('[data-test="password"]').type('secret_sauce')
    cy.get('[data-test="login-button"]').click()

    cy.get('.title').should('contain.text', 'Products')
  })

  it('Correct username wrong password', () => {
    cy.get('[data-test="username"]').type('standard_user')
    cy.get('[data-test="password"]').type('password')
    cy.get('[data-test="login-button"]').click()

    cy.get('[data-test="error"]').should('contain.text', 'Epic sadface: Username and password do not match any user in this service')
  })

  it('Correct password wrong username', () => {
    cy.get('[data-test="username"]').type('user')
    cy.get('[data-test="password"]').type('secret_sauce')
    cy.get('[data-test="login-button"]').click()

    cy.get('[data-test="error"]').should('contain.text', 'Epic sadface: Username and password do not match any user in this service')
  })

  it('Swapped username and password', () => {
    cy.get('[data-test="username"]').type('secret_sauce')
    cy.get('[data-test="password"]').type('standard_user')
    cy.get('[data-test="login-button"]').click()

    cy.get('[data-test="error"]').should('contain.text', 'Epic sadface: Username and password do not match any user in this service')
  })

  it('Random username and password', () => {
    cy.get('[data-test="username"]').type('aaaa')
    cy.get('[data-test="password"]').type('bbbb')
    cy.get('[data-test="login-button"]').click()

    cy.get('[data-test="error"]').should('contain.text', 'Epic sadface: Username and password do not match any user in this service')
  })

  it('Empty username', () => {
    cy.get('[data-test="password"]').type('password')
    cy.get('[data-test="login-button"]').click()

    cy.get('[data-test="error"]').should('contain.text', 'Epic sadface: Username is required')
  })

  it('Empty password', () => {
    cy.get('[data-test="username"]').type('standard_user')
    cy.get('[data-test="login-button"]').click()

    cy.get('[data-test="error"]').should('contain.text', 'Epic sadface: Password is required')
  })

  it('Empty username and password', () => {
    cy.get('[data-test="login-button"]').click()

    cy.get('[data-test="error"]').should('contain.text', 'Epic sadface: Username is required')
  })
})