(ns wellness-tracker.ports.http-in
  (:require [wellness-tracker.controllers :as controller]
            [cheshire.core :refer [parse-string]]
            [wellness-tracker.adapters :as adapters])
  (:use clojure.pprint))

(defn mood-history [request]
  (let [moods (controller/list-mood-history (:db request))]
    {:status 200 :body moods}))


(defn save-mood-record [request]
  (let [mood (:json-params request)
        db (:db request)]
    (-> mood
        (adapters/mood-in->mood-db)
        (controller/insert-mood-db! db))
    ;(controller/insert-mood-db! (adapters/mood-in->mood-db mood) db)
    {:status 200 :body {:message "Success" :mood mood}}))

