package de.boeg.rdf.generate.shacl.domain;

import org.apache.commons.lang3.Validate;
import org.apache.jena.shacl.vocabulary.SHACL;
import org.apache.jena.vocabulary.RDF;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClassTargetingShape extends AbstractShape {

    private final String targetClassIRI;
    private final List<AbstractShape> propertyShapes;
    private static final String BLUEPRINT =
              "[] ".concat(RDF.type.getURI()).concat(" ").concat(SHACL.NodeShape.getURI()).concat(";")
                    .concat(SHACL.message.getURI()).concat(" %s;")
                   .concat(" ").concat(SHACL.closed.getURI()).concat(" true;")
                   .concat(" ").concat(SHACL.ignoredProperties.getURI()).concat(" ( ").concat(RDF.type.getURI()).concat(");")
                   .concat(" ").concat(SHACL.targetClass.getURI()).concat(" %s;")
                   .concat(" %s .");

    ClassTargetingShape(String shapeMessage, String targetClassIRI, List<AbstractShape> propertyShapes) {
        super(shapeMessage);
        Validate.notBlank(targetClassIRI);
        this.targetClassIRI = targetClassIRI;
        Validate.notNull(propertyShapes);
        this.propertyShapes = new ArrayList<>(propertyShapes.size());
        this.propertyShapes.addAll(propertyShapes);
    }

    @Override
    public String asShape() {
        var propertyShapesStr = this.propertyShapes.parallelStream()
                                                   .map(AbstractShape::asShape)
                                                   .collect(Collectors.joining(" ;"));
        return String.format(BLUEPRINT, getRuleName(), targetClassIRI, propertyShapesStr);
    }
}
