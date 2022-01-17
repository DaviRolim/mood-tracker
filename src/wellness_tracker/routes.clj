(ns wellness-tracker.routes
  (:require [wellness-tracker.ports.http-in :as http-in]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]
            [com.stuartsierra.component :as component]))

(defn ok [body]
  {:status 200 :body body
   :headers {"Content-Type" "text/html"}})

(def echo
  {:name :echo
   :enter
   (fn [context]
     (let [request (:request context)
           response (ok context)]
       (assoc context :response response :request request)))})

(defrecord Routes []
  component/Lifecycle

  (start [this]
    (println "Starting Routes")
    (let [routes
          (route/expand-routes
            #{["/moods" :get http-in/mood-history :route-name :moods]
              ["/mood" :post [(body-params/body-params) http-in/save-mood-record] :route-name :save-mood]})]
     (assoc this :routes routes) ))
  (stop [this]
    (println "Stppping Routes")
    (dissoc this :routes)))

(defn new-routes []
  (->Routes))

;(defn new-routes []
;  (map->Routes {}))
