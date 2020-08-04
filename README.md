# ueap
University Enterprise Application Platform

### Common cURL Commands
`curl -i -X POST -H 'Content-Type:application/json' -w '\n' -v -d '{"addrLine1":"123 Fake Ave", "city":"Springfield", "country":"USA"}' http://localhost:9090/rest/api/address/save`

`curl -i -X POST -H 'Content-Type:application/json' -w '\n' -v -d '{"fname":"Waleed","mname":"Khaled","lname":"Al-Jaradat","addresses":[{"addrLine1":"123 Fake Ave", "city":"Springfield", "country":"USA"}],"vehicle":{"make":"Subaru","model":"WRX","year":"2004","licensePlate":"XNX5427","vin":"ABCD1234-EFGH5678"}}' http://localhost:9090/rest/api/person/save`

`curl -i -v -w '\n' -X POST http://localhost:9090/manage/shutdown`

`curl -i -v -w '\n' -X POST -H 'Content-Type:application/json' -d '{"fname":"Big", "mname":"Poppa", "lname":"Willay", "gender":"MALE", "dob":"1990-08-14", "vehicle":{"make":"Subaru", "model":"Impreza WRX", "year":"2005", "licensePlate":"XNX-5427", "vin":"ARE-YOU-SERIOUS!?"}, "addresses":[{"addrLine1":"124 Fake St", "addrLine2":"Apt L (for lion)", "city":"Crackendale", "state":"BumFuc", "country":"US of Shit", "postalCode":"22150"}, {"addrLine1":"125 Fake St", "addrLine2":"Apt R (for roar)", "city":"PuffPuffPass", "province":"BC", "country":"CA", "postalCode":"22150"}]}' http://localhost:9090/rest/api/person/save`

`curl -i -v -w '\n' -X GET http://localhost:9090/rest/api/address/find/123%20Fake%20Ave

`curl -i -v -w '\n' -X PATCH -H 'Content-Type:application/json' -H 'Accept:application/json' -d '{"fname":"Walid", "mname":"Mousa", "lname":"3ababney", "gender":"MALE"}'  http://localhost:9090/rest/api/address/update/679949c0-c112-41ff-a37e-7389991722a1/person`

`curl -i -v -w '\n' -X PATCH -H 'Content-Type:application/json' -H 'Accept:application/json' -d '{"name":"NOVA"}' http://localhost:9090/rest/api/address/update/0ad28f86-d67e-481a-8f0e-7a98bde6b090/university`
