(ns clojure-todos.model.migrations
  (:require [clojure.java.jdbc :as sql]))

(def db (or (System/getenv "DATABASE_URL")
              "postgresql://localhost:5432/todo_dev"))

(defn migrated? []
  (-> (sql/query db 
                 [(str "select count(*) from information_schema.tables "
                       "where table_name='todos'")])
      first :count pos?))

(defn migrate []
  (when (not (migrated?))
    (print "Creating database.. ") (flush)
    (sql/db-do-commands db
                        (sql/create-table-ddl
                          :todos
                          [:id :serial "PRIMARY KEY"]
                          [:title :varchar "NOT NULL"]
                          [:created_at :timestamp
                            "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]))
    (println " done!")))

