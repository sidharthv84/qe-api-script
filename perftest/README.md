

./gradlew clean build -x test

To run Load test: 
gradle runLoadTest

To run specific endpoint
./gradlew runLoadTest -Dendpoint=http://localhost:8086/test/sid/test4

Reports are generated in build --> gatling-results --> Load Simulation --> index.html




