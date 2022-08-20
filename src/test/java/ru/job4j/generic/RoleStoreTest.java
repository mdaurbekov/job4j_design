package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRoleNameIsManager() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Manager");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsManager() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        store.add(new Role("1", "Admin"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Manager");
    }

    @Test
    void whenReplaceThenRoleNameIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        store.replace("1", new Role("1", "Admin"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Admin");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        store.replace("10", new Role("10", "Admin"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Manager");
    }

    @Test
    void whenDeleteRoleThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsManager() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("Manager");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        boolean rsl = store.replace("1", new Role("1", "Admin"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        boolean rsl = store.replace("10", new Role("10", "Admin"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }

}