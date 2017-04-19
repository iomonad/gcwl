(ns gcwl.core-test
  (:require [clojure.test :refer :all]
            [gcwl.parse :as parse]))

(deftest some-foo-test
  (testing "Make sure test suite works"
    (is (= 1 1))))

(deftest get-user-command
  (testing "Extract user command from irc string"
    (is (= (parse/extract-command {:text ".test this test"})
           ["test" "this" "test"]))))
