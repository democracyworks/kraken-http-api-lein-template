(ns {{name}}.channels
  (:require [clojure.core.async :as async]))

;;; This namespace is for core.async channels used by kehaar and
;;; bifrost. Channels only need to be created for ougoing events and
;;; external services.

(defn close-all! []
  (let [channels []]
    (doseq [c channels]
      (async/close! c))))
