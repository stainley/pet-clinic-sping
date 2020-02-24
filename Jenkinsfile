pipeline {
    agent any
    environment {
        // This can be nexus3 or nexus2
          NEXUS_VERSION = "nexus3"
          // This can be http or https
          NEXUS_PROTOCOL = "http"
          // Where your Nexus is running. In my case:
          NEXUS_URL = "192.168.1.5:8081"
          // Repository where we will upload the artifact
          NEXUS_REPOSITORY = "maven-snapshots"
          // Jenkins credential id to authenticate to Nexus OSS
          NEXUS_CREDENTIAL_ID = "nexus-credentials"
          /*
            Windows: set the ip address of docker host. In my case 192.168.99.100.
            to obtains this address : $ docker-machine ip
            Linux: set localhost to SONARQUBE_URL
          */
          SONARQUBE_URL = "http://192.168.1.4"
          SONARQUBE_PORT = "9000"
    }

     options {
      skipDefaultCheckout()
     }

     stages {
        stage('SCM') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            parallel {
                stage('Compile') {
                    agent {
                        docker {
                            image 'maven:3.6.0-jdk-8-alpine'
                            args '-v /root/.m2/repository:/root/.m2/repository'
                            // to use the same node and workdir defined on top-level pipeline for all docker agents
                            reuseNode true
                        }
                    }
                    steps {
                        sh 'mvn clean compile'
                    }
                }
            }
        }
     }
}
