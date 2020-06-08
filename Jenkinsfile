
 pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean compile'  
            }
        }
        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Deploy') {
            steps {
              echo 'Deploying....'
              sshagent(credentials: ['ec2-linuxAMI']) {
              sh 'ls'
              
             }
            }
        }
    }
}
