(ns salmon.plugins.date
  (:require [salmon.parse :refer :all]))

(defn return-date [irc message]
  (let [date (new java.util.Date)]
    (try
      (format "Current date: %s" date)
      (catch Exception e
        (handleerr e)
        ))
    )  
  )
(def plugin {:commands {"date" return-date}})
