CREATE TABLE `employee`(
	`id` 			VARCHAR(20) PRIMARY KEY,
	`login`			VARCHAR(100) NOT NULL,
	`firstname`		VARCHAR(100) NOT NULL,
	`lastname` 		VARCHAR(100) NOT NULL,
	`password` 		VARCHAR(100) NOT NULL,
	`mail` 			VARCHAR(100) NOT NULL,
	`phone` 		VARCHAR(15) NOT NULL,
	`birth` 		DATE NOT NULL,
	`sex` 		    int NOT NULL DEFAULT 1,
	`role` 			int NOT NULL DEFAULT 1,
	`is_actif`  	int NOT NULL DEFAULT 1
);

/* Table employee*/
INSERT INTO `employee` (`id`, `login`, `firstname`, `lastname`, `password`, `mail`, `phone`, `role`, `nb_person`) VALUES
  ('usr-023-001', 'luffy', 'Luffy', 'Monkey D', '12345678', 'luffy@gmail.com', '6 90 18 53 35', 1, 3),
  ('usr-024-002', 'perona', 'Sama', 'Perona', 'Aqszaqsz0', 'perona@queen.com', '6 90 28 47 16', 1, 1);

  
  CREATE TABLE `supplier`(
  	`id` 			VARCHAR(20) PRIMARY KEY,
  	`firstname`		VARCHAR(100) NOT NULL,
  	`lastname` 		VARCHAR(100) NOT NULL,
  	`description` 	TEXT NOT NULL,
  	`phone` 		VARCHAR(15) NOT NULL,
  	`mail` 		    VARCHAR(100),
  	`is_actif`  	int NOT NULL DEFAULT 1
  );

  CREATE TABLE `customer`(
  	`id` 			VARCHAR(20) PRIMARY KEY,
  	`firstname`		VARCHAR(100) NOT NULL,
  	`lastname` 		VARCHAR(100) NOT NULL,
  	`phone` 		VARCHAR(15) NOT NULL,
  	`mail` 		    VARCHAR(100),
  	`is_actif`  	int NOT NULL DEFAULT 1
  );
  INSERT INTO `customer` (`id`, `firstname`, `lastname`, `phone`, `mail`, `is_actif`) VALUES
  ('cst-024-001', 'Brondon', 'NJOTSA', '6 90 18 53 35', 'brondonnjotsa@gmail.com', '1'),
  ('cst-024-002', 'Makam Audrey', 'KAMMEGNE', '6 98 50 65 78', 'makam@gmail.com', '1'),
  ('cst-024-003', 'Nick Larson', 'PIEBIENG', '6 46 65 48 64', 'nick@gmail.com', '1'),
  ('cst-024-004', 'Mamiafo', 'Gnitedemg', '6 54 64 40 50', 'mamiafo@gmail.com', '1');

  INSERT INTO `customer` (`id`, `firstname`, `lastname`, `phone`, `mail`, `is_actif`) VALUES
  ('cst-024-005', 'Elodie Erin', 'BABAN', '6 94 054 65 08', 'elodie@gmail.com', '1'),
  ('cst-024-006', 'D Luffy ', 'MONKEY', '6 87 04 54 04', 'luffy@gmail.com', '1'),
  ('cst-024-007', 'Zorro', 'RORONOA', '6 87 50 65 48', 'zorro@gmail.com', '1');

  INSERT INTO `customer` (`id`, `firstname`, `lastname`, `phone`, `mail`, `is_actif`) VALUES
  ('cst-024-008', 'Sanji', 'VINSMOOKE', '6 50 45 04 65', 'sanji@gmail.com', '1'),
  ('cst-024-009', 'Linlin', 'CHARLOTTE', '6 80 65 68 40', 'linlin@gmail.com', '1'),
  ('cst-024-010', 'Katakuri', 'CHARLOTTE', '6 80 98 50 07', 'katakuri@gmail.com', '1');

  INSERT INTO `customer` (`id`, `firstname`, `lastname`, `phone`, `mail`, `is_actif`) VALUES
  ('cst-024-011', 'D Roger', 'GOLD', '6 80 65 04 80', 'roger@gmail.com', '1'),
  ('cst-024-012', 'Rayleight', 'SYLVER', '6 80 50 65 87', 'rayleight@gmail.com', '1'),
  ('cst-024-013', 'Gaban', 'SCOPPER', '6 90 80 48 98', 'gaban@gmail.com', '1');

  CREATE TABLE `product`(
  	`id` 			VARCHAR(20) PRIMARY KEY,
  	`name`		    VARCHAR(100) NOT NULL,
  	`barcode` 		VARCHAR(100) NOT NULL,
  	`category` 		VARCHAR(100) NOT NULL,
  	`description` 	TEXT NOT NULL,
  	`base_price` 	int NOT NULL,
  	`min_price` 	int NOT NULL,
  	`is_actif`  	int NOT NULL DEFAULT 1
  );

  CREATE TABLE `lot`(
  	`id` 			    VARCHAR(20) PRIMARY KEY,
  	`product` 		    VARCHAR(20) NOT NULL,
  	`num_lot`		    VARCHAR(100) NOT NULL,
  	`quantity` 		    int NOT NULL,
  	`variant` 		    VARCHAR(20),
  	`delivery_date`     DATE NOT NULL,
  	`expiration_date` 	DATE,
  	`supplier` 		    VARCHAR(20) NOT NULL,
  	`price`     	    int NOT NULL,
  	`status` 	        int NOT NULL,
  	`is_actif`  	    int NOT NULL DEFAULT 1,
  	FOREIGN KEY (product) REFERENCES product(id),
  	FOREIGN KEY (supplier) REFERENCES supplier(id)
  );

  CREATE TABLE `invoice`(
  	`id` 			    VARCHAR(20) PRIMARY KEY,
  	`created_date` 		DATE DEFAULT CURRENT_TIMESTAMP,
  	`employee`		        VARCHAR(20) NOT NULL,
  	`customer`		    VARCHAR(20),
  	`price` 		    int NOT NULL,
  	`is_actif`  	    int NOT NULL DEFAULT 1,
  	FOREIGN KEY (employee) REFERENCES employee(id),
  	FOREIGN KEY (customer) REFERENCES customer(id)
  );

  CREATE TABLE `line_invoice`(
  	`id` 			    VARCHAR(20) PRIMARY KEY,
  	`invoice` 		    VARCHAR(20) NOT NULL,
  	`lot` 		        VARCHAR(20) NOT NULL,
  	`quantity`		    VARCHAR(20),
  	`price` 		    int NOT NULL,
  	`is_actif`  	    int NOT NULL DEFAULT 1,
  	FOREIGN KEY (invoice) REFERENCES invoice(id),
  	FOREIGN KEY (lot) REFERENCES lot(id)
  );

