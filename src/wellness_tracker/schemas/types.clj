(ns wellness-tracker.schemas.types
  (:require [schema.core :as s]
            [wellness-tracker.server :as server]))

(def ServerComponent (s/protocol server/ServerProvider))
