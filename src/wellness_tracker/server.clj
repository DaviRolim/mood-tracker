(ns wellness-tracker.server
  (:require [io.pedestal.http :as http]
            [io.pedestal.interceptor :as i]
            [io.pedestal.test :as test]
            [io.pedestal.http.route :as route]))



(defn respond-hello [request]
  {:status 200 :body "Hello, world!"})

(def routes
  (route/expand-routes
    #{["/greet" :get respond-hello :route-name :greet]}))

(def service-map-base {::http/routes routes
                       ::http/port   8890
                       ::http/type   :jetty
                       ::http/join?  false})

(def service-map (-> service-map-base
                     (http/default-interceptors)))

(defonce server (atom nil))

(defn start-server []
  (reset! server (http/start (http/create-server service-map))))

(defn test-request [verb url]
  (test/response-for (::http/service-fn @server) verb url))

(defn stop-server []
  (http/stop @server))

(defn restart-server []
  (stop-server)
  (start-server))