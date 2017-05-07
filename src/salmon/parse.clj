;; Filename: parse.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/07/2017 Sunday 19:33:48
;; Description: Parsing utils

(ns salmon.parse
  (:require [salmon.db :as db]))

(def ^:private admin (atom ["iomonad"])) ;; Registred users
(defn push-admin [nick]
  "Add user to admin atom"
  (swap! admin conj nick))

(defn admin? [nick]
  "Predicate to check if user is administrator"
  (if (.contains @admin)
    (true)
    (false)))

(defn handleerr [error]
  "Just avoiding formating usage"
  (str (format "Error: %s" error)))

(defn argerr [num]
  "Pass number of missing arguments"
  (str (format "Error: %s argument(s) requiered.")))

(defn extract-command [message]
  "Extract command from raw message"
  (if-let [[_ cmd rest-of-text]
           (re-find #"^[.](\S+)\s*(.*)$" (:text message))]
    [cmd (assoc message :text rest-of-text
                :command cmd)]))

(defn extract-word [message]
  "Extract arguments from raw message"
  (if-let [[_ word rest-of-text]
           (re-find #"^(\S+)\s*(.*)$" (:text message))]
    [word (assoc message :text rest-of-text)]))

(defn extract-nick-from-raw [raw]
  "fExtract nick from raw message"
  (if-let [[_ _ nick] (re-find #"(.*?):(.*?)!(.*?)" raw)]
    (str nick)))

(defn mongo-callback  [_ type s]
  "Logs ans store buffer activities to mongodb"  
  (let [nick (extract-nick-from-raw s)]
    (if-not (nil? nick)
      (db/salmon-buffer-logs nick
                             type
                             "Some blah blah"
                             s)
      (println s))))
