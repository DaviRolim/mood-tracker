(ns wellness-tracker.ports.http-in)

(defn respond-hello [request]
  {:status 200 :body "Hello, world!"})

