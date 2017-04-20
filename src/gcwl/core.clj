;; Filename: core.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 04/20/2017 Thursday 21:35:54
;; Description: Main namespace of the program


(ns gcwl.core
  "Main namespace of the program"
  (:require [gcwl.config  :as config]
            [gcwl.bot     :as bot]
            [gcwl.plugins :as plugins]
            )
  (:gen-class))

(defn run []
  (println "[*] Starting bot")
  (bot/start-bot @plugins/plugins-enabled))
