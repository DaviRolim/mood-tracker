(ns wellness-tracker.routes
  (:require [wellness-tracker.ports.http-in :as http-in]
            [io.pedestal.http.route :as route]
            [com.stuartsierra.component :as component]))

(defrecord Routes []
  component/Lifecycle

  (start [this]
    (println "Starting Routes")
    (let [routes
          (route/expand-routes
            #{["/greet" :get http-in/respond-hello :route-name :greet]})]
     (assoc this :routes routes) ))
  (stop [this]
    (println "Stppping Routes")
    (dissoc this :routes)))

(defn new-routes []
  (->Routes))

;(defn new-routes []
;  (map->Routes {}))
