IF object_id('[dbo].[rep_getUsersByDepAndBirthDay]') IS NOT NULL
BEGIN
    DROP procedure [dbo].[rep_getUsersByDepAndBirthDay]
END
GO


-- =============================================
-- Author:		<Author,Pyatak Nataliya>
-- Create date: <Create Date,,>
-- Description:	<Получить пользователей с учетом отдела и дня рождения>
-- =============================================
create PROCEDURE [dbo].[rep_getUsersByDepAndBirthDay]
(
    @dep bigint,
    @days  int
)
--with execute as owner
AS
BEGIN
SET ANSI_WARNINGS ON;
	WITH
  cteDepsByParent (id, id_parent, NameDep)
  AS
  (SELECT id, id_parent, NameDep
    FROM [dbo].[departments]
    WHERE id = @dep

    UNION ALL

    SELECT e.id, e.id_parent, e.NameDep
    FROM [dbo].[departments] e
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
	from [dbo].[users] where department in (SELECT id FROM cteDepsByParent) 
		and (DATEADD(YEAR, Year(GETDATE()) - Year(birthday), birthday)) < DATEADD(day, @days,GETDATE()) 
		and (DATEADD(YEAR, Year(GETDATE()) - Year(birthday), birthday)) > GETDATE()
end
--exec [dbo].[rep_getUsersByDepAndBirthDay]  11, 60