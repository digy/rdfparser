SELECT bg1.subx
FROM (SELECT subject as subx FROM data  WHERE predicate ='http://example.org/a' and object ='http://example.org/b'  ) as bg1, 
(SELECT predicate as prex FROM data  WHERE subject ='http://example.org/a' and object ='http://example.org/b'  ) as bg2, 
(SELECT object as objx FROM data  WHERE subject ='http://example.org/a' and predicate ='http://example.org/b'  ) as bg3
WHERE bg1.subx=bg1.subx and bg2.prex=bg1.subx and bg3.objx=bg1.subx and bg1.subx=bg2.prex and bg2.prex=bg2.prex and bg3.objx=bg2.prex and bg1.subx=bg3.objx and bg2.prex=bg3.objx and bg3.objx=bg3.objx 
