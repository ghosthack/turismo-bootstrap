package com.example.servlet;

import com.example.Html;

import io.github.ghosthack.turismo.action.Action;
import io.github.ghosthack.turismo.routes.RoutesList;

/**
 * Routes for servlet deployment. {@link RoutesList} supports wildcards and
 * named parameters; use {@code RoutesMap} instead for exact-match paths only.
 */
public class AppRoutes extends RoutesList {

    @Override
    protected void map() {
        get("/", new Action() {
            @Override
            public void run() {
                print("Hello World!");
            }
        });
        get("/wild/*/card/:id", new Action() {
            @Override
            public void run() {
                print("id " + Html.htmlEscape(params("id")));
            }
        });
        get("/redir1", new Action() {
            @Override
            public void run() {
                // 301 Moved Permanently
                movedPermanently("/dest");
            }
        });
        get("/redir2", new Action() {
            @Override
            public void run() {
                // 302 Found
                redirect("/dest");
            }
        });
        get("/dest", new Action() {
            @Override
            public void run() {
                print("Hello Redirect");
            }
        });
        post("/search", new Action() {
            @Override
            public void run() {
                String query = req().getParameter("q");
                print("Your search query was: " + Html.htmlEscape(query));
            }
        });
        route(new Action() {
            @Override
            public void run() {
                notFound();
            }
        });
    }

}
