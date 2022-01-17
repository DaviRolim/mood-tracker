(ns wellness-tracker.core
  (:require [com.stuartsierra.component :as component]
            [wellness-tracker.server :as server]
            [wellness-tracker.routes :as routes]
            [wellness-tracker.db :as db]
            [clojure.edn :as edn])
  (:use clojure.pprint)
  (:gen-class))

(defn- system-map []
  (component/system-map
    :database (db/new-database)
    :routes (routes/new-routes)
    :server (component/using (server/new-server) [:database :routes])))

(defn -main
  "Start the component orchestration"
  []
  (component/start (build-system-map)))
;(def component-result (component/start (system-map)))
;(server/test-request (:server component-result) :get "/greet")

;(edn/read-string
;  (:body (server/test-simple-request (:server component-result) :get "/moods")))
;
;(def mood-example {:mood       "excellent"
;                   :activities ["studying" "nubank eng onboarding"]})
;
;(server/test-post-request (:server component-result) "/mood" mood-example)

