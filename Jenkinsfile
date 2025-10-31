pipeline {
  agent any

  options {
    timestamps()
    ansiColor('xterm')
  }

  triggers {
    // Enables GitHub webhook builds when configured (see step 4)
    pollSCM('') // optional: disable polling if only using webhooks
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build & Test') {
      steps {
        // Linux agents
        sh 'chmod +x mvnw || true'
        sh './mvnw -B clean test'
        // If you don’t have mvnw, use:
        // sh 'mvn -B clean test'
        // On Windows agent, replace with:
        // bat 'mvn -B clean test'
      }
    }

    stage('Publish Reports') {
      steps {
        // Surefire (JUnit/TestNG XML) results
        junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml, target/testng-results.xml'

        // Cucumber JSON (if you generate it via plugin)
        // publishCucumberReports fileIncludePattern: 'target/cucumber*.json', jsonReportDirectory: 'target'
      }
    }

    stage('Archive Artifacts') {
      steps {
        archiveArtifacts artifacts: 'target/**', fingerprint: true
      }
    }
  }

  post {
    always {
      echo 'Build finished.'
    }
    failure {
      echo 'Build failed.'
    }
  }
}
