package main.java.org.papz20.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberTest {
    @Test
    void testGetRole() {
        Member member = new Member(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");
        Assertions.assertEquals("member", member.getRole());
    }
}
