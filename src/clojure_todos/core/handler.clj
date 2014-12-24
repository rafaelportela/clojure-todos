(ns clojure-todos.core.handler
  (:use ring.util.response)
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :as middleware]
            [ring.adapter.jetty :as ring]
            [compojure.handler :as handler]
            [clojure-todos.controllers.todos :as todos])
  (:gen-class))

(defroutes app-routes
  todos/todo-routes
  (GET "/" [] "Hello World")
  (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
    (middleware/wrap-json-body)
    (middleware/wrap-json-response)))

(defn start [port]
  (ring/run-jetty app {:port port :join? false}))

(defn -main []
  (let [port (Integer. (or (System/getenv "PORT") "8080"))]
    (start port)))
