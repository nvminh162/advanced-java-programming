// 1. Tạo unique constraints
CREATE CONSTRAINT FOR (c:Customer) REQUIRE c.id IS UNIQUE;
CREATE CONSTRAINT FOR (b:Booking) REQUIRE b.id IS UNIQUE;
CREATE CONSTRAINT FOR (r:Room) REQUIRE r.id IS UNIQUE;
CREATE CONSTRAINT FOR (bill:Bill) REQUIRE bill.id IS UNIQUE;

SHOW CONSTRAINT

// 2. Load dữ liệu từ CSV
// Load Customer
LOAD CSV WITH HEADERS FROM 'file:///hotel/customers.csv' AS row
CREATE (:Customer {
  id: row.id,
  customerName: row.customerName,
  phoneNumber: row.phoneNumber,
  email: row.email
});

// Load Room
LOAD CSV WITH HEADERS FROM 'file:///hotel/rooms.csv' AS row
CREATE (:Room {
  id: row.id,
  description: row.description,
  type: row.type,
  bedOptions: row.bedOptions,
  sleepsCount: toInteger(row.sleepsCount),
  smokingAllowed: toBoolean(row.smokingAllowed),
  price: toFloat(row.price)
});

// Load Booking
LOAD CSV WITH HEADERS FROM 'file:///hotel/bookings.csv' AS row
CREATE (:Booking {
  id: row.id,
  startDate: row.startDate,
  endDate: row.endDate,
  deposit: toFloat(row.deposit),
  note: row.note,
  customer_id: row.customer_id,
  room_ids: row.room_ids
});

// Load Bill
LOAD CSV WITH HEADERS FROM 'file:///hotel/bills.csv' AS row
CREATE (:Bill {
  id: row.id,
  days: toInteger(row.days),
  serviceFee: toFloat(row.serviceFee),
  totalAmount: toFloat(row.totalAmount),
  booking_id: row.booking_id
});

// 3. Tạo relationships
// Tạo relationship BOOKING_BY
MATCH (b:Booking)
MATCH (c:Customer {id: b.customer_id})
CREATE (c)-[:BOOKING_BY]->(b);

// Tạo relationship BOOKING_FOR
LOAD CSV WITH HEADERS FROM 'file:///hotel/bookings.csv' AS row
MATCH (b:Booking {id: row.id})
MATCH (r:Room {id: row.room_ids})
CREATE (r)-[:BOOKING_FOR]->(b);

// Tạo relationship CREATE_FROM
MATCH (bill:Bill)
MATCH (b:Booking {id: bill.booking_id})
CREATE (bill)-[:CREATE_FROM]->(b);