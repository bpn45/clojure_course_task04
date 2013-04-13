(defproject myblog "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [me.raynes/laser "1.1.1"]
                 [com.novemberain/monger "1.5.0"]
                 [lib-noir "0.4.9"]]
  :plugins [[lein-ring "0.8.2"]]
  :ring {:handler myblog.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]]}})
