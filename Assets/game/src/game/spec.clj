(ns game.spec
  (require  [clojure.spec :as s]
  					[game.core :refer :all])
  (import [UnityEngine GameObject]))


(s/def ::game-object #(= (type %) UnityEngine.GameObject))

(s/def ::player #{:X :O})

(s/def ::array-state (s/or 
	:none nil? 
	:player ::player))

(s/def ::array-row (s/coll-of ::array-state
	:kind vector?
	:count 3))

(s/def ::array (s/coll-of ::array-row
	:kind vector?
	:count 3))

; use unqualified keys
(s/def ::manager-state (s/keys :req-un [::array ::current-player]))

(s/def ::game-objects (s/coll-of ::game-object))

(s/fdef get-array 
	:ret ::array)