pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: "10"))  // 设置最多保存近10次的构建执行日志
//        disableConcurrentBuilds()  // 禁止pipeline并行执行
        gitLabConnection('localWang') // 设置gitlab连接
    }

    triggers{
        cron('30 15 * * 5') // 每周五的15点30分执行一次
//        gitlab( triggerOnPush: true,
//                triggerOnMergeRequest: true,
//                branchFilterType: 'All',
////                triggerOnNoteRequest: true,
////                noteRegex: '.*run.*'
//                secretToken: "97d1cda9bbab84248761221b196c208d"
//        )  // 设置gitlab触发器

//      plugin官网： https://plugins.jenkins.io/generic-webhook-trigger/
        GenericTrigger(
//                genericVariables: [
//                        [key: 'ref', value: '$.ref'],
//                        //[key: 'fixversion', value: '$.issue.fields.customfield_10415']
//                ],
                genericRequestVariables: [
                        [key: 'testSuitePath',defaultValue: "testng.xml"],

                ],
                token: 'jenkins3',
                printContributedVariables: true,
                printPostContent: true,
                silentResponse: false,
                regexpFilterText: '',
                regexpFilterExpression: ''
        )
    }

    tools {
        maven 'maven-3.6.3'
    }

    parameters {
        string(name: "PLAN_ROOT_PATH", defaultValue: "test_xml", description: "测试用例根目录")
        string(name: "test_file", defaultValue: "marketing_tag_test.xml", description: "测试用例文件名")
        string(name: "run_id", defaultValue: " ", description: "测试执行Id")
    }

    stages {
        stage("Build") {
            steps {
                echo "Hello world2!"
                sh "printenv" //打印环境变量
                sh "ls"
            }
        }
        stage("run test") {
            steps {
                echo 'run test'
                echo 'run_id: ${run_id}'
                sh "mvn clean test -Dsurefire.suiteXmlFiles=${PLAN_ROOT_PATH}/${test_file} -DrunId=${run_id} -P test -U"
//                sh "mvn clean test -Dsurefire.suiteXmlFiles=$testSuitePath -DrunId=${run_id} -P test -U"
            }
        }
    }
    post {
        success {
            // pipeline运行成功
            echo 'pipeline运行成功'
            updateGitlabCommitStatus name: 'build', state: 'success'
        }
        failure {
            // pipeline运行失败
            echo 'pipeline运行失败'
            updateGitlabCommitStatus name: 'build', state: 'failed'
        }
        aborted {
            // pipeline运行中止
            echo 'pipeline运行中止'
            updateGitlabCommitStatus name: 'build', state: 'canceled'
        }
    }

}