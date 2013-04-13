(ns myblog.model
  (:require [monger.core :as mg]
            [monger.collection :as mc]))

 ;      Root User: admin
 ;  Root Password: CBWicSHaTQYR
 ;  Database Name: course1task04
 ;
 ;Connection URL: mongodb://$OPENSHIFT_MONGODB_DB_HOST:$OPENSHIFT_MONGODB_DB_PORT/

(defn dbconnect [ & local]
  (if (empty? local)
    (let [env (into {} (System/getenv))
          username "admin"
          password "CBWicSHaTQYR"
          db "course1task04"
      host (get env "OPENSHIFT_MONGODB_DB_HOST")
          port (Integer/parseInt (get env "OPENSHIFT_MONGODB_DB_PORT"))]
      (when (and (string? host) (string? port) (not (= host "")))
        (do
  (mg/connect! {:host host
                     :port port})
(mg/use-db! db)
(mg/authenticate (mg/get-db db) username (.toCharArray password))))
(do
  (mg/connect!)
  (mg/use-db! (first local)))))

(dbconnect)

(defn todb [item]
  (assoc item :id (inc (long (mc/count "document")))))

(defn create-article [item]
    (mc/insert "document" (todb  item)))

(defn select-article []
  (mc/find-maps "document"))

(defn find-article [id]
   ;(first (mc/find-maps  "document" {:id id})))
    (mc/find-one-as-map "document" {:id id}))  

(defn update-article [item]
  (print (str item))
  (mc/update "document" {:id (:id item)}  item))

(defn delete-article [id]
  (mc/remove "document"  {:id id} ))

(defn delete-all []
  (mc/remove "document"))

