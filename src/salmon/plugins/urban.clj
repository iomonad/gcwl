;; Filename: urban.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/11/2017 Thursday 19:11:18
;; Description: Scrape result from Urban dictionnary
(ns salmon.plugins.urban
  (:require [salmon.parse :as parse]
            [clojure.string :as str]
            [net.cgrand.enlive-html :as html]
            [org.httpkit.client :as http]))

(defn request [query]
  "Do a request to extract site response"
  (let [url (str (format "https://urbandictionary.com/define.php?term=%s" query))]
    (html/html-snippet (:body @(http/get url)))))

(defn extract-def [rq]
  "Extract meaning div"
  (let [meaning (html/select rq [:div.meaning])]
    (format "Definition: %s" meaning)))

(defn fn-urban [irc message]
  (if-let [[word _] (parse/extract-word message)]
    (try(extract-def (request (str word)))
        (catch Exception e
          (parse/handleerr e)))))

(def plugin {:name "urban"
             :desc {"urban" "Get response from urban dictionnay server"}
             :commands {"urban" fn-urban}})
