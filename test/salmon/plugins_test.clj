(ns salmon.plugins-test
  (:require [clojure.test :refer :all]
            [salmon.parse :as parse]
            [salmon.plugins.urban :as urban]))

(def command-chunk ":iomonad!~iomonad@wow.such.vhost PRIVMSG #bottest :.urban pleb")

(deftest test-urban-query
  (testing "Check if the request is correct"
    (= (str "fuy")
       ;;TODO: (urban/fn-urban (nth (parse/raw->args chunk) 2))
       "fuy"
       )))
