(defproject gcwl "0.1.0"
  :description "Gcwl is my personnal irc bot, written in Clojure"
  :url "https://github.com/iomonad/gcwl"
  :license {:name "Do What the Fuck You Want to Public License"
            :url "http://www.wtfpl.net/"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [irclj "0.5.0-alpha4"]
                 [cheshire "5.7.0"]
                 [enlive "1.1.6"]
                 [http-kit "2.1.18"]]
  :main ^:skip-aot gcwl.core
  :profiles {:uberjar {:aot [gcwl.core]}})
