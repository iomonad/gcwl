;; Filename: core.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;;
;; Last-Updated: 05/07/2017 Sunday 12:16:38
;; Description: Main namespace of the program


(ns salmon.core
  "Main namespace of the program"
  (:require [salmon.bot     :as bot]
            [salmon.plugins :as plugins])  
  (:gen-class))

(defn -main []
  "Start each parts in thread"
  (->> [(bot/start-bot @plugins/plugins-enabled)]
       (Thread.)
       (.start)))
