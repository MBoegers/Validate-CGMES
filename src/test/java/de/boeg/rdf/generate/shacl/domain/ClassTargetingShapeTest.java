package de.boeg.rdf.generate.shacl.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

@DisplayName("The class representing a shape that targets a class")
public class ClassTargetingShapeTest {

    @DisplayName("Throw exception if wrong used")
    void ctor() {
        Assertions.assertThatThrownBy(() -> new ClassTargetingShape(null, "test", Collections.emptyList()));
        Assertions.assertThatThrownBy(() -> new ClassTargetingShape("test", null, Collections.emptyList()));
        Assertions.assertThatThrownBy(() -> new ClassTargetingShape("test", "test", null));
        Assertions.assertThatThrownBy(() -> new ClassTargetingShape("", "test", Collections.emptyList()));
        Assertions.assertThatThrownBy(() -> new ClassTargetingShape("test", "", Collections.emptyList()));
        Assertions.assertThatThrownBy(() -> new ClassTargetingShape("   ", "test", Collections.emptyList()));
        Assertions.assertThatThrownBy(() -> new ClassTargetingShape("test", "   ", Collections.emptyList()));
    }

    @Test
    @DisplayName("Produces the basic shape")
    void basicShape() {
        var targetClass = "http://example.test/TargetClass";
        var shapeMessage = "\"Lorem Ipsum\"";
        List<AbstractShape> propertyShapes = Collections.emptyList();

        var actual = new ClassTargetingShape(shapeMessage, targetClass, propertyShapes).asShape();

        Assertions.assertThat(actual)
                  .isNotNull()
                  .containsOnlyOnce(targetClass)
                  .containsOnlyOnce(shapeMessage);
    }

    @Test
    @DisplayName("Produces the shape with containment")
    void singleContainmentShape() {
        var targetClass = "http://example.test/TargetClass";
        var shapeMessage = "\"Lorem Ipsum\"";
        var containmentShip = "http://example.test/membership";
        var container = "http://example.test/Container";
        var containment = new ContainmentShape(containmentShip, container);

        var actual = new ClassTargetingShape(shapeMessage, targetClass, List.of(containment)).asShape();

        Assertions.assertThat(actual)
                  .isNotNull()
                  .containsOnlyOnce(targetClass)
                  .containsOnlyOnce(shapeMessage)
                  .containsOnlyOnce(containmentShip)
                  .containsOnlyOnce(container);
    }

    @Test
    @DisplayName("Produces the shape with containment")
    void multipleContainmentShape() {
        var targetClass = "http://example.test/TargetClass";
        var shapeMessage = "\"Lorem Ipsum\"";
        var containmentShip1 = "http://example.test/membership1";
        var container1 = "http://example.test/Container1";
        var containmentShip2 = "http://example.test/membership2";
        var container2 = "http://example.test/Container2";
        var containment1 = new ContainmentShape(containmentShip1, container1);
        var containment2 = new ContainmentShape(containmentShip2, container2);

        var actual = new ClassTargetingShape(shapeMessage, targetClass, List.of(containment1, containment2)).asShape();

        Assertions.assertThat(actual)
                  .isNotNull()
                  .containsOnlyOnce(targetClass)
                  .containsOnlyOnce(shapeMessage)
                  .containsOnlyOnce(containmentShip1)
                  .containsOnlyOnce(container1)
                  .containsOnlyOnce(containmentShip2)
                  .containsOnlyOnce(container2);
    }
}
