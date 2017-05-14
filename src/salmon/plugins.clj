;; Filename: plugins.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/14/2017 Sunday 11:51:41
;; Description: Plugins dsl to build commands

(ns salmon.plugins
  (:require [salmon.plugins.ping :as ping]
            [salmon.plugins.date :as date]
            [salmon.plugins.man :as man]
            [salmon.plugins.urban :as urban]
            [salmon.plugins.manage :as manage]
            [salmon.plugins.help :as help])
  (:gen-class))

;; Atom that contains enables plugins
(def plugins-enabled (atom [ping/plugin
                            date/plugin
                            man/plugin
                            urban/plugin
                            manage/plugin
                            help/plugin]))

(defn list-plugins []
  "List enabled plugins to *out*"
  (doseq [p @plugins-enabled]
    (println (format "[*] Enabled plugin: %s" p))))
