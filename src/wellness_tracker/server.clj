(ns wellness-tracker.server
  (:require [io.pedestal.http :as http]
            [io.pedestal.interceptor :as i]
            [io.pedestal.test :as test]
            [com.stuartsierra.component :as component]))


  (defn- service-map-base [routes] {::http/routes routes
                                    ::http/port   8890
                                    ::http/type   :jetty
                                    ::http/join?  false})

(defn- service-map
  [service-map-base]
  (-> service-map-base
    (http/default-interceptors)))

(defn- start-server [server service-map]
  (reset! server (http/start (http/create-server service-map))))

(defprotocol ServerProvider
  (test-request [self verb url]
    "Execute http tests usign REPL"))

(defrecord Server [database routes]
     component/Lifecycle
       (start [this]
         (println "Starting Server")
         (let [s-map (service-map (service-map-base (:routes routes)))
               server (atom nil)]
           ;; TODO inject database as interceptor in servicemap?
           (start-server server s-map)
         (assoc this :test-request test-request :server server)))

      (stop [this]
        (println "Stopping Server")
        (dissoc this :test-request)
        this)
  ServerProvider
  (test-request [this verb url]
    (let [server (:server this)]
      (test/response-for (::http/service-fn @server) verb url))))



(defn new-server []
  (map->Server {}))





;(defn stop-server []
;  (http/stop @server))
;
;(defn restart-server []
;  (stop-server)
;  (start-server))