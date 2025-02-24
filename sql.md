## Top 10 SQL Interview Queries

### 1. Find the Second Highest Salary
```sql
SELECT MAX(salary) AS second_highest_salary
FROM employees
WHERE salary < (SELECT MAX(salary) FROM employees);
```
✅ Uses `MAX()` with a `WHERE` condition to exclude the highest salary.

---

### 2. Find Employees with the Highest Salary in Each Department
```sql
SELECT department_id, employee_name, salary
FROM employees e1
WHERE salary = (
    SELECT MAX(salary)
    FROM employees e2
    WHERE e1.department_id = e2.department_id
);
```
✅ Uses a correlated subquery to fetch the maximum salary for each department.

---

### 3. Find Duplicate Records in a Table
```sql
SELECT column_name, COUNT(*)
FROM table_name
GROUP BY column_name
HAVING COUNT(*) > 1;
```
✅ Uses `GROUP BY` and `HAVING COUNT(*) > 1` to find duplicates.

---

### 4. Find the Nth Highest Salary (Using LIMIT & OFFSET - MySQL/PostgreSQL)
```sql
SELECT DISTINCT salary
FROM employees
ORDER BY salary DESC
LIMIT 1 OFFSET N-1;
```
✅ Uses `LIMIT` with `OFFSET` to fetch the Nth highest salary.

---

### 5. Find Employees Who Earn More Than Their Manager
```sql
SELECT e.employee_name, e.salary, m.salary AS manager_salary
FROM employees e
JOIN employees m ON e.manager_id = m.employee_id
WHERE e.salary > m.salary;
```
✅ Uses `SELF JOIN` to compare an employee's salary with their manager’s.

---

### 6. Find the Department with the Most Employees
```sql
SELECT department_id, COUNT(*) AS num_employees
FROM employees
GROUP BY department_id
ORDER BY num_employees DESC
LIMIT 1;
```
✅ Uses `COUNT(*)` and `ORDER BY DESC` to find the department with the most employees.

---

### 7. Retrieve the Employee Count per Department
```sql
SELECT department_id, COUNT(*) AS employee_count
FROM employees
GROUP BY department_id;
```
✅ Uses `GROUP BY` to count employees per department.

---

### 8. Retrieve Employees Who Joined in the Last 6 Months
```sql
SELECT *
FROM employees
WHERE join_date >= DATE_SUB(CURDATE(), INTERVAL 6 MONTH);
```
✅ Uses `DATE_SUB` to filter employees based on their joining date.

---

### 9. Find Employees Without a Manager (NULL values in a Foreign Key Column)
```sql
SELECT employee_name
FROM employees
WHERE manager_id IS NULL;
```
✅ Uses `IS NULL` to filter employees who don’t have a manager.

---

### 10. Find Customers Who Have Placed More Than 3 Orders
```sql
SELECT customer_id, COUNT(*) AS order_count
FROM orders
GROUP BY customer_id
HAVING COUNT(*) > 3;
```
✅ Uses `HAVING COUNT(*) > 3` to filter customers who have placed more than 3 orders.

