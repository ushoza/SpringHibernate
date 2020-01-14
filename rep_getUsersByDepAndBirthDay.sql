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

	select * from [dbo].[users] where department in (SELECT id FROM cteDepsByParent)
end
--exec rep_getUsersByDepAndBirthDay 11, ''