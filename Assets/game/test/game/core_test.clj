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

(defn is-valid? [spec value]
	(is (true? (s/valid? spec value))))

(deftest spec-test
	(testing "specs"
		(is-valid? :game.core/game-object manager)
		(is-valid? :game.core/manager-state (state manager))
		(is-valid? :game.core/game-objects buttons))
	(testing "array-specs"
		(is-valid? :game.core/array-state nil)
		(is-valid? :game.core/array-state :X)
		(is-valid? :game.core/array-state :O)))

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

(deftest win-condition-test
	(testing "win-condition"
		(is (true? (game-win (get-array))))))

; run this to test
(run-tests)