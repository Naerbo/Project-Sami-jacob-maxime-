
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


(ns chatbot.core)
(defn prompt-with-numbers
  [msg & selectables]
  (when-not selectables
    (throw (Exception. "You need selectables")))
  (let [rng (range (count selectables))
        mp (zipmap rng selectables)
        prompt (format "%s%n%s"
                       msg
                       (apply str (for [[k v] mp]
                                    (format "[%d] %s%n" k v))))
        response (try (Integer/parseInt (do (println prompt)
                                            (read-line)))
                      (catch Exception e))]
    (if (contains? mp response)
      (first {response (mp response)})
      (recur msg selectables))))

(defn bird-tree []
  (case (first (prompt-with-numbers "What color are the birds feathers?" "Black" "White"))
    0 (println "You probably saw a raven!")
    1 (println "You probably saw an owl!")))

(defn direction-tree []
  (case (first (prompt-with-numbers "Where do you want to go?" "North" "East" "South" "West"))
    0 (println "Here's what's to the north...")
    1 (println "Here's what's to the east...")
    2 (println "Here's what's to the south...")
    3 (println "Here's what's to the west...")))

(defn -main []
  (while :forever
    (case (first (prompt-with-numbers "What do you need information about?" "birds" "directions" "quit"))
      0 (bird-tree)
      1 (direction-tree)
      2 (System/exit 0))))
