SELECT bg1.subx,bg2.objy,bg4.objz
FROM (SELECT subject as subx FROM data  WHERE predicate ='http://www.w3.org/1999/02/22-rdf-syntax-ns#type' and object ='http://example.org/People'  ) as bg1, 
(SELECT subject as subx,object as objy FROM data  WHERE predicate ='http://example.org/friend'  ) as bg2, 
(SELECT subject as suby FROM data  WHERE predicate ='http://www.w3.org/1999/02/22-rdf-syntax-ns#type' and object ='http://example.org/People'  ) as bg3, 
(SELECT subject as suby,object as objz FROM data  WHERE predicate ='http://example.org/relationship'  ) as bg4
WHERE bg1.subx=bg1.subx and bg2.subx=bg1.subx and bg1.subx=bg2.subx and bg2.subx=bg2.subx and bg2.objy=bg2.objy and bg3.suby=bg2.objy and bg4.suby=bg2.objy and bg2.objy=bg3.suby and bg3.suby=bg3.suby and bg4.suby=bg3.suby and bg2.objy=bg4.suby and bg3.suby=bg4.suby and bg4.suby=bg4.suby and bg4.objz=bg4.objz 
