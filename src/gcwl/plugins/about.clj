;; Filename: about.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 04/20/2017 Thursday 20:59:37
;; Description: Bot description

(ns gcwl.plugins.about
  (:require [gcwl.parse     :refer [extract-word handleerr]]
            [clojure.string :as string]))

(def plugin {:commands {"about" (fn [irc message] "Check me at https://git.io/v9vqN")}})
