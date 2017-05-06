;; Filename: config.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/06/2017 Saturday 22:04:48
;; Description: Configuration namespaces for the bot

(ns salmon.config
  (:require [clojure.java.io :as io]
            [cheshire.core :as json]))

(def conf-path
  "Read configuration from file"
  (try
    (-> "../../config.json" io/resource)
    (catch Exception e
      (println (format "[*] FATAL: %s"e))
      (System/exit 0))))

(defn get-conf []
  "Parse json from configuration file"
  (json/parse-string (slurp conf-path) true))
