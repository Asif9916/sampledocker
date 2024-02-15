Hey buddy ! 

I have created a calculator application that uses Java and Python in a separate docker container, and they are hosted over a common docker network. 
I have used postman for inputs to the calculator.


#Download Docker Desktop.
run command docker compose up -d


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
