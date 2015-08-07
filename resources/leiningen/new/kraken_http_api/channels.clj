(ns {{name}}.channels
  (:require [clojure.core.async :as async]))


(defonce ok-requests (async/chan))
(defonce ok-responses (async/chan))

(defn close-all! []
  (doseq [c []]
    (async/close! c)))
