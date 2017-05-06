;; Filename: core.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/06/2017 Saturday 22:04:27
;; Description: Main namespace of the program


(ns salmon.core
  "Main namespace of the program"
  (:require [salmon.config  :as config]
            [salmon.bot     :as bot]
            [salmon.plugins :as plugins]
            )
  (:gen-class))

(defn -main []
  (println "[*] Starting bot")
  (bot/start-bot @plugins/plugins-enabled))
