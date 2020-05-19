Feature: Framework Setup

  @integrationTest
Scenario Outline: Validating the Response body and http status for rest call
Given API is up and running
When I submit a request to "<URL>"
Then On Successful completion of the request, Validate the "<http_status>"
And  Validate the response body
Examples:
|URL         |http_status|
|/test/sid3   |200        |