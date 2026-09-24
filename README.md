# turismo-bootstrap

A starter project for [turismo](https://github.com/ghosthack/turismo), the
lightweight Sinatra/Express-style Java web framework. Clone it, rename the
`com.example` package, and start adding routes.

Requires Java 17+ and Maven.

## Run

The main app uses turismo's built-in server (the JDK HTTP server, so it needs
no container):

```sh
mvn compile exec:java
```

Then try:

- http://localhost:8080/hello
- http://localhost:8080/users/42
- http://localhost:8080/search?q=turismo
- http://localhost:8080/api/users/7

Routes live in [`App.java`](src/main/java/com/example/App.java).

## Servlet deployment

[`com.example.servlet`](src/main/java/com/example/servlet) shows the same
framework deployed as a servlet, here in embedded Jetty:

```sh
mvn compile exec:java -Dexec.mainClass=com.example.servlet.ServletMain
```

To deploy in a Jakarta EE 10 container (Tomcat 10.1+, Jetty 12+, ...)
instead, register the servlet in `web.xml`:

```xml
<servlet>
  <servlet-name>app</servlet-name>
  <servlet-class>io.github.ghosthack.turismo.servlet.Servlet</servlet-class>
  <init-param>
    <param-name>routes</param-name>
    <param-value>com.example.servlet.AppRoutes</param-value>
  </init-param>
</servlet>
<servlet-mapping>
  <servlet-name>app</servlet-name>
  <url-pattern>/*</url-pattern>
</servlet-mapping>
```

If you only use the built-in server, you can delete `com.example.servlet`
and the Jetty dependency from `pom.xml`.

## Escaping output

`print()` writes text exactly as given. When a response is HTML, escape any
request data before printing it, as the examples do with
[`Html.htmlEscape`](src/main/java/com/example/Html.java). Otherwise a value
like `?q=<script>...</script>` runs in the visitor's browser (cross-site
scripting). `json()` needs no escaping.

## Test

```sh
mvn test
```

[`AppTest`](src/test/java/com/example/AppTest.java) starts the app on a random
port and checks real HTTP responses.

## License

Apache License 2.0. See [LICENSE.txt](LICENSE.txt).
