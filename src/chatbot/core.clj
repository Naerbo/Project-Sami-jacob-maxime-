
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

(def bertramka {"wc": true,
     "attractions": "cultural monument, classical music concerts, social events, W. A. ​​Mozart Museum",
     "biking": true,
     "skating": false,
     "sports": false,
     "playground": false,
     "transportation": "trams No. 4, 7, 9, 10, 58, 59.",
     "parking": true})

(def frantiskanska-zahrada {"wc": true,
    "attractions": "Church of Our Lady of the Snows",
    "biking": true,
    "skating": false,
    "sports": false,
    "playground": true,
    "dogs": false,
    "transportation": "Václavské náměstí trams. No. 3, 9, 14, 24, 51, 52, 54, 55, 56, 58, metro A, B Můstek",
    "parking": false})

(def obora-hvezda {"wc": true,
    "attractions": "memorial trees, nature trail, nature attractions",
    "biking": true,
    "skiing": true,
    "skating": false,
    "sports": false,
    "playground": true,
    "dogs": true,
    "transportation": "Nad Markétou, bus no. 179, 184, 191, 510, Libocká - bus no. 179, 510, Vypich, tram no. 15, 22, 25, 57",
    "parking": true})

(def kampa {"wc": true,
    "attractions": "view of the Vltava, Sovovy Mlýny Gallery, Čertovka, Charles Bridge, Werich House, memorial trees.",
    "biking": true,
    "skating": true,
    "sports": false,
    "playground": true,
    "transportation": "Hellichova tram no. 12, 20, 22, 23, 57, Újezd ​​tram no. 6, 9, 22, 23, 57, 58, 59",
    "parking": false})

(def kinskeho-zahrada {"wc": true,
    "attractions": "museum,ornamental lakes, natural attraction - Petrin Rocks.",
    "biking": false,
    "skating": false,
    "sports": false,
    "playground": true,
    "transportation": "Kobrova - BUS No. 176",
    "parking": true})

(def klamovka {"wc": true,
    "attractions": "art gallery, historical attractions, music club, garden restaurant.",
    "biking": true,
    "skating": false,
    "sports": true,
    "dogs": true,
    "playground": true,
    "transportation": "Klamovka - BUS No. 149, 191, 217, tram No. 4, 7, 9, 10, 58, 59",
    "parking": false,
    "restaurant": true})

(def ladronka {"wc": true,
    "attractions": "extensive leisure area, music and entertainment events",
    "biking": true,
    "skating": true,
    "sports": false,
    "playground": true,
    "dogs": true,
    "transportation": "U Ladronky, Rozýnova, Štefkova BUS No. 191, Vypich BUS No. 108, 174, 180, 191, I3, tram No. 15, 22, 25, 57",
    "parking": true,
    "restaurant": true})

(def letna {"wc": true,
    "attractions": "extensive leisure area, cultural events",
    "biking": true,
    "skating": true,
    "sports": "777 789 140",
    "playground": true,
    "dogs": true,
    "transportation": "Letenské náměstí and Sparta tram no. 1, 8, 15, 25, 26, 51, 56, Čechův most tram no. 12, 17, 53, Chotkovy sady tram no. 18, 20, 57, metro Hradčanská",
    "parking": true,
    "restaurant": true})

(def petrin {"wc": true,
    "attractions": "lookout tower, maze, observatory, underground galleries and fruit orchards",
    "biking": true,
    "skating": true,
    "sports": false,
    "playground": true,
    "transportation": "Újezd ​​tram no. 6, 9, 12, 20, 22, 23, 57, 58, 59, Hellichova tram no. 12, 20, 22, 23, 57, Pohořelec tram no. 22, 23, Dormitories Strahov BUS No. 143, 149, 217",
    "parking": true,
    "restaurant": true})

(def riegrovy-sady {"wc": true,
    "attractions": "view of Prague, classicist lookout tower, three-sided obelisk",
    "biking": true,
    "skating": true,
    "sports": true,
    "playground": true,
    "dogs": true,
    "transportation": "Metro Jiřího z Poděbrad, Na Smetance Bus no. 135, Italská and Vinohradská tržnice tram no. 11",
    "parking": false,
    "restaurant": true})

(def vysehrad {"wc": true,
    "attractions": "sightseeing, museum, cemetery of famous personalities Slavín, national cultural monument",
    "biking": true,
    "skating": false,
    "sports": true,
    "playground": true,
    "transportation": "Vyšehrad - metro C, Podolská vodárna tram no. 3, 16, 17, 21, 52",
    "parking": true})



(def parks{"stromovka" stromovka "bertramka" bertramka "frantiskanska-zahrada" frantiskanska-zahrada "obora-hvezda" obora-hvezda "kampa" kampa "kinskeho-zahrada" kinskeho-zahrada "klamovka" klamovka "ladronka" ladronka "letna" letna "petrin" petrin "riegrovy-sady" riegrovy-sady "vysehrad" vysehrad})

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
            (println "Sorry, I have no information about it:" A))))
      (swap! counter inc))))

(defn -main [& args]

; user greeting part
  (let [username (read-line-with-prompt "Hello. What is your name? ")]
    (if (= (some #(= username %) users) true)
      (println "Welcome back " username "! How are you?")
      (println "Welcome " username "! How are you?")))

; parsing user input and providing them with answers
  (parsing-questions (select-park parks)))

