# msmi-validator
Ontology-based systems structure validator

# clone -> build -> run
```
$ git clone https://github.com/open-simulation-platform/msmi-validator.git
$ cd msmi-validator
$ mvn clean package
$ java -jar ./target/msmi-validator-<version>-jar-with-dependencies.jar --osp-ontology ./src/main/resources/osp.owl --cse-config ./src/main/resources/json/cse-config.json
```
