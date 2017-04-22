;; Filename: kernel.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 04/22/2017 Saturday 23:17:07
;; Description: Get latest kernel version

(ns gcwl.plugins.kernel
  (:require [gcwl.parse     :refer [extract-word handleerr admin? push-admin]]
            [clojure.string :as string]
            [net.cgrand.enlive-html :as html]))


(defn fetch-url [url]
  "Small generic function to call http connection stream"
  (html/html-resource (java.net.URL. url)))

(defn grep-kernel-version []
  "Grep the value of the latest version in htnl"
  (map html/text
       (html/select (fetch-url "https://www.kernel.org/")
                    #{[:td.latest_link]})))

(defn fn-kernel [irc message]
  (try
    (let [raw (grep-kernel-version)
          v (apply str raw)]
      (format "Latest kernel version: %s" v))    
    (catch Exception e
      (handleerr e))))

(def plugin {:commands {"kernel" fn-kernel}})
