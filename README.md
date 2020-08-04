<img src="https://github.com/PerfectoMobileSA/Perfecto-Jenkins-Plugin/blob/master/DOC/perfecto.jpg" height="75" width="300"/>

# Perfecto Jenkins Plugin

Perfecto Jenkins Plugin provides the ability to auto-create/ re-use existing Perfecto Connect tunnel-id in build environment. 

## How to use
### Prerequisites

* Download Perfecto Connect client, extract to any folder.
* Generate Perfecto Security Token (if not generated already) 

### Steps to Enable the plugin 
1.	Click New Item in Jenkins home page.</br>
2.	Enter Your preferred Item name.</br>
3.	Select any project type except pipeline.</br>
4.	Click OK.</br>
5.	Select Perfecto Connect checkbox under Build Environment (Refer Screenshots section)</br>
6.	Provide Your Credentials.</br>
	a.	Select Add option next to Credentials dropdown and select Jenkins. </br>
	b.	Select option: “Perfecto” under Kind dropdown in Add Credentials window.</br>
	c.	Provide Your Cloud Name, Username and Security Token and click on Add.</br>
	
	<img src="https://github.com/PerfectoMobileSA/Perfecto-Jenkins-Plugin/blob/master/DOC/cred.png" height="260" width="760"/></br>

7.	Provide Perfecto Connect Path in Perfecto Connect Path text field. </br>
	a.	E.g.: - /Users/Mymac/Downloads</br>
8.	Provide Perfecto Connect File Name in Perfecto Connect File Name text field.</br>
	a.	E.g.: - Mac – perfectoconnect</br>
	b.	E.g.: - Windows – perfectoconnect64.exe or perfectoconnect32.exe</br>

## Screenshots
### Mac
<img src="https://github.com/PerfectoMobileSA/Perfecto-Jenkins-Plugin/blob/master/DOC/mac.png" height="260" width="760"/>
	 
### Windows
<img src="https://github.com/PerfectoMobileSA/Perfecto-Jenkins-Plugin/blob/master/DOC/win.png" height="260" width="760"/>

## Advanced Options

You can provide Advanced options such as Perfecto Connect Additional Parameters, Override Tunnel ID  Name and Existing Tunnel ID.</br>
1.	Additional Parameters</br>
	a.	Provide Perfecto Connect parameters such as bridgeproxyip, bridgeproxyport</br>
	b.	E.g.: - --bridgeproxyip=127.0.0.1 --bridgeproxyport=8888</br>
2.	You can override Tunnel ID Environment Variable name in Override Tunnel ID Name in text field. (The default Jenkins Build Environment variable name is tunnelId)</br>
3.	You can reuse already created Tunnel ID in Existing Tunnel ID text field.</br>

	<img src="https://github.com/PerfectoMobileSA/Perfecto-Jenkins-Plugin/blob/master/DOC/4.png" height="250" width="820"/></br>
 
## Steps to Stop Perfecto Connect

1.	Add a Post Build task under Post Build Action.</br>
2.	Enter the below script under Script text field.</br>

	<img src="https://github.com/PerfectoMobileSA/Perfecto-Jenkins-Plugin/blob/master/DOC/5.png" height="360" width="760"/></br>
 
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

## Perfecto Connect Usage in Selenium/Appium
### Maven Sample

<img src="https://github.com/PerfectoMobileSA/Perfecto-Jenkins-Plugin/blob/master/DOC/6.png" height="360" width="820"/>
 
### Gradle Sample

<img src="https://github.com/PerfectoMobileSA/Perfecto-Jenkins-Plugin/blob/master/DOC/7.png" height="360" width="760"/>
 
