;; Filename: about.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/06/2017 Saturday 22:08:04
;; Description: Bot description

(ns salmon.plugins.about
  (:require [salmon.parse     :refer [extract-word handleerr]]
            [clojure.string :as string]))

(def plugin {:commands {"about" (fn [irc message] "Check me at https://git.io/v9vqN")}})
