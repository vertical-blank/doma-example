SELECT /*%expand*/* 
FROM (
/*%for inf : selectInfos*/
	/*%for suf : inf.suffixes*/
	  (SELECT
		/*%for col : inf.cols*/
		  /*# col */ /*%if col_has_next */, /*%end */
		/*%end*/
	   FROM SEQUENTIAL_/*# suf */
	   WHERE /*# suf */ /*# "=" */ /* suf */1)
	  /*%if suf_has_next */ /*#"UNION ALL"*/ /*%end */
	/*%end*/
	
  /*%if inf_has_next */ /*#"UNION ALL"*/ /*%end */
/*%end*/
)
ORDER BY dat01, dat02
