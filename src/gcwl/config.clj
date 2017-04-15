(ns gcwl.config
  (:require [clojure.java.io :as io]
            [cheshire.core :as json]))

(def conf-path
  "Read configuration from file"
  (-> "config.json" io/resource))

(defn get-conf []
  "Parse json from configuration file"
  (json/parse-string (slurp conf-path) true))
