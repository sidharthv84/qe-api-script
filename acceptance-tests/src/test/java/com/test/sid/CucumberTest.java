package com.test.sid;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = {"com.test.sid.steps"},
        tags = {"@integrationTest"}
)
public class CucumberTest {

}