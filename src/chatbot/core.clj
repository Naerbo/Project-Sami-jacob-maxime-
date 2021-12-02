
(ns chatbot.core)

; data

(def stromovka {"hours" "Open all year around"
                "playground" "yes there are playground allowed to play"
                "refreshments" "yes there are refreshments nearby"
                "food" "No"
                "dogs" "yes pets are allowed at the park"
                "biking" "yes biking is allowed"
                "skating" "yes skating is allowed"
                "toilet" "yes there are public toilet"
                "attractions" "zoo, botanical gardens, natural attractions, planetarium, exhibition grounds"})

(def parks{"stromovka" stromovka})

(def users ["Jeremy" "Sami" "Maxim"])

; helper functions 
(defn read-line-with-prompt [prompt]
  (println prompt)
  (read-line))

(defn exit-now [word]
  (if (or (= word "end") (= word "bye"))
    (System/exit 0)))

(defn remove-punctuation [word]
  (clojure.string/replace word #"(?i)" " ")
  (clojure.string/lower-case word))

; main/answering user questions
(defn select-park [parks]
  (parks (remove-punctuation (read-line-with-prompt "I've information about Stromovka."))))

(defn parsing-questions [park]
  (def counter (atom 1))
  (doseq [line (repeatedly #(read-line-with-prompt "What do you want to know about the park? ")) :while line]
    (exit-now line)
    (reset! counter 1)
    (doseq [A (clojure.string/split (remove-punctuation line) #" ")]
      (let [result (park A)]
        (if result
          (println "For" A ":" result))
        (if (= @counter (count (clojure.string/split (remove-punctuation line) #" ")))
          (if-not result
            (println "no" A))))
      (swap! counter inc))))

(defn -main [& args]

; user greeting part
  (let [username (read-line-with-prompt "Hello. What is your name? ")]
    (if (= (some #(= username %) users) true)
      (println "Welcome back " username "! How are you?")
      (println "Welcome " username "! How are you?")))

; parsing user input and providing them with answers
  (parsing-questions (select-park parks)))

