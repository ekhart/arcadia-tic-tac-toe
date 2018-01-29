(ns game.core
	(use arcadia.core)
	(import [UnityEngine 
		GameObject
		Camera]
		[UnityEngine.UI Button Text]))


(def manager (GameObject. "Manager"))

(defn get-array []
	(vec (map vec (to-array-2d (repeat 3 (repeat 3 nil))))))

(state+ manager :array (get-array))

(defn update-array [array]
	(let [i 0
				j 0
				value 1
				row (nth array i)
				changed (assoc row j value)]
		(assoc array i changed)))

(update-state manager :array update-array)


(def button (object-named "Button"))

(defn button-click [go event key]
	(log go)
	(log event)
	(log key))

;; not do this
; (with-cmpt button [button-cmpt Button] 
; 	(.AddListener (.onClick button-cmpt) button-click))
; (.. button-cmpt onClick (AddListener button-click))
; (.. (.GetComponentInChildren button Text) text)

;; this dont work
; (hook+ button :on-mouse-down :button-mouse-down #'button-click)
; (hook+ button :on-mouse-enter :button-mouse-down #'button-click)

;; this works
(hook+ button :on-pointer-click :button-mouse-down #'button-click)



(defn camera-start [go key]
	(log "Hello")

	
	)

(hook+ Camera/main :start :camera-start #'camera-start)