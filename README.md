# ueap
University Enterprise Application Platform

`curl -i -X POST -H 'Content-Type:application/json' -w '\n' -v -d '{"addrLine1":"123 Fake Ave", "city":"Springfield", "country":"USA"}' http://localhost:9090/rest/api/address/save`

`curl -i -X POST -H 'Content-Type:application/json' -w '\n' -v -d '{"fname":"Waleed","mname":"Khaled","lname":"Al-Jaradat","addresses":[{"addrLine1":"123 Fake Ave", "city":"Springfield", "country":"USA"}],"vehicle":{"make":"Subaru","model":"WRX","year":"2004","licensePlate":"XNX5427","vin":"ABCD1234-EFGH5678"}}' http://localhost:9090/rest/api/person/save`

`curl -i -v -w '\n' -X POST http://localhost:9090/manage/shutdown`
