(ns gcwl.irc
  (:require [clojure.string :as string]
            [clojure.java.io :as io])
  (:import java.net.Socket
           java.io.IOException))

(defn make-connection [host port]
  "Create a connection to an IRC server"
  (let [socket (Socket. host port)]
    {:socket socket
     :in (io/reader socket)
     :out (io/writer socket)}))

(defn write-data [irc & s]
  "Write data throught raw irc socket"
  (let [s (string/join " " s)]
    ))
