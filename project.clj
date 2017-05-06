(defproject salmon "0.1.3"
  :description "Salmon is my personnal irc bot, written in Clojure"
  :url "https://github.com/iomonad/salmon"
  :license {:name "Do What the Fuck You Want to Public License"
            :url "http://www.wtfpl.net/"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [irclj "0.5.0-alpha4"]
                 [cheshire "5.7.1"]
                 [enlive "1.1.6"]
                 [com.novemberain/monger "3.1.0"]
                 [http-kit "2.2.0"]]
  :main ^:skip-aot salmon.core
  :profiles {:uberjar {:aot [salmon.core]}})
