(ns wellness-tracker.db
  (:require  [com.stuartsierra.component :as component]))

(defn- instant-now [] (java.util.Date/from (java.time.Instant/now)))

(defn- generate-uuid []
  (java.util.UUID/randomUUID))

(defn- mood-list-example []
  { #uuid"ffb99671-d5c7-449a-855f-c513d0a64bad" {:mood :good, :activities ["programming", "music"], :tx-date (instant-now)},
   #uuid"f8df71b8-781a-4099-b954-d628163dc631" {:mood :meh, :activities ["helping wife"], :tx-date (instant-now)}})

(defn- init-db-values [db]
  (reset! db (mood-list-example)))

(defrecord Database []
  component/Lifecycle
  (start [this]
    (let [db (atom {})]
      (println "Starting Database")
      (init-db-values db)
      (assoc this :database db)))

  (stop [this]
    (println "Stppping Database")
    (dissoc this :database)))

(defn new-database []
  (->Database))