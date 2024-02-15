# Dockerfile for Java Calculator HTTP Server

FROM openjdk:latest

WORKDIR /app

# Copy the Java source files
COPY JavaCalculatorServer.java .
COPY Calculator.java .

# Download the JSON library and add it to the classpath
RUN curl -O https://repo1.maven.org/maven2/org/json/json/20210307/json-20210307.jar

# Compile the Java source files with the JSON library and Calculator class
RUN javac -cp json-20210307.jar JavaCalculatorServer.java Calculator.java

CMD ["java", "-cp", "json-20210307.jar:.","JavaCalculatorServer"]
