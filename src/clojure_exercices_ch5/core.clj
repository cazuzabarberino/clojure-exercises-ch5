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

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})

(defn get-attr
  [c attr]
  (attr (:attributes c)))

(get-attr character :intelligence)

(def attr (comp #(%2 (:attributes %1))))

(attr character :intelligence)

(defn my-assoc-in
  [m [k & ks] val]
  (assoc m k (if (empty? ks)
               val
               (my-assoc-in (get m k) ks val))))

(defn my-update-in
  [m [k & ks] f & args]
  (assoc m k (if (empty? ks)
               (apply f (get m k) args)
               (apply my-update-in (get m k) ks f args))))

(def users [{:name "James" :age 26}  {:name "John" :age 43}])

(assoc-in users [1 :age] 44)
(my-assoc-in users [1 :age] 44)
(assoc users 1 (assoc (get users 1) :age 44))

(update-in users [1 :age] + 10)
(my-update-in users [1 :age] inc)

