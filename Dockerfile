FROM ubuntu:18.04

RUN apt-get update &&\

    apt-get upgrade &&\

    apt install software-properties-common -y

     

RUN apt-get install -y vim &&\

    apt-get install wget -y



RUN  apt-get install systemd -y



RUN wget https://download.java.net/java/GA/jdk16.0.1/7147401fd7354114ac51ef3e1328291f/9/GPL/openjdk-16.0.1_linux-x64_bin.tar.gz -P /tmp &&\

    tar xvf /tmp/openjdk-16.0.1_linux-x64_bin.tar.gz -C /opt 

ENV JAVA_HOME=/opt/jdk-16.0.1

ENV PATH=$PATH:$JAVA_HOME/bin  

RUN wget https://dlcdn.apache.org/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.tar.gz -P /tmp &&\

    tar xf /tmp/apache-maven-*.tar.gz -C /opt &&\

    ln -s /opt/apache-maven-* /opt/maven


RUN useradd -m -U -d /opt/tomcat -s /bin/false tomcat && \

    wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.54/bin/apache-tomcat-9.0.54.tar.gz -P /tmp &&\

    tar xzvf /tmp/apache-tomcat-*tar.gz -C /opt/tomcat --strip-components=1 &&\

    sh -c 'chmod +x /opt/tomcat/bin/*.sh'



RUN apt-get update; apt-get install -y fontconfig libfreetype6 


ENV M2_HOME=/opt/maven

ENV MAVEN_HOME=/opt/maven

ENV PATH=${M2_HOME}/bin:${PATH}


COPY .docker/tomcat-users.xml /opt/tomcat/conf/tomcat-users.xml

COPY .docker/context.xml /opt/tomcat/webapps/manager/META-INF/context.xml

COPY .docker/tomcat.service /etc/systemd/system/tomcat.service

COPY .docker/settings.xml /usr/share/maven/conf/settings.xml



COPY . /var/www/app

COPY .docker/command.sh /var/command.sh



CMD ["sh", "/var/command.sh"]




