;; Filename: kernel.clj
;; Copyright (c) 2008-2017 Clement Trösa <iomonad@riseup.net>
;; 
;; Last-Updated: 04/22/2017 Saturday 22:18:33
;; Description: Get latest kernel version

(ns gcwl.plugins.kernel
  (:require [gcwl.parse     :refer [extract-word handleerr admin? push-admin]]
            [clojure.string :as string]
            [net.cgrand.enlive-html :as html]
            [clj-http.client :as client]))

(defn fn-kernel [irc message]
  (try
    (let [raw (html/html-snippet
               (:body @(client/get "https://www.kernel.org/")))
          kernel-version (html/select raw  #{[:td.latest_link :a]})]
      (format "Latest kernel version: %s" kernel-version))
    (catch Exception e
      (handleerr e))))

(def plugin {:commands {"kernel" fn-kernel}})
