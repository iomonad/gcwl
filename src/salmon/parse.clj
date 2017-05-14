(ns salmon.parse
  (:require [salmon.db :as db]
            [clojure.string :as str]))

;; Admin related
(def ^:private admin (atom ["iomonad"])) ;; Registred users

(defn push-admin [nick]
  "Add user to admin atom"
  (swap! admin conj (str nick)))

(defn admin? [nick]
  "Predicate to check if user is administrator"
  (.contains @admin nick))

;; Error helper
(defn handleerr [error]
  "Just avoiding formating usage"
  (str (format "Error: %s" error)))
(defn argerr [num]
  "Pass number of missing arguments"
  (str (format "Error: %s argument(s) requiered.")))

;; Trivial extraction
(defn extract-command [message]
  "Extract command from raw message"
  (if-let [[_ cmd rest-of-text]
           (re-find #"^[%](\S+)\s*(.*)$" (:text message))]
    [cmd (assoc message :text rest-of-text
                :command cmd)]))

(defn extract-word [message]
  "Extract arguments from raw message"
  (if-let [[_ word rest-of-text]
           (re-find #"^(\S+)\s*(.*)$" (:text message))]
    [word (assoc message :text rest-of-text)]))

;; From raw to value
(defn raw->nick [raw]
  "Extract nick from raw message"
  (if-let [[_ _ nick] (re-find #"(.*?):(.*?)!(.*?)" raw)]
    (str nick)))

(defn raw->chan [raw]
  "Extract channel from raw message"
  (let [chan (nth (re-find #"(#\S+)" raw) 1)]
    (str chan)))

(defn raw->msg [raw]
  "Extract message from raw message"
  (let [rmsg (str/split raw #" ")
        msg (str/join " " (nthnext rmsg 3))]
    (str (str/trim (str/replace-first (str msg) #":" "")))))

(defn raw->args [raw]
  "Parse argument from raw message
   and return vec of arg, usable with nth"
  (if-let [chunk (str/lower-case (raw->msg raw))]
    (str/split chunk #" ")))

(defn mongo-callback  [_ type s]
  "Logs ans store buffer activities to mongodb"  
  (let [nick (raw->nick s)
        chan (raw->chan s)
        msg  (raw->msg  s)]
    (if-not (nil? nick) ; Don't care about server reply
      (if-not (str/blank? chan) ; Don't care about server query
        (if-not (str/blank? msg) ; Avoid JOIN and PART
          (db/salmon-buffer-logs nick ; User nick
                                 type ; In or Out
                                 chan ; Message channel
                                 msg  ; Message
                                 s) ; Raw structure
          )))))
