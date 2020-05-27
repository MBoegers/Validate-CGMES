package de.boeg.rdf.generate.shacl.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("The class representing a container ship rule")
class ContainmentShapeTest {

    @Test
    @DisplayName("throw errors if wrong used")
    void wrongUsage() {
        Assertions.assertThatThrownBy(() -> new ContainmentShape(null, "Test"));
        Assertions.assertThatThrownBy(() -> new ContainmentShape("Test", null));
        Assertions.assertThatThrownBy(() -> new ContainmentShape("", "Test"));
        Assertions.assertThatThrownBy(() -> new ContainmentShape("Test", ""));
        Assertions.assertThatThrownBy(() -> new ContainmentShape("    ", "Test"));
        Assertions.assertThatThrownBy(() -> new ContainmentShape("Test", "   "));
    }

    @Test
    void asShape() {
        var membershipProperty = "http://example.test/memebership";
        var containerClass = "Http://example.test/Container";

        var rule = new ContainmentShape(membershipProperty, containerClass);

        var expected = rule.asShape();

        Assertions.assertThat(expected)
                  .isNotNull()
                  .containsOnlyOnce(membershipProperty)
                  .containsOnlyOnce(containerClass);
    }
}