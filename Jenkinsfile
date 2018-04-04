#!groovy​
def appName = 'mynginx'
def appServicePort = '80'
def testerImage = 'maven:latest'

node () {
    stage ('Checkout'){
        echo 'Checking out git repository'
        checkout scm
    }

    stage ('Build Image') {
        wf.imageBuild(appName)
    }

    stage('Promote staging') {
        wf.imagePromote('staging')
    }

    wf.imageDeploy(appServicePort) {
        stage ('Test Image') {
        parallel(
            "Selenium Chrome": {
                wf.withSeleniumChrome() {
                    wf.inside(testerImage) {
                        wf.mvn("-DtestUrl=${wf.appServiceUrl} \
                                -DseleniumUrl=${wf.seleniumChromeUrl} \
                                -DseleniumBrowser=chrome test")
                    }
                }
            },
            "Selenium Firefox": {
                wf.withSeleniumFirefox() {
                    wf.inside(testerImage) {
                        wf.mvn("-DtestUrl=${wf.appServiceUrl} \
                                -DseleniumUrl=${wf.seleniumFirefoxUrl} \
                                -DseleniumBrowser=firefox test")
                    }
                }
            })
        }
        stage('Approval') {
            wf.imageApproval(wf.appExternalUrl)
        }
    }

    stage('Promote stable') {
        wf.imagePromote('stable')
    }

}
