;; Filename: plugins.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/08/2017 Monday 11:07:57
;; Description: Plugins dsl to build commands

(ns salmon.plugins
  (:require [salmon.plugins.ping :as ping]
            [salmon.plugins.date :as date]
            [salmon.plugins.auth :as auth]
            [salmon.plugins.about :as about]
            [salmon.plugins.help :as help]))

;; Atom that contains enables plugins
(def plugins-enabled (atom [ping/plugin
                            date/plugin
                            auth/plugin
                            about/plugin
                            help/plugin]))

(defn list-plugins []
  "List enabled plugins to *out*"
  (doseq [p @plugins-enabled]
    (println (format "[*] Enabled plugin: %s" p))))
