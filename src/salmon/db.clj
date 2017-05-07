;; Filename: db.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/07/2017 Sunday 09:25:58
;; Description: Database related functions

(ns salmon.db
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  (:import org.bson.types.ObjectId))

(def ^:dynamic *conn* (mg/connect{:host "127.0.0.1"}))
(def ^:dynamic *db* (mg/get-db *conn* "salmon"))

(defn salmon-buffer-logs [nick message]
  "Insert raw irc logs to mongodb"
  (let [date  (.toString (java.util.Date.))]
    (mc/insert *db* "buffer" {:nick nick
                              :date date
                              :message message})))

(defn salmon-command-logs [nick command message]
  "Insert command request irc logs to mongodb"
  (let [date  (.toString (java.util.Date.))]
    (mc/insert *db* "commands" {:nick nick
                                :date date
                                :command command
                                :message message})))
