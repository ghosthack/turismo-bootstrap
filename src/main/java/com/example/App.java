package com.example;

import static io.github.ghosthack.turismo.Turismo.*;

import java.util.Map;

/**
 * A turismo app using the built-in JDK HTTP server (no servlet container).
 *
 * <p>Run with {@code mvn compile exec:java}, then open
 * http://localhost:8080/hello
 */
public class App {

    public static void main(String[] args) {
        routes();
        start(8080);
    }

    /** Registers the app's routes. Kept separate so tests can reuse it. */
    public static void routes() {
        get("/hello", "Hello World!");

        // Named path parameter: /users/42
        get("/users/:id", () -> print("User ", Html.htmlEscape(param("id"))));

        // Query parameter: /search?q=turismo
        // Always escape request data before writing it into an HTML page.
        get("/search", () -> {
            type("text/html; charset=utf-8");
            print("<p>Your search query was: ", Html.htmlEscape(param("q")), "</p>");
        });

        // JSON responses need no escaping: the serializer quotes values
        get("/api/users/:id", () -> json(Map.of("id", param("id"))));

        // POST routes default to 201 Created
        post("/users", () -> json(Map.of("created", true)));

        // Redirects
        get("/old", () -> movedPermanently("/hello"));
        get("/go", () -> redirect("/hello"));

        notFound(() -> {
            status(404);
            print("Nothing here");
        });
    }

}
