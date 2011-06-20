clojure-sna is a Social Network Analysis library for the Clojure
language.

An Example
----------

Here's a very simple example of a REPL session with the SNA library.

    user=> (use 'sna.graph)
    nil
    user=> (def g (make-graph 1 2 3 4 3 5))
    #'user/g
    user=> g
    {3 {5 {}, 4 {}}, 1 {2 {}}}
    user=> (def g (add-edge g 6 7 :weight 10))
    #'user/g
    user=> g
    {6 {7 {:weight 10}}, 3 {5 {}, 4 {}}, 1 {2 {}}}
    user=> (get-edge 1 2)
    {}
    user=> (get-edge 6 7)
    {:weight 10}
    user=> (:weight (get-edge g 6 7))
    10
    user=> (:weight (get-edge g 1 2))
    nil
    
License and Copyright
---------------------

Copyright (c) Christopher Roach. All rights reserved.  
The use and distribution terms for this software are covered by the
Eclipse Public License 1.0 which can be found in the file LICENSE
at the root of this distribution. By using this software in any
fashion, you are agreeing to be bound by the terms of this license.
You must not remove this notice, or any other, from this software.
