;; Filename: help.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/09/2017 Tuesday 19:32:30
;; Description: Help command
(ns salmon.plugins.help
  (:require [salmon.parse :as parse]
            [clojure.string :as str]))

(defn fn-help [irc message]
  (let [plugins (:plugins @irc)]    
    (str "Available commands: " (str/join ", " (->> plugins (map :name)))
         )))

(def plugin {:name "help"
             :desc "Show help and documention about commands"
             :commands {"help" fn-help}})
