import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import org.json.JSONObject;

public class JavaCalculatorServer {
    public static void main(String[] args) throws IOException {
        int port = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/add", new AddHandler());
        server.createContext("/subtract", new SubtractHandler());
        server.createContext("/multiply", new MultiplyHandler());
        server.createContext("/divide", new DivideHandler());
        server.start();
        System.out.println("Java Calculator Server running on port " + port);
    }

    static class AddHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            handleRequest(exchange, "add");
        }
    }

    static class SubtractHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            handleRequest(exchange, "subtract");
        }
    }

    static class MultiplyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            handleRequest(exchange, "multiply");
        }
    }

    static class DivideHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            handleRequest(exchange, "divide");
        }
    }

    static void handleRequest(HttpExchange exchange, String operation) throws IOException {
        if ("POST".equals(exchange.getRequestMethod())) {
            InputStream requestBody = exchange.getRequestBody();
            BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
            StringBuilder requestBodyString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBodyString.append(line);
            }
            JSONObject jsonObject = new JSONObject(requestBodyString.toString());
            int a = jsonObject.getInt("a");
            int b = jsonObject.getInt("b");
            int result;
            switch (operation) {
                case "add":
                    result = Calculator.add(a, b);
                    break;
                case "subtract":
                    result = Calculator.subtract(a, b);
                    break;
                case "multiply":
                    result = Calculator.multiply(a, b);
                    break;
                case "divide":
                    result = Calculator.divide(a, b);
                    break;
                default:
                    result = 0;
            }
            String response = Integer.toString(result);
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } else {
            exchange.sendResponseHeaders(405, -1); // Method Not Allowed
        }
    }
}



