package com.kexin.controller;

import com.kexin.dao.DepartmentDao;
import com.kexin.dao.EmployeeDao;
import com.kexin.pojo.Department;
import com.kexin.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    // controller应该调service层，这里偷懒直接调用dao层，不推荐
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAllEmployees();
        model.addAttribute("emps",employees);
        return "emp/list";//返回到list页面
    }

    @GetMapping("/emp")
    public String add(Model model) {
        //查出所有的部门信息,添加到departments中,用于前端接收
        Collection<Department> departments = departmentDao.getDepartment();
        model.addAttribute("departments", departments);
        return "emp/add";//返回到添加员工页面
    }
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        employeeDao.addEmployee(employee);//调用底层业务方法保存员工信息
        return "redirect:/emps";
    }

    // 去到员工的修改页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id, Model model) {
        //查出原来的数据
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp",employee);
        //查出所有部门的信息
        Collection<Department> department = departmentDao.getDepartment();
        model.addAttribute("departments",department);
        return "emp/update";
    }

    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee) {
        employeeDao.addEmployee(employee);
        return "redirect:/emps";
    }


}