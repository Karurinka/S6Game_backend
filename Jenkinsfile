pipeline {
    agent {
		node {
			label 'master'
		}
	}
    tools{
        maven 'apache-maven'
        jdk 'JDK 11'
    }
    options {
        skipStagesAfterUnstable()
    }
  
    stages {
        stage('Docker cleanup'){
            steps{
				sh '''
				docker rmi $(docker images -f 'dangling=true' -q) || true
				docker rmi $(docker images | sed 1,2d | awk '{print $3}') || true
				'''
            }
        }

		stage('Package') {
			steps {
				sh 'mvn -f ./auth/pom.xml -B -DskipTests package'
			}
		}
		
		stage('Docker Build') {
			steps {
				sh 'docker build . -t michellebroens/solo-auth:develop'
			}
		}
		
		stage('Docker publish') {
			steps {
				withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId:'dockerhub', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
				sh 'docker login -u $USERNAME -p $PASSWORD'
				sh 'docker push michellebroens/solo-auth:develop'
				}	
			}
		}
    }
}