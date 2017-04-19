(ns gcwl.plugins.date
  (:require [gcwl.parse :refer :all]))

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
