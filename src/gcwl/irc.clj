;; Filename: irc.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 04/16/2017 Sunday 09:34:58
;; Description: Irc network related funtions

(ns gcwl.irc
  "Irc network protocol related functions"
  (:require [gcwl.config :as config]
            [clojure.string :as string]
            [clojure.java.io :as io])
  (:import java.net.Socket
           java.io.IOException))

;; Connections functions

(defn socket-connection [host port]
  "Create a socket connection to a server
   with io pipes"
  (let [socket (Socket. host port)]
    {:socket socket
     :in (io/reader socket)
     :out (io/writer socket)}))

(defn write-data [irc-connection & raw-msg]
  "Write data throught raw irc socket.
   Can take several arguments by joining thems
   with a space."
  (let [to-server (:in irc-connection)
        s (string/join " " raw-msg)]
    (.print to-server (format "%s\r\n" s))
    (.flush to-server)))

(defn connect-to-irc [host port]
  "Make a connection to an IRC server and
   return a logged socket named 'irc-connection' "
  (let [irc-connection (socket-connection host port)]
    (write-data irc-connection (format "NICK %s" (str (:nick config/settings))))
    (write-data irc-connection
                (format "USER %s %s %s :%s"
                        (:name config/settings)
                        (:nick config/settings)
                        (:sname config/settings)
                        (:rname config/settings)))
    irc-connection))

(defn close-connection [irc-connection]
  "Close connection for the irc server"
  (.close (:in irc-connection)))

;; Irc protocol functions

(defn join-channel [irc-connection channel]
  "Join a channel on the irc server"
  (write-data irc-connection (format "JOIN %s" channel)))
