SELECT bg1.prex
FROM (SELECT predicate as prex FROM data  WHERE subject ='http://example.org/Jonh' and object ='http://example.org/Marry'  ) as bg1, 
(SELECT predicate as prex FROM data  WHERE subject ='http://example.org/Will' and object ='http://example.org/John'  ) as bg2
WHERE bg1.prex=bg1.prex and bg2.prex=bg1.prex and bg1.prex=bg2.prex and bg2.prex=bg2.prex 
