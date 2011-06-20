;; Copyright (c) Christopher Roach. All rights reserved.  
;; The use and distribution terms for this software are covered by the
;; Eclipse Public License 1.0 which can be found in the file LICENSE
;; at the root of this distribution. By using this software in any
;; fashion, you are agreeing to be bound by the terms of this license.
;; You must not remove this notice, or any other, from this software.

(ns sna.test.graph
  (:use [sna.graph])
  (:use [clojure.test]))

(def test-graph (make-graph 1 2 3 4 3 5))

(deftest test-make-graph
  (let [correct-graph  {1 {2 {}},
                        3 {4 {}, 5 {}}}
        error-msg      (str "Graph '" test-graph
                            "' does not equal '" correct-graph "'")]
    (is (= test-graph correct-graph) error-msg)))

(defn test-add-edge [correct-graph test-graph]
  (let [error-msg    (str "Graph '" test-graph "' does not equal '"
                          correct-graph "'")]
    (is (= test-graph correct-graph) error-msg)))

(deftest test-add-edge-with-attr-map
  (let [correct-graph {1 {2 {}}
                       3 {4 {}, 5 {}}
                       6 {7 {}, 8 {:weight 10}}}
        test-graph  (-> test-graph
                        (add-edge 6 7)
                        (add-edge-with-attr-map 6 8 {:weight 10}))]
    (test-add-edge correct-graph test-graph)))
  
(deftest test-add-edge-1
  (let [correct-graph {1 {2 {}}
                       3 {4 {}, 5 {}}
                       6 {7 {}, 8 {}}}
        test-graph  (-> test-graph
                        (add-edge 6 7)
                        (add-edge 6 8))]
    (test-add-edge correct-graph test-graph)))

(deftest test-add-edge-2
  (let [correct-graph {1 {2 {}}
                       3 {4 {}, 5 {}}
                       6 {7 {}, 8 {:weight 10}}}
        test-graph  (-> test-graph
                        (add-edge 6 7)
                        (add-edge 6 8 :weight 10))]
    (test-add-edge correct-graph test-graph)))

(deftest test-add-node
  (let [correct-graph   {1 {2 {}}
                         3 {4 {}, 5 {}}
                         6 {}
                         7 {}}
        test-graph    (-> test-graph
                          (add-node 6)
                          (add-node 7))
        error-msg       (str "Graph '" test-graph "' does not equal '"
                             correct-graph "'")]
    (is (= test-graph correct-graph) error-msg)))

(deftest test-number-of-nodes
  (let [graph                   {1 {2 {}}
                                 3 {4 {}, 5 {}}
                                 6 {}
                                 7 {}}
        actual-number-of-nodes  (number-of-nodes graph)
        correct-number-of-nodes 7
        error-msg               (str "Number of nodes in sample graph is '"
                                     actual-number-of-nodes "', expected '"
                                     correct-number-of-nodes)]
    (is (= actual-number-of-nodes correct-number-of-nodes) error-msg)))