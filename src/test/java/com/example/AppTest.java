package com.example;

import static org.junit.Assert.assertEquals;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import io.github.ghosthack.turismo.Turismo;

public class AppTest {

    @BeforeClass
    public static void startServer() {
        App.routes();
        Turismo.start(0); // random free port
    }

    @AfterClass
    public static void stopServer() {
        Turismo.reset();
    }

    @Test
    public void hello() throws Exception {
        assertEquals("Hello World!", get("/hello"));
    }

    @Test
    public void pathParam() throws Exception {
        assertEquals("User 42", get("/users/42"));
    }

    @Test
    public void searchEscapesQuery() throws Exception {
        String q = URLEncoder.encode("<script>alert(1)</script>", StandardCharsets.UTF_8);
        assertEquals("<p>Your search query was: &lt;script&gt;alert(1)&lt;/script&gt;</p>",
                get("/search?q=" + q));
    }

    @Test
    public void json() throws Exception {
        assertEquals("{\"id\":\"7\"}", get("/api/users/7"));
    }

    private static String get(String path) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) URI
                .create("http://localhost:" + Turismo.port() + path).toURL().openConnection();
        assertEquals(200, conn.getResponseCode());
        return new String(conn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }

}
