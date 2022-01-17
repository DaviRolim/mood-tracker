(ns wellness-tracker.schemas.db
  (:require [schema.core :as s])
  (:use clojure.pprint))

(def mood-db
  "schema for mood inside db"
  {s/Uuid {:mood (s/enum :excellent :good :meh :bad)
           :activities [s/Str]
           :tx-date s/Inst}})