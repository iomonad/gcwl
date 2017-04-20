;; Filename: plugins.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 04/20/2017 Thursday 21:00:09
;; Description: Plugins dsl to build commands

(ns gcwl.plugins
  (:require [gcwl.plugins.ping :as ping]
            [gcwl.plugins.date :as date]
            [gcwl.plugins.echo :as echo]
            [gcwl.plugins.auth :as auth]
            [gcwl.plugins.about :as about]))

(defmacro defplugin [cmd & fn]
  "Macro to create a plugin"
  '(def plugin {:command cmd
                :commands {cmd (fn [irc message] ~fn)}}))
;; Pass 
(def plugins-enabled (atom [ping/plugin
                            date/plugin
                            echo/plugin
                            auth/plugin
                            about/plugin]))

(defn list-plugins []
  "List enabled plugins to *out*"
  (doseq [p @plugins-enabled]
    (println (format "[*] Enabled plugin: %s" p))))
