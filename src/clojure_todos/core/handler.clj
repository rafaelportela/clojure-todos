(ns clojure-todos.core.handler
  (:use ring.util.response)
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :as middleware]
            [compojure.handler :as handler]))

(defn all-todos []
  (response
    [{:id 123 :title "nice title"}]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/todos" [] (all-todos))
  (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
    (middleware/wrap-json-body)
    (middleware/wrap-json-response)))
