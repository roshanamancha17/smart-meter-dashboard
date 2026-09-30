pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'feature/meter-entry', url: 'https://github.com/roshanamancha17/smart-meter-dashboard.git'
            }
        }
        stage('Build & Unit Test') {
            steps {
                bat 'mvn clean package -DskipTests=false'
            }
        }
        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed during execution.'
        }
    }
}