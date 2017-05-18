(ns salmon.plugins-test
  (:require [clojure.test :refer :all]
            [salmon.parse :as parse]
            [salmon.plugins.urban :as urban]
            [salmon.plugins.help :as help]
            [salmon.plugins.date :as date]
            [salmon.plugins.man :as man]
            [salmon.plugins.ping :as ping]))

(deftest test-urban-query
  (testing "Check if the request is correct"
    (let [urban-chunk ":iomonad!~iomonad@wow.such.vhost PRIVMSG #bottest :.urban pleb"]
      (= (str "fuy")
         ;;TODO: (urban/fn-urban (nth (parse/raw->args chunk) 2))
         "fuy"
         ))))

(deftest test-ping-request
  (testing "Check if ping acces is correct"
    (let [ping-chunk ":iomonad!~iomonad@wow.such.vhost PRIVMSG #bottest :.ping"]
      (= "iomonad: pong"
         (ping/fn-ping nil ping-chunk)))))

(deftest test-help-request
  (testing "Check if help access is correct"    
    (let [local (atom {:plugins {:name "test"
                                 :desc{"test" "test"}}})
          ping-chunk ":iomonad!~iomonad@wow.such.vhost PRIVMSG #bottest :.help"]
      (= "Available commands: help"
         (help/fn-help local nil)))))

(deftest test-date-request
  (testing "Check if data access is correct"
    (let [date (new java.util.Date)
          date-chunk ":iomonad!~iomonad@wow.such.vhost PRIVMSG #bottest :.date"]
      (= (format "Current date: %s" date)
         (date/return-date nil date-chunk)))))

;; (deftest test-man-request
;;   (testing "Check if man is correctly parsed"
;;     (let [local (atom {:plugins {:name "test"
;;                                  :desc{"test" "some foo description"}}})
;;           man-chunk ":iomonad!~iomonad@wow.such.vhost PRIVMSG #bottest :.man test"
;;           man-chunk-buggy ":iomonad!~iomonad@wow.such.vhost PRIVMSG #bottest :.man break"]
;;       (= "test (1) - some foo description"
;;          (man/fn-man local man-chunk))
;;       (= "What manual page do you want? Please use help command."
;;          (man/fn-man local man-chunk-buggy)))))
;;       )))
