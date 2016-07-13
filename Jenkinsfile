node(){
    stage 'Checkout Source Code'
    checkout scm

    stage 'Build Maven Project'
    def maven = tool 'MAVEN'
    sh '${maven}/bin/mvn clean install'

}