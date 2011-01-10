SELECT bg1.subbook
FROM (SELECT subject as subbook,predicate as prebook,object as objbook FROM data  ) as bg1
WHERE bg1.subbook=bg1.subbook and bg1.objbook=bg1.subbook and bg1.prebook=bg1.subbook and bg1.subbook=bg1.objbook and bg1.objbook=bg1.objbook and bg1.prebook=bg1.objbook and bg1.subbook=bg1.prebook and bg1.objbook=bg1.prebook and bg1.prebook=bg1.prebook 

