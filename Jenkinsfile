pipeline {
    agent any

    triggers {
        githubPush()
    }

    environment {
        APP_NAME = "ritik7806/fms-backend"
        IMAGE_NAME = "${APP_NAME}:latest"

    }

    stages {

        stage('Clone Repository') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Ritik-7806/Finance-Management-System.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh """
                docker build -t ${IMAGE_NAME} .
                """
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {
                        sh """
                        echo \$PASS | docker login -u \$USER --password-stdin
                        """
                    }
            }
        }

        stage('Push Image on DockerHub') {
            steps {
                sh """
                docker push ${IMAGE_NAME}
                """
            }
        }

        stage('Restart Deployment') {
            steps {
                withCredentials([file(credentialsId: 'kind-kubeconfig', variable: 'KUBECONFIG')]) {
                sh 'kubectl rollout restart deployment fms-backend -n fms'
                }
            }
        }


    }
}