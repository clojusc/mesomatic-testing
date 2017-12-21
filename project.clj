(defproject clojusc/mesomatic-testing "0.1.0-SNAPSHOT"
  :description "A system and integration testing utility library for Mesomatic"
  :url "https://github.com/clojusc/mesomatic-testing"
  :license {
    :name "Apache License, Version 2.0"
    :url "https://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [
    [org.clojure/clojure "1.8.0"]]
  :profiles {
    :dev {
      :source-paths ["dev-resources/src"]
    }
    :ubercompile {
      :aot :all}
    :custom-repl {
      :repl-options {
        :init-ns mesomatic.testing.dev
        :prompt ~#(str "\u001B[35m[\u001B[34m"
                       %
                       "\u001B[35m]\u001B[33m λ\u001B[m=> ")}}
    :test {
      :exclusions [org.clojure/clojure]
      :dependencies [
        [clojusc/ltest "0.3.0-SNAPSHOT"]]
      :plugins [
        [jonase/eastwood "0.2.5"]
        [lein-ancient "0.6.15"]
        [lein-bikeshed "0.5.0"]
        [lein-kibit "0.1.6"]
        [lein-ltest "0.3.0-SNAPSHOT"]
        [venantius/yagni "0.1.4"]]}}
  :aliases {
    "repl" ["with-profile" "+custom-repl,+test" "repl"]
    "ubercompile" ["with-profile" "+ubercompile" "compile"]
    "check-deps" ["with-profile" "+test" "ancient" "check" ":all"]
    "lint" ["with-profile" "+test" "kibit"]
    "test" ["with-profile" "+test" "ltest"]
    "build-check" ["with-profile" "+test" "do"
      ;["check-deps"]
      ["lint"]
      ["ubercompile"]
      ["clean"]
      ["uberjar"]
      ["clean"]
      ["test"]]})
