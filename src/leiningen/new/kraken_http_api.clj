(ns leiningen.new.kraken-http-api
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "kraken-http-api"))

(defn kraken-http-api
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info (str "Generating fresh kraken-http-api project in directory " (:sanitized data) "/."))

    (main/info (str "TODO: (you probably want to `cd " name "` first)"))
    (main/info " * Review and address the TODO items in the README.")
    (main/info " * `chmod +x script/*`")
    (main/info " * `git init`")
    (main/info " * `git add .`")
    (main/info " * `git commit -m \"initial commit\"`")
    (main/info " * push this to github")
    (main/info " * make a Buildkite project")
    (->files data
             [".gitignore" (render ".gitignore" data)]
             ["Dockerfile" (render "Dockerfile" data)]
             ["{{name}}@.service.template" (render "PROJECT@.service.template" data)]
             ["docker-compose.yml" (render "docker-compose.yml" data)]
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             ["LICENSE" (render "LICENSE" data)]

             ["script/deploy" (render "script/deploy" data)]
             ["script/build" (render "script/build" data)]

             ["resources/config.edn" (render "config.edn" data)]
             ["resources/logback.xml" (render "logback.xml" data)]

             ["src/{{sanitized}}/server.clj" (render "server.clj" data)]
             ["src/{{sanitized}}/service.clj" (render "service.clj" data)]
             ["src/{{sanitized}}/queue.clj" (render "queue.clj" data)]
             ["src/{{sanitized}}/channels.clj" (render "channels.clj" data)]
             ["src/{{sanitized}}/handlers.clj" (render "handlers.clj" data)]
             ["test/{{sanitized}}/service_test.clj" (render "service_test.clj" data)])))
