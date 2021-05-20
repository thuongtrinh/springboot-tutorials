package com.txt.springsessionjdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpringSessionJdbcIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private static TestRestTemplate testRestTemplate;

    @BeforeAll
    public static void setup() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");

        testRestTemplate = new TestRestTemplate();
    }

    @Test
    public void givenApiHasStarted_whenH2DbIsQueried_thenSessionTablesAreEmpty() throws SQLException {
        Assertions.assertEquals(0, getSessionIdsFromDatabase().size());
        Assertions.assertEquals(0, getSessionAttributeBytesFromDatabase().size());
    }

    @Test
    public void givenGetInvoked_whenH2DbIsQueried_thenOneSessionIsCreated() throws SQLException {
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/", String.class)).isNotEmpty();
        Assertions.assertEquals(1, getSessionIdsFromDatabase().size());
    }

    @Test
    public void givenPostInvoked_whenH2DbIsQueried_thenSessionAttributeIsRetrieved() throws ClassNotFoundException, SQLException, IOException {
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("color", "red");
//        this.testRestTemplate.postForObject("http://localhost:" + port + "/saveColor", map, String.class);
        this.testRestTemplate.getForObject("http://localhost:" + port + "/saveColor?color=red", String.class);
        List<byte[]> queryResponse = getSessionAttributeBytesFromDatabase();
        Assertions.assertEquals(1, queryResponse.size());
        ObjectInput in = new ObjectInputStream(new ByteArrayInputStream(queryResponse.get(0)));
        List<String> obj = (List<String>) in.readObject(); //Deserialize byte[] to object
        Assertions.assertEquals("red", obj.get(0));
    }

    private List<String> getSessionIdsFromDatabase() throws SQLException {
        List<String> result = new ArrayList<>();
        ResultSet rs = getResultSet("SELECT * FROM SPRING_SESSION");
        while (rs.next()) {
            result.add(rs.getString("SESSION_ID"));
        }
        return result;
    }

    private List<byte[]> getSessionAttributeBytesFromDatabase() throws SQLException {
        List<byte[]> result = new ArrayList<>();
        ResultSet rs = getResultSet("SELECT * FROM SPRING_SESSION_ATTRIBUTES");
        while (rs.next()) {
            result.add(rs.getBytes("ATTRIBUTE_BYTES"));
        }
        return result;
    }

    private ResultSet getResultSet(String sql) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        Statement stat = conn.createStatement();
        return stat.executeQuery(sql);
    }
}
