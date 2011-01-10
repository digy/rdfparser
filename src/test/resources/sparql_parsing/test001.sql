SELECT bg1.subbook,bg1.objauthor,bg2.objname
FROM (SELECT subject as subbook,object as objauthor FROM data  WHERE predicate ='http://example.org/publishedBy'  ) as bg1, 
(SELECT subject as subauthor,object as objname FROM data  WHERE predicate ='http://example.org/Name'  ) as bg2
WHERE bg1.subbook=bg1.subbook and bg1.objauthor=bg1.objauthor and bg2.subauthor=bg1.objauthor and bg1.objauthor=bg2.subauthor and bg2.subauthor=bg2.subauthor and bg2.objname=bg2.objname 

