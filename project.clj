(defproject salmon "0.1.4"
  :description "Salmon is my personnal irc bot, written in Clojure"
  :url "https://github.com/iomonad/salmon"
  :license {:name "Do What the Fuck You Want to Public License"
            :url "http://www.wtfpl.net/"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [irclj "0.5.0-alpha4"]
                 [cheshire "5.8.0"]
                 [enlive "1.1.6"]
                 [com.novemberain/monger "3.1.0"]
                 [http-kit "2.3.0"]
                 [enlive "1.1.6"]]
  :scm {:name "git"
        :url  "https://github.com/iomonad/salmon"}
  :plugins [[lein-cloverage "1.0.10"]
            [lein-shell "0.5.0"]]
  :main ^:skip-aot salmon.core
  :aliases {"graphdeps" ["vizdeps" "-o" "resources/dependencies.png"]}
  :profiles {:coveralls {:aliases {"coveralls" ["do" "cloverage" "--coveralls,"
                                                "shell" "curl" "-F"
                                                "json_file=@target/coverage/coveralls.json"
                                                "https://coveralls.io/api/v1/jobs"]}}
             :uberjar {:aot [salmon.core]
                       :jar-name "salmon.jar"
                       :uberjar-name "salmon-standalone.jar"
                       :uberjar-exclusions [#"META-INF/DUMMY.SF"]}
             :graph {:hiera {:path "resources/ns-hierarchy.png"
                             :vertical true
                             :show-external true
                             :cluster-depth 1
                             :trim-ns-prefix true
                             :ignore-ns #{}}}})
