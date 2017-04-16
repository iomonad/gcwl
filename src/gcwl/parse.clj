;; Filename: parse.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 04/16/2017 Sunday 23:11:16
;; Description: Parsing utils

(ns gcwl.parse)

(defn extract-command [message]
  "Extract command from raw message"
  (if-let [[_ cmd rest-of-text]
           (re-find #"^[.](\S+)\s*(.*)$" (:text message))]
    [cmd (assoc message :text rest-of-text
                :command cmd)]))

(defn extract-word [message]
  "Extract word from raw message"
  (if-let [[_ word rest-of-text]
           (re-find #"^(\S+)\s*(.*)$" (:text message))]
    [word (assoc message :text rest-of-text)]))
