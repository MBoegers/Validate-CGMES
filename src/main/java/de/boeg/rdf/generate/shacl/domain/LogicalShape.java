package de.boeg.rdf.generate.shacl.domain;

import org.apache.commons.lang3.Validate;
import org.apache.jena.shacl.vocabulary.SHACL;

import java.util.List;
import java.util.stream.Collectors;

public abstract class LogicalShape extends AbstractShape {

    private final String operationIRI;
    private final List<AbstractShape> rules;

    LogicalShape(String ruleName, String operationIRI, List<AbstractShape> rules) {
        super(ruleName);
        Validate.notBlank(operationIRI);
        Validate.notEmpty(rules);
        this.operationIRI = operationIRI;
        this.rules = rules;
    }

    @Override
    public String asShape() {
        var ruleStr = rules.parallelStream()
                           .map(AbstractShape::asShape)
                           .collect(Collectors.joining(" ", "(", ")"));
        return operationIRI.concat(ruleStr);
    }

    public static class AndShape extends LogicalShape {

        public AndShape(List<AbstractShape> rules) {
            super("AndRule", SHACL.and.getURI(), rules);
        }
    }

    public static class OrShape extends LogicalShape {

        public OrShape(List<AbstractShape> rules) {
            super("OrRule", SHACL.or.getURI(), rules);
        }
    }

    public static class XoneShape extends LogicalShape {

        public XoneShape(List<AbstractShape> rules) {
            super("XORRule", SHACL.xone.getURI(), rules);
        }
    }

    public static class NotShape extends LogicalShape {

        public NotShape(AbstractShape rule) {
            super("NotRule", SHACL.not.getURI(), List.of(rule));
        }
    }

    public static class ImpliesShape extends LogicalShape {

        private final AbstractShape proposition;
        private final AbstractShape hypothesis;
        private static final String BLUEPRINT =
                  SHACL.or.getURI().concat(" ( ").concat(SHACL.not.getURI()).concat("( %s ) [ %s ] )");

        public ImpliesShape(AbstractShape proposition, AbstractShape hypothesis) {
            super("Implication", "implication", List.of(proposition, hypothesis));
            Validate.notNull(proposition);
            Validate.notNull(hypothesis);
            this.proposition = proposition;
            this.hypothesis = hypothesis;
        }

        @Override
        public String asShape() {
            return String.format(BLUEPRINT,proposition.asShape(), hypothesis.asShape());
        }
    }
}
