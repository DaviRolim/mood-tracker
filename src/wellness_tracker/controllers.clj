(ns wellness-tracker.controllers
  (:require [schema.core :as s]
            [wellness-tracker.schemas.db :as schema.db]
            [wellness-tracker.adapters :as adapters]))



(defn list-mood-history [db]
  @db)

(s/defn insert-mood-db! [mood :- schema.db/mood-db, db]
    (swap! db merge mood))

