(ns gcwl.plugins.date)

(defn return-date [irc message]
  (try
    (let [date (new java.util.Date)]
    (str (format "Current date: %s" date)))
    (catch Exception e
      (str (format "Error: %s" e)))))

(def plugin {:commands {"date" return-date}})
