;; Filename: about.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/09/2017 Tuesday 09:36:06
;; Description: Bot description

(ns salmon.plugins.about
  (:require [salmon.parse     :refer [extract-word handleerr]]
            [clojure.string :as string]))

(def plugin {:name "about"
             :desc "Show informations about the bot"
             :commands {"about" (fn [irc message] "[Salmon v.1.0.2] Fork me @ https://git.io/v9vqN")}})
