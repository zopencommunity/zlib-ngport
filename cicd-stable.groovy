node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/ZOSOpenTools/zlib-ngport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/ZOSOpenTools/zlib-ngport.git'), string(name: 'PORT_DESCRIPTION', value: 'zlib replacement with optimizations' ), string(name: 'BUILD_LINE', value: 'STABLE') ]
  }
}
