FROM quay.io/democracyworks/didor:latest

RUN mkdir -p /usr/src/{{name}}
WORKDIR /usr/src/{{name}}

COPY project.clj /usr/src/{{name}}/

RUN lein deps

COPY . /usr/src/{{name}}

RUN lein test
RUN lein immutant war --name {{name}} --destination target --nrepl-port=11111 --nrepl-start --nrepl-host=0.0.0.0
