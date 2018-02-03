(ns game.core-test
  (require [clojure.test :refer :all]
           [clojure.spec :as s]
           [game.core :refer :all]
           [game.spec :refer :all]
           [game.test-util :refer :all])
  (use arcadia.core))


(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))

(deftest spec-test1
	(testing "specs"
		(is-valid? :game.spec/game-object manager)
		(is-valid? :game.spec/manager-state (state manager))
		(is-valid? :game.spec/game-objects buttons))
	(testing "array-specs"
		(is-valid? :game.spec/player :X)
		(is-valid? :game.spec/player :O)
		(is-not-valid? :game.spec/player nil)
		(is-valid? :game.spec/array-state nil)
		(is-valid? :game.spec/array-state :X)
		(is-valid? :game.spec/array-state :O))
		(is-valid? :game.spec/array-row [nil nil nil])
		(is-valid? :game.spec/array-row [nil :X nil])
		(is-valid? :game.spec/array-row [nil nil :O])
		(is-valid? :game.spec/array-row [nil :X :O])
		(is-not-valid? :game.spec/array-row [nil nil])
		(is-not-valid? :game.spec/array-row [nil])
		(is-not-valid? :game.spec/array-row [])

		(is-valid? :game.spec/array (get-array))
		(is-valid? :game.spec/array (get-array 
			nil nil nil
			:X nil nil
			nil nil nil)))

(deftest array-test
	(testing "get-array")
		(is-equal (get-array)
			[[nil nil nil]
			 [nil nil nil]
			 [nil nil nil]])
		(is-equal (get-array 
			nil nil nil
			:X nil nil
			nil nil nil)
			[[nil nil nil]
			 [:X nil nil]
			 [nil nil nil]]))

(deftest win-condition-test
	(testing "win-condition"
		(is (true? (game-win (get-array))))))

; run this to test
(run-tests)