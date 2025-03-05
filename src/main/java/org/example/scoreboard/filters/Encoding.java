package org.example.scoreboard.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(value = {
        "/*"
})
public class Encoding extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        // res.setCharacterEncoding("UTF-8");
        // req.setCharacterEncoding("UTF-8");

        System.out.println("reqURI " + req.getRequestURI());
        super.doFilter(req, res, chain);
    }
}
