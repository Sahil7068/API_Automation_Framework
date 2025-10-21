Feature: Validation Place Apis

  Scenario Outline: Verify if Place is being successfully added
    Given Add Place Payload with "<name>" and "<phone_number>" and "<address>"
    When User Call "AddPlaceApi"
    Then Api Call is successful with status code 200
    And "status" in Response Body is "OK"

    Examples:
    | name | phone_number | address |
    | Frontline house | 9876543210 | dumka |