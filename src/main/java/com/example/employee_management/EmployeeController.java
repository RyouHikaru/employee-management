package com.example.employee_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping({"/showEmployees", "/", "/list"})
    public ModelAndView showEmployees() {
        ModelAndView modelAndView = new ModelAndView("list-employees");
        List<Employee> employeeList = employeeRepository.findAll();
        modelAndView.addObject("employees", employeeList);
        return modelAndView;
    }

    @GetMapping({"/addEmployeeForm"})
    public ModelAndView addEmployeeForm() {
        ModelAndView modelAndView = new ModelAndView("add-employee-form");
        Employee newEmployee = new Employee();
        modelAndView.addObject("employee", newEmployee);
        return modelAndView;
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long employeeId) {
        ModelAndView modelAndView = new ModelAndView("add-employee-form");
        Employee employee = employeeRepository.findById(employeeId).get();
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam Long employeeId) {
        employeeRepository.deleteById(employeeId);
        return "redirect:/list";
    }
}
