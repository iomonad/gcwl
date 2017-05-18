(ns salmon.plugins.date
  (:require [salmon.parse :refer :all]))

(defn return-date [irc message]
  (let [date (new java.util.Date)]
    (format "Current date: %s" date)))

(def plugin {:name "date"
             :desc {"date" "Show the current date"}
             :commands {"date" return-date}})
