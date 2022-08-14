package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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
    void checkVertex(){
        Box box = new Box(4, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isPositive().isEven();
    }

    @Test
    void isExist(){
        Box box = new Box(4, 8);
        int exist = box.getNumberOfVertices();
        assertThat(exist).isPositive().isEqualTo(4);
    }
    @Test
    void checkArea(){
        Box box = new Box(4, 6);
        double area = box.getArea();
        assertThat(area).isGreaterThan(62.30d).isLessThan(62.40d);
    }

}