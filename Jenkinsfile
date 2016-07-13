node(){
    stage 'Checkout Source Code'
    checkout scm

    stage 'Build Maven Project'
    def maven = tool 'MAVEN'
    ${maven}/bin/mvn clean install

}