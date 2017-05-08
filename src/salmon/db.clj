;; Filename: db.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/08/2017 Monday 09:56:23
;; Description: Database related functions

(ns salmon.db
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  (:import org.bson.types.ObjectId))


(def ^:private config (read-string (slurp "resources/config.example.clj")))

(def ^:dynamic *conn* (mg/connect{:host (or (get-in config [:mongo :host])
                                            "127.0.0.1")
                                  :port (or (get-in config [:mongo :port])
                                            "27017")}))
(def ^:dynamic *db* (mg/get-db *conn* (or (get-in config [:mongo :db])
                                          "salmon")))
(defn salmon-buffer-logs [nick type chan message raw]
  "Insert raw irc logs to mongodb"
  (let [date  (.toString (java.util.Date.))
        coll  (or (get-in config [:mongo :collections :buffer])
                  "buffer")]
    (mc/insert *db* coll {:nick nick
                          :type type
                          :channel chan
                          :date date
                          :message message
                          :raw raw})))

(defn salmon-command-logs [nick command chan message raw]
  "Insert command request irc logs to mongodb"
  (let [date  (.toString (java.util.Date.))
        coll  (or (get-in config [:mongo :collections :command])
                  "cmd")]
    (mc/insert *db* "coll" {:nick nick
                            :date date
                            :channel chan
                            :command command
                            :message message
                            :raw raw})))
