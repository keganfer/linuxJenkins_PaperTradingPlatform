
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
       stage('Dock push') {
            steps {
                sh 'docker build -t linux_papertradingplatform .'
			            	sh 'docker tag linux_papertradingplatform:latest keganferreira/linux_papertradingplatform:latest'
				            withDockerRegistry([ credentialsId: 'DockerCreds', url: '' ]) {
			            	sh 'docker push keganferreira/linux_papertradingplatform'
				          }

            }
        }
        stage('Deploy') {
            steps {
              echo 'Deploying....'
              sshagent(credentials: ['ec2-linuxAMI']) {
              sh 'ls'
	      sh 'docker pull keganferreira/linux_papertradingplatform'
	      sh 'docker run keganferreira/linux_papertradingplatform'	      
              
             }
            }
        }
    }
}
