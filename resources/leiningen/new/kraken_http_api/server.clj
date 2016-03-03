(ns {{name}}.server
    (:require [{{name}}.service :as service]
              [io.pedestal.http :as http]
              [turbovote.resource-config :refer [config]]
              [clojure.tools.logging :as log]
              [immutant.util :as immutant]
              [kehaar.power :as power]))

(defn start-http-server [& [options]]
  (-> (service/service)
      (merge options)
      http/create-server
      http/start))

(defn -main [& args]
  (power/connect-rabbit 5 (config [:rabbitmq :connection]))
  (start-http-server (config [:server]))
  (immutant/at-exit power/disconnect-rabbit))
