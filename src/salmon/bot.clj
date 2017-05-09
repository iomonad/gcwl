;; Filename: bot.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/09/2017 Tuesday 18:52:17
;; Description: Bot related function

(ns salmon.bot
  (:require [irclj.core     :as irc]
            [irclj.events   :as events]
            [salmon.parse   :refer [extract-command extract-word raw->nick
                                    raw->chan raw->msg mongo-callback]]
            [salmon.db      :as db]
            [clojure.string :as string])
  (:gen-class))

(defn choose-handler [plugins name]
  "Small function for handler selection"
  (->> plugins (map #(get-in % [:commands name])) (remove nil?) first))

(defn respond-with [irc message responses]
  "Parse response using with procedure"
  (when-not (nil? responses)
    (def vec-responses (if (coll? responses) responses [responses]))
    (doseq [r vec-responses]
      (irc/reply irc message r))))

(defn server-callback [plugins]
  "Server callback to analyse and parse raw data"
  (fn [irc msg]
    (try
      (when-let [[command updated-message] (extract-command msg)]        
        (if-let [handler (choose-handler plugins command)]
          (when-let [responses (handler irc updated-message)]
            (db/salmon-command-logs (raw->nick (:raw msg)) ; Log correct command request
                                    (str command)
                                    (raw->chan (:raw msg))
                                    (str (:text msg))
                                    (str (:raw msg)))
            (respond-with irc updated-message responses))
          (respond-with irc updated-message
                        (str (format "Command %s not found." (get updated-message :command))))))
      (catch Throwable e
        (irc/reply irc msg (str "Error: " e)) ; Throw error to channel
        (println (.getMessage e))
        (.printStackTrace e)))))

(defn run-bot [plugins & {:keys [host port nick realname username password channels server-password]}]
  "Bot entry point"
  ;; Create local bot instance
  (def bot (irc/connect host port nick
                        :pass server-password
                        :real-name realname
                        :username username
                        :callbacks {:privmsg (server-callback plugins)                                    
                                    :raw-log (or mongo-callback ; Using fallback callback
                                                 irclj.events/stdout-callback)}))
  (println (format "[*] Connecting as %s@%s" nick host))
  (when password (irc/identify bot password)) ; Auth if defined
  (dosync
   (alter bot assoc ; Add other options to bot connection
          :prefixes {}
          :ssl? true
          :plugins plugins)
   (doseq [c channels]
     (irc/join bot c))))


(defn start-bot [plugins]
  "Start the bot instance"
  (let [config (or (read-string (slurp "resources/config.clj"))
                   nil)
        nick (or (get config :nick)
                 "fake-salmon")
        pass (or (get config :pass)
                 "fake-password")
        realname (or (get config :realname)
                     "fake-salmon-name")
        username (or (get config :username)
                     "fake-salmon-username")
        host (or (get-in config [:server :host])
                 "irc.freenode.net")
        port (or (get-in config [:server :port])
                 6667)
        channels (or (get-in config [:server :channels])
                     ["#bottest"])]
    (run-bot plugins
             :host host
             :port port
             :nick nick
             :realname realname
             :username username
             :password pass
             :channels channels)))
