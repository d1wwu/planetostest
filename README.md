# Planet OS Test

<h2>Installation</h2>
<div>git clone https://github.com/d1wwu/planetostest.git</div>
<h2>Usage</h2>
<div>cd planetostest</div>
<div>mvn test -DsuiteXmlFile=src/test/resources/TestPlanetOS.xml -Dlogin={login} -Dpasswd={password} -Dapikey={apikey}</div>
<div>Command line argument -Dplatform=emulator use for mobile emulation</div>
<div>Log report: \target\surefire-reports\html\index.html</div>
