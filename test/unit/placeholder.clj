(ns placeholder
  (:require [clojure.test :refer :all]
            [matcher-combinators.test]
            [matcher-combinators.matchers :as m]))


(deftest test-matching-with-explicit-matchers
  (is (match? (m/equals 37) (+ 29 8)))
  (is (match? (m/regex #"fox") "The quick brown fox jumps over the lazy dog")))