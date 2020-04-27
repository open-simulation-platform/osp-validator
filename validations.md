### OspModelDescription

- [V1] All variable groups must be valid
  - 'Variable groups `x,y,z` are invalid'
    - {Error message from [V6], [V7] for each variable group}
- [V2] All variables must be valid
  - 'Variables `x,y,z` are invalid'
    - {Error message from [V8], [V9], [V10] for each variable}
- [V3] All variable group names must be unique
  - 'Variable group `x` and `y` have the same name. Variable group names must be unique'
- [V4] All variable names must be unique
  - 'Variable `x` and `y` have the same name. Variable names must be unique'
- [V5] All variable names must correspond to (fmi)modelDescription.xml variable names
  - 'Variable `x` and `y` does not exist in `(fmi)ModelDescription.xml'

#### VariableGroup

- [V6] All variables must be valid
  - 'Variables `x,y,z` in variable group `this` are invalid'
    - {Error message from [V8], [V9], [V10] for each variable}
- [V7] Type must be valid
  - 'Type `x` in variable group `this` is invalid'

#### Variable

- [V8] Type must be valid
  - 'Type `x` in variable `this` is invalid'
- [V9] Causality must be valid
  - 'Causality `x` in variable `this` is invalid'
- [V10] Unit must be valid
  - 'Unit `x` in variable `this` is invalid'


### OspSystemStructure

- [V11] All simulators must be valid
  - 'Simulator `x` is invalid'
    - {Error message from [V19]}
- [V12] All simulators must have unique names
- [V13] All Functions must be valid
- [V14] All variable group connections must be valid
  - Variable group connection `x` is invalid
    - {Error messages from [23], [24], [25], [26]}
- [V15] All variable connections must be valid
  - Variable connection `x` is invalid
    - {Error messages from [V27], [V28], [V29], [V30], V[31]}
- [V16] All signal group connections must be valid
- [V17] All signal connections must be valid
- [V18] All input variables exists in maximum 1 connection
  - 'Input variables `x,y,z` exists in more than one connection'
    - 'Variable `x` exists in variable groups: `a,b`'
    - 'Variable `y` exists in variable groups: `c,d`'
    - 'Variable `z` exists in variable groups: `e,f,g`'

#### Simulator

- [V19] OspModelDescription is valid
  - '`OspModelDescription` of simulator `this` is invalid'
    - {Error messages from [V1], [V2], [V3], [V4], [V5]}

#### Function

- [V20] All signal groups must be valid
- [V21] All signals must be valid

#### SignalGroup

- [V22] All signals must be valid

#### Signal

#### VariableGroupConnection

- [V23] Both simulators must be valid
  - 'Simulator `x` and/or `y` is invalid'
    - {Error message from [V19] for each simulator}
- [V24] Both variable groups must be valid
  - Variable group `x` and/or `y` is invalid
    - {Error messages from [V6], [V7]}
- [V25] Variable group types must match
  - Type `a` of variable group `x` is incompatible with type `b` of variable group `y`
- [V26] Variable causalities must be opposite
  - Variable `a` in variable group `x` has the same causality as variable `b` in variable group `y`

#### VariableConnection

- [V27] Both simulators must be valid
  - 'Simulator `x` and/or `y` is invalid'
    - {Error message from [V19] for each simulator}
- [V28] Both variables must be valid
  - 'Variable `x` and/or `y` is invalid'
    - {Error message from [V8], [V9], [V10] for each variable}
- [V29] Variables must have opposite causality
  - 'Variable `x` and `y` have the same causality. 'Causality of `x`: `x.causality`. 'Causality of `y`: `y.causality`
- [V30] Variables must have same type
  - 'Variable `x` and `y` have different types. 'Type of `x`: `x.type`. 'Type of `y`: `y.type`
- [V31] Variables must have compatible units
  - 'Variable `x` and `y` have incompatible units. 'Unit of `x`: `x.unit`. 'Unit of `y`: `y.unit`

#### SignalGroupConnection

- [V32] Simulator must be valid
- [V33] Variable group must be valid
- [V34] Function must be valid
- [V35] Signal group must be valid

#### SignalConnection

- [V36] Simulator must be valid
- [V37] Variable must be valid
- [V38] Function must be valid
- [V39] Signal must be valid




