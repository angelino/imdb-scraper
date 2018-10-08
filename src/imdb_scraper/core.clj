(ns imdb-scraper.core
  (:require [clojure.string :as str])
  (:import [org.jsoup Jsoup]))

(defonce page (-> "https://www.imdb.com/chart/top"
                  Jsoup/connect
                  (.timeout 60000)
                  (.maxBodySize (* 10 1024 1024))
                  .get))

(def table  (first (.select page "table")))

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

(clojure.pprint/print-table movies)