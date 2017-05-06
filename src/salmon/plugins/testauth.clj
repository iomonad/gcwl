;; Filename: testauth.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 05/06/2017 Saturday 22:06:46
;; Description:  Check if user is administrator

(ns salmon.plugins.testauth
  (:require [salmon.parse     :refer [extract-word handleerr admin?]]
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
