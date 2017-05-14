(ns salmon.parse-test
  (:require [clojure.test :refer :all]
            [salmon.parse :as parse]))

(deftest push-admin-test
  (testing "Aministrator predicate test"
    (parse/push-admin "bakari")
    (is (= true
           (parse/admin? "bakari")))))

(def rawchunk ":iomonad!~iomonad@wow.such.vhost PRIVMSG #bottest :Smoked Fluffy Salmon @@t3st!!")
(def command-chunk ":iomonad!~iomonad@wow.such.vhost PRIVMSG #bottest :.image --set foo.png")
(deftest nick-parsing-test
  (testing "Make sure nick extraction is done"
    (is (= "iomonad"
           (parse/raw->nick rawchunk)))))
(deftest channel-parsing-test
  (testing "Make sure channel extraction is done"
    (is (= "#bottest"
           (parse/raw->chan rawchunk)))))
(deftest message-parsing-test
  (testing "Make sure message extraction is done"
    (is (= "Smoked Fluffy Salmon @@t3st!!"
           (parse/raw->msg rawchunk)))))
(deftest arg-parsing-test
  (testing "Make sure arguments are parsed"
    (is (= [".image" "--set" "foo.png"]
           (parse/raw->args command-chunk)))))

