<img src="https://github.com/PerfectoMobileSA/perfecto-plugin/blob/master/DOC/perfecto.jpg" height="75" width="300"/>

# Perfecto Jenkins Plugin

Perfecto Jenkins Plugin provides the ability to auto-create/ re-use existing Perfecto Connect tunnel-id in build environment. 


# Table of Contents

- [Perfecto Jenkins Plugin](#perfecto-jenkins-plugin)
- [Table of Contents](#table-of-contents)
  - [How to use](#how-to-use)
  - [Prerequisites](#prerequisites)
    - [Steps to Enable the plugin](#steps-to-enable-the-plugin)
  - [Screenshots](#screenshots)
    - [Mac](#mac)
    - [Windows](#windows)
  - [Advanced Options](#advanced-options)
  - [Usage](#usage)
    - [Maven Sample](#maven-sample)
    - [Gradle Sample](#gradle-sample)
  - [Steps to Stop Perfecto Connect](#steps-to-stop-perfecto-connect)
  - [Perfecto Connect Pipeline Sample:](#perfecto-connect-pipeline-sample)
    - [Steps](#steps)
  - [Upload app to perfecto](#upload-app-to-perfecto)

    
## How to use

## Prerequisites

* Download Perfecto Connect client, extract to any folder.
* Generate Perfecto Security Token (if not generated already) 
* Mac users should install Jenkins via [brew.](https://www.jenkins.io/download/lts/macos/)

### Steps to Enable the plugin 

1.	Click New Item in Jenkins home page.</br>
2.	Enter Your preferred Item name.</br>
3.	Select any project type except pipeline.</br>
4.	Click OK.</br>
5.	Select Perfecto Connect checkbox under Build Environment (Refer Screenshots section)</br>
6.	Note: Get help from your Jenkins Administrator to create the below Perfecto credentials:</br>
	a.	Select Add option next to Credentials dropdown and select Jenkins. </br>
	b.	Select option: “Perfecto” under Kind dropdown in Add Credentials window.</br>
	c.	Provide Your Cloud Name, Username and Security Token and click on Add.</br>
	
<img src="https://github.com/PerfectoMobileSA/perfecto-plugin/blob/master/DOC/cred.png" height="260" width="760"/></br>

7.	Provide Perfecto Connect Path in Perfecto Connect Path text field. </br>
	a.	E.g.: - /Users/Mymac/Downloads</br>
8.	Provide Perfecto Connect File Name in Perfecto Connect File Name text field.</br>
	a.	E.g.: - Mac – perfectoconnect</br>
	b.	E.g.: - Windows – perfectoconnect64.exe or perfectoconnect32.exe</br>

## Screenshots
### Mac
<img src="https://github.com/PerfectoMobileSA/perfecto-plugin/blob/master/DOC/mac.png" height="260" width="760"/>
	 
### Windows
<img src="https://github.com/PerfectoMobileSA/perfecto-plugin/blob/master/DOC/win.png" height="260" width="760"/>

## Advanced Options

You can provide Advanced options such as Perfecto Connect Additional Parameters, Override Tunnel ID  Name and Existing Tunnel ID.</br>
1.	Additional Parameters</br>
	a.	Provide Perfecto Connect parameters such as bridgeproxyip, bridgeproxyport</br>
	b.	E.g.: - --bridgeproxyip=127.0.0.1 --bridgeproxyport=8888</br>
2.	You can override Tunnel ID Environment Variable name in Override Tunnel ID Name in text field. (The default Jenkins Build Environment variable name is tunnelId)</br>
3.	You can reuse already created Tunnel ID in Existing Tunnel ID text field.</br>

<img src="https://github.com/PerfectoMobileSA/perfecto-plugin/blob/master/DOC/4.png" height="200" width="620"/></br>

## Usage

### Maven Sample

<img src="https://github.com/PerfectoMobileSA/perfecto-plugin/blob/master/DOC/6.png" height="300" width="800"/>
 
### Gradle Sample

<img src="https://github.com/PerfectoMobileSA/perfecto-plugin/blob/master/DOC/7.png" height="360" width="800"/>
 
 
## Steps to Stop Perfecto Connect

1.	Add a Post Build task under Post Build Action.</br>
2.	Enter the below script under Script text field.</br>

<img src="https://github.com/PerfectoMobileSA/perfecto-plugin/blob/master/DOC/5.png" height="360" width="760"/></br>
 
## Perfecto Connect Pipeline Sample: 

### Steps

1.	Create a new Pipeline and add the below code to pipeline script text field.</br>
2.	Update cloudName, securityToken and perfectoConnectPath as applicable.</br>

```import javax.swing.GroupLayout.ParallelGroup
import groovy.json.JsonSlurperClassic
import groovy.json.JsonSlurper
 
node {
String cloudName = "<<CLOUD NAME e.g. demo>>";
String securityToken = "<<SECURITY TOKEN>>";
String perfectoConnectPath = "/Users/myMac/Downloads/perfectoconnect";
    environment {
        tunnelId = ""
    }
    stage('perfectoconnect start'){
        if(cloudName.contains("<<")){
             error "Kindly update cloudName, securityToken and perfectoConnectPath"
        }
        String script = perfectoConnectPath + " start  -c " + cloudName + ".perfectomobile.com  -s " + securityToken;
        echo script;
        tunnelId = sh (script: script , returnStdout: true).trim()
        env.tunnelId = "${tunnelId}"
    }
    stage('script'){
        echo "Tunnel id: ${tunnelId}"
    }
    stage('perfectoconnect stop'){
        sh label: '', returnStdout: true, script: perfectoConnectPath + " stop"
    }
}
```

## Upload app to perfecto
	
Follow [this](https://developers.perfectomobile.com/display/TT/How+to+upload+file+to+the+repository+via+API+using+postman+or+curl) documentation to use curl and upload app to perfecto media repository.
