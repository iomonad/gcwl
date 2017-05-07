;; Filename: db.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/07/2017 Sunday 12:19:04
;; Description: Database related functions

(ns salmon.db
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  (:import org.bson.types.ObjectId))

(def ^:dynamic *conn* (mg/connect{:host "127.0.0.1"}))
(def ^:dynamic *db* (mg/get-db *conn* "salmon"))

(defn salmon-buffer-logs [nick type message raw]
  "Insert raw irc logs to mongodb"
  (let [date  (.toString (java.util.Date.))]
    (mc/insert *db* "buffer" {:nick nick
                              :type type
                              :date date
                              :message message
                              :raw raw})))

(defn salmon-command-logs [nick command message raw]
  "Insert command request irc logs to mongodb"
  (let [date  (.toString (java.util.Date.))]
    (mc/insert *db* "cmd" {:nick nick
                           :date date
                           :command command
                           :message message
                           :raw raw})))
