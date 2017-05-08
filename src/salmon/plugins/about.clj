;; Filename: about.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/08/2017 Monday 10:56:47
;; Description: Bot description

(ns salmon.plugins.about
  (:require [salmon.parse     :refer [extract-word handleerr]]
            [clojure.string :as string]))

(def plugin {:name "about"
             :desc "Show informations about the bot"
             :commands {"about" (fn [irc message] "Fork me @ https://git.io/v9vqN")}})
