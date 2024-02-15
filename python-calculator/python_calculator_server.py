from http.server import BaseHTTPRequestHandler, HTTPServer
import json

class OperationHandler(BaseHTTPRequestHandler):
    def do_POST(self):
        content_length = int(self.headers['Content-Length'])
        post_data = self.rfile.read(content_length)
        data = json.loads(post_data)
        operation = data['operation']
        a = data['a']
        b = data['b']
        result = self.execute_operation(operation, a, b)
        self.send_response(200)
        self.send_header('Content-type', 'text/plain')
        self.end_headers()
        self.wfile.write(str(result).encode())

    def execute_operation(self, operation, a, b):
        if operation == 'add':
            return Calculator.add(a, b)
        elif operation == 'subtract':
            return Calculator.subtract(a, b)
        elif operation == 'multiply':
            return Calculator.multiply(a, b)
        elif operation == 'divide':
            return Calculator.divide(a, b)

class Calculator:
    @staticmethod
    def add(a, b):
        return a + b

    @staticmethod
    def subtract(a, b):
        return a - b

    @staticmethod
    def multiply(a, b):
        return a * b

    @staticmethod
    def divide(a, b):
        if b == 0:
            raise ValueError("Cannot divide by zero")
        return a / b

def run(server_class=HTTPServer, handler_class=OperationHandler, port=9000):
    server_address = ('', port)
    httpd = server_class(server_address, handler_class)
    print(f'Python Calculator Server running on port {port}')
    httpd.serve_forever()

if __name__ == "__main__":
    run()
    