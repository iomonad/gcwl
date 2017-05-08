(ns salmon.plugins.ping)

(def plugin {:name "ping"
             :desc "Respond to ping request"
             :commands {"ping" (fn [irc message] "pong")}})
