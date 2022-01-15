(ns wellness-tracker.db
  (:require  [com.stuartsierra.component :as component]))

(defrecord Database []
  component/Lifecycle
  (start [this]
    (println "Starting Database")
    this)
  (stop [this]
    (println "Stppping Database")
    this))

(defn new-database []
  (->Database))