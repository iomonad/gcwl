(ns gcwl.core
  (:require [gcwl.config   :as configs]
            [gcwl.irc      :as irc]
            [cheshire.core :as json]))

(defn run []
  (println "[*] Starting gcwl"))
