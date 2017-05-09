;; Filename: man.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/09/2017 Tuesday 22:34:35
;; Description: 

(ns salmon.plugins.man
  (:require [salmon.parse :as parse]
            [clojure.string :as str]))



(defn fn-man [irc message]
  (let [plugins (:plugins @irc)]
    (if-let [[command _] (parse/extract-word message)]
      (or (->> plugins (map #(get % :desc)) (remove nil?)
                                        ;first ;TODO: Predicate need to bee fixed
               ))
      (str "What manual page do you want? Please use help command."
           ))))


(def plugin {:name "man"
             :desc "Show command documention"
             :commands {"man" fn-man}})
