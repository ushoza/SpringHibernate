select u.id, u.first_name, u.last_name, u.department, u.birthday, d.NameDep from users  u
left join departments d on u.department = d.id