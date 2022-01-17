(ns wellness-tracker.adapters
  (:require [schema.core :as s]
            [wellness-tracker.schemas.wire-in :as schema-in]
            [wellness-tracker.schemas.db :as schema-db]))

(defn- instant-now [] (java.util.Date/from (java.time.Instant/now)))



(s/defn mood-in->mood-db :- schema-db/mood-db
  [mood-in :- schema-in/mood-in]
  (let [uuid (java.util.UUID/randomUUID)
        tx-date (instant-now)
        mood-with-date (assoc mood-in :tx-date tx-date)
        mood-db (update mood-with-date :mood keyword)]
    {uuid mood-db}))
