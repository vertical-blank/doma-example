CREATE TEMP TABLE TMP01 AS ( 
    SELECT
        /*%for col : columns*/
            /*# col */ /*%if col_has_next */, /*%end */
        /*%end*/
    FROM tbl1
);
