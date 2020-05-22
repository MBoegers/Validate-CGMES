# Quality Metrics for Electric Power System Modeling
This repository contains implementations of quality metrics for electrical power systems following the Common Grid Model Exchange Standard (CGMES).

## Abstract
The electrical power system is part of the European critical infrastructure.
The European Network of Transmission System Operators (ENTSO-E) coordinates cross border cooperation, exchange of information and empowers the integration of the 43 Transmission System Operators (TSO) around Europe. To ensure high reliability of the whole system, high-quality information about the system state is crucial.
The Common Grid Model Exchange Specification (CGMES) provides the exchange format for the individual grid model (IGM) of each TSO.
A Common Grid Model (CGM) is the combination of IGMs and provide all needed information for operational and planning processes with varying quality. However there are no automated method for execution of these quality specifications. Currently the quality of information reviewed manually, and due to the complexity of the represented information, the process is slow and error prone.   In this paper, we present a scalable and efficient way to assess the quality of IGMs and CGMs represented in the CGMES format, which is based on RDF Schema and a subset of UML.
This approach uses semantic web technologies to assess the quality of CGMES data in a semi-automatic way.
It produces SHACL shapes, which can assess the data quality of most electrical power system models, but we also point out limitations of SHACL in this setting. The outcome of this work can be adopted by ENTSO-E members for improving the planning software by validating the quality of incoming data from different sources. 

## Structure
This repository splits into different parts of the same solution.
Some requirements are in the RDF Schema files.
For other reuirements ENTSO-E use the Object Constraint Language (OCL).

### RDFS Requirements
SPARQL Inserts generate SHACL shapes for schema based requirements.
The Insert keyword can be exchanged with the Construct keyword to receive the generated shapes graph.
The developed scripts are in the [SPAQRL](src/main/sparql) source directory.
The input files are in the [RDFS](src/main/resources/rdfs) resource directory.

### OCL Requirements
A Java 13 program reads and analysis the OCL files.
SHACL shapes are generated from the invariants.
The programm files are in the [Java](src/main/java) source directory.
The input files are in the [OCL](src/main/resources/ocl) resource directory.

## References
* [Common Grid Model Exchange Standard](https://www.entsoe.eu/digital/cim/cim-for-grid-models-exchange/) provides:
  * CGMES definition
  * RDFS files
  * OCL files
* [Apache Jena Fuseki](https://jena.apache.org/documentation/fuseki2/) SPARQL Server used for testing
* [Apache Jena](https://jena.apache.org/) Java Semantic Web Framework used
* Authors [GitHub](https://github.com/MBoegers/jena-examples) Repository with useful Apache Jena snippets
