package de.boeg.rdf.generate.shacl.domain;

import org.apache.commons.lang3.Validate;
import org.apache.jena.shacl.vocabulary.SHACL;
import org.apache.jena.vocabulary.RDF;

public class ContainmentShape extends AbstractShape {

    private final String containerMembershipPropertyIRI;
    private final String containerClassIRI;
    private static final String BLUEPRINT =
              "[".concat(SHACL.property.getURI()).concat(" [")
                 .concat(SHACL.path.getURI())
                 .concat(" ( %s ").concat(RDF.type.getURI()).concat("); ")
                 .concat(SHACL.hasValue.getURI()).concat(" %s ;] ]");

    ContainmentShape(String containerMembershipPropertyIRI, String containerClassIRI) {
        super("ContainmentRule");
        Validate.notBlank(containerClassIRI);
        Validate.notBlank(containerMembershipPropertyIRI);
        this.containerMembershipPropertyIRI = containerMembershipPropertyIRI;
        this.containerClassIRI = containerClassIRI;
    }

    @Override
    public String asShape() {
        return String.format(BLUEPRINT, containerMembershipPropertyIRI, containerClassIRI);
    }
}
