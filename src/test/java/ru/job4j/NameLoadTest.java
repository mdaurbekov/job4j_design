package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmptyGetMap() {
        NameLoad nameLoad = new NameLoad();

        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkEmptyParse() {
        NameLoad nameLoad = new NameLoad();

        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");

    }

    @Test
    void checkEmptySymbolParse() {
        NameLoad nameLoad = new NameLoad();

        assertThatThrownBy(() -> nameLoad.parse("aabb"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain the symbol")
                .hasMessageContaining("aabb");


    }

    @Test
    void checkEmptyKeyParse() {
        NameLoad nameLoad = new NameLoad();

        assertThatThrownBy(() -> nameLoad.parse("=aa"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a key")
                .hasMessageContaining("=aa");

    }

    @Test
    void checkEmptyValueParse() {
        NameLoad nameLoad = new NameLoad();

        assertThatThrownBy(() -> nameLoad.parse("aa="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("does not contain a value")
                .hasMessageContaining("aa=");
    }

}