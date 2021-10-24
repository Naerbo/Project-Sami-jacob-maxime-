(defproject chatbot "0.1.0-SNAPSHOT" ;; 1
  :description "This robot will be useful for human"
  ;; The remote repository location in the internets:
  :url "https://moodle.praguecollege.cz/mod/assign/view.php?id=38425"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/%22%7D%22%7D" }
:dependencies [[org.clojure/clojure "1.10.3"]]
:main ^:skip-aot chatbot.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})