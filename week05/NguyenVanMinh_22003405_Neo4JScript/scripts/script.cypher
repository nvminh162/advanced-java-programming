MATCH (n:Person)
RETURN n.name;

//Load CSV
//LOAD CSV WITH HEADERS FROM 'file:///zips.csv' AS row
//CREATE (z:Zip {code: toInteger(row.code)})
//SET z.city = row.city,
//    z.loc = point({x: toFloat(row.`loc/x`), y: toFloat(row.`loc/y`)}),
//    z.pop = toInteger(row.pop),
//    z.state = row.state;

LOAD CSV WITH HEADERS FROM 'file:///zips.csv' AS row
CREATE (z:Zip {code: toInteger(row.code)})
SET z.city = row.city,
    z.pop = toInteger(row.pop),
    z.state = row.state,
    z.loc_x = toFloat(row.`loc/x`),
    z.loc_y = toFloat(row.`loc/y`);

MATCH (n:Zip) RETURN n LIMIT 10;

CREATE CONSTRAINT uq_zip_code FOR (z:Zip) REQUIRE z.code IS UNIQUE;

SHOW CONSTRAINT;

//2
CREATE (z:Zip{code:12345})
SET z.city='New York',
z.state = 'NY',
z.pop = 100000;

//3
MATCH (z:Zip {code:12345})
RETURN z;

//4
MATCH (z:Zip{code:1012})
SET z.pop = 200
RETURN z;

MATCH (n:Zip{code:12345}) RETURN n LIMIT 10;

//5
MATCH(z:Zip{code:12345})
DELETE z;

//6
MATCH (z:Zip)
DELETE z;

//7
MATCH (z:Zip {city: "PALMER"})
RETURN z;

//8
MATCH (z:Zip)
WHERE z.pop > 10000
RETURN z
LIMIT 20;

//9
MATCH (z:Zip {city: "FISHERS ISLAND"})
RETURN z.pop;

//10
MATCH (z:Zip)
WHERE z.pop >= 10 AND z.pop <= 50
RETURN z
LIMIT 20;

//11
MATCH (z:Zip {state: "MA"})
WHERE z.pop > 500
RETURN z.city, z.pop;

//12
MATCH (z:Zip)
RETURN DISTINCT z.state;

//13
MATCH (z:Zip)
RETURN z.state, avg(z.pop) AS avg_pop
ORDER BY avg_pop;

//14
MATCH (z:Zip)
WHERE z.state = "CT" AND z.city = "WATERBURY"
RETURN z;

//15
MATCH (z:Zip)
WHERE z.state = "WA"
RETURN COUNT(DISTINCT z.city) AS unique_cities;

//16
MATCH (z:Zip)
RETURN z.state, SUM(z.pop) AS total_pop
ORDER BY total_pop DESC;

//17
MATCH (z:Zip)
WITH z.state AS state, SUM(z.pop) AS total_pop
WHERE total_pop > 10000000
RETURN state, total_pop;

//18
MATCH (z:Zip)
WITH max(z.pop) as max_pop
MATCH (z:Zip{pop:max_pop})
RETURN z;

//19
MATCH (z:Zip)
WITH z.state as state, sum(z.pop) as total_pop
WITH max(total_pop) as max_pop, collect({state:state, total_pop:total_pop}) as states //collect giữ lại tập nguồn
UNWIND states as temp
WITH temp, max_pop WHERE temp.total_pop = max_pop
RETURN temp.state;
//-------------------------------

//----------------------------------------------------------------------------------------------------------
//18
//LON
MATCH (z:Zip)
RETURN z
ORDER BY z.pop DESC
LIMIT 1;
//NHO
MATCH (z:Zip)
RETURN z
ORDER BY z.pop ASC
LIMIT 1;

//19
//LON
MATCH (z:Zip)
RETURN z.state, SUM(z.pop) AS total_pop
ORDER BY total_pop DESC
LIMIT 1;
//NHO
MATCH (z:Zip)
RETURN z.state, SUM(z.pop) AS total_pop
ORDER BY total_pop ASC
LIMIT 1;