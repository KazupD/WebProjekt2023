insert into admin (adminname, adminpwd) values ("username", "password");
insert into admintoken (token) values ("secretkey");

insert into product (id, name, image_name, brand, power, price, type)
values (1, "StronK X1", "motor_img1", "ThunderTorque", 5000, 2099, "AC");
insert into product (id, name, image_name, brand, power, price, type)
values (2, "StronK L1", "motor_img2", "ThunderTorque", 3000, 1299, "AC");
insert into product (id, name, image_name, brand, power, price, type)
values (3, "StronK S1", "motor_img3", "ThunderTorque", 1500, 999, "AC");
insert into product (id, name, image_name, brand, power, price, type)
values (4, "AmperMaster A18", "motor_img4", "MegaWattMaster", 1800, 899, "AC");
insert into product (id, name, image_name, brand, power, price, type)
values (5, "AmperMaster A12", "motor_img5", "MegaWattMaster", 1200, 799, "AC");
insert into product (id, name, image_name, brand, power, price, type)
values (6, "AmperMaster A5", "motor_img6", "MegaWattMaster", 500, 399, "AC");
insert into product (id, name, image_name, brand, power, price, type)
values (7, "Pulse P30", "motor_img7", "FluxForceMax", 3000, 1599, "AC");
insert into product (id, name, image_name, brand, power, price, type)
values (8, "Pulse P80", "motor_img8", "FluxForceMax", 8000, 2599, "AC");
insert into product (id, name, image_name, brand, power, price, type)
values (9, "Electra S", "motor_img9", "VoltMach", 45, 59, "DC");
insert into product (id, name, image_name, brand, power, price, type)
values (10, "Electra M", "motor_img10", "VoltMach", 100, 99, "DC");
insert into product (id, name, image_name, brand, power, price, type)
values (11, "Electra L", "motor_img11", "VoltMach", 200, 149, "DC");
insert into product (id, name, image_name, brand, power, price, type)
values (12, "Electra XL", "motor_img11", "VoltMach", 500, 199, "DC");


insert into acmotor (product_id, id, cosine_phi, nominal_current, nominal_rpm, nominal_voltage, slip, phase_number, pole_pairs)
values (1, 1, 0.9, 18.0, 1450, 400, 3.33, 3, 1);
insert into acmotor (product_id, id, cosine_phi, nominal_current, nominal_rpm, nominal_voltage, slip, phase_number, pole_pairs)
values (2, 2, 0.85, 13.0, 1450, 400, 3.33, 3, 1);
insert into acmotor (product_id, id, cosine_phi, nominal_current, nominal_rpm, nominal_voltage, slip, phase_number, pole_pairs)
values (3, 3, 0.92, 10.0, 1450, 400, 3.33, 3, 1);
insert into acmotor (product_id, id, cosine_phi, nominal_current, nominal_rpm, nominal_voltage, slip, phase_number, pole_pairs)
values (4, 4, 0.88, 17.0, 725, 400, 3.33, 3, 2);
insert into acmotor (product_id, id, cosine_phi, nominal_current, nominal_rpm, nominal_voltage, slip, phase_number, pole_pairs)
values (5, 5, 0.92, 13.0, 725, 400, 3.33, 3, 2);
insert into acmotor (product_id, id, cosine_phi, nominal_current, nominal_rpm, nominal_voltage, slip, phase_number, pole_pairs)
values (6, 6, 0.78, 6.0, 725, 400, 3.33, 3, 2);
insert into acmotor (product_id, id, cosine_phi, nominal_current, nominal_rpm, nominal_voltage, slip, phase_number, pole_pairs)
values (7, 7, 0.87, 21.0, 350, 400, 6.67, 3, 3);
insert into acmotor (product_id, id, cosine_phi, nominal_current, nominal_rpm, nominal_voltage, slip, phase_number, pole_pairs)
values (8, 8, 0.91, 30.0, 350, 400, 6.67, 3, 3);

insert into dcmotor (product_id, id, nominal_rpm, nominal_voltage, power_factor, stall_current, steady_state_current, stall_torque, steady_state_torque, commutator_segments)
values (9, 9, 1500, 12.0, 0.7, 15.0, 2.0, 10.0, 0.5, 20);
insert into dcmotor (product_id, id, nominal_rpm, nominal_voltage, power_factor, stall_current, steady_state_current, stall_torque, steady_state_torque, commutator_segments)
values (10, 10, 2000, 20.0, 0.75, 18.0, 3.0, 13.0, 0.8, 18);
insert into dcmotor (product_id, id, nominal_rpm, nominal_voltage, power_factor, stall_current, steady_state_current, stall_torque, steady_state_torque, commutator_segments)
values (11, 11, 2500, 24.0, 0.73, 20.0, 4.0, 18.0, 1.4, 24);
insert into dcmotor (product_id, id, nominal_rpm, nominal_voltage, power_factor, stall_current, steady_state_current, stall_torque, steady_state_torque, commutator_segments)
values (12, 12, 3000, 50.0, 0.68, 30.0, 5.0, 27.0, 3.0, 12);