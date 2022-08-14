package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere").isNotEmpty();
    }

    @Test
    void isThisCube() {
        Box box = new Box(6, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube").isNotEmpty();
    }

    @Test
    void checkVertex() {
        Box box = new Box(4, 10);
        boolean vertex = box.isExist();
        assertThat(vertex).isTrue().isNotNull();
    }

    @Test
    void checkNumberOfVerticesTetrahedron() {
        Box box = new Box(4, 8);
        int exist = box.getNumberOfVertices();
        assertThat(exist).isPositive().isEqualTo(4);
    }

    @Test
    void checkNumberOfVerticesCube() {
        Box box = new Box(6, 8);
        int exist = box.getNumberOfVertices();
        assertThat(exist).isPositive().isEqualTo(6);
    }

    @Test
    void checkAreaTetrahedron() {
        Box box = new Box(4, 6);
        double area = box.getArea();
        assertThat(area).isGreaterThan(62.30d).isLessThan(62.40d);
    }

    @Test
    void checkAreaCube() {
        Box box = new Box(6, 8);
        double area = box.getArea();
        assertThat(area).isEqualTo(384.00d);
    }

}