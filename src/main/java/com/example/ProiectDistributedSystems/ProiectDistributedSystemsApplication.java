package com.example.ProiectDistributedSystems;

import com.example.ProiectDistributedSystems.model.*;
import com.example.ProiectDistributedSystems.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ProiectDistributedSystemsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProiectDistributedSystemsApplication.class, args);
    }


    @Bean
    CommandLineRunner init(UserRepository userRepository,
                           DeviceRepository deviceRepository,
                           SensorRepository sensorRepository,
                           AdminRepository adminRepository,
                           LogRepository logRepository) {
        return args -> {

            Admin admin = Admin.builder()
                    .username("admin")
                    .password("admin")
                    .build();
            adminRepository.save(admin);


            Device device = Device.builder()
                    .address("SomeDeviceAdress")
                    .description("SomeDeviceDescription")
                    .avgConsumption(15d)
                    .maxConsumption(25d)
                    .build();

            User user = User.builder()
                    .password("Hudi")
                    .username("Hudi")
                    .address("cevaAdresa")
                    .birthDate("cavaNastere")
                    .build();

            Sensor sensor = Sensor.builder()
                    .description("SomeSensor1")
                    .maximumValueMonitored(25d)
                    .build();

            sensorRepository.save(sensor);
            deviceRepository.save(device);
            userRepository.save(user);

            user = userRepository.findFirstByUsername("Hudi");
            device = deviceRepository.findFirstByDescriptionAndAddress("SomeDeviceDescription","SomeDeviceAdress");
            sensor = sensorRepository.findFirstByDescription("SomeSensor1");

            Set<Log> logs = new HashSet<>();
            Log log1 = Log.builder()
                    .date("Sat Dec 11 2021 10:00:00 GMT+0200 (Eastern European Standard Time)")
                    .sensor(sensor)
                    .value(10d)
                    .build();
            Log log2 = Log.builder()
                    .date("Sat Dec 11 2021 11:00:00 GMT+0200 (Eastern European Standard Time)")
                    .sensor(sensor)
                    .value(12d)
                    .build();
            Log log3 = Log.builder()
                    .date("Sat Dec 11 2021 12:00:00 GMT+0200 (Eastern European Standard Time)")
                    .sensor(sensor)
                    .value(8d)
                    .build();
            Log log4 = Log.builder()
                    .date("Sat Dec 11 2021 13:00:00 GMT+0200 (Eastern European Standard Time)")
                    .sensor(sensor)
                    .value(11d)
                    .build();
            Log log5 = Log.builder()
                    .date("Sat Dec 11 2021 14:00:00 GMT+0200 (Eastern European Standard Time)")
                    .sensor(sensor)
                    .value(11d)
                    .build();
            Log log6 = Log.builder()
                    .date("Sat Dec 11 2021 15:00:00 GMT+0200 (Eastern European Standard Time)")
                    .sensor(sensor)
                    .value(11d)
                    .build();
            logRepository.save(log1);
            logRepository.save(log2);
            logRepository.save(log3);
            logRepository.save(log4);
            logRepository.save(log5);
            logRepository.save(log6);

            logs.add(log1);
            logs.add(log2);
            logs.add(log3);
            logs.add(log4);
            logs.add(log5);
            logs.add(log6);


            sensor.setLogs(logs);
            sensor.setDevice(device);

            device.setSensor(sensor);
            device.setUser(user);


            Set<Device> devices = new HashSet<>();
            devices.add(device);
            user.setDevices(devices);

            sensorRepository.save(sensor);
            deviceRepository.save(device);
            userRepository.save(user);


//            User user = User.builder()
//                    .username("costel")
//                    .password("1234")
//                    .birthDate("20/12/1999")
//                    .address("strada aviatorilor")
//                    .isAdmin(true)
//                    .build();
//
//            userRepository.save(user);
//            User user2 = User.builder()
//                    .username("costelus")
//                    .password("1234")
//                    .birthDate("20/12/1999")
//                    .address("strada aviatorilor")
//                    .isAdmin(false)
//                    .build();
//
//            userRepository.save(user2);
//            User u = userRepository.findFirstByUsername("costelus");
//            Sensor s = new Sensor(null, "20d", 20.0, null, null);
//
//            Device device = Device.builder()
//                    .user(user2)
//                    .description("just a device 222")
//                    .avgConsumption(20.0d)
//                    .maxConsumption(100.0d)
//                    .address("strada portelanuluiasdf")
//                    .sensor(s)
//                    .build();
//
//            deviceRepository.save(device);
//
//            System.out.println("admins " +
//                    adminRepository.findFirstByUsernameAndPassword("admin","admin").toString());
//
//            sensorRepository.save(s);
//			userRepository.delete(u);
        };
    }

}
