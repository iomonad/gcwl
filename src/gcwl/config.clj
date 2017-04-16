;; Filename: config.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 04/16/2017 Sunday 23:34:04
;; Description: Configuration namespaces for the bot

(ns gcwl.config
  (:require [clojure.java.io :as io]
            [cheshire.core :as json]))

(def conf-path
  "Read configuration from file"
  (-> "config.json" io/resource))

(defn get-conf []
  "Parse json from configuration file"
  (json/parse-string (slurp conf-path) true))
