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