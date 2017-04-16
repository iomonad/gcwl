;; Filename: bot.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 04/16/2017 Sunday 23:31:11
;; Description: Bot related function

(ns gcwl.bot
  (:require [irclj.core     :as irc]
            [irclj.events   :as events]
            [gcwl.parse     :refer [extract-command]]
            [clojure.string :as string]))

(defn select-handler [plugins name]
  "S"
  (->> plugins (map #(get-in % [:commands name])) (remove nil?) first))

(defn respond-with [irc message responses]
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
        (if-let [handler (select-handler plugins command)]
          (when-let [responses (handler irc updated-message)]
            (respond-with irc updated-message responses))
          (respond-with irc updated-message
                        (str (format "Command %s not found." (get updated-message :command))))))
      (catch Throwable e
        (irc/reply irc msg (str "Error" e)) ; Throw error to channel
        (println (.getMessage e))
        (.printStackTrace e)))))
