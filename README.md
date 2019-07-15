# msmi-validator-core
Ontology-based systems structure validator

## clone -> build -> run
```
$ git clone https://github.com/open-simulation-platform/msmi-validator.git
$ cd msmi-validator/core
$ mvn clean package
$ java -jar ./target/msmi-validator-core-<version>-jar-with-dependencies.jar --osp-ontology ./src/test/resources/validator/osp.owl --cse-config ./src/test/resources/validator/cse-config-valid.json
```

## clone -> build -> run (with debug output) -> generate configuration.owl
```
$ git clone https://github.com/open-simulation-platform/msmi-validator.git
$ cd msmi-validator/core
$ mvn clean package
$ java -Dmsmi.validator.log.level=debug -jar ./target/msmi-validator-core-<version>-jar-with-dependencies.jar --osp-ontology ./src/test/resources/validator/osp.owl --cse-config ./src/test/resources/validator/cse-config-valid.json -s ./
```

# msmi-validator-http
Ontology-based systems structure validator

## clone -> build -> run
```
$ git clone https://github.com/open-simulation-platform/msmi-validator.git
$ cd msmi-validator/http
$ mvn clean package
$ java -Dmsmi.validator.log.level=debug -jar ./target/msmi-validator-http-<version>-jar-with-dependencies.jar --port <your-favourite-port>
- open web browser
- go to localhost:<your-favourite-port>/validate?configuration=/full/path/to/<msmi-validator>/http/src/test/resources/validator/cse-config-valid.json$ontology=/full/path/to/<msmi-validator>/http/src/test/resources/validator/osp.owl
```
