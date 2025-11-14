import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;

public class Server {

    public static void main(String args[]) throws Exception {

        HttpServer srv = HttpServer.create(new InetSocketAddress(8000), 0);

        srv.createContext("/students", (ex) -> {
            List<Student> list = StudentDatabase.getAll();

            String res = "[";
            for(Student s : list){
                res += "{\"name\":\"" + s.name + "\",\"roll\":" + s.roll + ",\"grade\":\"" + s.grade + "\"},";
            }
            if(res.endsWith(",")) res = res.substring(0, res.length()-1);
            res += "]";

            ex.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            ex.sendResponseHeaders(200, res.getBytes().length);
            ex.getResponseBody().write(res.getBytes());
            ex.close();
        });

        srv.createContext("/add", (ex) -> {
            ex.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

            InputStream is = ex.getRequestBody();
            String body = new String(is.readAllBytes());
            String[] p = body.split("&");

            String name = p[0].split("=")[1];
            int roll = Integer.parseInt(p[1].split("=")[1]);
            String grade = p[2].split("=")[1];

            StudentDatabase.addStudent(new Student(name, roll, grade));

            String msg = "ok";
            ex.sendResponseHeaders(200, msg.length());
            ex.getResponseBody().write(msg.getBytes());
            ex.close();
        });

        srv.createContext("/delete", (ex) -> {
            ex.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

            String q = ex.getRequestURI().getQuery();
            int roll = Integer.parseInt(q.split("=")[1]);

            StudentDatabase.deleteStudent(roll);

            String r = "deleted";
            ex.sendResponseHeaders(200, r.length());
            ex.getResponseBody().write(r.getBytes());
            ex.close();
        });

        srv.start();
    }
}
