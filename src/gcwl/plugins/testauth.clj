;; Filename: testauth.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 04/21/2017 Friday 23:18:22
;; Description:  Check if user is administrator

(ns gcwl.plugins.testauth
  (:require [gcwl.parse     :refer [extract-word handleerr admin?]]
            [clojure.string :as string]))

(defn test-auth [irc message]
  (if-let [[_ full] (extract-word message)]
    (let [nick (get-in full [:nick])]
      (try (if (admin? nick)
             (str "You are administrator")
             (str "You are a simple user"))
           (catch Exception e
             (handleerr e))))))

(def plugin {:commands { "authtest" test-auth}})
