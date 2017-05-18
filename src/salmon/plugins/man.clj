;; Filename: man.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/18/2017 Thursday 21:36:44
;; Description: 

(ns salmon.plugins.man
  (:require [salmon.parse :as parse]
            [clojure.string :as str]))

(defn fn-man [irc message]
  (let [plugins (:plugins @irc)]
    (if-let [[command _] (parse/extract-word message)]
      (or (->> plugins (map #(get-in % [:desc command])) (remove nil?)
               (first)
               (format "%s (1) - %s" command)))
      (str "What manual page do you want? Please use help command."
           ))))

(def plugin {:name "man"
             :desc {"man" "Show command documention"}
             :commands {"man" fn-man}})
