;; Filename: auth.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 04/21/2017 Friday 23:36:09
;; Description: Auth user to admin atom

(ns gcwl.plugins.auth
  (:require [gcwl.parse     :refer [extract-word handleerr admin?]]
            [clojure.string :as string]))

(def ^:dynamic *pass* "woot")

(defn fn-auth [irc message]
  (if-let [[pass full] (extract-word message)]
    (let [nick (get-in full [:nick])]
      (try (if (admin? nick) ; Check if user is already admin
             (if (= pass *pass*)
               (do (swap! gcwl.bot/admin conj nick)
                   (str (format "Success, you are now administrator.")))
               (format "Sorry %s, you entered an incorect password." nick))
             (format "Hey %s, you are already administrator" nick))
           (catch Exception e
             (handleerr e))))))

(def plugin {:commands { "auth" fn-auth}})
