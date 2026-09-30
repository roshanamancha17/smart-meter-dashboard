pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'feature/meter-entry', url: 'https://github.com/roshanamancha17/smart-meter-dashboard.git'
            }
        }
        stage('Build & Package') {
            steps {
                sh 'mvn clean package -DskipTests'
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
            echo 'Build and packaging completed successfully!'
        }
        failure {
            echo 'Pipeline encountered an error.'
        }
    }
}