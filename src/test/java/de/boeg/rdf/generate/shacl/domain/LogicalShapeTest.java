package de.boeg.rdf.generate.shacl.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

@DisplayName("The class representing a logical shape")
public class LogicalShapeTest {

    private String container1;
    private String container2;
    private String containment1;
    private String containment2;
    private ContainmentShape shape1;
    private ContainmentShape shape2;

    @BeforeAll
    void setup() {
        container1 = "http://example.test/Container1";
        container2 = "http://example.test/Container2";
        containment1 = "http://example.test/containment1";
        containment2 = "http://example.test/containment2";
        shape1 = new ContainmentShape(container1, containment1);
        shape2 = new ContainmentShape(container2, containment2);
    }

    @Test
    @DisplayName("Procures ANDs correctly")
    void and() {
        Assertions.assertThatThrownBy(() -> new LogicalShape.AndShape(Collections.emptyList()));
        Assertions.assertThat(new LogicalShape.AndShape(List.of(shape1)).asShape())
                .isNotNull()
                .containsOnlyOnce(container1)
                .containsOnlyOnce(containment1);
        Assertions.assertThat(new LogicalShape.AndShape(List.of(shape1, shape2)).asShape())
                .isNotNull()
                .containsOnlyOnce(container1)
                .containsOnlyOnce(containment1)
                .containsOnlyOnce(container2)
                .containsOnlyOnce(containment2);
    }

    @Test
    @DisplayName("Procures ORs correctly")
    void or() {
        Assertions.assertThatThrownBy(() -> new LogicalShape.OrShape(Collections.emptyList()));
        Assertions.assertThat(new LogicalShape.OrShape(List.of(shape1)).asShape())
                .isNotNull()
                .containsOnlyOnce(container1)
                .containsOnlyOnce(containment1);
        Assertions.assertThat(new LogicalShape.OrShape(List.of(shape1, shape2)).asShape())
                .isNotNull()
                .containsOnlyOnce(container1)
                .containsOnlyOnce(containment1)
                .containsOnlyOnce(container2)
                .containsOnlyOnce(containment2);
    }

    @Test
    @DisplayName("Procures XONEs correctly")
    void xone() {
        Assertions.assertThatThrownBy(() -> new LogicalShape.XoneShape(Collections.emptyList()));
        Assertions.assertThat(new LogicalShape.XoneShape(List.of(shape1)).asShape())
                .isNotNull()
                .containsOnlyOnce(container1)
                .containsOnlyOnce(containment1);
        Assertions.assertThat(new LogicalShape.XoneShape(List.of(shape1, shape2)).asShape())
                .isNotNull()
                .containsOnlyOnce(container1)
                .containsOnlyOnce(containment1)
                .containsOnlyOnce(container2)
                .containsOnlyOnce(containment2);
    }

    @Test
    @DisplayName("Procures NOTs correctly")
    void not() {
        Assertions.assertThatThrownBy(() -> new LogicalShape.NotShape(null));
        Assertions.assertThat(new LogicalShape.NotShape(shape1).asShape())
                .isNotNull()
                .containsOnlyOnce(container1)
                .containsOnlyOnce(containment1);

    }

    @Test
    @DisplayName("Procures Implications correctly")
    void implies() {
        Assertions.assertThatThrownBy(() -> new LogicalShape.ImpliesShape(null, null));
        Assertions.assertThatThrownBy(() -> new LogicalShape.ImpliesShape(shape1, null));
        Assertions.assertThatThrownBy(() -> new LogicalShape.ImpliesShape(null, shape2));

        Assertions.assertThat(new LogicalShape.ImpliesShape(shape1, shape2).asShape())
                .isNotNull()
                .containsOnlyOnce(container1)
                .containsOnlyOnce(containment1)
                .containsOnlyOnce(container2)
                .containsOnlyOnce(containment2);
    }
}
