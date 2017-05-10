;; Filename: urban.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/10/2017 Wednesday 23:07:52
;; Description: Scrape result from Urban dictionnary
(ns salmon.plugins.urban
  (:require [salmon.parse :as parse]
            [clojure.string :as str]))

(defn fn-urban [irc message]
  (if-let [[word _] (parse/extract-word message)]
    (format "Response for %s" word)))

(def plugin {:name "urban"
             :desc {"urban" "Get response from urban dictionnay server"}
             :commands {"urban" fn-urban}})
