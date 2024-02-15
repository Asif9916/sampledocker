Hey buddy ! 

I have created a calculator application that uses Java and Python in a separate docker container, and they communicate with each other on a network. 
I have used postman for inputs to the calculator.

ex:

(for Java)
POST: http://localhost:8000/add 
    JSON: 
    {
    "a": 5,
    "b": 3
    }


(for Python)

POST: http://localhost:9000
    JSON
    {
    "operation": "add",
    "a": 5,
    "b": 3
    }
