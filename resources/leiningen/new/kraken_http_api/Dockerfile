FROM quay.io/democracyworks/didor:latest

RUN mkdir -p /usr/src/{{name}}
WORKDIR /usr/src/{{name}}

COPY project.clj /usr/src/{{name}}/

ARG env=production

RUN lein with-profile $env deps

COPY . /usr/src/{{name}}

RUN lein with-profiles $env,test test
RUN lein with-profile $env uberjar

CMD java ${JVM_OPTS:--XX:+UseG1GC} \
    -javaagent:resources/jars/com.newrelic.agent.java/newrelic-agent.jar \
    -jar target/{{name}}.jar
