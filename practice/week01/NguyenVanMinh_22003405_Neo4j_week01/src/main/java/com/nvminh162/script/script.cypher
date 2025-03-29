// Nguyen Van Minh - 22003405 - DHKTPM18A

// #1. Phân tích heading row của file CSV.
//    city,         loc/x,      loc/y,      pop,    state,    code
//    AGAWAM,       -72.622739, 42.070206,  15338,  MA,       1001
//    CUSHMAN,      -72.51565,  42.377017,  36963,  MA,       1002
//    BARRE,        -72.108354, 42.409698,  4546,   MA,       1005
//    BELCHERTOWN,  -72.410953, 42.275103,  10579,  MA,       1007
//    BLANDFORD,    -72.936114, 42.182949,  1240,   MA,       1008
//    BRIMFIELD,    -72.188455, 42.116543,  3706,   MA,       1010
//    CHESTER,      -72.988761, 42.279421,  1688,   MA,       1011

// #2. Tạo ràng buộc cho cac trường dữ liệu. <Tuỳ đề bài yêu cầu tạo ràng buộc nào/>
  // Tạo ràng buộc (constraint) đảm bảo rằng thuộc tính 'code' của node có nhãn 'Zip' là DUY NHẤT
  CREATE CONSTRAINT zip_code_uq FOR (z:Zip) REQUIRE z.code IS UNIQUE;
  // Tạo ràng buộc đảm bảo rằng thuộc tính 'code' của node có nhãn 'Zip' KHÔNG ĐƯỢC ĐỂ TRỐNG.
  CREATE CONSTRAINT zip_code_notnull FOR (z:Zip) REQUIRE z.code IS NOT NULL;
  // Hiển thị danh sách các ràng buộc hiện có trong cơ sở dữ liệu.
  SHOW CONSTRAINT;

// #3. Tải dữ liệu từ file CSV.
  //---------------------------------------------------------------------
  // LOAD: Tải dữ liệu từ file CSV có tiêu đề.
  // WITH: Tạo node mới có nhãn 'Zip' và gán giá trị 'code', chuyển đổi sang kiểu số nguyên.
  // CREATE: Tạo node mới có nhãn 'Zip' và gán giá trị 'code', chuyển đổi sang kiểu số nguyên.
  // SET: Gán giá trị cho các thuộc tính 'city', 'loc_x', 'loc_y', 'pop', 'state' của node 'Zip'.
  //---------------------------------------------------------------------
  LOAD CSV WITH HEADERS FROM 'file:///zips.csv' AS row
  WITH row WHERE row.code IS NOT NULL
  CREATE (z:Zip {code: toInteger(row.code)})
  SET z.city = row.city,
      z.loc_x = toFloat(row.`loc/x`),
      z.loc_y = toFloat(row.`loc/y`),
      z.pop = toInteger(row.pop),
      z.state = row.state;
  // Sau khi load data kiểm tra đã có chưa bằng cach select => vì data lớn nên chỉ gioi hạn 25 node.
  MATCH (z:Zip) RETURN z LIMIT 25;

// #4. Exercise
  //  4.1. List n nodes
  MATCH (z:Zip) RETURN z LIMIT 5

  //  4.2. Create a new node
  // Cách 1:
  CREATE (z:Zip {code: 9999})
  SET z.city = 'TP HCM',
      z.loc_x = 10.762622,
      z.loc_y = 106.660172,
      z.pop = 100000000,
      z.state = 'VN'
  // Cách 2:
  CREATE (z:Zip {code: 9999, city: 'TP HCM', loc_x: 10.762622, loc_y: 106.660172, pop: 100000000, state: 'VN'})

  //  4.3. Tìm một node khi biết zip code
  MATCH (z:Zip {code: 9999})
  RETURN z

  //  4.4. Cập nhật thông tin của một node khi biết zip code
  MATCH (z:Zip {code: 9999})
  SET z.city = 'Dong Nai Province'
  RETURN z

  //  4.5. Xóa một node khi biết zip code => Detach delete relationship and without can't delete if have relationship
  MATCH (z:Zip {code: 9999})
  DELETE z

  //  4.6. Xóa tất cả các node => Detach delete relationship and without can't delete if have relationship
  MATCH (z:Zip)
  DELETE z

  MATCH (z:Zip)
  DELETE DETACH z

  //  4.7. Tìm các node có city là PALMER
  MATCH (z:Zip {city: 'PALMER'})
  RETURN z

  //  4.8. Tìm các node có dân số >100000
  MATCH (z:Zip)
  WHERE z.pop > 100000
  RETURN z

  //  4.9. Tìm dân số của thành phố FISHERS ISLAND
  MATCH (z:Zip {city: 'FISHERS ISLAND'})
  RETURN z.pop //chỉ định trường hiển thị dân số

  //  4.10. Tìm các thành phố có dân số từ 10 – 50
  MATCH (z:Zip)
  WHERE z.pop >= 10 AND z.pop <= 50
  RETURN z.city

  //  4.11. Tìm tất cả các city của bang MA có dân số trên 500
  MATCH (z:Zip)
  WHERE z.state = 'MA' AND z.pop > 500
  RETURN z.city

  //  4.12. Tìm tất cả các bang (không trùng)
  MATCH (z:Zip)
  RETURN DISTINCT z.state

  //  4.13. Tính dân số trung bình của mỗi bang
  // *** Suggestions:
  //                 Select state, avg(pop) as avg_pop
  //                 From Zips
  //                 Group by state
  MATCH (z:Zip)
  RETURN z.state, avg(z.pop) as avg_pop

  //  4.14. Tìm những document của bang 'CT' và thành phố 'WATERBURY'
  MATCH (z:Zip {state: 'CT', city: 'WATERBURY'})
  RETURN z

  //  4.15. Bang WA có bao nhiêu city (nếu trùng chỉ tính 1 lần)
  MATCH (z:Zip {state: 'WA'})
  RETURN count(DISTINCT z.city)

  //  4.16. Tổng dân số của từng bang, sắp xếp theo tổng dân số giảm dần
  MATCH (z:Zip)
  WITH z.state as state, sum(z.pop) as total_pop
  ORDER BY total_pop DESC
  RETURN state, total_pop

  //  4.17. Tìm tất cả các bang có tổng dân số trên 10000000
  MATCH (z:Zip)
  WITH z.state as state, sum(z.pop) as total_pop
  WHERE total_pop > 10000000
  RETURN state, total_pop

  //  4.18. Tìm các node có dân số
  //  lớn nhất
  MATCH (z:Zip)
  RETURN z
  ORDER BY z.pop DESC
  LIMIT 1
  //  nhỏ nhất
  MATCH (z:Zip)
  WHERE z.pop IS NOT NULL
  RETURN z
  ORDER BY z.pop ASC
  LIMIT 1

  //  4.19. Tìm bang có tổng dân số
  //  lớn nhất CA
  MATCH (z:Zip)
  WITH z.state AS state, SUM(z.pop) AS total_pop
  ORDER BY total_pop DESC
  LIMIT 1
  RETURN state, total_pop
  //  nhỏ nhất CA
  MATCH (z:Zip)
  WITH z.state AS state, SUM(z.pop) AS total_pop
  WHERE total_pop > 0
  ORDER BY total_pop ASC
  LIMIT 1
  RETURN state, total_pop


// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// # Other
  // *** CREATE:
  // 1. CRUD
  // *** CREATE:
  CREATE (z:Zip {code: 22003405, city: 'Dong Nai City', state: 'VNA'})
  // *** READ:
  MATCH (z:Zip {code: 22003405}) RETURN z
  // *** UPDATE:
  MATCH (z:Zip {code: 22003405})
  SET z.pop = 15000
  RETURN z
  // *** DELETE:
  MATCH (z:Zip {code: 22003405})
  DELETE z
  //Zip với code là 22003405. Nếu node này có mối quan hệ với các node khác, bạn cần dùng:
  MATCH (z:Zip {code: 22003405})
  DETACH DELETE z
  // DROP CONSTRAINT => DROP CONSTRAINT `constraint_name_example`;
  DROP CONSTRAINT uq_zip_code IF EXISTS;
  DROP CONSTRAINT zip_code_notnull IF EXISTS;
