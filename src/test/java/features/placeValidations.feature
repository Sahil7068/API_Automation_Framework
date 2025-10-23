Feature: Validation Place Apis

@addPlace @Regression
  Scenario Outline: Verify if Place is being successfully added
    Given Add Place Payload with "<name>" and "<phone_number>" and "<address>"
    When User Call "addPlaceAPI" with "POST" http request
    Then Api Call is successful with status code 200
    And "status" in Response Body is "OK"
    And Verify Place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:
    | name | phone_number | address |
    | Framework | 7807807809 | dumka |
    #| build | 9876543210 | patna |
   # | house | 9876543210 | godda |


  @deletePlace @Regression
  Scenario:  Verify if Delete functionality is Working
    Given Delete Place Payload
    When User Call "deletePlaceAPI" with "POST" http request
    Then Api Call is successful with status code 200
    And "status" in Response Body is "OK"
