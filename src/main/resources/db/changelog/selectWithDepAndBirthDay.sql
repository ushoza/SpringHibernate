declare @dep bigint
declare @days int

set @dep = 1
set @days = 7;

WITH
  cteDepsByParent (id, id_parent, NameDep)
  AS
  (SELECT id, id_parent, NameDep
    FROM departments
    WHERE id = @dep

    UNION ALL

    SELECT e.id, e.id_parent, e.NameDep
    FROM departments e
      INNER JOIN cteDepsByParent r
        ON e.id_parent = r.id
  )

	select 
		id,
		first_name, 
		last_name, 
		department, 
		birthday,
		[bdThisYear]= DATEADD(YEAR, Year(GETDATE()) - Year(birthday), birthday)
	from users where department in (SELECT id FROM cteDepsByParent) 
		and (DATEADD(YEAR, Year(GETDATE()) - Year(birthday), birthday)) < DATEADD(day, @days,GETDATE()) 
		and (DATEADD(YEAR, Year(GETDATE()) - Year(birthday), birthday)) > GETDATE()