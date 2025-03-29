CREATE
  (p1:Person {name: 'Alice', age: 25}),
  (p2:Person {name: 'Bob', age: 30}),
  (p3:Person {name: 'Charlie', age: 22}),
  (p4:Person {name: 'David', age: 35}),
  (p5:Person {name: 'Eve', age: 28}),
  (p6:Person {name: 'Frank', age: 40}),
  (p7:Person {name: 'Grace', age: 27}),
  (p8:Person {name: 'Henry', age: 32}),
  (p9:Person {name: 'Ivy', age: 29}),
  (p10:Person {name: 'Jack', age: 31}),

  (a1:Address {street: '123 Main St', city: 'New York', state: 'NY'}),
  (a2:Address {street: '456 Elm Ave', city: 'London', country: 'UK'}),
  (a3:Address {street: '789 Oak Ln', city: 'Paris', country: 'France'}),
  (a4:Address {street: '101 Pine Ct', city: 'Sydney', state: 'NSW'}),
  (a5:Address {street: '222 Maple Dr', city: 'Rome', country: 'Italy'}),
  (a6:Address {street: '333 Willow Rd', city: 'Berlin', country: 'Germany'}),
  (a7:Address {street: '444 Birch Pl', city: 'Madrid', country: 'Spain'}),
  (a8:Address {street: '555 Cedar Cir', city: 'Tokyo', country: 'Japan'}),
  (a9:Address {street: '666 Redwood St', city: 'Toronto', country: 'Canada'}),
  (a10:Address {street: '777 Oak Ave', city: 'Sao Paulo', country: 'Brazil'}),

  (p1)-[:FRIENDS_WITH]->(p2),
  (p1)-[:FRIENDS_WITH]->(p3),
  (p1)-[:FRIENDS_WITH]->(p6),
  (p2)-[:FRIENDS_WITH]->(p4),
  (p3)-[:FRIENDS_WITH]->(p5),
  (p4)-[:FRIENDS_WITH]->(p2),
  (p4)-[:FRIENDS_WITH]->(p6),
  (p4)-[:FRIENDS_WITH]->(p9),
  (p5)-[:FRIENDS_WITH]->(p7),
  (p6)-[:FRIENDS_WITH]->(p8),
  (p7)-[:FRIENDS_WITH]->(p3),
  (p7)-[:FRIENDS_WITH]->(p5),
  (p7)-[:FRIENDS_WITH]->(p9),
  (p7)-[:FRIENDS_WITH]->(p10),
  (p8)-[:FRIENDS_WITH]->(p10),
  (p9)-[:FRIENDS_WITH]->(p1),
  (p9)-[:FRIENDS_WITH]->(p6),
  (p10)-[:FRIENDS_WITH]->(p4),

  (p1)-[:LIVES_AT]->(a1),
  (p2)-[:LIVES_AT]->(a2),
  (p3)-[:LIVES_AT]->(a3),
  (p4)-[:LIVES_AT]->(a4),
  (p5)-[:LIVES_AT]->(a5),
  (p6)-[:LIVES_AT]->(a6),
  (p7)-[:LIVES_AT]->(a7),
  (p8)-[:LIVES_AT]->(a8),
  (p9)-[:LIVES_AT]->(a9),
  (p10)-[:LIVES_AT]->(a10);

//relationship
MATCH (a:Person {name: 'Jack'}), (b:Person {name: 'Ivy'})
CREATE (a)-[:FRIENDS_WITH]->(b);

//delete relationship
MATCH (a:Person{name:'Jack'})-[r:FRIENDS_WITH]->(b:Person{name:'Ivy'}) DELETE r;

//MATCH (p:Person)-[r:LIVES_AT]->(a:Address{city:'Paris'}) RETURN r;

//create index
CREATE INDEX INDEX_PERSON_NAME FOR (p:Person) ON (p.Name);