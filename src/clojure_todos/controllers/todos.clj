(ns clojure-todos.controllers.todos
  (:use ring.util.response)
  (:require [compojure.core :refer :all]))

(defn all-todos []
  (response
    [{:id 123 :title "nice title"}]))

(defroutes todo-routes
  (GET "/todos" [] (all-todos)))

