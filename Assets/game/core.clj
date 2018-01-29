(ns game.core
	(use arcadia.core)
	(import [UnityEngine 
		GameObject
		Camera]))


(defn camera-start [go key]
	(log "Hello")

	(def manager (GameObject. "Manager")))

(hook+ Camera/main :start :camera-start #'camera-start)