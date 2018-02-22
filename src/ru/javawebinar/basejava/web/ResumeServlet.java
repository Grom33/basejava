package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResumeServlet extends HttpServlet {
    protected Storage storage = new SqlStorage(
            "jdbc:postgresql://192.168.100.12:5432/Resume",
            "postgres",
            "postgres");

    public ResumeServlet() throws ClassNotFoundException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String uuid = request.getParameter("uuid");

        if (uuid == null) {
            response.getWriter().write("<table border=\"1\">");
            response.getWriter().write("<tr>");
            response.getWriter().write("<th>NAME</th>");
            response.getWriter().write("<th>UUID</th>");
            response.getWriter().write("</tr>");

            List<Resume> resumeList = new ArrayList<>();
            try {
                resumeList = storage.getAllSorted();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resumeList.stream().peek((resume) -> {
                try {
                    response.getWriter().write(HtmlHelper.tableRow(HtmlHelper.tableData(resume.getFullName())
                            + HtmlHelper.tableData(resume.getUuid())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).collect(Collectors.toList());
            response.getWriter().write("</table>");
        } else {
            Resume resume = new Resume();
            try {
                resume = storage.get(uuid);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.getWriter().write(HtmlHelper.header(resume.getFullName()));
            response.getWriter().write(HtmlHelper.paragraph(resume.getUuid()));
        }
    }


    public static class HtmlHelper {
        public static String header(String value) {
            return "<h1>" + value + "</h1>";
        }

        public static String paragraph(String value) {
            return "<p>" + value + "<p>";
        }

        public static String tableData(String value) {
            return "<td>" + value + "</td>";
        }

        public static String tableRow(String value) {
            return "<tr>" + value + "</tr>";
        }
    }
}
