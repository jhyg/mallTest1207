## How to run

- Run axon server and mysql firstly

```
cd infra
docker-compose up
```

## Build common API & Run each service

'''
cd common-api
mvn install
cd ..

cd Food Delivery App
mvn spring-boot:run
cd ..

cd Restaurant
mvn spring-boot:run
cd ..

cd Payment
mvn spring-boot:run
cd ..

cd Customer
mvn spring-boot:run
cd ..

'''
