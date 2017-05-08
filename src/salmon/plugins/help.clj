;; Filename: help.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/08/2017 Monday 11:26:33
;; Description: Help command
(ns salmon.plugins.help
  (:require [salmon.parse :as parse]
            [clojure.string :as str]))

(defn fn-help [irc msg]
  (if-let [arg (parse/raw->args (get irc :raw))]
    (try
      (format "%s" (type arg))
      (catch Exception e
        (parse/handleerr e)))))

(def plugin {:name "help"
             :desc "Show help and documention about commands"
             :commands {"help" fn-help}})
