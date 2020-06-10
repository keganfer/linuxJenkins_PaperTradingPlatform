
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
              sshagent(credentials: ['ec2-linuxAMI-v2']) {
              sh 'ls'
	      sh 'touch test.txt'      
	      sh 'docker pull keganferreira/linux_papertradingplatform'
	      sh 'docker run -p 8083:8083 -d keganferreira/linux_papertradingplatform'
	      	      
              
             }
            }
        }
    }
}
