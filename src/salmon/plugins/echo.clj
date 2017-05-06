(ns salmon.plugins.echo
  (:require [salmon.parse     :refer [extract-word handleerr]]
            [clojure.string :as string]))

(defn return-echo [irc message]
  "Small POC to extract first argument"
  (if-let [[arg1 _] (extract-word message)]
    (try
      (str (format "You say %s" arg1))
      (catch Exception e
        (handleerr e)
        ))
    ))

(def plugin {:commands { "echo" return-echo}})
