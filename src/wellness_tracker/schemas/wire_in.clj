(ns wellness-tracker.schemas.wire-in
  (:require [schema.core :as s])
  (:use clojure.pprint))

(def mood-in
  "Mood comming from outside-in"
  {:mood (s/enum "excellent" "good" "meh" "bad")
   :activities [s/Str]})


