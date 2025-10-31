package io.loop.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/html-reports/cucumber-report.html",
                "json:target/json-reports/json-report.json",
                "junit:target/junit/junit-report.xml",
                "rerun:target/rerun.txt",
        },
        features = {
                "src/test/resources/features_ui",
                "src/test/resources/features_api",
                "src/test/resources/features_db"
        },
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


/*
1. Exclude a single tag:
tags = "@ui and not @ignore"

2. Exclude multiple tags
tags = "@ui and not (@bug or @wip or @db)"

3.Exclude multiple tags
tags = "(@ui or @api) and not @db"

 */