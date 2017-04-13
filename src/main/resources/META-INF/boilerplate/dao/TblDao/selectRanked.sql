SELECT
  v1,
  v2,
  first_value(v2) OVER (PARTITION BY v1 ORDER BY v2),
  rank() OVER (PARTITION BY v1 ORDER BY v2)
FROM tbl1;
