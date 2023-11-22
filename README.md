# Partitioner

This Project provides a standalone Partitioner interface for the iterative Analysis Framework.
The abstract concept of Partitioning of iterable Sets is extracted to achieve dependency inversion.

**It is important that all Implementations of the interface Partitioner are registered** in the @JsonSubTypes annotation in the interface file above the interface definition.
This is neccessary for deserialization from JSON to the correct Subclass.
