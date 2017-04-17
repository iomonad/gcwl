;; Filename: parse.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 04/17/2017 Monday 23:04:50
;; Description: Parsing utils

(ns gcwl.parse)

; @ -> Prefix
(defn extract-command [message]
  "Extract command from raw message"
  (if-let [[_ cmd rest-of-text]
           (re-find #"^[@](\S+)\s*(@*)$" (:text message))]
    [cmd (assoc message :text rest-of-text
                :command cmd)]))

(defn extract-args [message]
  "Extract arguments from raw message"
  (if-let [[_ word rest-of-text]
           (re-find #"^(\S+)\s*(@*)$" (:text message))]
    [word (assoc message :text rest-of-text)]))
