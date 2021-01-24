@US_26
Feature: US_26 System should allow to update countries using api end point
  "https://www.gmibank.com/api/tp-countries"

  @TC_2601
  Scenario: TC_2601 User can just update each country 1 by 1
    Given Use api end point  "https://www.gmibank.com/api/tp-countries/"
    And   The status code must be 200
    And   The response format must be JSON "application/json"
    And   User finds out the size of the country list
    And   User send a Put request endpoint "https://www.gmibank.com/api/tp-countries/" as "Pays-Bas" for upddate Cekya
    Then  User verify the contry was updated