;; Filename: plugins.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/06/2017 Saturday 22:03:24
;; Description: Plugins dsl to build commands

(ns salmon.plugins
  (:require [salmon.plugins.ping :as ping]
            [salmon.plugins.date :as date]
            [salmon.plugins.echo :as echo]
            [salmon.plugins.auth :as auth]
            [salmon.plugins.about :as about]
            [salmon.plugins.testauth :as testauth]
            [salmon.plugins.kernel :as kernel]))

;; Atom that contains enables plugins
(def plugins-enabled (atom [ping/plugin
                            date/plugin
                            echo/plugin
                            auth/plugin
                            about/plugin
                            testauth/plugin
                            kernel/plugin]))

(defn list-plugins []
  "List enabled plugins to *out*"
  (doseq [p @plugins-enabled]
    (println (format "[*] Enabled plugin: %s" p))))
