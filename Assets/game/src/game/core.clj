(ns game.core
	(require [clojure.string :as str]
		[clojure.spec :as s])
	(use arcadia.core
		clojure.repl)
	(import [UnityEngine 
		GameObject
		Camera]
		[UnityEngine.UI Button Text]))

; https://clojure.org/guides/spec
; [clojure.spec.alpha :as s]) ; dont work
; [clojure.spec :as s]) ; this work

; clojure.repl for doc fun, but dont work ;/
; https://stackoverflow.com/questions/8332611/how-to-use-clojure-doc-function

(s/def ::game-object? #(= (type %) UnityEngine.GameObject))

(if-let [exist (object-named "Manager")]
	(def manager exist)
	(def manager (GameObject. "Manager")))

(defn get-array []
	(vec (map vec (to-array-2d (repeat 3 (repeat 3 nil))))))

; use unqualified keys
(s/def ::manager-state (s/keys :req-un [::array ::current-player]))

(state+ manager :array (get-array))
(state+ manager :current-player :X)

(defn update-array 
	([array] (update-array array 0 0 1))
	([array i j value]
		(let [row (nth array i)
					changed (assoc row j value)]
			(assoc array i changed))))


(defn set-current-player []
	(update-state manager :current-player #(if (= % :X) :O :X))
	(with-cmpt (object-named "Current Player") [text-cmpt Text]
		(set! (. text-cmpt text) 
			(str "Current Player: " (name (state manager :current-player))))))

(s/def ::game-objects? (s/coll-of ::game-object?))

(def buttons (children (object-named "Buttons")))

; (s/valid? ::game-objects? buttons) ; works

;; check if player win

(defn button-click [button event key]
	(let [current-player (state manager :current-player)
				i (state button :i)
				j (state button :j)]
		(update-state manager :array (fn [array]
			(update-array array i j current-player)))
		(let [button-text (.GetComponentInChildren button Text)]
			(set! (. button-text text) (name current-player))))
		(set-current-player))

(doseq [button buttons]
	(let [splitted-name (-> (.name button) (str/split #" "))
				[i j] (drop 1 splitted-name)]
		(state+ button :i (int i))
		(state+ button :j (int j)))
  (hook+ button 
  	:on-pointer-click 
  	:button-mouse-down 
  	#'button-click))


(defn camera-start [go key]
	(log "Hello"))

(hook+ Camera/main :start :camera-start #'camera-start)