(ns game.core
	(require [clojure.string :as str])
	(use arcadia.core)
	(import [UnityEngine 
		GameObject
		Camera]
		[UnityEngine.UI Button Text]))


(if-let [exist (object-named "Manager")]
	(def manager exist)
	(def manager (GameObject. "Manager")))

(defn get-array []
	(vec (map vec (to-array-2d (repeat 3 (repeat 3 nil))))))

(state+ manager :array (get-array))
(state+ manager :current-player :x)

(defn update-array 
	([array] (update-array array 0 0 1))
	([array i j value]
		(let [row (nth array i)
					changed (assoc row j value)]
			(assoc array i changed))))

(update-array (get-array))
(update-array (get-array) 0 0 :x)

(update-state manager :array update-array)



(def buttons (children (object-named "Canvas")))

(defn button-click [button event key]
	(let [current-player (state manager :current-player)
				i (state button :i)
				j (state button :j)]
		(update-state manager :array (fn [array]
			(update-array array i j current-player)))
		(let [button-text (.GetComponentInChildren button Text)]
			(set! (. button-text text) (name current-player))))
		(update-state manager :current-player #(if (= % :X) :O :X)))

; (mod 4 3)
; (int (/ 4 3.0))

(name :x)

(doseq [button buttons]
	(let [splitted-name (-> (.name button) (str/split #" "))
				[i j] (drop 1 splitted-name)]
		(state+ button :i (int i))
		(state+ button :j (int j))))
; 
		
;; not do this
; (with-cmpt button [button-cmpt Button] 
; 	(.AddListener (.onClick button-cmpt) button-click))
; (.. button-cmpt onClick (AddListener button-click))
; (.. (.GetComponentInChildren button Text) text)

;; this dont work
; (hook+ button :on-mouse-down :button-mouse-down #'button-click)
; (hook+ button :on-mouse-enter :button-mouse-down #'button-click)

;; this works
(doseq [button buttons]
  (hook+ button 
  	:on-pointer-click 
  	:button-mouse-down 
  	#'button-click))




(defn camera-start [go key]
	(log "Hello")

	
	)

(hook+ Camera/main :start :camera-start #'camera-start)