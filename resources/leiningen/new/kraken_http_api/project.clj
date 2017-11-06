(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: HTTP API gateway for ..."
  :url "https://github.com/democracyworks/{{name}}"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-beta4"]
                 [ch.qos.logback/logback-classic "1.2.3"]
                 [democracyworks/bifrost "0.2.0"]
                 [democracyworks/kehaar "0.11.2"]
                 [democracyworks/pedestal-toolbox "0.7.0"]
                 [io.pedestal/pedestal.immutant "0.5.3"]
                 [io.pedestal/pedestal.log "0.5.3"]
                 [io.pedestal/pedestal.service "0.5.3"]
                 [io.pedestal/pedestal.service-tools "0.5.3"]
                 [org.clojure/tools.logging "0.4.0"]
                 [org.immutant/core "2.1.9"]
                 [org.immutant/web "2.1.9"]
                 [turbovote.resource-config "0.2.1"]]
  :plugins [[lein-immutant "2.1.0"]
            [com.pupeno/jar-copier "0.4.0"]]
  :java-agents [[com.newrelic.agent.java/newrelic-agent "3.43.0"]]
  :jar-copier {:java-agents true
               :destination "resources/jars"}
  :prep-tasks ["javac" "compile" "jar-copier"]
  :main ^:skip-aot {{name}}.server
  :uberjar-name "{{name}}.jar"
  :profiles {:uberjar {:aot :all}
             :dev {:resource-paths ["dev-resources"]
                   :jvm-opts ["-Dlog-level=DEBUG"]}
             :test {:dependencies [[clj-http "3.7.0"]]
                    :jvm-opts ["-Dlog-level=INFO"]}})
