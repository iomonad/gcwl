;; Filename: bot.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 04/17/2017 Monday 21:08:36
;; Description: Bot related function

(ns gcwl.bot
  (:require [irclj.core     :as irc]
            [irclj.events   :as events]
            [gcwl.parse     :refer [extract-command]]
            [clojure.string :as string]))

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
        (println (format "CMD: %s %s" command (str (:text msg))))
        (if-let [handler (choose-handler plugins command)]
          (when-let [responses (handler irc updated-message)]
            (respond-with irc updated-message responses))
          (respond-with irc updated-message
                        (str (format "Command %s not found." (get updated-message :command))))))
      (catch Throwable e
        (irc/reply irc msg (str "Error" e)) ; Throw error to channel
        (println (.getMessage e))
        (.printStackTrace e)))))

(defn get-plugin-commands [plugins]
  "Return a map of plugins commands"
  (mapcat #(keys (:commands %)) plugins))

(defn run-bot [plugins & {:keys [host port nick password channels server-password]}]
  "Bot entry point"
  ;; Create local bot instance
  (def bot (irc/connect host port nick
                        :pass server-password
                        :callbacks {:privmsg (server-callback plugins)
                                    :raw-log irclj.events/stdout-callback}))
  (println (format "[*] Connecting as %s@%s" nick host))
  (when password (irc/identify bot password)) ; Auth if defined
  (dosync
   (alter bot assoc ; Add other options to bot connection
          :prefixes {}
          :ssl? true
          :plugins plugins)
   (doseq [c channels]
     (irc/join bot c)) ; Join each channels
   ))


(defn start-bot [plugins]
  "Start the bot instance"
  (let [nick "gcwl"
        host "irc.freenode.net"
        port 6667
        channels (-> (str "#bot-test")
                     (.split ",") ; Pass each chan to vec
                     vec)]
    (run-bot plugins
             :host host
             :port port
             :nick nick
             :channels channels)))
