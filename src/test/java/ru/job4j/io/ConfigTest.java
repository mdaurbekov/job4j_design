package ru.job4j.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password");
    }

    @Test
    void whenMoreThanOneCharacter() {
        String path = "./data/more_than_one_character_=.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("11111")).isEqualTo("22222=333333");
    }

    @Test
    void whenKeyIsNull() throws IllegalArgumentException {
        String path = "./data/key_is_null.properties";
        Config config = new Config(path);
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, config::load);
        Assertions.assertEquals("=rtgbrttgb", thrown.getMessage());
    }

    @Test
    void whenValueIsNull() {
        String path = "./data/value_is_null.properties";
        Config config = new Config(path);
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, config::load);
        Assertions.assertEquals("=rtgbrttgb", thrown.getMessage());
    }

    @Test
    void whenNoSymbol() {
        String path = "./data/no_symbol_=.properties";
        Config config = new Config(path);
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, config::load);
        Assertions.assertEquals("khadliadblaideblaibhaei;lbeb", thrown.getMessage());
    }

}