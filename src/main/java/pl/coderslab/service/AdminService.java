package pl.coderslab.service;

import pl.coderslab.entity.Admin;

public interface AdminService {
    Admin findByAdminName(String name);

    void saveAdmin(Admin admin);
}
