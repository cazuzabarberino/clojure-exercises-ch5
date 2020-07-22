(ns clojure-exercices-ch5.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


(defn sum
  ([vals] (sum vals 0))
  ([vals acumulated-val]
   (if (empty? vals)
     acumulated-val
     (recur (rest vals) (+ acumulated-val (first vals))))))

(sum [8 8 9])

(defn two-comp
  [f g]
  (fn [& args]
    (f (apply g args))))

(def two-comp-res (two-comp inc +))

(two-comp-res 2 5)

(defn mult-comp
  [& funcs]
  (fn [& args]
    (loop [[f & remaining-funcs] (reverse funcs) new-arg args]
      (let [result (if (seq? new-arg) (apply f new-arg) (f new-arg))]
        (if (empty? remaining-funcs)
          result
          (recur remaining-funcs result))))))

(def mult-comp-res (mult-comp inc inc #(/ % 2.0) +))

(mult-comp-res 2 5)

