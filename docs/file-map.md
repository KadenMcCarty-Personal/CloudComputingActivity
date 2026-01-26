# File Map

This document maps backend routes to frontend templates.
**This is the contract between Student 1 (Backend) and Student 2 (Frontend).**

> If a page does not appear in this file, it should not be implemented.

---

## Route to Template Mapping

| HTTP Method | Route         | Template         | Description              |
|-------------|---------------|------------------|--------------------------|
| GET         | /login        | login.html       | User login page          |
| GET         | /register     | register.html    | User registration page   |
| GET         | /admin/users  | userAdmin.html   | Admin user management    |
| GET         | /admin/users/edit/:id | userEdit.html | Admin edit user page |

---

## Template Summary

| Template         | Purpose                                      |
|------------------|----------------------------------------------|
| login.html       | Form for user authentication                 |
| register.html    | Form for new user registration               |
| userAdmin.html   | Table view of all users with edit/delete     |
| userEdit.html    | Form to edit a specific user's details       |
