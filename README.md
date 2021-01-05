Implement an interpreter (i.e., evaluator) for the expressions without user-defined functions. The syntax of the expressions without user-defined functions is defined by:
      ⟨exp⟩ → ⟨int⟩ | ⟨float⟩ | ⟨floatE⟩ | ⟨floatF⟩ | "nil" | "(" ⟨fun exp⟩ ")" | "if" ⟨exp⟩ "then" ⟨exp⟩ "else" ⟨exp⟩
      ⟨fun exp⟩ → ⟨fun op⟩ ⟨exp list⟩
      ⟨fun op⟩ → "pair" | "first" | "second" | ⟨arith op⟩ | ⟨bool op⟩ | ⟨comp op⟩
      ⟨arith op⟩ → + | − | * | /
      ⟨bool op⟩ → "or" | "and" | "not"
      ⟨comp op⟩ → "<" | "<=" | ">" | ">=" | "="
      ⟨exp list⟩ → ε | ⟨exp⟩ ⟨exp list⟩
        
The following are the specifications of the built-in operators to be implemented. The symbol ⊥v represents the runtime error value.
      nil, pair, first, second
The constant expression nil evaluates to the "nil value".

An expression (pair E1 E2) evaluates to a pair object whose first component is the value of E1 and second component is the value of E2. In the following, a pair object will be denoted by pair(valE1, valE2) where valE1, valE1 are, respectively, the values of E1 and E2.
The first and second operators apply only to pair objects; they are component extractors.
      (first pair(valE1, valE2)) evaluates to valE1
      (second pair(valE1, valE2)) evaluates to valE2
arithmetic expressions
      (+ E1 ··· En) = E1 + (E2 + ··· (En−1 + (En + 0))···)    n ≥ 0
      (− E1 ··· En) = E1 − (E2 − ··· (En−1 − (En − 0))···)    n ≥ 0
      (* E1 ··· En) = E1 * (E2 * ··· (En−1 * (En * 1))···)    n ≥ 0
      (/ E1 ··· En) = E1 / (E2 / ··· (En−1 / (En / 1))···)    n ≥ 0
      
Right associativity is to be implemented. If any Ei evaluates to a non-number or ⊥v, the evaluation result is ⊥v. If all Ei evaluate to integers, the result is an integer; if at least one Ei evaluates to a float, the result is a float. Integer division should implement round-down, e.g., (/ 2 3) = 0, (/ 3 2) = 1, (/ 5 2) = 2. But (/ 2. 3) = 0.6666667, (/ 3 2.) = 1.5, (/ 5.0 2) = 2.5, etc. Note that if n = 0, then:
      (+) = 0
      (−) = 0
      (*) = 1
      (/) = 1
Boolean expressions
      (or E1 ··· En) = E1 ∨ E2 ∨ ··· En−1 ∨ En ∨ false    n ≥ 0
      (and E1 ··· En) = E1 ∧ E2 ∧ ··· En−1 ∧ En ∧ true    n ≥ 0
If any Ei evaluates to a non-Boolean value or ⊥v, the evaluation result is ⊥v. Note that if n = 0, then:
      (or) = false
      (and) = true
      (not E) = ¬ E
      
If E evaluates to a non-Boolean value or ⊥v, the evaluation result is ⊥v.
comparison expressions
      (< E1 ··· En) = (E1 < E2 < ··· < En−1 < En)    n ≥ 0
      (≤ E1 ··· En) = (E1 ≤ E2 ≤ ··· ≤ En−1 ≤ En)    n ≥ 0
      (> E1 ··· En) = (E1 > E2 > ··· > En−1 > En)    n ≥ 0
      (≥ E1 ··· En) = (E1 ≥ E2 ≥ ··· ≥ En−1 ≥ En)    n ≥ 0
      (= E1 ··· En) = (E1 = E2 = ··· = En−1 = En)    n ≥ 0
      
If n = 0 or 1, the comparison expressions vacuously evaluate to true. If any Ei evaluates to ⊥v, the evaluation result is ⊥v. For <, ≤, >, ≥, if any Ei evaluates to a non-number, the result is ⊥v. For =, if n ≥ 2, values of different types are defined to be not equal, that is: nil is not equal to any non-nil values. A pair value is not equal to any non-pair values. A number is not equal to any non-numbers. A Boolean value is not equal to any non-Boolean values. In each of the above four cases, equality comparison results in false, not ⊥v.
Equality of pair values are defined component-wise recursively:
      (= pair(firstE1, secondE1) ··· pair(firstEi, secondEi) ··· pair(firstEn, secondEn)) =
      (= firstE1 ··· firstEi ··· firstEn) ∧ (= secondE1 ··· secondEi ··· secondEn)
conditional expressions

if B then E1 else E2

If B evaluates to ⊥v, the result is ⊥v. If B evaluates to a non-Boolean value, the result is ⊥v. If B evaluates to true, the result is the value of E1. If B evaluates to false, the result is the value of E2.
The interpreter (evaluator) is to be implemented by Eval functions in suitable syntactic-category classes. Implement function-call states by HashMap<String, Val> where Val is the class of values of variables; it maps the parameter variable named by a string to its value.

