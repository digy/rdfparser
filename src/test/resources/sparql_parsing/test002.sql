SELECT bg1.subbook
FROM (SELECT subject as subbook,object as objbook FROM data  WHERE predicate ='http://example.org/publishedBy'  ) as bg1
WHERE bg1.subbook=bg1.subbook and bg1.objbook=bg1.subbook and bg1.subbook=bg1.objbook and bg1.objbook=bg1.objbook 

