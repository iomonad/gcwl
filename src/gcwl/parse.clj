;; Filename: parse.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 04/19/2017 Wednesday 22:43:41
;; Description: Parsing utils

(ns gcwl.parse)

(defn handleerr [error]
  (str
   (format "Error: %s" error)))

                                        ; Prefix is: ~ 
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
