(ns gcwl.plugins.ping
  "Sends a PING back given .pong")

(def plugin {:commands {"ping" (fn [irc message] "pong")}})
