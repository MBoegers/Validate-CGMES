package de.boeg.rdf.generate.shacl.domain;

import org.apache.commons.lang3.Validate;

public abstract class AbstractShape {

    private final String ruleName;

    AbstractShape(String ruleName) {
        Validate.notBlank(ruleName);
        this.ruleName = ruleName;
    }

    public String getRuleName() {
        return ruleName;
    }

    public abstract String asShape();
}
