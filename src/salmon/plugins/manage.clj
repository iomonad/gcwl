;; Filename: manage.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/11/2017 Thursday 21:54:57
;; Description: Manage bot state

(ns salmon.plugins.manage
  (:require [salmon.parse :as parse]
            [clojure.string :as str]))

(defn fn-manage [irc message]
  (let [admin (get message :nick)
        ]
    (str message)))

(def plugin {:name "manage"
             :desc {"manage" "Maintenance commands to manage bot state"}
             :commands {"manage" fn-manage}})
