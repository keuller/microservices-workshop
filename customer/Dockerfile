FROM openjdk:11.0.3-jdk

LABEL "Author" "Keuller Magalhaes"

COPY $PWD/target/customer-1.0.0-SNAPSHOT.jar /customer.jar

EXPOSE 8080

CMD ["java", "-Xmx256m", "-jar", "/customer.jar"]
