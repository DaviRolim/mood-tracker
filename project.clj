(defproject wellness-tracker "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [aero "1.1.6"]
                 [com.stuartsierra/component "1.0.0"]
                 [io.pedestal/pedestal.service "0.5.9"]
                 [io.pedestal/pedestal.route "0.5.9"]
                 [io.pedestal/pedestal.jetty "0.5.9"]
                 [cheshire "5.10.1"]
                 [org.slf4j/slf4j-simple "1.7.32"]
                 [prismatic/schema "1.2.0"]
                 [prismatic/schema-generators "0.1.3"]]
  :test-paths ["test/unit/" "test/integration/"]
  :profiles {:uberjar {:aot :all}
             :dev     {:dependencies [[nubank/matcher-combinators "3.3.1"]
                                      [nubank/state-flow "5.14.0"]]}
                       :test {:dependencies [[nubank/matcher-combinators "3.3.1"]
                                             [nubank/state-flow "5.14.0"]]}}
  :repl-options {:init-ns wellness-tracker.core})
