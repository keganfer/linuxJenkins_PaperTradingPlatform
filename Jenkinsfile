
 pipeline {
    agent any
    environment {
    SPAC = ' '
}
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
              sshagent(credentials: ['ec2-linuxAMI-v3']) {
              sh 'ls'
	      sh 'docker pull keganferreira/linux_papertradingplatform'
		  sh 'if [ "$(docker ps --filter ancestor="keganferreira/linux_papertradingplatform")" ]; then docker stop $(docker ps --filter ancestor="keganferreira/linux_papertradingplatform" | cut -d'SPAC' -f1 | tail -1); docker rm $(docker ps --filter ancestor="keganferreira/linux_papertradingplatform" | cut -d' ' -f1 | tail -1); fi'    
		              
	      sh 'docker run -p 8083:8083 -d keganferreira/linux_papertradingplatform'
	      	      
              
             }
            }
        }
    }
}
