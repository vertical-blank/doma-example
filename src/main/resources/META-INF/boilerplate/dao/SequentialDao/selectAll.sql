SELECT 
  id, dat01, dat02
FROM (
/*%for suf : suffixes*/
  (SELECT * FROM SEQUENTIAL_/*# suf */) 
  /*%if suf_has_next */ /*#"UNION ALL"*/ /*%end */
/*%end*/
) x
WHERE
/*%for node : nodes*/ /*%if !node.isParam() */ /*#node.toString()*/ /*%else*/ /*node.classify()*/NULL /*%end */ /*%end*/
