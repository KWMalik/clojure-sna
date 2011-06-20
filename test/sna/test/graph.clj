;; Copyright (c) Christopher Roach. All rights reserved.  
;; The use and distribution terms for this software are covered by the
;; Eclipse Public License 1.0 which can be found in the file LICENSE
;; at the root of this distribution. By using this software in any
;; fashion, you are agreeing to be bound by the terms of this license.
;; You must not remove this notice, or any other, from this software.

(ns sna.test.graph
  (:use [sna.graph])
  (:use [clojure.test]))

(deftest test-make-sample-graph
  (let [correct-graph  {1 {2 {}},
                        3 {4 {}, 5 {}}}
        sample-graph   (make-graph 1 2 3 4 3 5)
        error-msg      (str "Graph '" sample-graph
                            "' does not equal '" correct-graph "'")]
    (is (= sample-graph correct-graph) error-msg)))

(deftest test-add-edge
  (let [correct-graph   {1 {2 {}}
                         3 {4 {}, 5 {}}
                         6 {7 {}, 8{}}}
        sample-graph    (make-graph 1 2 3 4 3 5)
        sample-graph    (add-edge sample-graph 6 7)
        sample-graph    (add-edge sample-graph 6 8)
        error-msg       (str "Graph '" sample-graph "' does not equal '"
                             correct-graph "'")]
    (is (= sample-graph correct-graph) error-msg)))

(deftest test-add-node
  (let [correct-graph   {1 {2 {}}
                         3 {4 {}, 5 {}}
                         6 {}
                         7 {}}
        sample-graph    (make-graph 1 2 3 4 3 5)
        sample-graph    (add-node sample-graph 6)
        sample-graph    (add-node sample-graph 7)
        error-msg       (str "Graph '" sample-graph "' does not equal '"
                             correct-graph "'")]
    (is (= sample-graph correct-graph) error-msg)))

(deftest test-number-of-nodes
  (let [sample-graph            {1 {2 {}}
                                 3 {4 {}, 5 {}}
                                 6 {}
                                 7 {}}
        actual-number-of-nodes  (number-of-nodes sample-graph)
        correct-number-of-nodes 7
        error-msg               (str "Number of nodes in sample graph is '"
                                     actual-number-of-nodes "', expected '"
                                     correct-number-of-nodes)]
    (is (= actual-number-of-nodes correct-number-of-nodes) error-msg)))