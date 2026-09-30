pipeline {
    agent any
    environment {
        IMAGE_NAME = 'smart-meter-dashboard'
        TAG = "${env.BUILD_NUMBER}"
    }
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
        stage('Docker Build') {
            steps {
                sh "docker build -t ${IMAGE_NAME}:${TAG} -t ${IMAGE_NAME}:latest ."
            }
        }
        stage('Deploy Container') {
            steps {
                sh 'docker stop smart-meter-prod || true'
                sh 'docker rm smart-meter-prod || true'
                sh "docker run -d --name smart-meter-prod -p 8082:8080 ${IMAGE_NAME}:${TAG}"
            }
        }
    }
    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
        success {
            echo "Deployment Successful! Application is live at http://localhost:8082"
        }
        failure {
            echo "Pipeline Failed during Build, Testing, or Container Deployment."
        }
    }
}