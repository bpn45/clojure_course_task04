(ns myblog.model
  (:require [monger.core :as mg]
            [monger.collection :as mc]))

 ;      Root User: admin
 ;  Root Password: CBWicSHaTQYR
 ;  Database Name: course1task04
 ;
 ;Connection URL: mongodb://$OPENSHIFT_MONGODB_DB_HOST:$OPENSHIFT_MONGODB_DB_PORT/


    (let [env (into {} (System/getenv))
          username "admin"
          password "CBWicSHaTQYR"
          db "course1task04"
          uri  (get env "OPENSHIFT_MONGODB_DB_URL" "mongodb://127.0.0.1:27017")
           ]
      
      (mg/connect-via-uri! (str uri "/" db)))





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

