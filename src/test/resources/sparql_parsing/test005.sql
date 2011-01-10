SELECT bg1.subx
FROM (SELECT subject as subx FROM data  WHERE predicate ='http://www.w3.org/1999/02/22-rdf-syntax-ns#type' and object ='http://example.org/Merchant'  ) as bg1, 
(SELECT subject as subx FROM data  WHERE predicate ='http://example.org/role' and object ='http://example.org/Manager'  ) as bg2, 
(SELECT subject as subx FROM data  WHERE predicate ='http://example.org/status' and object ='http://example.org/Working'  ) as bg3
WHERE bg1.subx=bg1.subx and bg2.subx=bg1.subx and bg3.subx=bg1.subx and bg1.subx=bg2.subx and bg2.subx=bg2.subx and bg3.subx=bg2.subx and bg1.subx=bg3.subx and bg2.subx=bg3.subx and bg3.subx=bg3.subx 
