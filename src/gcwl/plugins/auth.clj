;; Filename: auth.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 04/20/2017 Thursday 20:53:18
;; Description: Auth user to admin atom

(ns gcwl.plugins.auth
  (:require [gcwl.parse     :refer [extract-word handleerr]]
            [clojure.string :as string]))

(def admin (atom ["iomonad"]))
(def ^:dynamic *pass* "woot")

(defn fn-auth [irc message]
  (if-let [[pass full] (extract-word message)]
    (try
      (if (= pass *pass*)
        (do
          (swap! admin (get-in full :nick))
          (str "Yeah dude you are root"))        
        (format " Wrong password")
        )
      (catch Exception e
        (handleerr e)
        ))
    )
  )

(def plugin {:commands { "auth" fn-auth}})
