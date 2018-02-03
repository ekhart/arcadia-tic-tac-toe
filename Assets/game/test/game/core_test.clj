(ns game.core-test
  (:require [clojure.test :refer :all]
            [game.core :refer :all]
            [clojure.spec :as s])
  (use arcadia.core))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))

; (s/valid? ::game-object? manager) =>
; (s/valid? :game.core/game-object? manager) ; works

(deftest spec-test
	(testing "specs"
		(is (true? (s/valid? :game.core/game-object? manager)))
		(is (true? (s/valid? :game.core/manager-state (state manager))))
		(is (true? (s/valid? :game.core/game-objects? buttons)))))

(defn is-equal [a b]
	(is (= a b)))

(deftest array-test
	(testing "get-array")
		(is-equal (get-array)
			[[nil nil nil]
			 [nil nil nil]
			 [nil nil nil]])
		(is-equal (get-array 
			nil nil nil
			:x nil nil
			nil nil nil)
			[[nil nil nil]
			 [:x nil nil]
			 [nil nil nil]]))

; run this to test
(run-tests)