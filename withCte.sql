/****** Скрипт для команды SelectTopNRows из среды SSMS  ******/
--select * from [dbo].[users]

--select * from [dbo].[departments]


WITH
  cteDepsByParent (id, id_parent, NameDep)
  AS
  (SELECT id, id_parent, NameDep
    FROM [dbo].[departments]
    WHERE id = 11

    UNION ALL

    SELECT e.id, e.id_parent, e.NameDep
    FROM [dbo].[departments] e
      INNER JOIN cteDepsByParent r
        ON e.id_parent = r.id
  )
SELECT * FROM cteDepsByParent
