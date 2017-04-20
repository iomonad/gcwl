;; Filename: auth.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 04/20/2017 Thursday 22:19:39
;; Description: Auth user to admin atom

(ns gcwl.plugins.auth
  (:require [gcwl.parse     :refer [extract-word handleerr]]
            [clojure.string :as string]))

(def admin (atom ()))
(def ^:dynamic *pass* "woot")

(defn fn-auth [irc message]
  (if-let [[pass full] (extract-word message)]
    (let [nick (get-in full [:nick])]
      (if (= pass *pass*)
        (do
          (str (format "Success, you are now administrator.")))
        (format "Sorry %s, you entered an incorect password." nick)
        )
      )
    )
  )

(def plugin {:commands { "auth" fn-auth}})
