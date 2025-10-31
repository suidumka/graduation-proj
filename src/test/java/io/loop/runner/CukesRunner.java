package io.loop.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "io.loop",
        plugin = {
                "pretty",
                "html:target/html-reports/cucumber-html-reports.html",
                "json:target/json-reports/cucumber.json",
                "rerun:target/rerun.txt",
                // "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        publish = false
)
public class CukesRunner {
}

/*
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/html-reports/cucumber-report.html",
                "json:target/json-reports/cucumber.json",
                "rerun:target/rerun.txt"
        },
        features =  "src/test/resources/features",
        glue = {
                "io/loop/step_definitions_ui",
                "io/loop/step_definitions_api",
                "io/loop/step_definitions_db"
        },
        dryRun = false,
        tags = "@getClient",
        monochrome = true

)

public class CukesRunner {
}


*/
/*
1. Exclude a single tag:
tags = "@ui and not @ignore"

2. Exclude multiple tags
tags = "@ui and not (@bug or @wip or @db)"

3.Exclude multiple tags
tags = "(@ui or @api) and not @db"

 */
