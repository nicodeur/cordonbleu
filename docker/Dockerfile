FROM maven:3.3.9-jdk-8
MAINTAINER nicodeur <nico.deblock@gmail.com>

RUN apt-get update && apt-get install git

RUN git clone https://github.com/nicodeur/cordonbleu.git /opt/cordonbleu
    
RUN cd /opt/cordonbleu && mvn clean install -X && cp cordonbleu-main/target/cordonbleu-main-0.0.1-SNAPSHOT.jar .

VOLUME /root/.m2/

WORKDIR /opt/cordonbleu

CMD ./run.sh && tail -f cordonbleu.log


EXPOSE 8080