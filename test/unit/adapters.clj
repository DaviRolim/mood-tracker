(ns adapters
  (:require [clojure.test :refer :all]
            [wellness-tracker.adapters :as adapters]
            [schema.test]
            [schema-generators.generators :as g]
            [schema-generators.complete :as c]
            [matcher-combinators.test]
            [matcher-combinators.standalone]
            [wellness-tracker.schemas.wire-in :as w.in]))
(use-fixtures :once schema.test/validate-schemas)


(deftest wire-in->db
  "Test if the proper conversion is happening"
    (testing "the mood is changed from string to keyword once adapted"
      (is (match?  {:mood :good}
                   (first
                     (vals
                      (adapters/mood-in->mood-db
                        (c/complete {:mood "good"} w.in/mood-in)))))))
  )
