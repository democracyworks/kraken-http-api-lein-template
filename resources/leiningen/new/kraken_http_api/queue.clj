(ns {{name}}.queue
  (:require [clojure.tools.logging :as log]
            [langohr.core :as rmq]
            [kehaar.core :as k]
            [kehaar.wire-up :as wire-up]
            [kehaar.rabbitmq]
            [{{name}}.channels :as channels]
            [turbovote.resource-config :refer [config]]))

(defn initialize []
  (let [max-retries 5
        connection (kehaar.rabbitmq/connect-with-retries max-retries)]
    (let [incoming-events []
          incoming-services [(wire-up/incoming-service
                              connection
                              "{{name}}.ok"
                              (config [:rabbitmq :queues "{{name}}.ok"])
                              channels/ok-requests
                              channels/ok-responses)]
          external-services []
          outgoing-events []]
      {:connections [connection]
       :channels (vec (concat
                       incoming-events
                       incoming-services
                       external-services
                       outgoing-events))})))

(defn close-resources! [resources]
  (doseq [resource resources]
    (when-not (rmq/closed? resource) (rmq/close resource))))

(defn close-all! [{:keys [connections channels]}]
  (close-resources! channels)
  (close-resources! connections))
