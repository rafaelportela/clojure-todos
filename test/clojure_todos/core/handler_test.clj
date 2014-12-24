(ns clojure-todos.core.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [clojure.data.json :as json]
            [clojure-todos.core.handler :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404))))

  (testing "list of todos"
    (let [response (app (mock/request :get "/todos"))]
      (is (= (:status response) 200))
      (is (= (json/read-str (:body response)) [{"id" 123 "title" "nice title"}]))))) 
