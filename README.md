# infinispan-websocket
Inifinispan websocket spring webworkers

Basic prototype Infinispan embbeded with spring boot start, websocket and webworkers
Based in infinispan greeting demo.

## Requirements ##
Java JDK 1.8
Maven 3+

## Instalation ##
Just compile with mvn install 
It will create infinispan-websocket-0.1.0.jar java executable

## Configuration ##
In application.properties file you can set the spring port, even define next program instances port.
Just change server.additionalPorts=8090,8091,8092

## Execution ## 
Java -jar infinispan-websocket-0.1.0.jar
Open you borwser in http://localhost:[port]

Open as many instances you have defined in properties file.
For testing define the cluster name and press "Connect" button it will take a couple seconds to show a message.

Now you can send data to the infinispan cache. Just fill the desired fields and press the button "Send".

You can start several instances and connect them to the same infinispan cluster. Once the instances are connected to the cluster it will popup the cluster data.

Enjoy :)