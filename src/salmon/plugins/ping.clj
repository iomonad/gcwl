(ns salmon.plugins.ping)

(defn fn-ping [irc message]
  (let [nick (get message :nick)]
    (format "%s: pong" nick)))

(def plugin {:name "ping"
             :desc {"ping" "Respond to ping request"}
             :commands {"ping" fn-ping}})
