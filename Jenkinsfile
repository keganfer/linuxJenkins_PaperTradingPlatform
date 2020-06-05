
 pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                bat "mvn clean compile"  
            }
        }
        stage('Test') {
            steps {
                bat "mvn clean test"
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
             withCredentials([string(credentialsId: 'ec2-linuxAMI')]) {
              sh 'ls'
              
             }
            }
        }
    }
}
