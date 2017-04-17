;; Filename: core.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 04/17/2017 Monday 20:30:47
;; Description: Main namespace of the program

(ns gcwl.core
  "Main namespace of the program"
  (:require [gcwl.config    :as config]
            [gcwl.bot       :as bot]
            [clojure.string :as string]
            [cheshire.core  :as json]))

(defn run []
  (println "[*] Starting bot")
  (bot/start-bot []))
