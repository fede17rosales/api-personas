package com.frosales.test.personas.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class PersonTest {


    @Test
    void constructor_ShouldSetNameAndEmail() {
        Person p = new Person("Federico", "fede@test.com");

        assertThat(p.getName()).isEqualTo("Federico");
        assertThat(p.getEmail()).isEqualTo("fede@test.com");
        assertThat(p.getCreatedAt()).isNotNull();
        assertThat(p.getUpdatedAt()).isNotNull();
    }

    @Test
    void preUpdate_ShouldUpdateUpdatedAt() throws InterruptedException {
        Person p = new Person("Federico", "fede@test.com");
        Instant before = p.getUpdatedAt();

        Thread.sleep(10);
        p.preUpdate();

        Instant after = p.getUpdatedAt();
        assertThat(after).isAfter(before);
    }

    @Test
    void settersAndGetters_ShouldWorkCorrectly() {
        Person p = new Person();
        p.setName("Fede");
        p.setEmail("fede@test.com");

        assertThat(p.getName()).isEqualTo("Fede");
        assertThat(p.getEmail()).isEqualTo("fede@test.com");
    }
}