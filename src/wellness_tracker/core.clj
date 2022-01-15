(ns wellness-tracker.core
  (:require [com.stuartsierra.component :as component]
            [wellness-tracker.server :as server]
            [wellness-tracker.routes :as routes]
            [wellness-tracker.db :as db])
  (:use clojure.pprint)
  (:gen-class))

(defn- build-system-map []
  (component/system-map
    :database (db/new-database)
    :routes (routes/new-routes)
    :server (component/using (server/new-server) [:database :routes])))

;(defn -main
;  "Start the component orchestration"
;  []
;  (component/start (build-system-map)))
;; TODO QUESTION  How can I access this component through my system to get these values?
;; Inject as interceptor for routes? create an atom?
;; I want to access my 'public API' for my components.
;; Creating a def is not the best way because its global and etc..
(def component-result (component/start (build-system-map)))
;(pprint (:server component-result))
(server/test-request (:server component-result) :get "/greet")
;; TODO create more routes and start a database as an atom of map. (= course)



