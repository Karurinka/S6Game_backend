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
				sh 'mvn -f ./lobby/pom.xml -B -DskipTests package'
			}
		}
		
		stage('Docker Build') {
			steps {
				sh 'docker build -t mycontainerregistry1610.azurecr.io/auth-service:kube${BUILD_NUMBER} ./auth'
				sh 'docker build -t mycontainerregistry1610.azurecr.io/lobby-service:kube${BUILD_NUMBER} ./lobby'
			}
		}
		
		stage('Docker publish') {
			steps {
				withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId:'acr-credentials', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]){
				sh 'docker login mycontainerregistry1610.azurecr.io -u $USERNAME -p $PASSWORD'
				sh 'docker push mycontainerregistry1610.azurecr.io/auth-service:kube${BUILD_NUMBER}'
				sh 'docker push mycontainerregistry1610.azurecr.io/lobby-service:kube${BUILD_NUMBER}'
				}	
			}
		}
		
		stage('Kubectl magic'){
			steps {
				sh 'kubectl set image deployment/mycontainerregistry1610.azurecr.io/auth-service:kube${BUILD_NUMBER} --kubeconfig /home/MyAKSCluster/.kube/config'
				sh 'kubectl set image deployment/mycontainerregistry1610.azurecr.io/lobby-service:kube${BUILD_NUMBER} --kubeconfig /home/MyAKSCluster/.kube/config'
			}
		}
    }
}