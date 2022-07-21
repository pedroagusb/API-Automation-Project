Feature: Use of Swagger Petstore

  @TC-0001 @Pass
  Scenario: 1 - User wants to post a new Pet
    When user wants to send a post using the request from 'addPet.json'
    Then Status code is 200
    And The response matches with the JSON Schema 'postSchema.json'


  @TC-0002 @NotPass
  Scenario: 2 - User want to look for a pet
    When User wants to get the pet with id: 100
    Then Status code is 200
    And The response matches with the JSON Schema 'getSchema.json'

  @TC-0003 @Pass
  Scenario: 3 - User want to change a pet
    When User wants to change the pet using the request from 'putPet.json'
    Then Status code is 200
    And The response matches with the JSON Schema 'putSchema.json'

    
