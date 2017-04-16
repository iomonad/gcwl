;; Filename: core.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 04/16/2017 Sunday 09:42:08
;; Description: Main namespace of the program

(ns gcwl.core
  "Main namespace of the program"
  (:require [gcwl.config   :as config]
            [gcwl.irc      :as irc]
            [cheshire.core :as json]))

(defn run []
  (irc/connect-to-irc "irc.freenode.net" 6667))
