pipeline { 
    environment { 
        registry = "palindrome" 
        ecrurl = "https://539785008510.dkr.ecr.eu-north-1.amazonaws.com/palindrome"
        ecrcredentials = "aws-instance-role"
        dockerImage = '' 
    }
    agent any 
    stages {
        stage('Building our image') { 
            steps { 
                script { 
                    dockerImage = docker.build registry
                }
            } 
        }
        stage('Deploy our image') { 
            steps { 
                script { 
                    docker.withRegistry( ecrurl, ecrcredentials ) {
                        dockerImage.push("$BUILD_NUMBER")
                        dockerImage.push('latest')
                    }
                } 
            }
        } 
        stage('Cleaning up') { 
            steps { 
                sh "docker rmi $registry:$BUILD_NUMBER" 
                sh "docker rmi $registry:latest"
            }
        } 
    }
}