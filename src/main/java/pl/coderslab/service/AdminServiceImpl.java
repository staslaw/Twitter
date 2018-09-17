package pl.coderslab.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Admin;
import pl.coderslab.entity.Teacher;
import pl.coderslab.repository.AdminRepository;
import pl.coderslab.repository.RoleRepository;


@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository,
                              RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public Admin findByAdminName(String name) {
        return adminRepository.findByUsername(name);
    }


    @Override
    public void saveAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);
    }
}

