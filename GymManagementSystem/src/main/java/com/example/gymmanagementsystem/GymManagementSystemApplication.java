package com.example.gymmanagementsystem;

import com.example.gymmanagementsystem.entity.User;
import com.example.gymmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class GymManagementSystemApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(GymManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<User> existingAdmin = userRepository.findByUsername("admin");
		if (existingAdmin.isEmpty()) {
			User admin = new User();
			admin.setUsername("admin");
			admin.setPassword("123456"); // ⚠️ 开发阶段明文密码，生产建议加密
			admin.setRole("admin");
			userRepository.save(admin);
			System.out.println("✅ 已创建默认管理员账户：admin / 123456");
		} else {
			System.out.println("✅ 默认管理员已存在，无需重复添加");
		}
	}
}
