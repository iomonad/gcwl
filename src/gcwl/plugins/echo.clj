(ns gcwl.plugins.echo
  (:require [gcwl.parse     :refer [extract-args]]
            [clojure.string :as string]))

(defn return-echo [irc message]
  (if-let [[arg _] (extract-args message)]
    (let [arg1 (-> message :text first)]
      (try
        (str (format "Your parameter is %s." arg1)) 
        (catch Exception e
          (str "Error: %s" e)
          )))
    ))

(def plugin {:commands { "echo" return-echo }})
