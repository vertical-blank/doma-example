SELECT /*%expand*/* 
FROM (
/*%for suf : table1suffixes*/
  (SELECT
	/*%for col : table1cols*/
	  /*# col */ /*%if col_has_next */, /*%end */
	/*%end*/
   FROM SEQUENTIAL_/*# suf */) sb/*# suf */
  /*%if suf_has_next */ /*#"UNION ALL"*/ /*%end */
/*%end*/

UNION ALL

/*%for suf : table2suffixes*/
   (SELECT
	/*%for col : table2cols*/
	  /*# col */ /*%if col_has_next */, /*%end */
	/*%end*/
   FROM SEQUENTIAL_/*# suf */) sb/*# suf */
  /*%if suf_has_next */ /*#"UNION ALL"*/ /*%end */
/*%end*/

)
ORDER BY dat01, dat02
