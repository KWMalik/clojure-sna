;; Copyright (c) Christopher Roach. All rights reserved.  
;; The use and distribution terms for this software are covered by the
;; Eclipse Public License 1.0 which can be found in the file LICENSE
;; at the root of this distribution. By using this software in any
;; fashion, you are agreeing to be bound by the terms of this license.
;; You must not remove this notice, or any other, from this software.

(ns sna.graph)

(defprotocol Graph
  "Defines a protocal for a Graph type"
  (add-node [graph node] "Adds a new node to the graph")
  (add-edge-with-attr-map
    [graph u v attrs] "Adds a new edge with given attributes map")
  (add-edge
    [graph u v]
    [graph u v & attrs] "Adds a new edge to the graph")
  (get-edge [graph u v] "Returns the edge matching the endpoints")
  (number-of-nodes [graph] "Returns the number of nodes in the graph"))

;; Extend the Map class to be a Graph type
(extend-type clojure.lang.IPersistentMap
  Graph

  ;; Add Node 
  (add-node [graph node]
    (if (contains? graph node)
      graph
      (assoc graph node {})))

  ;; Add Edge
  (add-edge-with-attr-map [graph u v attrs]
    (if (contains? graph u)
      (let [neighbors (assoc (graph u) v attrs)]
        (assoc graph u neighbors))
      (assoc graph u {v attrs})))
    

  ;; TODO: If the edge already exists do we want to overwite the
  ;;       attributes within it or merge them?
  (add-edge [graph u v]
    (add-edge-with-attr-map graph u v {}))

  (add-edge [graph u v & keyvals]
    (let [attrs (apply hash-map keyvals)]
      (add-edge-with-attr-map graph u v attrs)))

  ;; Get edge (attributes)
  (get-edge [graph u v]
    (let [neighbors (graph u)]
      (if (not (nil? neighbors))
        (neighbors v)
        nil)))
  

  ;; Number of nodes
  (number-of-nodes [graph]
    (count graph)))
  
(defn make-graph 
  "Creates a new graph object from the given node edges"
  [& args]
  (loop [graph {}
         edges args]
    (let [[key val] (take 2 edges)]
      (if (empty? edges)
        graph
        (recur (add-edge graph key val) (nnext edges))))))
