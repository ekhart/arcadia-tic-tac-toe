(ns game.test-util
  (require 	[clojure.test :refer :all]
  					[clojure.spec :as s])
  (use arcadia.core))


(defn is-valid? [spec value]
	(is (true? (s/valid? spec value))))

(defn is-not-valid? [spec value]
	(is (false? (s/valid? spec value))))

(defn is-equal [a b]
	(is (= a b)))