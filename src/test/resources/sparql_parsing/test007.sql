SELECT bg1.objx
FROM (SELECT object as objx FROM data  WHERE subject ='http://example.org/Jonh' and predicate ='http://example.org/likes'  ) as bg1, 
(SELECT object as objx FROM data  WHERE subject ='http://example.org/Will' and predicate ='http://example.org/likes'  ) as bg2
WHERE bg1.objx=bg1.objx and bg2.objx=bg1.objx and bg1.objx=bg2.objx and bg2.objx=bg2.objx 
