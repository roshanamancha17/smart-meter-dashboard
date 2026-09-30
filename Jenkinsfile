pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'feature/meter-entry', url: 'https://github.com/roshanamancha17/smart-meter-dashboard.git'
            }
        }
        stage('Build & Test') {
            steps {
                sh 'mvn clean test package'
            }
        }
        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
        success {
            echo 'Quality Gate Passed: All Selenium and Unit tests succeeded!'
        }
        failure {
            echo 'Quality Gate Failed: Tests failed, stopping pipeline.'
        }
    }
}