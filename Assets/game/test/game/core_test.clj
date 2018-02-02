(ns game.core-test
  (:require [clojure.test :refer :all]
            [game.core :refer :all]
            [clojure.spec :as s]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))

; (s/valid? ::/game-object? manager) =>
; (s/valid? :game.core/game-object? manager) ; works

(deftest spec-test
	(testing "specs"
		(is (true? (s/valid? :game.core/game-object? manager)))))

; run this to test
(run-tests)