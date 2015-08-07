(ns {{name}}.server
    (:require [{{name}}.service :as service]
              [io.pedestal.http :as http]
              [{{name}}.channels :as channels]
              [{{name}}.queue :as queue]
              [turbovote.resource-config :refer [config]]
              [clojure.tools.logging :as log]
              [immutant.util :as immutant]))

(defn shutdown [rabbit-resources]
  (channels/close-all!)
  (queue/close-all! rabbit-resources))

(defn start-http-server [& [options]]
  (-> (service/service)
      (merge options)
      http/create-server
      http/start))

(defn -main [& args]
  (let [rabbit-resources (queue/initialize)]
    (start-http-server)
    (immutant/at-exit (partial shutdown rabbit-resources))))