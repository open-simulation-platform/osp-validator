# Description

Implements parsers and converters to generate `osp-validator-core` data model from `OspModelDescription.xml`

# Generating JAXB model from OspModelDescription.xsd

The following uses JAXB to generate java classes used when parsing `OspModelDescription.xml` files

```
$ cd osp-validator/osp-model-description
$ xjc /path/to/OspModelDescription.xsd -d src/main/java/ -p com.opensimulationplatform.modeldescription.xml.model
```

[JAXB?](https://javaee.github.io/jaxb-v2/)
[xjc?](https://stackoverflow.com/questions/10005632/how-do-i-get-xjc)
