(ns salmon.core-test
  (:require [clojure.test :refer :all]
            [salmon.parse :as parse]))

(deftest some-foo-test
  (testing "Make sure test suite works"
    (is (= 1 1))))


;; Parsing.clj
(def rawchunk ":iomonad!~iomonad@wow.such.vhost PRIVMSG #bottest :Smoked Fluffy Salmon @@t3st!!")

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
