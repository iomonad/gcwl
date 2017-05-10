(ns salmon.plugins.ping)

(def plugin {:name "ping"
             :desc {"ping" "Respond to ping request"}
             :commands {"ping" (fn [irc message] (let [nick (get message :nick)]
                                                   (format "%s: pong" nick)))}})
