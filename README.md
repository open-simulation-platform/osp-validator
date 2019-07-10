# msmi-validator
Ontology-based systems structure validator

## clone -> build -> run
```
$ git clone https://github.com/open-simulation-platform/msmi-validator.git
$ cd msmi-validator
$ mvn clean package
$ java -jar ./target/msmi-validator-<version>-jar-with-dependencies.jar --osp-ontology ./src/test/resources/validator/osp.owl --cse-config ./src/test/resources/validator/cse-config-valid.json
```
