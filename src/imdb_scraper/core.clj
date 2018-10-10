(ns imdb-scraper.core
  (:require [clojure.string :as str])
  (:import [org.jsoup Jsoup]))

(defn get-page [url & opts]
  (let [connection (Jsoup/connect url)
        timeout (or (:timeout (first opts)) 1000) ; alternativa: (get (first opts) :timeout 1000)
        max-body-size (or (:max-body-size (first opts)) (* 10 1024 1024))]
    (.timeout connection timeout)
    (.maxBodySize connection max-body-size)
    (.get connection)))

(defonce page (get-page "https://www.imdb.com/chart/top" {:timeout 60000}))

(def table (first (.select page "table")))

(def table-rows (.select table "tr"))

(def row-columns (for [tr table-rows]
                   (vec (for [td (.select tr "td")]
                          (.text td)))))

(def movies (for [row (filter not-empty row-columns)]
              (let [info (str/split (get row 1) #"\s")]
                {:ranking (Integer. (str/escape (first info) {\. ""}))
                 :title (str/join " " (subvec info 1 (dec (count info))))
                 :published (Integer. (str/escape (last info) {\( "" \) ""}))
                 :rating (Double. (get row 2))})))

(filter #(>= (:rating %) 9) movies)

(filter #(>= (:published %) 2018) movies)

(filter #(str/includes? (:title %) "Pirata") movies)

;(clojure.pprint/print-table movies)
;(clojure.inspector/inspect-table movies)
