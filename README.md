# ueap

> University Enterprise Application Platform

[![Current Release](https://img.shields.io/github/release/balena-io/etcher.svg?style=flat-square)](https://github.com/jaradat-pdb/ueap/)
[![License](https://img.shields.io/github/license/balena-io/etcher.svg?style=flat-square)](https://github.com/jaradat-pdb/ueap/blob/master/LICENSE)

***

## Common Commands
### Application

```sh
java -jar uni-eap-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```
```sh
mvn spring-boot:run -Dspring-boot.run.profiles=dev -X
```

### cURL
```sh
curl -i -X POST -H 'Content-Type:application/json' -w '\n' -v -d '{"addrLine1":"123 Fake Ave", "city":"Springfield", "country":"USA"}' http://localhost:9090/rest/api/address/save
```
```sh
curl -i -X POST -H 'Content-Type:application/json' -w '\n' -v -d '{"fname":"Waleed","mname":"Khaled","lname":"Al-Jaradat","addresses":[{"addrLine1":"123 Fake Ave", "city":"Springfield", "country":"USA"}],"vehicle":{"make":"Subaru","model":"WRX","year":"2004","licensePlate":"XNX5427","vin":"ABCD1234-EFGH5678"}}' http://localhost:9090/rest/api/person/save
```
```sh
curl -i -v -w '\n' -X POST http://localhost:9090/manage/shutdown
```
```sh
curl -i -v -w '\n' -X POST -H 'Content-Type:application/json' -d '{"fname":"Big", "mname":"Poppa", "lname":"Willay", "gender":"MALE", "dob":"1990-08-14", "vehicle":{"make":"Subaru", "model":"Impreza WRX", "year":"2005", "licensePlate":"XNX-5427", "vin":"ARE-YOU-SERIOUS!?"}, "addresses":[{"addrLine1":"124 Fake St", "addrLine2":"Apt L (for lion)", "city":"Crackendale", "state":"BumFuc", "country":"US of Shit", "postalCode":"22150"}, {"addrLine1":"125 Fake St", "addrLine2":"Apt R (for roar)", "city":"PuffPuffPass", "province":"BC", "country":"CA", "postalCode":"22150"}]}' http://localhost:9090/rest/api/person/save
```
```sh
curl -i -v -w '\n' -X GET http://localhost:9090/rest/api/address/find/123%20Fake%20Ave
```
```sh
curl -i -v -w '\n' -X PATCH -H 'Content-Type:application/json' -H 'Accept:application/json' -d '{"fname":"Walid", "mname":"Mousa", "lname":"3ababney", "gender":"MALE"}'  http://localhost:9090/rest/api/address/update/679949c0-c112-41ff-a37e-7389991722a1/person
```
```sh
curl -i -v -w '\n' -X PATCH -H 'Content-Type:application/json' -H 'Accept:application/json' -d '{"name":"NOVA"}' http://localhost:9090/rest/api/address/update/0ad28f86-d67e-481a-8f0e-7a98bde6b090/university
```
```sh
curl -i -v -w '\n' -X PUT -H 'Content-Type:application/json; charset=UTF-8' -d '{"title":"Aeon Flux", "description":"Reactive is the new cool"}' http://localhost:9091/movies
```
The following cURL command will retrieve the [Neo4j APOC Library](https://neo4j.com/developer/neo4j-apoc/), v4.1.0.2-all:
```sh
curl -L https://github.com/neo4j-contrib/neo4j-apoc-procedures/releases/download/4.1.0.2/apoc-4.1.0.2-all.jar -O
```

### Docker
```sh
docker run -p 7474:7474 -p 7687:7687 --volume=/workspace/docker/pdbneo/data:/var/lib/neo4j/data --volume=/workspace/docker/pdbneo/plugins:/var/lib/neo4j/plugins -e NEO4J_AUTH=neo4j/admin --env NEO4J_dbms_security_procedures_unrestricted=apoc.* --name pdbneo -d neo4j:latest
```

***

## External Links
[Docker Neo4j APOC Install](https://community.neo4j.com/t/docker-compose-setting-for-apoc-installation/11621)

[Spring Boot Reactive Tutorial](https://dzone.com/articles/spring-boot-reactive-tutorial)

[GitHub Flavored Markdown Spec](https://github.github.com/gfm/)
