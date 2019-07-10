CREATE DEFINER=`root`@`localhost` FUNCTION `UC_Words`(
	`str` VARCHAR(255) 
)
RETURNS varchar(255) CHARSET utf8
LANGUAGE SQL
DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN  
  DECLARE c CHAR(1);  
  DECLARE s VARCHAR(255);  
  DECLARE i INT DEFAULT 1;  
  DECLARE bool INT DEFAULT 1;  
  DECLARE punct CHAR(17) DEFAULT ' ()[]{},.-_!@;:?/';  
  SET s = LCASE( str );  
  WHILE i < LENGTH( str ) DO  
     BEGIN  
       SET c = SUBSTRING( s, i, 1 );  
       IF LOCATE( c, punct ) > 0 THEN  
        SET bool = 1;  
      ELSEIF bool=1 THEN  
        BEGIN  
          IF c >= 'a' AND c <= 'z' THEN  
             BEGIN  
               SET s = CONCAT(LEFT(s,i-1),UCASE(c),SUBSTRING(s,i+1));  
               SET bool = 0;  
             END;  
           ELSEIF c >= '0' AND c <= '9' THEN  
            SET bool = 0;  
          END IF;  
        END;  
      END IF;  
      SET i = i+1;  
    END;  
  END WHILE;  
  RETURN s;  
END





UPDATE cidade SET nome = UC_Words(nome);

UPDATE cidade SET nome = REPLACE(nome,'D\'oeste','D\'Oeste');
UPDATE cidade SET nome = REPLACE(nome,' De ',' de ');
UPDATE cidade SET nome = REPLACE(nome,' Da ',' da ');
UPDATE cidade SET nome = REPLACE(nome,' Do ',' do ');
UPDATE cidade SET nome = REPLACE(nome,' Das ',' das ');
UPDATE cidade SET nome = REPLACE(nome,' Dos ',' dos ');