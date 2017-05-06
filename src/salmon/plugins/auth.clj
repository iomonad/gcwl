;; Filename: auth.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/06/2017 Saturday 23:01:17
;; Description: Auth user to admin atom

(ns salmon.plugins.auth
  (:require [salmon.parse     :refer [extract-word handleerr admin? push-admin argerr]]
            [clojure.string :as string]))

(def ^:dynamic *pass* "woot")

(defn fn-auth [irc message]
  (if-let [[pass full] (extract-word message)]
    (let [nick (get-in full [:nick])]
      (try (if (admin? nick) ; Check if user is already admin
             (if (= pass *pass*)
               (do (push-admin nick)
                   (str (format "Success, you are now administrator.")))
               (format "Sorry %s, you entered an incorect password." nick))
             (format "Hey %s, you are already administrator" nick))
           (catch Exception e
             (handleerr e))))))

(def plugin {:commands { "auth" fn-auth}})
