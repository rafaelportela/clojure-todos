(defproject clojure-todos "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [ring/ring-defaults "0.1.2"]
                 [ring/ring-json "0.3.1"]
                 [ring/ring-jetty-adapter "1.2.1"]
                 [org.clojure/java.jdbc "0.3.2"]
                 [postgresql "9.1-901.jdbc4"]]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler clojure-todos.core.handler/-main}
  :main ^:skip-aot clojure-todos.core.handler
  :uberjar-name "todos-standalone.jar"
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]
                        [org.clojure/data.json "0.2.5"]]}
   :uberjar {:aot :all}})
