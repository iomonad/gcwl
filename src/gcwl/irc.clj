;; Filename: irc.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 04/16/2017 Sunday 23:31:39
;; Description: Irc network related funtions

(ns gcwl.irc
  (:require [gcwl.config :as config]
            [clojure.string :as string]
            [clojure.java.io :as io]))
