package main.java.org.papz20.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberTest {
    private Member member = new Member(1, "John", "Smith", "john.smith@gmail.com", "jsmith", "password");

    @Test
    void testGetRole() {
        Assertions.assertEquals("member", member.getRole());
    }
}
